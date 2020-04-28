package com.ggh.controller;

import com.ggh.common.json.Body;
import com.ggh.service.TrolleyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author chaihu
 * @function
 * @date 2020-04-22 13:55
 */
@CrossOrigin
@Controller
@ResponseBody
@RequestMapping("trolley")
public class TrolleyController {

    @Autowired
    private TrolleyService trolleyService;


    /**
     * 添加商品到购物车
     * @param goodsId 商品id
     * @param specKey 商品规格值
     * @param specKeyName  商品规格对应中文名字
     * @return
     */
    @RequestMapping("addTrolley")
    public Body addTrolley(
            Integer goodsId,
            String specKey,
            String specKeyName,
            Integer goodsNum
    ){
        return trolleyService.addGoodsTrolley(goodsId,specKey,specKeyName,goodsNum);
    }

    /**
     * 用户查询自己的购物车
     * @return
     */
    @RequestMapping("trolleyList")
    public Body trolleyList(){
        return trolleyService.queryTrolleyList();
    }

    /**
     * 用户删除自己的购物车
     * @param id  购物车id
     * @return
     */
    @RequestMapping("delTrolley")
    public Body delTrolley(Integer[] id){
        return trolleyService.removeTrolley(id);
    }

}
