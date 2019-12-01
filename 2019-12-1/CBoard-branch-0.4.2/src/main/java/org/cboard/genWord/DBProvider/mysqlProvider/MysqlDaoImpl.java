package org.cboard.genWord.DBProvider.mysqlProvider;



import org.cboard.genWord.DBProvider.BaseProvider;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * Created by chenf on 2019/11/20.
 */
public class MysqlDaoImpl extends BaseProvider {

    private static Connection conn=null;

    //数据库命令执行对象
    private static PreparedStatement pstmt;
    //数据库返回结果
    private static java.sql.ResultSet rs;

    private MysqlDaoImpl(){

    }

    public static Connection getConnection(){
        return  BaseProvider.getConnection("mysql");

    }



}
