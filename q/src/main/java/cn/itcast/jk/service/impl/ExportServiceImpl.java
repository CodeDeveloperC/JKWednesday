package cn.itcast.jk.service.impl;

import cn.itcast.jk.dao.ContractDao;
import cn.itcast.jk.dao.ExportDao;
import cn.itcast.jk.dao.ExportProductDao;
import cn.itcast.jk.dao.ExtEproductDao;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.domain.Export;
import cn.itcast.jk.domain.ExportProduct;
import cn.itcast.jk.domain.ExtEproduct;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.ExportService;
import cn.itcast.jk.vo.ContractProductVO;
import cn.itcast.jk.vo.ContractVO;
import cn.itcast.jk.vo.ExtCproductVO;
import cn.itcast.util.UtilFuns;
import org.springframework.beans.BeanUtils;
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
    @Resource
    ExportProductDao exportProductDao;
    @Resource
    ExtEproductDao extEproductDao;

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
            contractNos += contractVO.getContractNo() + " "; //以空格为分隔符
        }
        contractNos = UtilFuns.delLastChar(contractNos);//工具类，删除最后一个字符（此处为空格）

        Export export = new Export();
        export.setId(UUID.randomUUID().toString());

        //x,y
        export.setContractIds(UtilFuns.joinStr(contractIds, ","));//工具类，拼串
        export.setCustomerContract(contractNos);

        export.setState(0);//0，默认为草稿

        exportDao.insert(export);

        //处理货物信息
        for (int i = 0; i < contractIds.length; i++) {
            ContractVO contractVO = contractDao.view(contractIds[i]);

            for (ContractProductVO contractProductVO : contractVO.getContractProducts()
                    ) {
                ExportProduct exportProduct = new ExportProduct();
                exportProduct.setId(UUID.randomUUID().toString());//分次报运，ID不能重复
                exportProduct.setExportId(export.getId()); //绑定外键

                //数据搬家，将合同下的对应的货物信息写入到报运下的货物信息中
                exportProduct.setFactoryId(contractProductVO.getFactory().getId());
                exportProduct.setFactoryName(contractProductVO.getFactory().getFactoryName());
                exportProduct.setProductNo(contractProductVO.getProductNo());
                exportProduct.setPackingUnit(contractProductVO.getPackingUnit());
                exportProduct.setCnumber(contractProductVO.getCnumber());
                exportProduct.setBoxNum(contractProductVO.getBoxNum());
                exportProduct.setPrice(contractProductVO.getPrice());

                exportProductDao.insert(exportProduct);

                //处理附件信息
                for (ExtCproductVO extCproductVO : contractProductVO.getExtCproducts()
                        ) {
                    ExtEproduct extEproduct = new ExtEproduct();

                    //copyProperties spring
                    BeanUtils.copyProperties(extCproductVO, extEproduct);//spring工具类，数据的拷贝

                    extEproduct.setId(UUID.randomUUID().toString());
                    extEproduct.setExportProductId(exportProduct.getId()); //绑定外键

                    extEproduct.setFactoryId(extCproductVO.getFactory().getId());
                    extEproduct.setFactoryName(extCproductVO.getFactory().getFactoryName());

                    extEproductDao.insert(extEproduct);
                }
            }
        }
    }

    @Override
    public void update(Export contract,
                       String[] mr_id,
                       Integer[] mr_orderNo,
                       Integer[] mr_cnumber,
                       Double[] mr_grossWeight,
                       Double[] mr_netWeight,
                       Double[] mr_sizeLength,
                       Double[] mr_sizeHeight,
                       Double[] mr_exPrice,
                       Double[] mr_tax,
                       Integer[] mr_changed) {
        exportDao.update(contract);
        //批量修改货物信息

        for (int i = 0; i < mr_id.length; i++) {
            if (mr_changed[i] != null && mr_changed[i] == 1) {//修改标识，按行计算，只有用户修改的行才进行更新
                ExportProduct exportProduct = exportProductDao.get(mr_id[i]);
                exportProduct.setOrderNo(mr_orderNo[i]);
                exportProduct.setCnumber(mr_cnumber[i]);
                exportProduct.setGrossWeight(mr_grossWeight[i]);
                exportProduct.setNetWeight(mr_netWeight[i]);
                exportProduct.setSizeLength(mr_sizeLength[i]);
                exportProduct.setSizeHeight(mr_sizeHeight[i]);
                exportProduct.setExPrice(mr_exPrice[i]);
                exportProduct.setTax(mr_tax[i]);

                exportProductDao.update(exportProduct);
            }

        }

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

    @Override
    public String getMrecordData(String exportId) {
        Map paraMap = new HashMap();
        paraMap.put("exportId", exportId);

        List<ExportProduct> oList = exportProductDao.find(paraMap);

        StringBuffer stringBuffer = new StringBuffer();
        for (ExportProduct exportProduct :
                oList) {
            stringBuffer.append("addTRRecord(\"mRecordTable\",\"").append(exportProduct.getId()).append("\",\"").
                    append(exportProduct.getProductNo()).append("\", \"").append(exportProduct.getCnumber()).append("\", \"").
                    append(UtilFuns.convertNull(exportProduct.getGrossWeight())).append("\", \"").
                    append(UtilFuns.convertNull(exportProduct.getNetWeight())).append("\", \"").
                    append(UtilFuns.convertNull(exportProduct.getSizeLength())).append("\", \"").
                    append(UtilFuns.convertNull(exportProduct.getSizeWidth())).append("\", \"").
                    append(UtilFuns.convertNull(exportProduct.getSizeHeight())).append("\", \"").
                    append(UtilFuns.convertNull(exportProduct.getExPrice())).append("\", \"").
                    append(UtilFuns.convertNull(exportProduct.getTax())).append("\");");
        }

        return stringBuffer.toString();
    }


}















