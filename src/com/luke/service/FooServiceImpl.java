package com.luke.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luke.model.User;
import com.luke.persistence.mapper.UserMapper;

@Service
public class FooServiceImpl {

    @Autowired
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
      this.userMapper = userMapper;
    }

    public User doSomeBusinessStuff(String username) {
      return this.userMapper.getUser(username);
    }
    
    public List<User> getAll() {
       return  this.userMapper.getAll();
    }
    
    public void addUser(User user) throws Exception {
        this.userMapper.add(user);
    }
}