package com.lzt.ylu.community.mapper;

import com.lzt.ylu.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    @Insert("insert into user_question (title, description, gmt_create, gmt_modify, create_userId, comment_count, view_count, like_count, tag) values (#{title}, #{description}, #{gmtCreate}, #{gmtModify}, #{createUserId}, #{commentCount}, #{viewCount}, #{likeCount}, #{tag})")
    void create(Question publish);

}
