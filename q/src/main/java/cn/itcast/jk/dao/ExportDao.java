package cn.itcast.jk.dao;

import cn.itcast.jk.domain.Export;

import java.util.Map;

/**
 * Created by root on 2017/5/22.
 */
public interface ExportDao extends BaseDao<Export> {
    void UpdateState(Map map);   //修改状态

}
