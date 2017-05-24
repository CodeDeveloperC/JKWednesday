package cn.itcast.jk.service.impl;

import cn.itcast.jk.dao.FactoryDao;
import cn.itcast.jk.domain.Factory;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.FactoryService;
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
public class FactoryServiceImpl implements FactoryService {
    @Resource
    FactoryDao factoryDao;//和@Autowired都是可以的，只是将factoryDao注入进来

    public List<Factory> findPage(Page page) {
        return factoryDao.findPage(page);
    }

    public List<Factory> find(Map paraMap) {
        return factoryDao.find(paraMap);
    }

    public Factory get(Serializable id) {
        return factoryDao.get(id);
    }

    public void insert(Factory factory) {
        factory.setId(UUID.randomUUID().toString());//设置UUID
        factory.setState("1");//默认启用
        factoryDao.insert(factory);
    }

    public void update(Factory factory) {
        factoryDao.update(factory);
    }

    public void deleteById(Serializable id) {
        factoryDao.deleteById(id);
    }

    public void delete(Serializable[] ids) {
        factoryDao.delete(ids);
    }

    @Override
    public void start(Serializable[] ids) {
        Map map=new HashMap();
        map.put("state",1);     //1启用
        map.put("ids",ids);

        factoryDao.UpdateState(map);
    }

    @Override
    public void stop(Serializable[] ids) {
        Map map=new HashMap();
        map.put("state",0);     //0停用
        map.put("ids",ids);

        factoryDao.UpdateState(map);
    }
}
