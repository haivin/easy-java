package com.haivin.admin.table.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haivin.admin.table.entity.EaColumnInfo;
import com.haivin.admin.table.service.EaColumnInfoService;
import com.haivin.database.controller.BaseController;
import com.haivin.util.jackson.JsonCode;
import com.haivin.util.jackson.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Title:
 * @Description:
 * @Author: zr
 * @Date: 2018/6/14 17:13
 */
@RestController
@Slf4j
@RequestMapping("/system/table")
public class TableColumnController {
    @Autowired
    EaColumnInfoService eaColumnInfoService;
    @PostMapping(value = "/{tableId}/column",consumes = "application/json")
    public JsonResult save(@RequestBody EaColumnInfo eaColumnInfo, @PathVariable("tableId") String tableId){
        JsonResult jsonResult = new JsonResult();
        eaColumnInfo.setTableInfoId(tableId);
        boolean flag = eaColumnInfoService.insertOrUpdate(eaColumnInfo);
        jsonResult.setResultCode(flag? JsonCode.SUCCESS:JsonCode.SERVER_ERROR);
        return jsonResult;
    }

    @GetMapping(value = "/{tableId}/column")
    public JsonResult list(Page<EaColumnInfo> page, EaColumnInfo eaColumnInfo, @PathVariable("tableId") String tableId){
        JsonResult jsonResult = new JsonResult();
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        eaColumnInfo.setTableInfoId(tableId);
        List<EaColumnInfo> result = eaColumnInfoService.selectList(new EntityWrapper<EaColumnInfo>(eaColumnInfo));
        PageInfo<EaColumnInfo> pageInfo = new PageInfo<>(result);
        jsonResult.setResultInfo(pageInfo);
        return jsonResult;
    }

    @GetMapping(value ="/{tableId}/column/{id}")
    public JsonResult detail(@PathVariable("id") String id){
        JsonResult jsonResult = new JsonResult();
        EaColumnInfo eaColumnInfo = eaColumnInfoService.selectById(id);
        jsonResult.setResultInfo(eaColumnInfo);
        return jsonResult;
    }
}
