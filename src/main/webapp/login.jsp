<%@ page import="com.oraclewfk.bookmarket.model.Admin" %>
<%@ page import="java.util.Map" %>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="bower_components/bootswatch/dist/pulse/bootstrap.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="container-fluid" style="width: 80%;">
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-body">
						<form action="login" method="post" id="loginForm">
							<!-- 获取errors-->
						<%
							Map<String,String> errors= (Map<String,String>) request.getAttribute("errors");
							Admin admin= (Admin) request.getAttribute("admin");
						    if(admin!=null){
						%>
								<div class="form-group row">
								<label for="inputName" class="col-sm-2 col-form-label text-right">用户名</label>
								<div class="col-sm-10">
									<%
										if (errors!=null&&errors.get("name")!=null){
                                    %>

									<input type="text" class="form-control is-invalid" id="inputName" placeholder="用户名" name="name" value="<%=admin.getName()==null?"":admin.getName()%>>
									<div class="invalid-feedback"><%=errors.get("name")%></div>
									<%
										}else{

									%>
									<input type="text" class="form-control" id="inputName" placeholder="用户名" name="name" value="<%=admin.getName()%>">
									<%
										}
									%>
								</div>
							</div>

							<div class="form-group row">
								<label for="inputPwd" class="col-sm-2 col-form-label text-right">密码</label>
								<div class="col-sm-10">
									<%
                                    if (errors!=null&&errors.get("pwd")!=null){
									%>
									<input type="password" class="form-control is-invalid" id="inputPwd" placeholder="密码" name="pwd">
									<div class="invalid-feedback">
										<%=errors.get("pwd")%>
									</div>
									<%
                                    }else {
									%>
									<input type="password" class="form-control" id="inputPwd" placeholder="密码" name="pwd">
									<%
                                    }
                                    %>
								</div>
							</div>
							<%--验证码--%>
							<div class="form-group row">
								<label for="inputVcode" class="col-sm-2 col-form-label text-right">验证码</label>
								<div class="col-sm-4">
									<%
									if (errors!=null&&errors.get("vcode")!=null){
									  %>
									<input class="form-control is-invalid" id="inputVcode" placeholder="验证码" name="vcode" >
									<div class="invalid-feedback">
										<%=errors.get("vcode")%>
									</div>
									<%
									}else {
									%>
									<input class="form-control" id="inputVcode" placeholder="验证码" name="vcode" >
									<%
									}
									%>
								</div>
								<div class="col-sm-2">
									<img src="vcode.png" id="vcodeImg" title="看不清点击换">
								</div>
								<div class="invalid-feedback">
									<%

										if(request.getAttribute("msg")!=null){
									%>
									<%=request.getAttribute("msg")%>

									<%
										}
									%>
							</div>

							<div class="form-group row">
								<div class="col-sm-2"></div>
								<div class="col-sm-10">
									<button type="submit" class="btn btn-primary">登录</button>
								</div>
							</div>
				       <%
						}else{
					   %>
								<div class="form-group row">
								<label for="inputName" class="col-sm-2 col-form-label text-right">用户名</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputName" placeholder="用户名" name="name" >
								</div>
							</div>
							<div class="form-group row">
								<label for="inputPwd" class="col-sm-2 col-form-label text-right">密码</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="inputPwd" placeholder="密码" name="pwd">
								</div>
							</div>
							<%--验证码--%>
							<div class="form-group row">
								<label for="inputVcode" class="col-sm-2 col-form-label text-right">验证码</label>
								<div class="col-sm-4">
									<input class="form-control" id="inputVcode" placeholder="验证码" name="vcode" >
								</div>
								<div class="col-sm-2">
								<img src="vcode.png" id="vcodeImg" title="看不清点击换">
							</div>
								<div class="invalid-feedback">
									<%--当错误信息不为空的时候就返回粗无信息--%>
									<%

									if(request.getAttribute("msg")!=null){
									 %>
									<%=request.getAttribute("msg")%>

									<%
									}
									%>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-sm-2"></div>
								<div class="col-sm-10">
									<button type="submit" class="btn btn-primary">登录</button>
								</div>
							</div>
						<%
						}
						%>
						 </form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
	<script type="text/javascript" src="bower_components/jquery/dist/jquery.js"></script>
	<script type="text/javascript">
        $(function(){
            $("#vcodeImg").click(function () {
                $(this).attr("src","vcode.png?t="+Math.random());
            });
		});
	</script>
	<script type="text/javascript" src="bower_components/jquery-validation/dist/jquery.validate.js"></script>
	<%--验证后添加--%>
	<script type="text/javascript" src="bower_components/jquery-validation/src/localization/messages_zh.js"></script>
<script type="text/javascript">
	$(function () {
        $("#loginForm").validate({
            rules:{//验证的原则
                name:{
                    required:true,
                    minlength:3,
					maxlength:45
                },
                pwd:{
                    required:true,
                    minlength:3,
                    maxlength:45
                },
		       vcode:{
                   required:true,
                   minlength:4,
                   maxlength:4
			   }
            },
            errorElement:"div",
            errorClass:"invalid-feedback",//错误消息的样式

            heiglight:function(element,errorClass,validClass){//验证提示，添加无效的验证  去掉有效的
                $(element).addClass("is-invalid").removeClass(validClass);
            },
            unheiglight:function(element,errorClass,validClass){//验证之后 去掉无效的 添加有效的
                $(element).removeClass("is-invalid").addClass(validClass);
            },
            validClass:"is-valid"//指定纠正正确的时候输入框的样式
        });
    });

</script>
</body>
</html>