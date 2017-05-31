package cn.itcast.jk.dao.impl;

import cn.itcast.jk.dao.FactoryDao;
import cn.itcast.jk.dao.OutProductDao;
import cn.itcast.jk.domain.Factory;
import cn.itcast.jk.vo.OutProductVO;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by root on 2017/5/22.
 */
@Repository
public class OutProductDaoImpl extends BaseDaoImpl<OutProductVO> implements OutProductDao{
    public OutProductDaoImpl() {
        super.setNs("cn.itcast.jk.mapper.OutProductMapper");
    }

}
