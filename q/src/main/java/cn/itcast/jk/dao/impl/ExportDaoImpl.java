package cn.itcast.jk.dao.impl;

import cn.itcast.jk.dao.ExportDao;
import cn.itcast.jk.domain.Export;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by root on 2017/5/22.
 */
@Repository
public class ExportDaoImpl extends BaseDaoImpl<Export> implements ExportDao{
    public ExportDaoImpl() {
        super.setNs("cn.itcast.jk.mapper.ExportMapper");
    }

    @Override
    public void UpdateState(Map map) {
        super.getSqlSession().update(super.getNs()+".updateState",map);
    }

}
