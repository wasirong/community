package com.lzt.ylu.community.mapper;

import com.lzt.ylu.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into User (name, account_id, token, gmt_create, gmt_modify) values (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModify})")
    void insert(User user);

    @Select("select * from User where token = #{token}")
    User findByToken(@Param("token") String token);
}
