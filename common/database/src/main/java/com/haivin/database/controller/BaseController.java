package com.haivin.database.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haivin.util.jackson.JsonCode;
import com.haivin.util.jackson.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Title:
 * @Description:
 * @Author: zr
 * @Date: 2018/1/29 23:36
 */
public class BaseController<T> {

    @Autowired
    IService<T> iService;

    @PostMapping(value="",consumes = "application/json")
    public JsonResult save(@RequestBody T t){
        JsonResult jsonResult = new JsonResult();
        boolean flag = iService.insertOrUpdate(t);
        jsonResult.setResultCode(flag? JsonCode.SUCCESS:JsonCode.SERVER_ERROR);
        return jsonResult;
    }

    @GetMapping(value="")
    public JsonResult list(Page<T> page,T t){
        JsonResult jsonResult = new JsonResult();
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<T> result = iService.selectList(new EntityWrapper<>(t));
        PageInfo<T> pageInfo = new PageInfo<T>(result);
        jsonResult.setResultInfo(pageInfo);
        return jsonResult;
    }

    @GetMapping(value="/{id}")
    public JsonResult detail(@PathVariable("id") String id){
        JsonResult jsonResult = new JsonResult();
        T t = iService.selectById(id);
        jsonResult.setResultInfo(t);
        return jsonResult;
    }
}
