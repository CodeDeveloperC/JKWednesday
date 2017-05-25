package cn.itcast.jk.service.impl;

import cn.itcast.jk.dao.SysCodeDao;
import cn.itcast.jk.domain.SysCode;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.SysCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by root on 2017/5/25.
 */
@Service
public class SysCodeServiceImpl implements SysCodeService {
    @Resource
    SysCodeDao sysCodeDao;

    @Override
    public List<SysCode> findPage(Page page) {
        return sysCodeDao.findPage(page);
    }
}
