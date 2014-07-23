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
 * 修改信息Action
 *
 * @author Gxx
 * @module oa
 * @datetime 14-4-18 15:22
 */
public class ExportByMonthAction extends BaseAction {
    /**
     * 年
     */
    String year;
    /**
     * 月
     */
    String month;
    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("year=" + year + ",month:" + month);
        List<OrderInfo> orderInfoList = OrderInfoDao.queryOrderInfoByYearAndMonth(year, month);
        String demo = ServletActionContext.getServletContext().getRealPath("export") + "/" + "业务登记汇总.xls";
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(demo));
        HSSFSheet sheet = workbook.getSheetAt(0);
        //设置月份
        sheet.getRow(5).getCell(1).setCellValue(year + "-" + month);
        //填上数据
        for(int i=0;i<orderInfoList.size();i++){
            OrderInfo orderInfo = orderInfoList.get(i);
            HSSFRow tempRow = sheet.createRow(8 + i);//过滤掉第一行抬头
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
            tempRow.getCell(6).setCellValue("陈苏萍");
        }
        String nowDateTime = DateUtil.getDateTime(new Date());
        String exportFile = ServletActionContext.getServletContext().getRealPath("export") + "/" + nowDateTime + ".xls";
        FileOutputStream fOut = new FileOutputStream(exportFile);
        workbook.write(fOut);
        fOut.flush();
        fOut.close();
        logger.info("按月导出成功！exportFile=" + exportFile);

        //返回信息
        String resp = "{isSuccess:true,message:'按月导出成功！',hasNewToken:true,token:'" +
                TokenUtil.createToken(request) + "',fileRoute:'export/" + nowDateTime + ".xls" + "'}";
        //创建操作日志
        BaseUtil.createOperateLog(getUser().getName(), OperateLogInterface.TYPE_EXPORT_ORDER_INFO,
                "按月导出" + year + "-" +  month + "订单:[" + nowDateTime + ".xls]", date, time, getIp());
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
