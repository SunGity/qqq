package com.cn.ntl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.cn.ntl.mapper"})
public class NtlApplication {

    public static void main(String[] args) {
        SpringApplication.run(NtlApplication.class, args);
    }

}
