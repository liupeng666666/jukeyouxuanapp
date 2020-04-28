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
 * @function 收藏表
 * @date 2020-04-17 11:19
 */
@Data
@TableName("mglt_goods_collect_table")
public class GoodsCollect  extends Model<GoodsCollect> {

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
     * 收藏时间
     */
    private Date addTime;
    /**
     * 逻辑删除
     */
    private String delFlag;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
