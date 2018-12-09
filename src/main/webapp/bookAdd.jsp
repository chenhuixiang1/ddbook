<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>书籍添加</title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="bower_components/bootswatch/dist/pulse/bootstrap.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.custom-file-label::after {
	content: "浏览";
}
</style>
</head>
<body>
<div class="container-fluid" style="width: 80%;">
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-body">
						<form action="bookAdd" method="post" enctype="multipart/form-data" id="bookAddForm">
								<div class="form-group row">
								<label for="inputBid" class="col-sm-2 col-form-label text-right">大类名</label>
								<div class="col-sm-10">
									<select name="bid" id="inputBid" class="form-control">
									 <option>-----请选择-----</option>
									</select>
								</div>
							</div>
							<div class="form-group row">
								<label for="inputSid" class="col-sm-2 col-form-label text-right">小类名</label>
								<div class="col-sm-10">
									<select name="sid" id="inputSid" class="form-control">
									
									</select>
								</div>
							</div>
							<div class="form-group row">
								<label for="inputName" class="col-sm-2 col-form-label text-right">书名</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputName" placeholder="书名" name="name">
								</div>
							</div>
							<div class="form-group row">
								<label for="inputPrice" class="col-sm-2 col-form-label text-right">价格</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputPrice" placeholder="价格" name="price">
								</div>
							</div>
							<div class="form-group row">
								<label for="inputAuhtor" class="col-sm-2 col-form-label text-right">作者</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputAuhtor" placeholder="作者" name="author">
								</div>
							</div>
							<div class="form-group row">
								<label for="inputPublish" class="col-sm-2 col-form-label text-right">出版社</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputPublish" placeholder="出版社" name="publish">
								</div>
							</div>
							<div class="form-group row">
								<label for="inputPublishDate" class="col-sm-2 col-form-label text-right">出版日期</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputPublishDate" placeholder="出版日期" name="publishDate">
								</div>
							</div>
							<div class="form-group row">
								<label for="inputDescription" class="col-sm-2 col-form-label text-right">书籍简介</label>
								<div class="col-sm-10">
									<textarea class="form-control" id="inputDescription" placeholder="书籍简介" name="description"></textarea>
								</div>
							</div>
							<div class="form-group row">
								<label for="inputPhoto" class="col-sm-2 col-form-label text-right">图片</label>
								<div class="col-sm-10">
									<div class="input-group">
										<div class="custom-file">
											<input type="file" class="custom-file-input" id="inputPhoto" aria-describedby="inputGroupFileAddon04" name="photo"> <label
												class="custom-file-label" for="inputGroupFile04">请选择文件</label>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-sm-2"></div>
								<div class="col-sm-10">
									<button type="submit" class="btn btn-primary">添加</button>
								</div>
							</div>
						 </form>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript" src="bower_components/jquery/dist/jquery.js"></script>
<script type="text/javascript" src="bower_components/bootstrap/dist/js/bootstrap.js"></script>

<script type="text/javascript">
            function findSell(types) {
				for (var i = 0; i < types.length; i++) {
					var op=new Option(types[i].name,types[i].id);
					//获取的name和id 通过select的id传到select
					document.getElementById("inputBid").appendChild(op);
				}
			}
            function findSmallSell(types) {
            	document.getElementById("inputSid").innerHTML="";
				for (var i = 0; i < types.length; i++) {
					var op=new Option(types[i].name,types[i].id);
					//获取的name和id 通过select的id传到select
					document.getElementById("inputSid").appendChild(op);
				}
			}
			$.ajax({
               url:"findAllBigType",
                dataType:"jsonp",
		        jsonpCallback:"findSell"
			});
			$("#inputBid").change(function() {
				$.ajax({
		               url:"findAllSmallType",
		                dataType:"jsonp",
		                data:"bid="+$(this).val(),
				        jsonpCallback:"findSmallSell"
					});
			});
			
</script>
<script type="text/javascript" src="bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="bower_components/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js"></script>
<script type="text/javascript">
		$('#inputPublishDate').datepicker({
				language: 'zh-CN',
				format:'yyyy-mm-dd',
				autoclose:true,
			    defaultViewDate: {
				    year:new Date().getFullYear()-18
				    }
		     });
		</script>
<script type="text/javascript" src="bower_components/jquery-validation/dist/jquery.validate.js"></script>
<%--验证后添加--%>
<script type="text/javascript" src="bower_components/jquery-validation/dist/additional-methods.js"></script>
<%--默认--%>
<script type="text/javascript" src="bower_components/jquery-validation/src/localization/messages_zh.js"></script>
<script type="text/javascript">
	$(function () {
		$("#bookAddForm").validate({
			rules:{//验证的原则
			    price:{
			        required:true,
					number:true
				},
                description:{
                    required:true,
                    maxlength:120
				}
            },
				messages:{//验证不通过 应该显示消息  如果不写默认 用默认消息
			        price:{
			            required:"价格必填",
                        number:"必须填数字"
			        }
				},
            errorElement:"div",
            errorClass:"invalid-feedback",//错误消息的样式
            heighlight:function(element,errorClass,validClass){//验证提示，添加无效的验证  去掉有效的
                $(element).addClass("is-invalid").removeClass(validClass);
            },
			unheighlight:function(element,errorClass,validClass){//验证之后 去掉无效的 添加有效的
			    $(element).removeClass("is-invalid").addClass(validClass);
			},
            validClass:"is-valid"//指定纠正正确的时候输入框的样式
		  });
    });
</script>
</body>
</html>