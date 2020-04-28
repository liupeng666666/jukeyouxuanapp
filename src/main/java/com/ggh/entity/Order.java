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
 * @function 订单表
 * @date 2020-04-17 12:04
 */
@Data
@TableName("mglt_order_table")
public class Order extends Model<Order> {


    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 订单编号
     */
    private String orderSn;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 订单类型
     */
    private String promType;
    /**
     * 订单状态
     */
    private String orderStatus;
    /**
     * 发货状态
     */
    private String shippingStatus;
    /**
     * 支付状态
     */
    private String payStatus;
    /**
     * 收货人
     */
    private String consignee;
    /**
     * 收货地址
     */
    private String consigneeAdd;
    /**
     * 收货电话
     */
    private String consigneePhone;
    /**
     * 物流名称
     */
    private String shippingName;
    /**
     * 物流单号
     */
    private String shippingCode;
    /**
     * 支付方式
     */
    private String payName;
    /**
     * 商品总价
     */
    private Double goodsPrice;
    /**
     * 应付价格
     */
    private Double orderAmount;
    /**
     * 订单总价
     */
    private Double totalAmount;
    /**
     * 下单时间
     */
    private Date addTime;
    /**
     * 最新发货时间
     */
    private Date shippingTime;
    /**
     * 收货确认时间
     */
    private Date confirmTime;
    /**
     * 支付时间
     */
    private Date payTime;
    /**
     * 用户备注
     */
    private String userNote;
    /**
     * 订单所属员工id
     */
    private String  staffId;
    /**
     * 订单所属公司
     */
    private String officeId;
    /**
     * 逻辑删除
     */
    private String delFlag;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
