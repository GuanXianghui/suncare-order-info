package com.gxx.order;

import com.gxx.order.dao.OrderInfoDao;
import com.gxx.order.entities.OrderInfo;
import com.gxx.order.interfaces.OperateLogInterface;
import com.gxx.order.interfaces.OrderInfoInterface;
import com.gxx.order.utils.BaseUtil;
import com.gxx.order.utils.TokenUtil;

/**
 * 删除订单信息Action
 *
 * @author Gxx
 * @module oa
 * @datetime 14-4-18 15:22
 */
public class DeleteOrderInfoAction extends BaseAction {
    /**
     * 订单信息id
     */
    String id;
    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("id:" + id);
        OrderInfo orderInfo = OrderInfoDao.getOrderInfoById(Integer.parseInt(id));
        if(null == orderInfo || orderInfo.getState() != OrderInfoInterface.STATE_NORMAL){
            String resp = "{isSuccess:false,message:'不存在该订单信息！',hasNewToken:true,token:'" +
                    TokenUtil.createToken(request) + "'}";
            write(resp);
            return null;
        }
        OrderInfoDao.deleteOrderInfo(orderInfo);
        String resp = "{isSuccess:true,message:'删除订单信息成功！',hasNewToken:true,token:'" +
                TokenUtil.createToken(request) + "'}";
        //创建操作日志
        BaseUtil.createOperateLog(getUser().getName(), OperateLogInterface.TYPE_DELETE_ORDER_INFO,
                "删除订单信息[id=" + id + "]", date, time, getIp());
        write(resp);
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
