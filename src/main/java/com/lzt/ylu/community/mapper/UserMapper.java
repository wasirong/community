package com.lzt.ylu.community.mapper;

import com.lzt.ylu.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("insert into User (name, account_id, token, gmt_create, gmt_modify) values (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModify})")
    void insert(User user);
}
