$(function(){
    initDeptDataGrid();
});

function  initDeptDataGrid(){
    $("#deptDataGrid").datagrid({
        fitColumns:true,  //防止水平滚动
        method:'post',
        idField:'id',
        fit:true,  //自动适应屏幕大小
        url:path+"/dept/getAllDept",
        pagination:true,  //DataGrid控件底部显示分页工具栏
        rownumbers:true,   //显示一个行号列
        singleSelect:true,  //只允许选择一行
        pageNumber:1,  //设置分页属性的时候初始化页码
        pageSize:10,    //设置分页属性的时候初始化页面大小
        pageList:[10,20,50],  //设置分页属性的时候 初始化页面大小选择列表
        columns:[[
            {field:'id',title:'部门编号',width:70,align:'center'},
            {field:'text',title:'部门名称',width:80,align:'center'},
            {field:'dept_desc',title:'部门描述',width:100,align:'center'},
            {field:'dept_location',title:'部门位置',width:100,align:'center'},
            {field:'inuse',title:'是否可用',width:70,align:'center',
                formatter: function(value,row,index){
                    if (value=="0"){
                        return "可用";
                    } else {
                        return "禁用";
                    }
                }
            },
            {field:'deleteDept',title:'删除',width:70,align:'center',
                formatter: function(value,row,index){
                    return "<a href='javascript:deleteDept()'>删除</a>";
                }
            },
        ]],

        // 在双击一个单元格的时候开始编辑并生成编辑器，然后定位到编辑器的输入框上
        onDblClickRow:function (rowIndex, rowData) {
           //  alert(rowData.text+"--"+rowData.role_desc);
            // 初始化部门编辑窗口（增加或修改）
            initDeptEditWindow();
            //打开 编辑窗口
            $("#deptEditWindow").window('open');
            $("#deptEditWindow").window('center');  //将窗口绝对居中


            //双击时获取所选行的属性
            $("#dept_id").val(rowData.id);
            $("#dept_name").textbox('setValue',rowData.text);
            $("#dept_desc").textbox('setValue',rowData.dept_desc);
            $("#dept_location").textbox('setValue',rowData.dept_location);
            $("#inuse3").combobox('setValue',rowData.inuse);
        }
    });
}


//删除
function deleteDept() {
//获取当前选中的行
    var node=$("#deptDataGrid").datagrid('getSelected');
    $.messager.confirm('提示', '请判断是否要执行删除操作?', function(b){
        if (b){
            $.ajax({
                url:path+"/dept/deleteDept",
                type:"post",
                data:{dept_id:node.id},
                dataType:"json",
                success:function (data) {
                    if(data.info=="success"){
                      //  $.messager.alert("系统提示", "您确定要执行删除操作吗！","question");
                        refreshDept(); //刷新
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


/*-------------------------------------------------*/
//搜索
function findDept(){
    var dept_name=$("#findByName").textbox('getValue');
    var dept_desc=$("#findByDesc").textbox('getValue');
    var param={
        dept_name:dept_name,
        dept_desc:dept_desc
    };
    //重新加载数据
    $('#deptDataGrid').datagrid('reload',param);
}


/*--------------------------------------------------------------*/

// 初始化部门编辑窗口
function initDeptEditWindow(){
    $('#deptEditWindow').window({
        title:'部门编辑',   //标题
        collapsible:false,  //定义是否显示可折叠按钮。
        minimizable:false,  //定义是否显示最小化按钮
        maximizable:false,  //定义是够显示最大化按钮
        resizable:false,  //定义是否能够改变窗口大小
        width:400,
        height:300,
        modal:true,   //定义是否将窗体显示为模式化窗口
        closed:true,  //默认窗口是关闭
        onBeforeClose:function(){
            //退出时 清空窗口的内容
            $("#dept_id").val("");
            $("#dept_name").textbox('setValue',"");  //setValue : 设置组件的值。
            $("#dept_desc").textbox('setValue',"");  // getValue : 获取组件的值。
            $("#dept_location").textbox('setValue',"");
            $("#inuse3").combobox('setValue',"0");
        }
    });
}


//增加角色
function addDept(){
    initDeptEditWindow();
    $("#deptEditWindow").window('open');
    $("#deptEditWindow").window('center');  //center: 将窗口绝对居中

}


//刷新角色
function refreshDept(){
    $('#deptDataGrid').datagrid('reload');	// 重新载入所有行
}


//保存按钮 (添加修改）
function saveDept() {
    //判断输入框是否为空,自己写
    var  dept_name=$("#dept_name").textbox('getValue'); //名称
    var  dept_desc=$("#dept_desc").textbox('getValue'); //描述

    if( dept_name.trim()==""|| dept_name.trim()==null ||
        dept_desc.trim()==""|| dept_desc.trim()==null){  //trim():排除空格
        $.messager.alert('系统提示','输入框不能为空!','info');
    } else{
        //1.组织参数
        var param={
            dept_id:$("#dept_id").val(),
            dept_name:$("#dept_name").textbox('getValue'),
            dept_desc:$("#dept_desc").textbox('getValue'),
            dept_location:$("#dept_location").textbox('getValue'),
            inuse:$("#inuse3").combobox('getValue')
        };

        $.ajax({
            url:path+"/dept/saveOrUpdateDept",
            type:"post",
            data:param,
            dataType:"json",
            success:function (data) {
                if(data.info=="success"){
                    $.messager.alert('系统提示', '保存成功!');

                    //1.关闭窗口
                    closeDept();
                    //2.刷新数据
                    refreshDept();

                }else{
                    $.messager.alert('系统提示','保存失败!','error');
                }
            }
        });
    }
}


//关闭按钮
function closeDept() {
    $('#deptEditWindow').window('close');  // close a window
}