package com.ggh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * @author chaihu
 * @function 规格项表
 * @date 2020-04-18 10:08
 */
@Data
@TableName("mglt_spec_item_table")
public class SpecItem extends Model<SpecItem> {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 规格id
     */
    private Integer specId;
    /**
     * 规格项名
     */
    private String item;
    /**
     * 排序
     */
    private Integer orderIndex;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
