package com.ggh.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ggh.common.json.Body;
import com.ggh.entity.Banner;
import com.ggh.entity.Goods;
import com.ggh.entity.GoodsCategory;
import com.ggh.entity.HistorySearch;
import com.ggh.mapper.BannerMapper;
import com.ggh.mapper.GoodsCategoryMapper;
import com.ggh.mapper.GoodsMapper;
import com.ggh.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author chaihu
 * @function
 * @date 2020-04-17 19:30
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private BannerMapper bannerMapper;

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 首页查询轮播图
     * @return
     */
    @Override
    public Body queryBanner() {
        List<Banner> banners = bannerMapper.selectList(new LambdaQueryWrapper<Banner>());
        return Body.newInstance(banners);
    }

    /**
     * 首页查询分类,查询7个
     * @return
     */
    @Override
    public Body queryTypeList() {
        List<GoodsCategory> categories = goodsCategoryMapper.selectList(new LambdaQueryWrapper<GoodsCategory>()
                .eq(GoodsCategory::getParentId,"0")
                .eq(GoodsCategory::getParentId,"0")
                .orderByAsc(GoodsCategory::getSort)
                .last("limit 7")
        );
        return Body.newInstance(categories);
    }

    /**
     * 首页查询推荐商品
     * @return
     */
    @Override
    public Body queryRecommendList() {
        List<Goods> goods = goodsMapper.selectList(new LambdaQueryWrapper<Goods>()
                .eq(Goods::getIsRecommend,1)
                .eq(Goods::getDelFlag,"0")
        );
        return Body.newInstance(goods);
    }

    /**
     * 首页搜索商品
     * @param keywords
     * @return
     */
    @Override
    public Body querySearchGoods(String keywords) {
        // 搜索分2中情况,一种是登录的,一种是未登录,先写登录的
        if(StpUtil.isLogin()){
            // 登录过,根据关键字搜索商品
            List<Goods> goods = goodsMapper.selectList(new LambdaQueryWrapper<Goods>()
                    .like(Goods::getKeywords,keywords)
                    .like(Goods::getGoodsName,keywords)
            );
            // 创建历史搜索记录
            HistorySearch historySearch = new HistorySearch();
            Integer userId = (Integer) StpUtil.getLoginId();

            historySearch.setUserId(userId);
            historySearch.setContents(keywords);
            historySearch.setCreateDate(new Date());
            if(historySearch.insert()){
                return Body.newInstance(goods);
            }
            return Body.newInstance(goods);
        }else{
            // 未登录过,根据关键字搜索商品
            List<Goods> goods = goodsMapper.selectList(new LambdaQueryWrapper<Goods>()
                    .like(Goods::getKeywords,keywords)
                    .or()
                    .like(Goods::getGoodsName,keywords)
            );
            return Body.newInstance(goods);
        }
    }
}
