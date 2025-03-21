package com.ling.banking.core.mapper;

import com.ling.banking.core.pojo.dto.ExcelDictDTO;
import com.ling.banking.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author ling
 * @since 2025-03-18
 */
public interface DictMapper extends BaseMapper<Dict> {


    void insertBatch(List<ExcelDictDTO> list);
}
