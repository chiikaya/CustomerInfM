$(function () {
    initAllotDataGrid();
});

function  initAllotDataGrid() {
    $("#allotDataGrid").datagrid({
        fitColumns:true,  //防止水平滚动
        singleSelect: false,//鼠标点击某行就会勾选checkbox
        method:'post',
        idField:'id',
        fit:true,  //自动适应屏幕大小
        url:path+"/allot/getAllAllot",
        pagination:true,  //DataGrid控件底部显示分页工具栏
        rownumbers:true,   //显示一个行号列
        singleSelect:true,  //只允许选择一行
        pageNumber:1,  //设置分页属性的时候初始化页码
        pageSize:10,    //设置分页属性的时候初始化页面大小
        pageList:[10,20,50],  //设置分页属性的时候 初始化页面大小选择列表
        columns:[[
            //{field:'id',title:'分配编号',width:60,align:'center',hidden:'true'},
            {field:'id',checkbox:'true',width:60,align:'center'},
            {field:'text',title:'姓名',width:60,align:'center'},
            {field:'allot_inuse',title:'客户状态',width:100,align:'center',
                formatter: function(value,row,index){
                    if (value=="1"){
                        return "潜在客户";
                    } else if(value=="2"){
                        return "意向客户";
                    }else {
                        return "交易客户";
                    }
                }
            },
            {field:'allot_source',title:'客户来源',width:100,align:'center',
                formatter: function(value,row,index){
                    if (value=="1"){
                        return "自己上门";
                    } else {
                        return "朋友推荐";
                    }
                }
            },
            {field:'allot_type',title:'客户类型',width:100,align:'center',
                formatter: function(value,row,index){
                    if (value=="1"){
                        return "合作伙伴";
                    } else {
                        return "供应商";
                    }
                }
            },
            {field:'create_time',title:'创建时间',width:100,align:'center'},
            {field:'company',title:'公司',width:100,align:'center'},
            {field:'empowerAllot',title:'分配',width:70,align:'center',
                formatter: function(value,row,index){
                    return "<a href='javascript:empowerAllot()'>分配</a>";
                }
            },
        ]],

    });
}



/*---------------- 分配客户  --------------------*/
