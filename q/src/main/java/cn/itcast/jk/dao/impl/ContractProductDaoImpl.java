package cn.itcast.jk.dao.impl;

import cn.itcast.jk.dao.ContractProductDao;
import cn.itcast.jk.domain.ContractProduct;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by root on 2017/5/22.
 */
@Repository
public class ContractProductDaoImpl extends BaseDaoImpl<ContractProduct> implements ContractProductDao {
    public ContractProductDaoImpl() {
        super.setNs("cn.itcast.jk.mapper.ContractProductMapper");
    }

    @Override
    public void deleteByContractProductById(Serializable[] ids) {
        super.getSqlSession().delete(super.getNs()+".deleteByContractProductById",ids);
    }

    @Override
    public void deleteByContractId(Serializable[] ids) {
        super.getSqlSession().delete(super.getNs()+".deleteByContractId",ids);
    }


}
