package com.gxx.order;

import com.gxx.order.dao.UserDao;
import com.gxx.order.entities.User;
import com.gxx.order.interfaces.OperateLogInterface;
import com.gxx.order.utils.BaseUtil;
import com.gxx.order.utils.TokenUtil;

/**
 * �����û�Action
 *
 * @author Gxx
 * @module oa
 * @datetime 14-4-18 15:22
 */
public class CreateUserAction extends BaseAction {
    /**
     * �û�����
     */
    String name;
    /**
     * �绰����
     */
    String phone;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("name:" + name + ",phone:" + phone);
        String resp;
        if(UserDao.isNameExist(name)){
            resp = "{isSuccess:false,message:'�������Ѵ��ڣ�',hasNewToken:true,token:'" +
                    TokenUtil.createToken(request) + "'}";
        } else if(UserDao.isPhoneExist(phone)){
            resp = "{isSuccess:false,message:'���ֻ��ѱ��󶨣�',hasNewToken:true,token:'" +
                    TokenUtil.createToken(request) + "'}";
        } else {
            //�õ�Ĭ������
            String defaultPassword = BaseUtil.generateDefaultPwd();
            User newUser = new User(name, defaultPassword, phone);
            UserDao.insertUser(newUser);
            resp = "{isSuccess:true,message:'�����û��ɹ���',hasNewToken:true,token:'" +
                    TokenUtil.createToken(request) + "'}";
            //����������־
            BaseUtil.createOperateLog(getUser().getName(), OperateLogInterface.TYPE_CREATE_USER,
                    "�����û�[" + name + "]", date, time, getIp());
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
