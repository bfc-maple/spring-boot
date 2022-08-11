package com.bfc.boot.code.bill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bfc.boot.code.bill.entity.BillInfo;
import com.bfc.boot.code.common.model.ResultModel;

import java.util.List;

/**
 * <p>
 * 账单表 服务类
 * </p>
 *
 * @author 白枫朝
 * @since 2022-03-02 19:10:27
 */
public interface IBillInfoService extends IService<BillInfo> {

    public ResultModel importBill(String fileName,List<List<String>> lists);

}
