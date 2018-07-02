package com.haivin.admin.table.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.haivin.admin.table.service.EaTableInfoService;
import com.haivin.database.activerecord.EaModel;
import com.haivin.util.ApplicationContextUtil;
import com.haivin.util.idcenter.IdWorker;
import com.haivin.util.jackson.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.haivin.database.controller.BaseController;
import com.haivin.admin.table.entity.EaTableInfo;
import java.util.Map;

/**
 * <p>
 * 系统表信息 前端控制器
 * </p>
 *
 * @author zhouran
 * @since 2018-06-08
 */
@RestController
@Slf4j
@RequestMapping("/system/table")
public class EaTableInfoController extends BaseController<EaTableInfo> {

    @Autowired
    EaTableInfoService eaTableInfoService;

    @RequestMapping("/saveInfo")
    public JsonResult saveInfo(){
        JsonResult jsonResult = new JsonResult();
        ApplicationContext ctx = ApplicationContextUtil.getContext();
        String[] beanNames = ctx.getBeanNamesForType(EaModel.class);

        int columnCount = beanNames.length;
        for (String bn : beanNames) {
            EaTableInfo eaTableInfo = new EaTableInfo();
            EaModel model = ctx.getBean(bn, EaModel.class);
            Map<String,String> maps = model.getEaTableInfo();
            //遍历
            String packageName = maps.get("package");
            String entityName = maps.get("entityName");
            String name = maps.get("name");
            String title = maps.get("title");
            eaTableInfo.setPackageName(packageName);
            eaTableInfo.setEntityName(entityName);
            EntityWrapper<EaTableInfo> conditionWrap = new EntityWrapper<EaTableInfo>(eaTableInfo);
            EaTableInfo result = (EaTableInfo) eaTableInfoService.selectOne(conditionWrap);
            eaTableInfo.setName(name);
            eaTableInfo.setDesc(title);
            eaTableInfo.setTitle(title);
            if(result == null){
                eaTableInfo.setId(""+new IdWorker().getId());
                eaTableInfo.insert();
            }else{
                eaTableInfo.setId(result.getId());
            }
//            eaTableInfo.insertOrUpdate();
        }

        int count = eaTableInfoService.selectCount(new EntityWrapper<EaTableInfo>(new EaTableInfo()));
        log.info("DB count:{},Entity count:{}",count,columnCount);
        jsonResult.setResultMsg(""+(columnCount == count));
        return jsonResult;
    }
}