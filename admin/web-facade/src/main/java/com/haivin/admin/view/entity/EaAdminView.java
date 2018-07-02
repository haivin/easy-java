package com.haivin.admin.view.entity;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
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
@TableName("ea_admin_view")
@Component
public class EaAdminView extends EaModel<EaAdminView> {

    private static final long serialVersionUID=1L;

    /**
     * 展示视图ID
     */
                            private String id;
    /**
     * 页面名称
     */
        private String name;
    /**
     * 页面地址
     */
        private String url;
    /**
     * 页面描述
     */
        private String desc;


@Override
protected Serializable pkVal(){
            return this.id;
        }

    @JsonIgnore
    public Map<String,String> getEaTableInfo(){
        Map<String, String> map = new HashMap<String,String>();
        map.put("package","com.haivin.admin.view.entity");
        map.put("name","ea_admin_view");
        map.put("entityName",NamingStrategy.underlineToCamel("ea_admin_view"));
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
        idMap.put("title","展示视图ID");
        idMap.put("tableName",NamingStrategy.underlineToCamel("ea_admin_view"));
        list.add(idMap);
        Map<String,String> nameMap = new HashMap<String,String>();
        nameMap.put("package","com.haivin.admin.view.entity");
        nameMap.put("name","name");
        nameMap.put("propertyName","name");
        nameMap.put("title","页面名称");
        nameMap.put("tableName",NamingStrategy.underlineToCamel("ea_admin_view"));
        list.add(nameMap);
        Map<String,String> urlMap = new HashMap<String,String>();
        urlMap.put("package","com.haivin.admin.view.entity");
        urlMap.put("name","url");
        urlMap.put("propertyName","url");
        urlMap.put("title","页面地址");
        urlMap.put("tableName",NamingStrategy.underlineToCamel("ea_admin_view"));
        list.add(urlMap);
        Map<String,String> descMap = new HashMap<String,String>();
        descMap.put("package","com.haivin.admin.view.entity");
        descMap.put("name","desc");
        descMap.put("propertyName","desc");
        descMap.put("title","页面描述");
        descMap.put("tableName",NamingStrategy.underlineToCamel("ea_admin_view"));
        list.add(descMap);
        return list;
    }

}
