package cn.itcast.jk.dao.impl;

import cn.itcast.jk.dao.ExportProductDao;
import cn.itcast.jk.domain.ExportProduct;
import org.springframework.stereotype.Repository;

/**
 * Created by root on 2017/5/22.
 */
@Repository
public class ExportProductDaoImpl extends BaseDaoImpl<ExportProduct> implements ExportProductDao {
    public ExportProductDaoImpl() {
        super.setNs("cn.itcast.jk.mapper.ExportProductMapper");
    }


}
