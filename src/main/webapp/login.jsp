<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Wopop</title>
    <link href="${path}/static/login/./Wopop_files/style_log.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="${path}/static/login/./Wopop_files/style.css">
    <link rel="stylesheet" type="text/css" href="${path}/static/login/./Wopop_files/userpanel.css">
    <link rel="stylesheet" type="text/css" href="${path}/static/login/./Wopop_files/jquery.ui.all.css">
    <script type="text/javascript" src="${path}/static/js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="${path}/static/js/jquery.cookie.js"></script>
    <script type="text/javascript">
        //当页面元素加载完成之后，即dom对象加载完成之后执行
        $(function(){
            var username=$.cookie("username");
            var password=$.cookie("password");
            var remember=$.cookie("remember");
            //alert(username+"-----"+password+"----"+remember);
            if(remember!=null){
                $("#username").val(username);
                $("#password").val(password);
                $("#remember").attr("checked","checked");
            }
        });

        function login() {
            var remember=$("#remember").is(":checked");
            var username = $("#username").val();
            var password = $("#password").val();
            if (username == "" || password == "") {
                alert("请填写用户名和密码");
                return;
            }

            $.ajax({
                url: "${path}/user/login",
                type: "post",
                dataType: "json",
                async: false,
                data: {
                    username: username,
                    password: password
                },
                success: function (result) {
                    console.log(result);
                    if (result.success) {
                        alert(result.info);
                        if(remember){
                            $.cookie("username",username,{expires:7});
                            $.cookie("password",password,{expires:7});
                            $.cookie("remember",remember,{expires:7});
                        }else{
                            $.cookie("username",null);
                            $.cookie("password",null);
                            $.cookie("remember",null);
                        }
                        window.location.href = "${path}/user/tomain";
                    } else {
                        alert(result.info);
                    }
                }
            });

        }


    </script>

</head>

<body class="login" mycollectionplug="bind">
<div class="login_m">
    <div class="login_logo"><img src="${path}/static/login/./Wopop_files/logo.png" width="196" height="46"></div>
    <div class="login_boder">

        <div class="login_padding" id="login_model">

            <h2>用户名</h2>
            <label>
                <input type="text" id="username" class="txt_input txt_input2"  value="Your name">
            </label>
            <h2>密码</h2>
            <label>
                <input type="password" name="textfield2" id="password" class="txt_input"  value="******">
            </label>




            <p class="forgot"><a id="iforget" href="javascript:void(0);">忘记密码?</a></p>
            <div class="rem_sub">
                <div class="rem_sub_l">
                    <input type="checkbox" name="remember" id="remember">
                    记住密码
                </div>
                <label>
                    <input type="submit" class="sub_button" name="button" id="button" value="登陆" onclick="login()" style="opacity: 0.7;">
                </label>
            </div>
        </div>

        <div class="copyrights">Collect from <a href="http://www.cssmoban.com/" >天上掉下来的模板</a></div>

        <div id="forget_model" class="login_padding" style="display:none">
            <br>

            <h1>Forgot password</h1>
            <br>
            <div class="forget_model_h2">(Please enter your registered email below and the system will automatically reset users’ password and send it to user’s registered email address.)</div>
            <label>
                <input type="text" id="usrmail" class="txt_input txt_input2">
            </label>


            <div class="rem_sub">
                <div class="rem_sub_l">
                </div>
                <label>
                    <input type="submit" class="sub_buttons" name="button" id="Retrievenow" value="Retrieve now" style="opacity: 0.7;">
                    　　　
                    <input type="submit" class="sub_button" name="button" id="denglou" value="Return" style="opacity: 0.7;">　　

                </label>
            </div>
        </div>






        <!--login_padding  Sign up end-->
    </div><!--login_boder end-->
</div><!--login_m end-->
<br> <br>
<p align="center"> More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">么么哒</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">么么哒</a></p>



</body></html>