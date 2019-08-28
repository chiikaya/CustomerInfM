$(function () {
    initFromDataGrid();
});

function  initFromDataGrid() {
    $("#fromDataGrid").datagrid({
        fitColumns:true,  //防止水平滚动
        method:'post',
        idField:'id',
        fit:true,  //自动适应屏幕大小
        url:path+"/from/getAllFrom",
        pagination:true,  //DataGrid控件底部显示分页工具栏
        rownumbers:true,   //显示一个行号列
        singleSelect:true,  //只允许选择一行
        pageNumber:1,  //设置分页属性的时候初始化页码
        pageSize:10,    //设置分页属性的时候初始化页面大小
        pageList:[10,20,50],  //设置分页属性的时候 初始化页面大小选择列表
        columns:[[
            {field:'id',title:'来源编号',width:60,align:'center',hidden:'true'},
            {field:'text',title:'来源名称',width:60,align:'center'},
            {field:'deleteFrom',title:'删除',width:70,align:'center',
                formatter: function(value,row,index){
                    return "<a href='javascript:deleteFrom()'>删除</a>";
                }
            },
        ]],
        // 在双击一个单元格的时候开始编辑并生成编辑器，然后定位到编辑器的输入框上
        onDblClickRow:function (rowIndex, rowData) {
            //alert(rowData.text+"--"+rowData.role_desc);

            // 初始化客户来源编辑窗口（增加或修改）
            initFromEditWindow();
            //打开 编辑窗口
            $("#fromEditWindow").window('open');
            $("#fromEditWindow").window('center');  //将窗口绝对居中


            //双击时获取所选行的属性
            $("#from_id").val(rowData.id);
            $("#from_name").textbox('setValue', rowData.text);
        }

    });
}

//删除
function deleteFrom() {
    var node=$("#fromDataGrid").datagrid('getSelected');
    $.messager.confirm('提示', '请判断是否要执行删除操作?', function(b){
        if (b){
            $.ajax({
                url:path+"/from/deleteFrom",
                type:"post",
                data:{from_id:node.id},
                dataType:"json",
                success:function (data) {
                    if(data.info=="success"){
                        $.messager.alert("系统提示", "删除成功！","info");
                        refreshFrom();
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
function findFrom(){
    var from_name=$("#findFromByname").textbox('getValue');
    var param={
        from_name:from_name
    };
    //重新加载数据
    $('#fromDataGrid').datagrid('reload',param);
}



/*--------------------------------------------------------------*/

// 初始化客户来源编辑窗口
function initFromEditWindow(){
    $('#fromEditWindow').window({
        title:'客户来源编辑窗口',   //标题
        collapsible:false,  //定义是否显示可折叠按钮。
        minimizable:false,  //定义是否显示最小化按钮
        maximizable:false,  //定义是够显示最大化按钮
        resizable:false,  //定义是否能够改变窗口大小
        width:360,
        height:160,
        modal:true,   //定义是否将窗体显示为模式化窗口
        closed:true,  //默认窗口是关闭
        onBeforeClose:function(){

            //退出时 清空窗口的内容
            $("#from_id").val("");
            $("#from_name").textbox('setValue',"");  //setValue : 设置组件的值。
        }
    });
}


//增加按钮
function addFrom(){
    initFromEditWindow();
    $("#fromEditWindow").window('open');
    $("#fromEditWindow").window('center');  //center: 将窗口绝对居中

}


//刷新
function refreshFrom(){
    $('#fromDataGrid').datagrid('reload');	// 重新载入所有行
}


//保存按钮 (添加修改）
function saveFrom() {
    //判断输入框是否为空,自己写
    var  from_name=$("#from_name").textbox('getValue'); //来源名称

    if( from_name.trim()==""|| from_name.trim()==null){  //trim():排除空格
        $.messager.alert('系统提示','输入框不能为空!','info');
    } else{

        //1.组织参数
        var param={
            from_id:$("#from_id").val(),
            from_name:$("#from_name").textbox('getValue')
        };

        $.ajax({
            url:path+"/from/saveOrUpdateFrom",
            type:"post",
            data:param,
            dataType:"json",
            success:function (data) {
                if(data.info=="success"){
                    $.messager.alert('系统提示', '保存成功!');

                    //1.关闭窗口
                    closeFrom();
                    //2.刷新数据
                    refreshFrom();

                }else{
                    $.messager.alert('系统提示','保存失败!','error');
                }
            }
        });
    }

}


//关闭按钮
function closeFrom() {
    $('#fromEditWindow').window('close');  // close a window
}