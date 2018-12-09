package com.oraclewfk.bookmarket.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oraclewfk.bookmarket.biz.BookBiz;
import com.oraclewfk.bookmarket.bizimpl.BookBizImpl;
import com.oraclewfk.bookmarket.model.Book;
import com.oraclewfk.bookmarket.util.PageContants;

@WebServlet(name="bookList",value = "/bookList")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public BookListServlet() {
        super();

    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取currentPage当前页
		String strCur=request.getParameter("currentPage");
		//如果不选择就是默认第一页
		if(strCur==null) {
			strCur="1";
		}	
		int currentPage=Integer.parseInt(strCur);
		//获取name
		String name=request.getParameter("name");
		//获取小类
		String strSid=request.getParameter("sid")==null?"-1":request.getParameter("sid");
		int sid=Integer.parseInt(strSid);
		//获取大类
		String strBid=request.getParameter("bid")==null?"-1":request.getParameter("bid");
		int bid=Integer.parseInt(strBid);
		
		BookBiz bookBiz=new BookBizImpl();
		List<Book> ls=bookBiz.findAll(currentPage,name,sid);
		int totalRow=bookBiz.totalRow(name,sid);
		System.out.println(totalRow);
		int totalPage=totalRow%PageContants.PAGE_SIZE==0?totalRow/PageContants.PAGE_SIZE:totalRow/PageContants.PAGE_SIZE+1;
		request.setAttribute("ls", ls);
		System.out.println("页数: "+currentPage);
		request.setAttribute("currentPage", currentPage);	
		request.setAttribute("totalPage", totalPage);
		System.out.println("总页数"+totalPage); 
		request.setAttribute("name", name);
		request.setAttribute("bid", bid);
		request.setAttribute("sid", sid);
		request.getRequestDispatcher("bookList.jsp").forward(request, response);
	}
             
}
