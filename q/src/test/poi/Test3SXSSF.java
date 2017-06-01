package poi;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;

/**
 * Created by root on 2017/6/1.
 */
public class Test3SXSSF {
    @Test
    public void printXSSF() throws Exception{
        String xlsFile = "d:/outproduct/poiXSSFBigData.xlsx";
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("我的第一个工作簿");		//建立新的sheet对象

        Row nRow = null;
        Cell nCell   = null;

        for(int i=0;i<100;i++){
            nRow = sheet.createRow(i);
            for(int j=0;j<20;j++){
                nCell = nRow.createCell(j);
                nCell.setCellValue("我是单元格传智播客");
                nCell.setCellStyle(style(wb));
            }
            System.out.println(i);
        }
        FileOutputStream fOut = new FileOutputStream(xlsFile);
        wb.write(fOut);
        fOut.flush();
        fOut.close();

        System.out.println("finish.");
    }

    //设置单元格样式
    private CellStyle style(Workbook wb){
        CellStyle curStyle = wb.createCellStyle();
        Font curFont = wb.createFont();								//设置字体
        curFont.setFontName("微软雅黑");								//设置英文字体
        curFont.setCharSet(XSSFFont.DEFAULT_CHARSET);					//设置中文字体，那必须还要再对单元格进行编码设置
        curFont.setFontHeightInPoints((short)10);						//字体大小
        curStyle.setFont(curFont);

        curStyle.setBorderTop(XSSFCellStyle.BORDER_THICK);				//粗实线
        curStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);			//实线
        curStyle.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);			//比较粗实线
        curStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);				//实线

        curStyle.setWrapText(true);  									//换行
        curStyle.setAlignment(XSSFCellStyle.ALIGN_RIGHT);				//横向具右对齐
        curStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);	//单元格垂直居中

        return curStyle;
    }
}
