package com.forawhile.blog.service;

import com.forawhile.blog.dao.UserRepository;
import com.forawhile.blog.po.User;
import com.forawhile.blog.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {

        User user = userRepository.findByUsernameAndPassword(username, MD5Util.code(password));
        return user;
    }

    @Override
    public User saveUser(User user) {
        return null;
    }
}
