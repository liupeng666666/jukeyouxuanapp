package com.ggh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * @author chaihu
 * @function 商品分类表
 * @date 2020-04-17 11:17
 */
@Data
@TableName("mglt_goods_category_table")
public class GoodsCategory extends Model<GoodsCategory> {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 分类名
     */
    private String name;
    /**
     * 分类图标
     */
    private String images;
    /**
     * 父级id
     */
    private String parentId;
    /**
     * 排序
     */
    private Integer sort;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
