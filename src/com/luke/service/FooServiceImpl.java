package com.luke.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.luke.annotation.Cache;
import com.luke.model.FooBean;
import com.luke.model.user.User;
import com.luke.persistence.mapper.UserMapper;

@Service
public class FooServiceImpl {
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * Convert Object to String formatted as CSV
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
    
    public void getTranscation() {
    	userMapper.updatePasswordByName(new User("jack", "11222"));
    }
    
    @Cache
    public void testCache(String str, Jedis... jedis) {
    	System.out.println(str);
		jedis[0].set("foo", "test foo");
		System.out.println("get 'foo' from redis :"+jedis[0].get("foo"));
		jedis[0].del("foo");
		System.out.println("delete 'foo' from redis :"+jedis[0].get("foo"));
    }
}