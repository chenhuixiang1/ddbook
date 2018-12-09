<%@page import="com.oraclewfk.bookmarket.model.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>书籍删除修改</title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" type="text/css" href="bower_components/fontawesome/web-fonts-with-css/css/fontawesome-all.css">
<link rel="stylesheet" type="text/css" id="themelink" />
<script type="text/javascript" src="bower_components/jquery/dist/jquery.js"></script>
<script type="text/javascript" src="bower_components/jquery.cookie/jquery.cookie.js"></script>
<script type="text/javascript">
	//页面加载完毕后
	if ($.cookie("bootstrapthem")) {
		//如果点击修改主题 就把切换的主题切换成bootstrapthem的值
		$("#themelink").attr("href","bower_components/bootswatch/dist/" + $.cookie("bootstrapthem")+ "/bootstrap.css");
		//select的值就变成bootstrapthem的值
	} else {//如果没有 就使用默认的主题
		$("#themelink").attr("href","bower_components/bootswatch/dist/darkly/bootstrap.css");
	}
</script>
</head>
<body>
	<div class="container-fluid" style="padding: 0px">
		<div class="row" style="padding: 0px; margin: 0px">
			<div class="col-md-12" style="padding: 0px; margin: 0px">
				<nav class="navbar navbar-expand-lg navbar-light bg-light">

				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="navbar-toggler-icon"></span>
				</button>
				<a class="navbar-brand" href="#">Brand</a>
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="navbar-nav">
						<li class="nav-item active"><a class="nav-link" href="#">Link
								<span class="sr-only">(current)</span>
						</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="http://example.com" id="navbarDropdownMenuLink" data-toggle="dropdown">Dropdown link</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
								<a class="dropdown-item" href="#">Action</a> <a class="dropdown-item" href="#">Another action</a> <a class="dropdown-item" href="#">Something else here</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#">Separated link</a>
							</div>
							</li>
					</ul>
					<form class="form-inline">
						<input class="form-control mr-sm-2" type="text" />
						<button class="btn btn-primary my-2 my-sm-0" type="submit">
							Search</button>
					</form>
					<ul class="navbar-nav ml-md-auto">
						<li class="nav-item active"><a class="nav-link"
							href="javascript:void(0)"> <select id="themesel"
								class="custom-select">
									<option>darkly</option>
									<option>minty</option>
									<option>pulse</option>
									<option>lux</option>
							</select>
						</a></li>
						<li class="nav-item dropdown">
							<a href="edit.jsp" class="fa fa-power-off fa-2x" title="退出">
							</a></li>
					     </ul>
				     </div>
				</nav>
			</div>
		</div>
	</div>
		<div class="row" style="padding: 0px; margin: 0px">
			<div class="col-md-12" style="padding: 0px; margin: 0px">
					<div class="card">
					<!--card的头 -->
	                 <div class="card-header">
		<form class="form-inline"  action="bookList" method="get" id="formsearch">
  <label class="sr-only" for="c">书名</label>
  <input type="text" class="form-control mb-2 mr-sm-2" id="inputName" placeholder="书名" name="name" value=<%=request.getParameter("name")==null?"":request.getParameter("name")%>>
  
  <label class="sr-only" for="inputBid">大类名</label>
  <div class="input-group mb-2 mr-sm-2">
    <select class="form-control" id="inputBid" name="bid"><option value="-1">--请选择--</option></select>
  </div>
    <label class="sr-only" for="inputSid">小类名</label>
  <div class="input-group mb-2 mr-sm-2">
    <select class="form-control" id="inputSid" name="sid"><option value="-1">--请选择--</option></select>
  </div>
  <button type="submit" class="btn btn-primary mb-2">搜索</button>
