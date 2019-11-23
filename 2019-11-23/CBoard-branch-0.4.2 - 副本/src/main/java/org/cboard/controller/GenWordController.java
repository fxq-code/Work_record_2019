package org.cboard.controller;

import com.alibaba.fastjson.JSONObject;
import com.lowagie.text.DocumentException;
import org.cboard.genWord.CoreEngine.entity.ReportEntity;
import org.cboard.pojo.DashboardDataset;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import static org.cboard.genWord.CoreEngine.center.Center.genCenterReport;
import static org.cboard.genWord.CoreEngine.depart.Depart.genDepartReport;

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

        String[] arr=new String[8];
        String fileName="";
        for (int i = 0; i < arr.length; i++) {
            arr[i]="earth.png";
        }
        try {
            fileName=genDepartReport(arr,new ReportEntity());
            return "SUCCESS@"+fileName;

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }


    }

    @RequestMapping(value = "/center")
    public String genWordCenter(Object o) {
        //JSONObject object = (JSONObject) o;
        System.out.println(o);
        String[] arr1=new String[15];
        String fileName="";

        for (int i = 0; i < arr1.length; i++) {
            arr1[i]="earth.png";
        }
        try {
            fileName=genCenterReport(arr1,new ReportEntity());
            return "SUCCESS@"+fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }



    }

}
