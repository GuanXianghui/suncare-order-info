package com.gxx.order;

import com.gxx.order.dao.UserDao;
import com.gxx.order.entities.User;
import com.gxx.order.interfaces.OperateLogInterface;
import com.gxx.order.utils.BaseUtil;
import com.gxx.order.utils.TokenUtil;

/**
 * 创建用户Action
 *
 * @author Gxx
 * @module oa
 * @datetime 14-4-18 15:22
 */
public class CreateUserAction extends BaseAction {
    /**
     * 用户姓名
     */
    String name;
    /**
     * 电话号码
     */
    String phone;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("name:" + name + ",phone:" + phone);
        String resp;
        if(UserDao.isNameExist(name)){
            resp = "{isSuccess:false,message:'该名字已存在！',hasNewToken:true,token:'" +
                    TokenUtil.createToken(request) + "'}";
        } else if(UserDao.isPhoneExist(phone)){
            resp = "{isSuccess:false,message:'该手机已被绑定！',hasNewToken:true,token:'" +
                    TokenUtil.createToken(request) + "'}";
        } else {
            //得到默认密码
            String defaultPassword = BaseUtil.generateDefaultPwd();
            User newUser = new User(name, defaultPassword, phone);
            UserDao.insertUser(newUser);
            resp = "{isSuccess:true,message:'新增用户成功！',hasNewToken:true,token:'" +
                    TokenUtil.createToken(request) + "'}";
            //创建操作日志
            BaseUtil.createOperateLog(getUser().getName(), OperateLogInterface.TYPE_CREATE_USER,
                    "创建用户[" + name + "]", date, time, getIp());
        }
        write(resp);
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
