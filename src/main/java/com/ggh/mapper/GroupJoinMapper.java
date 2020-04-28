package com.ggh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ggh.entity.GroupJoin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Auther: Administrator
 * @Date: 2020/4/27 11:00
 * @Description:
 */
@Mapper
@Repository
public interface GroupJoinMapper extends BaseMapper<GroupJoin> {
}
