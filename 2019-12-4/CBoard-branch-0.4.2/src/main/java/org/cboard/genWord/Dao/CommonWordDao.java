package org.cboard.genWord.Dao;

import org.cboard.genWord.CoreEngine.entity.ReportEntity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by chenf on 2019/12/3.
 */
//生成word的相关的通用的方法
public interface CommonWordDao {
    HashMap<String,String >genImgs(ReportEntity reportEntity);

    HashMap<String,List<HashMap>> genTables(ReportEntity reportEntity);
//填充相应的 实体对象
    ReportEntity genBlank();

}
