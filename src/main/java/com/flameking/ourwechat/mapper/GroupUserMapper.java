package com.flameking.ourwechat.mapper;

import com.flameking.ourwechat.entity.GroupUser;
import com.flameking.ourwechat.entity.GroupUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupUserMapper {
    int countByExample(GroupUserExample example);

    int deleteByExample(GroupUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GroupUser record);

    int insertSelective(GroupUser record);

    List<GroupUser> selectByExample(GroupUserExample example);

    GroupUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GroupUser record, @Param("example") GroupUserExample example);

    int updateByExample(@Param("record") GroupUser record, @Param("example") GroupUserExample example);

    int updateByPrimaryKeySelective(GroupUser record);

    int updateByPrimaryKey(GroupUser record);
}