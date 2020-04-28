package com.ggh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * @author chaihu
 * @function 商品属性表
 * @date 2020-04-17 11:14
 */
@Data
@TableName("mglt_attribute_table")
public class Attribute extends Model<Attribute> {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 属性名
     */
    private String attrName;
    /**
     * 属性分类id
     */
    private Integer typeId;
    /**
     * 属性可选值列表
     */
    private String attrValue;


    @Override
    protected Serializable pkVal() {
        return id;
    }
}
