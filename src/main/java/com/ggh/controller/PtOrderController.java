package com.ggh.controller;

import com.ggh.common.json.Body;
import com.ggh.service.PtOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: Administrator
 * @Date: 2020/4/27 11:15
 * @Description:
 */
@CrossOrigin
@Controller
@ResponseBody
@RequestMapping("ptOrder")
public class PtOrderController {
    @Autowired
    private PtOrderService ptOrderService;

    @RequestMapping("addPtOrderInfo")
    public Body addPtOrderInfo(Integer userId,Integer ptGoodsId,String specId){
        return ptOrderService.addPtOrderInfo(userId,ptGoodsId,specId);
    }
    @RequestMapping("joinPtOrderInfo")
    public Body joinPtOrderInfo(Integer groupUserId,Integer userId,Integer joinUserId,String specId){
        return ptOrderService.joinPtOrderInfo(groupUserId,userId,joinUserId,specId);
    }
}
