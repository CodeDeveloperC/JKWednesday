package cn.itcast.jk.controller.basicinfo.factory;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Factory;
import cn.itcast.jk.service.FactoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by root on 2017/5/22.
 */
@Controller
public class FactoryController extends BaseController {
    @Resource
    FactoryService factoryService;

    //列表
    @RequestMapping("/basicinfo/factory/list.action")
    public String list(Model model){
        List<Factory> dataList = factoryService.find(null);
        model.addAttribute("dataList",dataList);//将数据传递到页面

        //return "1.jsp";
        return "/basicinfo/factory/JFactoryList.jsp";//转向页面
    }

    //转向新增页面
    @RequestMapping("/basicinfo/factory/tocreate.action")
    public String tocreate(){
        return "/basicinfo/factory/JFactoryCreate.jsp";
    }

    //新增保存
    @RequestMapping("/basicinfo/factory/insert.action")
    public String insert(Factory factory){
        factoryService.insert(factory);
        return "redirect:/basicinfo/factory/list.action";//转向列表的action
    }

    //转向修改页面
    @RequestMapping("/basicinfo/factory/toupdate.action")
    public String toupdate(String id,Model model){
        Factory obj = factoryService.get(id);
        model.addAttribute("obj",obj);
        return "/basicinfo/factory/JFactoryUpdate.jsp";
    }
    //保存页面
    @RequestMapping("/basicinfo/factory/update.action")
    public String update(Factory factory){
        factoryService.update(factory);
        return "redirect:/basicinfo/factory/list.action";//转向列表的action
    }

    //删除一条
    @RequestMapping("/basicinfo/factory/deleteById.action")
    public String deleteById(String id){
        factoryService.deleteById(id);
        return "redirect:/basicinfo/factory/list.action";//转向列表的action
    }

    //删除多条
    @RequestMapping("/basicinfo/factory/delete.action")
    public String delete(@RequestParam("id")String[] ids){
        factoryService.delete(ids);
        return "redirect:/basicinfo/factory/list.action";//转向列表的action
    }

    //查看页面
    @RequestMapping("/basicinfo/factory/toview.action")
    public String toview(String id,Model model){
        Factory obj = factoryService.get(id);
        model.addAttribute("obj",obj);
        return "/basicinfo/factory/JFactoryView.jsp";
    }

    //批量启用
    @RequestMapping("/basicinfo/factory/start.action")
    public String start(@RequestParam("id")String[] ids){
        factoryService.start(ids);
        return "redirect:/basicinfo/factory/list.action";//转向列表的action
    }

    //批量停用
    @RequestMapping("/basicinfo/factory/stop.action")
    public String stop(@RequestParam("id")String[] ids){
        factoryService.stop(ids);
        return "redirect:/basicinfo/factory/list.action";//转向列表的action
    }

}
