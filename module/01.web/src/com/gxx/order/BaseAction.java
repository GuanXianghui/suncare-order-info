package com.gxx.order;

import com.gxx.order.entities.User;
import com.gxx.order.interfaces.BaseInterface;
import com.gxx.order.utils.BaseUtil;
import com.gxx.order.utils.DateUtil;
import com.gxx.order.utils.IPAddressUtil;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Date;

/**
 * 基础Action
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 12:44
 */
public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
    /**
     * 日志处理器
     */
    Logger logger = Logger.getLogger(BaseAction.class);

    /**
     * 当前时间
     */
    String date;
    String time;
    String defaultDateTime;

    /**
     * token串 一定会有值，否则过不了StrutsFilter
     */
    String token;

    /**
     * 消息
     */
    String message;

    /**
     * request，response
     */
    HttpServletRequest request;
    HttpServletResponse response;

    /**
     * 构造函数
     */
    public BaseAction() {
        this.date = DateUtil.getNowDate();
        this.time = DateUtil.getNowTime();
        this.defaultDateTime = DateUtil.getDefaultDateTime(new Date());
    }

    /**
     * 获取ip
     * @return
     */
    public String getIp() {
        return IPAddressUtil.getIPAddress(request);
    }

    /**
     * 获取session
     * @return
     */
    public HttpSession getSession() {
        return request.getSession();
    }

    /**
     * 获取application
     * @return
     */
    public ServletContext getApplication() {
        return request.getSession().getServletContext();
    }

    /**
     * 获取User
     * @return
     */
    public User getUser() {
        return (User) getSession().getAttribute(BaseInterface.USER_KEY);
    }

    /**
     * 刷新session缓存中的user
     * @param user
     */
    public void refreshSessionUser(User user) {
        getSession().setAttribute(BaseInterface.USER_KEY, user);
    }

    /**
     * ajax写出结果
     * @param resp
     */
    public void write(String resp) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(resp);
        writer.flush();
        writer.close();
    }

    /**
     * 判是否超级管理员
     * @return
     */
    public boolean isSuperMan(){
        return BaseUtil.isSuperMan(getUser().getName());
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }
}