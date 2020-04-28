package com.ggh.service;


import com.ggh.common.json.Body;

/**
 * @author chaihu
 * @function
 * @date 2020-04-20 12:06
 */
public interface GoodsService {
    Body queryGoodsInfo(Integer goodsId);

    Body queryGoodsSpec(Integer goodsId);
}
