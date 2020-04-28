package com.ggh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * @author chaihu
 * @function 订单消息
 * @date 2020-04-17 10:41
 */
@Data
@TableName("mglt_order_message_table")
public class OrderMessage  extends Model<OrderMessage> {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 订单id
     */
    private Integer orderId;
    /**
     * 订单编号
     */
    private String orderSn;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 订单消息标题
     */
    private String title;
    /**
     * 订单消息内容
     */
    private String content;






    @Override
    protected Serializable pkVal() {
        return id;
    }
}
