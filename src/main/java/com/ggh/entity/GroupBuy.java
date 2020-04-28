package com.ggh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * @author chaihu
 * @function 团购表
 * @date 2020-04-17 11:34
 */
@Data
@TableName("mglt_group_buy_table")
public class GroupBuy extends Model<GroupBuy> {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 活动名称
     */
    private String title;
    /**
     * 拼团有效时间
     */
    private Integer validTime;
    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 团购价格
     */
    private Double price;

    /**
     * 商品参团数量
     */
    private Integer goodsNum;
    /**
     * 商品已经购买数量
     */
    private Integer buyNum;
    /**
     * 商品已下单人数
     */
    private Integer orderNum;

    /**
     * 虚拟购买数
     */
    private Integer virtualNum;



    @Override
    protected Serializable pkVal() {
        return id;
    }
}
