package com.luke.persistence.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.luke.model.User;

public interface UserMapper {
    @Select("SELECT * FROM user WHERE username = #{username}")
    User getUser(@Param("username") String username);
    
    @Select("SELECT * FROM user")
    List<User> getAll();
    
    @Insert("INSERT INTO user (username) values(#{username})")
    //@SelectKey(statement="call next value for TestSequence", keyProperty="nameId", before=true, resultType=int.class)
    void add(User user);
}