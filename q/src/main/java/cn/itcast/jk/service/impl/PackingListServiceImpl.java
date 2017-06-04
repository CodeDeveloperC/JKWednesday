package cn.itcast.jk.service.impl;

import cn.itcast.jk.dao.ExportDao;
import cn.itcast.jk.dao.PackingListDao;
import cn.itcast.jk.domain.Export;
import cn.itcast.jk.domain.PackingList;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.PackingListService;
import cn.itcast.util.UtilFuns;
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
public class PackingListServiceImpl implements PackingListService {
    @Resource
    PackingListDao packingListDao;//和@Autowired都是可以的，只是将packingListDao注入进来
    @Resource
    ExportDao exportDao;

    public List<PackingList> findPage(Page page) {
        return packingListDao.findPage(page);
    }

    public List<PackingList> find(Map paraMap) {
        return packingListDao.find(paraMap);
    }

    public PackingList get(Serializable id) {
        return packingListDao.get(id);
    }


    public void insert(PackingList packingList) {
        this.spellString(packingList);
        packingList.setId(UUID.randomUUID().toString());//设置UUID
        packingList.setState(0);//0草稿 1上报
        packingListDao.insert(packingList);
    }

    public void update(PackingList packingList) {
        this.spellString(packingList);

        packingListDao.update(packingList);
    }

    public void deleteById(Serializable id) {

        Serializable[] ids = {id};

        packingListDao.deleteById(id);
    }

    public void delete(Serializable[] ids) {

        packingListDao.delete(ids);
    }

    @Override
    public void submit(Serializable[] ids) {
        Map map = new HashMap();
        map.put("state", 1);     //1启用
        map.put("ids", ids);

        packingListDao.UpdateState(map);
    }

    @Override
    public void cancel(Serializable[] ids) {
        Map map = new HashMap();
        map.put("state", 0);     //0停用
        map.put("ids", ids);

        packingListDao.UpdateState(map);
    }

    private PackingList spellString(PackingList packingList) {
        String _exportIds = "";
        String _exportNos = "";

        String[] _s = packingList.getExportIds().split(",");
        for (int i = 0; i < _s.length; i++) {
            String[] _tmp = _s[i].split("\\|");//正则表达式，转义
            _exportIds += _tmp[0] + "|";
            _exportNos += _tmp[1] + "|";
        }

        _exportIds = UtilFuns.delLastChar(_exportIds);
        _exportNos = UtilFuns.delLastChar(_exportNos);

        packingList.setExportIds(_exportIds);
        packingList.setExportNos(_exportNos);

        return packingList;
    }

    //拼接一个HTML片段
    public String getDivDataCreate(String[] exportIds) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < exportIds.length; i++) {
            Export export = exportDao.get(exportIds[i]);
            stringBuffer.append("<input type=\"checkbox\" name=\"exportIds\" checked value=\"").append(exportIds[i]).append("|").append(export.getCustomerContract()).append("\" class=\"input\"/>");
            stringBuffer.append(export.getCustomerContract()).append("&nbsp;&nbsp;");
        }

        return stringBuffer.toString();
    }

    //拼接一个HTML片段
    public String getDivDataUpdate(String[] exportIds,String[] exportNos){
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < exportIds.length; i++) {
            stringBuffer.append("<input type=\"checkbox\" name=\"exportIds\" checked value=\"").
                    append(exportIds[i]).append("|").append(exportNos[i]).append("\" class=\"input\"/>");
            stringBuffer.append(exportNos[i]).append("&nbsp;&nbsp");
        }

        return stringBuffer.toString();
    }

    public String getDivDataView(String[] exportNos){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < exportNos.length; i++) {
            stringBuffer.append(exportNos[i]).append("&nbsp;&nbsp");
        }

        return stringBuffer.toString();
    }
}
























