package com.ggh.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ggh.common.alisms.MessageType;
import com.ggh.common.alisms.SendMessage;
import com.ggh.common.json.Body;
import com.ggh.entity.JkUser;
import com.ggh.mapper.JkUserMapper;
import com.ggh.service.SendCodeService;
import com.ggh.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Function
 * @Author chaihu
 * @Date 2020/1/18 10:28
 * @Place 29
 * @Version 1.0.0
 * @Copyright GGH
 */
@Service
public class SendCodeServiceImpl implements SendCodeService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JkUserMapper jkUserMapper;

    /**
     * 发送验证码的方法
     * @param phone
     * @param type
     * @return
     */
    @Override
    public Body doSendCode(String phone, Integer type) {
        // 根据手机号查询用户信息
        JkUser jkUser = jkUserMapper.selectOne(new LambdaQueryWrapper<JkUser>().eq(JkUser::getUserPhone,phone));

        // 生成随机验证码
        Integer random =  (int)((Math.random()*9+1)*100000);
        String code = random + "";
        if(type == 1){
            // 验证手机号是否已经注册
           if(jkUser != null){
               return Body.newInstance(500,"该手机号已被使用！");
           }
            // 发送注册验证码
            SendMessage.send(MessageType.REGISTER,phone,code);
            redisUtil.set("re"+phone,code,300);
            return Body.newInstance();
        }else if(type == 2){
            // 验证手机号是否存在
           if(jkUser == null){
               return Body.newInstance(500,"该手机号未注册!");
           }
            // 发送登录验证码
            SendMessage.send(MessageType.LOGIN,phone,code);
            redisUtil.set("lg"+phone,code,300);
            return Body.newInstance();
        }else if(type == 3){
           if(jkUser == null){
               return Body.newInstance(500,"该手机号未注册");
           }
            // 修改密码
            SendMessage.send(MessageType.PASSWORD,phone,code);
            redisUtil.set("pwd"+phone,code,300);
            return Body.newInstance();
        }else if(type == 4){
            if(jkUser != null){
                return Body.newInstance(500,"该手机号已被使用");
            }
            // 发送第三方登录绑定手机号验证码
            SendMessage.send(MessageType.WXREGISTER,phone,code);
            redisUtil.set("wx"+phone,code,300);
            return Body.newInstance();
        }
        return Body.newInstance(500,"发送失败");
    }
}
