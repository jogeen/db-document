package cn.jogeen.dbdocument.excel;

import cn.jogeen.dbdocument.excel.model.Index;
import cn.jogeen.dbdocument.jdbc.model.Column;
import cn.jogeen.dbdocument.jdbc.model.Table;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ExcelService {

    public void buildExcel(String path, String database, List<Table> tables) {
        //创建目录
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(path + "/" + database + ".xls");
        List<Index> list = new ArrayList<Index>();
        for (Table table : tables) {
            Index index = new Index();
            index.setTableName(table.getTableName());
            index.setTableComment(table.getTableComment());
            list.add(index);
        }
        ExcelWriter excelWriter = EasyExcel.write(file).build();
        WriteSheet writeSheet = EasyExcel.writerSheet(0, "数据库表格目录").registerWriteHandler(new IndexWriteHandler()).head(Index.class).build();
        excelWriter.write(list, writeSheet);
        for (int i = 0; i < tables.size(); i++) {
            Table table = tables.get(i);
            String sheetName = table.getTableName();
            WriteSheet writeSheetTemp = EasyExcel.writerSheet(i + 1, sheetName).head(Column.class).build();
            excelWriter.write(table.getColumnList(), writeSheetTemp);
        }
        excelWriter.finish();
    }
}
