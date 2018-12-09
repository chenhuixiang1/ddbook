package com.oraclewfk.bookmarket.web;

import com.oraclewfk.bookmarket.biz.BookBiz;
import com.oraclewfk.bookmarket.biz.SmallTypeBiz;
import com.oraclewfk.bookmarket.bizimpl.BookBizImpl;
import com.oraclewfk.bookmarket.bizimpl.SmallTypeBizImpl;
import com.oraclewfk.bookmarket.model.Book;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "tobookEdit",value = "/tobookEdit")
public class TobookEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request,response);
}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取id
        String strId=request.getParameter("id");
        int id=Integer.parseInt(strId);
        BookBiz bookBiz=new BookBizImpl();
        Book book=bookBiz.findBookId(id);
        //获取SamllType中的Bid
        SmallTypeBiz smallTypeBiz=new SmallTypeBizImpl();
        int bid=smallTypeBiz.findBidById(book.getSid());
        request.setAttribute("book",book);
        request.setAttribute("bid",bid);
        request.getRequestDispatcher("/bookEdit.jsp").forward(request,response);
    }
}
