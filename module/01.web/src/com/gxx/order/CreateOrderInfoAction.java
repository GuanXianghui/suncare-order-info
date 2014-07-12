package com.gxx.order;

import com.gxx.order.dao.OrderInfoDao;
import com.gxx.order.entities.OrderInfo;
import com.gxx.order.interfaces.OperateLogInterface;
import com.gxx.order.interfaces.OrderInfoInterface;
import com.gxx.order.utils.BaseUtil;
import com.gxx.order.utils.TokenUtil;

/**
 * 新建信息Action
 *
 * @author Gxx
 * @module oa
 * @datetime 14-4-18 15:22
 */
public class CreateOrderInfoAction extends BaseAction {
    /**
     * 用户姓名
     */
    String name;
    /**
     * 电话号码
     */
    String phone;
    /**
     * 订单类型
     */
    String type;

    /**
     * 订单日期
     */
    String date;
    /**
     * 订单时间
     */
    String time;
    /**
     * 备注信息
     */
    String resv;
    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("name:" + name + ",phone:" + phone + ",type:" + type + ",date:" + date + ",time:" +
                time + ",resv:" + resv);
        OrderInfo orderInfo = new OrderInfo(name, phone, Integer.parseInt(type), date, time, resv,
                OrderInfoInterface.STATE_NORMAL, super.date, super.time, getIp());
        OrderInfoDao.insertOrderInfo(orderInfo);
        String resp = "{isSuccess:true,message:'新建信息成功！',hasNewToken:true,token:'" +
                TokenUtil.createToken(request) + "'}";
        //创建操作日志
        BaseUtil.createOperateLog(getUser().getName(), OperateLogInterface.TYPE_CREATE_ORDER_INFO,
                "新建信息[" + name + "," + phone + "]", date, time, getIp());
        write(resp);
        return null;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
}
