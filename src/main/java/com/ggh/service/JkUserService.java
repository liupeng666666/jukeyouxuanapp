package com.ggh.service;

import com.ggh.common.json.Body;

/**
 * @author chaihu
 * @function
 * @date 2020-04-23 20:00
 */
public interface JkUserService {
    Body queryUserInfo();

    Body upHeadImgInfo(String headImg);

    Body upUserNameInfo(String userName);

    Body addFeedbackInfo(String title, String content, String phone);
}
