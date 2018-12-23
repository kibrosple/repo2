package com.itheima.service;

import com.itheima.domain.PageBean;
import com.itheima.domain.User;

import java.util.List;

public interface UserService {
    List<User> findAll(PageBean pageBean) throws Exception;

    User login(User user)throws Exception;

    User findUserById(Integer id) throws Exception;

    void updateUser(User user) throws Exception;

    void addUser(PageBean pageBean)throws Exception;

    void deleteUserById(Integer id) throws Exception;

    void delSelectedUser(String[] ids) throws Exception;

    int findTotalCount(PageBean pageBean);
}
