package cn.itcast.jk.service;

import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.vo.ContractVO;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 2017/6/4.
 */
public interface ContractHisService {
    List<Contract> findPage(Page page);  //分页查询
    List<Contract> find(Map paraMap);    //带条件查询，条件可以为null，即没有条件；返回list对象集合

    ContractVO view(String contractId);//关联对象的查询一个


    void pigeinhole(String[] contractIds);//归档

    void pigeouthole(String[] contractIds);//取消归档


}
