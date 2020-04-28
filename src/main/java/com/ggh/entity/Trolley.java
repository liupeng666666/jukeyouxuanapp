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
 * @function 购物车表
 * @date 2020-04-17 11:20
 */
@Data
@TableName("mglt_trolley_table")
public class Trolley extends Model<Trolley> {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 商品id
     */
    private Integer goodsId;
    /**
     * 商品规格key,对应mglt_spec_goods_table
     */
    private String specKey;

    /**
     * 商品规格组合名称
     */
    private String specKeyName;

    /**
     * 购物车中商品数量
     */
    private Integer goodsNum;
    /**
     * 添加时间
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
