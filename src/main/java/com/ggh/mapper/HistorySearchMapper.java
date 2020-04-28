package com.ggh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ggh.entity.HistorySearch;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author chaihu
 * @function
 * @date 2020-04-17 13:46
 */
@Mapper
@Repository
public interface HistorySearchMapper extends BaseMapper<HistorySearch> {
}
