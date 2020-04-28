package com.ggh.vo;

import com.ggh.entity.Goods;
import com.ggh.entity.Trolley;
import lombok.Data;

/**
 * @author chaihu
 * @function
 * @date 2020-04-23 10:36
 */
@Data
public class TrolleyVO {
    /**
     * 商品
     */
    private Goods goods;
    /**
     * 购物车商品信息
     */
    private Trolley trolley;
}
