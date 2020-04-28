package com.ggh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: Administrator
 * @Date: 2020/4/26 10:00
 * @Description:
 */
@Data
@TableName("mglt_ptgoods_table")
public class PtGoods extends Model<PtGoods> {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     *商品id
     */
    private Integer goodsId;
    /**
     *规格id
     */
    private String specId;
    /**
     *商品数量
     */
    private Integer number;
    /**
     *已购商品数量
     */
    private Integer buyNumber;
    /**
     *商品价格
     */
    private Double price;
    /**
     *拼团价格
     */
    private Double ptPrice;
    /**
     *拼团所需人数
     */
    private Integer ptNum;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
