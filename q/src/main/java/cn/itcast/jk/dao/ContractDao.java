package cn.itcast.jk.dao;

import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.vo.ContractVO;

import java.util.Map;

/**
 * Created by root on 2017/5/22.
 */
public interface ContractDao extends BaseDao<Contract> {
    void UpdateState(Map map);   //修改状态

    ContractVO view(String contractId);  //查询某个合同下的全部信息
}
