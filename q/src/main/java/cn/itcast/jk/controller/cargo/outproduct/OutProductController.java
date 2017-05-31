package cn.itcast.jk.controller.cargo.outproduct;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.service.OutProductService;
import cn.itcast.jk.vo.OutProductVO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.*;
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

    @RequestMapping("/cargo/outproduct/print.action")
    public void print(String inputDate) throws IOException { //inputDate格式：yyyy-MM

        List<OutProductVO> dataList = outProductService.find(inputDate);

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row nRow = null;
        Cell nCell = null;

        int rowNo = 0;//行号
        rowNo++;


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

        //标题
        for (int i = 0; i < titles.length; i++) {
            nCell = nRow.createCell(i + 1);
            nCell.setCellValue(titles[i]);
        }
        //数据
        nRow = sheet.createRow(rowNo++);

        OutputStream outputStream = new FileOutputStream(new File("d:\\outproduct\\outproduct.xls"));
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();

        System.out.println(inputDate);
    }
}
