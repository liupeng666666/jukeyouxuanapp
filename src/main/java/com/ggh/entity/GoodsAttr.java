package com.ggh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * @author chaihu
 * @function 商品属性对应表
 * @date 2020-04-17 11:12
 */
@Data
@TableName("mglt_goods_attr_table")
public class GoodsAttr extends Model<GoodsAttr> {


    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 商品id
     */
    private Integer goodsId;
    /**
     * 商品属性值id
     */
    private Integer attrId;

    /**
     * 商品属性值
     */
    private String attrValue;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
