package com.ling.banking.sms.controller.api;

import com.ling.banking.sms.client.CoreUserInfoClient;
import com.ling.banking.sms.service.SmsService;
import com.ling.banking.sms.util.SmsProperties;
import com.ling.common.exception.Assert;
import com.ling.common.result.ResponseEnum;
import com.ling.common.result.Result;
import com.ling.common.util.RandomUtils;
import com.ling.common.util.RegexValidateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/sms")
@Api(tags = "短信管理")
//@CrossOrigin //跨域
@Slf4j
public class ApiSmsController {

    @Resource
    private SmsService smsService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private CoreUserInfoClient coreUserInfoClient;

    @ApiOperation("获取验证码")
    @GetMapping("/send/{mobile}")
    public Result send(
            @ApiParam(value = "手机号", required = true)
            @PathVariable String mobile){

        //校验手机号吗不能为空
        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);
        //是否是合法的手机号码
        Assert.isTrue(RegexValidateUtils.checkCellphone(mobile), ResponseEnum.MOBILE_ERROR);

        //判断手机号是否已经注册
        boolean result = coreUserInfoClient.checkMobile(mobile);
        log.info("result = " + result);
        Assert.isTrue(result == false, ResponseEnum.MOBILE_EXIST_ERROR);

        String code = RandomUtils.getFourBitRandom();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", code);
        smsService.send(mobile, SmsProperties.TEMPLATE_CODE, map);

        //将验证码存入redis
        redisTemplate.opsForValue().set("banking:sms:code:" + mobile, code, 5, TimeUnit.MINUTES);

        return Result.ok().message("短信发送成功");
    }
}
