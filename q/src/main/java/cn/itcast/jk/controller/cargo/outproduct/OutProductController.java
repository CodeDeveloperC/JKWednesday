package cn.itcast.jk.controller.cargo.outproduct;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.service.OutProductService;
import cn.itcast.jk.vo.OutProductVO;
import cn.itcast.util.DownloadUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by root on 2017/5/31.
 */
@Controller
public class OutProductController extends BaseController {
    @Resource
    OutProductService outProductService;


    //转向输入年月的界面
    @RequestMapping("/cargo/outproduct/toedit.action")
    public String toedit() {
        return "/cargo/outproduct/JOutProduct.jsp";
    }

    @RequestMapping("/cargo/outproduct/printNotemplate.action")
    public void printNotemplate(String inputDate, HttpServletResponse response) throws IOException { //inputDate格式：yyyy-MM

        List<OutProductVO> dataList = outProductService.find(inputDate);

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row nRow = null;
        Cell nCell = null;

        int rowNo = 0;//行号
        int colNo = 1;//列号

        //声明样式对象和字体对象
        CellStyle nStyle = workbook.createCellStyle();
        Font nFont = workbook.createFont();

        sheet.setColumnWidth(0, 2 * 300);  //列宽
        sheet.setColumnWidth(1, 26 * 300);//列宽BUG，底层设置不够精确，注意*256
        sheet.setColumnWidth(2, 12 * 300);
        sheet.setColumnWidth(3, 29 * 300);
        sheet.setColumnWidth(4, 10 * 300);
        sheet.setColumnWidth(5, 12 * 300);
        sheet.setColumnWidth(6, 8 * 300);
        sheet.setColumnWidth(7, 10 * 300);
        sheet.setColumnWidth(8, 10 * 300);
        sheet.setColumnWidth(9, 8 * 300);

        //大标题，合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 9));//开始行，结束行，开始列，结束列
        nRow = sheet.createRow(rowNo++);
        nRow.setHeightInPoints(36);
        nCell = nRow.createCell(1);

        nCell.setCellValue(inputDate.replaceFirst("-0", "-").replaceFirst("-", "年") + "月份出货表");
        nCell.setCellStyle(this.bigStyle(nFont, nStyle));


        String[] titles = new String[]{
                "客户",
                "订单号",
                "货号",
                "数量",
                "工厂",
                "附件",
                "工厂交期",
                "船期",
                "贸易条款"
        };

        nRow = sheet.createRow(rowNo++);
        nRow.setHeightInPoints(26.25f);

        //初始化
        nStyle = workbook.createCellStyle();
        nFont = workbook.createFont();

        //标题
        for (int i = 0; i < titles.length; i++) {
            nCell = nRow.createCell(i + 1);
            nCell.setCellValue(titles[i]);
            nCell.setCellStyle(this.titleStyle(nFont, nStyle));
        }

        //初始化
        nStyle = workbook.createCellStyle();
        nFont = workbook.createFont();

        //处理数据
        for (int i = 0; i < dataList.size(); i++) {
            colNo = 1;//初始化列号
            OutProductVO outProductVO = dataList.get(i);

            nRow = sheet.createRow(rowNo++);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(outProductVO.getCustomName());
            nCell.setCellStyle(this.textStyle(nFont, nStyle));

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(outProductVO.getContractNo());
            nCell.setCellStyle(this.textStyle(nFont, nStyle));

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(outProductVO.getProductNo());
            nCell.setCellStyle(this.textStyle(nFont, nStyle));

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(outProductVO.getCnumber());
            nCell.setCellStyle(this.textStyle(nFont, nStyle));

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(outProductVO.getFactoryName());
            nCell.setCellStyle(this.textStyle(nFont, nStyle));

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(outProductVO.getExts());
            nCell.setCellStyle(this.textStyle(nFont, nStyle));

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(outProductVO.getDeliverPeriod());
            nCell.setCellStyle(this.textStyle(nFont, nStyle));

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(outProductVO.getShipTime());
            nCell.setCellStyle(this.textStyle(nFont, nStyle));

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(outProductVO.getTradeTerms());
            nCell.setCellStyle(this.textStyle(nFont, nStyle));
        }


//        OutputStream outputStream = new FileOutputStream(new File("d:\\outproduct\\outproduct.xls"));
//        workbook.write(outputStream);
//        outputStream.flush();
//        outputStream.close();

        DownloadUtil downloadUtil = new DownloadUtil();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        downloadUtil.download(outputStream, response, "出货表.xls");

    }

