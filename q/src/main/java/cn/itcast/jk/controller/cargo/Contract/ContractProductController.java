package cn.itcast.jk.controller.cargo.Contract;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.ContractProduct;
import cn.itcast.jk.domain.Factory;
import cn.itcast.jk.service.ContractProductService;
import cn.itcast.jk.service.FactoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 2017/5/25.
 */
@Controller
public class ContractProductController extends BaseController{
    @Resource
    ContractProductService contractProductService;
    @Resource
    FactoryService factoryService;

    //转向新增页面
    @RequestMapping("/cargo/contractProduct/tocreate.action")
    public String tocreate(String contractId, Model model){//传递主表ID
        model.addAttribute("contractId",contractId);

        //准备生产厂家下拉列表
        List<Factory> factoryList=factoryService.getFactoryList();
        model.addAttribute("factoryList",factoryList);

        //某个合同下的货物类列表
        Map paraMap = new HashMap();
        paraMap.put("contractId",contractId);

        List<ContractProduct> dataList=contractProductService.find(paraMap);
        model.addAttribute("dataList",dataList);

        return "/cargo/contract/JContractProductCreate.jsp"; //货物的新增页面
    }

    //新增
    @RequestMapping("cargo/contractProduct/insert.action")
    public String insert(ContractProduct contractProduct,Model model){
        contractProductService.insert(contractProduct);

        //传递主表ID
        model.addAttribute("contractId",contractProduct.getContractId());

        return "redirect:/cargo/contractProduct/tocreate.action"; //转向新增页面，便于批量增加
    }
}
