package cn.itcast.jk.service;

import cn.itcast.jk.domain.PackingList;
import cn.itcast.jk.pagination.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 2017/5/22.
 */
public interface PackingListService {
    List<PackingList> findPage(Page page);                //分页查询

    List<PackingList> find(Map paraMap);                //带条件查询，条件可以为null，既没有条件；返回list对象集合

    PackingList get(Serializable id);                    //只查询一个，常用于修改

    void insert(PackingList packingList);                    //插入，用实体作为参数

    void update(PackingList packingList);                    //修改，用实体作为参数

    void deleteById(Serializable id);        //按id删除，删除一条；支持整数型和字符串类型ID

    void delete(Serializable[] ids);            //批量删除；支持整数型和字符串类型ID

    void submit(Serializable[] ids);      //上报

    void cancel(Serializable[] ids);       //取消

    String getDivDataCreate(String[] exportIds);//获取div，展示数据

    String getDivDataUpdate(String[] exportIds, String[] exportNos);

    String getDivDataView(String[] exportNos);
}
