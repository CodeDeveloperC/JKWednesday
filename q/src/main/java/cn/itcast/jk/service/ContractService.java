package cn.itcast.jk.service;

import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.vo.ContractVO;
import com.ctc.wstx.sw.OutputElementBase;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 2017/5/22.
 */
public interface ContractService {
    List<Contract> findPage(Page page);                //分页查询

    List<Contract> find(Map paraMap);                //带条件查询，条件可以为null，既没有条件；返回list对象集合

    Contract get(Serializable id);                    //只查询一个，常用于修改

    ContractVO view(String contractId);              //关联对象查询一个

    void insert(Contract contract);                    //插入，用实体作为参数

    void update(Contract contract);                    //修改，用实体作为参数

    void deleteById(Serializable id);        //按id删除，删除一条；支持整数型和字符串类型ID

    void delete(Serializable[] ids);            //批量删除；支持整数型和字符串类型ID

    void submit(Serializable[] ids);      //上报

    void cancel(Serializable[] ids);       //取消
}
