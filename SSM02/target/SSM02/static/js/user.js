$(function () {
    initUserDataGrid();

    //部门
    $('#user_dept').combobox({
        url:path+"/user/getUSDept",
        valueField:'id',
        textField:'text'
    });
});

function  initUserDataGrid() {
    $("#userDataGrid").datagrid({
        fitColumns:true,  //防止水平滚动
        method:'post',
        idField:'id',
        fit:true,  //自动适应屏幕大小
        url:path+"/user/getAllUser",
        pagination:true,  //DataGrid控件底部显示分页工具栏
        rownumbers:true,   //显示一个行号列
        singleSelect:true,  //只允许选择一行
        pageNumber:1,  //设置分页属性的时候初始化页码
        pageSize:10,    //设置分页属性的时候初始化页面大小
        pageList:[10,20,50],  //设置分页属性的时候 初始化页面大小选择列表
        columns:[[
            {field:'id',title:'用户编号',width:60,align:'center'},
            {field:'text',title:'用户名称',width:60,align:'center'},
            {field:'password',title:'用户密码',width:60,align:'center'},
            {field:'tel',title:'电话号码',width:60,align:'center'},
            {field:'email',title:'电子邮箱',width:60,align:'center'},
            {field:'dept_name',title:'所属部门',width:60,align:'center'},
            {field:'inuse',title:'是否可用',width:60,align:'center',
                formatter: function(value,row,index){
                    if (value=="0"){
                        return "可用";
                    } else {
                        return "禁用";
                    }
                }
            },
            {field:'empower',title:'授权',width:70,align:'center',
                formatter: function(value,row,index){
                    return "<a name='empowerButton' href='javascript:empower()'>授权</a>";

                }
            },
           /* {
                field: 'change_dept', title: '更改部门', width: 80,align:'center',
                formatter: function () {
                    return "<a href='javascript:changeDept()'>更改部门</a>"
                }
            },*/
            {
                field: 'delete_user', title: '删除', width: 70,align:'center',
                formatter: function () {
                    return "<a href='javascript:deleteUser()'>删除</a>";
                }
            }
        ]],
        // 在双击一个单元格的时候开始编辑并生成编辑器，然后定位到编辑器的输入框上
        onDblClickRow:function (rowIndex, rowData) {
            //alert(rowData.text+"--"+rowData.role_desc);
                //初始化用户编辑窗口（增加或修改）
                initUserEditWindow();
                //打开 编辑窗口
                $("#userEditWindow").window('open');
                $("#userEditWindow").window('center');  //将窗口绝对居中

                //双击时获取所选行的属性
                $("#user_id").val(rowData.id);
                $("#username").textbox('setValue', rowData.text);
                $("#password").textbox('setValue', rowData.password);
                $("#pwd2").passwordbox('setValue', rowData.password);
                $("#tel").numberbox('setValue', rowData.tel);
                $("#email").textbox('setValue', rowData.email);
                $("#user_dept").combobox('setValue', rowData.dept_id);
                $("#inuse1").combobox('setValue', rowData.inuse);

        }

    });
}
/*

//更改部门
function changeDept() {
    initChangeDeptWindow(); //初始化更改部门的编辑窗口
    $("#changeDeptWindow").window('open');
    $("#changeDeptWindow").window('center');//将窗口绝对居中
}
//----------更改部门------
//初始化更改部门的编辑窗口
function initChangeDeptWindow() {

    $("#changeDeptWindow").window({
        title: '更改部门',
        collapsible: false,
        minimizable: false,
        maximizable: false,
        resizable: false,
        width: 260,
        height: 200,
        modal: true,
        closed: true,

    });
    initChangeDeptTree();//初始化tree
}
//更改部门的超链接地址
function initChangeDeptTree() {
    var node = $("#userDataGrid").datagrid('getSelected');
    $("#changeDeptTree").tree({
        url: path + "/user/changeUserDept?user_id=" + node.id,
        method: 'get',
        animate: true,
        checkbox: true,

        /!*单选*!/
        onCheck: function (node, checked) {
            if (checked) {
                var nodes = $('#changeDeptTree').tree('getChecked');
                if (nodes.length > 1) {
                    for (var i = 0; i < nodes.length; i++) {
                        $('#changeDeptTree').tree('uncheck', nodes[i].target)
                    }
                    $('#changeDeptTree').tree('check', node.target);
                }
            }

        },

    });
}
/!*部门的关闭窗口*!/
function closeDeptUserWindow() {
    $("#changeDeptWindow").window('close');
}
/!*保存部门*!/
function saveDptOfUser() {

    /!*当前选中的行*!/
    var node = $("#userDataGrid").datagrid('getSelected');
    var nodes = $('#changeDeptTree').tree('getChecked');


    if (nodes != null && nodes.length > 0) {
        for (var i = 0; i < nodes.length; i++) {
            var dept_id = nodes[i].id;
        }
    }
    if(dept_id==""||dept_id==0 || dept_id==null){
        alert("请选择部门!");
    }else{
        var param = {user_id:node.id,dept_id:dept_id};
        $.ajax({
            url: path + "/user/saveDptOfUser",
            type: "post",
            data: param,
            dataType: "json",
            success: function (data) {
                if (data.info == "success") {
                    $.messager.alert('系统提示', "保存成功!");
                    //1.关闭窗口
                    closeUser();
                    //2.刷新数据
                    refreshUser();
                } else {
                    $.messager.alert('系统提示', "保存失败，请稍后再试!");
                }
            }
        });
    }
}
*/

/*--------------------------------------------*/

