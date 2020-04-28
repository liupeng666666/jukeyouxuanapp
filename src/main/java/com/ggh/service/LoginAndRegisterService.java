package com.ggh.service;


import com.ggh.common.json.Body;

/**
 * @author chaihu
 * @function
 * @date 2020-04-17 16:19
 */
public interface LoginAndRegisterService {
    Body loginForPhonePwd(String userPhone, String userPwd);

    Body registerJkUser(String userPhone, String code, String userPwd, Integer parentId);

    Body wxRegisterJkUser(String openId, String userPhone, String code, Integer parentId);

    Body loginForPhoneCode(String userPhone, String code);

    Body loginForWx(String openId);

    Body updatePwdForCode(String userPhone, String newPwd, String code);
}
