package com.luke.persistence.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;




import com.luke.model.user.Authority;
import com.luke.model.user.User;

public interface UserMapper {
    @Select("SELECT * FROM users WHERE username = #{username}")
    User getUser(@Param("username") String username);
    
    @Select("SELECT * FROM users")
    List<User> getAll();
    
    @Insert("INSERT INTO users (username, password, enabled) VALUES (#{username}, #{password}, 1)")
    //@SelectKey(statement="call next value for TestSequence", keyProperty="nameId", before=true, resultType=int.class)
    void addUser(User user);
    
    @Insert("INSERT INTO authorities (username, authority) VALUES (#{username}, #{authority})")
    void addAuth(Authority authority);
    
    @Delete("DELETE FROM users WHERE username = #{username}")
    int removeUser(String username);
    
    @Delete("DELETE FROM authorities WHERE username = #{username}")
    int removeAuthority(String username);
    
    @Update("UPDATE users SET password = #{password} WHERE username = #{username}")
    int updateUserByName(User user);
}