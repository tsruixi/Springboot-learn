package com.example.community.learn.mapper;


import com.example.community.learn.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * @Author: ruixi
 * @Date: 2019/12/9 19:17
 */
@Mapper
@Repository
public interface UserMapper {
        @Insert("insert into user (name, account_id, token, gmt_create, gmt_modified) " +
                "values (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified})")
        void insert(User user);
}
