$(function(){
    initNoticeDataGrid();

    //员工（公告人）
    $('#DescByName').combobox({
        url:path+"/notice/getDESC",
        valueField:'id',
        textField:'text'
    });
});

function  initNoticeDataGrid(){
    $("#noticeDataGrid").datagrid({
        fitColumns:true,  //防止水平滚动
        method:'post',
        idField:'id',
        fit:true,  //自动适应屏幕大小
        url:path+"/notice/getAllNotice",
        pagination:true,  //DataGrid控件底部显示分页工具栏
        rownumbers:true,   //显示一个行号列
        singleSelect:true,  //只允许选择一行
        pageNumber:1,  //设置分页属性的时候初始化页码
        pageSize:10,    //设置分页属性的时候初始化页面大小
        pageList:[10,20,50],  //设置分页属性的时候 初始化页面大小选择列表
        columns:[[
            {field:'id',title:'编号',width:60,align:'center'},
            {field:'text',title:'公告人',width:70,align:'center'},
            {field:'notice_text',title:'公告主题',width:80,align:'center'},
            {field:'notice_content',title:'公告内容',width:70,align:'center'},
            {field:'create_time',title:'公告时间',width:120,align:'center'},
            {field:'reply_time',title:'公告截止时间',width:120,align:'center'},
            {field:'deleteNotice',title:'删除',width:70,align:'center',
                formatter: function(value,row,index){
                    return "<a href='javascript:deleteNotice()'>删除</a>";
                }
            },
        ]],

        // 在双击一个单元格的时候开始编辑并生成编辑器，然后定位到编辑器的输入框上
        onDblClickRow:function (rowIndex, rowData) {
            // alert(rowData.text+"--"+rowData.role_desc);

            // 初始化客户关怀编辑窗口（增加或修改）
            initNoticeEditWindow();
            //打开 编辑窗口
            $("#noticeEditWindow").window('open');
            $("#noticeEditWindow").window('center');  //将窗口绝对居中


            //双击时获取所选行的属性
            $("#notice_id").val(rowData.id);
            $("#DescByName").combobox('setValue', rowData.staff_id);
            $("#notice_text").textbox('setValue', rowData.notice_text);
            $("#notice_content").textbox('setValue', rowData.notice_content);
            $("#create_time3").datetimebox('setValue', rowData.create_time);
            $("#reply_time3").datetimebox('setValue', rowData.reply_time);
        }
    });
}




//删除
function deleteNotice() {
//获取当前选中的行
    var node=$("#noticeDataGrid").datagrid('getSelected');
    $.messager.confirm('提示', '请判断是否要执行删除操作?', function(b){
        if (b){
            $.ajax({
                url:path+"/notice/deleteNotice",
                type:"post",
                data:{notice_id:node.id},
                dataType:"json",
                success:function (data) {
                    if(data.info=="success"){
                        $.messager.alert("系统提示", "您确定要执行删除操作吗！","question");
                        refreshNotice();
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
function findNotice(){
    var notice_text=$("#findNoticeByName").textbox('getValue');
    var checked6 = $("#state6 option:selected").text();
    var param={
        notice_text:notice_text,
        notice_way:checked6
    };
    //重新加载数据
    $('#noticeDataGrid').datagrid('reload',param);
}




/*--------------------------------------------*/
//刷新
function refreshNotice(){
    $('#noticeDataGrid').datagrid('reload');	// 重新载入所有行
}

// 初始化公告编辑窗口
function initNoticeEditWindow(){
    $('#noticeEditWindow').window({
        title:'公告编辑窗口',   //标题
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
            $("#notice_id").val("");  //setValue : 设置组件的值。
            $("#DescByName").combobox('setValue',"");
            $("#notice_text").textbox('setValue',"");  // getValue : 获取组件的值。
            $("#notice_content").textbox('setValue',"");
            $("#create_time3").datetimebox('setValue',"");
            $("#reply_time3").datetimebox('setValue',"");
        }
    });
}


//增加
function addNotice(){
    initNoticeEditWindow();
    $("#noticeEditWindow").window('open');
    $("#noticeEditWindow").window('center');  //center: 将窗口绝对居中

}



//保存按钮 (添加修改）
function saveNotice() {
    //判断输入框是否为空,自己写
    var  notice_text=$("#notice_text").textbox('getValue'); //主题
    var  notice_content=$("#notice_content").textbox('getValue');  //方式

    if(notice_text.trim()==""|| notice_text.trim()==null ||
        notice_content.trim()==""||notice_content.trim()==null){  //trim():排除空格
        $.messager.alert('系统提示','主题，内容输入框不能为空!','info');
    } else{

        //1.组织参数
        var param={
            notice_id:$("#notice_id").val(),
            notice_person:$("#DescByName").combobox('getValue'),
            notice_text:$("#notice_text").textbox('getValue'),
            notice_content:$("#notice_content").textbox('getValue'),
            create_time3:$("#create_time3").datetimebox('getValue'),
            reply_time3:$("#reply_time3").datetimebox('getValue')
        };
        console.log(param);
        $.ajax({
            url:path+"/notice/saveOrUpdateNotice",
            type:"post",
            data:param,
            dataType:"json",
            success:function (data) {
                console.log(data);
                if(data.info=="success"){
                    $.messager.alert('系统提示', '保存成功!');

                    //1.关闭窗口
                    closeNotice();
                    //2.刷新数据
                    refreshNotice();

                }else{
                    $.messager.alert('系统提示','保存失败!','error');
                }
            }
        });
    }

}


//关闭按钮
function closeNotice() {
    $('#noticeEditWindow').window('close');  // close a window
}