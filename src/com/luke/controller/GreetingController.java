package com.luke.controller;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luke.model.Greeting;
import com.luke.model.fruit.Apple;
import com.luke.model.fruit.Fruit;

@Controller
@RequestMapping("/test/*")
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    // 导入定义在properties中的属性
    @Value("#{configProperties['remote.ip']}")
    String remoteIP;
    
    @Value("#{configProperties['remote.port']}")
    Integer remotePort;
    
    @Autowired
    @Qualifier("orange") // inject instance named "orange" for fruit implement
    private Fruit fruit;
    
    @Autowired
    private Apple apple;
    
    @PostConstruct
    public void init() {
    	System.out.println("@PostContrust run once when this Bean's lifecycle start in spring container");
    }
    
    @PreDestroy
    public void destory() {
    	System.out.println("@PreDestroy run once when this Bean's lifecycle will destory in spring container");
    }
    
    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    @ResponseBody
    public Greeting greeting(@RequestParam(value="name", required=false, defaultValue="World") String name) {
    	System.out.println("name:"+name);
    	Greeting greet = new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    	System.out.println("greet id: "+greet.getId()+" data:"+greet.getContent());
        return greet;
    }
    
//    @RequestMapping(value = "/", method = RequestMethod.GET)
    @RequestMapping(value = "/fruit", method = RequestMethod.GET)
    public String showJSP(Map<String, Object> model) {
    	System.out.println("fruit-1 name: "+fruit.getName());
    	System.out.println("apple-2 name: "+apple.getName());
    	System.out.println(String.format("remote IP: %s, port: %s", remoteIP, remotePort));
    	
//    	model will be used in JSP View
    	model.put("fruit", fruit);
    	return "/sale/sale-fruit";
    }
    
    @RequestMapping("/exit")
    public void exit() {
    	// 调用@destory
    	System.exit(1);
    }
}
