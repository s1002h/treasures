package com.ancao.treasures.mapper;

import com.ancao.treasures.bean.TreasuresUser;
import com.ancao.treasures.bean.TreasuresUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TreasuresUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table treasures_user
     *
     * @mbggenerated Thu Jan 25 14:29:36 CST 2018
     */
    int countByExample(TreasuresUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table treasures_user
     *
     * @mbggenerated Thu Jan 25 14:29:36 CST 2018
     */
    int deleteByExample(TreasuresUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table treasures_user
     *
     * @mbggenerated Thu Jan 25 14:29:36 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table treasures_user
     *
     * @mbggenerated Thu Jan 25 14:29:36 CST 2018
     */
    int insert(TreasuresUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table treasures_user
     *
     * @mbggenerated Thu Jan 25 14:29:36 CST 2018
     */
    int insertSelective(TreasuresUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table treasures_user
     *
     * @mbggenerated Thu Jan 25 14:29:36 CST 2018
     */
    List<TreasuresUser> selectByExample(TreasuresUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table treasures_user
     *
     * @mbggenerated Thu Jan 25 14:29:36 CST 2018
     */
    TreasuresUser selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table treasures_user
     *
     * @mbggenerated Thu Jan 25 14:29:36 CST 2018
     */
    int updateByExampleSelective(@Param("record") TreasuresUser record, @Param("example") TreasuresUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table treasures_user
     *
     * @mbggenerated Thu Jan 25 14:29:36 CST 2018
     */
    int updateByExample(@Param("record") TreasuresUser record, @Param("example") TreasuresUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table treasures_user
     *
     * @mbggenerated Thu Jan 25 14:29:36 CST 2018
     */
    int updateByPrimaryKeySelective(TreasuresUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table treasures_user
     *
     * @mbggenerated Thu Jan 25 14:29:36 CST 2018
     */
    int updateByPrimaryKey(TreasuresUser record);
}