package cn.itcast.jk.controller.cargo.Contract;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.ExtCproduct;
import cn.itcast.jk.domain.Factory;
import cn.itcast.jk.domain.SysCode;
import cn.itcast.jk.service.ExtCproductService;
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
public class ExtCproductController extends BaseController {
    @Resource
    ExtCproductService extCproductService;
    @Resource
    FactoryService factoryService;

    //新增页面
    @RequestMapping("/cargo/extcproduct/tocreate.action")
    public String tocreate(String contractProductId, Model model){

        model.addAttribute("contractProductId",contractProductId);
        //查询某个货物下的附件列表
        Map paraMap=new HashMap();
        paraMap.put("contractProductId",contractProductId);

        List<ExtCproduct> dataList = extCproductService.find(paraMap);
        model.addAttribute("dataList",dataList);


        //准备生产厂家下拉列表
        List<Factory> factoryList=factoryService.getFactoryList();
        model.addAttribute("factoryList",factoryList);

        //准备分类下拉列表
        List<SysCode> ctypeList=extCproductService.getCtypeList();
        model.addAttribute("ctypeList",ctypeList);

        return "/cargo/contract/JExtCproductCreate.jsp";
    }

    //保存数据
    @RequestMapping("/cargo/extcproduct/insert.action")
    public String insert(ExtCproduct extCproduct,Model model){
        extCproductService.insert(extCproduct);

        model.addAttribute("contractProductId",extCproduct.getContractProductId());

        return "redirect:/cargo/extcproduct/tocreate.action";
    }

    //转向修改页面
    @RequestMapping("/cargo/extcproduct/toupdate.action")
    public String toupdate(String id,Model model){
        ExtCproduct obj = extCproductService.get(id);
        model.addAttribute("obj",obj);

        //准备生产厂家下拉列表
        List<Factory> factoryList=factoryService.getFactoryList();
        model.addAttribute("factoryList",factoryList);

        //准备分类下拉列表
        List<SysCode> ctypeList=extCproductService.getCtypeList();
        model.addAttribute("ctypeList",ctypeList);

        return "/cargo/contract/JExtCproductUpdate.jsp";
    }

    //保存
    @RequestMapping("/cargo/extcproduct/update.action")
    public String update(ExtCproduct extCproduct,Model model){
        extCproductService.update(extCproduct);
        model.addAttribute("contractProductId",extCproduct.getContractProductId()); //传递主表ＩＤ

        return "redirect:/cargo/extcproduct/tocreate.action";
    }

    //删除
    @RequestMapping("/cargo/extcproduct/deleteById.action")
    public String deleteById(String id,Model model,String contractProductId){
        extCproductService.deleteById(id);
        model.addAttribute("contractProductId",contractProductId);//为了转回来，知道是哪一个货物下面的

        return "redirect:/cargo/extcproduct/tocreate.action";
    }

}
