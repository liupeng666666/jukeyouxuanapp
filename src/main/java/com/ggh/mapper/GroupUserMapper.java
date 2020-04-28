package com.ggh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ggh.entity.GroupUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Auther: Administrator
 * @Date: 2020/4/26 14:02
 * @Description:
 */
@Mapper
@Repository
public interface GroupUserMapper extends BaseMapper<GroupUser> {
}
