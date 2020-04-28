package com.ggh.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ggh.common.json.Body;
import com.ggh.entity.GoodsCollect;
import com.ggh.mapper.GoodsCollectMapper;
import com.ggh.service.GoodsCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author chaihu
 * @function 收藏service
 * @date 2020-04-23 22:33
 */
@Service
public class GoodsCollectServiceImpl implements GoodsCollectService {

    @Autowired
    private GoodsCollectMapper goodsCollectMapper;

    /**
     * 用户查询所有的收藏的商品
     * @return
     */
    @Override
    public Body queryCollect() {
        // 获取用户id
        Integer userId = StpUtil.getLoginId(-1);
        if(userId == -1){
            return Body.newInstance(500,"请先登录");
        }
        List<GoodsCollect> goodsCollects = goodsCollectMapper.selectList(new LambdaQueryWrapper<GoodsCollect>()
                .eq(GoodsCollect::getUserId,userId)
                .orderByDesc(GoodsCollect::getAddTime)
        );
        return Body.newInstance(goodsCollects);
    }

    /**
     * 用户添加收藏的商品
     * @param goodsId
     * @return
     */
    @Override
    public Body addGoodsCollectInfo(Integer goodsId) {
        // 获取用户id
        Integer userId = StpUtil.getLoginId(-1);
        if(userId == -1){
            return Body.newInstance(500,"请先登录");
        }
        GoodsCollect goodsCollect = new GoodsCollect();
        goodsCollect.setUserId(userId);
        goodsCollect.setGoodsId(goodsId);
        goodsCollect.setAddTime(new Date());
        goodsCollect.setDelFlag("0");
        if(goodsCollect.insert()){
            return Body.newInstance();
        }
        return Body.newInstance(500,"收藏失败,请重试");
    }

    /**
     * 用户取消收藏
     * @param goodsId
     * @return
     */
    @Override
    public Body cancelGoodsCollectInfo(Integer goodsId) {
        // 获取用户id
        Integer userId = StpUtil.getLoginId(-1);
        if(userId == -1){
            return Body.newInstance(500,"请先登录");
        }
        GoodsCollect goodsCollect = goodsCollectMapper.selectOne(new LambdaQueryWrapper<GoodsCollect>()
                .eq(GoodsCollect::getUserId,userId)
                .eq(GoodsCollect::getGoodsId,goodsId)
        );
        // 检查用户是否收藏
        if(goodsCollect == null){
            Body.newInstance(500,"用户未收藏,是否收藏?");
        }
        // 取消收藏
        if(goodsCollect.deleteById()){
            return Body.newInstance();
        }
        return Body.newInstance(500,"取消失败,请重试");
    }

    /**
     * 检查用户是否已经收藏商品
     * @return
     */
    @Override
    public Body checkGoodsCollectInfo(Integer goodsId) {
        // 获取用户id
        Integer userId = StpUtil.getLoginId(-1);
        if(userId == -1){
            return Body.newInstance(500,"请先登录");
        }
        GoodsCollect goodsCollect = goodsCollectMapper.selectOne(new LambdaQueryWrapper<GoodsCollect>()
                .eq(GoodsCollect::getUserId,userId)
                .eq(GoodsCollect::getGoodsId,goodsId)
        );
        Integer collect;
        if(goodsCollect == null){
            // 未收藏
            collect = 0;
            return Body.newInstance(collect);
        }else {
            // 已经收藏
            collect = 1;
            return Body.newInstance(collect);
        }
    }
}
