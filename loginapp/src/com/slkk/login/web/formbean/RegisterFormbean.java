package com.slkk.login.web.formbean;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import java.util.HashMap;
import java.util.Map;

public class RegisterFormbean {
    private String userPwd;
    private String confirmPwd;
    private String email;
    private String birthday;
    private HashMap<String, String> errors = new HashMap<String, String>();
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = (HashMap<String, String>) errors;
    }

    public boolean validate() {
        boolean isOk = true;
        if (this.userName == null || this.userName.trim().equals("")) {
            isOk = false;
            errors.put("userName", "用户名不能为空");
        } else {
            if (!this.userName.matches("[a-zA-Z]{3,8}")) {
                isOk = false;
                errors.put("userName", "用户名必须是3到8位的字母");
            }
        }
        if ((this.userPwd == null) || (this.userPwd.trim().equals(""))) {
            isOk = false;
            errors.put("userPwd", "密码不能为空");
        } else {
            if (!this.userPwd.matches("\\d{3,8}")) {
                isOk = false;
                errors.put("userPwd", " 密码必须是3到8位的数字");
            }
        }
        if (this.confirmPwd != null) {
            if (!this.confirmPwd.equals(this.userPwd)) {
                isOk = false;
                errors.put("confirmPwd", "两次密码不一致");
            }
        }

        if (this.email != null && !this.email.trim().equals("")) {
            if (!this.email.matches("\\w+@\\w+(\\.\\w+)+")) {
                isOk = false;
                errors.put("email", "邮箱不是一个合法的邮箱");
            }
        }
        if (this.birthday != null && this.birthday.trim().equals("")) {
            try {
                DateLocaleConverter dateLocaleConverter = new DateLocaleConverter();
                dateLocaleConverter.convert(this.birthday);
            } catch (Exception e) {
                isOk = false;
                errors.put("birthday", "生日必须是一个日期");
            }
        }
        return isOk;
    }
}
