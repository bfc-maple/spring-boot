package com.bfc.boot.code.common.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("通用返回信息")
public class ResultModel {

    private String code;
    private String msg;
    private Object data;
}
