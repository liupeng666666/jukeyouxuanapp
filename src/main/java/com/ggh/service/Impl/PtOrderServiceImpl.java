package com.ggh.service.Impl;



import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ggh.common.json.Body;
import com.ggh.entity.*;
import com.ggh.mapper.*;
import com.ggh.service.PtOrderService;
import com.ggh.utils.DoubleOperationUtil;
import com.ggh.utils.Hutool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2020/4/26 20:46
 * @Description:拼团订单
 */
@Service
@EnableScheduling   // 1.开启定时任务
@EnableAsync        // 2.开启多线程
public class PtOrderServiceImpl implements PtOrderService {
    @Autowired
    private PtGoodsMapper ptGoodsMapper;//查询拼团商品信息
    @Autowired
    private AddressMapper addressMapper;//查地址
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private GroupUserMapper groupUserMapper;
    @Autowired
    private GroupJoinMapper groupJoinMapper;
     /**
     *发起人发起拼团订单
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Async
    @Scheduled(fixedDelay = 1000*60*1)
    public Body addPtOrderInfo(Integer userId,Integer ptGoodsId,String specId) {
        //根据商品id查询拼团商品信息(支付宝微信接口查询商品)
//        // 获取用户id
//        Integer userId = 1701;
//        // 拼团商品表id
//         Integer ptGoodsId= 2501;
//        // 商品规格值
//        String specId="22008";
        // 购买商品数量
        Integer number=2;
        // 用户地址id
        Integer addressId=1;
        // 用户备注
        String userNote="暂无";

//        if(userId == -1){
//            return Body.newInstance(500,"请先登录");
//        }
//        JkUser jkUser = jkUserMapper.selectById(userId);
        // 根据团购商品表id查询团购商品详细信息
        PtGoods ptGoods = ptGoodsMapper.selectById(ptGoodsId);
        if(ptGoods == null){
            return Body.newInstance(500,"拼团商品不存在");
        }
        // 根据地址id查询地址信息
        Address address = addressMapper.selectById(addressId);
        if(address==null){
            return Body.newInstance(500,"收货地址异常,请重试提交订单");
        }
        // 计算订单总价
        Double goodsPrice = DoubleOperationUtil.mul(ptGoods.getPtPrice(),number.doubleValue());

        // 计算应付金额
        Double orderAmount= DoubleOperationUtil.mul(ptGoods.getPtPrice(),number.doubleValue());
        // 创建订单
        Order order = new Order();
        order.setOrderSn(Hutool.getId());//订单编号
        order.setUserId(userId);//用户id
        order.setPromType("1");//拼团类型
        order.setOrderStatus("4");//可发货状态
        order.setShippingStatus("0");//发货状态
        order.setPayStatus("0");//支付状态
        order.setConsignee(address.getConsigneeName());//收货人
        order.setConsigneeAdd(address.getConsigneeAdd());//收货人地址
        order.setConsigneePhone(address.getConsigneePhone());//收货人电话
        order.setGoodsPrice(goodsPrice);//总价
        order.setOrderAmount(orderAmount);//应付
        order.setTotalAmount(orderAmount);//订单总价
        order.setAddTime(new Date());//下单时间
        order.setUserNote(userNote);//用户备注
        order.setStaffId("未处理分公司业务");
        order.setOfficeId("未处理分公司业务");
        order.setDelFlag("0");//逻辑删除显示
        // 创建订单
        if(order.insert()){

            // 查询订单id,根据订单编号
            Order oldOrder = orderMapper.selectOne(new LambdaQueryWrapper<Order>().eq(Order::getOrderSn,order.getOrderSn()));
            // 创建 订单商品信息
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setGoodsId(ptGoods.getGoodsId());//商品id
            orderGoods.setOrderId(oldOrder.getId());//订单编号id
            orderGoods.setGoodsNum(number);//购买商品数量
            orderGoods.setGoodsPrice(ptGoods.getPtPrice());//拼团价格
            orderGoods.setSpecKey(specId);//规格id
            orderGoods.setSpecKeyName("规格名称暂无");
            orderGoods.setCommentStatus("0");//评论状态
            orderGoods.setDelFlag("0");//逻辑删除

            if(orderGoods.insert()){// 订单商品信息添加成功
                Date startTime = new Date();//计算拼团发起时间
                Date endTime = new Date(startTime.getTime() + 1*60*1000);// 拼团结束时间
                // 创建 发起拼团信息
                GroupUser groupUser = new GroupUser();
                groupUser.setPtId(Hutool.getId());//发起团id
                groupUser.setPtGoodsId(ptGoodsId);//拼团商品表id
                groupUser.setUserId(userId.toString());//用户id-openid
                groupUser.setStartTime(startTime);
                groupUser.setEndTime(endTime);
                groupUser.setState(0);// 0拼团中,1:拼团成功,2拼团失败

                if(groupUser.insert()){ //发起团的信息创建成功
                   //根据发起团id查询发起团信息
                    GroupUser groupUser1  = groupUserMapper.selectOne(new LambdaQueryWrapper<GroupUser>()
                            .eq(GroupUser::getPtId,groupUser.getPtId())
                    );
                    // 发起信息加入 参团用户表信
                    GroupJoin groupJoin = new GroupJoin();
                    groupJoin.setGroupUserId(groupUser1.getId());//发起团id
                    groupJoin.setUserId(userId.toString());//发起用户id
                    groupJoin.setCreateTime(new Date());
                    groupJoin.setOrderId(order.getId());

                    if(groupJoin.insert()){//将发起者加入 参与用户团

                        if(ptGoods.getNumber()>=number){
                            ptGoods.setNumber(ptGoods.getNumber()-number);
                            ptGoods.setBuyNumber(ptGoods.getBuyNumber()+number);
                            ptGoods.updateById();
                            if(new Date().getTime()>groupUser.getEndTime().getTime()){
                                //join表大小不等于user表ptNum
                                //修改user状态为2  修改订单表状态为 ----失败
                                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                                return Body.newInstance(500,"拼团时间超时。。。");
                            }else{
                                return Body.newInstance();
                            }

                        }else{
                           // TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                            return Body.newInstance(500,"库存不足!");
                        }



                    }

                }

            }
        }
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return Body.newInstance(500,"发起拼团失败!");
    }
    /**
     * 参与拼团
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Body joinPtOrderInfo(Integer groupUserId,Integer userId,Integer joinUserId,String specId) {
        //根据商品id查询团购商品信息(支付宝微信接口查询商品)
       // PtGoods ptGoods = ptGoodsMapper.selectById(ptGoodsId);
//        //发起团的id
//        Integer groupUserId=1;
//        // 获取用户id
//        Integer joinUserId = 170002;
//        //发起团用户id
//        Integer userId = 170001;
//       //商品规格值
//        String specId="22008";
        // 购买商品数量
        Integer number=2;
        // 商品地址id
        Integer addressId=2;
        // 用户备注
        String userNote="暂无";

        // 获取用户id
//        Integer userId = StpUtil.getLoginId(-1);
//        if(userId == -1){
//            return Body.newInstance(500,"请先登录");
//        }
//        JkUser jkUser = jkUserMapper.selectById(userId);
        // 根据发起团的信息id查询 团购信息
        GroupUser groupUser = groupUserMapper.selectById(groupUserId);
        if(groupUser == null){
            return Body.newInstance(500,"团购信息不存在");
        }
        if(groupUser.getState()!=0){
            return Body.newInstance(500,"该团已经无法再加入");
        }

        // 判断该团购的团信息是否失效
        Date date = new Date();
        if(date.getTime()>groupUser.getEndTime().getTime()){
            return Body.newInstance(500,"该团已经失效");
        }

        // 根据团购信息表中的团购商品表id查询团购商品
        PtGoods ptGoods = ptGoodsMapper.selectById(groupUser.getPtGoodsId());
        if(ptGoods == null){
            return Body.newInstance(500,"团购商品不存在");
        }
        // 根据地址id查询地址信息
        Address address = addressMapper.selectById(addressId);
        if(address==null){
            return Body.newInstance(500,"收货地址异常,请重新设置地址");
        }

        // 计算订单总价
        Double goodsPrice = DoubleOperationUtil.mul(ptGoods.getPtPrice(),number.doubleValue());
        // 计算应付金额
        Double orderAmount= DoubleOperationUtil.mul(ptGoods.getPtPrice(),number.doubleValue());

        // 创建订单
        Order order = new Order();
        order.setOrderSn(Hutool.getId());
        order.setUserId(joinUserId);//参与用户id
        order.setPromType("1");//拼团商品
        order.setOrderStatus("4");//可发货状态
        order.setShippingStatus("0");//发货状态
        order.setPayStatus("0");//支付状态
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


        if(order.insert()){ // 参与用户订单创建成功
            // 根据订单编号查询订单id
            Order oldOrder = orderMapper.selectOne(new LambdaQueryWrapper<Order>().eq(Order::getOrderSn,order.getOrderSn()));

            // 创建订单商品信息
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setGoodsId(ptGoods.getGoodsId());
            orderGoods.setOrderId(oldOrder.getId());
            orderGoods.setGoodsNum(number);
            orderGoods.setGoodsPrice(ptGoods.getPtPrice());
            orderGoods.setSpecKey(specId);
            orderGoods.setSpecKeyName("规格名暂无");
            orderGoods.setCommentStatus("0");
            orderGoods.setDelFlag("0");
            if(orderGoods.insert()){// 订单商品创建成功
                // 创建参团人信息
                GroupJoin groupJoin = new GroupJoin();
                groupJoin.setGroupUserId(groupUserId);//发起团id
                groupJoin.setUserId(userId.toString());//发起人
                groupJoin.setJoinUserId(joinUserId.toString());//参团人
                groupJoin.setCreateTime(new Date());
                groupJoin.setOrderId(order.getId());


                if(groupJoin.insert()){//创建参与团信息
                    // 检查团人数是否已经凑齐
                    List<GroupJoin> groupJoins = groupJoinMapper.selectList(new LambdaQueryWrapper<GroupJoin>()
                            .eq(GroupJoin::getGroupUserId,groupUserId)
                    );
                    System.out.println("-------274---参与人数------"+groupJoins.size());
                    // 检查团人数够不够
                    if(groupJoins.size() <= ptGoods.getPtNum()){
                       if(groupJoins.size() == ptGoods.getPtNum()){ // 团人数已经够了
                            // 根据参与团中发起团id  修改发起团的状态
                           GroupUser logGroupUser = groupUserMapper.selectById(groupJoin.getGroupUserId());
                           logGroupUser.setState(1);// 修改成拼团成功
                           logGroupUser.updateById();
                            //修改拼团订单(拼主，拼A...,自己)   状态为2:可发货
                           List<GroupJoin> groupJoins1 = groupJoinMapper.selectList(new LambdaQueryWrapper<GroupJoin>()
                                   .eq(GroupJoin::getGroupUserId,groupJoin.getGroupUserId())
                           );

                           order.setShippingStatus("2");//可发货
                           order.setOrderStatus("1");//待收货
                           for (GroupJoin gj : groupJoins1) {
                               order.update(new LambdaQueryWrapper<GroupJoin>()
                                  .eq(GroupJoin::getId,gj.getOrderId())
                               );
                           }

                            //修改商品数量
                           if(ptGoods.getNumber()>=number){
                               ptGoods.setNumber(ptGoods.getNumber()-number);
                               ptGoods.setBuyNumber(ptGoods.getBuyNumber()+number);
                               ptGoods.updateById();
                               return Body.newInstance();
                           }else{
                               return Body.newInstance(500,"库存不足!");
                           }


                       }else{
                           // 不修改发起团的状态---还是0拼团中
                           //不修改订单状态---还是4---0
                           //修改商品数量
                           if(ptGoods.getNumber()>=number){
                               ptGoods.setNumber(ptGoods.getNumber()-number);
                               ptGoods.setBuyNumber(ptGoods.getBuyNumber()+number);
                               ptGoods.updateById();
                               return Body.newInstance(500,"等待拼团中......");
                           }else{
                               return Body.newInstance(500,"库存不足!");
                           }

                       }

                    }else{
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        return Body.newInstance(500,"拼团已完成");
                    }
                }
            }

        }
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return Body.newInstance(500,"参与失败,请重试");
    }




}
