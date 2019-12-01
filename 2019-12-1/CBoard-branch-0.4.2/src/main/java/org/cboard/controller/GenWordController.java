package org.cboard.controller;

import com.alibaba.fastjson.JSONObject;
import com.lowagie.text.DocumentException;
import org.apache.http.HttpRequest;
import org.cboard.genWord.CoreEngine.entity.ReportEntity;
import org.cboard.pojo.DashboardDataset;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

import static org.cboard.genWord.CoreEngine.center.Center.genCenterReport;
import static org.cboard.genWord.CoreEngine.depart.Depart.genDepartReport;
import static org.cboard.genWord.genImg.utils.Echarts2WordUtil.getImgFileNameArrayByCenter;
import static org.cboard.genWord.genImg.utils.Echarts2WordUtil.getImgFileNameArrayByDepart;

/**
 * Created by chenf on 2019/11/19.
 */
@RestController
@RequestMapping("/genWord")
public class GenWordController  extends  BaseController {
    private  static ReportEntity reportEntity=new ReportEntity();

    @ResponseBody
    @RequestMapping(value="/genPartImgs")
    public String genPartImgs(@RequestBody String name,String password) {
       // System.out.println("HashMap="+o.toString());
        System.out.println("name="+name+",password="+password);
        try {
            String []arr=getImgFileNameArrayByDepart();
            String imgsPath=Arrays.asList(arr).toString();
            imgsPath= imgsPath.substring(1,imgsPath.length()-1);
            System.out.println(imgsPath);
            return "SUCCESS@"+imgsPath;

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }

    }


    @RequestMapping(value = "/part")

    public String genWordPart(HttpServletRequest m) {

        Map<String, String[]> arriMap = m.getParameterMap();
        String password=arriMap.getOrDefault("password",new String[]{""})[0];
        String name=arriMap.getOrDefault("name",new String[]{""})[0];
        System.out.println("name="+name+",password="+password);
//        System.out.println("param"+m.getParameter("password"));
//        System.out.println("attri"+m.getAttribute("password"));
//        System.out.println(m.getAttributeNames().toString());
//        Set<String> set1 = m.getParameterMap().keySet();
//        System.out.println(m.getParameterMap().keySet());

        String fileName="";

        try {

            //fileName=genDepartReport(getImgFileNameArrayByDepart(),new ReportEntity());
            return "SUCCESS@"+fileName;

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }


    }

    @RequestMapping(value = "/center")

    public String genWordCenter(HttpServletRequest m) {

        System.out.println(m.getParameter("name"));
        System.out.println(m.getAttributeNames().toString());
        System.out.println(m.getParameterMap().keySet());
        //System.out.println(m.getParameterMap().toString());

        String fileName="";

        try {
            fileName=genCenterReport(getImgFileNameArrayByCenter(),new ReportEntity());
            return "SUCCESS@"+fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }



    }

}
