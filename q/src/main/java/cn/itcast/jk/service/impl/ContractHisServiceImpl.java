package cn.itcast.jk.service.impl;

import cn.itcast.common.springdao.SqlDao;
import cn.itcast.jk.dao.ContractHisDao;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.ContractHisService;
import cn.itcast.jk.vo.ContractVO;
import cn.itcast.util.UtilFuns;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 2017/6/4.
 */
@Service
public class ContractHisServiceImpl implements ContractHisService {
    @Resource
    SqlDao sqlDao;
    @Resource
    ContractHisDao contractHisDao;

    @Override
    public List<Contract> findPage(Page page) {
        return contractHisDao.findPage(page);
    }

    @Override
    public List<Contract> find(Map paraMap) {
        return contractHisDao.find(paraMap);
    }

    @Override
    public ContractVO view(String contractId) {
        return contractHisDao.view(contractId);
    }

    @Override
    public void pigeinhole(String[] contractIds) {
        sqlDao.batchSQL(this.doData(contractIds, "", "_his"));//批量执行
    }

    @Override
    public void pigeouthole(String[] contractIds) {
        sqlDao.batchSQL(this.doData(contractIds, "_his", ""));//批量执行
    }

    //处理数据：有源表向目标表复制数据，将源表数据删除
    public String[] doData(String[] contractIds, String source, String target) {
        StringBuffer stringBuffer = new StringBuffer();

        String inStr = UtilFuns.joinStr(contractIds, "'", "'", ",");//合同的id串x,y ，构造成in子查询串'x','y'

        stringBuffer.append("insert into contract").append(target).append("_c (select * from contract").
                append(source).append("_c where contract_id in (").append(inStr).append("));");

        stringBuffer.append("insert into contract_product").append(target).append("_c (select * from contract_product").
                append(source).append("_c where contract_id in (").append(inStr).append("));");

        stringBuffer.append("insert into ext_cproduct").append(target).append("_c (select * from ext_cproduct").
                append(source).append("_c where contract_product_id in (select contract_product_id from contract_product")
                .append(source).append("_c where contract_id in (")
                .append(inStr).append(")));");

        stringBuffer.append("delete from ext_cproduct").append(source).
                append("_c where contract_product_id in (select contract_product_id from contract_product").append(source)
                .append("_c where contract_id in (")
                .append(inStr).append("));");

        stringBuffer.append("delete from contract_product").append(source).append("_c where contract_id in (").append(inStr).append(");");

        stringBuffer.append("delete from contract").append(source).append("_c where contract_id in (").append(inStr).append(");");


        return stringBuffer.toString().split(";");
    }

//    public String[] doData(String[] contractIds, String source, String target){
//        StringBuffer sBuf = new StringBuffer();
//        String inStr = UtilFuns.joinStr(contractIds, "'", "'", ",");			//合同的id串 x,y ，构造成in子查询串 'x','y'
//
//        sBuf.append("insert into contract").append(target).append("_c (select * from contract").append(source).
//                append("_c where contract_id in (").append(inStr).append("));");
//        sBuf.append("insert into contract_product").append(target).
//                append("_c (select * from contract_product").append(source).
//                append("_c where contract_id in (").append(inStr).append("));");
//        sBuf.append("insert into ext_cproduct").append(target).
//                append("_c (select * from ext_cproduct").append(source)
//                .append("_c where contract_product_id in (select contract_product_id from contract_product")
//                .append(source).append("_c where contract_id in (").append(inStr).append(")));");
//
//        sBuf.append("delete from ext_cproduct").append(source).
//                append("_c where contract_product_id in (select contract_product_id from contract_product").
//                append(source).append("_c where contract_id in (").append(inStr).append("));");
//        sBuf.append("delete from contract_product").append(source).
//                append("_c where contract_id in (").append(inStr).append(");");
//        sBuf.append("delete from contract").append(source).
//                append("_c where contract_id in (").append(inStr).append(");");
//
//        return sBuf.toString().split(";");
//    }

}























