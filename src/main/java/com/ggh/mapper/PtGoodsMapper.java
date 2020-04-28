package com.ggh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ggh.entity.PtGoods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Auther: Administrator
 * @Date: 2020/4/26 10:09
 * @Description:
 */
@Mapper
@Repository
public interface PtGoodsMapper extends BaseMapper<PtGoods> {
}
