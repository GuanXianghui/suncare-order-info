package com.gxx.order.utils;

import com.gxx.order.interfaces.BaseInterface;
import org.apache.log4j.Logger;

import java.net.URLEncoder;

/**
 * ���ŷ��񹤾���
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-30 12:09
 */
public class SMSUtil implements BaseInterface {
    /**
     * ��־������
     */
    static Logger logger = Logger.getLogger(SMSUtil.class);

    /**
     * ���Ͷ���
     * @param phone
     * @param content
     * @return
     * @throws Exception
     */
    public static int sendSMS(String phone, String content) throws Exception {
        String smsUid = PropertyUtil.getInstance().getProperty(SMS_UID);
        String smsKey = PropertyUtil.getInstance().getProperty(SMS_KEY);
        String url = "http://gbk.sms.webchinese.cn/?Uid=" + URLEncoder.encode(smsUid, "GBK") + "&Key=" +
                smsKey + "&smsMob=" + phone + "&smsText=" + URLEncoder.encode(content, "GBK");
        String result = HttpClientUtils.postUrl(url, "GBK", "GBK");
        return Integer.parseInt(result);
    }

    /**
     * ���ݽ���жϷ����Ƿ�ɹ�
     * @param result
     * @return
     */
    public static boolean checkSuccess(int result){
        if(result > 0){
            return true;
        }
        return false;
    }
}
