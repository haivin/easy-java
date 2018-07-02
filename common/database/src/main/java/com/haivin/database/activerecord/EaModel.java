package com.haivin.database.activerecord;

import com.baomidou.mybatisplus.activerecord.Model;

import java.util.List;
import java.util.Map;

/**
 * @Title:
 * @Description:
 * @Author: zr
 * @Date: 2018/6/8 15:54
 */
public abstract class EaModel<T extends Model> extends Model<T>{

    public abstract List<Map<String, String>> getEaColumnInfo();
    public abstract Map<String,String> getEaTableInfo();
}
