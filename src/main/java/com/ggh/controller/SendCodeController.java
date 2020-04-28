package com.ggh.controller;

import com.ggh.common.json.Body;
import com.ggh.service.SendCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chaihu
 * @function
 * @date 2020-04-17 17:07
 */
@CrossOrigin
@Controller
@ResponseBody
@RequestMapping("send")
public class SendCodeController {

    @Autowired
    private SendCodeService sendCodeService;


    /**
     * 发送验证码
     * @param userPhone
     * @return
     */
    @RequestMapping("code")
    public Body sendRegister(String userPhone, Integer type){
        return sendCodeService.doSendCode(userPhone,type);
    }
}
