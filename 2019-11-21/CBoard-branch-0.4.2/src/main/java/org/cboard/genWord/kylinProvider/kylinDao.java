package org.cboard.genWord.kylinProvider;



import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by chenf on 2019/11/20.
 */
public class kylinDao {
    public final Logger logger=Logger.getLogger(String.valueOf(kylinDao.class));

    private static Connection connection=null;
    private kylinDao(){

    }

    public static Connection getConnection(){
        if(connection==null){
            InputStream res = kylinDao.class.getClassLoader().getResourceAsStream("genEcharts.properties");
            Properties properties=new Properties();
            try {
//                properties.load(res);
//                Driver driver= (Driver) Class.forName(properties.getProperty("kylin.driver")).newInstance();
//                Properties info=new Properties();
//                info.put("user",properties.getProperty("kylin.username"));
//                info.put("password",properties.getProperty("kylin.password"));
//
//                connection=driver.connect(properties.getProperty("kylin.url"),info);
                properties.load(res);
                Class.forName(properties.getProperty("kylin.driver")).newInstance();
                String user=properties.getProperty("kylin.username");
                String pwd=properties.getProperty("kylin.password");
                connection = DriverManager.getConnection(properties.getProperty("kylin.url"), user, pwd);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return  connection;

    }


}
