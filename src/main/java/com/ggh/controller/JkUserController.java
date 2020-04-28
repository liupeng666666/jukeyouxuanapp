package com.ggh.controller;

import com.ggh.common.json.Body;
import com.ggh.entity.GoodsCollect;
import com.ggh.service.GoodsCollectService;
import com.ggh.service.JkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chaihu
 * @function 用户controller
 * @date 2020-04-23 19:59
 */
@CrossOrigin
@Controller
@ResponseBody
@RequestMapping("jkUser")
public class JkUserController {

    @Autowired
    private JkUserService jkUserService;

    @Autowired
    private GoodsCollectService goodsCollectService;

    /**
     * 查询用户信息
     * @return
     */
    @RequestMapping("userInfo")
    public Body userInfo(){
        return jkUserService.queryUserInfo();
    }

    /**
     * 用户修改头像
     * @param headImg
     * @return
     */
    @RequestMapping("upHeadImg")
    public Body upHeadImg(String headImg){
        return jkUserService.upHeadImgInfo(headImg);
    }

    /**
     * 用户修改昵称
     * @param userName
     * @return
     */
    @RequestMapping("upUserName")
    public Body upUserName(String userName){
        return jkUserService.upUserNameInfo(userName);
    }

    /**
     * 用户提交意见反馈
     * @param title 标题
     * @param content 内容
     * @param phone 联系电话
     * @return
     */
    @RequestMapping("addFeedback")
    public Body addFeedback(
            String title,
            String content,
            String phone
    ){
        return jkUserService.addFeedbackInfo(title,content,phone);
    }

    /**
     * 用户收藏的商品
     * @return
     */
    @RequestMapping("collectGoods")
    public Body collectGoods(){
        return goodsCollectService.queryCollect();
    }

}
