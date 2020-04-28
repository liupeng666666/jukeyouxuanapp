package com.ggh.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import com.ggh.common.json.Body;
import com.ggh.entity.Feedback;
import com.ggh.entity.JkUser;
import com.ggh.mapper.JkUserMapper;
import com.ggh.service.JkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author chaihu
 * @function
 * @date 2020-04-23 20:00
 */
@Service
public class JkUserServiceImpl implements JkUserService {

    @Autowired
    private JkUserMapper jkUserMapper;

    /**
     * 查询用户信息
     * @return
     */
    @Override
    public Body queryUserInfo() {
        // 获取用户id
        Integer userId = StpUtil.getLoginId(-1);
        if(userId == -1){
            return Body.newInstance(500,"用户未登录");
        }
        // 根据用户id查询用户信息
        JkUser jkUser =jkUserMapper.selectById(userId);
        return Body.newInstance(jkUser);
    }

    /**
     * 用户修改头像
     * @param headImg
     * @return
     */
    @Override
    public Body upHeadImgInfo(String headImg) {
        // 获取用户id
        Integer userId = StpUtil.getLoginId(-1);
        if(userId == -1){
            return Body.newInstance(500,"用户未登录");
        }
        // 修改用户信息
        JkUser jkUser = new JkUser();
        jkUser.setId(userId);
        jkUser.setHeadImg(headImg);
        if(jkUser.updateById()){
            return Body.newInstance();
        }
        return Body.newInstance(500,"修改失败,请重试");
    }

    /**
     * 用户修改昵称
     * @param userName
     * @return
     */
    @Override
    public Body upUserNameInfo(String userName) {
        // 获取用户id
        Integer userId = StpUtil.getLoginId(-1);
        if(userId == -1){
            return Body.newInstance(500,"用户未登录");
        }
        // 修改用户信息
        JkUser jkUser = new JkUser();
        jkUser.setId(userId);
        jkUser.setName(userName);
        if(jkUser.updateById()){
            return Body.newInstance();
        }
        return Body.newInstance(500,"修改失败,请重试");
    }

    /**
     * 用户提交反馈
     * @param title
     * @param content
     * @param phone
     * @return
     */
    @Override
    public Body addFeedbackInfo(String title, String content, String phone) {
        // 获取用户id
        Integer userId = StpUtil.getLoginId(-1);
        if(userId == -1){
            return Body.newInstance(500,"用户未登录");
        }
        JkUser jkUser = jkUserMapper.selectById(userId);
        Feedback feedback = new Feedback();
        feedback.setUserId(userId);
        feedback.setUserName(jkUser.getName());
        feedback.setTitle(title);
        feedback.setContent(content);
        feedback.setPhone(phone);
        feedback.setCreateDate(new Date());
        feedback.setDelFlag("0");
        if(feedback.insert()){
            return Body.newInstance();
        }
        return Body.newInstance(500,"提交失败,请重试!");
    }
}
