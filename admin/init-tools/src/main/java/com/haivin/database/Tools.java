package com.haivin.database;

/**
 * @Title:
 * @Description:
 * @Author: zr
 * @Date: 2018/6/7 19:50
 */
public class Tools {
    public static String columnName2Variable(String columnName){
        String[] s = columnName.toLowerCase().split("_");
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for(int i=0;i<s.length;i++){
            String first ="";
            String others = "";
            if(i==0&&s[0].equals("")){
                flag = true;
            }else if(i==0&&!s[0].equals("")){
                first="";
                others = s[i];
            }else{
                if(flag&&i==1){
                    first="";
                    others = s[i];
                }else{
                    first = s[i].substring(0, 1).toUpperCase();
                    others = s[i].substring(1);
                }
            }
            sb.append(first).append(others);
        }
        return sb.toString();
    }
}
