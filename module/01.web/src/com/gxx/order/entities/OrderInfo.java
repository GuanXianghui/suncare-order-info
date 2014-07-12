package com.gxx.order.entities;

import com.gxx.order.interfaces.OrderInfoInterface;
import org.apache.commons.lang.StringUtils;

/**
 * ������Ϣʵ��
 *
 * @author Gxx
 * @module oa
 * @datetime 14-7-11 16:53
 */
public class OrderInfo {
    int id;
    String name;
    String phone;
    int type;
    String date;
    String time;
    String resv;
    int state;
    String createDate;
    String createTime;
    String createIp;

    /**
     * ����ʱʹ��
     * @param name
     * @param phone
     * @param type
     * @param date
     * @param time
     * @param resv
     * @param state
     * @param createDate
     * @param createTime
     * @param createIp
     */
    public OrderInfo(String name, String phone, int type, String date, String time, String resv, int state, String createDate,
                     String createTime, String createIp) {
        this.name = name;
        this.phone = phone;
        this.type = type;
        this.date = date;
        this.time = time;
        this.resv = resv;
        this.state = state;
        this.createDate = createDate;
        this.createTime = createTime;
        this.createIp = createIp;
    }

    /**
     * ��ѯʱʹ��
     * @param id
     * @param name
     * @param phone
     * @param type
     * @param date
     * @param time
     * @param resv
     * @param state
     * @param createDate
     * @param createTime
     * @param createIp
     */
    public OrderInfo(int id, String name, String phone, int type, String date, String time, String resv, int state,
                     String createDate, String createTime, String createIp) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.type = type;
        this.date = date;
        this.time = time;
        this.resv = resv;
        this.state = state;
        this.createDate = createDate;
        this.createTime = createTime;
        this.createIp = createIp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getResv() {
        return resv;
    }

    public void setResv(String resv) {
        this.resv = resv;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp;
    }

    /**
     * ���ض�����������
     * @return
     */
    public String getTypeDesc(){
        if(OrderInfoInterface.TYPE_PROJECT == type){
            return "����";
        }
        if(OrderInfoInterface.TYPE_PRIVATE == type){
            return "˽��";
        }
        if(OrderInfoInterface.TYPE_AGENCY == type){
            return "����";
        }
        return StringUtils.EMPTY;
    }
}
