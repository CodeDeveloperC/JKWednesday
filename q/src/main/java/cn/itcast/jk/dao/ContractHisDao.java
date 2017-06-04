package cn.itcast.jk.dao;

import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.vo.ContractVO;

/**
 * Created by root on 2017/6/4.
 */
public interface ContractHisDao extends BaseDao<Contract> {
    ContractVO view(String contractId);//查询某个合同号
}
