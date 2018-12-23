package com.itheima.mapper;

import com.itheima.domain.PageBean;
import com.itheima.domain.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    List<User> findAll(PageBean pageBean) throws Exception;
    User findUserById(Integer id) throws Exception;
    void saveUser(User user)throws Exception;
    User login(User user) throws Exception;
    void deleteUserById(Integer id) throws Exception;
    void addUser(PageBean pageBean) throws Exception;
    void updateUser(User user);
    int findTotalCount(PageBean pageBean);
}
