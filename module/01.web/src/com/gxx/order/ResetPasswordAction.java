package com.gxx.order;

import com.gxx.order.dao.UserDao;
import com.gxx.order.entities.User;
import com.gxx.order.interfaces.OperateLogInterface;
import com.gxx.order.utils.BaseUtil;
import com.gxx.order.utils.TokenUtil;

/**
 * 重置密码Action
 *
 * @author Gxx
 * @module oa
 * @datetime 14-4-18 15:22
 */
public class ResetPasswordAction extends BaseAction {
    /**
     * 用户ID
     */
    String userId;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("userId:" + userId);
        String resp;
        //用户id
        int userIdInt;
        try{
            userIdInt = Integer.parseInt(userId);
        } catch (Exception e) {
            resp = "{isSuccess:false,message:'用户ID非法！',hasNewToken:true,token:'" +
                    TokenUtil.createToken(request) + "'}";
            write(resp);
            return null;
        }
        //查询用户
        User resetUser = UserDao.getUserById(Integer.parseInt(userId));
        if(null == resetUser){
            resp = "{isSuccess:false,message:'不存在该用户id=" + userId + "！',hasNewToken:true,token:'" +
                    TokenUtil.createToken(request) + "'}";
        } else {
            resetUser.setPassword(BaseUtil.generateDefaultPwd());
            UserDao.updateUser(resetUser);
            resp = "{isSuccess:true,message:'重置密码成功！',hasNewToken:true,token:'" +
                    TokenUtil.createToken(request) + "'}";
            //创建操作日志
            BaseUtil.createOperateLog(getUser().getName(), OperateLogInterface.TYPE_UPDATE_USER,
                    "重置密码[" + resetUser.getName() + "]", date, time, getIp());
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
