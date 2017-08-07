package com.yang.controller;


import com.yang.exception.UserException;
import com.yang.model.User;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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
    public String add(User user ,MultipartFile attach,HttpServletRequest request) throws IOException{
        //获取upload文件夹真实路径
        String realpath = request.getSession().getServletContext().getRealPath("/resources/upload");
        //new一个目标文件
        File file = new File(realpath+"/"+attach.getOriginalFilename());
        //利用FileUtils将attach的输入流copy到文件中
        FileUtils.copyInputStreamToFile(attach.getInputStream(),file);
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

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "user/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(User user, HttpSession session){
        if (!users.containsKey(user.getUsername())){
            throw new UserException("用户名不存在！");
        }
        User u = users.get(user.getUsername());
        if (!u.getPassword().equals(user.getPassword())){
            throw new UserException("密码不正确");
        }
        session.setAttribute("loginUser",u);
        return "redirect:/user/users";
    }

    @RequestMapping(value = "/loginout",method = RequestMethod.GET)
    public String loginout(HttpSession session){
        session.invalidate();
        return "user/login";
    }

    //局部异常处理，只能处理当前Controller中的异常
    @ExceptionHandler(value = {UserException.class})
    public String handlerException(UserException e, HttpServletRequest httpServletRequest){
        httpServletRequest.setAttribute("e",e);
        return "user/error";
    }
}
