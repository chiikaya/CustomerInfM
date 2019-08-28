$(function(){
    initTreeGrid();
    var authArray = ['addAuth','editAuth','refreshAuth'];
    empowerResource(authArray);
});

function   initTreeGrid(){
    $('#authTreeGrid').treegrid({
        fit:true,  //自动适应屏幕大小
        rownumbers: true,
        animate: true, //定义在节点展开或折叠的时候是否显示动画效果。
        collapsible: true,  //是否可以折叠
        fitColumns: true,
        method: 'get',  //获取方式
        onContextMenu: onContextMenu,  /* 调用的右击菜单 */
        idField:'id',   //定义关键字段来标识树节点。
        treeField:'text',  //定义树节点字段
        columns:[[
            {field:'id',title:'权限编号',width:150,align:'center'},
            {field:'text',title:'权限名称',width:300,align:'center'},
            {field:'auth_desc',title:'权限描述',width:330,align:'center'},
            {field:'auth_layer',title:'权限层级',width:150,align:'center'},
            {field:'auth_url',title:'权限url',width:160,align:'center'},
            {field:'auth_order',title:'权限排序',width:150,align:'center'},
            {field:'parent_id',title:'父权限编号',width:150,align:'center'},

            {field:'auth_type',title:'权限类型',width:150,align:'center',
                formatter: function(value,row,index){
                    if (value=="0"){
                        return "模块";
                    } else {
                        return "资源";
                    }
                }

            },
            {field:'inuse',title:'是否再用',width:150,align:'center',
                formatter: function(value,row,index){
                    if (value=="0"){
                        return "可用";
                    } else {
                        return "禁用";
                    }
                }
            }
        ]]
    });
    //给treegrid添加url属性
    $("#authTreeGrid").treegrid('options').url=path+"/auth/getAllAuth";
    $("#authTreeGrid").treegrid('load');//加载数据
}

/*右键菜单*/
function onContextMenu(e,row){
    if (row){
        e.preventDefault();
        $(this).treegrid('select', row.id);
        $('#authMenu').menu('show',{
            left: e.pageX,
            top: e.pageY
        });
    }
}


/*初始化权限编辑窗口
* 思路:添加权限和编辑权限使用同一个窗口，
* 在窗口中添加一个隐藏域，如果是添加权限的话，隐藏域属性留空
* 如果是编辑权限，就把当前权限的id保存到隐藏域
* 添加或编辑权限服务器服务器端使用一个controller处理，
* 通过权限的dbid来判断，如果有dbid就是修改权限，
* 如果没有传dbid过来，就是添加权限。
* */
function initAuthEditWindow(){
    $('#authEditWindow').window({
        title:'权限编辑',   //标题
        collapsible:false,  //定义是否显示可折叠按钮。
        minimizable:false,  //定义是否显示最小化按钮
        maximizable:false,  //定义是够显示最大化按钮
        resizable:false,  //定义是否能够改变窗口大小
        width:400,
        height:400,
        modal:true,   //定义是否将窗体显示为模式化窗口
        closed:true,  //定义是否可以关闭窗口
        onBeforeClose:function(){
            //退出时 清空窗口的内容
            $("#auth_id").val("");
            $("#parent_id").val("");
            $("#parentAuth").textbox('setValue',"");
            $("#auth_name").textbox('setValue',"");  //setValue : 设置组件的值。
            $("#auth_desc").textbox('setValue',"");  // getValue : 获取组件的值。
            $("#auth_url").textbox('setValue',"");
            $("#auth_order").textbox('setValue',"");
            $("#auth_type").combobox('setValue',"0");
            $("#inuse").combobox('setValue',"0");
        }
    });
}

//增加权限
function addAuth(){
    initAuthEditWindow();
    $("#authEditWindow").window('open');
    $("#authEditWindow").window('center');  //center: 将窗口绝对居中
    //获取当前选中的这一行   getSelected:获取选择的节点并返回它
    var node = $("#authTreeGrid").treegrid('getSelected');
    //赋值
    $("#parentAuth").textbox('setValue',node.text);
    $("#auth_layer").textbox('setValue',parseInt(node.auth_layer)+1);
    $("#parent_id").val(node.id); //父权限id

}

//修改权限
function editAuth(){  // 修改权限 隐藏域我们只使用dbid 用来保存当前权限的id
    initAuthEditWindow();
    $("#authEditWindow").window('open');
    $("#authEditWindow").window('center');  //将窗口绝对居中
    //获取当前选中的这一行
    var node=$("#authTreeGrid").treegrid('getSelected');
    //getParent:获取父节点
    var parentNode=$("#authTreeGrid").treegrid('getParent',node.id);
    $("#auth_id").val(node.id); //当前权限的id

    //判断是不是父权限
    if(parentNode!=null){
        $("#parentAuth").textbox('setValue',parentNode.text);
    }

    $("#auth_layer").textbox('setValue',node.auth_layer);
    $("#auth_name").textbox('setValue',node.text);
    $("#auth_desc").textbox('setValue',node.auth_desc);
    $("#auth_url").textbox('setValue',node.auth_url);
    $("#auth_order").textbox('setValue',node.auth_order);
    $("#auth_type").combobox('setValue',node.auth_type);
    $("#inuse").combobox('setValue',node.inuse);
}

//刷新
function refreshAuth(){
    $('#authTreeGrid').treegrid('reload');	// 重新载入所有行
}

//保存按钮 (添加修改）
function saveAuth() {
    //判断输入框是否为空,自己写
    var  auth_name=$("#auth_name").textbox('getValue'); //名称
    var  auth_desc=$("#auth_desc").textbox('getValue'); //描述
    var  auth_layer=$("#auth_layer").textbox('getValue'); //权限层级
    var  auth_order=$("#auth_order").textbox('getValue');  //排序

    if( auth_name.trim()==""|| auth_name.trim()==null ||
        auth_desc.trim()==""|| auth_desc.trim()==null ||
        auth_layer.trim()==""|| auth_layer.trim()==null ||
        auth_order.trim()==""||auth_order.trim()==null){  //trim():排除空格
        $.messager.alert('系统提示','输入框不能为空!','info');
    } else{

        //1.组织参数
        var param={
            auth_id:$("#auth_id").val(),
            parent_id:$("#parent_id").val(),
            auth_name:$("#auth_name").textbox('getValue'),
            auth_desc:$("#auth_desc").textbox('getValue'),
            auth_layer:$("#auth_layer").textbox('getValue'),
            auth_url:$("#auth_url").textbox('getValue'),
            auth_order:$("#auth_order").textbox('getValue'),
            auth_type:$("#auth_type").combobox('getValue'),
            inuse:$("#inuse").combobox('getValue')
        };

        $.ajax({
            url:path+"/auth/saveOrUpdateAuth",
            type:"post",
            data:param,
            dataType:"json",
            success:function (data) {
                if(data.info=="success"){
                    // $.messager.alert('系统提示','保存成功!','success');
                    $.messager.alert('系统提示', '保存成功!');

                    //1.关闭窗口
                    closeAuth();
                    //2.刷新数据
                    refreshAuth();
                }else{
                    $.messager.alert('系统提示','保存失败!','error');
                }
            }
        });
    }

}
//关闭
function closeAuth() {
    $('#authEditWindow').window('close');  // close a window
}

