package com.ling.banking.core.service;

import com.ling.banking.core.pojo.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ling.banking.core.pojo.vo.LoginVO;
import com.ling.banking.core.pojo.vo.RegisterVO;
import com.ling.banking.core.pojo.vo.UserIndexVO;
import com.ling.banking.core.pojo.vo.UserInfoVO;

/**
 * <p>
 * 用户基本信息 服务类
 * </p>
 *
 * @author ling
 * @since 2025-03-18
 */
public interface UserInfoService extends IService<UserInfo> {

    void register(RegisterVO registerVO);

    UserInfoVO login(LoginVO loginVO, String ip);

    UserIndexVO getIndexUserInfo(Long userId);

    boolean checkMobile(String mobile);
}
