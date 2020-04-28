package com.ggh.controller;

import com.ggh.common.json.Body;
import com.ggh.service.LoginAndRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chaihu
 * @function 注册controller
 * @date 2020-04-17 16:35
 */
@CrossOrigin
@Controller
@ResponseBody
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private LoginAndRegisterService loginAndRegisterService;

    /**
     * 手机号注册
     * @param userPhone
     * @param code
     * @param userPwd
     * @return
     */
    @RequestMapping("phoneRegister")
    public Body phoneRegister(String userPhone, String code, String userPwd, Integer parentId){
        return loginAndRegisterService.registerJkUser(userPhone,code,userPwd,parentId);
    }

    /**
     * 微信绑定手机号
     * @param openId
     * @param userPhone
     * @param code
     * @param parentId
     * @return
     */
    @RequestMapping("wxRegister")
    public Body wxRegister(String openId,String userPhone,String code,Integer parentId){
        return loginAndRegisterService.wxRegisterJkUser(openId,userPhone,code,parentId);
    }

}
