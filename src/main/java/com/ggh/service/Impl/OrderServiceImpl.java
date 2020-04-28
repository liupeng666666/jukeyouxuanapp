package com.ggh.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ggh.common.json.Body;
import com.ggh.entity.*;
import com.ggh.mapper.*;
import com.ggh.service.OrderService;
import com.ggh.utils.DoubleOperationUtil;
import com.ggh.utils.Hutool;
import com.ggh.vo.OrderGoodsVO;
import com.ggh.vo.OrderListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chaihu
 * @function 订单service
 * @date 2020-04-23 16:21
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderGoodsMapper orderGoodsMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private TrolleyMapper trolleyMapper;

    /**
     * 创建单独购买订单
     * @param goodsId
     * @param specKey
     * @param specKeyName
     * @param goodsNum
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Body addSingleOrderInfo(Integer goodsId, String specKey, String specKeyName, Integer goodsNum,Integer addressId,String userNote) {
        // 获取用户id
        Integer userId = StpUtil.getLoginId(-1);
        if(userId == -1){
            return Body.newInstance(500,"请先去登录");
        }
        // 根据收货地址id查询收货地址信息
        Address address = addressMapper.selectById(addressId);
        if(address==null){
            return Body.newInstance(500,"收货地址异常,请重试提交订单");
        }

        // 根据商品id查询商品价格
        Goods goods = goodsMapper.selectById(goodsId);
        // 计算订单总价
        Double goodsPrice = DoubleOperationUtil.mul(goods.getGoodsPrice(),goodsNum.doubleValue());

        // 计算应付金额
        Double sum = DoubleOperationUtil.sub(goods.getGoodsPrice(),goods.getGoodsDiscount());
        Double orderAmount= DoubleOperationUtil.mul(sum,goodsNum.doubleValue());


        Order order = new Order();
        order.setOrderSn(Hutool.getId());
        order.setUserId(userId);
        order.setPromType("0");
        order.setOrderStatus("0");
        order.setShippingStatus("0");
        order.setPayStatus("0");
        order.setConsignee(address.getConsigneeName());
        order.setConsigneeAdd(address.getConsigneeAdd());
        order.setConsigneePhone(address.getConsigneePhone());
        order.setGoodsPrice(goodsPrice);
        order.setOrderAmount(orderAmount);
        order.setTotalAmount(orderAmount);
        order.setAddTime(new Date());
        order.setUserNote(userNote);

        order.setStaffId("未处理分公司业务");
        order.setOfficeId("未处理分公司业务");

        order.setDelFlag("0");
        // 创建订单
        if(order.insert()){
            // 查询订单id,根据订单编号
            Order oldOrder = orderMapper.selectOne(new LambdaQueryWrapper<Order>().eq(Order::getOrderSn,order.getOrderSn()));
            // 创建订单商品信息
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setGoodsId(goodsId);
            orderGoods.setOrderId(oldOrder.getId());
            orderGoods.setGoodsNum(goodsNum);
            orderGoods.setGoodsPrice(goods.getGoodsPrice());
            orderGoods.setSpecKey(specKey);
            orderGoods.setSpecKeyName(specKeyName);
            orderGoods.setDelFlag("0");
            if(orderGoods.insert()){
                // 订单商品信息添加成功
                return Body.newInstance();
            }
        }
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return Body.newInstance(500,"订单提交失败,请重试");
    }

    /**
     * 创建购物车订单
     * @param trolleyId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Body addTrolleyOrderInfo(Integer[] trolleyId,Integer addressId, String userNote) {
        // 获取用户id
        Integer userId = StpUtil.getLoginId(-1);
        if(userId == -1){
            return Body.newInstance(500,"请先登录");
        }
        List<Trolley> trolleys = new ArrayList<>();
        for (Integer integer : trolleyId) {
            Trolley trolley = trolleyMapper.selectById(integer);
            if(!trolley.getUserId().equals(userId)){
                Body.newInstance(500,"购物车商品信息异常");
            }
            trolleys.add(trolley);
        }
        // 得到购物车商品信息集合
        // 循环得到订单总价,应付总价
        Double goodsPrice = 0.0;
        Double orderAmount = 0.0;
        //根据购物车商品几个 查询出每个商品的价格
        for (Trolley trolley : trolleys) {
            Goods goods = goodsMapper.selectById(trolley.getGoodsId());
            goodsPrice = goodsPrice + DoubleOperationUtil.mul(goods.getGoodsPrice(),trolley.getGoodsNum().doubleValue());
            orderAmount = orderAmount + DoubleOperationUtil.mul(DoubleOperationUtil.sub(goods.getGoodsPrice(),goods.getGoodsDiscount()),trolley.getGoodsNum().doubleValue());
        }
        // 得到商品总价和应付总价

        // 得到收货人信息
        Address address = addressMapper.selectById(addressId);

        // 创建订单信息
        Order order = new Order();
        order.setOrderSn(Hutool.getId());
        order.setUserId(userId);
        order.setPromType("0");
        order.setOrderStatus("0");
        order.setShippingStatus("0");
        order.setPayStatus("0");
        order.setConsignee(address.getConsigneeName());
        order.setConsigneeAdd(address.getConsigneeAdd());
        order.setConsigneePhone(address.getConsigneePhone());
        order.setGoodsPrice(goodsPrice);
        order.setOrderAmount(orderAmount);
        order.setTotalAmount(orderAmount);
        order.setAddTime(new Date());
        order.setUserNote(userNote);

        order.setStaffId("未处理分公司业务");
        order.setOfficeId("未处理分公司业务");

        order.setDelFlag("0");
        if(order.insert()){
            // 订单创建成功
            // 查询订单id,根据订单编号
            Order oldOrder = orderMapper.selectOne(new LambdaQueryWrapper<Order>().eq(Order::getOrderSn,order.getOrderSn()));
            // 创建订单商品信息
            for (Trolley trolley : trolleys) {
                Goods goods = goodsMapper.selectById(trolley.getGoodsId());
                OrderGoods orderGoods = new OrderGoods();
                orderGoods.setGoodsId(goods.getId());
                orderGoods.setOrderId(oldOrder.getId());
                orderGoods.setGoodsNum(trolley.getGoodsNum());
                orderGoods.setGoodsPrice(goods.getGoodsPrice());
                orderGoods.setSpecKey(trolley.getSpecKey());
                orderGoods.setSpecKeyName(trolley.getSpecKeyName());
                orderGoods.setDelFlag("0");
                if(!orderGoods.insert()){
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return Body.newInstance(500,"订单商品信息创建失败");
                }
                // 在循环添加订单商品表信息后,删除数据
                if(!trolley.deleteById()){
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return Body.newInstance(500,"删除购物车商品信息失败,请重试");
                }
            }

            return Body.newInstance();
        }

        return Body.newInstance(500,"订单创建失败");
    }

    /**
     * 用户取消订单  订单状态(默认0)(0待付款,1待发货,2待收货,3已完成,9已取消)
     * @param orderId 订单id
     * @param cancelDesc 取消原因
     * @return
     */
    @Override
    public Body cancelOrderInfo(Integer orderId, String cancelDesc) {
        Order order = orderMapper.selectById(orderId);
        if(order == null){
            return Body.newInstance(500,"订单不存在");
        }
        order.setOrderStatus("9");
        order.setUserNote(cancelDesc);
        if(order.updateById()){
            return Body.newInstance();
        }
        return Body.newInstance(500,"取消失败,请重试");
    }

    /**
     * 用户确认收货功能
     * @param orderId
     * @return
     */
    @Override
    public Body confirmOrderInfo(Integer orderId) {
        Order order = orderMapper.selectById(orderId);
        if(order == null){
            return Body.newInstance(500,"订单不存在");
        }
        // 待收货状态
        String orderStatus = "2";
        if(!orderStatus.equals(order.getOrderStatus())){
            return Body.newInstance(500,"无法收货!");
        }
        // 可以收货,改成已完成
        order.setOrderStatus("3");
        order.setConfirmTime(new Date());
        if(order.updateById()){
            return Body.newInstance();
        }
        return Body.newInstance(500,"收货失败,请重试");
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
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Body commentOrderInfo(Integer orderGoodsId, Integer globalComment, String content, String images, Integer logisticComment, Integer accordComment, Integer serviceComment, Integer isAnonym) {
        // 获取用户id
        Integer userId = StpUtil.getLoginId(-1);
        if(userId == -1){
            return Body.newInstance(500,"请先登录");
        }

        OrderGoods orderGoods = orderGoodsMapper.selectById(orderGoodsId);
        if(orderGoods == null){
            return Body.newInstance(500,"订单商品不存在");
        }
        // 订单商品待评价状态
        String commentStatus = "1";
        if(commentStatus.equals(orderGoods.getCommentStatus())){
            return Body.newInstance(500,"已评价!");
        }
        // 未评价,可以评价
        Comment comment =new Comment();
        comment.setGoodsId(orderGoods.getGoodsId());
        comment.setOrderId(orderGoods.getOrderId());
        comment.setUserId(userId);
        comment.setGlobalComment(globalComment);
        comment.setContent(content);
        comment.setImages(images);
        comment.setLogisticComment(logisticComment);
        comment.setAccordComment(accordComment);
        comment.setServiceComment(serviceComment);
        comment.setIsAnonym(isAnonym);
        if(comment.insert()){
            // 评价成功,修改订单商品状态
            orderGoods.setCommentStatus("1");
            if(orderGoods.updateById()){
                return Body.newInstance();
            }
        }
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return Body.newInstance(500,"评价失败,请重试");
    }

    /**
     * 根据订单状态查询订单列表
     * @param orderStatus
     * @return
     */
    @Override
    public Body queryOrderList(String orderStatus) {
        // 获取用户id
        Integer userId = StpUtil.getLoginId(-1);
        if(userId == -1){
            return Body.newInstance(500,"请先登录");
        }
        /**
         * 此处代码可能有人看不懂,我也有点懵,主要还是不想写sql语句,可能有人会说,sql几行就搞定,但是我就是不想写,你能咋地我
         * OrderListVO 是为了存储每个订单的数据,他里面有每一个订单信息,因为一个订单可能有2个以上的商品
         * OrderGoodsVO 是为了存储订单商品数据,每个是订单商品的信息,因为订单商品表中没有商品图片和商品名称
         */
        List<OrderListVO> orderListVOS = new ArrayList<>();
        List<OrderGoodsVO> orderGoodsVOS = new ArrayList<>();
        OrderListVO orderListVO = new OrderListVO();
        OrderGoodsVO orderGoodsVO = new OrderGoodsVO();

        String commentStatus = "5";
        if(commentStatus.equals(orderStatus)){
            // 查询待评价列表
            // 根据用户id查询用户的订单列表
            List<Order> orders = orderMapper.selectList(new LambdaQueryWrapper<Order>()
                    .eq(Order::getDelFlag,"0")
                    .eq(Order::getUserId,userId)
                    .orderByDesc(Order::getAddTime)
            );
            // 根据订单id查询所有订单的未评价商品
            for (Order order : orders) {
                List<OrderGoods> orderGoods = orderGoodsMapper.selectList(new LambdaQueryWrapper<OrderGoods>()
                        .eq(OrderGoods::getOrderId,order.getId())
                        .eq(OrderGoods::getCommentStatus,"0")
                );
                for (OrderGoods orderGood : orderGoods) {
                    Goods goods = goodsMapper.selectById(orderGood.getGoodsId());
                    // 把商品数据存入订单商品vo中
                    orderGoodsVO.setGoods(goods);
                    // 把订单商品数据存入订单商品vo中
                    orderGoodsVO.setOrderGoods(orderGood);
                    // 把订单商品数据存入集合
                    orderGoodsVOS.add(orderGoodsVO);
                }
                // 把订单商品+商品数据存入订单列表vo中
                orderListVO.setOrderGoodsVOS(orderGoodsVOS);
                orderListVOS.add(orderListVO);
            }
            return Body.newInstance(orderListVOS);

        }else{
            // 根据用户id查询用户的订单列表
            List<Order> orders = orderMapper.selectList(new LambdaQueryWrapper<Order>()
                    .eq(Order::getDelFlag,"0")
                    .eq(Order::getUserId,userId)
                    .eq(Order::getOrderStatus,orderStatus)
                    .orderByDesc(Order::getAddTime)
            );

            // 根据订单id查询订单商品
            for (Order order : orders) {
                List<OrderGoods> orderGoods = orderGoodsMapper.selectList(new LambdaQueryWrapper<OrderGoods>()
                        .eq(OrderGoods::getOrderId,order.getId())
                );
                for (OrderGoods orderGood : orderGoods) {
                    Goods goods = goodsMapper.selectById(orderGood.getGoodsId());
                    // 把商品数据存入订单商品vo中
                    orderGoodsVO.setGoods(goods);
                    // 把订单商品数据存入订单商品vo中
                    orderGoodsVO.setOrderGoods(orderGood);
                    // 把订单商品数据存入集合
                    orderGoodsVOS.add(orderGoodsVO);
                }
                // 把订单商品+商品数据存入订单列表vo中
                orderListVO.setOrderGoodsVOS(orderGoodsVOS);
                orderListVOS.add(orderListVO);
            }
            return Body.newInstance(orderListVOS);
        }
    }


}
