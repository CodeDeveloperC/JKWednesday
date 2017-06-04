package cn.itcast.jk.controller.stat.chart;

import cn.itcast.common.springdao.SqlDao;
import cn.itcast.util.file.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by root on 2017/6/4.
 */
@Controller
public class ChartController {
    @Resource
    SqlDao sqlDao;
    //生产厂家销售饼形图

    /**
     * 开发步骤
     * 1、组织数据源
     * 2、拼接成xml
     * 3、创建一个文件TXT格式，xml工具类
     * 4、转向对应目录下的index.html
     */

    @RequestMapping("/stat/chart/factorySale.action")
    public String factorySale(HttpServletRequest request) throws FileNotFoundException {
        String path = request.getSession().getServletContext().getRealPath("/");//真实路径
        String dir = "factorysale";

        String sql = "SELECT f.factory_name,cp.countnum FROM (SELECT factory_id,factory_name FROM factory_c) f RIGHT JOIN (SELECT factory_id,COUNT(*) AS countnum FROM contract_product_c GROUP BY factory_id ) cp ON f.factory_id=cp.factory_id";

        this.writeXML(path, dir, this.getPieXml(this.getData(sql)));

        return "/stat/chart/jStat.jsp?forward=" + dir;
    }

    @RequestMapping("/stat/chart/productSale.action")
    public  String productSale(HttpServletRequest request) throws FileNotFoundException {
        String path = request.getSession().getServletContext().getRealPath("/");//真实路径
        String dir = "productsale";

        String sql = "SELECT product_no,SUM(cnumber) AS sumnum FROM contract_product_c GROUP BY product_no ORDER BY SUM(cnumber)  DESC LIMIT 15";
        this.writeXML(path, dir, this.getColumnAndLineXml(this.getData(sql)));

        return "/stat/chart/jStat.jsp?forward=" + dir;
    }

    //系统访问压力的曲线图
    @RequestMapping("/stat/chart/onlineInfo.action")
    public String onlineInfo(HttpServletRequest request) throws FileNotFoundException{
        String path = request.getSession().getServletContext().getRealPath("/");	//真实路径
        String dir = "onlineinfo";

        String sql = "SELECT t.a1,p.countnum FROM (SELECT a1 FROM online_t) t LEFT JOIN (SELECT SUBSTRING(login_time,12,2) AS a1,COUNT(*) AS countnum FROM login_log_p GROUP BY SUBSTRING(login_time,12,2)) p ON t.a1=p.a1";
        this.writeXML(path, dir, this.getColumnAndLineXml(this.getData(sql)));

        return "/stat/chart/jStat.jsp?forward=" + dir;
    }

    //获取柱状图xml
    private String getColumnAndLineXml(List<String> dataList) {
        StringBuffer sBuf = new StringBuffer();
        sBuf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sBuf.append("<chart>");
        sBuf.append("<series>");

        int xid = 0;			//对应标识
        for(int i=0;i<dataList.size();){
            sBuf.append("<value xid=\"").append(xid++).append("\">").append(dataList.get(i++)).append("</value>");
            i++;		//skip
        }
        sBuf.append("</series>");
        sBuf.append("<graphs>");
        sBuf.append("<graph gid=\"30\" color=\"#FFCC00\" gradient_fill_colors=\"#111111, #1A897C\">");

        xid = 0;
        for(int i=0;i<dataList.size();){
            i++;		//skip
            sBuf.append("<value xid=\"").append(xid++).append("\">").append(dataList.get(i++)).append("</value>");
        }

        sBuf.append("</graph>");
        sBuf.append("</graphs>");
        sBuf.append("</chart>");

        return sBuf.toString();
    }

    //获取数据
    public List<String> getData(String sql) {
        return sqlDao.executeSQL(sql);
    }

    //拼接饼形xml
    public String getPieXml(List<String> dataList) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        stringBuffer.append("<pie>");
        for (int i = 0; i < dataList.size(); ) {
            stringBuffer.append("<slice title=\"").append(dataList.get(i++)).append("\">").append(dataList.get(i++)).append("</slice>");
        }

        stringBuffer.append("</pie>");

        return stringBuffer.toString();
    }

    public void writeXML(String path, String dir, String content) throws FileNotFoundException {
        FileUtil fileUtil = new FileUtil();
        fileUtil.createTxt(path + "/stat/chart/" + dir, "data.xml", content, "utf-8");
    }
}


















