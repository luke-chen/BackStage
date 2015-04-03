package com.luke.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Spring 用例
 * 
 * @Component @Service 等默认都是 @Scope("singleton") 
 * 除非特别声明为 @Scope("prototype") 否则都是以单例方式注入
 */
@Controller
@RequestMapping("/test/*")
@Scope("singleton")
public class FooController {
	// LogBack 打印对象
	private static final Logger logger = LoggerFactory
			.getLogger(FooController.class);

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	// 导入定义在properties中的属性
	@Value("#{configProperties['remote.ip']}")
	String remoteIP;

	@Value("#{configProperties['remote.port']}")
	Integer remotePort;

	@Autowired
	@Qualifier("orange")
	// inject instance which name is "orange" for fruit implement
	private Fruit fruit;

	@Autowired
	private FooServiceImpl fooService;

	@PostConstruct
	public void init() {
		logger.info("'@PostContrust' run once when this Bean's lifecycle start in spring container");
	}

	@PreDestroy
	public void destory() {
		logger.info("'@PreDestroy' run once when this Bean's lifecycle will destory in spring container");
	}

	/* A JSON API */
	@RequestMapping(value = "/json", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<FooBean> json(
			@RequestParam(value = "name", required = false, defaultValue = "World") String content) {
		ArrayList<FooBean> list = new ArrayList<FooBean>();
		FooBean foo1 = new FooBean(counter.incrementAndGet(), String.format(
				template, content));
		FooBean foo2 = new FooBean(counter.incrementAndGet(), "天气预报");
		list.add(foo1);
		list.add(foo2);
		logger.info("foo id: " + foo1.getId() + " content:" + foo1.getContent());
		return list;
	}

	/* A JSP SHOWING */
	// @RequestMapping(value = "/", method = RequestMethod.GET)
	@RequestMapping(value = "/jsp/{favourite}", method = RequestMethod.GET)
	public String jsp(@PathVariable String favourite, ModelMap model) {
		Apple apple = new Apple();
		logger.info("fruit-1 name: " + fruit.getName());
		logger.info("apple-2 name: " + apple.getName());
		logger.info("favourite: " + favourite);
		logger.info(String.format("remote IP: %s, port: %s", remoteIP,
				remotePort));

		fruit.setMade("YanTai");

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
	public void downloadCSV(HttpServletRequest request,
			HttpServletResponse response) {
		String titles = "\"编号\", \"内容\"\n";
		ArrayList<FooBean> contentList = new ArrayList<FooBean>();
		contentList.add(new FooBean(20, "测试内容1"));
		contentList.add(new FooBean(30, "测试内容,\"2"));
		response.setHeader("Content-Disposition", "attachment;filename=wo.csv");
		response.setHeader("Content-Type", "application/csv");
		try {
			response.getOutputStream().write(
					fooService.toCSV(titles, contentList).getBytes("utf-8"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@RequestMapping("/exit")
	public void exit() {
		// @destory注解将被调用
		logger.warn("exit now...");
		System.exit(1);
	}

	@RequestMapping("/log")
	public void log() {
		logger.trace("======trace");
		logger.debug("======debug");
		logger.info("======info");
		logger.warn("======warn");
		logger.error("======error");
	}

	/**
	 * only return a black image which is 1x1 pix
	 * 
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/image/tiny", method = RequestMethod.GET)
	public void test(HttpServletResponse response) throws IOException {
		final byte[] whitespot = new byte[] { 71, 73, 70, 56, 57, 97, 1, 0, 1,
				0, -128, -1, 0, -1, -1, -1, 0, 0, 0, 44, 0, 0, 0, 0, 1, 0, 1,
				0, 0, 2, 2, 68, 1, 0, 59 };

		response.setContentType("image/gif");
		response.getOutputStream().write(whitespot);
		response.setContentLength(whitespot.length);
		response.setStatus(200);
		response.flushBuffer();
	}
}