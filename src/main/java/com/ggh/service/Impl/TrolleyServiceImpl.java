package com.ggh.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ggh.common.json.Body;
import com.ggh.entity.Goods;
import com.ggh.entity.Trolley;
import com.ggh.mapper.GoodsMapper;
import com.ggh.mapper.TrolleyMapper;
import com.ggh.service.TrolleyService;
import com.ggh.vo.TrolleyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chaihu
 * @function 购物车service
 * @date 2020-04-22 18:50
 */
@Service
public class TrolleyServiceImpl implements TrolleyService {

    @Autowired
    private TrolleyMapper trolleyMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 添加商品到购物车
     * @param goodsId
     * @param specKey
     * @return
     */
    @Override
    public Body addGoodsTrolley(Integer goodsId, String specKey,String specKeyName,Integer goodsNum) {

        Integer userId = StpUtil.getLoginId(-1);
        if(userId == -1){
            return Body.newInstance(500,"请先去登录");
        }
        // 根据商品id和规格值查询购物车中是否已经添加了商品
        Trolley trolleys = trolleyMapper.selectOne(new LambdaQueryWrapper<Trolley>()
                .eq(Trolley::getUserId,userId)
                .eq(Trolley::getGoodsId,goodsId)
                .eq(Trolley::getSpecKey,specKey));
        // 如果购物车中有这个商品,数量就加1
        if(trolleys != null){
            trolleys.setGoodsNum(trolleys.getGoodsNum()+1);
            if(trolleys.updateById()){
                // 添加成功
                return Body.newInstance();
            }
            return Body.newInstance(500,"添加失败");
        }
        // 如果购物车中没有该商品
        // 创建一个购物车商品
        Trolley trolley = new Trolley();
        trolley.setGoodsId(goodsId);
        trolley.setUserId(userId);
        trolley.setSpecKey(specKey);
        trolley.setSpecKeyName(specKeyName);
        trolley.setGoodsNum(goodsNum);
        trolley.setCreateDate(new Date());
        trolley.setDelFlag("0");
        if(trolley.insert()){
            // 购物车添加成功
            return Body.newInstance();
        }
        return Body.newInstance(500,"添加失败,请重试");
    }

    /**
     * 用户查询自己的购物车
     * @return
     */
    @Override
    public Body queryTrolleyList() {
        // 获取用户id
        Integer userId = StpUtil.getLoginId(-1);
        // 判断用户是否登录
        if(userId == -1){
            return Body.newInstance(500,"请先去登录");
        }
        // 如果用户登录,查询用户的购物车列表
        List<Trolley> trolleys = trolleyMapper.selectList(new LambdaQueryWrapper<Trolley>()
                .eq(Trolley::getUserId,userId)
                .eq(Trolley::getDelFlag,"0")
                .orderByDesc(Trolley::getCreateDate)
        );

        List<TrolleyVO> trolleyVOS = new ArrayList<>();
        TrolleyVO trolleyVO = new TrolleyVO();
        // 循环购物车集合,找寻匹配的商品信息
        for (Trolley trolley : trolleys) {
            Goods goods = goodsMapper.selectById(trolley.getGoodsId());
            trolleyVO.setGoods(goods);
            trolleyVO.setTrolley(trolley);
            trolleyVOS.add(trolleyVO);
        }
        return Body.newInstance(trolleyVOS);
    }

    /**
     * 用户删除购物车中的商品
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Body removeTrolley(Integer[] id) {
        for (Integer integer : id) {
            Trolley trolley = new Trolley();
            trolley.setId(integer);
            if(!trolley.deleteById()){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return Body.newInstance(500,"删除失败,请重试");
            }
        }
        return Body.newInstance();
    }
}
