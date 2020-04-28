package com.ggh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * @author chaihu
 * @function
 * @date 2020-04-17 11:05
 */
@Data
@TableName("mglt_spec_table")
public class Spec extends Model<Spec> {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 规格类型id
     */
    private Integer typeId;

    /**
     * 规格名称
     */
    private String name;


    @Override
    protected Serializable pkVal() {
        return id;
    }
}
