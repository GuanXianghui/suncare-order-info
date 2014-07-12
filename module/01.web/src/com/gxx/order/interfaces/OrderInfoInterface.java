package com.gxx.order.interfaces;

/**
 * ������Ϣ�ӿ�
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 13:16
 */
public interface OrderInfoInterface extends BaseInterface {
    /**
     * ״̬ 1:���� 2:ɾ��
     */
    public static final int STATE_NORMAL = 1;
    public static final int STATE_DELETED = 2;

    /**
     * ���� 1:���� 2:˽�� 3:����
     */
    public static final int TYPE_PROJECT = 1;
    public static final int TYPE_PRIVATE = 2;
    public static final int TYPE_AGENCY = 3;
}