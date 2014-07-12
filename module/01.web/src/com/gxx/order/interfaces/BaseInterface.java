package com.gxx.order.interfaces;

/**
 * 基础接口
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 13:16
 */
public interface BaseInterface {
    /**
     * mysql数据库链接
     */
    public static final String MYSQL_CONNECTION = "mysql_connection";
    /**
     * 默认密码
     */
    public static final String DEFAULT_PASSWORD = "default_password";
    /**
     * md5 key
     */
    public static final String MD5_KEY = "md5_key";
    /**
     * session缓存中的token集合
     */
    public static final String SESSION_TOKEN_LIST = "session_token_list";
    /**
     * TOKEN键
     */
    public static final String TOKEN_KEY = "token";
    /**
     * 用户键
     */
    public static final String USER_KEY = "user";
    /**
     * 用户列表每页大小
     */
    public static final String USER_PAGE_SIZE = "user_page_size";
    /**
     * 订单信息列表每页大小
     */
    public static final String ORDER_INFO_PAGE_SIZE = "order_info_page_size";
    /**
     * 操作日志列表每页大小
     */
    public static final String OPERATE_LOG_PAGE_SIZE = "operate_log_page_size";
    /**
     * 超级管理员名字
     */
    public static final String SUPER_ADMIN_USER_NAMES = "super_admin_user_names";
    /**
     * 短信服务相关配置
     * 短信服务用户id
     */
    public static final String SMS_UID = "sms_uid";
    /**
     * 短信服务相关配置
     * 短信服务key
     */
    public static final String SMS_KEY = "sms_key";
}
