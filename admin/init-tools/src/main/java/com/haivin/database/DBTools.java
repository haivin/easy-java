package com.haivin.database;

import java.io.Reader;
import java.sql.*;
import java.util.List;
import java.util.Map;

import com.haivin.util.idcenter.IdWorker;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.type.TypeAliasRegistry;

/**
 * @Title:
 * @Description:
 * @Author: zr
 * @Date: 2018/6/7 17:32
 */
public class DBTools {
    public static SqlSessionFactory sessionFactory;

    static {
        try {
// 使用MyBatis提供的Resources类加载mybatis的配置文件
            Reader reader = Resources.getResourceAsReader("mybatis.xml");
// 构建sqlSession的工厂
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            TypeAliasRegistry registry = sessionFactory.getConfiguration().getTypeAliasRegistry();
            registry.registerAlias("InfoSchemaMapper",InfoSchemaMapper.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 创建能执行映射文件中sql的sqlSession
    public static SqlSession getSession() {
        return sessionFactory.openSession();
    }

    public static ResultSet query(String sql,Object... objs){
        SqlSession sqlSession = DBTools.getSession();
        Connection connection = null;
        try {
            connection = sqlSession.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int i = 0;
            for (Object obj : objs) {
                i++;
                preparedStatement.setObject(i,obj);
            }

//            for (int i=1; i <= objs.length; i++) {
//                Object obj = objs[i];
//                if(obj instanceof String){
//                    preparedStatement.setString(i,(String)obj);
//                }
//            }

            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(connection!=null&&!connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static boolean insertEaTableInfo (ResultSet resultSet){
        if(resultSet == null) return false;
        String sql = "insert into ea_table_info values (?,?,?,?,?)";
        SqlSession sqlSession = DBTools.getSession();
        Connection connection = null;
        try {
            connection = sqlSession.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            while (resultSet.next()){
                String tableName = resultSet.getString("TABLE_NAME");
                String tableComment = resultSet.getString("TABLE_COMMENT");
                preparedStatement.setObject(1,new IdWorker().getId());
                preparedStatement.setObject(2,tableName);
                preparedStatement.setObject(3,Tools.columnName2Variable(tableName));
                preparedStatement.setObject(4,tableComment);
                preparedStatement.setObject(5,tableComment);
                preparedStatement.addBatch();
            }
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
            }
            int[] result = preparedStatement.executeBatch();
            connection.setAutoCommit(true);
            return result!=null&&result.length>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(connection!=null&&!connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean insertEaColumnInfo (ResultSet resultSet){
        if(resultSet == null) return false;
        String sql = "insert into ea_column_info values (?,?,?,?,?,?,?)";
        SqlSession sqlSession = DBTools.getSession();
        Connection connection = null;
        try {
            connection = sqlSession.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            while (resultSet.next()){
                String tableId = resultSet.getString("table_info_id");
                String name = resultSet.getString("name");
                String title = resultSet.getString("title");
                preparedStatement.setObject(1,new IdWorker().getId());
                preparedStatement.setObject(2,tableId);
                preparedStatement.setObject(3,name);
                preparedStatement.setObject(4,Tools.columnName2Variable(name));
                preparedStatement.setObject(5,title);
                preparedStatement.setObject(6,title);
                preparedStatement.setObject(7,"");
                preparedStatement.addBatch();
            }
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
            }
            int[] result = preparedStatement.executeBatch();
            connection.setAutoCommit(true);
            return result!=null&&result.length>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(connection!=null&&!connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