    @RequestMapping("/cargo/outproduct/printHSSF.action")
    public void printHSSF(String inputDate, HttpServletResponse response, HttpServletRequest request) throws IOException {
        //inputDate格式：yyyy-MM

        //linux下jdk1.8 方法获取时，不会拼接自己写的目录
        String path = request.getSession().getServletContext().getRealPath("/") + "/make/xlsprint/";
        List<OutProductVO> dataList = outProductService.find(inputDate);
        FileInputStream inputStream = new FileInputStream(new File(path + "tOUTPRODUCT.xls"));

        Workbook workbook = new HSSFWorkbook(inputStream);//打开一个模板文件
        Sheet sheet = workbook.getSheetAt(0);//获取到第一个工作表
        Row nRow = null;
        Cell nCell = null;

        int rowNo = 0;//行号
        int colNo = 1;//列号


        //获取模板上的单元格格式
        nRow = sheet.getRow(2);

        //客户的样式
        nCell = nRow.getCell(1);
        CellStyle customStyle = nCell.getCellStyle();

        //订单的样式
        nCell = nRow.getCell(2);
        CellStyle contractNoStyle = nCell.getCellStyle();

        //货号的样式
        nCell = nRow.getCell(3);
        CellStyle productNoStyle = nCell.getCellStyle();

        //客户的样式
        nCell = nRow.getCell(4);
        CellStyle numStyle = nCell.getCellStyle();

        //客户的样式
        nCell = nRow.getCell(5);
        CellStyle factoryStyle = nCell.getCellStyle();

        //客户的样式
        nCell = nRow.getCell(6);
        CellStyle dateStyle = nCell.getCellStyle();

        //客户的样式
        nCell = nRow.getCell(8);
        CellStyle tradeStyle = nCell.getCellStyle();

        //大标题
        nRow = sheet.getRow(rowNo++);//获取一个行对象
        nCell = nRow.getCell(colNo);//获取一个单元格对象
        nCell.setCellValue(inputDate.replaceFirst("-0", "-").replaceFirst("-", "年") + "月份出货表");

        rowNo++;

        //处理数据
        for (int i = 0; i < dataList.size(); i++) {
            colNo = 1;//初始化列号
            OutProductVO outProductVO = dataList.get(i);

            nRow = sheet.createRow(rowNo++);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(outProductVO.getCustomName());
            nCell.setCellStyle(customStyle);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(outProductVO.getContractNo());
            nCell.setCellStyle(contractNoStyle);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(outProductVO.getProductNo());
            nCell.setCellStyle(productNoStyle);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(outProductVO.getCnumber());
            nCell.setCellStyle(numStyle);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(outProductVO.getFactoryName());
            nCell.setCellStyle(factoryStyle);

//            nCell = nRow.createCell(colNo++);
//            nCell.setCellValue(outProductVO.getExts());
//            nCell.setCellStyle(ex);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(outProductVO.getDeliverPeriod());
            nCell.setCellStyle(dateStyle);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(outProductVO.getShipTime());
            nCell.setCellStyle(dateStyle);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(outProductVO.getTradeTerms());
            nCell.setCellStyle(tradeStyle);
        }


//        OutputStream outputStream = new FileOutputStream(new File("d:\\outproduct\\outproduct.xls"));
//        workbook.write(outputStream);
//        outputStream.flush();
//        outputStream.close();

        DownloadUtil downloadUtil = new DownloadUtil();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        downloadUtil.download(outputStream, response, "出货表.xls");
        outputStream.flush();
        outputStream.close();

    }

