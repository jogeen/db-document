package cn.jogeen.dbdocument.excel;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import java.util.List;

public class IndexWriteHandler implements CellWriteHandler {
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer integer, Integer integer1, Boolean aBoolean) {
    }

    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer integer, Boolean aBoolean) {
    }

    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> list, Cell cell, Head head, Integer integer, Boolean aBoolean) {
        if (cell.getRowIndex() >= 1 && cell.getColumnIndex() == 0) {
            Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
            CreationHelper helper = workbook.getCreationHelper();
            Hyperlink hyperlink = helper.createHyperlink(HyperlinkType.DOCUMENT);
            hyperlink.setAddress("#"+cell.getStringCellValue()+"!A1");
            CellStyle link_style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setUnderline(Font.U_SINGLE);
            font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
            link_style.setFont(font);
            cell.setHyperlink(hyperlink);
            cell.setCellStyle(link_style);
            System.out.println(cell.getStringCellValue());
        }

    }
}
