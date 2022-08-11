package com.bfc.boot.code.bill.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfc.boot.code.bill.entity.BillInfo;
import com.bfc.boot.code.bill.mapper.BillInfoMapper;
import com.bfc.boot.code.bill.service.IBillInfoService;
import com.bfc.boot.code.common.constant.Constants;
import com.bfc.boot.code.common.model.ResultModel;
import com.bfc.boot.code.common.utils.IdGen;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 账单表 服务实现类
 * </p>
 *
 * @author 白枫朝
 * @since 2022-03-02 19:10:27
 */
@Primary
@Service
public class BillInfoServiceImpl extends ServiceImpl<BillInfoMapper, BillInfo> implements IBillInfoService {

    @Override
    public ResultModel importBill(String fileName, List<List<String>> lists) {
        ResultModel resultModel = new ResultModel();
        if (CollectionUtil.isEmpty(lists)) {
            resultModel.setCode("201");
            resultModel.setMsg("csv解析数据为空");
            resultModel.setData("");
            return resultModel;
        }
        List<BillInfo> datas = new ArrayList<>();
        String account = "";
        String name = "";
        if (fileName.contains("微信支付账单")) {
            for (List<String> list : lists) {
                if (!CollectionUtil.isEmpty(list) && !"交易时间".equals(list.get(0).trim()) && list.size() > 8 && StringUtils.isNotBlank(list.get(8))) {
                    BillInfo data = new BillInfo();
                    data.setId(IdGen.getUUID());
                    if (list.get(0).contains("-"))
                        data.setTradeTime(DateUtil.parse(list.get(0), "yyyy-MM-dd HH:mm"));
                    else
                        data.setTradeTime(DateUtil.parse(list.get(0), "yyyy/MM/dd HH:mm"));
                    data.setTradeType(list.get(1));
                    data.setTradeMan(list.get(2));
                    data.setTradeGoods(list.get(3));
                    data.setInOrOut(list.get(4));
                    String sub = list.get(5).substring(1).replace(",", "");
                    data.setAmount(new BigDecimal(sub));
                    data.setPayWay(list.get(6));
                    data.setTradeStatus(list.get(7));
                    data.setTradeCode(list.get(8));
                    data.setMerchantCode(list.get(9));
                    data.setRemark(list.get(10));
                    data.setTradePlatform(Constants.PAY_TYPE_WX);
                    datas.add(data);
                } else {
                    if (!CollectionUtil.isEmpty(list) && list.get(0) != null && list.get(0).startsWith("微信昵称")) {
                        String s = list.get(0);
                        name = account = s.substring(s.lastIndexOf("[") + 1, s.lastIndexOf("]"));
                    }
                }
            }
        } else {
            for (List<String> list : lists) {
                if (!CollectionUtil.isEmpty(list) && !"收/支".equals(list.get(0).trim()) && list.size() > 8 && StringUtils.isNotBlank(list.get(8))) {
                    BillInfo data = new BillInfo();
                    data.setId(IdGen.getUUID());
                    data.setTradePlatform(Constants.PAY_TYPE_ZFB);
                    data.setInOrOut(list.get(0).trim());
                    data.setTradeMan(list.get(1).trim());
                    data.setTradeAccount(list.get(2).trim());
                    data.setTradeGoods(list.get(3).trim());
                    data.setPayWay(list.get(4).trim());
                    String str = list.get(5).trim();
                    data.setAmount(new BigDecimal(str));
                    data.setTradeStatus(list.get(6).trim());
                    data.setTradeType(list.get(7).trim());
                    data.setTradeCode(list.get(8).trim());
                    data.setMerchantCode(list.get(9).trim());
                    String date = list.get(10).trim();
                    data.setTradeTime(DateUtil.parse(date, "yyyy-MM-dd HH:mm:ss"));
                    datas.add(data);
                } else {
                    if (!CollectionUtil.isEmpty(list) && list.get(0) != null) {
                        if (list.get(0).startsWith("姓名")) {
                            name = list.get(0).substring(list.get(0).indexOf("：")+1).trim();
                        }
                        if (list.get(0).startsWith("支付宝账户")) {
                            account = list.get(0).substring(list.get(0).indexOf("：")+1).trim();
                        }
                    }
                }
            }
        }
        for (BillInfo bill : datas) {
            bill.setName(name);
            bill.setAccount(account);
        }
        this.saveBatch(datas);
        resultModel.setCode("200");
        resultModel.setMsg("导入成功");
        resultModel.setData("");
        return resultModel;
    }
}
