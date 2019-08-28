$(function(){
    initRoleDataGrid();
    var authArray = ['addRole'];
    empowerResource(authArray);
});

function  initRoleDataGrid(){
    $("#roleDataGrid").datagrid({
        fitColumns:true,  //防止水平滚动
        method:'post',
        idField:'id',
        fit:true,  //自动适应屏幕大小
        url:path+"/role/getAllRole",
        pagination:true,  //DataGrid控件底部显示分页工具栏
        rownumbers:true,   //显示一个行号列
        singleSelect:true,  //只允许选择一行
        pageNumber:1,  //设置分页属性的时候初始化页码
        pageSize:10,    //设置分页属性的时候初始化页面大小
        pageList:[10,20,50],  //设置分页属性的时候 初始化页面大小选择列表
        columns:[[
            {field:'id',title:'角色编号',width:70,align:'center'},
            {field:'text',title:'角色名称',width:80,align:'center'},
            {field:'role_desc',title:'角色描述',width:110,align:'center'},
            {field:'role_order',title:'角色排序',width:70,align:'center'},
            {field:'inuse',title:'是否可用',width:70,align:'center',
                formatter: function(value,row,index){
                    if (value=="0"){
                        return "可用";
                    } else {
                        return "禁用";
                    }
                }
            },
            {field:'empower2',title:'授权',width:70,align:'center',
                formatter: function(value,row,index){
                   if(resArray.indexOf('empowerButton')==-1){
                        return "~ ~";
                    }else {
                        return "<a name='empowerButton' href='javascript:empower2()'>授权</a>";
                      }
                    }
                },
            {field:'deleteRole',title:'删除',width:70,align:'center',
                formatter: function(value,row,index){
                    return "<a href='javascript:deleteRole()'>删除</a>";
                }
            },
        ]],

        // 在双击一个单元格的时候开始编辑并生成编辑器，然后定位到编辑器的输入框上
        onDblClickRow:function (rowIndex, rowData) {
            // alert(rowData.text+"--"+rowData.role_desc);

         if(resArray.indexOf('editRole')!=-1){

      }else{
             // 初始化角色编辑窗口（增加或修改）
             initRoleEditWindow();
             //打开 编辑窗口
             $("#roleEditWindow").window('open');
             $("#roleEditWindow").window('center');  //将窗口绝对居中


             //双击时获取所选行的属性
             $("#role_id").val(rowData.id);
             $("#role_name").textbox('setValue', rowData.text);
             $("#role_desc").textbox('setValue', rowData.role_desc);
             $("#role_order").textbox('setValue', rowData.role_order);
             $("#inuse2").combobox('setValue', rowData.inuse);
       }

        }

    });
}


