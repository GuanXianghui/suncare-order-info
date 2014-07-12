package com.gxx.order.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ���ڹ�����
 *
 * @author Gxx
 * @module oa
 * @datetime 14-4-18 11:22
 */
public class DateUtil {
    /**
     * ��־������
     */
    static Logger logger = Logger.getLogger(DateUtil.class);

    /**
     * ��ʽ��ʱ��ΪDate����
     *
     * @param srcDate    ԭʱ��
     * @param srcPattern ԭģʽ
     * @return ��ʽ����ʱ��
     */
    public static Date parseDateTime(String srcDate, String srcPattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(srcPattern);
            return sdf.parse(srcDate);
        } catch (Exception e) {
            logger.error("�쳣����~", e);
            return null;
        }
    }


    /**
     * ��ʽ��ʱ��Ϊ�ַ�������
     *
     * @param srcDate    ԭʱ��
     * @param srcPattern ԭģʽ
     * @param dstPattern Ŀ��ģʽ
     * @return ��ʽ����ʱ��
     */
    public static String formatDateTime(Object srcDate, String srcPattern, String dstPattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(srcPattern == null ? "yyyyMMdd" : srcPattern);
            Date _date = null;
            if (srcDate == null) {
                return "";
            }
            if (srcDate instanceof String) {
                _date = sdf.parse(srcDate.toString());
            } else if (srcDate instanceof Date) {
                _date = (Date) srcDate;
            }
            sdf = new SimpleDateFormat(dstPattern);
            return sdf.format(_date);
        } catch (Exception e) {
            logger.error("�쳣����~", e);
            return null;
        }
    }

    /**
     * �������ַ���ת��ΪDate
     *
     * @param date �����ַ���
     * @return ����
     */
    public static Date getDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        if (StringUtils.isBlank(date)) {
            return null;
        }
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            logger.error("�쳣����~", e);
            return null;
        }
    }

    /**
     * ��dateTimeת����date
     *
     * @param dateTime ʱ��yyyyMMddHHmmss
     * @return Date
     */
    public static Date getDateTime(String dateTime) {
        if (StringUtils.isBlank(dateTime)) {
            return null;
        }
        SimpleDateFormat sdf;
        if (dateTime.indexOf("-") > 0) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        }
        try {
            sdf.setLenient(false);
            return sdf.parse(dateTime);
        } catch (ParseException e) {
            logger.error("�쳣����~", e);
            return null;
        }
    }

    /**
     * ��ʱ��ת����date
     *
     * @param time �ַ���ʽʱ��HHmmss
     * @return DATE
     */
    public static Date getTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        if (StringUtils.isBlank(time)) {
            return null;
        }
        try {
            return sdf.parse(time);
        } catch (ParseException e) {
            logger.error("�쳣����~", e);
            return null;
        }
    }

    /**
     * ������ת���ɱ�׼��ʽyyyy-mm-dd hh:mm:ss
     *
     * @param dateTime ʱ��
     * @return ��׼��ʽ�ַ���
     */
    public static String getDefaultDateTime(Date dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.format(dateTime);
        } catch (Exception e) {
            logger.error("�쳣����~", e);
            return null;
        }
    }


    /**
     * ������ת�ɳ��ַ���
     *
     * @param dateTime dateTime�����ַ���
     * @return ֧���������������ַ���
     */
    public static String getDateTime(Date dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            return sdf.format(dateTime);
        } catch (Exception e) {
            logger.error("�쳣����~", e);
            return null;
        }
    }

    /**
     * �����ں�ʱ��ת����date
     *
     * @param date date�����ַ���
     * @param time time�����ַ���
     * @return ֧���������������ַ���
     */
    public static Date getDateTime(String date, String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            return sdf.parse(date + time);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * ������ת��Ϊ�����ַ���
     *
     * @param date ����
     * @return �����ַ���
     */
    public static String getDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        if (date == null) {
            return "";
        }
        try {
            return sdf.format(date);
        } catch (Exception e) {
            logger.error("�쳣����~", e);
            return "";
        }
    }

    /**
     * ������ת��Ϊ���ַ�������ʽ��yyMMdd
     *
     * @param date ����
     * @return �ַ���
     */
    public static String getShortDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        if (date == null) {
            return null;
        }
        try {
            return sdf.format(date);
        } catch (Exception e) {
            logger.error("�쳣����~", e);
            return null;
        }
    }

    /**
     * ������ת��Ϊ���ַ�������ʽ��yyyy-MM-dd
     *
     * @param date ����
     * @return �ַ���
     */
    public static String getLongDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (date == null) {
            return null;
        }
        try {
            return sdf.format(date);
        } catch (Exception e) {
            logger.error("�쳣����~", e);
            return null;
        }
    }

    /**
     * ��ʱ��ת��Ϊ�����ַ���
     *
     * @param date ʱ��
     * @return �����ַ���
     */
    public static String getTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        if (date == null) {
            return "";
        }
        try {
            return sdf.format(date);
        } catch (Exception e) {
            logger.error("�쳣����~", e);
            return "";
        }
    }

    /**
     * ��ʱ��ת��Ϊ�����ַ���
     *
     * @param date ʱ��
     * @return �����ַ���
     */
    public static String getLongTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        if (date == null) {
            return "";
        }
        try {
            return sdf.format(date);
        } catch (Exception e) {
            logger.error("�쳣����~", e);
            return "";
        }
    }

    /**
     * �������ַ���ת��ΪDate��Ȼʱ��
     *
     * @param date �����ַ���
     * @return ��Ȼʱ��
     */
    public static String getFreeTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSSS");
        if (date == null) {
            return null;
        }
        try {
            return sdf.format(date);
        } catch (Exception e) {
            logger.error("�쳣����~", e);
            return null;
        }
    }

    /**
     * �������ַ���ת��ΪDate��Ȼ���ڵ���
     *
     * @param date �����ַ���
     * @return ��Ȼ���ڵ���
     */
    public static String getFreeDay(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        if (date == null) {
            return null;
        }
        try {
            return sdf.format(date);
        } catch (Exception e) {
            logger.error("�쳣����~", e);
            return null;
        }
    }

    /**
     * ת�����й���������
     *
     * @param date
     * @return
     */
    public static String getCNDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");
        if (date == null) {
            return StringUtils.EMPTY;
        }
        try {
            return sdf.format(date);
        } catch (Exception e) {
            logger.error("�쳣����~", e);
            return StringUtils.EMPTY;
        }
    }

    /**
     * ת�����й���������ʱ��
     *
     * @param date
     * @return
     */
    public static String getCNDateTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd�� HHʱmm��ss��");
        if (date == null) {
            return StringUtils.EMPTY;
        }
        try {
            return sdf.format(date);
        } catch (Exception e) {
            logger.error("�쳣����~", e);
            return StringUtils.EMPTY;
        }
    }

    /**
     * �õ����ڵ�ǰһ��
     *
     * @param date
     * @return
     */
    public static Date getYesterday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        Date yesterday = calendar.getTime();
        return yesterday;
    }

    /**
     * �õ���ǰ����
     *
     * @return
     */
    public static String getNowDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }

    /**
     * �õ���ǰ����
     *
     * @return
     */
    public static String getNowTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        return sdf.format(new Date());
    }
}