package com.ggh.service;

import com.ggh.common.json.Body;

/**
 * @author chaihu
 * @function
 * @date 2020-04-23 16:20
 */
public interface OrderService {
    Body addSingleOrderInfo(Integer goodsId, String specKey, String specKeyName, Integer goodsNum,Integer addressId,String userNote);

    Body addTrolleyOrderInfo(Integer[] trolleyId,Integer addressId, String userNote);

    Body cancelOrderInfo(Integer orderId,String cancelDesc);

    Body confirmOrderInfo(Integer orderId);

    Body commentOrderInfo(Integer orderGoodsId, Integer globalComment, String content, String images, Integer logisticComment, Integer accordComment, Integer serviceComment, Integer isAnonym);

    Body queryOrderList(String orderStatus);
}
