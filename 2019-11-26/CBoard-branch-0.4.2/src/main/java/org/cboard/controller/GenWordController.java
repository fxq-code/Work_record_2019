package org.cboard.controller;

import com.alibaba.fastjson.JSONObject;
import com.lowagie.text.DocumentException;
import org.cboard.genWord.CoreEngine.entity.ReportEntity;
import org.cboard.pojo.DashboardDataset;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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

    @RequestMapping(value="/genPartImgs")
    public String genPartImgs(Object o) {

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
    @ResponseBody
    public String genWordPart(@RequestParam Map<String, Object> m) {
        //JSONObject object = (JSONObject) o;
        System.out.println(m);
        System.out.println("part="+m.get("password"));
//        String str=(String)o;
//        System.out.println("接收到的arr="+str);
//        String []arr1=str.split(", ");

        String fileName="";

        try {
            //fileName=genDepartReport(arr1,new ReportEntity());
            fileName=genDepartReport(getImgFileNameArrayByDepart(),new ReportEntity());
            return "SUCCESS@"+fileName;

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }


    }

    @RequestMapping(value = "/center")
    @ResponseBody
    public String genWordCenter(@RequestParam Map<String, Object> m) {
        //JSONObject object = (JSONObject) o;
        System.out.println("center="+m.get("name"));
        System.out.println(m);

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
