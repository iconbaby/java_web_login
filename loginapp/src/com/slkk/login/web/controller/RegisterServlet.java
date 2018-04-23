package com.slkk.login.web.controller;

import com.slkk.login.domain.User;
import com.slkk.login.exception.UserExistException;
import com.slkk.login.service.IUserService;
import com.slkk.login.service.impl.UserServiceImpl;
import com.slkk.login.util.WebUtils;
import com.slkk.login.web.formbean.RegisterFormbean;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.Date;

@WebServlet(name = "RegisterServlet",urlPatterns = "/registerServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RegisterFormbean formbean = WebUtils.request2Bean(request, RegisterFormbean.class);
        if (!formbean.validate()) {
            request.setAttribute("formbean", formbean);
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
            return;
        }

        User user = new User();
        ConvertUtils.register(new DateLocaleConverter(), Date.class);
        try {
            BeanUtils.copyProperties(user, formbean);
            user.setId(WebUtils.makeId());
            IUserService userService = new UserServiceImpl();
            userService.registerUser(user);
            String message = String.format("注册成功！！3秒后为您自动跳到登录页面！！<meta http-equiv='refresh' content='3;url=%s'/>",
                    request.getContextPath() + "/servlet/LoginUIServlet");
            request.setAttribute("message", message);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        } catch (UserExistException e) {
            formbean.getErrors().put("userName", "用户已存在");
            request.setAttribute("formbean", formbean);
            request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "对不起，注册失败");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
    }
}
