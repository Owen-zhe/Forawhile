package com.forawhile.blog.service;

import com.forawhile.blog.po.User;

public interface UserService {

    User checkUser(String username, String password);

    User saveUser(User user);
}
