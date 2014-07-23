package com.gxx.order.interfaces;

/**
 * 订单信息接口
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 13:16
 */
public interface OrderInfoInterface extends BaseInterface {
    /**
     * 状态 1:正常 2:删除
     */
    public static final int STATE_NORMAL = 1;
    public static final int STATE_DELETED = 2;

    /**
     * 类型 1:工程 2:私单 3:代理 4:样板 5:工程招投标 6:私单招投标
     */
    public static final int TYPE_PROJECT = 1;
    public static final int TYPE_PRIVATE = 2;
    public static final int TYPE_AGENCY = 3;
    public static final int TYPE_DEMO = 4;
    public static final int TYPE_PROJECT_ZTB = 5;
    public static final int TYPE_PRIVATE_ZTB = 6;
}
