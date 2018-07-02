package com.haivin.admin.table.entity;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.haivin.database.activerecord.EaModel;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 系统表信息
 * </p>
 *
 * @author zhouran
 * @since 2018-06-14
 */
@Data
@Accessors(chain = true)
@TableName("ea_table_info")
@Component
public class EaTableInfo extends EaModel<EaTableInfo> {

    private static final long serialVersionUID=1L;

    /**
     * 系统表ID
     */
                            private String id;
    /**
     * 名称
     */
        private String name;
    @TableField("package_name")
        private String packageName;
    @TableField("entity_name")
        private String entityName;
    /**
     * 标题
     */
        private String title;
    /**
     * 描述
     */
        private String desc;


@Override
protected Serializable pkVal(){
            return this.id;
        }

    @JsonIgnore
    public Map<String,String> getEaTableInfo(){
        Map<String, String> map = new HashMap<String,String>();
        map.put("package","com.haivin.admin.table.entity");
        map.put("name","ea_table_info");
        map.put("entityName",NamingStrategy.underlineToCamel("ea_table_info"));
        map.put("title","系统表信息");
        return map;
    }

    @JsonIgnore
    public List<Map<String,String>> getEaColumnInfo(){
        List<Map<String, String>> list = new ArrayList<Map<String,String>>();

        Map<String,String> idMap = new HashMap<String,String>();
        idMap.put("package","com.haivin.admin.table.entity");
        idMap.put("name","id");
        idMap.put("propertyName","id");
        idMap.put("title","系统表ID");
        idMap.put("tableName",NamingStrategy.underlineToCamel("ea_table_info"));
        list.add(idMap);
        Map<String,String> nameMap = new HashMap<String,String>();
        nameMap.put("package","com.haivin.admin.table.entity");
        nameMap.put("name","name");
        nameMap.put("propertyName","name");
        nameMap.put("title","名称");
        nameMap.put("tableName",NamingStrategy.underlineToCamel("ea_table_info"));
        list.add(nameMap);
        Map<String,String> packageNameMap = new HashMap<String,String>();
        packageNameMap.put("package","com.haivin.admin.table.entity");
        packageNameMap.put("name","package_name");
        packageNameMap.put("propertyName","packageName");
        packageNameMap.put("title","");
        packageNameMap.put("tableName",NamingStrategy.underlineToCamel("ea_table_info"));
        list.add(packageNameMap);
        Map<String,String> entityNameMap = new HashMap<String,String>();
        entityNameMap.put("package","com.haivin.admin.table.entity");
        entityNameMap.put("name","entity_name");
        entityNameMap.put("propertyName","entityName");
        entityNameMap.put("title","");
        entityNameMap.put("tableName",NamingStrategy.underlineToCamel("ea_table_info"));
        list.add(entityNameMap);
        Map<String,String> titleMap = new HashMap<String,String>();
        titleMap.put("package","com.haivin.admin.table.entity");
        titleMap.put("name","title");
        titleMap.put("propertyName","title");
        titleMap.put("title","标题");
        titleMap.put("tableName",NamingStrategy.underlineToCamel("ea_table_info"));
        list.add(titleMap);
        Map<String,String> descMap = new HashMap<String,String>();
        descMap.put("package","com.haivin.admin.table.entity");
        descMap.put("name","desc");
        descMap.put("propertyName","desc");
        descMap.put("title","描述");
        descMap.put("tableName",NamingStrategy.underlineToCamel("ea_table_info"));
        list.add(descMap);
        return list;
    }

}
