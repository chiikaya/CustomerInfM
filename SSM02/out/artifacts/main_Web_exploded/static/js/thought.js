$(function(){
    initThoughtDataGrid();
    //客户
    $('#KHByName').combobox({
        url:path+"/thought/getKH",
        valueField:'id',
        textField:'text'
    });
});

function  initThoughtDataGrid(){
    $("#thoughtDataGrid").datagrid({
        fitColumns:true,  //防止水平滚动
        method:'post',
        idField:'id',
        fit:true,  //自动适应屏幕大小
        url:path+"/thought/getAllThought",
        pagination:true,  //DataGrid控件底部显示分页工具栏
        rownumbers:true,   //显示一个行号列
        singleSelect:true,  //只允许选择一行
        pageNumber:1,  //设置分页属性的时候初始化页码
        pageSize:10,    //设置分页属性的时候初始化页面大小
        pageList:[10,20,50],  //设置分页属性的时候 初始化页面大小选择列表
        columns:[[
            {field:'id',title:'客户编号',width:60,align:'center'},
            {field:'text',title:'客户',width:70,align:'center'},
            {field:'client_id',hidden:'true'},
            {field:'thought_text',title:'关怀主题',width:80,align:'center'},
            {field:'thought_way',title:'关怀方式',width:70,align:'center'},
            {field:'create_time',title:'关怀时间',width:120,align:'center'},
            {field:'reply_time',title:'下次关怀时间',width:120,align:'center'},
            {field:'thought_desc',title:'备注',width:110,align:'center'},
            {field:'person',title:'关怀人',width:80,align:'center'},
            {field:'deleteThought',title:'删除',width:70,align:'center',
                formatter: function(value,row,index){
                    return "<a href='javascript:deleteThought()'>删除</a>";
                }
            },
        ]],

        // 在双击一个单元格的时候开始编辑并生成编辑器，然后定位到编辑器的输入框上
        onDblClickRow:function (rowIndex, rowData) {
            // alert(rowData.text+"--"+rowData.role_desc);

            // 初始化客户关怀编辑窗口（增加或修改）
            initThoughtEditWindow();
            //打开 编辑窗口
            $("#thoughtEditWindow").window('open');
            $("#thoughtEditWindow").window('center');  //将窗口绝对居中


            //双击时获取所选行的属性
            $("#thought_id").val(rowData.id);
            $("#KHByName").combobox('setValue', rowData.client_id);
            $("#thought_text").textbox('setValue', rowData.thought_text);
            $("#thought_way").textbox('setValue', rowData.thought_way);
            $("#create_time2").datetimebox('setValue', rowData.create_time);
            $("#reply_time2").datetimebox('setValue', rowData.reply_time);
            $("#thought_desc").textbox('setValue', rowData.thought_desc);
            $("#person").textbox('setValue', rowData.person);
            }
      });
}




//删除
function deleteThought() {
//获取当前选中的行
    var node=$("#thoughtDataGrid").datagrid('getSelected');
    $.messager.confirm('提示', '请判断是否要执行删除操作?', function(b){
        if (b){
            $.ajax({
                url:path+"/thought/deleteThought",
                type:"post",
                data:{thought_id:node.id},
                dataType:"json",
                success:function (data) {
                    if(data.info=="success"){
                        $.messager.alert("系统提示", "您确定要执行删除操作吗！","question");
                        refreshThought();
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
function findThought(){
    var thought_text=$("#findThoughtByName").textbox('getValue');
    var checked2 = $("#state2 option:selected").text();
    var param={
        thought_text:thought_text,
        thought_way:checked2
    };
    //重新加载数据
    $('#thoughtDataGrid').datagrid('reload',param);
}




/*--------------------------------------------*/
//刷新
function refreshThought(){
    $('#thoughtDataGrid').datagrid('reload');	// 重新载入所有行
}

// 初始化客户关怀编辑窗口
function initThoughtEditWindow(){
    $('#thoughtEditWindow').window({
        title:'客户关怀编辑窗口',   //标题
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
            $("#thought_id").val("");  //setValue : 设置组件的值。
            $("#KHByName").combobox('setValue',"");
            $("#thought_text").textbox('setValue',"");  // getValue : 获取组件的值。
            $("#thought_way").textbox('setValue',"");
            $("#create_time2").datetimebox('setValue',"");
            $("#reply_time2").datetimebox('setValue',"");
            $("#thought_desc").textbox('setValue',"");
            $("#person").textbox('setValue',"");
        }
    });
}


//增加客户关怀
function addThought(){
    initThoughtEditWindow();
    $("#thoughtEditWindow").window('open');
    $("#thoughtEditWindow").window('center');  //center: 将窗口绝对居中

}



//保存按钮 (添加修改）
function saveThought() {
    //判断输入框是否为空,自己写
    var  thought_text=$("#thought_text").textbox('getValue'); //主题
    var  thought_way=$("#thought_way").textbox('getValue');  //方式

    if(thought_text.trim()==""|| thought_text.trim()==null ||
        thought_way.trim()==""||thought_way.trim()==null){  //trim():排除空格
        $.messager.alert('系统提示','主题，方式输入框不能为空!','info');
    } else{

        //1.组织参数
        var param={
            thought_id:$("#thought_id").val(),
            client_id:$("#KHByName").combobox('getValue'),
            thought_text:$("#thought_text").textbox('getValue'),
            thought_way:$("#thought_way").textbox('getValue'),
            create_time2:$("#create_time2").datetimebox('getValue'),
            reply_time2:$("#reply_time2").datetimebox('getValue'),
            thought_desc:$("#thought_desc").textbox('getValue'),
            person:$("#person").textbox('getValue')
        };
        console.log(param);
        $.ajax({
            url:path+"/thought/saveOrUpdateThought",
            type:"post",
            data:param,
            dataType:"json",
            success:function (data) {
                console.log(data);
                if(data.info=="success"){
                    $.messager.alert('系统提示', '保存成功!');

                    //1.关闭窗口
                    closeThought();
                    //2.刷新数据
                    refreshThought();

                }else{
                    $.messager.alert('系统提示','保存失败!','error');
                }
            }
        });
    }

}


//关闭按钮
function closeThought() {
    $('#thoughtEditWindow').window('close');  // close a window
}