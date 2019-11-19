package com.echarts.springboot.service;

import com.echarts.springboot.dao.domain.MusicInfo;
import com.echarts.springboot.dao.mapper.MusicInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by chenf on 2019/11/19.
 */
public interface MusicInfoService {


     List<MusicInfo> selectAll(MusicInfo musicInfo);
}
