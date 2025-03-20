package com.ling.banking.core.controller.admin;

import com.alibaba.excel.EasyExcel;
import com.ling.banking.core.pojo.dto.ExcelDictDTO;
import com.ling.banking.core.pojo.entity.Dict;
import com.ling.banking.core.service.DictService;
import com.ling.common.exception.BusinessException;
import com.ling.common.result.ResponseEnum;
import com.ling.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2021-02-20
 */
@Api(tags = "数据字典管理")
@RestController
@RequestMapping("/admin/core/dict")
@Slf4j
//@CrossOrigin
public class AdminDictController {

    @Resource
    DictService dictService;

    @ApiOperation("Excel数据的批量导入")
    @PostMapping("/import")
    public Result batchImport(
            @ApiParam(value = "Excel数据字典文件", required = true)
            @RequestParam("file") MultipartFile file){
        try {
            InputStream inputStream = file.getInputStream();
            dictService.importData(inputStream);
            return Result.ok().message("数据字典数据批量导入成功");
        } catch (Exception e) {
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR, e);
        }
    }
    @ApiOperation("Excel数据的导出")
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + URLEncoder.encode("data.xlsx", "UTF-8"));
        // 这里URLEncoder.encode可以防止中文乱码
        String fileName = URLEncoder.encode("mydict", "UTF-8").replaceAll("\\+", "%20");
        EasyExcel.write(response.getOutputStream(), ExcelDictDTO.class).sheet("数据字典").doWrite(dictService.listDictData());
    }

    /*树形数据的两种加载方案
  方案一：非延迟加载
  需要后端返回的数据结构中包含嵌套数据，并且嵌套数据放在children属性中


  方案二：延迟加载
  不需要后端返回数据中包含嵌套数据，并且要定义布尔属性hasChildren，表示当前节点是否包含子数据
  如果hasChildren为true，就表示当前节点包含子数据
  如果hasChildren为false，就表示当前节点不包含子数据
  如果当前节点包含子数据，那么点击当前节点的时候，就需要通过load方法加载子数据*/
    @ApiOperation("根据上级id获取子节点数据列表")
    @GetMapping("/listByParentId/{parentId}")
    public Result listByParentId(
            @ApiParam(value = "上级节点id", required = true)
            @PathVariable Long parentId){
        List<Dict> dictList = dictService.listByParentId(parentId);
        return Result.ok().data("list", dictList);
    }

}

