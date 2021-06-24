package cn.jogeen.dbdocument.jdbc.model;


import lombok.Data;

import java.util.List;

@Data
public class Table {
    private String database;
    private String tableName;
    private String tableComment;
    List<Column> columnList;
}
