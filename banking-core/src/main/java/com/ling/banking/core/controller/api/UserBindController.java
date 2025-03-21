package com.ling.banking.core.controller.api;


import com.alibaba.fastjson.JSON;
import com.ling.banking.base.util.JwtUtils;
import com.ling.banking.core.hfb.RequestHelper;
import com.ling.banking.core.pojo.vo.UserBindVO;
import com.ling.banking.core.service.UserBindService;
import com.ling.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 用户绑定表 前端控制器
 * </p>
 *
 * @author lzx
 * @since 2021-02-20
 */
@Api(tags = "会员账号绑定")
@RestController
@RequestMapping("/api/core/userBind")
@Slf4j
public class UserBindController {

    @Resource
    private UserBindService userBindService;

    @ApiOperation("账户绑定提交数据")
    @PostMapping("/auth/bind")
    public Result bind(@RequestBody UserBindVO userBindVO, HttpServletRequest request){

        //从header中获取token，并对token进行校验，确保用户已登录，并从token中提取userId
        String token = request.getHeader("token");
        Long userId = JwtUtils.getUserId(token);

        //根据userId做账户绑定，生成一个动态表单的字符串
        String formStr = userBindService.commitBindUser(userBindVO, userId);
        return Result.ok().data("formStr", formStr);
    }

    @ApiOperation("账户绑定异步回调")
    @PostMapping("/notify")
    public String notify(HttpServletRequest request){

        //银行向金融借款平台发起回调请求时携带的参数
        Map<String, Object> paramMap = RequestHelper.switchMap(request.getParameterMap());
        log.info("账户绑定异步回调接收的参数如下：" + JSON.toJSONString(paramMap));
        //验签
        if(!RequestHelper.isSignEquals(paramMap)){
            log.error("用户账号绑定异步回调签名验证错误：" + JSON.toJSONString(paramMap));
            return "fail";
        }
        log.info("验签成功！开始账户绑定");
        userBindService.notify(paramMap);
        return "success";
    }
}

