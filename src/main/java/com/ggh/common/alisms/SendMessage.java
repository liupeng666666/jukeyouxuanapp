package com.ggh.common.alisms;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @author ch
 * @version 1.0
 * @description 发送消息的工具类
 * @date 2019/6/25
 */
public abstract class SendMessage {

    private final static String REGION_ID = "cn-hangzhou";

    private final static String ACCESS_KEY_ID = "";

    private final static String ACCESS_KEY_SECRET = "";

    private final static String REGISTER_TEMPLATE = "";

    private final static String LOGIN_TEMPLATE = "";

    private final static String PASSWORD_TEMPLATE = "";

    private final static String WXREGISTER_TEMPLATE = "";



    private final static String OK = "\"Message\":\"OK\"";

    private static IAcsClient client = null;

    static {
        DefaultProfile profile = DefaultProfile.getProfile(REGION_ID, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        client = new DefaultAcsClient(profile);
    }

    /**
     * 发送消息
     * @param messageType 消息类型
     * @param signName 签名
     * @param phone 手机
     * @param content 模板内容
     * @return 是否发送成功
     */
    public static boolean send(MessageType messageType, String signName, String phone, String content) {
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        // 设置REGION_ID，固定
        request.putQueryParameter("REGION_ID", REGION_ID);
        // 设置签名，已审核通过
        request.putQueryParameter("SignName", signName);
        // 设置模板id，已审核通过
        if (messageType == MessageType.REGISTER) {
            request.putQueryParameter("TemplateCode", REGISTER_TEMPLATE);
        }else if(messageType == MessageType.LOGIN){
            request.putQueryParameter("TemplateCode", LOGIN_TEMPLATE);
        }else if(messageType == MessageType.PASSWORD){
            request.putQueryParameter("TemplateCode", PASSWORD_TEMPLATE);
        }else if(messageType == MessageType.WXREGISTER){
            request.putQueryParameter("TemplateCode", WXREGISTER_TEMPLATE);
        }
        // 设置电话号码
        request.putQueryParameter("PhoneNumbers", phone);
        // 设置模板内容
        String json = "{\"code\":\"" + content + "\"}";
        request.putQueryParameter("TemplateParam", json);

        // 发送消息
        try {
            CommonResponse response = client.getCommonResponse(request);
            String data = response.getData();
            if (data.contains(OK)) {
                return true;
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 发送消息, 默认使用签名
     * @param messageType
     * @param phone
     * @param content
     * @return
     */
    public static boolean send(MessageType messageType, String phone, String content) {
        return send(messageType, "聚客优选", phone, content);
    }
}
