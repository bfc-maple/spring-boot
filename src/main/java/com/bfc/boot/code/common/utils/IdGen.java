package com.bfc.boot.code.common.utils;

import java.util.UUID;

public class IdGen {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
