package cn.itcast.jk.dao.impl;

import cn.itcast.jk.dao.PackingListDao;
import cn.itcast.jk.domain.PackingList;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by root on 2017/5/22.
 */
@Repository
public class PackingListDaoImpl extends BaseDaoImpl<PackingList> implements PackingListDao{
    public PackingListDaoImpl() {
        super.setNs("cn.itcast.jk.mapper.PackingListMapper");
    }

    @Override
    public void UpdateState(Map map) {
        super.getSqlSession().update(super.getNs()+".updateState",map);
    }

}
