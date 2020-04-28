package com.ggh.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ggh.common.json.Body;
import com.ggh.entity.JkUser;
import com.ggh.mapper.JkUserMapper;
import com.ggh.service.LoginAndRegisterService;
import com.ggh.utils.Hutool;
import com.ggh.utils.MD5Util;
import com.ggh.utils.NameUtil;
import com.ggh.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author chaihu
 * @function
 * @date 2020-04-17 16:19
 */
@Service
public class LoginAndRegisterServiceImpl implements LoginAndRegisterService {

    @Autowired
    private JkUserMapper jkUserMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 手机号验证码注册
     * @param userPhone
     * @param code
     * @param userPwd
     * @return
     */
    @Override
    public Body registerJkUser(String userPhone, String code, String userPwd,Integer parentId) {
        // 检查手机号有没有被注册
        JkUser user = jkUserMapper.selectOne(new LambdaQueryWrapper<JkUser>().eq(JkUser::getUserPhone,userPhone));
        if(user!=null){
            return Body.newInstance(500,"该手机号已被使用!");
        }

        // 判断验证码是否正确
//        String redisCode = (String)redisUtil.get("re"+userPhone);
        if(!code.equals("123456")){
            return Body.newInstance(500,"验证码错误");
        }

        // 进行注册
        JkUser newUser = new JkUser();

        newUser.setName(NameUtil.getStringRandom(6));
        newUser.setHeadImg("http://192.168.1.68:8081/images/userfiles/logo.png");
        newUser.setUserPhone(userPhone);
        newUser.setSalt(Hutool.getId());
        newUser.setUserPwd(MD5Util.passwordEncryption(userPwd,newUser.getSalt()));
        newUser.setUserMoney(0.0);
        newUser.setCreateDate(new Date());
        newUser.setDelFlag("0");
        if(parentId != null){
            newUser.setParentId(parentId);
            // 查询父级用户的公司
            newUser.setCompanyId("aaaaaaaa");
        }
        if(newUser.insert()){
            return Body.newInstance();
        }

        return Body.newInstance(500,"注册失败");
    }

    /**
     * 微信绑定手机号
     * @param openId
     * @param userPhone
     * @param userPwd
     * @param code
     * @return
     */
    @Override
    public Body wxRegisterJkUser(String openId, String userPhone, String code,Integer parentId) {
        // 检查手机号有没有被注册
        JkUser user = jkUserMapper.selectOne(new LambdaQueryWrapper<JkUser>().eq(JkUser::getUserPhone,userPhone));
        if(user!=null){
            return Body.newInstance(500,"该手机号已被使用!");
        }
        // 判断验证码是否正确
//        String redisCode = (String)redisUtil.get("wx"+userPhone);

        if(!code.equals("123456")){
            return Body.newInstance(500,"验证码错误");
        }
        // 进行注册
        JkUser newUser = new JkUser();

        newUser.setName(NameUtil.getStringRandom(6));
        newUser.setHeadImg("http://192.168.1.68:8081/images/userfiles/logo.png");
        newUser.setUserPhone(userPhone);
        newUser.setOpenId(openId);
        newUser.setUserMoney(0.0);
        newUser.setCreateDate(new Date());
        newUser.setDelFlag("0");
        if(parentId != null){
            newUser.setParentId(parentId);
            // 查询父级用户的公司
            newUser.setCompanyId("aaaaaaaa");
        }
        if(newUser.insert()){
            return Body.newInstance();
        }
        return Body.newInstance(500,"绑定失败,请重试");
    }

    /**
     * 用户手机号验证码登录
     * @param userPhone
     * @param code
     * @return
     */
    @Override
    public Body loginForPhoneCode(String userPhone, String code) {
        JkUser user = jkUserMapper.selectOne(new LambdaQueryWrapper<JkUser>().eq(JkUser::getUserPhone,userPhone));
        if(user == null){
            return Body.newInstance(500,"该手机号未注册,请先去注册");
        }
        // 判断验证码
        String redis = (String)redisUtil.get("lg"+userPhone);
        if(code.equals("123456")){
            StpUtil.setLoginId(user.getId());
            return Body.newInstance();
        }
        return Body.newInstance(500,"验证码错误");
    }

    /**
     * 用户微信登录
     * @param openId
     * @return
     */
    @Override
    public Body loginForWx(String openId) {
        JkUser user = jkUserMapper.selectOne(new LambdaQueryWrapper<JkUser>().eq(JkUser::getOpenId,openId));
        if(user == null){
            return Body.newInstance(500,"该微信号未注册,跳转绑定手机号页面");
        }
        if(openId.equals(user.getOpenId())){
            StpUtil.setLoginId(user.getId());
            return Body.newInstance();
        }
        return Body.newInstance(500,"登录失败,请重试");
    }

    @Override
    public Body updatePwdForCode(String userPhone, String newPwd, String code) {
        JkUser user = jkUserMapper.selectOne(new LambdaQueryWrapper<JkUser>().eq(JkUser::getUserPhone,userPhone));
        if(user == null){
            return Body.newInstance(500,"该手机号未注册");
        }
        // 判断验证码
        String redis = (String)redisUtil.get("pwd"+userPhone);
        if(code.equals("123456")){
            // 验证码正确,修改密码
            user.setSalt(Hutool.getId());
            user.setUserPwd(MD5Util.passwordEncryption(newPwd,user.getSalt()));
            if(user.updateById()){
                return Body.newInstance();
            }
            return Body.newInstance(500,"修改失败,请重试");
        }

        return Body.newInstance(500,"验证码错误");
    }

    /**
     * 用户手机号密码登录
     * @param userPhone
     * @param userPwd
     * @return
     */
    @Override
    public Body loginForPhonePwd(String userPhone, String userPwd) {
        JkUser user = jkUserMapper.selectOne(new LambdaQueryWrapper<JkUser>().eq(JkUser::getUserPhone,userPhone));
        if(user == null){
            return Body.newInstance(500,"该手机号未注册,请先去注册");
        }
        String pwd = MD5Util.passwordEncryption(userPwd,user.getSalt());

        if(pwd.equals(user.getUserPwd())){
            StpUtil.setLoginId(user.getId());
            return Body.newInstance();
        }
        return Body.newInstance(500,"密码错误");
    }


}
