$(function(){
    initClientDataGrid();

    //状态
    $('#status_inuse1').combobox({
        url:path+"/client/getStatus",
        valueField:'id',
        textField:'text'
    });
    //来源
    $('#from_name1').combobox({
        url:path+"/client/getFrom",
        valueField:'id',
        textField:'text'
    });
    //所属员工
    $('#staff_name1').combobox({
        url:path+"/client/getStaff",
        valueField:'id',
        textField:'text'
    });
    //类型
    $('#mold_name1').combobox({
        url:path+"/client/getMold",
        valueField:'id',
        textField:'text'
    });


});

function  initClientDataGrid(){
    $("#clientDataGrid").datagrid({
        fitColumns:true,  //防止水平滚动
        method:'post',
        idField:'id',
        fit:true,  //自动适应屏幕大小
        url:path+"/client/getAllClient",
        pagination:true,  //DataGrid控件底部显示分页工具栏
        rownumbers:true,   //显示一个行号列
        singleSelect:true,  //只允许选择一行
        pageNumber:1,  //设置分页属性的时候初始化页码
        pageSize:10,    //设置分页属性的时候初始化页面大小
        pageList:[10,20,50],  //设置分页属性的时候 初始化页面大小选择列表
        columns:[[
            {field:'id',title:'客户编号',width:70,align:'center'},
            {field:'text',title:'客户姓名',width:80,align:'center'},
            {field:'status_inuse',title:'客户状态',width:100,align:'center'},
            {field:'status_id',hidden:'true'},
            {field:'from_name',title:'客户来源',width:100,align:'center'},
            {field:'from_id',hidden:'true'},
            {field:'staff_name',title:'所属员工',width:70,align:'center'},
            {field:'staff_id',hidden:'true'},
            {field:'mold_name',title:'客户类型',width:100,align:'center'},
            {field:'mold_id',hidden:'true'},
            {field:'sex',title:'性别',width:100,align:'center',
                formatter: function(value,row,index){
                    if (value=="0"){
                        return "男";
                    } else {
                        return "女";
                    }
                }
            },
            {field:'tel',title:'手机',width:100,align:'center'},
            {field:'QQ',title:'QQ',width:100,align:'center'},
            {field:'email',title:'电子邮箱',width:100,align:'center'},
            {field:'post',title:'职位',width:100,align:'center'},
            {field:'company',title:'公司',width:100,align:'center'},
            {
                field: 'deleteClient', title: '删除', width: 70,align:'center',
                formatter: function () {
                    return "<a href='javascript:deleteClient()'>删除</a>";
                }
            }
        ]],

        // 在双击一个单元格的时候开始编辑并生成编辑器，然后定位到编辑器的输入框上
        onDblClickRow:function (rowIndex, rowData) {
          // alert(rowData.text+"--"+rowData.role_desc);

            // 初始化客户信息角色编辑窗口（增加或修改）
            initClientEditWindow();
            //打开 编辑窗口
            $("#clientEditWindow").window('open');
            $("#clientEditWindow").window('center');  //将窗口绝对居中


            //双击时获取所选行的属性
            $("#client_id").val(rowData.id);
            $("#clientname").textbox('setValue', rowData.text);
            $("#status_inuse1").combobox('setValue',rowData.status_id);
            $("#from_name1").combobox('setValue',rowData.from_id);
            $("#staff_name1").combobox('setValue', rowData.staff_id);
            $("#mold_name1").combobox('setValue', rowData.mold_id);
            $("#sex").combobox('setValue', rowData.sex);
            $("#tel1").numberbox('setValue', rowData.tel);
            $("#QQ").textbox('setValue', rowData.QQ);
            $("#email1").textbox('setValue', rowData.email);
            $("#post").textbox('setValue', rowData.post);
            $("#company").textbox('setValue', rowData.company);


        }
    });
}

