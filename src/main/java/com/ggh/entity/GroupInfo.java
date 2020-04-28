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
 * @function 团购信息表
 * @date 2020-04-17 11:46
 */
@Data
@TableName("mglt_group_info_table")
public class GroupInfo  extends Model<GroupInfo> {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 团购表主键id
     */
    private Integer groupBuyId;
    /**
     * 拼团发起人用户id
     */
    private Integer userId;
    /**
     * 拼团发起时间
     */
    private Date createTime;
    /**
     * 拼团失效时间
     */
    private Date stopTime;
    /**
     * 拼团状态
     */
    private Integer state;


    @Override
    protected Serializable pkVal() {
        return id;
    }
}
