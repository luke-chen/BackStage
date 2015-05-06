package com.luke.annotation;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Aspect
public class CacheAspect {
	@Autowired
	@Qualifier("myJedisPool")
	private JedisPool jedisPool;

	@Around("@annotation(com.luke.annotation.Cache)")
	public void accessRedis(ProceedingJoinPoint pjp) {
		System.out.println("before:"+jedisPool.getNumActive());
		Jedis cache = null;
		Object[] objs = pjp.getArgs();
		try {
			for(int i=objs.length-1; i>=0; i--) {
				if(objs[i] instanceof Jedis[]) {
					cache = jedisPool.getResource();
					objs[i] = new Jedis[]{cache};
					break;
				}
			}
			pjp.proceed(objs);
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			if(cache != null)
				jedisPool.returnResource(cache);
		}
		System.out.println("after:"+jedisPool.getNumActive());
	}
}