//删除客户
function deleteClient() {
    var node=$("#clientDataGrid").datagrid('getSelected');
    $.messager.confirm('提示', '请判断是否要执行删除操作?', function(b){
        if (b){
            $.ajax({
                url:path+"/client/deleteClient",
                type:"post",
                data:{client_id:node.id},
                dataType:"json",
                success:function (data) {
                    if(data.info=="success"){
                        $.messager.alert("系统提示", "删除成功！","info");
                        refreshClient();
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

function findClient(){
    var checked = $("#state1 option:selected").text();
    var find_text=$("#findClientByName").textbox('getValue');

    var param={
        find_text:find_text,
        find_where:checked
    };
    //重新加载数据
    $('#clientDataGrid').datagrid('reload',param);
}




/*--------------------------------------------*/
// 初始化客户信息编辑窗口
function initClientEditWindow(){
    $('#clientEditWindow').window({
        title:'客户信息编辑',   //标题
        collapsible:false,  //定义是否显示可折叠按钮。
        minimizable:false,  //定义是否显示最小化按钮
        maximizable:false,  //定义是够显示最大化按钮
        resizable:false,  //定义是否能够改变窗口大小
        width:400,
        height:480,
        modal:true,   //定义是否将窗体显示为模式化窗口
        closed:true,  //默认窗口是关闭
        onBeforeClose:function(){

            //退出时 清空窗口的内容
            $("#client_id").val("");
            $("#clientname").textbox('setValue',"");  //setValue : 设置组件的值。
            $("#status_inuse1").combobox('setValue',"");
            $("#from_name1").combobox('setValue',"");
            $("#staff_name1").combobox('setValue',"");
            $("#mold_name1").combobox('setValue',"");
            $("#sex").combobox('setValue',"");
            $("#tel1").numberbox('setValue',"");
            $("#QQ").textbox('setValue',"");
            $("#email1").textbox('setValue',"");
            $("#post").textbox('setValue',"");
            $("#company").textbox('setValue',"");
        }
    });
}


//增加客户信息
function addClient(){
    initClientEditWindow();
    $("#clientEditWindow").window('open');
    $("#clientEditWindow").window('center');  //center: 将窗口绝对居中
}


//刷新客户信息
function refreshClient(){
    $('#clientDataGrid').datagrid('reload');	// 重新载入所有行
}


//保存按钮 (添加修改）
function saveClient() {

    //判断输入框是否为空,自己写
      var  clientname= $("#clientname").textbox('getValue');  //名称
       var status_inuse=  $("#status_inuse1").combobox('getText');
       var from_name=  $("#from_name1").combobox('getText');
       var mold_name=  $("#mold_name1").combobox('getText');


    if( clientname.trim()==""|| clientname.trim()==null ||
        status_inuse.trim()==""|| status_inuse.trim()==null ||
        from_name.trim()==""|| from_name.trim()==null ||
        mold_name.trim()==""|| mold_name.trim()==null ){  //trim():排除空格
        $.messager.alert('系统提示','姓名、状态、来源、类型不能为空!','info');
    } else{
        //alert(sys_status+":"+sys_from+":"+sys_mold);
        //1.组织参数
        var param={
            client_id:$("#client_id").val(),
            clientname:$("#clientname").textbox('getValue'),// getValue : 获取组件的值。
            status_inuse: $("#status_inuse1").combobox('getValue'),
            from_name:$("#from_name1").combobox('getValue'),
            staff_name:$("#staff_name1").combobox('getValue'),
            mold_name:$("#mold_name1").combobox('getValue'),
            sex:$("#sex").combobox('getValue'),
            tel:$("#tel1").numberbox('getValue'),
            QQ:$("#QQ").textbox('getValue'),
            email:$("#email1").textbox('getValue'),
            post:$("#post").textbox('getValue'),
            company:$("#company").textbox('getValue')
        };

        console.log(param);

        $.ajax({
            url:path+"/client/saveOrUpdateClient",
            type:"post",
            data:param,
            dataType:"json",
            success:function (data) {
                console.log(data);
                if(data.info=="success"){
                    $.messager.alert('系统提示', '保存成功!');

                    //1.关闭窗口
                    closeClient();
                    //2.刷新数据
                    refreshClient();

                }else{
                    $.messager.alert('系统提示','保存失败!','error');
                }
            }
        });
    }

}


//关闭按钮
function closeClient() {
    $('#clientEditWindow').window('close');  // close a window
}