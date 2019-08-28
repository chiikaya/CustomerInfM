$(function(){
    initDescDataGrid();

   //部门
    $('#staff_dept').combobox({
        url:path+"/desc/getDept",
        valueField:'id',
        textField:'text'
    });
    //角色
    $('#staff_role').combobox({
        url:path+"/desc/getRole",
        valueField:'id',
        textField:'text'
    });

});

function  initDescDataGrid(){
    $("#descDataGrid").datagrid({
        fitColumns:true,  //防止水平滚动
        method:'post',
        idField:'id',
        fit:true,  //自动适应屏幕大小
        url:path+"/desc/getAllDesc",
        pagination:true,  //DataGrid控件底部显示分页工具栏
        rownumbers:true,   //显示一个行号列
        singleSelect:true,  //只允许选择一行
        pageNumber:1,  //设置分页属性的时候初始化页码
        pageSize:10,    //设置分页属性的时候初始化页面大小
        pageList:[10,20,50],  //设置分页属性的时候 初始化页面大小选择列表
        columns:[[
            {field:'id',title:'员工编号',width:70,align:'center'},
            {field:'text',title:'员工姓名',width:80,align:'center'},
            {field:'staff_age',title:'年龄',width:80,align:'center'},
            {field:'staff_sex',title:'性别',width:80,align:'center',
                formatter: function(value,row,index){
                    if (value=="0"){
                        return "男";
                    } else {
                        return "女";
                    }
                }
            },
            {field:'staff_nation',title:'民族',width:80,align:'center'},
            {field:'dept_name',title:'部门',width:100,align:'center'},
            {field:'role_name',title:'角色',width:100,align:'center'},
            {field:'staff_degree',title:'学历',width:100,align:'center'},
            {field:'staff_marital',title:'婚姻',width:80,align:'center',
                formatter: function(value,row,index){
                    if (value=="0"){
                        return "未婚";
                    } else {
                        return "已婚";
                    }
                }
            },
            {field:'staff_address',title:'家庭住址',width:100,align:'center'},
            {field:'staff_phone',title:'手机',width:100,align:'center'},
            {field:'staff_tel',title:'电话',width:100,align:'center'},
            {field:'staff_email',title:'Email',width:100,align:'center'},
            {
                field: 'deleteDesc', title: '删除', width: 100,align:'center',
                formatter: function () {
                    return "<a href='javascript:deleteDesc()'>删除</a>";
                }
            }
        ]],

        // 在双击一个单元格的时候开始编辑并生成编辑器，然后定位到编辑器的输入框上
        onDblClickRow:function (rowIndex, rowData) {
            // alert(rowData.text+"--"+rowData.role_desc);

            // 初始化员工信息角色编辑窗口（增加或修改）
            initDescEditWindow();
            //打开 编辑窗口
            $("#descEditWindow").window('open');
            $("#descEditWindow").window('center');  //将窗口绝对居中


            //双击时获取所选行的属性
            $("#staff_id").val(rowData.id);
            $("#staff_name").textbox('setValue',rowData.text);  //setValue : 设置组件的值。
            $("#staff_age").numberbox('setValue',rowData.staff_age);
            $("#staff_sex").combobox('setValue',rowData.staff_sex);
            $("#staff_nation").textbox('setValue',rowData.staff_nation);
            $("#staff_dept").combobox('setValue',rowData.dept_id);
            $("#staff_role").combobox('setValue',rowData.role_id);
            $("#staff_degree").textbox('setValue',rowData.staff_degree);
            $("#staff_marital").combobox('setValue',rowData.staff_marital);
            $("#staff_address").textbox('setValue',rowData.staff_address);
            $("#staff_phone").numberbox('setValue',rowData.staff_phone);
            $("#staff_tel").numberbox('setValue',rowData.staff_tel);
            $("#staff_email").textbox('setValue',rowData.staff_email);

        }
    });
}


