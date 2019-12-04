package org.cboard.genWord.DBProvider.mysqlProvider;

import org.cboard.genWord.genImg.utils.SqlCommonUtil;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by chenf on 2019/11/20.
 */
public class QueryDemo {




    public static ArrayList getAllData(String tableName){
        ArrayList arrayList=new ArrayList();

        Connection connection = MysqlDaoImpl.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from " + tableName);
            ResultSet resultSet = preparedStatement.executeQuery();
            arrayList= (ArrayList) SqlCommonUtil.converList(resultSet);

            //能够正确的 取到相应的值
            for (int i = 0; i < arrayList.size(); i++) {
                System.out.println(arrayList.get(i));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  arrayList;

    }

    public static void main(String[] args) {

        //QueryDemo.getAllData("tbl_user");

        ArrayList list1= SqlCommonUtil.getResultList("select * from tbl_user",null);
        HashMap map=(HashMap)list1.get(0);
        System.out.println(map.keySet().size());


        for (int i = 0; i < list1.size(); i++) {
            //System.out.println((HashMap)list1.get(i));
           HashMap map1=(HashMap)list1.get(i);
            System.out.println(map1.values());
//            System.out.println(map.keySet().size());

        }
    }

}
