package com.yang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Yang on 2017/8/4.
 */
@Controller
public class HelloController {

    //表示对应url
    @RequestMapping("/hello")
    public String hello(String username, Model model){
        System.out.print("hello "+username);
        model.addAttribute("username",username);
        return "hello";
    }


}
