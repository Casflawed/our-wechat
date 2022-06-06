package com.flameking.ourwechat.mapper;

import com.flameking.ourwechat.entity.Friend;
import com.flameking.ourwechat.entity.FriendExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FriendMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend
     *
     * @mbggenerated Mon Jun 06 22:01:55 CST 2022
     */
    int countByExample(FriendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend
     *
     * @mbggenerated Mon Jun 06 22:01:55 CST 2022
     */
    int deleteByExample(FriendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend
     *
     * @mbggenerated Mon Jun 06 22:01:55 CST 2022
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend
     *
     * @mbggenerated Mon Jun 06 22:01:55 CST 2022
     */
    int insert(Friend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend
     *
     * @mbggenerated Mon Jun 06 22:01:55 CST 2022
     */
    int insertSelective(Friend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend
     *
     * @mbggenerated Mon Jun 06 22:01:55 CST 2022
     */
    List<Friend> selectByExample(FriendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend
     *
     * @mbggenerated Mon Jun 06 22:01:55 CST 2022
     */
    Friend selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend
     *
     * @mbggenerated Mon Jun 06 22:01:55 CST 2022
     */
    int updateByExampleSelective(@Param("record") Friend record, @Param("example") FriendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend
     *
     * @mbggenerated Mon Jun 06 22:01:55 CST 2022
     */
    int updateByExample(@Param("record") Friend record, @Param("example") FriendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend
     *
     * @mbggenerated Mon Jun 06 22:01:55 CST 2022
     */
    int updateByPrimaryKeySelective(Friend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend
     *
     * @mbggenerated Mon Jun 06 22:01:55 CST 2022
     */
    int updateByPrimaryKey(Friend record);
}