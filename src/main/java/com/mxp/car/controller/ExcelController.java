package com.mxp.car.controller;

import com.github.pagehelper.Page;
import com.mxp.car.model.Commission;
import com.mxp.car.service.CommissionService;
import com.mxp.car.view.ExcelCommissionExportView;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author DongXing
 * @user Liang
 * @time 2020/3/14 15:34
 */
@Log4j2
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private CommissionService commissionService;

    @Autowired
    private ExcelCommissionExportView excelCommissionExportView;

    @GetMapping("/export/commission/{pageNum}/{pageSize}")
    public void exportExcelByCommission(@PathVariable int pageNum,
                                        @PathVariable int pageSize,
                                        @RequestParam Map<String, Object> param,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
        Page<Commission> listByPage = commissionService.findListByPage(pageNum, pageSize, param);
        param.put("commissionData", listByPage.getResult());
        SXSSFWorkbook workbook = excelCommissionExportView.createWorkbook(param, request);
        try {
            excelCommissionExportView.buildExcelDocument(null, workbook, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
