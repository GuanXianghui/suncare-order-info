package com.gxx.order;

import com.gxx.order.dao.OrderInfoDao;
import com.gxx.order.entities.OrderInfo;
import com.gxx.order.interfaces.OperateLogInterface;
import com.gxx.order.interfaces.OrderInfoInterface;
import com.gxx.order.utils.BaseUtil;
import com.gxx.order.utils.TokenUtil;

/**
 * 修改信息Action
 *
 * @author Gxx
 * @module oa
 * @datetime 14-4-18 15:22
 */
public class UpdateOrderInfoAction extends BaseAction {
    /**
     * id
     */
    String id;
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
        logger.info("id=" + id + ",name:" + name + ",phone:" + phone + ",type:" + type + ",date:" + date +
                ",time:" + time + ",resv:" + resv);
        OrderInfo orderInfo = OrderInfoDao.getOrderInfoById(Integer.parseInt(id));
        if(null == orderInfo || orderInfo.getState() != OrderInfoInterface.STATE_NORMAL){
            String resp = "{isSuccess:false,message:'该订单信息不存在！',hasNewToken:true,token:'" +
                    TokenUtil.createToken(request) + "'}";
            write(resp);
            return null;
        }
        //修改前信息
        String oldName = orderInfo.getName();
        String oldPhone = orderInfo.getPhone();

        //修改信息
        orderInfo.setName(name);
        orderInfo.setPhone(phone);
        orderInfo.setType(Integer.parseInt(type));
        orderInfo.setDate(date);
        orderInfo.setTime(time);
        orderInfo.setResv(resv);
        OrderInfoDao.updateOrderInfo(orderInfo);
        //返回信息
        String resp = "{isSuccess:true,message:'修改信息成功！',hasNewToken:true,token:'" +
                TokenUtil.createToken(request) + "'}";
        //创建操作日志
        BaseUtil.createOperateLog(getUser().getName(), OperateLogInterface.TYPE_UPDATE_ORDER_INFO,
                "修改信息[id:" + id + ",老的名字:" + oldName + ",老的电话:" + oldPhone + "]", date, time, getIp());
        write(resp);
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
