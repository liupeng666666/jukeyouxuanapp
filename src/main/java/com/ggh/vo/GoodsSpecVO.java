package com.ggh.vo;

import com.ggh.entity.Spec;
import com.ggh.entity.SpecItem;
import lombok.Data;

import java.util.List;

/**
 * @author chaihu
 * @function 商品规格VO
 * @date 2020-04-22 11:11
 */
@Data
public class GoodsSpecVO {

    private Integer id;
    /**
     * 规格类型id
     */
    private Integer typeId;

    /**
     * 规格名称
     */
    private String name;

    /**
     * 所有规格
     */
    private List<SpecItem> specItems;

}
