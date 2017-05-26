package cn.itcast.jk.service.impl;

import cn.itcast.jk.dao.ContractProductDao;
import cn.itcast.jk.dao.ExtCproductDao;
import cn.itcast.jk.domain.ContractProduct;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.ContractProductService;
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
public class ContractProductServiceImpl implements ContractProductService {
    @Resource
    ContractProductDao contractProductDao;//和@Autowired都是可以的，只是将contractDao注入进来
    @Resource
    ExtCproductDao extCproductDao;

    public List<ContractProduct> findPage(Page page) {
        return contractProductDao.findPage(page);
    }

    public List<ContractProduct> find(Map paraMap) {
        return contractProductDao.find(paraMap);
    }

    public ContractProduct get(Serializable id) {
        return contractProductDao.get(id);
    }

    public void insert(ContractProduct contractProduct) {
        contractProduct.setId(UUID.randomUUID().toString());//设置UUID

        //自动计算总金额，总金额=单价*数量
        if(UtilFuns.isNotEmpty(contractProduct.getCnumber()) && UtilFuns.isNotEmpty(contractProduct.getPrice())){
            contractProduct.setAmount(contractProduct.getCnumber()*contractProduct.getPrice());
        }


        contractProductDao.insert(contractProduct);
    }

    public void update(ContractProduct contractProduct) {
        contractProductDao.update(contractProduct);
    }

    public void deleteById(Serializable id) {
        Serializable ids[] = {id};  //附件通过外键删除，所以删除的ID一样
        extCproductDao.deleteByContractProductById(ids);    //删除这些货物下的所有附件
        contractProductDao.deleteById(id);
    }

    public void delete(Serializable[] ids) {
        extCproductDao.deleteByContractProductById(ids);    //删除这些货物下的所有附件
        contractProductDao.delete(ids);
    }


}
