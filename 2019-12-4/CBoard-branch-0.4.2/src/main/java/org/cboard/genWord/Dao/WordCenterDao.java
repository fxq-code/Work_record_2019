package org.cboard.genWord.Dao;

import org.cboard.genWord.CoreEngine.entity.ReportEntity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by chenf on 2019/12/3.
 */
public class WordCenterDao implements CommonWordDao {
    @Override
    public HashMap<String, String> genImgs(ReportEntity reportEntity) {
        return null;
    }

    @Override
    public HashMap<String, List<HashMap>> genTables(ReportEntity reportEntity) {
        return null;
    }

    @Override
    public ReportEntity genBlank() {
        return null;
    }
}
