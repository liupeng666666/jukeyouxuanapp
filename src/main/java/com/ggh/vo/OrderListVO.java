package com.ggh.vo;

import com.ggh.entity.Goods;
import lombok.Data;

import java.util.List;

/**
 * @author chaihu
 * @function 订单列表视图对象,用来处理订单列表数据展示
 * @date 2020-04-23 21:38
 */
@Data
public class OrderListVO {

    private List<OrderGoodsVO> orderGoodsVOS;

}
