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
 * @function 意见反馈
 * @date 2020-04-17 10:45
 */
@Data
@TableName("mglt_feedback_table")
public class Feedback extends Model<Feedback> {

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
     * 用户名
     */
    private String userName;
    /**
     * 意见反馈标题
     */
    private String title;
    /**
     * 反馈内容
     */
    private String content;
    /**
     * 联系电话
     */
    private String phone;

    /**
     * 提交时间
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
