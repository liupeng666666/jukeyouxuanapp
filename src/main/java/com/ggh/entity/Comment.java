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
 * @function 评论表
 * @date 2020-04-20 14:20
 */
@Data
@TableName("mglt_comment_table")
public class Comment extends Model<Comment> {
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
     * 订单id
     */
    private Integer orderId;

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 整体评价
     */
    private Integer globalComment;
    /**
     * 评价内容
     */
    private String content;
    /**
     * 评价图片
     */
    private String images;
    /**
     * 物流评价
     */
    private Integer logisticComment;

    /**
     * 描述相符
     */
    private Integer accordComment;

    /**
     * 服务态度
     */
    private Integer serviceComment;
    /**
     * 是否匿名
     */
    private Integer isAnonym;
    /**
     * 创建时间
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
