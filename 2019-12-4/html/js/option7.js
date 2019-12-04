var options7={
        title:{
            text:'饼图',
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
		label:{
			formatter: "{b}:{c}"
		},
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['智联招聘','51job','拉钩','Boss直聘']
        },
        series:[{
            name:'访问量',
            type:'pie',
            radius:'80%',
            data:[
                {name:"智联招聘",value:600},
                {name:"51job",value:310},
                {name:"拉钩",value:200},
                {name:"Boss直聘",value:8008888888}
            ]
        }]
    
	
};