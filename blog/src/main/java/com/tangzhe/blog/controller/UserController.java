package com.tangzhe.blog.controller;

import com.tangzhe.blog.entity.Authority;
import com.tangzhe.blog.entity.User;
import com.tangzhe.blog.service.AuthorityService;
import com.tangzhe.blog.service.UserService;
import com.tangzhe.blog.util.ConstraintViolationExceptionHandler;
import com.tangzhe.blog.vo.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 唐哲
 * 2018-04-22 23:39
 */
@RestController
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")  // 指定角色权限才能操作方法
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthorityService authorityService;

    /**
     * 查询所用用户
     */
    @GetMapping
    public ModelAndView list(@RequestParam(value="async", required=false) boolean async,
                             @RequestParam(value="pageIndex", required=false, defaultValue="0") int pageIndex,
                             @RequestParam(value="pageSize", required=false, defaultValue="10") int pageSize,
                             @RequestParam(value="name", required=false, defaultValue="") String name,
                             Model model) {

        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<User> page = userService.listUsersByNameLike(name, pageable);
        List<User> list = page.getContent();

        model.addAttribute("page", page);
        model.addAttribute("userList", list);
        return new ModelAndView(async==true?"users/list :: #mainContainerRepleace":"users/list", "userModel", model);
    }

    @GetMapping("/add")
    public ModelAndView createForm(Model model) {
        model.addAttribute("user", new User(null, null, null, null));
        return new ModelAndView("users/add", "userModel", model);
    }

    /**
     * 新建用户
     */
    @PostMapping
    public JsonResponse<User> create(User user, Long authorityId) {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authorityService.getAuthorityById(authorityId));
        user.setAuthorities(authorities);

        if(user.getId() == null) {
            user.setEncodePassword(user.getPassword()); // 加密密码
        }else {
            // 判断密码是否做了变更
            User originalUser = userService.getUserById(user.getId());
            String rawPassword = originalUser.getPassword();
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodePasswd = encoder.encode(user.getPassword());
            boolean isMatch = encoder.matches(rawPassword, encodePasswd);
            if (!isMatch) {
                user.setEncodePassword(user.getPassword());
            }else {
                user.setPassword(user.getPassword());
            }
        }

        try {
            userService.saveUser(user);
        }  catch (ConstraintViolationException e)  {
            return JsonResponse.fail(ConstraintViolationExceptionHandler.getMessage(e));
        }

        return JsonResponse.success(user, "处理成功");
    }

    /**
     * 删除用户
     */
    @DeleteMapping(value = "/{id}")
    public JsonResponse delete(@PathVariable("id") Long id, Model model) {
        try {
            userService.removeUser(id);
        } catch (Exception e) {
            return JsonResponse.fail(e.getMessage());
        }
        return JsonResponse.success("处理成功");
    }

    /**
     * 获取修改用户的界面及数据
     */
    @GetMapping(value = "edit/{id}")
    public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return new ModelAndView("users/edit", "userModel", model);
    }

}
