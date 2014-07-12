package com.gxx.order.utils;

import com.gxx.order.dao.OperateLogDao;
import com.gxx.order.entities.OperateLog;
import com.gxx.order.interfaces.BaseInterface;
import com.gxx.order.interfaces.SymbolInterface;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 基础工具类
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-30 12:09
 */
public class BaseUtil implements SymbolInterface {
    /**
     * 判登录
     *
     * @param request
     */
    public static boolean isLogin(HttpServletRequest request) throws Exception {
        if(request.getSession().getAttribute(BaseInterface.USER_KEY) == null) {
            return false;
        }
        return true;
    }

    /**
     * 得到默认密码
     * md5(defaultPwd + md5Key)
     *
     * @return
     */
    public static String generateDefaultPwd(){
        String md5Key = PropertyUtil.getInstance().getProperty(BaseInterface.MD5_KEY);
        String defaultPwd = PropertyUtil.getInstance().getProperty(BaseInterface.DEFAULT_PASSWORD);
        MD5Util md5 = new MD5Util();
        String password = md5.md5(defaultPwd + md5Key);
        return password;
    }

    /**
     * 创建操作日志
     *
     * @param userName
     * @param type
     * @param content
     * @param date
     * @param time
     * @param ip
     * @throws Exception
     */
    public static void createOperateLog(String userName, int type, String content, String date, String time,
                                        String ip) throws Exception{
        OperateLog operateLog = new OperateLog(userName, type, content, date, time, ip);
        OperateLogDao.insertOperateLog(operateLog);
    }

    /**
     * 根据名字判是否超级管理员
     *
     * @param userName
     * @return
     */
    public static boolean isSuperMan(String userName){
        String superAdminUserNames = PropertyUtil.getInstance().getProperty(BaseInterface.SUPER_ADMIN_USER_NAMES);
        String[] names = superAdminUserNames.split(SYMBOL_COMMA);
        for(int i=0;i<names.length;i++){
            if(StringUtils.equals(userName, names[i])){
                return true;
            }
        }
        return false;
    }
}
