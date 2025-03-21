package com.ling.banking.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ling.banking.core.enums.BorrowerStatusEnum;
import com.ling.banking.core.mapper.BorrowerAttachMapper;
import com.ling.banking.core.mapper.UserInfoMapper;
import com.ling.banking.core.mapper.UserIntegralMapper;
import com.ling.banking.core.pojo.entity.Borrower;
import com.ling.banking.core.mapper.BorrowerMapper;
import com.ling.banking.core.pojo.entity.BorrowerAttach;
import com.ling.banking.core.pojo.entity.UserInfo;
import com.ling.banking.core.pojo.vo.BorrowerVO;
import com.ling.banking.core.service.BorrowerAttachService;
import com.ling.banking.core.service.BorrowerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ling.banking.core.service.DictService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 借款人 服务实现类
 * </p>
 *
 * @author ling
 * @since 2025-03-18
 */
@Service
public class BorrowerServiceImpl extends ServiceImpl<BorrowerMapper, Borrower> implements BorrowerService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private BorrowerAttachMapper borrowerAttachMapper;

    @Resource
    private BorrowerAttachService borrowerAttachService;

    @Resource
    private DictService dictService;

    @Resource
    private UserIntegralMapper userIntegralMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveBorrowerVOByUserId(BorrowerVO borrowerVO, Long userId) {
        //获取用户基本信息
        UserInfo userInfo = userInfoMapper.selectById(userId);

        //保存借款人信息
        Borrower borrower = new Borrower();
        BeanUtils.copyProperties(borrowerVO, borrower);
        borrower.setUserId(userId);
        borrower.setName(userInfo.getName());
        borrower.setIdCard(userInfo.getIdCard());
        borrower.setMobile(userInfo.getMobile());
        borrower.setStatus(BorrowerStatusEnum.AUTH_RUN.getStatus()); //认证中
        baseMapper.insert(borrower);

        //保存附件
        List<BorrowerAttach> borrowerAttachList = borrowerVO.getBorrowerAttachList();
        borrowerAttachList.forEach(borrowerAttach -> {
            borrowerAttach.setBorrowerId(borrower.getId());
            borrowerAttachMapper.insert(borrowerAttach);
        });
        //更新userInfo中的借款人认证状态
        userInfo.setBorrowAuthStatus(BorrowerStatusEnum.AUTH_RUN.getStatus());
        userInfoMapper.updateById(userInfo);
    }

    @Override
    public Integer getStatusByUserId(Long userId) {
        QueryWrapper<Borrower> borrowerQueryWrapper = new QueryWrapper<>();
        borrowerQueryWrapper.select("status").eq("user_id", userId);
        List<Object> objects = baseMapper.selectObjs(borrowerQueryWrapper);
        if(objects.size() == 0){
            return BorrowerStatusEnum.NO_AUTH.getStatus();
        }
        Integer status = (Integer)objects.get(0);
        return status;
    }
}
