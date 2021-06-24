package cn.jogeen.dbdocument.jdbc.model;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

@Data
public class Column {
    @ExcelProperty("序号")
    @ColumnWidth(10)
    private String ordinalPosition;

    @ExcelProperty("列名")
    @ColumnWidth(20)
    private String columnName;

    @ExcelProperty("类型")
    @ColumnWidth(30)
    private String columnType;

    @ExcelProperty("是否为空")
    @ColumnWidth(15)
    private String isNullable;

    @ExcelProperty("默认值")
    @ColumnWidth(10)
    private String columnDefault;

    @ExcelProperty("描述")
    @ColumnWidth(50)
    private String columnComment;

    @Override
    public String toString() {
        return "Column{" +
                "ordinalPosition='" + ordinalPosition + '\'' +
                ", columnName='" + columnName + '\'' +
                ", isNullable='" + isNullable + '\'' +
                ", columnType='" + columnType + '\'' +
                ", columnDefault='" + columnDefault + '\'' +
                ", columnComment='" + columnComment + '\'' +
                '}';
    }
}
