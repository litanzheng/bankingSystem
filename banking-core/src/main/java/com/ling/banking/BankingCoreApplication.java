package com.ling.banking;

/**
 * @author ：lizx
 * @date ：Created in 2025/3/18 17:40
 * @company: private
 * @description：
 * @modified By：
 * @version: $
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.ling.banking"})
public class BankingCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankingCoreApplication.class, args);
    }
}