package com.ling.banking.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.ling"})
@EnableFeignClients
public class ServiceSmsApplication {

    public static void main(String[] args) {
        try{
            SpringApplication.run(ServiceSmsApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}