</form>
   </div>
   <!-- card的body -->
				<div class="card-body"> 
				<table class="table table-bordered table-hover table-sm">
					<thead>
						<tr>
							<th>#</th>
							<th>书名</th>
							<th>价格</th>
							<th>作者</th>
							<th>出版社</th>
							<th>出版日期</th>
							<th>书籍简介</th>
							<th>小类</th>
							<th style="max-width: 100px">图片</th>
							<th>操作</th>
						</tr>						
					</thead>
					<tbody>
						<%
							List<Book> ls = (List<Book>) request.getAttribute("ls");
							for (Book book : ls) {
						%>
						<tr class="table-danger">
							<td><%=book.getId()%></td>
							<td><%=book.getName()%></td>
							<td><%=book.getPrice()%></td>
							<td><%=book.getAuthor()%></td>
							<td><%=book.getPublish()%></td>
							<td><%=book.getPublishDate()%></td>
							<td style="max-width: 300px"><%=book.getDescription()%></td>
							<td><%=book.getSid()%></td>
							<td style="max-width: 400px"><img src="upload/<%=book.getPhoto()%>"></td>
								<td>
								<a href="#modal-container-821124" data-toggle="modal" onclick="window.delId=<%=book.getId()%>" class="fa fa-trash fa-2x" title="删除"></a>&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="tobookEdit?id=<%=book.getId()%>" class="fa fa-edit fa-2x" title="修改"></a>


								</td>
						</tr>
						<%
							}
						%>
					</tbody>
			</table>
			</div>
			<!-- card footer -->
			<div class="card-footer" style="padding: 0px; margin: 0px">
			<nav><ul class="pagination">
									<%
										//获取总页数
										int totalPage = (Integer) request.getAttribute("totalPage");
										//获取当前页数
										int currentPage = (Integer) request.getAttribute("currentPage");
										//如果当前页是第一页的话 就让第一页不选中 
										if (currentPage == 1) {
									%>
									<li class="page-item disabled" ><a class="page-link" href="#">上一页</a></li>
									<%
									//如果不是第一页的话就选择上一页
										} else {
									%>
									<li class="page-item"><a class="page-link" href="bookList?currentPage=<%=currentPage - 1%>">上一页</a></li>
									<%
										}
									%>

									<%
										if (totalPage <= 5) {//总页数小于5 就去不显示出来(i)
											for (int i = 1; i <= totalPage; i++) {
									%>
									<li class="page-item"><a class="page-link" href="bookList?currentPage=<%=i%>"><%=i%></a></li>

									<%
										}
										} else if (currentPage <= 3) {//如果当前页是小于散的话  总页数就...
									%>
									<li class="page-item"><a class="page-link" href="bookList?currentPage=1">1</a></li>
										
									<li class="page-item"><a class="page-link" href="bookList?currentPage=2">2</a></li>
										
									<li class="page-item"><a class="page-link" href="bookList?currentPage=3">3</a></li>
										
									<li class="page-item"><a class="page-link" href="bookList?currentPage=4">4</a></li>
										
									<li class="page-item"><a class="page-link" href="bookList?currentPage=<%=totalPage%>">...<%=totalPage%></a></li>	
									<%
									}else if(currentPage <= totalPage - 3) {//如果当前页小于总页数-3的话就显示1...     ...总页数
									%>
									<li class="page-item"><a class="page-link" href="bookList?currentPage=1">1...</a></li>
									<li class="page-item"><a class="page-link" href="bookList?currentPage=<%=currentPage - 1%>"><%=currentPage - 1%></a></li>
									<li class="page-item"><a class="page-link" href="bookList?currentPage=<%=currentPage%>"><%=currentPage%></a></li>
									<li class="page-item"><a class="page-link" href="bookList?currentPage=<%=currentPage + 1%>"><%=currentPage + 1%></a></li>
									<li class="page-item"><a class="page-link" href="bookList?currentPage=<%=totalPage%>">...<%=totalPage%></a></li>
									<%
									}else{//或者就是如果当前页大于总页数-3的话 就显示1   ...最后一页
									%>
									<li class="page-item"><a class="page-link" href="bookList?currentPage=1">1...</a></li>
										
									<li class="page-item"><a class="page-link" href="bookList?currentPage=<%=totalPage - 3%>"><%=totalPage - 3%></a></li>
										
									<li class="page-item"><a class="page-link" href="bookList?currentPage=<%=totalPage - 2%>"><%=totalPage - 2%></a></li>
										
									<li class="page-item"><a class="page-link" href="bookList?currentPage=<%=totalPage - 1%>"><%=totalPage - 1%></a></li>
										
									<li class="page-item"><a class="page-link" href="bookList?currentPage=<%=totalPage%>"><%=totalPage%></a></li>
										
									<%
										}
									%>
									<%
									if(currentPage==totalPage){//如果当前页等于总页数就让最后一页无效选择
									%>
									<li class="page-item disabled"><a class="page-link" href="#">下一页</a></li>
									<%
									}else{//如果不是就选择当前页的下一页
									%>
									<li class="page-item"><a class="page-link" href="bookList?currentPage=<%=currentPage+1%>">下一页</a></li>
									<%
									}
									%>			
				             </ul>
			             </nav>
		           </div>
		      </div>
	      </div>
	  </div>
      <div class="modal fade" id="modal-container-821124" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog" data-toggle="modal" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="myModalLabel">
								删除确认框
							</h5> 
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">×</span>
							</button>
						</div>
						<div class="modal-body">
							您确定要删除吗？
						</div>
						<div class="modal-footer">
							 
							<button type="button" class="btn btn-primary" onclick="exedel(event)">
								确定
							</button> 
							<button type="button" class="btn btn-secondary" data-dismiss="modal">
								取消
							</button>
						</div>
					</div>
					
				</div>
				
			</div>
      
