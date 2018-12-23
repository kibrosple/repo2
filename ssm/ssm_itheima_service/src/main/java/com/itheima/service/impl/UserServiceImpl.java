package com.itheima.service.impl;

import com.itheima.domain.PageBean;
import com.itheima.domain.User;
import com.itheima.mapper.UserMapper;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> findAll(PageBean pageBean)throws Exception {
        return userMapper.findAll(pageBean);
    }

    public User login(User user) throws Exception {
        return userMapper.login(user);
    }

    public User findUserById(Integer id) throws Exception {
        User user = userMapper.findUserById(id);
        return user;
    }

    public void updateUser(User user) throws Exception {
        userMapper.updateUser(user);
    }

    public void addUser(PageBean pageBean)throws Exception {
        userMapper.addUser(pageBean);
    }

    public void deleteUserById(Integer id) throws Exception {
        userMapper.deleteUserById(id);
    }

    public void delSelectedUser(String[] ids) throws Exception {
        //遍历数组,对id进行非空判断防止空指针异常
        for (String id : ids) {
            if(id !=null && !"".equals(id)){
                userMapper.deleteUserById(Integer.parseInt(id));
            }
        }
    }

    public int findTotalCount(PageBean pageBean) {

        return userMapper.findTotalCount(pageBean);
    }
}
