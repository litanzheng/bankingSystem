package com.ling.banking.core.controller.admin;


import com.ling.banking.core.pojo.entity.IntegralGrade;
import com.ling.banking.core.service.IntegralGradeService;
import com.ling.common.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "积分等级管理")
@RestController
@RequestMapping("/admin/core/integralGrade")
@CrossOrigin
public class AdminIntegralGradeController {

    @Resource
    private IntegralGradeService adminIntegralGradeService;

    @ApiOperation("积分等级列表")
    @GetMapping("/list")
    public List<IntegralGrade> getIntegralGrade(){
        return adminIntegralGradeService.list();
    }

    @ApiOperation(value = "根据id删除数据记录", notes="逻辑删除数据记录")
    @DeleteMapping("removeById/{id}")
    public boolean removeById(@PathVariable Long id){
        return adminIntegralGradeService.removeById(id);
    }

    @ApiOperation("新增积分等级")
    @PostMapping("/save")
    public R save(
            @ApiParam(value = "积分等级对象", required = true)
            @RequestBody IntegralGrade integralGrade){
        boolean result = adminIntegralGradeService.save(integralGrade);
        if(result){
            return R.ok().message("保存成功");
        }else{
            return R.error().message("保存失败");
        }
    }

    @ApiOperation("根据id获取积分等级")
    @GetMapping("/get/{id}")
    public R getById(
            @ApiParam(value = "数据id", required = true, example = "1")
            @PathVariable Long id){
        IntegralGrade integralGrade = adminIntegralGradeService.getById(id);
        if(integralGrade != null){
            return R.ok().data("record", integralGrade);
        }else{
            return R.error().message("数据获取失败");
        }
    }

    @ApiOperation("更新积分等级")
    @PutMapping("/update")
    public R updateById(
            @ApiParam(value = "积分等级对象", required = true)
            @RequestBody IntegralGrade integralGrade){
        boolean result = adminIntegralGradeService.updateById(integralGrade);
        if(result){
            return R.ok().message("更新成功");
        }else{
            return R.error().message("更新失败");
        }
    }
}

