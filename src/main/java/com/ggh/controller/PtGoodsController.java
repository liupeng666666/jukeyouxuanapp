package com.ggh.controller;

import com.ggh.common.json.Body;
import com.ggh.service.PtGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: Administrator
 * @Date: 2020/4/26 09:54
 * @Description:
 */
@CrossOrigin
@Controller
@ResponseBody
@RequestMapping("ptgoods")
public class PtGoodsController {
    @Autowired
    private PtGoodsService ptGoodsService;

    @RequestMapping("ptgoodsInfo")
    public Body ptGoodsInfo(Integer ptGoodsId){
        return ptGoodsService.queryPtGoodsInfo(ptGoodsId);
    }
}
