package com.ggh.vo;

import com.ggh.entity.Goods;
import com.ggh.entity.OrderGoods;
import lombok.Data;

/**
 * @author chaihu
 * @function 订单商品vo,用来处理订单列表数据展示(因为订单商品中没有存商品名和商品图片,需要一个vo对象存储)
 * @date 2020-04-23 21:42
 */
@Data
public class OrderGoodsVO {

    /**
     * 订单商品信息
     */
    private OrderGoods orderGoods;

    /**
     * 商品信息
     */
    private Goods goods;
}
