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
@TableName("ea_admin_menu")
@Component
public class EaAdminMenu extends EaModel<EaAdminMenu> {

    private static final long serialVersionUID=1L;

    /**
     * 菜单ID
     */
                            private String id;
    /**
     * 菜单名称
     */
        private String name;
    /**
     * 页面ID
     */
    @TableField("admin_view_id")
        private String adminViewId;
    /**
     * 父菜单ID
     */
    @TableField("parent_id")
        private String parentId;


@Override
protected Serializable pkVal(){
            return this.id;
        }

    @JsonIgnore
    public Map<String,String> getEaTableInfo(){
        Map<String, String> map = new HashMap<String,String>();
        map.put("package","com.haivin.admin.view.entity");
        map.put("name","ea_admin_menu");
        map.put("entityName",NamingStrategy.underlineToCamel("ea_admin_menu"));
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
        idMap.put("title","菜单ID");
        idMap.put("tableName",NamingStrategy.underlineToCamel("ea_admin_menu"));
        list.add(idMap);
        Map<String,String> nameMap = new HashMap<String,String>();
        nameMap.put("package","com.haivin.admin.view.entity");
        nameMap.put("name","name");
        nameMap.put("propertyName","name");
        nameMap.put("title","菜单名称");
        nameMap.put("tableName",NamingStrategy.underlineToCamel("ea_admin_menu"));
        list.add(nameMap);
        Map<String,String> adminViewIdMap = new HashMap<String,String>();
        adminViewIdMap.put("package","com.haivin.admin.view.entity");
        adminViewIdMap.put("name","admin_view_id");
        adminViewIdMap.put("propertyName","adminViewId");
        adminViewIdMap.put("title","页面ID");
        adminViewIdMap.put("tableName",NamingStrategy.underlineToCamel("ea_admin_menu"));
        list.add(adminViewIdMap);
        Map<String,String> parentIdMap = new HashMap<String,String>();
        parentIdMap.put("package","com.haivin.admin.view.entity");
        parentIdMap.put("name","parent_id");
        parentIdMap.put("propertyName","parentId");
        parentIdMap.put("title","父菜单ID");
        parentIdMap.put("tableName",NamingStrategy.underlineToCamel("ea_admin_menu"));
        list.add(parentIdMap);
        return list;
    }

}
