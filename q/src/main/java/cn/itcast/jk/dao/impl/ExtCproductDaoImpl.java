package cn.itcast.jk.dao.impl;

import cn.itcast.jk.dao.ExtCproductDao;
import cn.itcast.jk.domain.ExtCproduct;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by root on 2017/5/22.
 */
@Repository
public class ExtCproductDaoImpl extends BaseDaoImpl<ExtCproduct> implements ExtCproductDao {
    public ExtCproductDaoImpl() {
        super.setNs("cn.itcast.jk.mapper.ExtCproductMapper");
    }

    @Override
    public void deleteByContractProductById(Serializable[] ids) {
        super.getSqlSession().delete(super.getNs()+".deleteByContractProductById",ids);
    }

    @Override
    public void deleteByContractId(Serializable[] contractIds) {
        super.getSqlSession().delete(super.getNs()+".deleteByContractId",contractIds);
    }


}
