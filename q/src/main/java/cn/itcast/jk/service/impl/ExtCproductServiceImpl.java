package cn.itcast.jk.service.impl;

import cn.itcast.jk.dao.ExtCproductDao;
import cn.itcast.jk.dao.SysCodeDao;
import cn.itcast.jk.domain.ExtCproduct;
import cn.itcast.jk.domain.SysCode;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.ContractProductService;
import cn.itcast.jk.service.ExtCproductService;
import cn.itcast.util.UtilFuns;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by root on 2017/5/22.
 */
@Service
public class ExtCproductServiceImpl implements ExtCproductService {
    @Resource
    ExtCproductDao extCproductDao;//和@Autowired都是可以的，只是将contractDao注入进来
    @Resource
    SysCodeDao sysCodeDao;

    public List<ExtCproduct> findPage(Page page) {
        return extCproductDao.findPage(page);
    }

    public List<ExtCproduct> find(Map paraMap) {
        return extCproductDao.find(paraMap);
    }

    public ExtCproduct get(Serializable id) {
        return extCproductDao.get(id);
    }

    public void insert(ExtCproduct contractProduct) {
        contractProduct.setId(UUID.randomUUID().toString());//设置UUID

        //自动计算总金额，总金额=单价*数量
        if(UtilFuns.isNotEmpty(contractProduct.getCnumber()) && UtilFuns.isNotEmpty(contractProduct.getPrice())){
            contractProduct.setAmount(contractProduct.getCnumber()*contractProduct.getPrice());
        }


        extCproductDao.insert(contractProduct);
    }

    public void update(ExtCproduct contractProduct) {
        extCproductDao.update(contractProduct);
    }

    public void deleteById(Serializable id) {
        extCproductDao.deleteById(id);
    }

    public void delete(Serializable[] ids) {
        extCproductDao.delete(ids);
    }

    @Override
    public List<SysCode> getCtypeList() {
        Map paraMap=new HashMap();
        paraMap.put("parentId","0104"); //sys_code_b 0104 附件分类

        return sysCodeDao.find(paraMap);
    }


}
