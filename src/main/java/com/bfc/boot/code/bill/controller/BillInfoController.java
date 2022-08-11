package com.bfc.boot.code.bill.controller;

import com.bfc.boot.code.bill.service.IBillInfoService;
import com.bfc.boot.code.common.model.ResultModel;
import com.bfc.boot.code.common.utils.CsvImportUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * <p>
 * 账单表 前端控制器
 * </p>
 *
 * @author 白枫朝
 * @since 2022-03-02 19:10:27
 */
@RestController
@Api(tags = "账单接口")
@RequestMapping("/bill/billInfo")
public class BillInfoController {

    @Autowired
    IBillInfoService billInfoService;

    @GetMapping("/get")
    public String get(){
        return "success";
    }
    @ApiOperation("导入账单信息csv")
    @PostMapping(value = "importBill")
    public ResultModel importBill(@RequestParam("file") MultipartFile file) {
        String filename = file.getOriginalFilename();
        // 使用CSV工具类，生成file文件
        File csvFile = CsvImportUtil.uploadFile(file);
        // 将文件内容解析，存入List容器，List<String>为每一行内容的集合，6为CSV文件每行的总列数
        List<List<String>> lists = CsvImportUtil.readCSV(csvFile.getPath());

        ResultModel result = billInfoService.importBill(filename,lists);

        return result;
    }

}
