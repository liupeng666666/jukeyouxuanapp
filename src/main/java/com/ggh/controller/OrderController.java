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
 * @function 订单controller
 * @date 2020-04-23 20:14
 */
@CrossOrigin
@Controller
@ResponseBody
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 用户取消订单
     * @param orderId 订单id
     * @param cancelDesc 取消原因
     * @return
     */
    @RequestMapping("cancelOrder")
    public Body cancelOrder(Integer orderId,String cancelDesc){
        return orderService.cancelOrderInfo(orderId,cancelDesc);
    }


    /**
     * 用户收货
     * @param orderId 订单id
     * @return
     */
    @RequestMapping("confirmOrder")
    public Body confirmOrder(Integer orderId){
        return orderService.confirmOrderInfo(orderId);
    }


    /**
     * 用户评价订单商品
     * @param orderGoodsId 订单id
     * @param globalComment 整体评价
     * @param content 评论内容
     * @param images 评论图
     * @param logisticComment 物流评价
     * @param accordComment 描述相符
     * @param serviceComment 服务态度
     * @param isAnonym 是否匿名
     * @return
     */
    @RequestMapping("commentOrder")
    public Body commentOrder(
            Integer orderGoodsId,
            Integer globalComment,
            String content,
            String images,
            Integer logisticComment,
            Integer accordComment,
            Integer serviceComment,
            Integer isAnonym
    ){
        return orderService.commentOrderInfo(orderGoodsId,globalComment,content,images,logisticComment,accordComment,serviceComment,isAnonym);
    }


    /**
     * 订单列表
     * @param orderStatus 订单状态 0:未付款订单,1:待发货,2:待收货,3已完成,5待评价(未测试),售后/退还(未设定状态)
     * @return
     */
    @RequestMapping("orderList")
    public Body orderList(
            String orderStatus
    ){
        return orderService.queryOrderList(orderStatus);
    }



}
