package com.ggh.service;

import com.ggh.common.json.Body;

/**
 * @Auther: Administrator
 * @Date: 2020/4/26 20:45
 * @Description:
 */
public interface PtOrderService {
    Body addPtOrderInfo(Integer userId,Integer ptGoodsId,String specId);
    Body joinPtOrderInfo(Integer groupUserId,Integer userId,Integer joinUserId,String specId);
}
