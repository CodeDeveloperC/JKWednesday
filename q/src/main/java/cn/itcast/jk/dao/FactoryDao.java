package cn.itcast.jk.dao;

import cn.itcast.jk.domain.Factory;

import java.util.Map;

/**
 * Created by root on 2017/5/22.
 */
public interface FactoryDao extends BaseDao<Factory> {
    public void UpdateState(Map map);   //修改状态
}
