package org.cboard.controller;

import com.alibaba.fastjson.JSONObject;
import org.cboard.pojo.DashboardDataset;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * Created by chenf on 2019/11/19.
 */
@RestController
@RequestMapping("/genWord")
public class GenWordController  extends  BaseController {

    @RequestMapping(value = "/part")
    public String genWordPart(Object o) {
        //JSONObject object = (JSONObject) o;
        System.out.println(o);
        return "1";
    }

    @RequestMapping(value = "/center")
    public String genWordCenter(Object o) {
        //JSONObject object = (JSONObject) o;
        System.out.println(o);
        return "1";
    }

}
