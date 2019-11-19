package com.echarts.springboot.service.impl;

import com.echarts.springboot.dao.domain.MusicInfo;
import com.echarts.springboot.dao.mapper.MusicInfoMapper;
import com.echarts.springboot.service.MusicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by chenf on 2019/11/19.
 */
@Service
public  class MusicInfoServiceImpl implements MusicInfoService {


    @Autowired
    private MusicInfoMapper musicInfoMapper;

    public List<MusicInfo> selectAll(MusicInfo musicInfo) {

        return  musicInfoMapper.selectAll(null);
    }
}
