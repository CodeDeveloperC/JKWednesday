package cn.itcast.jk.dao;

import cn.itcast.jk.domain.ExtCproduct;

import java.io.Serializable;

/**
 * Created by root on 2017/5/22.
 */
public interface ExtCproductDao extends BaseDao<ExtCproduct> {
    void deleteByContractProductById(Serializable[] ids);
    void deleteByContractId(Serializable[] ids);
}
