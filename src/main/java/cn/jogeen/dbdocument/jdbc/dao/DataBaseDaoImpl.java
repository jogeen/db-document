package cn.jogeen.dbdocument.jdbc.dao;

import cn.jogeen.dbdocument.jdbc.connection.ConnectionUtils;
import cn.jogeen.dbdocument.jdbc.model.Column;
import cn.jogeen.dbdocument.jdbc.model.Table;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBaseDaoImpl implements DataBaseDao {

    private String ip;
    private Integer port;
    private String databaseName;
    private String username;
    private String password;
    private Connection conn;

    public DataBaseDaoImpl(String ip, Integer port, String databaseName, String username, String password) {
        this.ip = ip;
        this.port = port;
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;
        initConnection();
    }

    private void initConnection() {
        conn = ConnectionUtils.getConnection(ip, port, databaseName, username, password);
        if (conn == null) {
            throw new RuntimeException("链接数据库失败");
        }
    }

    @Override
    public List<Table> showTables(String database) {
        List<Table> list = new ArrayList<Table>();
        String sql = String.format("select table_name,Table_comment from information_schema.`TABLES` WHERE table_schema='%s'", database);
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                String tableName = rs.getString(1);
                String tableComment = rs.getString(2);
                Table table = new Table();
                table.setTableName(tableName);
                table.setTableComment(tableComment);
                list.add(table);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Column> showColumns(String database, String tableName) {
        List<Column> list = new ArrayList<Column>();
        String sql = String.format("SELECT ordinal_position,column_name,is_nullable,column_type,column_default,column_comment FROM information_schema.`COLUMNS` WHERE\n" +
                "\ttable_name = '%s' \n" +
                "\tAND table_schema = '%s'", tableName, database);
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                String ordinal_position = rs.getString("ordinal_position");
                String column_name = rs.getString("column_name");
                String is_nullable = rs.getString("is_nullable");
                String column_type = rs.getString("column_type");
                String column_default = rs.getString("column_default");
                String column_comment = rs.getString("column_comment");
                Column column = new Column();
                column.setOrdinalPosition(ordinal_position);
                column.setColumnName(column_name);
                column.setIsNullable(is_nullable);
                column.setColumnType(column_type);
                column.setColumnDefault(column_default);
                column.setColumnComment(column_comment);
                list.add(column);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
