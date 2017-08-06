package com.yang.controller;

import com.yang.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yang on 2017/8/4.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private Map<String,User> users = new HashMap<String, User>();

    public UserController(){
        users.put("zyp",new User("zyp","123","周云蓬","123@qq.com"));
        users.put("wxl",new User("wxl","456","万晓利","456@qq.com"));
        users.put("lz",new User("lz","789","李志","789@qq.com"));
    }

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("users",users);
        return "user/list";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(Model model){
        //开启ModelDirven
        model.addAttribute(new User());
        return "user/add";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(User user){
        users.put(user.getUsername(),user);
        return "redirect:/user/users";
    }

   @RequestMapping(value = "/{username}",method = RequestMethod.GET)
    public String show(@PathVariable String username,Model model){
        model.addAttribute(users.get(username));
        return "user/show";
    }

    @RequestMapping(value = "/{username}/update",method = RequestMethod.GET)
    public String update(@PathVariable String username,Model model){
        model.addAttribute(users.get(username));
        return "user/update";
    }

    @RequestMapping(value = "/{username}/update",method = RequestMethod.POST)
    public String update(User user){
        users.put(user.getUsername(),user);
        return "redirect:/user/users";
    }

    @RequestMapping(value = "/{username}/delete",method = RequestMethod.GET)
    public String delete(User user){
        users.remove(user.getUsername());
        return "redirect:/user/users";
    }
}
