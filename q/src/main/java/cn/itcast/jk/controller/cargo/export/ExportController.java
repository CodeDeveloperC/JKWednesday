package cn.itcast.jk.controller.cargo.export;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.domain.Export;
import cn.itcast.jk.service.ExportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by root on 2017/6/1.
 */
@Controller
public class ExportController extends BaseController {
    @Resource
    ExportService exportService;

    @RequestMapping("/cargo/export/list.action")
    public String list(Model model){
        List<Export> dataList = exportService.find(null);
        model.addAttribute("dataList",dataList);
        return "/cargo/export/JExportList.jsp";
    }

    //购销合同查询列表
    @RequestMapping("/cargo/export/contractList.action")
    public String contractList(Model model){
        List<Contract> dataList = exportService.getContractList();
        model.addAttribute("dataList",dataList);
        return "/cargo/export/JContractList.jsp"; //报运目录下调用购销合同列表
    }
}
