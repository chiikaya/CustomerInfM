$(function () {
    initInfoDataGrid();

    //户型
    $('#type_id2').combobox({
        url:path+"/info/getType",
        valueField:'id',
        textField:'text'
    });

    //管理员工
    $('#staff_id2').combobox({
        url:path+"/info/getDesc2",
        valueField:'id',
        textField:'text'
    });
});

function  initInfoDataGrid() {
    $("#infoDataGrid").datagrid({
        fitColumns:true,  //防止水平滚动
        method:'post',
        idField:'id',
        fit:true,  //自动适应屏幕大小
        url:path+"/info/getAllInfo",
        pagination:true,  //DataGrid控件底部显示分页工具栏
        rownumbers:true,   //显示一个行号列
        singleSelect:true,  //只允许选择一行
        pageNumber:1,  //设置分页属性的时候初始化页码
        pageSize:10,    //设置分页属性的时候初始化页面大小
        pageList:[10,20,50],  //设置分页属性的时候 初始化页面大小选择列表
        columns:[[
            {field:'id',title:'房屋编号',width:60,align:'center'},
            {field:'text',title:'户型',width:100,align:'center'},
            {field:'type_id',hidden:'true'},
            {field:'staff_name',title:'管理员工',width:80,align:'center'},
            {field:'staff_id',hidden:'true'},
            {field:'info_adress',title:'房屋地址',width:100,align:'center'},
            {field:'info_price',title:'房屋价格(平米)',width:100,align:'center'},
            {field:'deleteInfo',title:'删除',width:80,align:'center',
                formatter: function(value,row,index){
                    return "<a href='javascript:deleteInfo()'>删除</a>";
                }
            },
        ]],
        // 在双击一个单元格的时候开始编辑并生成编辑器，然后定位到编辑器的输入框上
        onDblClickRow:function (rowIndex, rowData) {
            //alert(rowData.text+"--"+rowData.role_desc);

     // 初始化编辑窗口（增加或修改）
            initInfoEditWindow();
            //打开 编辑窗口
            $("#infoEditWindow").window('open');
            $("#infoEditWindow").window('center');  //将窗口绝对居中


            //双击时获取所选行的属性
            $("#info_id").val(rowData.id);
            $("#type_id2").combobox('setValue', rowData.type_id);
            $("#staff_id2").combobox('setValue', rowData.staff_id);
            $("#info_adress").textbox('setValue', rowData.info_adress);
            $("#info_price").numberbox('setValue', rowData.info_price);
        }

    });
}


//删除
function deleteInfo() {
    var node=$("#infoDataGrid").datagrid('getSelected');
    $.messager.confirm('提示', '请判断是否要执行删除操作?', function(b){
        if (b){
            $.ajax({
                url:path+"/info/deleteInfo",
                type:"post",
                data:{info_id:node.id},
                dataType:"json",
                success:function (data) {
                    if(data.info=="success"){
                        $.messager.alert("系统提示", "删除成功！","info");
                        refreshInfo();
                    }else{
                        $.messager.alert('系统提示','删除失败,请稍后再试!','error');
                    }
                }
            });
        }else{
            $.messager.alert('系统提示','删除失败,请稍后再试!','error');

        }
    });
}



//搜索
function findInfo(){
     var checked5 = $("#state5 option:selected").text();
    var info_text=$("#findInfoByName").textbox('getValue');

    var param={
        info_text:info_text,
        info_where:checked5
    };
    //重新加载数据
    $('#infoDataGrid').datagrid('reload',param);
}



/*-----------------------------------------------*/


// 初始化客户来源编辑窗口
function initInfoEditWindow(){
    $('#infoEditWindow').window({
        title:'房屋信息编辑窗口',   //标题
        collapsible:false,  //定义是否显示可折叠按钮。
        minimizable:false,  //定义是否显示最小化按钮
        maximizable:false,  //定义是够显示最大化按钮
        resizable:false,  //定义是否能够改变窗口大小
        width:400,
        height:360,
        modal:true,   //定义是否将窗体显示为模式化窗口
        closed:true,  //默认窗口是关闭
        onBeforeClose:function(){

            //退出时 清空窗口的内容
            $("#info_id").val("");
            $("#type_id2").combobox('setValue',"");//setValue : 设置组件的值。
            $("#staff_id2").combobox('setValue', "");
            $("#info_adress").textbox('setValue', "");
            $("#info_price").numberbox('setValue', "");
        }
    });
}


//增加按钮
function addInfo(){
    initInfoEditWindow();
    $("#infoEditWindow").window('open');
    $("#infoEditWindow").window('center');  //center: 将窗口绝对居中

}


//刷新
function refreshInfo(){
    $('#infoDataGrid').datagrid('reload');	// 重新载入所有行
}

//info_id,type_id,staff_id,info_adress,info_price
//保存按钮 (添加修改）
function saveInfo() {
    //判断输入框是否为空,自己写
    var type_id=$("#type_id2").combobox('getText');//类型
    var staff_id=$("#staff_id2").combobox('getText');//管理员
    var info_adress=$("#info_adress").textbox('getValue');//地址

    if( type_id.trim()==""|| type_id.trim()==null||
        staff_id.trim()==""|| staff_id.trim()==null ||
        info_adress.trim()==""|| info_adress.trim()==null){  //trim():排除空格
        $.messager.alert('系统提示','类型、管理员、地址输入框不能为空!','info');
    } else{

        //1.组织参数
        var param={
            info_id:$("#info_id").val(),
            type_id:$("#type_id2").combobox('getValue'),
            staff_id :$("#staff_id2").combobox('getValue'),
            info_adress:$("#info_adress").textbox('getValue'),
            info_price:$("#info_price").numberbox('getValue')
        };

        $.ajax({
            url:path+"/info/saveOrUpdateInfo",
            type:"post",
            data:param,
            dataType:"json",
            success:function (data) {
                if(data.info=="success"){
                    $.messager.alert('系统提示', '保存成功!');

                    //1.关闭窗口
                    closeInfo();
                    //2.刷新数据
                    refreshInfo();

                }else{
                    $.messager.alert('系统提示','保存失败!','error');
                }
            }
        });
    }

}


//关闭按钮
function closeInfo() {
    $('#infoEditWindow').window('close');  // close a window
}
