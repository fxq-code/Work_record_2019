package org.cboard.genWord.genImg.utils;

/**
 * Created by chenf on 2019/11/20.
 */
//注意windows 跟linux 路径分隔符的转换
public class FilePathUtil {

    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    //public static final String FILE_SEPARATOR = File.separator;

    public static String getRealFilePath(String path) {
        return path.replace("/", FILE_SEPARATOR).replace("\\", FILE_SEPARATOR);
    }

    public static String getHttpURLPath(String path) {
        return path.replace("\\", "/");
    }


}
