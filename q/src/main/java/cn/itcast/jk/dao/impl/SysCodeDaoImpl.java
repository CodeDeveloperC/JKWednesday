package cn.itcast.jk.dao.impl;

import cn.itcast.jk.dao.ContractDao;
import cn.itcast.jk.dao.SysCodeDao;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.domain.SysCode;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by root on 2017/5/22.
 */
@Repository
public class SysCodeDaoImpl extends BaseDaoImpl<SysCode> implements SysCodeDao{
    public SysCodeDaoImpl() {
        super.setNs("cn.itcast.jk.mapper.SysCodeMapper");
    }

}
