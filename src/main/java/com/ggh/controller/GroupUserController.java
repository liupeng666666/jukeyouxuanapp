//package com.ggh.controller;
//
//import com.ggh.common.json.Body;
//import com.ggh.service.GroupUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
///**
// * @Auther: Administrator
// * @Date: 2020/4/26 10:19
// * @Description:发起拼团
// */
//@CrossOrigin
//@Controller
//@ResponseBody
//@RequestMapping("groupUser")
//public class GroupUserController {
//    @Autowired
//    private GroupUserService groupUserService;
//
//    @RequestMapping("addGroupUser")
//    public Body addGroupUserInfo(String userId, Integer ptGoodsId, Integer ptNumber, Integer state,Integer addressId,String userNote) {
//        return groupUserService.addGroupUserInfo(userId,ptGoodsId,ptNumber,state,addressId,userNote);
//    }
//}
