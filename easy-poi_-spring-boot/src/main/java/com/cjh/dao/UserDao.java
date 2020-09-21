package com.cjh.dao;

import com.cjh.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: huahua
 * @Date: 2020-09-15 10:10
 */
@Mapper
public interface UserDao {
    List<User> findAll();
    void save(User user);
}
