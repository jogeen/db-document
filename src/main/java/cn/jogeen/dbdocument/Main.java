package cn.jogeen.dbdocument;

import cn.jogeen.dbdocument.excel.ExcelService;
import cn.jogeen.dbdocument.jdbc.dao.DataBaseDaoImpl;
import cn.jogeen.dbdocument.jdbc.model.Column;
import cn.jogeen.dbdocument.jdbc.model.Table;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String database = "financerent_project_test";
        DataBaseDaoImpl dataBaseDao = new DataBaseDaoImpl("172.32.6.176", "3306", database, "stp", "jD360$sa");
        List<Table> tablse = dataBaseDao.showTables(database);
        for (Table table : tablse) {
            List<Column> columns = dataBaseDao.showColumns(database, table.getTableName());
            table.setColumnList(columns);
        }
        ExcelService excelService = new ExcelService();
        excelService.buildExcel("c:\\excel", database, tablse);
    }
}
