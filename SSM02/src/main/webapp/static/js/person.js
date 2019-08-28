$(function(){
    initPersonDataGrid();

    //客户姓名
    $('#person_customer').combobox({
        url:path+"/person/getPersonKH",
        valueField:'id',
        textField:'text'
    });
});

function  initPersonDataGrid(){
    $("#personDataGrid").datagrid({
        fitColumns:true,  //防止水平滚动
        method:'post',
        idField:'id',
        fit:true,  //自动适应屏幕大小
        url:path+"/person/getAllPerson",
        pagination:true,  //DataGrid控件底部显示分页工具栏
        rownumbers:true,   //显示一个行号列
        singleSelect:true,  //只允许选择一行
        pageNumber:1,  //设置分页属性的时候初始化页码
        pageSize:10,    //设置分页属性的时候初始化页面大小
        pageList:[10,20,50],  //设置分页属性的时候 初始化页面大小选择列表
        columns:[[
            {field:'id',title:'联系编号',width:60,align:'center'},
            {field:'text',title:'属于的客户',width:70,align:'center'},
            {field:'person_name',title:'联系人姓名',width:80,align:'center'},
            {field:'person_sex',title:'性别',width:70,align:'center',
                formatter: function(value,row,index){
                    if (value=="0"){
                        return "男";
                    } else {
                        return "女";
                    }
                }
            },
            {field:'person_age',title:'年龄',width:80,align:'center'},
            {field:'person_post',title:'职位',width:110,align:'center'},
            {field:'person_tel',title:'电话',width:110,align:'center'},
            {field:'customer_nexus',title:'与客户关系',width:110,align:'center'},

            {field:'deletePerson',title:'删除',width:70,align:'center',
                formatter: function(value,row,index){
                    return "<a href='javascript:deletePerson()'>删除</a>";
                }
            },
        ]],

        // 在双击一个单元格的时候开始编辑并生成编辑器，然后定位到编辑器的输入框上
        onDblClickRow:function (rowIndex, rowData) {
            // alert(rowData.text+"--"+rowData.role_desc);

            // 初始化联系人编辑窗口（增加或修改）
            initPersonEditWindow();
            //打开 编辑窗口
            $("#personEditWindow").window('open');
            $("#personEditWindow").window('center');  //将窗口绝对居中


            //双击时获取所选行的属性
            $("#person_id").val(rowData.id);
            $("#person_customer").combobox('setValue', rowData.client_id);
            $("#person_name").textbox('setValue', rowData.person_name);
            $("#person_sex").combobox('setValue', rowData.person_sex);
            $("#person_age").textbox('setValue', rowData.person_age);
            $("#person_post").textbox('setValue', rowData.person_post);
            $("#person_tel").textbox('setValue', rowData.person_tel);
            $("#customer_nexus").textbox('setValue', rowData.customer_nexus);

        }
    });
}


//删除
function deletePerson() {
//获取当前选中的行
    var node=$("#personDataGrid").datagrid('getSelected');
    $.messager.confirm('提示', '请判断是否要执行删除操作?', function(b){
        if (b){
            $.ajax({
                url:path+"/person/deletePerson",
                type:"post",
                data:{person_id:node.id},
                dataType:"json",
                success:function (data) {
                    if(data.info=="success"){
                        $.messager.alert("系统提示", "您确定要执行删除操作吗！","question");
                        refreshPerson();
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
function findPerson(){
    var person_text=$("#findPersonByName").textbox('getValue');
    var checked4 = $("#state4 option:selected").text();
    var param={
        person_text:person_text,
        person_way:checked4
    };
    //重新加载数据
    $('#personDataGrid').datagrid('reload',param);
}



/*--------------------------------------------*/
//刷新
function refreshPerson(){
    $('#personDataGrid').datagrid('reload');	// 重新载入所有行
}

// 初始化联系人编辑窗口
function initPersonEditWindow(){
    $('#personEditWindow').window({
        title:'联系人窗口',   //标题
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
            $("#person_id").val("");
            $("#person_customer").combobox('setValue',"");  //setValue : 设置组件的值。
            $("#person_name").textbox('setValue',"");
            $("#person_sex").combobox('setValue',"");
            $("#person_age").textbox('setValue',"");  // getValue : 获取组件的值。
            $("#person_post").textbox('setValue',"");
            $("#person_tel").textbox('setValue',"");
            $("#customer_nexus").textbox('setValue',"");
        }
    });
}


//增加按钮（联系人）
function addPerson(){
    initPersonEditWindow();
    $("#personEditWindow").window('open');
    $("#personEditWindow").window('center');  //center: 将窗口绝对居中

}


//保存按钮 (添加修改）
function savePerson() {
    //判断输入框是否为空,自己写
    var  person_customer=$("#person_customer").textbox('getValue'); //属于的客户
    var  person_name=$("#person_name").textbox('getValue'); //联系人姓名

    if( person_customer.trim()==""|| person_customer.trim()==null ||
        person_name.trim()==""|| person_name.trim()==null){  //trim():排除空格
        $.messager.alert('系统提示','输入框不能为空!','info');
    } else{

        //1.组织参数
        /*person_id,person_customer,person_name,person_sex,person_age,person_post,person_tel,customer_nexus*/
        var param={
            person_id:$("#person_id").val(),
            person_customer:$("#person_customer").combobox('getValue'),
            person_name:$("#person_name").textbox('getValue'),
            person_sex:$("#person_sex").combobox('getValue'),
            person_age:$("#person_age").textbox('getValue'),
            person_post:$("#person_post").textbox('getValue'),
            person_tel:$("#person_tel").textbox('getValue'),
            customer_nexus:$("#customer_nexus").textbox('getValue')
        };

        $.ajax({
            url:path+"/person/saveOrUpdatePerson",
            type:"post",
            data:param,
            dataType:"json",
            success:function (data) {
                if(data.info=="success"){
                    $.messager.alert('系统提示', '保存成功!');

                    //1.关闭窗口
                    closePerson();
                    //2.刷新数据
                    refreshPerson();

                }else{
                    $.messager.alert('系统提示','保存失败!','error');
                }
            }
        });
    }

}


//关闭按钮
function closePerson() {
    $('#personEditWindow').window('close');  // close a window
}