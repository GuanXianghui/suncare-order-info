package com.gxx.order;

import com.gxx.order.dao.UserDao;
import com.gxx.order.entities.User;
import com.gxx.order.interfaces.BaseInterface;
import com.gxx.order.interfaces.OperateLogInterface;
import com.gxx.order.utils.BaseUtil;
import com.gxx.order.utils.TokenUtil;
import org.apache.commons.lang.StringUtils;

/**
 * �޸�����Action
 *
 * @author Gxx
 * @module oa
 * @datetime 14-4-18 15:22
 */
public class UpdateInfoAction extends BaseAction {
    /**
     * ����
     */
    String password;
    /**
     * �绰����
     */
    String phone;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("password:" + password + ",phone:" + phone);
        //�޸�����
        User user = getUser();
        String oldPhone = user.getPhone();
        if(!StringUtils.equals(oldPhone, phone) && UserDao.isPhoneExist(phone)){
            String resp = "{isSuccess:false,message:'���ֻ����ѱ��󶨣�',hasNewToken:true,token:'" +
                    TokenUtil.createToken(request) + "'}";
            write(resp);
            return null;
        }
        user.setPassword(password);
        user.setPhone(phone);
        UserDao.updateUser(user);
        request.getSession().setAttribute(BaseInterface.USER_KEY, user);
        String resp = "{isSuccess:true,message:'�޸����ϳɹ���',hasNewToken:true,token:'" +
                TokenUtil.createToken(request) + "'}";
        //����������־
        BaseUtil.createOperateLog(user.getName(), OperateLogInterface.TYPE_UPDATE_USER,
                "�޸����ϣ��Ϻ��룺[" + oldPhone + "]���º��룺[" + phone + "]", date, time, getIp());
        write(resp);
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
