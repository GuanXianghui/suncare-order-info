package com.gxx.order.entities;

import org.apache.commons.lang.StringUtils;

/**
 * �û�ʵ��
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 18:21
 */
public class User {
    int id;
    String name;
    String password;
    String phone;
    String validateCode;

    /**
     * ����ʱʹ��
     * @param name
     * @param password
     * @param phone
     */
    public User(String name, String password, String phone) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.validateCode = StringUtils.EMPTY;
    }

    /**
     * ��ѯʱʹ��
     * @param id
     * @param name
     * @param password
     * @param phone
     * @param validateCode
     */
    public User(int id, String name, String password, String phone, String validateCode) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.validateCode = validateCode;
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

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
}
