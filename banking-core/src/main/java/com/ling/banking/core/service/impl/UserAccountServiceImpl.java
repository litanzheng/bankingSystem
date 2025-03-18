package com.ling.banking.core.service.impl;

import com.ling.banking.core.pojo.entity.UserAccount;
import com.ling.banking.core.mapper.UserAccountMapper;
import com.ling.banking.core.service.UserAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账户 服务实现类
 * </p>
 *
 * @author ling
 * @since 2025-03-18
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

}
