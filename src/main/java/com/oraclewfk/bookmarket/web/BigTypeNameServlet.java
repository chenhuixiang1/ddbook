package com.oraclewfk.bookmarket.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oraclewfk.bookmarket.biz.BigTypeBiz;
import com.oraclewfk.bookmarket.bizimpl.BigTypeBizImpl;

@WebServlet(name="bigtypename",value="/bigtypename")
public class BigTypeNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BigTypeNameServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//获取参数
		String name=request.getParameter("name");
		BigTypeBiz userBiz=new BigTypeBizImpl();
		boolean b=userBiz.save(name);
		if(b) {
			response.sendRedirect("main.jsp");
		}else{
		request.setAttribute("name", name);
		request.getRequestDispatcher("bigtypename.jsp").forward(request,response);
		}
	}

}
