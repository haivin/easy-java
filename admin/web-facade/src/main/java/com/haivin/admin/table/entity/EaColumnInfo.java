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
 * 
 * </p>
 *
 * @author zhouran
 * @since 2018-06-14
 */
@Data
@Accessors(chain = true)
@TableName("ea_column_info")
@Component
public class EaColumnInfo extends EaModel<EaColumnInfo> {

    private static final long serialVersionUID=1L;

    /**
     * 系统表的列ID
     */
                            private String id;
    /**
     * 表ID
     */
    @TableField("table_info_id")
        private String tableInfoId;
    /**
     * 数据库列名
     */
        private String name;
    /**
     * 列对应的变量名
     */
    @TableField("property_name")
        private String propertyName;
    /**
     * 展示的列名
     */
        private String title;
    /**
     * 描述
     */
        private String desc;
    /**
     * 类型
     */
        private String type;
    /**
     * 验证
     */
        private String verify;


@Override
protected Serializable pkVal(){
            return this.id;
        }

    @JsonIgnore
    public Map<String,String> getEaTableInfo(){
        Map<String, String> map = new HashMap<String,String>();
        map.put("package","com.haivin.admin.table.entity");
        map.put("name","ea_column_info");
        map.put("entityName",NamingStrategy.underlineToCamel("ea_column_info"));
        map.put("title","");
        return map;
    }

    @JsonIgnore
    public List<Map<String,String>> getEaColumnInfo(){
        List<Map<String, String>> list = new ArrayList<Map<String,String>>();

        Map<String,String> idMap = new HashMap<String,String>();
        idMap.put("package","com.haivin.admin.table.entity");
        idMap.put("name","id");
        idMap.put("propertyName","id");
        idMap.put("title","系统表的列ID");
        idMap.put("tableName",NamingStrategy.underlineToCamel("ea_column_info"));
        list.add(idMap);
        Map<String,String> tableInfoIdMap = new HashMap<String,String>();
        tableInfoIdMap.put("package","com.haivin.admin.table.entity");
        tableInfoIdMap.put("name","table_info_id");
        tableInfoIdMap.put("propertyName","tableInfoId");
        tableInfoIdMap.put("title","表ID");
        tableInfoIdMap.put("tableName",NamingStrategy.underlineToCamel("ea_column_info"));
        list.add(tableInfoIdMap);
        Map<String,String> nameMap = new HashMap<String,String>();
        nameMap.put("package","com.haivin.admin.table.entity");
        nameMap.put("name","name");
        nameMap.put("propertyName","name");
        nameMap.put("title","数据库列名");
        nameMap.put("tableName",NamingStrategy.underlineToCamel("ea_column_info"));
        list.add(nameMap);
        Map<String,String> propertyNameMap = new HashMap<String,String>();
        propertyNameMap.put("package","com.haivin.admin.table.entity");
        propertyNameMap.put("name","property_name");
        propertyNameMap.put("propertyName","propertyName");
        propertyNameMap.put("title","列对应的变量名");
        propertyNameMap.put("tableName",NamingStrategy.underlineToCamel("ea_column_info"));
        list.add(propertyNameMap);
        Map<String,String> titleMap = new HashMap<String,String>();
        titleMap.put("package","com.haivin.admin.table.entity");
        titleMap.put("name","title");
        titleMap.put("propertyName","title");
        titleMap.put("title","展示的列名");
        titleMap.put("tableName",NamingStrategy.underlineToCamel("ea_column_info"));
        list.add(titleMap);
        Map<String,String> descMap = new HashMap<String,String>();
        descMap.put("package","com.haivin.admin.table.entity");
        descMap.put("name","desc");
        descMap.put("propertyName","desc");
        descMap.put("title","描述");
        descMap.put("tableName",NamingStrategy.underlineToCamel("ea_column_info"));
        list.add(descMap);
        Map<String,String> typeMap = new HashMap<String,String>();
        typeMap.put("package","com.haivin.admin.table.entity");
        typeMap.put("name","type");
        typeMap.put("propertyName","type");
        typeMap.put("title","类型");
        typeMap.put("tableName",NamingStrategy.underlineToCamel("ea_column_info"));
        list.add(typeMap);
        Map<String,String> verifyMap = new HashMap<String,String>();
        verifyMap.put("package","com.haivin.admin.table.entity");
        verifyMap.put("name","verify");
        verifyMap.put("propertyName","verify");
        verifyMap.put("title","验证");
        verifyMap.put("tableName",NamingStrategy.underlineToCamel("ea_column_info"));
        list.add(verifyMap);
        return list;
    }

}
