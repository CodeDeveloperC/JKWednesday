package cn.itcast.jk.service.impl;

import cn.itcast.jk.dao.ContractDao;
import cn.itcast.jk.dao.ContractProductDao;
import cn.itcast.jk.dao.ExtCproductDao;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.ContractService;
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
public class ContractServiceImpl implements ContractService {
    @Resource
    ContractDao contractDao;//和@Autowired都是可以的，只是将contractDao注入进来
    @Resource
    ContractProductDao contractProductDao;
    @Resource
    ExtCproductDao extCproductDao;

    public List<Contract> findPage(Page page) {
        return contractDao.findPage(page);
    }

    public List<Contract> find(Map paraMap) {
        return contractDao.find(paraMap);
    }

    public Contract get(Serializable id) {
        return contractDao.get(id);
    }

    public void insert(Contract contract) {
        contract.setId(UUID.randomUUID().toString());//设置UUID
        contract.setState(0);//0草稿 1上报
        contractDao.insert(contract);
    }

    public void update(Contract contract) {
        contractDao.update(contract);
    }

    public void deleteById(Serializable id) {

        Serializable[] ids = {id};

        extCproductDao.deleteByContractId(ids); //删除货物下的附件信息

        contractProductDao.deleteByContractId(ids); //删除这些合同下的货物信息

        contractDao.deleteById(id);
    }

    public void delete(Serializable[] ids) {

        extCproductDao.deleteByContractId(ids); //删除货物下的附件信息

        contractProductDao.deleteByContractId(ids); //删除这些合同下的货物信息

        contractDao.delete(ids);
    }

    @Override
    public void submit(Serializable[] ids) {
        Map map=new HashMap();
        map.put("state",1);     //1启用
        map.put("ids",ids);

        contractDao.UpdateState(map);
    }

    @Override
    public void cancel(Serializable[] ids) {
        Map map=new HashMap();
        map.put("state",0);     //0停用
        map.put("ids",ids);

        contractDao.UpdateState(map);
    }
}
