$(function(){
    initRecordDataGrid();
    //客户姓名
    $('#KHName').combobox({
        url:path+"/record/getKH",
        valueField:'id',
        textField:'text'
    });
    //联系人姓名
    $('#record_who').combobox({
        url:path+"/record/getPerson",
        valueField:'id',
        textField:'text'
    });

});

function  initRecordDataGrid(){
    $("#recordDataGrid").datagrid({
        fitColumns:true,  //防止水平滚动
        method:'post',
        idField:'id',
        fit:true,  //自动适应屏幕大小
        url:path+"/record/getAllRecord",
        pagination:true,  //DataGrid控件底部显示分页工具栏
        rownumbers:true,   //显示一个行号列
        singleSelect:true,  //只允许选择一行
        pageNumber:1,  //设置分页属性的时候初始化页码
        pageSize:10,    //设置分页属性的时候初始化页面大小
        pageList:[10,20,50],  //设置分页属性的时候 初始化页面大小选择列表
        columns:[[
            {field:'id',title:'客户编号',width:60,align:'center'},
            {field:'text',title:'客户姓名',width:70,align:'center'},
            {field:'client_id',hidden:'true'},
            {field:'create_time',title:'联系时间',width:120,align:'center'},
            {field:'reply_time',title:'下次联系时间',width:120,align:'center'},
            {field:'record_type',title:'联系类型',width:80,align:'center'},
            {field:'person_name',title:'是谁联系',width:70,align:'center'},
            {field:'person_id',hidden:'true'},
            {field:'record_text',title:'联系主题',width:80,align:'center'},
            {field:'record_desc',title:'备注',width:110,align:'center'},

            {field:'deleteRecord',title:'删除',width:70,align:'center',
                formatter: function(value,row,index){
                    return "<a href='javascript:deleteRecord()'>删除</a>";
                }
            },
        ]],

        // 在双击一个单元格的时候开始编辑并生成编辑器，然后定位到编辑器的输入框上
        onDblClickRow:function (rowIndex, rowData) {

            // alert(rowData.text+"--"+rowData.role_desc);

            // 初始化联系记录编辑窗口（增加或修改）
            initRecordEditWindow();
            //打开 编辑窗口
            $("#recordEditWindow").window('open');
            $("#recordEditWindow").window('center');  //将窗口绝对居中

            <!--record_id,record_name,create_time,reply_time, record_type,record_who,record_text,record_desc-->
            //双击时获取所选行的属性
            $("#record_id").val(rowData.id);
            $("#KHName").combobox('setValue', rowData.client_id);
            $("#create_time").datetimebox('setValue', rowData.create_time);
            $("#reply_time").datetimebox('setValue', rowData.reply_time);
            $("#record_type").textbox('setValue', rowData.record_type);
            $("#record_who").combobox('setValue', rowData.person_id);
            $("#record_text").textbox('setValue', rowData.record_text);
            $("#record_desc").textbox('setValue', rowData.record_desc);

        }
    });
}


//删除
function deleteRecord() {
//获取当前选中的行
    var node=$("#recordDataGrid").datagrid('getSelected');
    $.messager.confirm('提示', '请判断是否要执行删除操作?', function(b){
        if (b){
            $.ajax({
                url:path+"/record/deleteRecord",
                type:"post",
                data:{record_id:node.id},
                dataType:"json",
                success:function (data) {
                    if(data.info=="success"){
                        $.messager.alert("系统提示", "您确定要执行删除操作吗！","question");
                        refreshRecord();
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





/*-----------------------------------------------------*/

//搜索
function findRecord(){
    var record_text=$("#findREByName").textbox('getValue');
    var checked3 = $("#state3 option:selected").text();
    var param={
        record_text:record_text,
        record_way:checked3
    };
    //重新加载数据
    $('#recordDataGrid').datagrid('reload',param);
}



/*--------------------------------------------*/
//刷新
function refreshRecord(){
    $('#recordDataGrid').datagrid('reload');	// 重新载入所有行
}

// 初始化联系记录编辑窗口
function initRecordEditWindow(){
    $('#recordEditWindow').window({
        title:'联系记录编辑窗口',   //标题
        collapsible:false,  //定义是否显示可折叠按钮。
        minimizable:false,  //定义是否显示最小化按钮
        maximizable:false,  //定义是够显示最大化按钮
        resizable:false,  //定义是否能够改变窗口大小
        width:400,
        height:400,
        modal:true,   //定义是否将窗体显示为模式化窗口
        closed:true,  //默认窗口是关闭
        onBeforeClose:function(){

            //退出时 清空窗口的内容
            $("#record_id").val("");
            $("#KHName").combobox('setValue',"");  //setValue : 设置组件的值。
            $("#create_time").datetimebox('setValue',"");
            $("#reply_time").datetimebox('setValue',"");
            $("#record_type").textbox('setValue',"");  // getValue : 获取组件的值。
            $("#record_who").combobox('setValue',"");
            $("#record_text").textbox('setValue',"");
            $("#record_desc").textbox('setValue',"");
        }
    });
}


//增加按钮（联系记录）
function addRecord(){
    initRecordEditWindow();
    $("#recordEditWindow").window('open');
    $("#recordEditWindow").window('center');  //center: 将窗口绝对居中

}


//保存按钮 (添加修改）
function saveRecord() {
    //判断输入框是否为空,自己写
    var  record_type=$("#record_type").textbox('getValue'); //类型
    var  record_text=$("#record_text").textbox('getValue');  //主题
    console.log($("#KHName").combobox('getValue'));

    if(record_type.trim()==""|| record_type.trim()==null ||
        record_text.trim()==""||record_text.trim()==null){  //trim():排除空格
        $.messager.alert('系统提示','输入框不能为空!','info');
    } else{

        //1.组织参数
        var param={
            record_id:$("#record_id").val(),
            client_id:$("#KHName").combobox('getValue'),
            create_time:$("#create_time").datetimebox('getValue'),
            reply_time:$("#reply_time").datetimebox('getValue'),
            record_type:$("#record_type").textbox('getValue'),
            record_who:$("#record_who").combobox('getValue'),
            record_text:$("#record_text").textbox('getValue'),
            record_desc:$("#record_desc").textbox('getValue')
        };

        $.ajax({
            url:path+"/record/saveOrUpdateRecord",
            type:"post",
            data:param,
            dataType:"json",
            success:function (data) {
                if(data.info=="success"){
                    $.messager.alert('系统提示', '保存成功!');

                    //1.关闭窗口
                    closeRecord();
                    //2.刷新数据
                    refreshRecord();

                }else{
                    $.messager.alert('系统提示','保存失败!','error');
                }
            }
        });
    }

}


//关闭按钮
function closeRecord() {
    $('#recordEditWindow').window('close');  // close a window
}