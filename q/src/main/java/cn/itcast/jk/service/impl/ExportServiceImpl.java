package cn.itcast.jk.service.impl;

import cn.itcast.jk.dao.ContractDao;
import cn.itcast.jk.dao.ExportDao;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.domain.Export;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.ExportService;
import cn.itcast.jk.vo.ContractVO;
import cn.itcast.util.UtilFuns;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by root on 2017/6/1.
 */
@Service
public class ExportServiceImpl implements ExportService {
    @Resource
    ExportDao exportDao;
    @Resource
    ContractDao contractDao;

    @Override
    public List<Export> findPage(Page page) {
        return exportDao.findPage(page);
    }

    @Override
    public List<Export> find(Map paraMap) {
        return exportDao.find(paraMap);
    }

    @Override
    public Export get(Serializable id) {
        return exportDao.get(id);
    }

    @Override
    public void insert(String[] contractIds) {
        /**
         * 步骤：
         * 1.根据合同ID获取合同对象，获取合同号
         * 2.合同下的货物信息搬家到报运下的货物表中
         * 3.合同下的附件信息搬家到报运下的附件表中
         */

        //拼接合同号，报运号
        String contractNos = "";
        for (int i = 0; i < contractIds.length; i++) {
            ContractVO contractVO = contractDao.view(contractIds[i]);
            contractNos += contractVO.getContractNo()+" "; //以空格为分隔符
        }
        contractNos = UtilFuns.delLastChar(contractNos);//工具类，删除最后一个字符（此处为空格）

        Export export = new Export();
        export.setId(UUID.randomUUID().toString());

        //x,y
        export.setContractIds(UtilFuns.joinStr(contractIds, ","));//工具类，拼串
        export.setCustomerContract(contractNos);

        export.setState(0);//0，草稿

        exportDao.insert(export);

        //处理货物信息
//        for (int i = 0; i < contractIds.length; i++) {
//            ContractVO contractVO = contractDao.view(contractIds[i]);
//
//            for (ContractProductVO cp:contractVO.getContractProducts()
//                 ) {
//                Exportp
//            }
//        }
    }

    @Override
    public void update(Export contract) {
        exportDao.update(contract);
    }

    @Override
    public void deleteById(Serializable id) {
        exportDao.deleteById(id);
    }

    @Override
    public void delete(Serializable[] ids) {
        exportDao.delete(ids);
    }

    @Override
    public void submit(Serializable[] ids) {
        Map map = new HashMap();
        map.put("state", 1);     //1启用
        map.put("ids", ids);

        exportDao.UpdateState(map);
    }

    @Override
    public void cancel(Serializable[] ids) {
        Map map = new HashMap();
        map.put("state", 0);     //0停用
        map.put("ids", ids);

        exportDao.UpdateState(map);
    }

    @Override
    public List<Contract> getContractList() {
        Map paraMap = new HashMap();
        paraMap.put("state", 1);//1表示查询已上报的
        return contractDao.find(paraMap);
    }
}
