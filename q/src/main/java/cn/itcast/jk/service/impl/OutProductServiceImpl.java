package cn.itcast.jk.service.impl;

import cn.itcast.jk.dao.FactoryDao;
import cn.itcast.jk.dao.OutProductDao;
import cn.itcast.jk.domain.Factory;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.FactoryService;
import cn.itcast.jk.service.OutProductService;
import cn.itcast.jk.vo.OutProductVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by root on 2017/5/22.
 */
@Service
public class OutProductServiceImpl implements OutProductService {
    @Resource
    OutProductDao outProductDao;


    @Override
    public List<OutProductVO> find(String inputDate) {
        Map paraMap = new HashMap();
        paraMap.put("inputDate",inputDate);

        return outProductDao.find(paraMap);
    }
}
