package org.cboard.genWord.DBProvider.kylinProvider;



import org.cboard.genWord.DBProvider.BaseProvider;

import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by chenf on 2019/11/20.
 */
public class KylinDaoImpl extends BaseProvider{
    public final Logger logger=Logger.getLogger(String.valueOf(KylinDaoImpl.class));

    private static Connection connection=null;
    private KylinDaoImpl(){

    }

    public static Connection getConnection(){

        return BaseProvider.getConnection("kylin");

    }


}
