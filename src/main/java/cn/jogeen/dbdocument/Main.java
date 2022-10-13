package cn.jogeen.dbdocument;

import cn.jogeen.dbdocument.excel.ExcelService;
import cn.jogeen.dbdocument.jdbc.dao.DataBaseDaoImpl;
import cn.jogeen.dbdocument.jdbc.model.Column;
import cn.jogeen.dbdocument.jdbc.model.Table;
import cn.jogeen.dbdocument.ui.MainDialog;

import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //noUiStart();
        uiStart();
    }

    private static void noUiStart() {

        String ip ="localhost";
        Integer port = 3306;
        String database = "springboot_demo";
        String username = "root";
        String password = "1234";
        String path = "c:\\excel";

        DataBaseDaoImpl dataBaseDao = new DataBaseDaoImpl(ip, port, database, username, password);
        List<Table> tables = dataBaseDao.showTables(database);
        for (Table table : tables) {
            List<Column> columns = dataBaseDao.showColumns(database, table.getTableName());
            table.setColumnList(columns);
        }
        ExcelService excelService = new ExcelService();
        excelService.buildExcel(path, database, tables);
    }

    public static void uiStart(){
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//设置windows的窗口风格
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        MainDialog dialog = new MainDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
