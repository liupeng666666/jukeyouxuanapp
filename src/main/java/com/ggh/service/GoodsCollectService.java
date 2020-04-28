package com.ggh.service;

import com.ggh.common.json.Body;

/**
 * @author chaihu
 * @function
 * @date 2020-04-23 22:33
 */
public interface GoodsCollectService {
    Body queryCollect();

    Body addGoodsCollectInfo(Integer goodsId);

    Body cancelGoodsCollectInfo(Integer goodsId);

    Body checkGoodsCollectInfo(Integer goodsId);
}
