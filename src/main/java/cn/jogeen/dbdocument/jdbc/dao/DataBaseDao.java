package cn.jogeen.dbdocument.jdbc.dao;

import cn.jogeen.dbdocument.jdbc.model.Column;
import cn.jogeen.dbdocument.jdbc.model.Table;

import java.util.List;

public interface DataBaseDao {

    public List<Table> showTables(String database);

    public List<Column> showColumns(String database, String tableName);

    public void closeConnection();
}
