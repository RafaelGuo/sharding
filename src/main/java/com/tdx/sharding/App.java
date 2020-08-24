package com.tdx.sharding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude= {DruidDataSourceAutoConfigure.class})
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
//@MapperScan("com.tdx.sharding.mapper")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
}
