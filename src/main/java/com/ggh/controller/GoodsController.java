package com.ggh.controller;

import com.ggh.common.json.Body;
import com.ggh.service.CommentService;
import com.ggh.service.GoodsCollectService;
import com.ggh.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chaihu
 * @function 商品controller
 * @date 2020-04-20 12:04
 */
@CrossOrigin
@Controller
@ResponseBody
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private GoodsCollectService goodsCollectService;

    /**
     * 根据商品id查询商品详情接口
     * @param goodsId
     * @return
     */
    @RequestMapping("goodsInfo")
    public Body goodsInfo(
            Integer goodsId
    ){
        return goodsService.queryGoodsInfo(goodsId);
    }

    /**
     * 查询商品的属性
     * @param goodsId
     * @return
     */
    @RequestMapping("goodsSpec")
    public Body goodsSpec(Integer goodsId){
        return goodsService.queryGoodsSpec(goodsId);
    }


    /**
     * 根据商品id查询商品所有评论
     * @param goodsId
     * @return
     */
    @RequestMapping("comment")
    public Body commentList(
            Integer goodsId
    ){
        return commentService.queryCommentByGoodsId(goodsId);
    }

    /**
     * 用户收藏商品
     * @param goodsId
     * @return
     */
    @RequestMapping("addGoodsCollect")
    public Body addGoodsCollect(Integer goodsId){
        return goodsCollectService.addGoodsCollectInfo(goodsId);
    }

    /**
     * 用户取消收藏
     * @param goodsId
     * @return
     */
    @RequestMapping("cancelGoodsCollect")
    public Body cancelGoodsCollect(Integer goodsId){
        return goodsCollectService.cancelGoodsCollectInfo(goodsId);
    }

    /**
     * 检查商品是否被收藏
     * @param goodsId
     * @return
     */
    @RequestMapping("checkGoodsCollect")
    public Body checkGoodsCollect(Integer goodsId){
        return goodsCollectService.checkGoodsCollectInfo(goodsId);
    }
}
