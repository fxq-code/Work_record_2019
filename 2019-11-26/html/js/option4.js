var options4={
  "title": {
    "text": "呵呵哒",
    "subtext": "呵呵哒",
    "textStyle": {
      "color": "rgba(30,144,255,0.8)",
      "fontSize": 35,
      "fontFamily": "微软雅黑",
      "fontWeight": "bolder"
    },
    "x": "center",
    "y": "top"
  },
  "tooltip": {
    "formatter": "{a} <br/>{b} : {c} ({d}%)",
    "show": true
  },
  "legend": {
    "orient": "vertical",
    "data": [
      "展现",
      "点击",
      "访问",
      "咨询",
      "订单"
    ],
    "x": "left"
  },
  "series": [
    {
      "name": "呵呵哒",
      "type": "funnel",
      "itemStyle": {
        "normal": {
          "borderColor": "#fff",
          "borderWidth": 2,
          "opacity": 0.8
        }
      },
      "label": {
        "normal": {
          "formatter": function (params,ticket,callback) {
			  if(params.dataIndex==4){
				  var baseValue=params.value
			  }
			  console.log(baseValue)

              let res = params.dataIndex+params.name+params.value+ (params.value/baseValue)*100;
			  
			  return res+"%"
          },
          "position": "inside",
          "textStyle": {
            "color": "#777"
          }
        }
      },
      "data": [
        {
          "name": "访问",
          "value": 30
        },
        {
          "name": "咨询",
          "value": 10
        },
        {
          "name": "订单",
          "value": 5
        },
        {
          "name": "点击",
          "value": 50
        },
        {
          "name": "展现",
          "value": 80
        }
      ]
    }
  ]
};