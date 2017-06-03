package cn.itcast.jk.dao;

import cn.itcast.jk.domain.PackingList;

import java.util.Map;

/**
 * Created by root on 2017/5/22.
 */
public interface PackingListDao extends BaseDao<PackingList> {
    void UpdateState(Map map);   //修改状态

}
