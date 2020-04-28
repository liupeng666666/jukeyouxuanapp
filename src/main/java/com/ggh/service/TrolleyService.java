package com.ggh.service;

import com.ggh.common.json.Body;

import java.util.List;

/**
 * @author chaihu
 * @function
 * @date 2020-04-22 18:49
 */
public interface TrolleyService {
    Body addGoodsTrolley(Integer goodsId, String specKey, String specKeyName,Integer goodsNum);

    Body queryTrolleyList();

    Body removeTrolley(Integer[] id);
}
