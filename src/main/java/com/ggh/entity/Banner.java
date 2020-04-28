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
 * @date 2020-04-17 10:48
 */
@Data
@TableName("mglt_banner_table")
public class Banner extends Model<Banner> {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 轮播图标题
     */
    private String title;
    /**
     * 轮播图关联商品id
     */
    private Integer bannerDate;
    /**
     * 轮播图图片
     */
    private String bannerImg;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
