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
 * @Date: 2020/4/26 21:50
 * @Description:
 */
@Data
@TableName("mglt_group_join")
public class GroupJoin extends Model<GroupJoin> {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 参与者id
     */
    private String joinUserId;
    /**
     * 发起者id
     */
    private String userId;
    /**
     *发起团id
     */
    private Integer groupUserId;
    /**
     *开始时间
     */
    private Date createTime;
    /**
     *订单id
     */
    private Integer orderId;

    @Override
    protected Serializable pkVal() {
        return id;
    }

}
