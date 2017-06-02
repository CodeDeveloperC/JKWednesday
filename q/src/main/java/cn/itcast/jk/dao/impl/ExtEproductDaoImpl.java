package cn.itcast.jk.dao.impl;

import cn.itcast.jk.dao.ExtEproductDao;
import cn.itcast.jk.domain.ExtEproduct;
import org.springframework.stereotype.Repository;

/**
 * Created by root on 2017/5/22.
 */
@Repository
public class ExtEproductDaoImpl extends BaseDaoImpl<ExtEproduct> implements ExtEproductDao {
    public ExtEproductDaoImpl() {
        super.setNs("cn.itcast.jk.mapper.ExtEproductMapper");
    }

}
