package com.gxx.order;

import com.gxx.order.dao.UserDao;
import com.gxx.order.entities.User;
import com.gxx.order.interfaces.OperateLogInterface;
import com.gxx.order.utils.BaseUtil;
import com.gxx.order.utils.TokenUtil;

/**
 * ��������Action
 *
 * @author Gxx
 * @module oa
 * @datetime 14-4-18 15:22
 */
public class ResetPasswordAction extends BaseAction {
    /**
     * �û�ID
     */
    String userId;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("userId:" + userId);
        String resp;
        //�û�id
        int userIdInt;
        try{
            userIdInt = Integer.parseInt(userId);
        } catch (Exception e) {
            resp = "{isSuccess:false,message:'�û�ID�Ƿ���',hasNewToken:true,token:'" +
                    TokenUtil.createToken(request) + "'}";
            write(resp);
            return null;
        }
        //��ѯ�û�
        User resetUser = UserDao.getUserById(Integer.parseInt(userId));
        if(null == resetUser){
            resp = "{isSuccess:false,message:'�����ڸ��û�id=" + userId + "��',hasNewToken:true,token:'" +
                    TokenUtil.createToken(request) + "'}";
        } else {
            resetUser.setPassword(BaseUtil.generateDefaultPwd());
            UserDao.updateUser(resetUser);
            resp = "{isSuccess:true,message:'��������ɹ���',hasNewToken:true,token:'" +
                    TokenUtil.createToken(request) + "'}";
            //����������־
            BaseUtil.createOperateLog(getUser().getName(), OperateLogInterface.TYPE_UPDATE_USER,
                    "��������[" + resetUser.getName() + "]", date, time, getIp());
        }
        write(resp);
        return null;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
