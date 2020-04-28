package com.ggh.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ggh.common.json.Body;
import com.ggh.entity.Goods;
import com.ggh.entity.Spec;
import com.ggh.entity.SpecGoods;
import com.ggh.entity.SpecItem;
import com.ggh.mapper.GoodsMapper;
import com.ggh.mapper.SpecGoodsMapper;
import com.ggh.mapper.SpecItemMapper;
import com.ggh.mapper.SpecMapper;
import com.ggh.service.GoodsService;
import com.ggh.vo.GoodsSpecVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chaihu
 * @function
 * @date 2020-04-20 12:06
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private SpecGoodsMapper specGoodsMapper;

    @Autowired
    private SpecItemMapper specItemMapper;

    @Autowired
    private SpecMapper specMapper;

    /**
     * 根据商品id查询商品详情
     * @param goodsId
     * @return
     */
    @Override
    public Body queryGoodsInfo(Integer goodsId) {
        // 根据商品id查询商品信息
        Goods goods = goodsMapper.selectById(goodsId);
        return Body.newInstance(goods);
    }

    /**
     * 根据商品id查询商品规格
     * @param goodsId
     * @return
     */
    @Override
    public Body queryGoodsSpec(Integer goodsId) {

        // 根据商品id查询商品规格
        List<SpecGoods> specGoods = specGoodsMapper.selectList(new LambdaQueryWrapper<SpecGoods>()
                .eq(SpecGoods::getGoodsId,goodsId)
                .eq(SpecGoods::getDelFlag,"0")
        );

        // 修改根据第一个规格查询所有尺码和颜色
        String item = specGoods.get(0).getKeyValue();
        String[] items = item.split(",");
        // String数组转int数组
        Integer[] ints = new Integer[items.length];
        for(int i=0;i<items.length;i++){
            ints[i] = Integer.parseInt(items[i]);
        }

        List<GoodsSpecVO> goodsSpecVOS = new ArrayList<>();
        for(int k = 0;k<ints.length;k++){
            // 查询
            SpecItem specItem = specItemMapper.selectById(ints[k]);
            // 查询规格名
            Spec spec =specMapper.selectById(specItem.getSpecId());
            // 查询所有item
            List<SpecItem> specItems =specItemMapper.selectList(new LambdaQueryWrapper<SpecItem>()
                    .eq(SpecItem::getSpecId,specItem.getSpecId())
                    .last("ORDER BY item")
            );
            GoodsSpecVO specVO = new GoodsSpecVO();
            specVO.setId(spec.getId());
            specVO.setName(spec.getName());
            specVO.setTypeId(spec.getTypeId());
            specVO.setSpecItems(specItems);
            goodsSpecVOS.add(specVO);
        }

        return Body.newInstance(goodsSpecVOS);
    }
}
