package com.ggh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ggh.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author chaihu
 * @function
 * @date 2020-04-17 13:43
 */
@Mapper
@Repository
public interface GoodsMapper extends BaseMapper<Goods> {
}
