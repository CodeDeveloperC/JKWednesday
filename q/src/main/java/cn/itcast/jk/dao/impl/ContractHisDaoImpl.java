package cn.itcast.jk.dao.impl;

import cn.itcast.jk.dao.ContractHisDao;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.vo.ContractVO;
import org.springframework.stereotype.Repository;

/**
 * Created by root on 2017/6/4.
 */
@Repository
public class ContractHisDaoImpl extends BaseDaoImpl<Contract> implements ContractHisDao {

    public ContractHisDaoImpl() {
        super.setNs("cn.itcast.jk.mapper.ContractHisMapper");
    }

    @Override
    public ContractVO view(String contractId) {
        return super.getSqlSession().selectOne(super.getNs() + ".view", contractId);
    }
}
