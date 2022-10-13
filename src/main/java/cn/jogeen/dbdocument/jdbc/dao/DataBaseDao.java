package cn.jogeen.dbdocument.jdbc.dao;

import cn.jogeen.dbdocument.jdbc.model.Column;
import cn.jogeen.dbdocument.jdbc.model.Table;

import java.util.List;

public interface DataBaseDao {

     List<Table> showTables(String database);

     List<Column> showColumns(String database, String tableName);

     void closeConnection();
}
