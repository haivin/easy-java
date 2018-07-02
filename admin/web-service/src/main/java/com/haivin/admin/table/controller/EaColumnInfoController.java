package com.haivin.admin.table.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.haivin.admin.table.entity.EaTableInfo;
import com.haivin.admin.table.service.EaColumnInfoService;
import com.haivin.database.activerecord.EaModel;
import com.haivin.util.ApplicationContextUtil;
import com.haivin.util.idcenter.IdWorker;
import com.haivin.util.jackson.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import com.haivin.database.controller.BaseController;
import com.haivin.admin.table.entity.EaColumnInfo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhouran
 * @since 2018-06-08
 */
@RestController
@Slf4j
@RequestMapping("/system/column")
public class EaColumnInfoController extends BaseController<EaColumnInfo> {
    @Autowired
    EaColumnInfoService eaColumnInfoService;

    @RequestMapping("/saveInfo")
    public JsonResult saveInfo() {
        JsonResult jsonResult = new JsonResult();
        ApplicationContext ctx = ApplicationContextUtil.getContext();
        String[] beanNames = ctx.getBeanNamesForType(EaModel.class);
        log.info("所以beanNames个数：{}", beanNames.length);

        int columnCount = 0;
        for (String bn : beanNames) {
            EaModel model = ctx.getBean(bn, EaModel.class);
            List<Map<String, String>> lists = model.getEaColumnInfo();
            columnCount += (lists!=null)?lists.size():0;
            //遍历
            for (Map<String, String> maps : lists) {
                EaColumnInfo eaColumnInfo = new EaColumnInfo();
                EaTableInfo eaTableInfo = new EaTableInfo();
                String packageName = maps.get("package");
                String tableName = maps.get("tableName");
                eaTableInfo.setPackageName(packageName);
                eaTableInfo.setEntityName(tableName);
                EntityWrapper<EaTableInfo> etiConditionWrap = new EntityWrapper<EaTableInfo>(eaTableInfo);
                EaTableInfo eaTableInfoResult = eaTableInfo.selectOne(etiConditionWrap);
                if(eaTableInfoResult == null){
                    break;
                }

                String propertyName = maps.get("propertyName");
                String name = maps.get("name");
                String title = maps.get("title");
                eaColumnInfo.setTableInfoId(eaTableInfoResult.getId());
                eaColumnInfo.setPropertyName(propertyName);
                EntityWrapper<EaColumnInfo> conditionWrap = new EntityWrapper<EaColumnInfo>(eaColumnInfo);
                EaColumnInfo result = (EaColumnInfo) eaColumnInfoService.selectOne(conditionWrap);
                eaColumnInfo.setName(name);
                eaColumnInfo.setDesc(title);
                eaColumnInfo.setTitle(title);
                if (result == null) {
                    eaColumnInfo.setId("" + new IdWorker().getId());
                    eaColumnInfo.insert();
                } else {
                    eaColumnInfo.setId(result.getId());
                }
//                eaColumnInfo.insertOrUpdate();
            }
        }
        int count = eaColumnInfoService.selectCount(new EntityWrapper<EaColumnInfo>(new EaColumnInfo()));
        log.info("DB count:{},Entity count:{}",count,columnCount);
        jsonResult.setResultMsg(""+(columnCount == count));
        return jsonResult;
    }


}