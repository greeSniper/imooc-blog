package com.tangzhe.thymeleaf.dao;

import com.tangzhe.thymeleaf.entity.User;

import java.util.List;

/**
 * Created by 唐哲
 * 2018-04-13 15:58
 */
public interface UserDao {

    /** 查询所有用户 */
    List<User> findAll();

    /**
     * 通过id查询用户
     * @param id 用户id
     * @return
     */
    User findOne(Long id);

    /**
     * 保存或修改用户
     * @param user 用户
     */
    void saveOrUpdate(User user);

    /**
     * 删除用户
     * @param id 用户id
     */
    void delete(Long id);

}
