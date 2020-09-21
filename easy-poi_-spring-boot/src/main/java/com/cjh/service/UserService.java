package com.cjh.service;

import com.cjh.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: huahua
 * @Date: 2020-09-15 10:21
 */
public interface UserService {
    List<User> findAll();
    void saveAll(List<User> users);
}
