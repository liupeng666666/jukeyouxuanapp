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
 * @function 团购用户表
 * @date 2020-04-17 11:56
 */
@Data
@TableName("mglt_group_user_table")
public class GroupUser2 extends Model<GroupUser2> {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 拼团信息表id
     */
    private Integer groupInfoId;
    /**
     * 拼团发起人id
     */
    private Integer captainId;
    /**
     * 拼团发起人
     */
    private String captainName;
    /**
     * 参团用户id
     */
    private Integer userId;
    /**
     * 参团用户名
     */
    private String userName;
    /**
     * 参团时间
     */
    private Date addTime;

    /**
     * 订单id
     */
    private Integer orderId;
    /**
     * 订单编号
     */
    private String orderSn;
    /**
     * 参团状态
     */
    private Integer state;


    @Override
    protected Serializable pkVal() {
        return null;
    }
}
