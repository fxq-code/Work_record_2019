package com.echarts.springboot.dao.mapper;

import com.echarts.springboot.dao.domain.MusicInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chenf on 2019/11/19.
 */
@Repository
public interface MusicInfoMapper {

    @ResultMap("BaseResultMap")
    @Select("select * from music_info")
    List<MusicInfo> selectAll(MusicInfo musicInfo);
}
