package com.gxx.order.interfaces;

/**
 * ������־�ӿ�
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 13:16
 */
public interface OperateLogInterface extends BaseInterface {
    /**
     * 1:��ȡ��½���� 2:��½ 3:�˳� 4:�½��û� 5:ɾ���û� 6:�޸��û� 7:�½���Ϣ 8ɾ����Ϣ 9:�޸���Ϣ 10:������Ϣ
     */
    public static final int TYPE_LOG_SMS = 1;
    public static final int TYPE_LOG_IN = 2;
    public static final int TYPE_LOG_OUT = 3;
    public static final int TYPE_CREATE_USER = 4;
    public static final int TYPE_DELETE_USER = 5;
    public static final int TYPE_UPDATE_USER = 6;
    public static final int TYPE_CREATE_ORDER_INFO = 7;
    public static final int TYPE_DELETE_ORDER_INFO = 8;
    public static final int TYPE_UPDATE_ORDER_INFO = 9;
    public static final int TYPE_EXPORT_ORDER_INFO = 10;
}
