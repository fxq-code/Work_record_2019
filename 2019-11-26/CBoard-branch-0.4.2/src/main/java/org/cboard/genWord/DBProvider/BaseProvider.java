package org.cboard.genWord.DBProvider;

import org.cboard.genWord.DBProvider.mysqlProvider.MysqlDaoImpl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Created by chenf on 2019/11/21.
 */
public class BaseProvider {

    private static Connection conn=null;

    //数据库命令执行对象
    private static PreparedStatement pstmt;
    //数据库返回结果
    private static java.sql.ResultSet rs;

    private static String dbName;

    public static String getDbName() {
        return dbName;
    }

    public static void setDbName(String dbName) {
        BaseProvider.dbName = dbName;
    }

    public static Connection getConnection(String dbName1){
        setDbName(dbName1);
        if(conn==null){
            InputStream res = MysqlDaoImpl.class.getClassLoader().getResourceAsStream("genEcharts.properties");
            Properties properties=new Properties();
            try {
                properties.load(res);
                Class.forName(properties.getProperty(dbName1+".driver"));
                String user=properties.getProperty(dbName1+".username");
                String pwd=properties.getProperty(dbName1+".password");
                conn = DriverManager.getConnection(properties.getProperty(dbName1+".url"), user, pwd);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return  conn;

    }

    //执行读操作方法
    public static  java.sql.ResultSet executeQuery(String query,
                                                   List<Object> params){
        conn=getConnection(dbName);
        try {
            //3、创建命令执行对象
            pstmt = conn.prepareStatement(query);
            //4、执行
            if(params!=null && params.size()>0){
                for(int i=0;i<params.size();i++){
                    pstmt.setObject(i+1, params.get(i));
                }
            }
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    //执行写操作方法
    public static  int executeUpdate(String query,
                                     List<Object> params){
        int result = 0;
        getConnection(dbName);
        try {
            //3、创建命令执行对象
            pstmt = conn.prepareStatement(query);
            //4、执行
            if(params!=null && params.size()>0){
                for(int i=0;i<params.size();i++){
                    pstmt.setObject(i+1, params.get(i));
                }
            }
            //5、处理结果
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //6、释放资源
            close();
        }
        return result;
    }


    //关闭资源
    public static void close(){
        try {
            if(rs!=null){
                rs.close();
                rs = null;
            }
            if(pstmt!=null){
                pstmt.close();
                pstmt = null;
            }
            if(conn!=null){
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
