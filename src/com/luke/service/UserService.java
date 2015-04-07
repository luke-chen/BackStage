package com.luke.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.luke.model.status.ActionStatus;
import com.luke.model.status.FailedActionStatus;
import com.luke.model.status.SuccessfulActionStatus;
import com.luke.model.user.Authority;
import com.luke.model.user.User;
import com.luke.persistence.mapper.UserMapper;

@Service
public class UserService {
    public static HashMap<String, Integer> ROLE_AUTH = new HashMap<String, Integer>();
    static {
        // admin authority
        ROLE_AUTH.put("ROLE_ADMIN", 0);
        // user authority
        ROLE_AUTH.put("ROLE_USER", 1);
    }
    
    @Autowired
    private UserMapper userMapper;

    public User getUser(String username) {
        return userMapper.getUser(username);
    }

    public List<User> getAllUsers() {
        return userMapper.getAll();
    }
    
    public List<User> getUsersByRoleUser() {
        return userMapper.getUsersByRoleUser();
    }

    public void addUser(User user) {
        userMapper.addUser(user);
    }
    
    public int updatePasswordByName(User user) {
        return userMapper.updatePasswordByName(user);
    }

    public int removeUser(String username) {
        return userMapper.removeUser(username);
    }
    
    public void addAuthority(Authority authority) {
        userMapper.addAuth(authority);
    }
    
    public int removeAuthority(String username) {
        return userMapper.removeAuthority(username);
    }
    
    public ActionStatus addUserAndAuthority(User user, String authority) {
        if(!ROLE_AUTH.containsKey(authority))
            return new FailedActionStatus("该权限不存在");
        addUser(user);
        Authority auth = new Authority();
        auth.setUsername(user.getUsername());
        auth.setAuthority(authority);
        addAuthority(auth);
        return new SuccessfulActionStatus();
    }
    
    public ActionStatus deleteUserAndAuthority(String username) {
        return removeAuthority(username) > 0 && removeUser(username) > 0 ? new SuccessfulActionStatus()
                : new FailedActionStatus("该用户不存在");
    }
    
    public ActionStatus updateUserAndAuthorityByName(User user, String authority) {
        if(!ROLE_AUTH.containsKey(authority))
            return new FailedActionStatus("该权限不存在");
        if(updatePasswordByName(user) <= 0)
            return new FailedActionStatus("该用户不存在");
        removeAuthority(user.getUsername());
        Authority auth = new Authority();
        auth.setUsername(user.getUsername());
        auth.setAuthority(authority);
        addAuthority(auth);
        return new SuccessfulActionStatus();
    }
}
