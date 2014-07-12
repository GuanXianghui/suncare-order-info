package com.gxx.order.interfaces;

/**
 * 操作日志接口
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 13:16
 */
public interface OperateLogInterface extends BaseInterface {
    /**
     * 1:获取登陆短信 2:登陆 3:退出 4:新建用户 5:删除用户 6:修改用户 7:新建信息 8删除信息 9:修改信息 10:导出信息
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
