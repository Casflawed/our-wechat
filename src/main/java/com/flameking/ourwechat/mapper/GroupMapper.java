package com.flameking.ourwechat.mapper;

import com.flameking.ourwechat.entity.Group;
import com.flameking.ourwechat.entity.GroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group
     *
     * @mbggenerated Mon Jun 06 22:01:42 CST 2022
     */
    int countByExample(GroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group
     *
     * @mbggenerated Mon Jun 06 22:01:42 CST 2022
     */
    int deleteByExample(GroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group
     *
     * @mbggenerated Mon Jun 06 22:01:42 CST 2022
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group
     *
     * @mbggenerated Mon Jun 06 22:01:42 CST 2022
     */
    int insert(Group record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group
     *
     * @mbggenerated Mon Jun 06 22:01:42 CST 2022
     */
    int insertSelective(Group record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group
     *
     * @mbggenerated Mon Jun 06 22:01:42 CST 2022
     */
    List<Group> selectByExample(GroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group
     *
     * @mbggenerated Mon Jun 06 22:01:42 CST 2022
     */
    Group selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group
     *
     * @mbggenerated Mon Jun 06 22:01:42 CST 2022
     */
    int updateByExampleSelective(@Param("record") Group record, @Param("example") GroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group
     *
     * @mbggenerated Mon Jun 06 22:01:42 CST 2022
     */
    int updateByExample(@Param("record") Group record, @Param("example") GroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group
     *
     * @mbggenerated Mon Jun 06 22:01:42 CST 2022
     */
    int updateByPrimaryKeySelective(Group record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group
     *
     * @mbggenerated Mon Jun 06 22:01:42 CST 2022
     */
    int updateByPrimaryKey(Group record);
}