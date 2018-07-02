package com.haivin.admin.view.entity;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.haivin.database.activerecord.EaModel;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhouran
 * @since 2018-06-14
 */
@Data
@Accessors(chain = true)
@TableName("ea_admin_component")
@Component
public class EaAdminComponent extends EaModel<EaAdminComponent> {

    private static final long serialVersionUID=1L;

    /**
     * 组件ID
     */
                            private String id;
    /**
     * 组件名称
     */
    @TableField("base_code_id")
        private String baseCodeId;
    @TableField("admin_view_id")
        private String adminViewId;


@Override
protected Serializable pkVal(){
            return this.id;
        }

    @JsonIgnore
    public Map<String,String> getEaTableInfo(){
        Map<String, String> map = new HashMap<String,String>();
        map.put("package","com.haivin.admin.view.entity");
        map.put("name","ea_admin_component");
        map.put("entityName",NamingStrategy.underlineToCamel("ea_admin_component"));
        map.put("title","");
        return map;
    }


    @JsonIgnore
    public List<Map<String,String>> getEaColumnInfo(){
        List<Map<String, String>> list = new ArrayList<Map<String,String>>();

        Map<String,String> idMap = new HashMap<String,String>();
        idMap.put("package","com.haivin.admin.view.entity");
        idMap.put("name","id");
        idMap.put("propertyName","id");
        idMap.put("title","组件ID");
        idMap.put("tableName",NamingStrategy.underlineToCamel("ea_admin_component"));
        list.add(idMap);
        Map<String,String> baseCodeIdMap = new HashMap<String,String>();
        baseCodeIdMap.put("package","com.haivin.admin.view.entity");
        baseCodeIdMap.put("name","base_code_id");
        baseCodeIdMap.put("propertyName","baseCodeId");
        baseCodeIdMap.put("title","组件名称");
        baseCodeIdMap.put("tableName",NamingStrategy.underlineToCamel("ea_admin_component"));
        list.add(baseCodeIdMap);
        Map<String,String> adminViewIdMap = new HashMap<String,String>();
        adminViewIdMap.put("package","com.haivin.admin.view.entity");
        adminViewIdMap.put("name","admin_view_id");
        adminViewIdMap.put("propertyName","adminViewId");
        adminViewIdMap.put("title","");
        adminViewIdMap.put("tableName",NamingStrategy.underlineToCamel("ea_admin_component"));
        list.add(adminViewIdMap);
        return list;
    }

}
