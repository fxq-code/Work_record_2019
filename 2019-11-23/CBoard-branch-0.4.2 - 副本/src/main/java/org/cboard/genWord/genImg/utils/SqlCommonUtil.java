package org.cboard.genWord.genImg.utils;

import org.cboard.genWord.mysqlProvider.mysqlDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenf on 2019/11/20.
 */
public class SqlCommonUtil {


    public static ArrayList getResultList(String sql,List<Object> params){
        ArrayList arrayList=new ArrayList();

        Connection connection = mysqlDao.getConnection();
        try {

            arrayList= (ArrayList) SqlCommonUtil.converList(mysqlDao.executeQuery(sql,params));

//            //能够正确的 取到相应的值
//            for (int i = 0; i < arrayList.size(); i++) {
//                System.out.println(arrayList.get(i));
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  arrayList;

    }



    //ResultSetMetaData 和Map
    public static List converList(ResultSet rs)throws SQLException {



            List list = new ArrayList();

            ResultSetMetaData md = rs.getMetaData();

            int columnCount = md.getColumnCount();

            while (rs.next()) {

                Map rowData = new HashMap();

                for (int i = 1; i <= columnCount; i++) {

                    rowData.put(md.getColumnName(i), rs.getObject(i));

                }

                list.add(rowData);

            }

            return list;


    }
}
