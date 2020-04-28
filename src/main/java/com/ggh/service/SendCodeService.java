package com.ggh.service;


import com.ggh.common.json.Body;

/**
 * @Function
 * @Author chaihu
 * @Date 2020/1/18 10:28
 * @Place 29
 * @Version 1.0.0
 * @Copyright GGH
 */
public interface SendCodeService {
    /**
     * 发送验证码
     * @param phone 手机号
     * @param type  验证码类型 1：注册，2：登录,3修改密码，4第三方登录绑定手机号
     * @return body
     */
    Body doSendCode(String phone, Integer type);
}
