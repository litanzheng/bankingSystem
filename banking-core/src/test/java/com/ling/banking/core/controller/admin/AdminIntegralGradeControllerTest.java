package com.ling.banking.core.controller.admin;

import com.ling.banking.core.pojo.entity.IntegralGrade;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ：lizx
 * @date ：Created in 2025/3/18 18:31
 * @company: private
 * @description：
 * @modified By：
 * @version: $
 */
@SpringBootTest
@Slf4j
class AdminIntegralGradeControllerTest {

    @Resource
    AdminIntegralGradeController adminIntegralGradeController;
    @Test
    void getIntegralGrade() {
        List<IntegralGrade> integralGrade = adminIntegralGradeController.getIntegralGrade();
        log.info("数据{}",integralGrade);
    }

    @Test
    void removeById() {
        adminIntegralGradeController.removeById(1L);
    }
}