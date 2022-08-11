package com.bfc.boot.code.common.generate;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CodeGenerate {

    String tables = "tab_bill_info";
    String modelName = "bill";


    @Test
    public void getCode(){

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/dev?characterEncoding=utf8", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("白枫朝") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .commentDate("yyyy-MM-dd HH:mm:ss")
                            .outputDir(System.getProperty("user.dir")+"\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.bfc.boot.code") // 设置父包名
                            .moduleName(modelName) // 设置父包模块名
                            .entity("entity")
                            .service("service")
                            .serviceImpl("service.impl")
                            .xml("xml")
                            .controller("controller")
                            .mapper("mapper")
                            // 设置Xml生成路径
                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir")+"\\src\\main\\resources\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables) // 设置需要生成的表名
                            // 设置过滤表前缀
                            .addTablePrefix("tab_", "c_")
                            .serviceBuilder().formatServiceFileName("I%sService").formatServiceImplFileName("%sServiceImpl")
                            .entityBuilder().enableLombok().enableTableFieldAnnotation()
                            .controllerBuilder().formatFileName("%sController").enableRestStyle()
                            .mapperBuilder().formatMapperFileName("%sMapper").formatXmlFileName("%sMapper")
                            .enableBaseColumnList().enableBaseResultMap().enableMapperAnnotation()
                    ;

                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    // 处理 all 情况
    protected List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
