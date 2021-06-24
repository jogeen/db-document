package cn.jogeen.dbdocument.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

@Data
public class Index {
    @ExcelProperty("表名字")
    @ColumnWidth(30)
    private String tableName;
    @ExcelProperty("描述")
    @ColumnWidth(50)
    private String tableComment;

}
