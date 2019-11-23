package org.cboard.genWord.kylinProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by chenf on 2019/11/20.
 */
public class QueryDemo {


    private static  final Logger logger=Logger.getLogger(String.valueOf(QueryDemo.class));

    public static ArrayList queryAllData(String tableName){
        ArrayList arrayList=new ArrayList();
        try {
//            Statement statement=kylinConnection.getConnection().createStatement();
//            ResultSet rs= statement.executeQuery("select * from "+tableName);
//            while(rs.next()){
//
//            }

            Connection connection = kylinDao.getConnection();
            ResultSet tables = connection.getMetaData().getTables(null, null, null,
                    new String[]{"TABLE"});

            while(tables.next()){

                String col1=tables.getString(1);//表类别
                String col2=tables.getString(2);//表模式
                String col3=tables.getString(3);//表名称
                logger.info(col1+" "+col2+" "+col3);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  arrayList;
    }


}
