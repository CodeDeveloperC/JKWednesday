package cn.itcast.jk.service;

import cn.itcast.jk.domain.Factory;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.vo.OutProductVO;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 2017/5/22.
 */
public interface OutProductService {
    List<OutProductVO> find(String inputDate);

}
