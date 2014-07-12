package com.gxx.order.dao;

import com.gxx.order.entities.OrderInfo;
import com.gxx.order.interfaces.BaseInterface;
import com.gxx.order.interfaces.OrderInfoInterface;
import com.gxx.order.utils.PropertyUtil;
import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单信息实体操作类
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 20:22
 */
public class OrderInfoDao {
    /**
     * 根据条件查订单信息
     *
     * @param name
     * @param pageNum
     * @return
     * @throws Exception
     */
    public static List<OrderInfo> queryOrderInfoByConditions(String name, String phone, String type, String date, String resv, int pageNum) throws Exception {
        //订单信息列表每页大小
        int pageSize = Integer.parseInt(PropertyUtil.getInstance().getProperty(BaseInterface.ORDER_INFO_PAGE_SIZE));
        //订单信息集合
        List<OrderInfo> list = new ArrayList<OrderInfo>();
        //sql语句
        String sql = "SELECT id,name,phone,type,date,time,resv,state,create_date,create_time,create_ip FROM order_info WHERE 1=1";
        //如果name非空带上为条件
        if(StringUtils.isNotBlank(name)){
            sql += " AND name LIKE '%" + name + "%'";
        }
        //如果phone非空带上为条件
        if(StringUtils.isNotBlank(phone)){
            sql += " AND phone LIKE '%" + phone + "%'";
        }
        //如果type非空带上为条件
        if(StringUtils.isNotBlank(type)){
            sql += " AND type=" + type;
        }
        //如果date非空带上为条件
        if(StringUtils.isNotBlank(date)){
            sql += " AND date='" + date + "'";
        }
        //如果resv非空带上为条件
        if(StringUtils.isNotBlank(resv)){
            sql += " AND resv LIKE '%" + resv + "%'";
        }
        sql += " AND state=" + OrderInfoInterface.STATE_NORMAL;
        sql += " ORDER BY id LIMIT " + ((pageNum-1) * pageSize) + "," + pageSize;
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                int id = rs.getInt("id");
                String newName = rs.getString("name");
                String newPhone = rs.getString("phone");
                int newType = rs.getInt("type");
                String newDate = rs.getString("date");
                String time = rs.getString("time");
                String newResv = rs.getString("resv");
                int state = rs.getInt("state");
                String createDate = rs.getString("create_date");
                String createTime = rs.getString("create_time");
                String createIp = rs.getString("create_ip");
                OrderInfo orderInfo = new OrderInfo(id, newName, newPhone, newType, newDate, time, newResv, state,
                        createDate, createTime, createIp);
                list.add(orderInfo);
            }
            return list;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 根据条件查订单信息数量
     *
     * @param name
     * @return
     * @throws Exception
     */
    public static int countOrderInfoByConditions(String name, String phone, String type, String date, String resv) throws Exception {
        //结果
        int count = 0;
        //sql语句
        String sql = "SELECT count(1) count_num FROM order_info WHERE 1=1";
        //如果name非空带上为条件
        if(StringUtils.isNotBlank(name)){
            sql += " AND name LIKE '%" + name + "%'";
        }
        //如果phone非空带上为条件
        if(StringUtils.isNotBlank(phone)){
            sql += " AND phone LIKE '%" + phone + "%'";
        }
        //如果type非空带上为条件
        if(StringUtils.isNotBlank(type)){
            sql += " AND type=" + type;
        }
        //如果date非空带上为条件
        if(StringUtils.isNotBlank(date)){
            sql += " AND date='" + date + "'";
        }
        //如果resv非空带上为条件
        if(StringUtils.isNotBlank(resv)){
            sql += " AND resv LIKE '%" + resv + "%'";
        }
        sql += " AND state=" + OrderInfoInterface.STATE_NORMAL;
        sql += " ORDER BY id";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                count = rs.getInt("count_num");
            }
            return count;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 根据id查用户
     *
     * @param id
     * @return
     * @throws Exception
     */
    public static OrderInfo getOrderInfoById(int id) throws Exception {
        String sql = "SELECT id,name,phone,type,date,time,resv,state,create_date,create_time,create_ip FROM " +
                "order_info WHERE id=" + id + " order by id";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                int type = rs.getInt("type");
                String date = rs.getString("date");
                String time = rs.getString("time");
                String resv = rs.getString("resv");
                int state = rs.getInt("state");
                String createDate = rs.getString("create_date");
                String createTime = rs.getString("create_time");
                String createIp = rs.getString("create_ip");
                OrderInfo orderInfo = new OrderInfo(id, name, phone, type, date, time, resv, state, createDate, createTime,
                        createIp);
                return orderInfo;
            }
            return null;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 新增订单信息
     *
     * @param orderInfo
     * @throws Exception
     */
    public static void insertOrderInfo(OrderInfo orderInfo) throws Exception {
        String sql = "insert into order_info" +
                "(id,name,phone,type,date,time,resv,state,create_date,create_time,create_ip)" +
                "values" +
                "(null,'" + orderInfo.getName() + "','" + orderInfo.getPhone() + "'," + orderInfo.getType() +
                ",'" + orderInfo.getDate() + "','" + orderInfo.getTime() + "','" + orderInfo.getResv() + "'," + orderInfo.getState() +
                ",'" + orderInfo.getCreateDate() + "','" + orderInfo.getCreateTime() + "','" + orderInfo.getCreateIp() + "')";
        DB.executeUpdate(sql);
    }

    /**
     * 更新订单信息
     *
     * @param orderInfo
     * @throws Exception
     */
    public static void updateOrderInfo(OrderInfo orderInfo) throws Exception {
        String sql = "update order_info set name='" + orderInfo.getName() + "',phone='" + orderInfo.getPhone() +
                "',type=" + orderInfo.getType() + ",date='" + orderInfo.getDate() +
                "',time='" + orderInfo.getTime() + "',resv='" + orderInfo.getResv() + "' where id=" + orderInfo.getId();
        DB.executeUpdate(sql);
    }

    /**
     * 删除订单信息
     *
     * @param orderInfo
     * @throws Exception
     */
    public static void deleteOrderInfo(OrderInfo orderInfo) throws Exception {
        String sql = "update order_info set state=" + OrderInfoInterface.STATE_DELETED + " where id=" + orderInfo.getId();
        DB.executeUpdate(sql);
    }
}
