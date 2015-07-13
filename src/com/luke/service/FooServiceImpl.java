package com.luke.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.luke.annotation.Cache;
import com.luke.model.FooBean;
import com.luke.model.user.User;
import com.luke.persistence.mapper.UserMapper;

@Service
public class FooServiceImpl {
	
	private static final Logger logger = LoggerFactory
			.getLogger(FooServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * Convert Object to String that is formatted as CSV
	 * @param titles = "编号", "内容"\n;
	 * @param list
	 * @return
	 */
    public String toCSV(String titles, List<FooBean> list) {
        // title
        StringBuffer sb = new StringBuffer(titles);
        // content
        for(FooBean n : list) {
            sb.append(String.format("\"%d\",", n.getId()));
            sb.append(String.format("\"%s\"\n", n.getContent().replaceAll("\"", "\"\""))); // finish a line
        }
        return sb.toString();
    }
    
    public void getUpdateUserByReadOnly() {
    	userMapper.updatePasswordByName(new User("jack1", "111111"));
    }
    
    public void addUserWithExceptionInTransaction() {
    	// roll back this transaction
    	userMapper.addUser(new User("jack2", "111111"));
    	userMapper.updatePasswordByName(new User("jack2", "222222"));
		// Null exception arise
    	String error = null;
    	error.indexOf("null pointer exception is being thrown");
    }
    
    public void addUserInTransaction() {
    	// This transaction will be committed successfully
    	userMapper.addUser(new User("jack2", "111111"));
    	userMapper.updatePasswordByName(new User("jack2", "222222"));
    }
    
    public void xxxAddUserWithoutTransaction() {
    	// operate db is successful even if raising an exception follow it 
    	userMapper.addUser(new User("jack3", "111111"));
    	userMapper.updatePasswordByName(new User("jack3", "222222"));
		// Null exception arise
    	String error = null;
    	error.indexOf("null pointer exception is being thrown");
    }
    
    @Cache
    public void testCache(String str, Jedis... jedis) {
    	logger.info("your input:"+str);
		jedis[0].set("foo", str);
		logger.info("get value from Redis by key named 'foo':"+jedis[0].get("foo"));
		long n = jedis[0].del("foo");
		logger.info("delete key 'foo' in Redis, deleted number :"+n);
    }
}