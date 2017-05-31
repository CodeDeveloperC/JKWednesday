package poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by root on 2017/5/26.
 */
public class TestPOIDemo {
    private Workbook workbook;

    @Test
    public void HSSF() throws IOException {
        //1.创建一个工作簿Excel文件
        Workbook workbook = new HSSFWorkbook();
        //2.创建一个工作表sheet
        Sheet sheet = workbook.createSheet();
        //3.创建一个行对象Row
        Row nRow = sheet.createRow(4);
        //4.创建一个单元格对象，制定列
        Cell nCell = nRow.createCell(3);
        //5.给单元格设置内容
        nCell.setCellValue("JAVAEE服务器");
        //6.保存
        OutputStream outputStream= new FileOutputStream("D:\\1.xls");
        workbook.write(outputStream);
        //7.关闭
        outputStream.close();
    }

    //带样式
    @Test
    public void HSSFstyleMore() throws IOException {
        //1.创建一个工作簿Excel文件
        Workbook workbook = new HSSFWorkbook();
        //2.创建一个工作表sheet
        Sheet sheet = workbook.createSheet();
        //3.创建一个行对象Row
        Row nRow = sheet.createRow(4);
        //4.创建一个单元格对象，制定列
        Cell nCell = nRow.createCell(3);
        //5.给单元格设置内容
        nCell.setCellValue("JAVAEE服务器");

        //创建一个单元格样式
        CellStyle cellStyle=workbook.createCellStyle();
        //创建一个字体对象
        Font titleFont=workbook.createFont();
        //设置字体
        titleFont.setFontName("华文行楷");

        titleFont.setFontHeightInPoints((short)26);

        cellStyle.setFont(titleFont);
        nCell.setCellStyle(cellStyle);

        //载创建一个单元格
        Row XRow = sheet.createRow(5);
        Cell Xcell = XRow.createCell(6);

        CellStyle textStyle = workbook.createCellStyle();
        Font textFont = workbook.createFont();

        textFont.setFontName("Times New Roman");
        textFont.setFontHeightInPoints((short)14);

        textStyle.setFont(textFont);
        Xcell.setCellValue("java.itcast.cn");
        Xcell.setCellStyle(textStyle);

        //6.保存
        OutputStream outputStream= new FileOutputStream("D:\\1.xls");
        workbook.write(outputStream);
        //7.关闭
        outputStream.close();
    }

    //带样式
    @Test
    public void HSSFstyle() throws IOException {
        //1.创建一个工作簿Excel文件
        Workbook workbook = new HSSFWorkbook();
        //2.创建一个工作表sheet
        Sheet sheet = workbook.createSheet();
        //3.创建一个行对象Row
        Row nRow = sheet.createRow(4);
        //4.创建一个单元格对象，制定列
        Cell nCell = nRow.createCell(3);
        //5.给单元格设置内容
        nCell.setCellValue("JAVAEE服务器");

        //创建一个单元格样式
        CellStyle cellStyle=workbook.createCellStyle();
        //创建一个字体对象
        Font titleFont=workbook.createFont();
        //设置字体
        titleFont.setFontName("华文行楷");

        titleFont.setFontHeightInPoints((short)26);

        cellStyle.setFont(titleFont);
        nCell.setCellStyle(cellStyle);

        //载创建一个单元格
        nRow = sheet.createRow(5);
        nCell = nRow.createCell(6);

        CellStyle textStyle = workbook.createCellStyle();
        Font textFont = workbook.createFont();

        textFont.setFontName("Times New Roman");
        textFont.setFontHeightInPoints((short)14);

        textStyle.setFont(textFont);
        nCell.setCellValue("java.itcast.cn");
        nCell.setCellStyle(textStyle);

        //6.保存
        OutputStream outputStream= new FileOutputStream("D:\\1.xls");
        workbook.write(outputStream);
        //7.关闭
        outputStream.close();
    }

    //带样式
    @Test
    public void HSSFstyleLess() throws IOException {
        //1.创建一个工作簿Excel文件
        Workbook workbook = new HSSFWorkbook();
        //2.创建一个工作表sheet
        Sheet sheet = workbook.createSheet();
        //3.创建一个行对象Row
        Row nRow = sheet.createRow(4);
        //4.创建一个单元格对象，制定列
        Cell nCell = nRow.createCell(3);
        //5.给单元格设置内容
        nCell.setCellValue("JAVAEE服务器");

        //创建一个单元格样式
        CellStyle nStyle=workbook.createCellStyle();
        //创建一个字体对象
        Font nFont=workbook.createFont();
        //设置字体
        nFont.setFontName("华文行楷");

        nFont.setFontHeightInPoints((short)26);
        nStyle.setFont(nFont);
        nCell.setCellStyle(nStyle);

        //载创建一个单元格
        nRow = sheet.createRow(5);
        nCell = nRow.createCell(6);
        nCell.setCellValue("java.itcast.cn");

        //创建一个单元格样式
        nStyle=workbook.createCellStyle();
        //创建一个字体对象
        nFont=workbook.createFont();

        nFont.setFontName("Times New Roman");

        nFont.setFontHeightInPoints((short)14);
        nStyle.setFont(nFont);
        nCell.setCellStyle(nStyle);

        //6.保存
        OutputStream outputStream= new FileOutputStream("D:\\1.xls");
        workbook.write(outputStream);
        //7.关闭
        outputStream.close();
    }

    //带样式
    @Test
    public void HSSFstyleLess02() throws IOException {
        //1.创建一个工作簿Excel文件
        Workbook workbook = new HSSFWorkbook();
        //2.创建一个工作表sheet
        Sheet sheet = workbook.createSheet();
        //3.创建一个行对象Row
        Row nRow = sheet.createRow(4);
        //4.创建一个单元格对象，制定列
        Cell nCell = nRow.createCell(3);
        //5.给单元格设置内容
        nCell.setCellValue("JAVAEE服务器");

        //创建一个单元格样式
        CellStyle nStyle=workbook.createCellStyle();
        //创建一个字体对象
        Font nFont=workbook.createFont();

        nCell.setCellStyle(this.titleStyle(nFont,nStyle));

        //载创建一个单元格
        nRow = sheet.createRow(5);
        nCell = nRow.createCell(6);
        nCell.setCellValue("java.itcast.cn");

        //创建一个单元格样式,(样式初始化)
        nStyle=workbook.createCellStyle();
        //创建一个字体对象
        nFont=workbook.createFont();


        nCell.setCellStyle(this.textStyle(nFont,nStyle));

        //6.保存
        OutputStream outputStream= new FileOutputStream("D:\\1.xls");
        workbook.write(outputStream);
        //7.关闭
        outputStream.close();
    }



    //标题样式
    public CellStyle titleStyle(Font nFont,CellStyle nStyle){
        //设置字体
        nFont.setFontName("华文行楷");

        nFont.setFontHeightInPoints((short)26);
        nStyle.setFont(nFont);

        return nStyle;
    }

    //字体样式
    public CellStyle textStyle(Font nFont,CellStyle nStyle){
        //设置字体
        nFont.setFontName("Times New Roman");

        nFont.setFontHeightInPoints((short)14);
        nStyle.setFont(nFont);

        return nStyle;
    }
}
