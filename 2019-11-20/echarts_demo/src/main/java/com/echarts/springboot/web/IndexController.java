package com.echarts.springboot.web;

import com.echarts.springboot.dao.domain.MusicInfo;
import com.echarts.springboot.service.MusicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * Created by chenf on 2019/11/19.
 */
@Controller
public class IndexController {


    private static final String INDEX = "index";
    @Autowired
    private MusicInfoService musicInfoService;

        @RequestMapping("/show")
        public String getIndex() {

            return INDEX;
        }

    @RequestMapping("/music")
    @ResponseBody
    public List<MusicInfo> getMusicInfo(MusicInfo musicInfo) {
        List<MusicInfo> musicInfoList = musicInfoService.selectAll(null);
        return musicInfoList;
    }

}
