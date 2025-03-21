package com.ling.banking.core.service;

import com.ling.banking.core.pojo.entity.UserBind;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ling.banking.core.pojo.vo.UserBindVO;

import java.util.Map;

/**
 * <p>
 * 用户绑定表 服务类
 * </p>
 *
 * @author ling
 * @since 2025-03-18
 */
public interface UserBindService extends IService<UserBind> {

    String commitBindUser(UserBindVO userBindVO, Long userId);

    void notify(Map<String, Object> paramMap);

    String getBindCodeByUserId(Long userId);
}
