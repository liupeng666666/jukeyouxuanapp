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
 * @function
 * @date 2020-04-17 13:38
 */
@Data
@TableName("mglt_address_table")
public class Address extends Model<Address> {

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
     * 收货人
     */
    private String consigneeName;
    /**
     * 收货地址
     */
    private String consigneeAdd;
    /**
     * 收货联系电话
     */
    private String consigneePhone;
    /**
     * 是否是默认地址
     */
    private Integer isDefault;
    /**
     * 添加时间
     */
    private Date createDate;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
