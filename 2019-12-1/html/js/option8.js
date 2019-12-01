var options8={
  "title": {
    "subtext": "呵呵哒",
    "textStyle": {
      "color": "rgba(30,144,255,0.8)",
      "fontSize": 25,
      "fontFamily": "微软雅黑",
      "fontWeight": "bolder"
    },
    "x": "center",
    "y": "top",
    "itemGap": 20
  },
  "tooltip": {
    "formatter": "{a} <br/>{b} : {c} ({d}%)",
    "show": true
  },
  "legend": {
    "orient": "vertical",
    "x": "left"
  },
  "series": [
    {
      "radius": 100,
      "startAngle": 180,
      "endAngle": 0,
      "axisLine": {
        "lineStyle": {
          "width": 20
        }
      },
      "axisLabel": {
        "formatter": function (value) {
          switch (value) {
             case 0: return 0;
            default: return value;
             
          } 
        }
      },
      "splitLine": {
        "lineStyle": {
          "width": 0
        }
      },
      "pointer": {
        "width": 1
      },
      "detail": {
        "formatter": "访问:30",
        "textStyle": {
          "fontSize": 30
        }
      },
      "type": "gauge",
      "data": [
        {
          "name": "",
          "value": 30
        }
      ]
    },
    {
      "radius": 180,
      "startAngle": 180,
      "endAngle": 0,
      "axisLine": {
        "lineStyle": {
          "width": 20
        }
      },
      "axisLabel": {
        "formatter": function (value) {
          switch (value) {
             case 0: return 0;
            default: return value*100;
             
          } 
        }
      },
      "splitLine": {
        "lineStyle": {
          "width": 0
        }
      },
      "pointer": {
        "width": 1
      },
      "detail": {
        "formatter": "咨询:3430",
        "textStyle": {
          "fontSize": 30
        }
      },
      "type": "gauge",
      "data": [
        {
          "name": "",
          "value": 34
        }
      ]
    },
    {
      "radius": 260,
      "startAngle": 180,
      "endAngle": 0,
      "axisLine": {
        "lineStyle": {
          "width": 20
        }
      },
      "axisLabel": {
        "formatter": function (value) {
          switch (value) {
             case 0: return 0;
            default: return value+"E12";
             
          } 
        }
      },
      "splitLine": {
        "lineStyle": {
          "width": 0
        }
      },
      "pointer": {
        "width": 1
      },
      "detail": {
        "formatter": "订单:5.49E12",
        "textStyle": {
          "fontSize": 30
        }
      },
      "type": "gauge",
      "data": [
        {
          "name": "",
          "value": 54
        }
      ]
    }
  ]
};