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
@TableName("ea_admin_column")
@Component
public class EaAdminColumn extends EaModel<EaAdminColumn> {

    private static final long serialVersionUID=1L;

    /**
     * 页面内容ID
     */
                            private String id;
    /**
     * 组件ID
     */
    @TableField("admin_component_id")
        private String adminComponentId;
    /**
     * 表ID
     */
    @TableField("ea_table_id")
        private String eaTableId;
    /**
     * 字段ID
     */
    @TableField("ea_column_id")
        private String eaColumnId;
    /**
     * 类型
     */
        private String type;
    /**
     * 显示名称
     */
        private String title;
    /**
     * 是否可编辑
     */
        private Integer editable;


@Override
protected Serializable pkVal(){
            return this.id;
        }

    @JsonIgnore
    public Map<String,String> getEaTableInfo(){
        Map<String, String> map = new HashMap<String,String>();
        map.put("package","com.haivin.admin.view.entity");
        map.put("name","ea_admin_column");
        map.put("entityName",NamingStrategy.underlineToCamel("ea_admin_column"));
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
        idMap.put("title","页面内容ID");
        idMap.put("tableName",NamingStrategy.underlineToCamel("ea_admin_column"));
        list.add(idMap);
        Map<String,String> adminComponentIdMap = new HashMap<String,String>();
        adminComponentIdMap.put("package","com.haivin.admin.view.entity");
        adminComponentIdMap.put("name","admin_component_id");
        adminComponentIdMap.put("propertyName","adminComponentId");
        adminComponentIdMap.put("title","组件ID");
        adminComponentIdMap.put("tableName",NamingStrategy.underlineToCamel("ea_admin_column"));
        list.add(adminComponentIdMap);
        Map<String,String> eaTableIdMap = new HashMap<String,String>();
        eaTableIdMap.put("package","com.haivin.admin.view.entity");
        eaTableIdMap.put("name","ea_table_id");
        eaTableIdMap.put("propertyName","eaTableId");
        eaTableIdMap.put("title","表ID");
        eaTableIdMap.put("tableName",NamingStrategy.underlineToCamel("ea_admin_column"));
        list.add(eaTableIdMap);
        Map<String,String> eaColumnIdMap = new HashMap<String,String>();
        eaColumnIdMap.put("package","com.haivin.admin.view.entity");
        eaColumnIdMap.put("name","ea_column_id");
        eaColumnIdMap.put("propertyName","eaColumnId");
        eaColumnIdMap.put("title","字段ID");
        eaColumnIdMap.put("tableName",NamingStrategy.underlineToCamel("ea_admin_column"));
        list.add(eaColumnIdMap);
        Map<String,String> typeMap = new HashMap<String,String>();
        typeMap.put("package","com.haivin.admin.view.entity");
        typeMap.put("name","type");
        typeMap.put("propertyName","type");
        typeMap.put("title","类型");
        typeMap.put("tableName",NamingStrategy.underlineToCamel("ea_admin_column"));
        list.add(typeMap);
        Map<String,String> titleMap = new HashMap<String,String>();
        titleMap.put("package","com.haivin.admin.view.entity");
        titleMap.put("name","title");
        titleMap.put("propertyName","title");
        titleMap.put("title","显示名称");
        titleMap.put("tableName",NamingStrategy.underlineToCamel("ea_admin_column"));
        list.add(titleMap);
        Map<String,String> editableMap = new HashMap<String,String>();
        editableMap.put("package","com.haivin.admin.view.entity");
        editableMap.put("name","editable");
        editableMap.put("propertyName","editable");
        editableMap.put("title","是否可编辑");
        editableMap.put("tableName",NamingStrategy.underlineToCamel("ea_admin_column"));
        list.add(editableMap);
        return list;
    }

}
