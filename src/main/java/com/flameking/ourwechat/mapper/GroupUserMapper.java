package com.flameking.ourwechat.mapper;

import com.flameking.ourwechat.entity.GroupUser;
import com.flameking.ourwechat.entity.GroupUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_user
     *
     * @mbggenerated Mon Jun 06 22:01:30 CST 2022
     */
    int countByExample(GroupUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_user
     *
     * @mbggenerated Mon Jun 06 22:01:30 CST 2022
     */
    int deleteByExample(GroupUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_user
     *
     * @mbggenerated Mon Jun 06 22:01:30 CST 2022
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_user
     *
     * @mbggenerated Mon Jun 06 22:01:30 CST 2022
     */
    int insert(GroupUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_user
     *
     * @mbggenerated Mon Jun 06 22:01:30 CST 2022
     */
    int insertSelective(GroupUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_user
     *
     * @mbggenerated Mon Jun 06 22:01:30 CST 2022
     */
    List<GroupUser> selectByExample(GroupUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_user
     *
     * @mbggenerated Mon Jun 06 22:01:30 CST 2022
     */
    GroupUser selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_user
     *
     * @mbggenerated Mon Jun 06 22:01:30 CST 2022
     */
    int updateByExampleSelective(@Param("record") GroupUser record, @Param("example") GroupUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_user
     *
     * @mbggenerated Mon Jun 06 22:01:30 CST 2022
     */
    int updateByExample(@Param("record") GroupUser record, @Param("example") GroupUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_user
     *
     * @mbggenerated Mon Jun 06 22:01:30 CST 2022
     */
    int updateByPrimaryKeySelective(GroupUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_user
     *
     * @mbggenerated Mon Jun 06 22:01:30 CST 2022
     */
    int updateByPrimaryKey(GroupUser record);
}