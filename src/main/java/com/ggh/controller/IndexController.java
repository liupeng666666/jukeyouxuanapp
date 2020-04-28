package com.ggh.controller;

import com.ggh.common.json.Body;
import com.ggh.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chaihu
 * @function
 * @date 2020-04-17 16:12
 */
@CrossOrigin
@Controller
@ResponseBody
@RequestMapping("index")
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
     * 首页查询轮播图
     * @return
     */
    @RequestMapping("banner")
    public Body showBanner(){
        return indexService.queryBanner();
    }

    /**
     * 首页查询分类 查询7个
     * @return
     */
    @RequestMapping("type")
    public Body typeList(){
        return indexService.queryTypeList();
    }

    /**
     * 首页查询推荐商品
     * @return
     */
    @RequestMapping("recommendGoods")
    public Body recommendGoods(){
        return indexService.queryRecommendList();
    }

    /**
     * 首页搜索接口
     * @return
     */
    @RequestMapping("search")
    public Body search(String keywords){
        return indexService.querySearchGoods(keywords);
    }



}
