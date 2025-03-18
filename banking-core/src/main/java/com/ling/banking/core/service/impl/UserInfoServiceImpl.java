package com.ling.banking.core.service.impl;

import com.ling.banking.core.pojo.entity.UserInfo;
import com.ling.banking.core.mapper.UserInfoMapper;
import com.ling.banking.core.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户基本信息 服务实现类
 * </p>
 *
 * @author ling
 * @since 2025-03-18
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
