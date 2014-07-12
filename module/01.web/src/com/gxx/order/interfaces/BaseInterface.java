package com.gxx.order.interfaces;

/**
 * �����ӿ�
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 13:16
 */
public interface BaseInterface {
    /**
     * mysql���ݿ�����
     */
    public static final String MYSQL_CONNECTION = "mysql_connection";
    /**
     * Ĭ������
     */
    public static final String DEFAULT_PASSWORD = "default_password";
    /**
     * md5 key
     */
    public static final String MD5_KEY = "md5_key";
    /**
     * session�����е�token����
     */
    public static final String SESSION_TOKEN_LIST = "session_token_list";
    /**
     * TOKEN��
     */
    public static final String TOKEN_KEY = "token";
    /**
     * �û���
     */
    public static final String USER_KEY = "user";
    /**
     * �û��б�ÿҳ��С
     */
    public static final String USER_PAGE_SIZE = "user_page_size";
    /**
     * ������Ϣ�б�ÿҳ��С
     */
    public static final String ORDER_INFO_PAGE_SIZE = "order_info_page_size";
    /**
     * ������־�б�ÿҳ��С
     */
    public static final String OPERATE_LOG_PAGE_SIZE = "operate_log_page_size";
    /**
     * ��������Ա����
     */
    public static final String SUPER_ADMIN_USER_NAMES = "super_admin_user_names";
    /**
     * ���ŷ����������
     * ���ŷ����û�id
     */
    public static final String SMS_UID = "sms_uid";
    /**
     * ���ŷ����������
     * ���ŷ���key
     */
    public static final String SMS_KEY = "sms_key";
}
