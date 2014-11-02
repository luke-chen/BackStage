package com.luke.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luke.model.FooBean;
import com.luke.model.user.User;
import com.luke.persistence.mapper.UserMapper;

@Service
public class FooServiceImpl {
    
    public String toCSV(List<FooBean> list) {
        // title
        StringBuffer sb = new StringBuffer("\"编号\", \"内容\"\n");
        // content
        for(FooBean n : list) {
            sb.append(String.format("\"%d\",", n.getId()));
            sb.append(String.format("\"%s\"\n", CSVFormat(n.getContent()))); // finish a line
        }
        return sb.toString();
    }

    private String CSVFormat(String item) {
        return item.replaceAll("\"", "\"\"");
    }
}