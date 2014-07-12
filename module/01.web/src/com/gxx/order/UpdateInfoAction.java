package com.gxx.order;

import com.gxx.order.dao.UserDao;
import com.gxx.order.entities.User;
import com.gxx.order.interfaces.BaseInterface;
import com.gxx.order.interfaces.OperateLogInterface;
import com.gxx.order.utils.BaseUtil;
import com.gxx.order.utils.TokenUtil;
import org.apache.commons.lang.StringUtils;

/**
 * 修改资料Action
 *
 * @author Gxx
 * @module oa
 * @datetime 14-4-18 15:22
 */
public class UpdateInfoAction extends BaseAction {
    /**
     * 密码
     */
    String password;
    /**
     * 电话号码
     */
    String phone;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("password:" + password + ",phone:" + phone);
        //修改资料
        User user = getUser();
        String oldPhone = user.getPhone();
        if(!StringUtils.equals(oldPhone, phone) && UserDao.isPhoneExist(phone)){
            String resp = "{isSuccess:false,message:'该手机号已被绑定！',hasNewToken:true,token:'" +
                    TokenUtil.createToken(request) + "'}";
            write(resp);
            return null;
        }
        user.setPassword(password);
        user.setPhone(phone);
        UserDao.updateUser(user);
        request.getSession().setAttribute(BaseInterface.USER_KEY, user);
        String resp = "{isSuccess:true,message:'修改资料成功！',hasNewToken:true,token:'" +
                TokenUtil.createToken(request) + "'}";
        //创建操作日志
        BaseUtil.createOperateLog(user.getName(), OperateLogInterface.TYPE_UPDATE_USER,
                "修改资料，老号码：[" + oldPhone + "]，新号码：[" + phone + "]", date, time, getIp());
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
