package com.luke.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luke.model.status.ActionStatus;
import com.luke.model.status.FailedActionStatus;
import com.luke.model.user.User;
import com.luke.service.UserService;

@Controller
@RequestMapping("/user/*")
public class UserController {
    @Autowired
    private UserService userService;
   
	/* User 增删改查 */
    @RequestMapping(value = "/add")
    @ResponseBody
	public ActionStatus addUser(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "authority", required = true) String authority) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        try {
        	return userService.addUserAndAuthority(user, authority);
        } catch (Exception e) {
        	return new FailedActionStatus(e.toString());
        }
    }
    
    @RequestMapping(value = "/delete")
    @ResponseBody
    public ActionStatus deleteUser(@RequestParam(value = "username", required = true) String username) {
    	try {
	    	return userService.deleteUserAndAuthority(username);
    	} catch (Exception e) {
        	return new FailedActionStatus(e.toString());
        }
    }
    
    @RequestMapping(value = "/update")
    @ResponseBody
    public ActionStatus updateUser(
    		@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "authority", required = true) String authority) {
		try {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			return userService.updateUserAndAuthorityByName(user, authority);
		} catch (Exception e) {
			return new FailedActionStatus(e.toString());
		}
    }
    
    @RequestMapping(value = "/get")
    @ResponseBody
    public User getUser(@RequestParam(value = "username", required = true) String username) {
        return userService.getUser(username);
    }
    
    @RequestMapping(value = "/all")
    @ResponseBody
    public List<User> allUsers() {
        return userService.getAllUsers();
    }
    
    /* Spring Security */
    @RequestMapping(value = "/whoami")
    @ResponseBody
    public Collection<GrantedAuthority> whoAmI() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String str = authentication.getName();
        System.out.print("current user is "+str+" ROLE:");
        Collection<GrantedAuthority> authority = (Collection<GrantedAuthority>)authentication.getAuthorities();
        for(GrantedAuthority g : authority)
            System.out.print(" "+g.getAuthority());
        System.out.println();
        return authority;
    }
}
