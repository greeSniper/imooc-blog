package com.tangzhe.thymeleaf.dao;

import com.tangzhe.thymeleaf.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by 唐哲
 * 2018-04-13 16:01
 */
@Repository
public class UserDaoImpl implements UserDao {

    private final ConcurrentMap<Long, User> USERS_MAP = new ConcurrentHashMap<>();
    private static AtomicLong idCounter = new AtomicLong();

    public UserDaoImpl() {
        User user = new User("张三", "888888@qq.com");
        this.saveOrUpdate(user);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(this.USERS_MAP.values());
    }

    @Override
    public User findOne(Long id) {
        return this.USERS_MAP.getOrDefault(id, new User("查无此用户", "XXX"));
    }

    @Override
    public void saveOrUpdate(User user) {
        if (user.getId() == null) {
            user.setId(idCounter.incrementAndGet());
        }
        this.USERS_MAP.put(user.getId(), user);
    }

    @Override
    public void delete(Long id) {
        this.USERS_MAP.remove(id);
    }

}