    @RequestMapping("/cargo/outproduct/print.action")
    public void print(String inputDate, HttpServletResponse response, HttpServletRequest request) throws IOException {
        //inputDate格式：yyyy-MM

        //linux下jdk1.8 方法获取时，不会拼接自己写的目录
        String path = request.getSession().getServletContext().getRealPath("/") + "/make/xlsprint/";
        List<OutProductVO> dataList = outProductService.find(inputDate);
        FileInputStream inputStream = new FileInputStream(new File(path + "tOUTPRODUCT.xlsx"));

        Workbook workbook = new XSSFWorkbook(inputStream);//打开一个模板文件
        Sheet sheet = workbook.getSheetAt(0);//获取到第一个工作表
        Row nRow = null;
        Cell nCell = null;

        int rowNo = 0;//行号
        int colNo = 1;//列号


        //获取模板上的单元格格式
        nRow = sheet.getRow(2);

        //客户的样式
        nCell = nRow.getCell(1);
        CellStyle customStyle = nCell.getCellStyle();

        //订单的样式
        nCell = nRow.getCell(2);
        CellStyle contractNoStyle = nCell.getCellStyle();

        //货号的样式
        nCell = nRow.getCell(3);
        CellStyle productNoStyle = nCell.getCellStyle();

        //客户的样式
        nCell = nRow.getCell(4);
        CellStyle numStyle = nCell.getCellStyle();

        //客户的样式
        nCell = nRow.getCell(5);
        CellStyle factoryStyle = nCell.getCellStyle();

        //客户的样式
        nCell = nRow.getCell(6);
        CellStyle dateStyle = nCell.getCellStyle();

        //客户的样式
        nCell = nRow.getCell(8);
        CellStyle tradeStyle = nCell.getCellStyle();

        //大标题
        nRow = sheet.getRow(rowNo++);//获取一个行对象
        nCell = nRow.getCell(colNo);//获取一个单元格对象
        nCell.setCellValue(inputDate.replaceFirst("-0", "-").replaceFirst("-", "年") + "月份出货表");

        rowNo++;

        //处理数据
        for (int i = 0; i < dataList.size(); i++) {
            colNo = 1;//初始化列号
            OutProductVO outProductVO = dataList.get(i);

            nRow = sheet.createRow(rowNo++);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(outProductVO.getCustomName());
            nCell.setCellStyle(customStyle);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(outProductVO.getContractNo());
            nCell.setCellStyle(contractNoStyle);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(outProductVO.getProductNo());
            nCell.setCellStyle(productNoStyle);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(outProductVO.getCnumber());
            nCell.setCellStyle(numStyle);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(outProductVO.getFactoryName());
            nCell.setCellStyle(factoryStyle);

//            nCell = nRow.createCell(colNo++);
//            nCell.setCellValue(outProductVO.getExts());
//            nCell.setCellStyle(ex);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(outProductVO.getDeliverPeriod());
            nCell.setCellStyle(dateStyle);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(outProductVO.getShipTime());
            nCell.setCellStyle(dateStyle);

            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(outProductVO.getTradeTerms());
            nCell.setCellStyle(tradeStyle);
        }


//        OutputStream outputStream = new FileOutputStream(new File("d:\\outproduct\\outproduct.xls"));
//        workbook.write(outputStream);
//        outputStream.flush();
//        outputStream.close();

        DownloadUtil downloadUtil = new DownloadUtil();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        downloadUtil.download(outputStream, response, "出货表.xlsx");
        outputStream.flush();
        outputStream.close();

    }


    //大标题样式
    public CellStyle bigStyle(Font nFont, CellStyle nStyle) {
        //设置字体
        nFont.setFontName("宋体");

        nFont.setFontHeightInPoints((short) 16);
        nFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//字体加粗

        nStyle.setAlignment(CellStyle.ALIGN_CENTER); //横向居中
        nStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //纵向居中

        nStyle.setBorderLeft(CellStyle.BORDER_NONE);

        nStyle.setFont(nFont);

        return nStyle;
    }

    //标题样式
    public CellStyle titleStyle(Font nFont, CellStyle nStyle) {
        //设置字体
        nFont.setFontName("黑体");

        nFont.setFontHeightInPoints((short) 12);
        nFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//字体加粗

        nStyle.setAlignment(CellStyle.ALIGN_CENTER); //横向居中
        nStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //纵向居中

        //表格线
        nStyle.setBorderBottom(CellStyle.BORDER_THIN); //底部细线
        nStyle.setBorderTop(CellStyle.BORDER_THIN);
        nStyle.setBorderRight(CellStyle.BORDER_THIN);
        nStyle.setBorderLeft(CellStyle.BORDER_THIN);

        nStyle.setFont(nFont);

        return nStyle;
    }


    //文字样式
    public CellStyle textStyle(Font nFont, CellStyle nStyle) {
        //设置字体
        nFont.setFontName("Times New Roman");

        nFont.setFontHeightInPoints((short) 10);
        nFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//字体加粗

        nStyle.setAlignment(CellStyle.ALIGN_CENTER); //横向居中
        nStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //纵向居中

        nStyle.setBorderBottom(CellStyle.BORDER_THIN); //底部细线
        nStyle.setBorderTop(CellStyle.BORDER_THIN);
        nStyle.setBorderRight(CellStyle.BORDER_THIN);
        nStyle.setBorderLeft(CellStyle.BORDER_THIN);

        nStyle.setFont(nFont);

        return nStyle;
    }
}
