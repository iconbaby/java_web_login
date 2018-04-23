package com.slkk.login.service;

import com.slkk.login.domain.User;
import com.slkk.login.exception.UserExistException;

public interface IUserService {
    /**
     * 提供注册服务
     *
     * @param user
     * @throws UserExistException
     */
    void registerUser(User user) throws UserExistException;

    /**
     * 提供登录服务
     *
     * @param userName
     * @param userPwd
     * @return
     */
    User loginUser(String userName, String userPwd);
}
