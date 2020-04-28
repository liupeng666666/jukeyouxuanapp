package com.ggh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ggh.entity.SpecItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author chaihu
 * @function
 * @date 2020-04-18 10:12
 */
@Mapper
@Repository
public interface SpecItemMapper extends BaseMapper<SpecItem> {
}
