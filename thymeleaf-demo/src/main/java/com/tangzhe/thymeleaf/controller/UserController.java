package com.tangzhe.thymeleaf.controller;

import com.tangzhe.thymeleaf.dao.UserDao;
import com.tangzhe.thymeleaf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 唐哲
 * 2018-04-13 16:15
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    /**
     * 获取所有用户
     */
    @GetMapping("/list")
    public ModelAndView list(Model model) {
        model.addAttribute("userList", userDao.findAll());
        model.addAttribute("title", "用户管理");
        return new ModelAndView("users/list", "userModel", model);
    }

    /**
     * 进入form表单页面
     */
    @GetMapping("/form")
    public ModelAndView form(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "创建用户");
        return new ModelAndView("users/form", "userModel", model);
    }

    /**
     * 保存用户
     */
    @PostMapping
    public ModelAndView saveOrUpdate(User user) {
        userDao.saveOrUpdate(user);
        return new ModelAndView("redirect:/user/list");
    }

    /**
     * 查看用户
     */
    @GetMapping("/{id}")
    public ModelAndView view(@PathVariable("id") Long id, Model model) {
        model.addAttribute("title", "查看用户");
        model.addAttribute("user", userDao.findOne(id));
        return new ModelAndView("users/view", "userModel", model);
    }

    /**
     * 进入修改用户页面
     */
    @GetMapping("/modify/{id}")
    public ModelAndView modify(@PathVariable("id") Long id, Model model) {
        model.addAttribute("title", "修改用户");
        model.addAttribute("user", userDao.findOne(id));
        return new ModelAndView("users/form", "userModel", model);
    }

    /**
     * 删除用户
     */
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        userDao.delete(id);
        return new ModelAndView("redirect:/user/list");
    }

}
