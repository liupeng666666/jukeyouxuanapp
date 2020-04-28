package com.ggh.controller;

import com.ggh.common.json.Body;
import com.ggh.service.LoginAndRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chaihu
 * @function 登录controller
 * @date 2020-04-17 16:13
 */
@CrossOrigin
@Controller
@ResponseBody
@RequestMapping("login")
public class LoginInfoController {

    @Autowired
    private LoginAndRegisterService loginAndRegisterService;

    /**
     * 用户手机号密码登录
     * @param userPhone
     * @param userPwd
     * @return
     */
    @RequestMapping("phoneAndPwd")
    public Body phoneAndPwd(String userPhone, String userPwd){
        return loginAndRegisterService.loginForPhonePwd(userPhone,userPwd);
    }

    /**
     * 用户手机号验证码登录
     * @param userPhone
     * @param code
     * @return
     */
    @RequestMapping("phoneAndCode")
    public Body phoneAndCode(String userPhone,String code){
        return loginAndRegisterService.loginForPhoneCode(userPhone,code);
    }

    /**
     * 微信登录
     * @param openId
     * @return
     */
    @RequestMapping("wxLogin")
    public Body wxLogin(String openId){
        return loginAndRegisterService.loginForWx(openId);
    }

    /**
     * 用户修改密码接口
     * @param userPhone
     * @param newPwd
     * @param code
     * @return
     */
    @RequestMapping("updatePwd")
    public Body updatePwd(String userPhone,String newPwd,String code){
        return loginAndRegisterService.updatePwdForCode(userPhone,newPwd,code);
    }
}
