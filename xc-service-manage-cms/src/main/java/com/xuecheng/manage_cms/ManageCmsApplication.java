package com.xuecheng.manage_cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


/**
 * @ClassName ManageCmsApplication
 * @Description cms后台管理服务
 * @Aothor zh
 * @Date 2020/8/30 17:38
 * @Version 1.0
 */
@SpringBootApplication
@EntityScan("com.xuecheng.framework.domain.cms") //cms实体类所在的包
@ComponentScan(basePackages = {"com.xuecheng.api","com.xuecheng.framework"}) //扫描接口所在的包
@ComponentScan(basePackages = {"com.xuecheng.manage_cms"}) //本项目下所有包
public class ManageCmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageCmsApplication.class,args);
    }
}
