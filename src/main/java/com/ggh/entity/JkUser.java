package com.ggh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chaihu
 * @function 聚客用户信息
 * @date 2020-04-17 10:32
 */
@Data
@TableName("mglt_jk_user_table")
public class JkUser extends Model<JkUser> {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String name;
    /**
     * 用户头像
     */
    private String headImg;
    /**
     * 用户电话(账号)
     */
    private String userPhone;
    /**
     * 用户密码
     */
    private String userPwd;
    /**
     * 加密的盐
     */
    private String salt;
    /**
     * 用户余额
     */
    private Double userMoney;
    /**
     * 微信openId
     */
    private String openId;
    /**
     * 用户类型
     */
    private String userType;
    /**
     * 用户父级id
     */
    private Integer parentId;
    /**
     * 所属公司id
     */
    private String companyId;
    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 逻辑删除
     */
    private String delFlag;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
