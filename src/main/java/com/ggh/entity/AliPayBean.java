package com.ggh.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p>IJPay 让支付触手可及，封装了微信支付、支付宝支付、银联支付常用的支付方式以及各种常用的接口。</p>
 *
 * <p>不依赖任何第三方 mvc 框架，仅仅作为工具使用简单快速完成支付模块的开发，可轻松嵌入到任何系统里。 </p>
 *
 * <p>IJPay 交流群: 723992875</p>
 *
 * <p>Node.js 版: https://gitee.com/javen205/TNW</p>
 *
 * <p>支付宝配置 Bean</p>
 *
 * @author Javen
 */
@Component
public class AliPayBean {

    /**
     * 应用编号
     */
    @Value("${aliPay.appId}")
    private String appId;

    /**
     * 应用私钥
     */
    @Value("${aliPay.privateKey}")
    private String privateKey;

    /**
     * 支付宝公钥，通过应用公钥上传到支付宝开放平台换取支付宝公钥(如果是证书模式，公钥与私钥在CSR目录)。
     */
    @Value("${aliPay.publicKey}")
    private String publicKey;

    /**
     * 应用公钥证书
     */
    @Value("${aliPay.appCertPath}")
    private String appCertPath;

    /**
     * 支付宝公钥证书
     */
    @Value("${aliPay.aliPayCertPath}")
    private String aliPayCertPath;

    /**
     * 支付宝根证书
     */
    @Value("${aliPay.aliPayRootCertPath}")
    private String aliPayRootCertPath;

    /**
     * 支付宝支付网关
     */
    @Value("${aliPay.serverUrl}")
    private String serverUrl;

    /**
     * 外网访问项目的域名，支付通知中会使用
     */
    @Value("${aliPay.domain}")
    private String domain;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getAppCertPath() {
        return appCertPath;
    }

    public void setAppCertPath(String appCertPath) {
        this.appCertPath = appCertPath;
    }

    public String getAliPayCertPath() {
        return aliPayCertPath;
    }

    public void setAliPayCertPath(String aliPayCertPath) {
        this.aliPayCertPath = aliPayCertPath;
    }

    public String getAliPayRootCertPath() {
        return aliPayRootCertPath;
    }

    public void setAliPayRootCertPath(String aliPayRootCertPath) {
        this.aliPayRootCertPath = aliPayRootCertPath;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }


    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return "AliPayBean{" +
                "appId='" + appId + '\'' +
                ", privateKey='" + privateKey + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", appCertPath='" + appCertPath + '\'' +
                ", aliPayCertPath='" + aliPayCertPath + '\'' +
                ", aliPayRootCertPath='" + aliPayRootCertPath + '\'' +
                ", serverUrl='" + serverUrl + '\'' +
                ", domain='" + domain + '\'' +
                '}';
    }
}