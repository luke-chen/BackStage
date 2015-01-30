package com.luke.controller;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luke.model.status.ActionStatus;
import com.luke.model.status.FailedActionStatus;
import com.luke.model.status.SuccessfulActionStatus;
import com.luke.model.user.User;
import com.luke.service.UserService;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
    @Autowired
    private UserService userService;
    
    Md5PasswordEncoder md5Encoder = new Md5PasswordEncoder();
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String showHomePage() {
        return "/main/index";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String showUserPage(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", authentication.getName());
        List<User> list;
        Collection<GrantedAuthority> authority = (Collection<GrantedAuthority>)authentication.getAuthorities();
        /* ROLE_ADMIN,返回全部用户 */
        for(GrantedAuthority g : authority) {
            if(g.getAuthority().equals("ROLE_ADMIN")) {
                //System.out.print(" "+g.getAuthority());
                list = userService.getAllUsers();
                model.addAttribute("users", list);
                return "/main/users";
            }
        }
        /* ROLE_ROLE,返回ROLE用户 */
        list = userService.getUsersByRoleUser();
        model.addAttribute("users", list);
        //System.out.println("current user is:" + authentication.getName());
        return "/main/users";
    }
   
	/* User 增删改查 */
    @RequestMapping(value = "/user/add")
    @ResponseBody
	public ActionStatus addUser(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "authority", required = true) String authority) {
        username = username.trim();
        password = password.trim();
        authority = authority.trim();
        User user = new User();
        user.setUsername(username);
        user.setPassword(md5Encoder.encodePassword(password, null));
        try {
        	return userService.addUserAndAuthority(user, authority);
        } catch (Exception e) {
        	return new FailedActionStatus(e.toString());
        }
    }
    
    @RequestMapping(value = "/user/delete")
    @ResponseBody
    public ActionStatus deleteUser(@RequestParam(value = "username", required = true) String username) {
    	try {
            username = username.trim();
	    	return userService.deleteUserAndAuthority(username);
    	} catch (Exception e) {
        	return new FailedActionStatus(e.toString());
        }
    }
    
    @RequestMapping(value = "/user/update")
    @ResponseBody
    public ActionStatus updateUser(
    		@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "authority", required = true) String authority) {
		try {
	        username = username.trim();
	        password = password.trim();
	        authority = authority.trim();
			User user = new User();
			user.setUsername(username);
			user.setPassword(md5Encoder.encodePassword(password, null));
			return userService.updateUserAndAuthorityByName(user, authority);
		} catch (Exception e) {
			return new FailedActionStatus(e.toString());
		}
    }

    @RequestMapping(value = "/user/changePassword")
    public void addUser(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password) {
        username = username.trim();
        password = password.trim();
        User user = new User();
        user.setUsername(username);
        user.setPassword(md5Encoder.encodePassword(password, null));
        userService.updateUserByName(user);
    }
    
    @RequestMapping(value = "/user/get")
    @ResponseBody
    public User getUser(@RequestParam(value = "username", required = true) String username) {
        username = username.trim();
        return userService.getUser(username);
    }
    
    @RequestMapping(value = "/user/all")
    @ResponseBody
    public List<User> allUsers() {
        return userService.getAllUsers();
    }
    
    /* Spring Security */
    @RequestMapping(value = "/user/whoami")
    @ResponseBody
    public Collection<GrantedAuthority> whoAmI() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String str = authentication.getName();
        logger.info("current user is "+str+" ROLE:");
        Collection<GrantedAuthority> authority = (Collection<GrantedAuthority>)authentication.getAuthorities();
        for(GrantedAuthority g : authority)
        	logger.info(" "+g.getAuthority());
        return authority;
    }
}