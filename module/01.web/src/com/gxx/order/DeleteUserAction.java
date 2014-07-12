package com.gxx.order;

import com.gxx.order.dao.UserDao;
import com.gxx.order.entities.User;
import com.gxx.order.interfaces.OperateLogInterface;
import com.gxx.order.utils.BaseUtil;
import com.gxx.order.utils.TokenUtil;

/**
 * ɾ���û�Action
 *
 * @author Gxx
 * @module oa
 * @datetime 14-4-18 15:22
 */
public class DeleteUserAction extends BaseAction {
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

        //���Ƿ�ɾ���Լ�
        if(userIdInt == getUser().getId()){
            resp = "{isSuccess:false,message:'����ɾ���Լ���',hasNewToken:true,token:'" +
                    TokenUtil.createToken(request) + "'}";
        } else {
            //��ѯ�û�
            User deleteUser = UserDao.getUserById(Integer.parseInt(userId));
            if(BaseUtil.isSuperMan(deleteUser.getName())){
                resp = "{isSuccess:false,message:'������ɾ����������Ա��',hasNewToken:true,token:'" +
                        TokenUtil.createToken(request) + "'}";
                write(resp);
                return null;
            }
            if(null == deleteUser){
                resp = "{isSuccess:false,message:'�����ڸ��û�id=" + userId + "��',hasNewToken:true,token:'" +
                        TokenUtil.createToken(request) + "'}";
            } else {
                UserDao.deleteUser(deleteUser);
                resp = "{isSuccess:true,message:'ɾ���ɹ���',hasNewToken:true,token:'" +
                        TokenUtil.createToken(request) + "'}";
                //����������־
                BaseUtil.createOperateLog(getUser().getName(), OperateLogInterface.TYPE_DELETE_USER,
                        "ɾ���û�[" + deleteUser.getName() + "]", date, time, getIp());
            }
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
