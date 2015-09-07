package com.luke.cms.annotation;


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

	@Around("@annotation(com.luke.cms.annotation.Cache)")
	public void redisAccess(ProceedingJoinPoint pjp) {
		System.out.println("before:"+jedisPool.getNumActive());
		Jedis cache = null;
		Object[] args = pjp.getArgs();
		try {
			for(int i=args.length-1; i>=0; i--) {
				if(args[i] instanceof Jedis[]) {
					cache = jedisPool.getResource();
					args[i] = new Jedis[]{cache};
					break;
				}
			}
			pjp.proceed(args);
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
