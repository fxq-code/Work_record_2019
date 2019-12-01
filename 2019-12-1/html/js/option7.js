var options7={
    tooltip : {
        formatter: "{a} <br/>{b} : {c}%"
    },
    series : [
        {
            name:'业务指标',
            type:'gauge',
            detail : {formatter:'{value}%'}, //仪表盘显示数据
			axisLine: { //仪表盘轴线样式
				lineStyle: {
					width: 20
				}
			},
			splitLine: { //分割线样式
				length: 20
			},
            data:[
			//{value: 50, name: '完成率'},
			{value: 60, name: '完成率123'},
			]
        }
    ]
};