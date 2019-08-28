$(function () {
    //页面加载完毕之后 ,去cookie读取有没有用户名和密码
    var res=$.cookie("flag");
    if(res=="true"){
        $("input:eq(0)").val($.cookie("username"));
        $("input:eq(1)").val($.cookie("password"));
        $("#save_me").prop("checked",true);
    }


    $("#button").click(function () {

        var name = $("input:eq(0)").val();
        var pwd = $("input:eq(1)").val();
        var status = $("#save_me").prop("checked");  //看是否选中 登陆按钮
        if (status) {  //选中了
            $.cookie("username", name, {expires: 7});
            $.cookie("password", pwd, {expires: 7});
            $.cookie("flag", true, {expires: 7});
        } else {  //没有选择自动登录 移除信息
            $.removeCookie("username");
            $.removeCookie("password");
            $.removeCookie("flag");
        }



        var param = {
            username:$("#username").val(),
            password:$("#userpwd").val()
        };

        $.ajax({
            url:"/SSM02/sys/userLogin",
            type:"post",
            dataType:"json",
            data:param,
            success:function (data) {
                if(data.info=="success"){
                    window.location.href="/SSM02/sys/toHome";
                }else{
                    alert("登陆失败");
                }
            }
        });

    });
});