//删除
function deleteRole() {
//获取当前选中的行
    var node=$("#roleDataGrid").datagrid('getSelected');
    $.messager.confirm('提示', '请判断是否要执行删除操作?', function(b){
        if (b){
            $.ajax({
                url:path+"/role/deleteRole",
                type:"post",
                data:{role_id:node.id},
                dataType:"json",
                success:function (data) {
                    if(data.info=="success"){
                        $.messager.alert("系统提示", "您确定要执行删除操作吗！","info");
                        refreshRole();
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


//授权
function empower2(){
    initEmpowerAuthWindow();
    //打开窗口
    $('#empowerAuthWindow').window('open');
    $('#empowerAuthWindow').window('center'); //将窗口绝对居中
}
//初始化授权窗口
function  initEmpowerAuthWindow(){
    $('#empowerAuthWindow').window({
        title: '角色授权',   //标题
        collapsible: false,  //定义是否显示可折叠按钮。
        minimizable: false,  //定义是否显示最小化按钮
        maximizable: false,  //定义是够显示最大化按钮
        resizable: false,  //定义是否能够改变窗口大小
        width: 400,
        height: 400,
        modal: true,   //定义是否将窗体显示为模式化窗口
        closed: true,  //默认窗口是关闭
    });
    initEmpowerAuthTree();  //初始化tree
}
//
function initEmpowerAuthTree(){
    //获取当前选中的行
    var node=$("#roleDataGrid").datagrid('getSelected');
    $("#empowerAuthTree").tree({
        url:path+"/role/getAuthByRoleId?role_id="+node.id,
        method:'get',
        animate:true,
        checkbox:true,  //定义是否在每一个借点之前都显示复选框
        cascadeCheck:false,
    });
}
//授权保存按钮
function savaAuthOfRole(){
    //获取当前选中的行
    var node=$("#roleDataGrid").datagrid('getSelected');
    //获取所有选中的节点
    var nodes = $('#empowerAuthTree').tree('getChecked');
    var arr=[];
    //把所有已选中节点的id存入数组
    if(node!=null && nodes.length>0){
        for(var i=0;i<nodes.length;i++){
            arr.push(nodes[i].id);
        }
    }
    $.ajax({
        url:path+"/role/saveAuthOfRole",
        type:"post",
        data:{role_id:node.id,array:arr},
        dataType:"json",
        success:function (data) {
            if(data.info=="success"){
                $.messager.alert('系统提示',"保存成功!");
                $('#empowerAuthWindow').window('close');
            }else{
                $.messager.alert('系统提示','保存失败,请稍后再试!','error');
            }
        }
    });
}
//授权关闭按钮
function closeempowerAuthWindow(){
    $('#empowerAuthWindow').window('close');  // close a window
}








//搜索
function findRole(){
    var role_name=$("#findRoleByName").textbox('getValue');
    var role_desc=$("#byDesc").textbox('getValue');
    var param={
        role_name:role_name,
        role_desc:role_desc
    };
    //重新加载数据
    $('#roleDataGrid').datagrid('reload',param);
}



/*--------------------------------------------------------------*/

// 初始化角色编辑窗口
function initRoleEditWindow(){
    $('#roleEditWindow').window({
        title:'角色编辑',   //标题
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
            $("#role_id").val("");
            $("#role_name").textbox('setValue',"");  //setValue : 设置组件的值。
            $("#role_desc").textbox('setValue',"");  // getValue : 获取组件的值。
            $("#role_order").textbox('setValue',"");
            $("#inuse2").combobox('setValue',"0");
        }
    });
}


//增加角色
function addRole(){
    initRoleEditWindow();
    $("#roleEditWindow").window('open');
    $("#roleEditWindow").window('center');  //center: 将窗口绝对居中

}


//刷新角色
function refreshRole(){
    $('#roleDataGrid').datagrid('reload');	// 重新载入所有行
}


//保存按钮 (添加修改）
function saveRole() {
    //判断输入框是否为空,自己写
    var  role_name=$("#role_name").textbox('getValue'); //名称
    var  role_desc=$("#role_desc").textbox('getValue'); //描述
    var  role_order=$("#role_order").textbox('getValue');  //排序

    if( role_name.trim()==""|| role_name.trim()==null ||
        role_desc.trim()==""|| role_desc.trim()==null ||
        role_desc.trim()==""||role_desc.trim()==null){  //trim():排除空格
        $.messager.alert('系统提示','输入框不能为空!','info');
    } else{

        //1.组织参数
        var param={
            role_id:$("#role_id").val(),
            role_name:$("#role_name").textbox('getValue'),
            role_desc:$("#role_desc").textbox('getValue'),
            role_order:$("#role_order").textbox('getValue'),
            inuse:$("#inuse2").combobox('getValue')
        };

        $.ajax({
            url:path+"/role/saveOrUpdateRole",
            type:"post",
            data:param,
            dataType:"json",
            success:function (data) {
                if(data.info=="success"){
                    // $.messager.alert('系统提示','保存成功!','success');
                    $.messager.alert('系统提示', '保存成功!');

                    //1.关闭窗口
                    closeRole();
                    //2.刷新数据
                    refreshRole();

                }else{
                    $.messager.alert('系统提示','保存失败!','error');
                }
            }
        });
    }

}


//关闭按钮
function closeRole() {
    $('#roleEditWindow').window('close');  // close a window
}