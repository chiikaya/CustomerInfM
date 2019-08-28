$(function () {
    initStatusDataGrid();
});

function  initStatusDataGrid() {
    $("#statusDataGrid").datagrid({
        fitColumns:true,  //防止水平滚动
        method:'post',
        idField:'id',
        fit:true,  //自动适应屏幕大小
        url:path+"/status/getAllStatus",
        pagination:true,  //DataGrid控件底部显示分页工具栏
        rownumbers:true,   //显示一个行号列
        singleSelect:true,  //只允许选择一行
        pageNumber:1,  //设置分页属性的时候初始化页码
        pageSize:10,    //设置分页属性的时候初始化页面大小
        pageList:[10,20,50],  //设置分页属性的时候 初始化页面大小选择列表
        columns:[[
            {field:'id',title:'状态编号',width:60,align:'center',hidden:'true'},
            {field:'text',title:'状态名称',width:60,align:'center'},
            {field:'status_desc',title:'状态描述',width:100,align:'center'},
            {field:'deleteStatus',title:'删除',width:70,align:'center',
                formatter: function(value,row,index){
                    return "<a href='javascript:deleteStatus()'>删除</a>";
                }
            },
        ]],
        // 在双击一个单元格的时候开始编辑并生成编辑器，然后定位到编辑器的输入框上
        onDblClickRow:function (rowIndex, rowData) {
            //alert(rowData.text+"--"+rowData.role_desc);

            // 初始化客户关怀编辑窗口（增加或修改）
            initStatusEditWindow();
            //打开 编辑窗口
            $("#statusEditWindow").window('open');
            $("#statusEditWindow").window('center');  //将窗口绝对居中


            //双击时获取所选行的属性
            $("#status_id").val(rowData.id);
            $("#status_inuse").textbox('setValue', rowData.text);
            $("#status_desc").textbox('setValue', rowData.status_desc);

        }

    });
}

//删除
function deleteStatus() {
    var node=$("#statusDataGrid").datagrid('getSelected');
    $.messager.confirm('提示', '请判断是否要执行删除操作?', function(b){
        if (b){
            $.ajax({
                url:path+"/status/deleteStatus",
                type:"post",
                data:{status_id:node.id},
                dataType:"json",
                success:function (data) {
                    if(data.info=="success"){
                        $.messager.alert("系统提示", "删除成功！","info");
                        refreshStatus();
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
function findStatus(){
    var status_inuse=$("#findStatusByInuse").textbox('getValue');
    var param={
        status_inuse:status_inuse
    };
    //重新加载数据
    $('#statusDataGrid').datagrid('reload',param);
}





/*--------------------------------------------------------------*/

// 初始化客户状态编辑窗口
function initStatusEditWindow(){
    $('#statusEditWindow').window({
        title:'客户状态编辑窗口',   //标题
        collapsible:false,  //定义是否显示可折叠按钮。
        minimizable:false,  //定义是否显示最小化按钮
        maximizable:false,  //定义是够显示最大化按钮
        resizable:false,  //定义是否能够改变窗口大小
        width:380,
        height:230,
        modal:true,   //定义是否将窗体显示为模式化窗口
        closed:true,  //默认窗口是关闭
        onBeforeClose:function(){

            //退出时 清空窗口的内容
            $("#status_id").val("");
            $("#status_inuse").textbox('setValue',"");  //setValue : 设置组件的值。
            $("#status_desc").textbox('setValue',"");  // getValue : 获取组件的值。
        }
    });
}


//增加按钮
function addStatus(){
    initStatusEditWindow();
    $("#statusEditWindow").window('open');
    $("#statusEditWindow").window('center');  //center: 将窗口绝对居中

}


//刷新
function refreshStatus(){
    $('#statusDataGrid').datagrid('reload');	// 重新载入所有行
}


//保存按钮 (添加修改）
function saveStatus() {
    //判断输入框是否为空,自己写
    var  status_inuse=$("#status_inuse").textbox('getValue'); //状态

    if( status_inuse.trim()==""|| status_inuse.trim()==null){  //trim():排除空格
        $.messager.alert('系统提示','输入框不能为空!','info');
    } else{

        //1.组织参数
        var param={
            status_id:$("#status_id").val(),
            status_inuse:$("#status_inuse").textbox('getValue'),
            status_desc:$("#status_desc").textbox('getValue')
        };

        $.ajax({
            url:path+"/status/saveOrUpdateStatus",
            type:"post",
            data:param,
            dataType:"json",
            success:function (data) {
                if(data.info=="success"){
                    $.messager.alert('系统提示', '保存成功!');

                    //1.关闭窗口
                    closeStatus();
                    //2.刷新数据
                    refreshStatus();

                }else{
                    $.messager.alert('系统提示','保存失败!','error');
                }
            }
        });
    }

}


//关闭按钮
function closeStatus() {
    $('#statusEditWindow').window('close');  // close a window
}