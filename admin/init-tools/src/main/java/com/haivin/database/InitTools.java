package com.haivin.database;

import org.apache.ibatis.session.SqlSession;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


/**
 * @Title:
 * @Description:
 * @Author: zr
 * @Date: 2018/6/7 16:58
 */
public class InitTools {

    public static void main(String[] args) throws SQLException {
//        InitTools.initSystemTable();
        InitTools.initSystemColumn();
    }

    public static void initSystemTable(){
        ResultSet result = DBTools.query(
                "select TABLE_NAME,TABLE_COMMENT " +
                        "from information_schema.`TABLES` " +
                        "where TABLE_SCHEMA=?","easy_admin");
        boolean res = DBTools.insertEaTableInfo(result);
        System.out.println("res = [" + res + "]");
    }

    public static void initSystemColumn(){
        ResultSet result = DBTools.query(
                "SELECT " +
                        " eti.id as table_info_id," +
                        " isc.COLUMN_NAME as name," +
                        " isc.COLUMN_COMMENT as title" +
                        " FROM" +
                        " information_schema.`COLUMNS` isc" +
                        " LEFT JOIN ea_table_info eti on isc.TABLE_NAME = eti.`name`" +
                        " WHERE" +
                        " TABLE_SCHEMA = ?","easy_admin");
        boolean res = DBTools.insertEaColumnInfo(result);
        System.out.println("res = [" + res + "]");
    }
}
