package com.bigtree.aitest.controller;

import com.bigtree.aitest.domain.UserDomain;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author admin
 * @ClassName HomePageController
 * @description: TODO
 * @date 2023年08月11日
 * @version: 1.0
 */
@Controller
public class HomePageController {


    @GetMapping("/index")
    public String index(ModelMap modelMap) {
        // 返回数据
        List<UserDomain> userList = new ArrayList<>();
        userList.add(new UserDomain(100, "张三", "15178673344"));
        userList.add(new UserDomain(200, "李四", "15178273399"));
        userList.add(new UserDomain(300, "王五", "15178873357"));
        userList.add(new UserDomain(400, "赵六", "15178673356"));
        //设定视图名，也就是前端页面名称
        modelMap.addAttribute("users",userList);
        // 指定返回模板
        return "home";
    }

    @GetMapping("/chat")
    public String chat(ModelMap modelMap) {
        //设定视图名，也就是前端页面名称
        modelMap.addAttribute("demo","测试websocket!!");
        // 指定返回模板
        return "chat";
    }


}