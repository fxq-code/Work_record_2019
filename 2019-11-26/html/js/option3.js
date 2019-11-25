var options3=
{
        // 图表标题
        title: {
            text: '折线图Line'
        },
        // 提示框
        tooltip : {
            trigger: 'axis',          //// 触发类型，默认数据触发，见下图，可选为：'item' / 'axis'
            textStyle:{align:'left'},  //显示的数据居左
            
        /*  设置显示数据中标题居中，其它数据居左显示
            formatter : function(params) {
                var result = params[0].name;
                var results = params[1].name;
                result += '<p style="text-align: center;">'+params[0].name+'</p>';
                    params.forEach(function(item) {
                    results +='<div style="text-align: left;line-height:10px;padding-bottom:5px">'
                    results += '<br/>';
                    results += '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:' + item.color + '"></span>';
                    results += item.seriesName + "：";
                    results += isNaN(item.value) ? 0 : item.value;
                    results +='</div>'
                });
                return results;
            } 
        */
            
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#6a7985'
                }
            }
        },
        // 图例
        legend: {            //使上方标题(上古卷轴5 天际、正当防卫3、辐射4、GTA5、巫师3)x轴居中，y轴20
            x : 'center',   
            y : '20',
            data:['上古卷轴5 天际','正当防卫3','辐射4','GTA5','巫师3']
        },
        toolbox: {
            feature: {
                saveAsImage: {}        //保存为图片
            }
        },
        grid: {             //x轴、y轴的显示位置
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [            //x轴的数据和样式
            {
                type : 'category',
                name: 'x',          
                boundaryGap : false,
                data : ['周一','周二','周三','周四','周五','周六','周日']
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        //系列中的数据内容数组通常为具体的数据项，通常来说数据用一个二维数组表示。如下，每一列被称为一个[维度]。
        series : [            
            {
                name:'上古卷轴5 天际',
                type:'line',
                stack: '总量',
                areaStyle: {},
                data:[100, 132, 101, 134, 90, 230, 216]
            },
            {
                name:'正当防卫3',
                type:'line',
                stack: '总量',
                areaStyle: {},
                data:[210, 182, 191, 234, 290, 330, 320]
            },
            {
                name:'辐射4',
                type:'line',
                stack: '总量',
                areaStyle: {},
                data:[150, 232, 201, 154, 190, 330, 410]
            },
            {
                name:'GTA5',
                type:'line',
                stack: '总量',
                areaStyle: {normal: {}},    //折线之间的颜色样式(如：GTA5与辐射4之间)
                data:[320, 452, 301, 374, 390, 350, 380]
            },
            {
                name:'巫师3',
                type:'line',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                areaStyle: {normal: {}},     //折线之间的颜色样式(如：巫师3与GTA5之间)
                data:[820, 932, 901, 934, 1290, 1030, 1320]
            }
        ]
};