package com.ggh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author chaihu
 * @function 商品类型表
 * @date 2020-04-17 11:11
 */
@Data
@TableName("mglt_goods_type_table")
public class GoodsType extends Model<GoodsType> {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 商品类型名
     */
    private String name;


    @Override
    protected Serializable pkVal() {
        return id;
    }
}
