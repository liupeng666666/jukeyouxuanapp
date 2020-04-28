package com.ggh.service.Impl;

import com.ggh.common.json.Body;
import com.ggh.entity.PtGoods;
import com.ggh.mapper.PtGoodsMapper;
import com.ggh.service.PtGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Administrator
 * @Date: 2020/4/26 09:58
 * @Description:
 */
@Service
public class PtGoodsServiceImpl implements PtGoodsService {
    @Autowired
    private PtGoodsMapper ptGoodsMapper;
    @Override
    public Body queryPtGoodsInfo(Integer ptGoodsId) {
        PtGoods ptGoods = ptGoodsMapper.selectById(ptGoodsId);
        return Body.newInstance(ptGoods);
    }
}