//删除用户
function deleteDesc() {
    var node=$("#descDataGrid").datagrid('getSelected');
    $.messager.confirm('提示', '请判断是否要执行删除操作?', function(b){
        if (b){
            $.ajax({
                url:path+"/desc/deleteDesc",
                type:"post",
                data:{staff_id:node.id},
                dataType:"json",
                success:function (data) {
                    if(data.info=="success"){
                        $.messager.alert("系统提示", "删除成功！","info");
                        refreshDesc();
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

function findDesc4(){
    var checked4 = $("#state4 option:selected").text();
    var desc_text4=$("#findDescByName4").textbox('getValue');

    var param={
        desc_text4:desc_text4,
        desc_where4:checked4
    };
    //重新加载数据
    $('#descDataGrid').datagrid('reload',param);
}



/*--------------------------------------------*/
// 初始化客户信息编辑窗口
function initDescEditWindow(){
    $('#descEditWindow').window({
        title:'员工信息编辑',   //标题
        collapsible:false,  //定义是否显示可折叠按钮。
        minimizable:false,  //定义是否显示最小化按钮
        maximizable:false,  //定义是够显示最大化按钮
        resizable:false,  //定义是否能够改变窗口大小
        width:400,
        height:540,
        modal:true,   //定义是否将窗体显示为模式化窗口
        closed:true,  //默认窗口是关闭
        onBeforeClose:function(){

            //退出时 清空窗口的内容
            $("#staff_id").val("");
            $("#staff_name").textbox('setValue',"");  //setValue : 设置组件的值。
            $("#staff_age").numberbox('setValue',"");
            $("#staff_sex").combobox('setValue',"");
            $("#staff_nation").textbox('setValue',"");
            $("#staff_dept").combobox('setValue',"");
            $("#staff_role").combobox('setValue',"");
            $("#staff_degree").textbox('setValue',"");
            $("#staff_marital").combobox('setValue',"");
            $("#staff_address").textbox('setValue',"");
            $("#staff_phone").numberbox('setValue',"");
            $("#staff_tel").numberbox('setValue',"");
            $("#staff_email").textbox('setValue',"");
        }
    });
}


//增加客户信息
function addDesc(){
    initDescEditWindow();
    $("#descEditWindow").window('open');
    $("#descEditWindow").window('center');  //center: 将窗口绝对居中
}


//刷新客户信息
function refreshDesc(){
    $('#descDataGrid').datagrid('reload');	// 重新载入所有行
}


//保存按钮 (添加修改）
function saveDesc() {

    //判断输入框是否为空,自己写
    var  staff_name= $("#staff_name").textbox('getValue');  //姓名
    var staff_dept=  $("#staff_dept").combobox('getText');  //部门
    var staff_role=  $("#staff_role").combobox('getText');   //角色
    var staff_degree=  $("#staff_degree").textbox('getValue'); //学历


    if( staff_name.trim()==""|| staff_name.trim()==null ||
        staff_dept.trim()==""|| staff_dept.trim()==null ||
        staff_role.trim()==""|| staff_role.trim()==null ||
        staff_degree.trim()==""|| staff_degree.trim()==null ){  //trim():排除空格
        $.messager.alert('系统提示','姓名、部门、角色、学历不能为空!','info');
    } else{
        //alert(sys_status+":"+sys_from+":"+sys_mold);
        //1.组织参数
        var param={
            staff_id:$("#staff_id").val(),
            staff_name:$("#staff_name").textbox('getValue'),
            staff_age: $("#staff_age").numberbox('getValue'),
            staff_sex:$("#staff_sex").combobox('getValue'),
            staff_nation :$("#staff_nation").textbox('getValue'),
            staff_dept:$("#staff_dept").combobox('getValue'),
            staff_role:$("#staff_role").combobox('getValue'),
            staff_degree:$("#staff_degree").textbox('getValue'),
            staff_marital:$("#staff_marital").combobox('getValue'),
            staff_address:$("#staff_address").textbox('getValue'),
            staff_phone:$("#staff_phone").numberbox('getValue'),
            staff_tel:$("#staff_tel").numberbox('getValue'),
            staff_email:$("#staff_email").textbox('getValue')
        };

        console.log(param);

        $.ajax({
            url:path+"/desc/saveOrUpdateDesc",
            type:"post",
            data:param,
            dataType:"json",
            success:function (data) {
                console.log(data);
                if(data.info=="success"){
                    $.messager.alert('系统提示', '保存成功!');

                    //1.关闭窗口
                    closeDesc();
                    //2.刷新数据
                    refreshDesc();

                }else{
                    $.messager.alert('系统提示','保存失败!','error');
                }
            }
        });
    }

}


//关闭按钮
function closeDesc() {
    $('#descEditWindow').window('close');  // close a window
}