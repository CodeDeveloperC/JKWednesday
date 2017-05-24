package cn.itcast.jk.dao.impl;

import cn.itcast.jk.dao.FactoryDao;
import cn.itcast.jk.domain.Factory;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by root on 2017/5/22.
 */
@Repository
public class FactoryDaoImpl extends BaseDaoImpl<Factory> implements FactoryDao{
    public FactoryDaoImpl() {
        super.setNs("cn.itcast.jk.mapper.FactoryMapper");
    }

    @Override
    public void UpdateState(Map map) {
        super.getSqlSession().update(super.getNs()+".updateState",map);
    }
}
