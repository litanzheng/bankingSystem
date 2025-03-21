package com.ling.banking.core.controller.api;


import com.ling.banking.base.util.JwtUtils;
import com.ling.banking.core.pojo.vo.BorrowerVO;
import com.ling.banking.core.service.BorrowerService;
import com.ling.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 借款人 前端控制器
 * </p>
 *
 * @author Jiangw
 * @since 2021-03-31
 */
@Api(tags = "借款人")
@RestController
@RequestMapping("/api/core/borrower")
@Slf4j
public class BorrowerController {


    @Resource
    private BorrowerService borrowerService;

    @ApiOperation("保存借款人信息")
    @PostMapping("/auth/save")
    public Result save(@RequestBody BorrowerVO borrowerVO, HttpServletRequest request) {
        String token = request.getHeader("token");
        Long userId = JwtUtils.getUserId(token);
        borrowerService.saveBorrowerVOByUserId(borrowerVO, userId);
        return Result.ok().message("信息提交成功");
    }

    @ApiOperation("获取借款人认证状态")
    @GetMapping("/auth/getBorrowerStatus")
    public Result getBorrowerStatus(HttpServletRequest request){

        String token = request.getHeader("token");
        Long userId = JwtUtils.getUserId(token);
        Integer status = borrowerService.getStatusByUserId(userId);
        return Result.ok().data("borrowerStatus", status);
    }
}



