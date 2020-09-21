package com.cjh.service.Impl;

import com.cjh.dao.UserDao;
import com.cjh.entity.User;
import com.cjh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

/**
 * @Author: huahua
 * @Date: 2020-09-15 10:22
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired()
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void saveAll(List<User> users) {
        for (User user : users) {
            user.setId(null);
            userDao.save(user);
        }
    }
}
