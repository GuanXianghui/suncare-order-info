package com.gxx.order;

import com.gxx.order.dao.OrderInfoDao;
import com.gxx.order.entities.OrderInfo;
import com.gxx.order.interfaces.OperateLogInterface;
import com.gxx.order.interfaces.OrderInfoInterface;
import com.gxx.order.utils.BaseUtil;
import com.gxx.order.utils.TokenUtil;

/**
 * �޸���ϢAction
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
     * �û�����
     */
    String name;
    /**
     * �绰����
     */
    String phone;
    /**
     * ��������
     */
    String type;

    /**
     * ��������
     */
    String date;
    /**
     * ����ʱ��
     */
    String time;
    /**
     * ��ע��Ϣ
     */
    String resv;
    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("id=" + id + ",name:" + name + ",phone:" + phone + ",type:" + type + ",date:" + date +
                ",time:" + time + ",resv:" + resv);
        OrderInfo orderInfo = OrderInfoDao.getOrderInfoById(Integer.parseInt(id));
        if(null == orderInfo || orderInfo.getState() != OrderInfoInterface.STATE_NORMAL){
            String resp = "{isSuccess:false,message:'�ö�����Ϣ�����ڣ�',hasNewToken:true,token:'" +
                    TokenUtil.createToken(request) + "'}";
            write(resp);
            return null;
        }
        //�޸�ǰ��Ϣ
        String oldName = orderInfo.getName();
        String oldPhone = orderInfo.getPhone();

        //�޸���Ϣ
        orderInfo.setName(name);
        orderInfo.setPhone(phone);
        orderInfo.setType(Integer.parseInt(type));
        orderInfo.setDate(date);
        orderInfo.setTime(time);
        orderInfo.setResv(resv);
        OrderInfoDao.updateOrderInfo(orderInfo);
        //������Ϣ
        String resp = "{isSuccess:true,message:'�޸���Ϣ�ɹ���',hasNewToken:true,token:'" +
                TokenUtil.createToken(request) + "'}";
        //����������־
        BaseUtil.createOperateLog(getUser().getName(), OperateLogInterface.TYPE_UPDATE_ORDER_INFO,
                "�޸���Ϣ[id:" + id + ",�ϵ�����:" + oldName + ",�ϵĵ绰:" + oldPhone + "]", date, time, getIp());
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
