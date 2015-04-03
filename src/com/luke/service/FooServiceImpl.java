package com.luke.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.luke.model.FooBean;

@Service
public class FooServiceImpl {
    
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
}