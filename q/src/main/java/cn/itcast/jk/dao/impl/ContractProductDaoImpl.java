package cn.itcast.jk.dao.impl;

import cn.itcast.jk.dao.ContractProductDao;
import cn.itcast.jk.domain.ContractProduct;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by root on 2017/5/22.
 */
@Repository
public class ContractProductDaoImpl extends BaseDaoImpl<ContractProduct> implements ContractProductDao {
    public ContractProductDaoImpl() {
        super.setNs("cn.itcast.jk.mapper.ContractProductMapper");
    }

}
