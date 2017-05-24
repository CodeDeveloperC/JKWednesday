package cn.itcast.jk.dao.impl;

import cn.itcast.jk.dao.ContractDao;
import cn.itcast.jk.domain.Contract;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by root on 2017/5/22.
 */
@Repository
public class ContractDaoImpl extends BaseDaoImpl<Contract> implements ContractDao{
    public ContractDaoImpl() {
        super.setNs("cn.itcast.jk.mapper.ContractMapper");
    }

    @Override
    public void UpdateState(Map map) {
        super.getSqlSession().update(super.getNs()+".updateState",map);
    }
}
