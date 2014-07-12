package com.gxx.order.entities;

import com.gxx.order.interfaces.OperateLogInterface;
import org.apache.commons.lang.StringUtils;

/**
 * 操作日志实体
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 18:21
 */
public class OperateLog implements OperateLogInterface {
    int id;
    String userName;
    int type;
    String content;
    String date;
    String time;
    String ip;

    /**
     * 新增时使用
     * @param userName
     * @param type
     * @param content
     * @param date
     * @param time
     * @param ip
     */
    public OperateLog(String userName, int type, String content, String date, String time, String ip) {
        this.userName = userName;
        this.type = type;
        this.content = content;
        this.date = date;
        this.time = time;
        this.ip = ip;
    }

    /**
     * 查询时使用
     * @param id
     * @param userName
     * @param type
     * @param content
     * @param date
     * @param time
     * @param ip
     */
    public OperateLog(int id, String userName, int type, String content, String date, String time, String ip) {
        this.id = id;
        this.userName = userName;
        this.type = type;
        this.content = content;
        this.date = date;
        this.time = time;
        this.ip = ip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 返回操作类型描述
     * 1:获取登陆短信 2:登陆 3:退出 4:新建用户 5:删除用户 6:修改用户 7:新建信息 8:删除信息 9:修改信息 10:导出信息
     * @return
     */
    public String getTypeDesc(){
        String typeDesc = StringUtils.EMPTY;
        if(type == TYPE_LOG_SMS){
            typeDesc = "获取登陆短信";
        } else if(type == TYPE_LOG_IN){
            typeDesc = "登陆";
        } else if(type == TYPE_LOG_OUT){
            typeDesc = "退出";
        } else if(type == TYPE_CREATE_USER){
            typeDesc = "新建用户";
        } else if(type == TYPE_DELETE_USER){
            typeDesc = "删除用户";
        } else if(type == TYPE_UPDATE_USER){
            typeDesc = "修改用户";
        } else if(type == TYPE_CREATE_ORDER_INFO){
            typeDesc = "新建信息";
        } else if(type == TYPE_DELETE_ORDER_INFO){
            typeDesc = "删除信息";
        } else if(type == TYPE_UPDATE_ORDER_INFO){
            typeDesc = "修改信息";
        } else if(type == TYPE_EXPORT_ORDER_INFO){
            typeDesc = "导出信息";
        }
        return typeDesc;
    }
}
