package com.haivin.database;

import org.apache.ibatis.annotations.Insert;

/**
 * @Title:
 * @Description:
 * @Author: zr
 * @Date: 2018/6/7 17:46
 */
public interface EaTableInfoMapper {

    @Insert("insert into ea_table_info values(#{id},#{name},#{entityName},#{title},#{desc})")
    public boolean insert();
}
