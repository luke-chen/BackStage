package com.luke.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luke.model.status.ActionStatus;
import com.luke.model.status.FailedActionStatus;
import com.luke.model.status.SuccessfulActionStatus;
import com.luke.model.user.Authority;
import com.luke.model.user.User;
import com.luke.persistence.mapper.UserMapper;

@Service
public class UserService {
	public static HashMap<String, String[]> ROLE_AUTH = new HashMap<String, String[]>();
	static {
		// admin authority
		ROLE_AUTH.put("ROLE_ADMIN", new String[] { "ROLE_ADMIN", "ROLE_USER" });
		// user authority
		ROLE_AUTH.put("ROLE_USER", new String[] { "ROLE_USER" });
	}
	
	@Autowired
	private UserMapper userMapper;

	public User getUser(String username) {
		return userMapper.getUser(username);
	}

	public List<User> getAllUsers() {
		return userMapper.getAll();
	}

	public void addUser(User user) {
		userMapper.addUser(user);
	}

	public int updateUserByName(User user) {
		return userMapper.updateUserByName(user);
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
			return new FailedActionStatus("权限不存在");
		addUser(user);
		String[] roleList = ROLE_AUTH.get(authority);
		for(String role : roleList) {
			Authority auth = new Authority();
			auth.setUsername(user.getUsername());
			auth.setAuthority(role);
			addAuthority(auth);
		}
		return new SuccessfulActionStatus();
	}
	
	public ActionStatus deleteUserAndAuthority(String username) {
		return removeAuthority(username) > 0 && removeUser(username) > 0 ? new SuccessfulActionStatus()
				: new FailedActionStatus("该用户不存在");
	}
	
	public ActionStatus updateUserAndAuthorityByName(User user, String authority) {
		if(!ROLE_AUTH.containsKey(authority))
			return new FailedActionStatus("权限不存在");
		if(updateUserByName(user) <= 0)
			return new FailedActionStatus("该用户不存在");
		removeAuthority(user.getUsername());
		String[] roleList = ROLE_AUTH.get(authority);
		for(String role : roleList) {
			Authority auth = new Authority();
			auth.setUsername(user.getUsername());
			auth.setAuthority(role);
			addAuthority(auth);
		}
		return new SuccessfulActionStatus();
	}
}
