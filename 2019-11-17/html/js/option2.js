 var options2=
{
  "calculable": true,
  "toolbox": {
    "feature": {
      "mark": {
        "show": true,
        "title": {
          "markUndo": "删除辅助线",
          "markClear": "清空辅助线",
          "mark": "辅助线开关"
        },
        "lineStyle": {
          "color": "#1e90ff",
          "type": "dashed",
          "width": 2
        }
      },
      "dataView": {
        "show": true,
        "title": "数据视图",
        "readOnly": false,
        "lang": [
          "数据视图",
          "关闭",
          "刷新"
        ]
      },
      "magicType": {
        "show": true,
        "title": {
          "bar": "柱形图切换",
          "stack": "堆积",
          "tiled": "平铺",
          "line": "折线图切换"
        },
        "type": [
          "line",
          "bar"
        ]
      },
      "restore": {
        "show": true,
        "title": "还原"
      },
      "saveAsImage": {
        "show": true,
        "title": "保存为图片",
        "type": "png",
        "lang": [
          "点击保存"
        ]
      }
    },
    "show": true
  },
  "tooltip": {
    "trigger": "axis"
  },
  "legend": {
    "data": [
      "高度(km)与气温(°C)变化关系"
    ]
  },
  "xAxis": [
    {
      "type": "value",
      "axisLabel": {
        "formatter": "{value} °C"
      }
    }
  ],
  "yAxis": [
    {
      "type": "category",
      "axisLine": {
        "onZero": false
      },
      "axisLabel": {
        "formatter": "{value} km"
      },
      "boundaryGap": false,
      "data": [
        0,6,1,7,6,4,4
      ]
    }
  ],
  "series": [
    {
      "smooth": true,
      "name": "高度(km)与气温(°C)变化关系",
      "type": "line",
      "data": [
        "老年人","儿童","妇女","高血压","糖尿病","精神病","冠心病"
      ]
    }
  ]
};