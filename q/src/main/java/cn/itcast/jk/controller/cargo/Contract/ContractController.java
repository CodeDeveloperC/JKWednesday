package cn.itcast.jk.controller.cargo.Contract;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.service.ContractService;
import cn.itcast.jk.vo.ContractVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by root on 2017/5/24.
 */
@Controller
public class ContractController extends BaseController {
    @Resource
    ContractService contractService;

    @RequestMapping("/cargo/contract/list.action")
    public String list(Model model) {
        List<Contract> dataList = contractService.find(null);
        model.addAttribute("dataList", dataList);//将数据传递到页面

        return "/cargo/contract/JContractList.jsp";//转向页面
    }

    //转向新增页面
    @RequestMapping("/cargo/contract/tocreate.action")
    public String tocreate() {
        return "/cargo/contract/JContractCreate.jsp";
    }

    //新增保存
    @RequestMapping("/cargo/contract/insert.action")
    public String insert(Contract contract) {
        contractService.insert(contract);
        return "redirect:/cargo/contract/list.action";//转向列表的action
    }

    @RequestMapping("/cargo/contract/toupdate.action")
    public String toupdate(String id, Model model) {
        Contract obj = contractService.get(id);
        model.addAttribute("obj", obj);
        return "/cargo/contract/JContractUpdate.jsp";
    }

    //新增保存
    @RequestMapping("/cargo/contract/update.action")
    public String update(Contract contract) {
        contractService.update(contract);

        return "redirect:/cargo/contract/list.action";//转向列表的action
    }

    //批量删除
    @RequestMapping("/cargo/contract/delete.action")
    public String delete(String[] id){
        contractService.delete(id);

        return "redirect:/cargo/contract/list.action";//转向列表的action
    }

    //查看
    @RequestMapping("/cargo/contract/toview.action")
    public String toview(String id, Model model) {
        ContractVO obj = contractService.view(id);
        model.addAttribute("obj", obj);

        return "/cargo/contract/JContractView.jsp";
    }

    //上报
    @RequestMapping("/cargo/contract/submit.action")
    public String submit(String[] id){
        contractService.submit(id);

        return "redirect:/cargo/contract/list.action";//转向列表的action
    }

    //取消
    @RequestMapping("/cargo/contract/cancel.action")
    public String cancel(String[] id){
        contractService.cancel(id);

        return "redirect:/cargo/contract/list.action";//转向列表的action
    }
}
