package com.ggh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * @author chaihu
 * @function 订单商品表
 * @date 2020-04-17 13:34
 */
@Data
@TableName("mglt_order_goods_table")
public class OrderGoods extends Model<OrderGoods> {

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
     * 商品id
     */
    private Integer goodsId;

    /**
     * 商品数量
     */
    private Integer goodsNum;
    /**
     * 商品单价
     */
    private Double goodsPrice;
    /**
     * 商品规格key
     */
    private String specKey;
    /**
     * 规格对应中文名字
     */
    private String specKeyName;
    /**
     * 评论状态
     */
    private String commentStatus;
    /**
     * 逻辑删除
     */
    private String delFlag;


    @Override
    protected Serializable pkVal() {
        return id;
    }
}