<script type="text/javascript" src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
<script type="text/javascript">
function findSell(types) {
	for (var i = 0; i < types.length; i++) {
		var op=new Option(types[i].name,types[i].id);
		//获取的name和id 通过select的id传到select
		document.getElementById("inputBid").appendChild(op);
	}
	//回填bid
	$("#inputBid").val('<%=request.getAttribute("bid")%>');
	//如果不产生该事件 (change) 小类就不会回填
	$("#inputBid").trigger("change");
}

function findSmallSell(types) {
	document.getElementById("inputSid").innerHTML='<option value="-1">--请选择--</option>';
	for (var i = 0; i < types.length; i++) {
		var op=new Option(types[i].name,types[i].id);
		//获取的name和id 通过select的id传到select
		document.getElementById("inputSid").appendChild(op);
	}	
	//回填sid
	$("#inputSid").val('<%=request.getAttribute("sid")%>');
}

$.ajax({
	  type:"GET",
   url:"findAllBigType",
    dataType:"jsonp",
    jsonpCallback:"findSell"
});
$("#inputBid").change(function() {
	$.ajax({
		type:"GET",
           url:"findAllSmallType",
            dataType:"jsonp",
            data:"bid="+$(this).val(),
	        jsonpCallback:"findSmallSell"
	});
});




 $(function() {
     $("#themesel").val($.cookie("bootstrapthem"));
	$("#themesel").change(function(evt){

		//切换主题并得到列表中对应主题的值
		$("#themelink").attr("href","bower_components/bootswatch/dist/"+$(evt.target).val()+"/bootstrap.css");
		//当关闭浏览器就保留多大的期限
		$.cookie("bootstrapthem",$(evt.target).val(),{expires:2});
	});
	//将改变的肢体传入select这个框(id)

	//找到带有currrent这个href然后付玉素是li的 改为active（选中）
	$('a[href="bookList?currentPage=<%=currentPage%>"]').parent("li").addClass("active");
 });

 $('a[class="page-link"][href^="bookList?currentPage="]').click(function(){
	  $(this).attr("href",$(this).attr("href")+"&"+$("#formsearch").serialize());
 });

 function exedel(event){
	window.location.href="bookDel?id="+window.delId;
}

</script>
</body>
</html>