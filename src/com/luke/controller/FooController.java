package com.luke.controller;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luke.model.FooBean;
import com.luke.model.fruit.Apple;
import com.luke.model.fruit.Fruit;
import com.luke.service.FooServiceImpl;

@Controller
@RequestMapping("/test/*")
public class FooController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    // 导入定义在properties中的属性
    @Value("#{configProperties['remote.ip']}")
    String remoteIP;
    
    @Value("#{configProperties['remote.port']}")
    Integer remotePort;
    
    @Autowired
    @Qualifier("orange") // inject instance which name is "orange" for fruit implement
    private Fruit fruit;
    
    @Autowired
    private Apple apple;
    
    @Autowired
    private FooServiceImpl fooService;
    
    @PostConstruct
    public void init() {
    	System.out.println("'@PostContrust' run once when this Bean's lifecycle start in spring container");
    }
    
    @PreDestroy
    public void destory() {
    	System.out.println("'@PreDestroy' run once when this Bean's lifecycle will destory in spring container");
    }
    
    /* A JSON API */
    @RequestMapping(value = "/json", method = RequestMethod.GET)
    @ResponseBody
    public FooBean json(@RequestParam(value="name", required=false, defaultValue="World") String content) {
    	FooBean foo = new FooBean(counter.incrementAndGet(),
                String.format(template, content));
    	System.out.println("foo id: "+foo.getId()+" content:"+foo.getContent());
        return foo;
    }

    /* A JSP SHOWING */
//    @RequestMapping(value = "/", method = RequestMethod.GET)
	@RequestMapping(value = "/jsp/{favourite}", method = RequestMethod.GET)
	public String jsp(@PathVariable String favourite, ModelMap model) {
		System.out.println("fruit-1 name: " + fruit.getName());
		System.out.println("apple-2 name: " + apple.getName());
		System.out.println("favourite: " + favourite);
		System.out.println(String.format("remote IP: %s, port: %s", remoteIP,
				remotePort));
		
		// model will be used in JSP View
		model.addAttribute("fruit", fruit);
		model.addAttribute("favourite", favourite);
		return "/sale/sale-fruit";
	}
    
	/* 302 Download */
    @RequestMapping(value = "/download302", method = RequestMethod.GET)
    public String downloadBy302() {
    	return "redirect:/test/fruit";
    }

    /* Download CSV */
    @RequestMapping(value = "/downloadCSV", method = RequestMethod.GET)
    public void downloadCSV(HttpServletRequest request, HttpServletResponse response) {
    	String titles = "\"编号\", \"内容\"\n";
        ArrayList<FooBean> contentList = new ArrayList<FooBean>();
        contentList.add(new FooBean(20, "测试内容1"));
        contentList.add(new FooBean(30, "测试内容,\"2"));
        response.setHeader("Content-Disposition", "attachment;filename=wo.csv");
        response.setHeader("Content-Type", "application/csv");
        try {
            response.getOutputStream().write(
                fooService.toCSV(titles, contentList).getBytes("utf-8"));
        } catch(Exception ex) {
            ex.printStackTrace();  
        }
    }

    @RequestMapping("/exit")
    public void exit() {
    	// @destory注解将被调用
    	System.exit(1);
    }
}
