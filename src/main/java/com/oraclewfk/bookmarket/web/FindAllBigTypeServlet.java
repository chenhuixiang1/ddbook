package com.oraclewfk.bookmarket.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.oraclewfk.bookmarket.biz.BigTypeBiz;
import com.oraclewfk.bookmarket.bizimpl.BigTypeBizImpl;
import com.oraclewfk.bookmarket.model.BigType;

@WebServlet(name="findAllBigType",value="/findAllBigType")
public class FindAllBigTypeServlet extends HttpServlet {
			private static final long serialVersionUID = 1L;

    public FindAllBigTypeServlet() {
				super();

			}
			protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String callBack=request.getParameter("callback");
				BigTypeBiz bigTypeBiz=new BigTypeBizImpl();
				List<BigType> ls=bigTypeBiz.findAllBigType();
				//告诉客户端发送的是js
				response.setContentType("text/javascript;charset=UTF-8");
				PrintWriter out=response.getWriter();
				//java的list转成js的数组
				JSONArray jsonArray=new JSONArray(ls);
				out.println(callBack+"("+jsonArray.toString()+")");
				out.flush();
	}
}