//删除用户
function deleteUser() {
    var node=$("#userDataGrid").datagrid('getSelected');
    $.messager.confirm('提示', '请判断是否要执行删除操作?', function(b){
        if (b){
            $.ajax({
                url:path+"/user/deleteUser",
                type:"post",
                data:{user_id:node.id},
                dataType:"json",
                success:function (data) {
                    if(data.info=="success"){
                        $.messager.alert("系统提示", "删除成功！","info");
                        refreshUser();
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

//授权
function empower(){
    initEmpowerRoleWindow();//初始化授权编辑窗口
    //打开窗口
    $('#empowerRoleWindow').window('open');
    $('#empowerRoleWindow').window('center'); //将窗口绝对居中
}
//初始化授权窗口
function  initEmpowerRoleWindow(){
    $('#empowerRoleWindow').window({
        title: '用户授权',   //标题
        collapsible: false,  //定义是否显示可折叠按钮。
        minimizable: false,  //定义是否显示最小化按钮
        maximizable: false,  //定义是够显示最大化按钮
        resizable: false,  //定义是否能够改变窗口大小
        width: 300,
        height: 300,
        modal: true,   //定义是否将窗体显示为模式化窗口
        closed: true,  //默认窗口是关闭
    });
    initEmpowerRoleTree();  //初始化tree
}
//
function initEmpowerRoleTree(){
    //获取当前选中的行
    var node=$("#userDataGrid").datagrid('getSelected');
    $("#empowerRoleTree").tree({
        url:path+"/user/getRoleByUserId?user_id="+node.id,
        method:'get',
        animate:true,
        checkbox:true,  //定义是否在每一个借点之前都显示复选框
    });
}
//授权保存按钮
function savaRoleOfUser2(){
    //获取当前选中的行
    var node=$("#userDataGrid").datagrid('getSelected');
    //获取所有选中的节点
    var nodes = $('#empowerRoleTree').tree('getChecked');
    var arr=[];
    //把所有已选中节点的id存入数组
    if(node!=null && nodes.length>0){
        for(var i=0;i<nodes.length;i++){
            arr.push(nodes[i].id);
        }
    }

    $.ajax({
        url:path+"/user/saveRoleOfUser",
        type:"post",
        data:{user_id:node.id,array:arr},
        dataType:"json",
        success:function (data) {
            if(data.info=="success"){
                $.messager.alert('系统提示',"保存成功!");
                //关闭
                $('#empowerRoleWindow').window('close');  // close a window
            }else{
                $.messager.alert('系统提示','保存失败,请稍后再试!','error');
            }
        }
    });
}
//授权关闭按钮
function closeempowerRoleWindow(){
    $('#empowerRoleWindow').window('close');  // close a window
}



//搜索
function findUser(){
    var username=$("#findUserbyName").textbox('getValue');
    var param={
        username:username
    };
    //重新加载数据
    $('#userDataGrid').datagrid('reload',param);
}


/*---------------------------------------------------------*/
// 初始化用户编辑窗口
function initUserEditWindow(){
    $('#userEditWindow').window({
        title:'用户编辑',   //标题
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
            $("#user_id").val("");
            $("#username").textbox('setValue',"");  //setValue : 设置组件的值。
            $("#password").textbox('setValue',"");  // getValue : 获取组件的值。
            $("#pwd2").passwordbox('setValue',"");
            $("#tel").numberbox('setValue',"");
            $("#email").textbox('setValue',"");
            $("#user_dept").combobox('setValue',"");
            $("#inuse1").combobox('setValue',"0");

        }
    });
}


//增加用户
function addUser(){
    initUserEditWindow();    //初始化用户编辑窗口
    $("#userEditWindow").window('open');
    $("#userEditWindow").window('center');  //center: 将窗口绝对居中

}


//刷新用户
function refreshUser(){
    $('#userDataGrid').datagrid('reload');	// 重新载入所有行
}


//保存按钮（添加修改）
function saveUser() {
    //判断输入框是否为空,自己写
    var  username=$("#username").textbox('getValue'); //名称
    var  password=$("#password").textbox('getValue'); //密码
    var  pwd2=$("#pwd2").passwordbox('getValue'); //确认密码

    if( username.trim()==""|| username.trim()==null ||
        password.trim()==""||password.trim()==null ||
        pwd2.trim()==""||pwd2.trim()==null){  //trim():排除空格
        $.messager.alert('系统提示','用户名，密码,确认密码不能为空!','info');
    }else {
        if (password != pwd2) {
            $.messager.alert("系统提示", "密码不一致!","warning");
        } else {
            //1.组织参数
            var param = {
                user_id: $("#user_id").val(),
                username: $("#username").textbox('getValue'),
                password: $("#password").textbox('getValue'),
                pwd2:$("#pwd2").passwordbox('getValue'),
                tel: $("#tel").numberbox('getValue'),
                email: $("#email").textbox('getValue'),
                dept_id: $("#user_dept").combobox('getValue'),
                inuse: $("#inuse1").combobox('getValue')
            };
            console.log(param);

            $.ajax({
                url: path + "/user/saveOrUpdateUser",
                type: "post",
                data: param,
                dataType: "json",
                success: function (data) {
                    if (data.info == "success") {
                        // $.messager.alert('系统提示','保存成功!','success');
                        $.messager.alert('系统提示', '保存成功!');

                        //1.关闭窗口
                        closeUser();
                        //2.刷新数据
                        refreshUser();

                    } else {
                        $.messager.alert('系统提示', '保存失败!', 'error');
                    }
                }

            });
        }
    }
}


//关闭按钮
function closeUser() {
    $('#userEditWindow').window('close');  // close a window
}