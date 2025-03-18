package com.ling.banking.core.service;

import com.ling.banking.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author ling
 * @since 2025-03-18
 */
public interface DictService extends IService<Dict> {

    @Transactional
    void importData(InputStream inputStream);

    List<Dict> listByParentId(Long parentId);

    List listDictData();
}
