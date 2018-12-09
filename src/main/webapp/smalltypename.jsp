<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>小类名</title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="bower_components/bootswatch/dist/materia/bootstrap.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="container-fluid" style="width: 80%;">
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-body">
						<form action="smallTypeName" method="post">
								<div class="form-group row">
								<label for="inputBid" class="col-sm-2 col-form-label text-right">大类名</label>
								<div class="col-sm-10">
									<select name="bid" id="inputBid" class="form-control">
									
									</select>
								</div>
							</div>
							
							<div class="form-group row">
								<label for="inputName" class="col-sm-2 col-form-label text-right">小类名</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputName" placeholder="小类名" name="name">
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
       /* var xhr=new XMLHttpRequest();
        xhr.open("GET","findAllBigType");//method  url
        xhr.send();//点击拨打/
        xhr.onreadystatechange=function(){
               if(xhr.readyState==4){//请求完全接受到了
                   if(xhr.status==200){//请求成功了
                        //让字符串在javascript执行用eval()
                	   eval(xhr.responseText);
                	  // console.dir(eval(xhr.responseText));
                       }
                  }
            }*/
        
            function findSell(types) {
				for (var i = 0; i < types.length; i++) {
					var op=new Option(types[i].name,types[i].id);
					//获取的name和id 通过select的id传到select
					document.getElementById("inputBid").appendChild(op);
				}
			}
			$.ajax({
               url:"findAllBigType",
                dataType:"jsonp",
		        jsonpCallback:"findSell"
			});
</script>
</body>
</html>