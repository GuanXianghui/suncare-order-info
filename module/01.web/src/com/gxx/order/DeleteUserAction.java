package com.gxx.order;

import com.gxx.order.dao.UserDao;
import com.gxx.order.entities.User;
import com.gxx.order.interfaces.OperateLogInterface;
import com.gxx.order.utils.BaseUtil;
import com.gxx.order.utils.TokenUtil;

/**
 * 删除用户Action
 *
 * @author Gxx
 * @module oa
 * @datetime 14-4-18 15:22
 */
public class DeleteUserAction extends BaseAction {
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

        //判是否删除自己
        if(userIdInt == getUser().getId()){
            resp = "{isSuccess:false,message:'不能删除自己！',hasNewToken:true,token:'" +
                    TokenUtil.createToken(request) + "'}";
        } else {
            //查询用户
            User deleteUser = UserDao.getUserById(Integer.parseInt(userId));
            if(BaseUtil.isSuperMan(deleteUser.getName())){
                resp = "{isSuccess:false,message:'不允许删除超级管理员！',hasNewToken:true,token:'" +
                        TokenUtil.createToken(request) + "'}";
                write(resp);
                return null;
            }
            if(null == deleteUser){
                resp = "{isSuccess:false,message:'不存在该用户id=" + userId + "！',hasNewToken:true,token:'" +
                        TokenUtil.createToken(request) + "'}";
            } else {
                UserDao.deleteUser(deleteUser);
                resp = "{isSuccess:true,message:'删除成功！',hasNewToken:true,token:'" +
                        TokenUtil.createToken(request) + "'}";
                //创建操作日志
                BaseUtil.createOperateLog(getUser().getName(), OperateLogInterface.TYPE_DELETE_USER,
                        "删除用户[" + deleteUser.getName() + "]", date, time, getIp());
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
