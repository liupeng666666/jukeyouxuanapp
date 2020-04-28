package com.ggh.entity;

/**
 * @author chaihu
 * @function 规格库存表
 * @date 2020-04-17 11:07
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("mglt_spec_goods_table")
public class SpecGoods extends Model<SpecGoods> {

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
     * 规格键名
     */
    private String keyValue;
    /**
     * 规格键名对应中文
     */
    private String keyName;
    /**
     * 库存
     */
    private Integer StoreCount;
    /**
     * 逻辑删除
     */
    private String delFlag;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
