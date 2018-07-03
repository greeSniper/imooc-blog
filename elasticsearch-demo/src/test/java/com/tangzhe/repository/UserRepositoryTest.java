package com.tangzhe.repository;

import com.tangzhe.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

/**
 * Created by 唐哲
 * 2018-04-18 21:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findOneTest() {
        Optional<User> optional = userRepository.findById(1L);
        User user = optional.get();
        Assert.assertEquals(user.getPassword(), "123456");
        System.out.println(user);
    }

    @Test
    public void saveTest() {
        User user = userRepository.save(new User("张三", "666666"));
        Assert.assertNotNull(user);
        System.out.println(user);
    }

    @Test
    public void findAllTest() {
        List<User> users = userRepository.findAll();
        Assert.assertEquals(users.size(), 3);
        for (User user : users) {
            System.out.println(user);
        }
    }
    
}
