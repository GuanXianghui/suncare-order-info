package com.gxx.order;

import com.gxx.order.dao.OrderInfoDao;
import com.gxx.order.entities.OrderInfo;
import com.gxx.order.interfaces.OperateLogInterface;
import com.gxx.order.utils.BaseUtil;
import com.gxx.order.utils.DateUtil;
import com.gxx.order.utils.TokenUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

/**
 * �޸���ϢAction
 *
 * @author Gxx
 * @module oa
 * @datetime 14-4-18 15:22
 */
public class ExportByMonthAction extends BaseAction {
    /**
     * ��
     */
    String year;
    /**
     * ��
     */
    String month;
    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("year=" + year + ",month:" + month);
        List<OrderInfo> orderInfoList = OrderInfoDao.queryOrderInfoByYearAndMonth(year, month);
        String demo = ServletActionContext.getServletContext().getRealPath("export") + "/" + "ҵ��Ǽǻ���.xls";
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(demo));
        HSSFSheet sheet = workbook.getSheetAt(0);
        //�����·�
        sheet.getRow(5).getCell(1).setCellValue(year + "-" + month);
        //��������
        for(int i=0;i<orderInfoList.size();i++){
            OrderInfo orderInfo = orderInfoList.get(i);
            HSSFRow tempRow = sheet.createRow(8 + i);//���˵���һ��̧ͷ
            tempRow.createCell(0);
            tempRow.getCell(0).setCellValue(i + 1);
            tempRow.createCell(1);
            tempRow.getCell(1).setCellValue(DateUtil.getLongDateTime(DateUtil.getDateTime(orderInfo.getDate() + orderInfo.getTime())));
            tempRow.createCell(2);
            tempRow.getCell(2).setCellValue(orderInfo.getName());
            tempRow.createCell(3);
            tempRow.getCell(3).setCellValue(orderInfo.getPhone());
            tempRow.createCell(4);
            tempRow.getCell(4).setCellValue(orderInfo.getTypeDesc());
            tempRow.createCell(5);
            tempRow.getCell(5).setCellValue(orderInfo.getResv());
            tempRow.createCell(6);
            tempRow.getCell(6).setCellValue("����Ƽ");
        }
        String nowDateTime = DateUtil.getDateTime(new Date());
        String exportFile = ServletActionContext.getServletContext().getRealPath("export") + "/" + nowDateTime + ".xls";
        FileOutputStream fOut = new FileOutputStream(exportFile);
        workbook.write(fOut);
        fOut.flush();
        fOut.close();
        logger.info("���µ����ɹ���exportFile=" + exportFile);

        //������Ϣ
        String resp = "{isSuccess:true,message:'���µ����ɹ���',hasNewToken:true,token:'" +
                TokenUtil.createToken(request) + "',fileRoute:'export/" + nowDateTime + ".xls" + "'}";
        //����������־
        BaseUtil.createOperateLog(getUser().getName(), OperateLogInterface.TYPE_EXPORT_ORDER_INFO,
                "���µ���" + year + "-" +  month + "����:[" + nowDateTime + ".xls]", date, time, getIp());
        write(resp);
        return null;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
