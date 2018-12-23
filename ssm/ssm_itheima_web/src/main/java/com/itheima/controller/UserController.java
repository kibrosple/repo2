package com.itheima.controller;
import com.itheima.domain.PageBean;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    public String findAll(Model model, PageBean pb) throws Exception {
        //System.out.println(pb);
        PageBean pageBean = transferPb(pb);
        //System.out.println(pageBean);
        if(pageBean.getCurrentPage()==0&&pageBean.getRows()==0) {
                pageBean.setCurrentPage(1);
                pageBean.setRows(4);
                pageBean.setStart(0);
            }
        int rows = 4;
        pageBean.setRows(rows);

        int totalCount = userService.findTotalCount(pageBean);

        pageBean.setTotalCount(totalCount);
        int totalPage = (int) Math.ceil((double) totalCount/rows);
        pageBean.setTotalPage(totalPage);
        int start = (pageBean.getCurrentPage()-1)*rows;
        pageBean.setStart(start);



        List<User> userList = userService.findAll(pageBean);
        model.addAttribute("pb",pageBean);
        model.addAttribute("userList",userList);
        return "list";
    }
    @RequestMapping("/login")
    public String login(HttpSession session, User user,String ck, HttpServletResponse response) throws Exception{
        User loginuser = userService.login(user);
        if(loginuser !=null ) {
            //将用户的信息保存在session域中
            session.setAttribute("user",loginuser);
            Cookie cookieName = new Cookie("username",loginuser.getName());
            Cookie cookiePassword = new Cookie("password",loginuser.getPassword());
            if("1".equals(ck)){//如果被选中,则保存cookie
                cookieName.setMaxAge(7*24*60*60);
                cookiePassword.setMaxAge(7*24*60*60);
            }else{
                cookieName.setMaxAge(0);
                cookiePassword.setMaxAge(0);
            }
            //设置路径
            cookieName.setPath("/");
            cookiePassword.setPath("/");
            //将cookie返回页面
            response.addCookie(cookieName);
            response.addCookie(cookiePassword);
            return "index1";
        }else {
            return"loginError" ;
        }
    }

    @RequestMapping("/findUserById")
    public String findUserById(Model model, Integer id) throws Exception{
        User user = userService.findUserById(id);
        model.addAttribute("user",user);
       return "update";
    }

    @RequestMapping("/updateUser")
    public String updateUser(User user) throws Exception{
       userService.updateUser(user);
        return "forward:findAll";
    }

    @RequestMapping("/addUser")
    public String addUser(PageBean pageBean) throws Exception{
        System.out.println(pageBean);
        userService.addUser(pageBean);
        return "redirect:/user/findAll";
    }
    @RequestMapping("/add")
    public String add() throws Exception{
        return "add";
    }
    @RequestMapping("/deleteUserById")
    public String deleteUserById(Integer id) throws Exception{
       userService.deleteUserById(id);
        return "forward:findAll";
    }
    @RequestMapping("/delSelectedUser")
    public String  delSelectedUser( String[] uid) throws Exception{
        userService.delSelectedUser(uid);
        return "forward:findAll";
    }

    private static PageBean transferPb(PageBean pageBean){
        String reg = "\\s+";
        if(pageBean!=null&&pageBean.getUser()!=null){

            User user = pageBean.getUser();

            String username = pageBean.getUser().getUsername();
            String newUsername = username.replaceAll(reg, "");

            String address = pageBean.getUser().getAddress();
            String newAddress = address.replaceAll(reg, "");

            String email = pageBean.getUser().getEmail();
            String newEmail = email.replaceAll(reg, "");

            if(newUsername.length()==0){
                user.setUsername(null);
            }
            else{
                user.setUsername(newUsername);
            }

            if(newAddress.length()==0){
                user.setAddress(null);
            }
            else{
                user.setAddress(newAddress);
            }

            if(newEmail.length()==0){
                user.setEmail(null);
            }else{
                user.setEmail(newEmail);
            }

        }
        return pageBean;
    }


}
