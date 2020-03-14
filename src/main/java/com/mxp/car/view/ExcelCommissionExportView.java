package com.mxp.car.view;

import com.mxp.car.util.Utils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.document.AbstractXlsxStreamingView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @author DongXing
 * @user Liang
 * @time 2020/3/14 16:00
 */
@Service
public class ExcelCommissionExportView extends AbstractXlsxStreamingView {

    @Override
    public SXSSFWorkbook createWorkbook(Map<String, Object> model, HttpServletRequest request) {
        SXSSFWorkbook workbook = super.createWorkbook(model, request);
        SXSSFSheet sheet = workbook.createSheet();
        setFirstLineRow(sheet);
        List<Map> commissionData = Utils.CarUtil.objectcastList(model.get("commissionData"), Map.class);
        for (int i = 0; i < commissionData.size(); i++) {
            Map<String, Object> map = commissionData.get(i);
            setLineRow(sheet, map, i + 1);
        }
        return workbook;
    }

    @Override
    public void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" +
                new String("修理订单.xlsx".getBytes("gbk"), StandardCharsets.ISO_8859_1));
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    private void setLineRow(SXSSFSheet sheet, Map<String, Object> map, Integer index) {
        SXSSFRow row1 = sheet.createRow(index);
        row1.createCell(0).setCellValue(String.valueOf(map.get("commissionId")));
        row1.createCell(1).setCellValue(String.valueOf(map.get("userId")));
        row1.createCell(2).setCellValue(String.valueOf(map.get("principal")));
        row1.createCell(3).setCellValue(String.valueOf(map.get("contact")));
        row1.createCell(4).setCellValue(String.valueOf(map.get("phone")));
        row1.createCell(5).setCellValue(String.valueOf(map.get("policyNum")));
        row1.createCell(6).setCellValue(String.valueOf(map.get("company")));
        row1.createCell(7).setCellValue(String.valueOf(map.get("insured")));
        row1.createCell(8).setCellValue(String.valueOf(map.get("riskDate")));
        row1.createCell(9).setCellValue(String.valueOf(map.get("reportNum")));
        row1.createCell(10).setCellValue(String.valueOf(map.get("damageAmount")));
        row1.createCell(11).setCellValue(String.valueOf(map.get("discount")));
        row1.createCell(12).setCellValue(String.valueOf(map.get("salesman")));
        row1.createCell(13).setCellValue(String.valueOf(map.get("paymentDate")));
        row1.createCell(14).setCellValue(String.valueOf(map.get("checkoutDate")));
        row1.createCell(15).setCellValue(String.valueOf(map.get("reminderTime")));
        row1.createCell(16).setCellValue(String.valueOf(map.get("createTime")));
        row1.createCell(17).setCellValue(String.valueOf(map.get("reminder")));
        row1.createCell(18).setCellValue(String.valueOf(map.get("remarks")));
        row1.createCell(19).setCellValue(String.valueOf(map.get("direct")));
        row1.createCell(20).setCellValue(String.valueOf(map.get("moneyBack")));
        row1.createCell(21).setCellValue(String.valueOf(map.get("carNum")));
        row1.createCell(22).setCellValue(String.valueOf(map.get("carType")));

    }

    private void setFirstLineRow(SXSSFSheet sheet) {
        SXSSFRow row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("委托id");
        row1.createCell(1).setCellValue("归属修理厂");
        row1.createCell(2).setCellValue("委托人");
        row1.createCell(3).setCellValue("联系人");
        row1.createCell(4).setCellValue("电话");
        row1.createCell(5).setCellValue("保单号");
        row1.createCell(6).setCellValue("保险公司");
        row1.createCell(7).setCellValue("被保险人");
        row1.createCell(8).setCellValue("出险时间");
        row1.createCell(9).setCellValue("报案号");
        row1.createCell(10).setCellValue("定损金额");
        row1.createCell(11).setCellValue("折扣");
        row1.createCell(12).setCellValue("业务员");
        row1.createCell(13).setCellValue("回款时间");
        row1.createCell(14).setCellValue("结账时间");
        row1.createCell(15).setCellValue("提醒时间");
        row1.createCell(16).setCellValue("提醒内容");
        row1.createCell(17).setCellValue("备注");
        row1.createCell(18).setCellValue("创建时间");
        row1.createCell(19).setCellValue("直赔");
        row1.createCell(20).setCellValue("回款金额");
        row1.createCell(21).setCellValue("车牌");
        row1.createCell(22).setCellValue("车辆类型");
    }
}
