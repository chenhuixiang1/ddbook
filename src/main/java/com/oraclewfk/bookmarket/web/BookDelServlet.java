package com.oraclewfk.bookmarket.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oraclewfk.bookmarket.biz.BookBiz;
import com.oraclewfk.bookmarket.bizimpl.BookBizImpl;

/**
 * Servlet implementation class BookDelServlet
 */
@WebServlet(name="bookDel",value = "/bookDel")
public class BookDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public BookDelServlet() {
        super();
    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String strId=request.getParameter("id");
			int id=Integer.parseInt(strId);
			System.out.println("id为"+id);
			BookBiz bookBiz=new BookBizImpl();
			int ret=bookBiz.delId(id);
			response.sendRedirect("bookList");

		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
