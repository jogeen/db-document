package cn.jogeen.dbdocument.jdbc.connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionUtils {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";


    public static Connection getConnection(String ip, String port, String databaseName, String username, String password) {
        String dbUrl = String.format("jdbc:mysql://%s:%s/%s?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", ip, port, databaseName);
        Connection conn = null;
        // 注册 JDBC 驱动
        try {
            Class.forName(JDBC_DRIVER);
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(dbUrl, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }


    public static List<String> testConnection(String ip, String port, String username, String password) throws ClassNotFoundException, SQLException {
        List<String> database = new ArrayList<String>();
        String dbUrl = String.format("jdbc:mysql://%s:%s?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", ip, port);
        // 注册 JDBC 驱动
        Class.forName(JDBC_DRIVER);
        // 打开链接
        System.out.println("连接数据库...");
        Connection connection = DriverManager.getConnection(dbUrl, username, password);
        if (connection != null) {
             database = showDataBase(connection);
            connection.close();
        }
        return database;
    }


    public static List<String> showDataBase(Connection conn) {
        List<String> list = new ArrayList<String>();
        String sql = "show databases";
        ResultSet rs = null;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                String name = rs.getString(1);
                list.add(name);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        List<String> strings = testConnection("172.32.6.176", "3306", "stp", "jD360$sa");
        System.out.println(strings);
    }
}
