package com.ling.banking.core.service;

import com.ling.banking.core.pojo.entity.Borrower;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ling.banking.core.pojo.vo.BorrowerVO;

/**
 * <p>
 * 借款人 服务类
 * </p>
 *
 * @author ling
 * @since 2025-03-18
 */
public interface BorrowerService extends IService<Borrower> {

    void saveBorrowerVOByUserId(BorrowerVO borrowerVO, Long userId);

    Integer getStatusByUserId(Long userId);
}
