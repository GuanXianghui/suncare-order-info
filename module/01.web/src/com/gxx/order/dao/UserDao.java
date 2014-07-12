package com.gxx.order.dao;

import com.gxx.order.entities.User;
import com.gxx.order.interfaces.BaseInterface;
import com.gxx.order.utils.BaseUtil;
import com.gxx.order.utils.PropertyUtil;
import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户实体操作类
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 20:22
 */
public class UserDao {
    /**
     * 查询所有用户
     *
     * @return
     * @throws Exception
     */
    public static List<User> queryAllUsers() throws Exception {
        List<User> list = new ArrayList<User>();
        String sql = "SELECT id,name,password,phone,validate_code FROM user order by id";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                String validateCode = rs.getString("validate_code");
                User user = new User(id, name, password, phone, validateCode);
                list.add(user);
            }
            return list;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 根据名字模糊查询用户
     *
     * @param name
     * @param pageNum
     * @return
     * @throws Exception
     */
    public static List<User> queryUsersByLikeName(String name, int pageNum) throws Exception {
        //用户列表每页大小
        int pageSize = Integer.parseInt(PropertyUtil.getInstance().getProperty(BaseInterface.USER_PAGE_SIZE));
        //用户集合
        List<User> list = new ArrayList<User>();
        //sql语句
        String sql = "SELECT id,name,password,phone,validate_code FROM user";
        //如果name非空带上为条件
        if(StringUtils.isNotBlank(name)){
            sql += " WHERE name LIKE '%" + name + "%'";
        }
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
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                String validateCode = rs.getString("validate_code");
                User user = new User(id, newName, password, phone, validateCode);
                list.add(user);
            }
            return list;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 根据名字模糊查询用户数量
     *
     * @param name
     * @return
     * @throws Exception
     */
    public static int countUsersByLikeName(String name) throws Exception {
        //结果
        int count = 0;
        //sql语句
        String sql = "SELECT count(1) count_num FROM user";
        //如果name非空带上为条件
        if(StringUtils.isNotBlank(name)){
            sql += " WHERE name LIKE '%" + name + "%'";
        }
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
    public static User getUserById(int id) throws Exception {
        String sql = "SELECT id,name,password,phone,validate_code FROM user WHERE id=" + id + " order by id";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                String name = rs.getString("name");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                String validateCode = rs.getString("validate_code");
                User user = new User(id, name, password, phone, validateCode);
                return user;
            }
            return null;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 根据姓名查用户
     *
     * @param name
     * @return
     * @throws Exception
     */
    public static User getUserByName(String name) throws Exception {
        String sql = "SELECT id,name,password,phone,validate_code FROM user WHERE name='" + name + "' order by id";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                int id = rs.getInt("id");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                String validateCode = rs.getString("validate_code");
                User user = new User(id, name, password, phone, validateCode);
                return user;
            }
            return null;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 判该名字是否已存在
     *
     * @param name
     * @return
     * @throws Exception
     */
    public static boolean isNameExist(String name) throws Exception {
        User user = getUserByName(name);
        return user != null;
    }

    /**
     * 根据手机查用户
     *
     * @param phone
     * @return
     * @throws Exception
     */
    public static User getUserByPhone(String phone) throws Exception {
        String sql = "SELECT id,name,password,phone,validate_code FROM user WHERE phone='" + phone + "' order by id";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String validateCode = rs.getString("validate_code");
                User user = new User(id, name, password, phone, validateCode);
                return user;
            }
            return null;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 判该手机号是否已绑定
     *
     * @param phone
     * @return
     * @throws Exception
     */
    public static boolean isPhoneExist(String phone) throws Exception {
        User user = getUserByPhone(phone);
        return user != null;
    }

    /**
     * 新增用户
     *
     * @param user
     * @throws Exception
     */
    public static void insertUser(User user) throws Exception {
        String sql = "insert into user" +
                "(id,name,password,phone,validate_code)" +
                "values" +
                "(null,'" + user.getName() + "','" + user.getPassword() + "','" + user.getPhone() + "','" + user.getValidateCode() + "')";
        DB.executeUpdate(sql);
    }

    /**
     * 更新用户密码，电话，验证码
     *
     * @param user
     * @throws Exception
     */
    public static void updateUser(User user) throws Exception {
        String sql = "update user set password='" + user.getPassword() + "',phone='" + user.getPhone() +
                "',validate_code='" + user.getValidateCode() + "' where id=" + user.getId();
        DB.executeUpdate(sql);
    }

    /**
     * 删除用户
     *
     * @param user
     * @throws Exception
     */
    public static void deleteUser(User user) throws Exception {
        String sql = "delete from user where id=" + user.getId();
        DB.executeUpdate(sql);
    }
}
