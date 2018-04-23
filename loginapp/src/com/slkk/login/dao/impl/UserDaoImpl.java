package com.slkk.login.dao.impl;

import com.slkk.login.dao.IUserDao;
import com.slkk.login.domain.User;
import com.slkk.login.util.XmlUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import java.text.SimpleDateFormat;

public class UserDaoImpl implements IUserDao {
    @Override
    public User find(String userName, String userPwd) {
        try {
            Document document = XmlUtils.getDocument();
            //使用XPath表达式来操作XML节点
            Element e = (Element) document.selectSingleNode("//user[@userName='" + userName + "' and @userPwd='" + userPwd + "']");
            if (e == null) {
                return null;
            }
            User user = new User();
            user.setId(e.attributeValue("id"));
            user.setEmail(e.attributeValue("email"));
            user.setUserName(e.attributeValue("userName"));
            user.setUserPwd(e.attributeValue("userPwd"));
            String birth = e.attributeValue("birthday");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            user.setBirthday((simpleDateFormat.parse(birth)));

            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(User user) {
        try {
            Document document = XmlUtils.getDocument();
            Element root = document.getRootElement();
            Element user_node = root.addElement("user");
            user_node.setAttributeValue("id", user.getId());
            user_node.setAttributeValue("userName", user.getUserName());
            user_node.setAttributeValue("userPwd", user.getUserPwd());
            user_node.setAttributeValue("email", user.getEmail());

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            user_node.setAttributeValue("birthday", simpleDateFormat.format(user.getBirthday()));

            XmlUtils.write2Xml(document);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User find(String userName) {
        try {
            Document document = XmlUtils.getDocument();
            Element e = (Element) document.selectSingleNode("//user[@userName='" + userName + "']");
            if (e == null) {
                return null;
            }
            User user = new User();
            user.setId(e.attributeValue("id"));
            user.setEmail(e.attributeValue("email"));
            user.setUserPwd(e.attributeValue("userPwd"));
            user.setUserName(e.attributeValue("userName"));
            String birth = e.attributeValue("birthday");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            user.setBirthday(sdf.parse(birth));

            return user;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
