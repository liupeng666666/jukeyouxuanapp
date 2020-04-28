//package com.ggh.service.Impl;
//
//import cn.dev33.satoken.stp.StpUtil;
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.ggh.common.json.Body;
//import com.ggh.entity.*;
//import com.ggh.mapper.AddressMapper;
//import com.ggh.mapper.OrderMapper;
//import com.ggh.mapper.PtGoodsMapper;
//import com.ggh.service.GroupUserService;
//import com.ggh.utils.DoubleOperationUtil;
//import com.ggh.utils.Hutool;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.transaction.interceptor.TransactionAspectSupport;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
///**
// * @Auther: Administrator
// * @Date: 2020/4/26 10:32
// * @Description:
// */
//@Service
//public class GroupUserServiceImpl implements GroupUserService {
//    @Autowired
//    private GroupUserService groupUserService;
//    @Autowired
//    private PtGoodsMapper ptGoodsMapper;
//    @Autowired
//    private AddressMapper addressMapper;
//    @Autowired
//    private OrderMapper orderMapper;
//    /**
//     *用户发起拼团
//     */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public Body addGroupUserInfo(String userId, Integer ptGoodsId, Integer ptNumber,Integer state,Integer addressId,String userNote) {
//        // 获取用户id
////        Integer useId = StpUtil.getLoginId(-1);
////        if(useId == -1){
////            return Body.newInstance(500,"请先去登录");
////        }
//        String orderId=null;
//        PtGoods ptGoods = ptGoodsMapper.selectOne(new LambdaQueryWrapper<PtGoods>()
//                .eq(PtGoods::getGoodsId,ptGoodsId)
//        );
//
//        GroupUser groupUser = new GroupUser();
//        groupUser.setId(1);
//        groupUser.setUserId(userId);
//        groupUser.setPtGoodsId(ptGoodsId);
//        groupUser.setPtNumber(ptGoods.getPtNum());
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date();
//        groupUser.setStartTime(date);
//        groupUser.setEndTime(new Date(date.getTime() + 300000));
//        groupUser.setState(state);
//        if(state==0){
//            //未支付状态    只生成发起拼团订单
//            groupUser.insert();
//            return Body.newInstance(500,"未支付");
//        }else if(state==1){
//            groupUser.setWcPtNumber(1);
//            groupUser.insert();
//            orderId=Hutool.getId();
//            groupUser.setOrderId(orderId);//生成订单号
//            //拼团商品表商品数量-1 获取拼团商品价格 数量，将拼团商品价格 给订单
//            ptGoods.setNumber(ptGoods.getNumber()-1);
//            ptGoods.setBuyNumber(ptGoods.getBuyNumber()+1);
//            ptGoods.updateById();
//            //向订单表添加拼团订单1,发起拼团订单，获取购买人的地址
//
//            //addPtOrderInfo(ptGoods.getGoodsId(),ptGoods.getPtPrice(),orderId, ptGoods.getSpecId(), "1", 1, addressId, userNote);
//
//           // return Body.newInstance();
//
//
//        }else{
//            //支付失败
//            return Body.newInstance(500,"提交失败,请重试");
//        }
//
//
////        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//        return Body.newInstance();
//    }
//
//    /**
//     * 创建拼团订单
//     * @return
//     */
//    @Transactional(rollbackFor = Exception.class)
//    public Body addPtOrderInfo(Integer ptGoodsId,Integer ptPrice,String orderId, String specKey, String specKeyName, Integer goodsNum,Integer addressId,String userNote) {
//        // 获取用户id
//        Integer userId = 1;
////        if(userId == -1){
////            return Body.newInstance(500,"请先去登录");
////        }
//        // 根据收货地址id查询收货地址信息
//        Address address = addressMapper.selectById(addressId);
//        if(address==null){
//            return Body.newInstance(500,"收货地址异常,请重试提交订单");
//        }
//
//        // 计算订单总价
//        Double goodsPrice = DoubleOperationUtil.mul(ptPrice,goodsNum.doubleValue());
//        // 计算应付金额
//        Double sum = DoubleOperationUtil.sub(ptPrice,0);
//        Double orderAmount= DoubleOperationUtil.mul(sum,goodsNum.doubleValue());
//
//
//        Order order = new Order();
//        order.setOrderSn(orderId);
//        order.setUserId(userId);
//        order.setPromType("1");
//        order.setOrderStatus("4");//4已付款等待拼团
//        order.setShippingStatus("0");
//        order.setPayStatus("1");
//        order.setConsignee(address.getConsigneeName());
//        order.setConsigneeAdd(address.getConsigneeAdd());
//        order.setConsigneePhone(address.getConsigneePhone());
//        order.setGoodsPrice(goodsPrice);
//        order.setOrderAmount(orderAmount);
//        order.setTotalAmount(orderAmount);
//        order.setAddTime(new Date());
//        order.setUserNote(userNote);
//
//        order.setStaffId("未处理分公司业务");
//        order.setOfficeId("未处理分公司业务");
//
//        order.setDelFlag("0");
//        // 创建订单
//        if(order.insert()){
//            // 查询订单id,根据订单编号
//            Order oldOrder = orderMapper.selectOne(new LambdaQueryWrapper<Order>().eq(Order::getOrderSn,order.getOrderSn()));
//            // 创建订单商品信息
//            OrderGoods orderGoods = new OrderGoods();
//            orderGoods.setGoodsId(ptGoodsId);
//            orderGoods.setOrderId(oldOrder.getId());
//            orderGoods.setGoodsNum(goodsNum);
//            orderGoods.setGoodsPrice(goodsPrice);
//            orderGoods.setSpecKey(specKey);
//            orderGoods.setSpecKeyName(specKeyName);
//            orderGoods.setDelFlag("0");
//            if(orderGoods.insert()){
//                // 订单商品信息添加成功
//                return Body.newInstance();
//            }
//        }
//        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//        return Body.newInstance(500,"订单提交失败,请重试");
//    }
//
//
//}
