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
 * @date 2020-04-17 10:52
 */
@Data
@TableName("mglt_goods_table")
public class Goods extends Model<Goods> {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 分类id
     */
    private Integer catId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品主图
     */
    private String headImg;
    /**
     * 商品轮播图
     */
    private String goodsBanner;
    /**
     * 商品单价
     */
    private Double goodsPrice;
    /**
     * 折扣价格
     */
    private Double goodsDiscount;

    /**
     * 商品搜索关键词
     */
    private String keywords;

    /**
     * 商品所属类型id
     */
    private Integer goodsType;
    /**
     * 商品规格类型
     */
    private Integer specType;
    /**
     * 平铺图
     */
    private String tileImg;
    /**
     * 面料图
     */
    private String textureImg;
    /**
     * 参数
     */
    private String goodsParam;
    /**
     * 所有尺码
     */
    private String goodsSize;
    /**
     * 商家时间
     */
    private String onTime;
    /**
     * 其他
     */
    private String restContent;
    /**
     * 是否是推荐商品
     */
    private Integer isRecommend;
    /**
     * 销量
     */
    private Integer salesSum;

    /**
     * 逻辑删除
     */
    private String delFlag;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
