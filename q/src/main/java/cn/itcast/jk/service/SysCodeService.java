package cn.itcast.jk.service;

import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.domain.SysCode;
import cn.itcast.jk.pagination.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 2017/5/22.
 */
public interface SysCodeService {
    List<SysCode> findPage(Page page);                //分页查询
}
