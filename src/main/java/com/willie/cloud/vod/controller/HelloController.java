package com.willie.cloud.vod.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/23 11:14</p>
 */
@Controller
public class HelloController {

    @RequestMapping(value = {"", "/"})
    public String index(Map map) {
        map.put("msg", "你大爷的");
        return "/index";
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "/test/hello";
    }
}
