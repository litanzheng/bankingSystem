package com.ling.banking.core.controller.admin;


import com.ling.banking.core.pojo.entity.IntegralGrade;
import com.ling.banking.core.service.IntegralGradeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 积分等级表 前端控制器
 * </p>
 *
 * @author ling
 * @since 2025-03-18
 */
@RestController
@RequestMapping("/adminIntegralGrade")
@CrossOrigin
public class AdminIntegralGradeController {

    @Resource
    private IntegralGradeService adminIntegralGradeService;

    @GetMapping("/list")
    public List<IntegralGrade> getIntegralGrade(){
        return adminIntegralGradeService.list();
    }

    @GetMapping("removeById/{id}")
    public boolean removeById(@PathVariable Long id){
        return adminIntegralGradeService.removeById(id);
    }
}

