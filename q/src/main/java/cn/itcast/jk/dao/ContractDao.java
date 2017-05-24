package cn.itcast.jk.dao;

import cn.itcast.jk.domain.Contract;

import java.util.Map;

/**
 * Created by root on 2017/5/22.
 */
public interface ContractDao extends BaseDao<Contract> {
    public void UpdateState(Map map);   //修改状态
}
