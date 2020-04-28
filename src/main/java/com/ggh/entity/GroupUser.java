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
 * @Date: 2020/4/26 13:44
 * @Description:
 */
@Data
@TableName("mglt_group_user")
public class GroupUser extends Model<GroupUser> {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 发起者id
     */
    private String userId;
    /**
     * 拼团商品表id
     */
    private Integer ptGoodsId;
    /**
     * 团id
     */
    private String ptId;
    /**
     *开始时间
     */
    private Date startTime;
    /**
     *结束时间
     */
    private Date endTime;
    /**
     *拼团状态
     */
    private Integer state;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
