package com.ggh.controller;

import com.ggh.common.json.Body;
import com.ggh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chaihu
 * @function 创建订单的controller
 * @date 2020-04-23 16:12
 */
@CrossOrigin
@Controller
@ResponseBody
@RequestMapping("create")
public class CreateOrderController {

    @Autowired
    private OrderService orderService;


    /**
     * 创建单独购买订单
     * @param goodsId 商品id
     * @param specKey 商品规格值
     * @param specKeyName 商品规格值对应中文名称
     * @param goodsNum 商品数量
     * @return
     */
    @RequestMapping("singleBuy")
    public Body singleBuy(
            Integer goodsId,
            String specKey,
            String specKeyName,
            Integer goodsNum,
            Integer addressId,
            String userNote
    ){
        return orderService.addSingleOrderInfo(goodsId,specKey,specKeyName,goodsNum,addressId,userNote);
    }

    /**
     * 创建购物车订单
     * @param trolleyId
     * @return
     */
    @RequestMapping("trolleyOrder")
    public Body trolleyOrder(Integer[] trolleyId,Integer addressId, String userNote){
        return orderService.addTrolleyOrderInfo(trolleyId,addressId,userNote);
    }


}
