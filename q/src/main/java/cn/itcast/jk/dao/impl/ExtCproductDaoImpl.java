package cn.itcast.jk.dao.impl;

import cn.itcast.jk.dao.ExtCproductDao;
import cn.itcast.jk.domain.ExtCproduct;
import org.springframework.stereotype.Repository;

/**
 * Created by root on 2017/5/22.
 */
@Repository
public class ExtCproductDaoImpl extends BaseDaoImpl<ExtCproduct> implements ExtCproductDao {
    public ExtCproductDaoImpl() {
        super.setNs("cn.itcast.jk.mapper.ExtCproductMapper");
    }

}
