package com.oraclewfk.bookmarket.web;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.oraclewfk.bookmarket.biz.BookBiz;
import com.oraclewfk.bookmarket.bizimpl.BookBizImpl;
import com.oraclewfk.bookmarket.model.Book;
import com.oraclewfk.bookmarket.util.MyBeanUtils;

@WebServlet(name = "bookAdd",value="/bookAdd")
@MultipartConfig
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public BookServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取book类
		Book book=new Book();
		MyBeanUtils.populate(book, request.getParameterMap(),"yyyy-MM-dd");
		Part part = request.getPart("photo");
		String newfile = null;
		if (part.getHeader("Content-Disposition").contains("; filename=")) {
			if (part.getSubmittedFileName()!= null && !part.getSubmittedFileName().equals("")) {
				String exe = part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf(".") + 1);
				newfile = UUID.randomUUID() + "." + exe;
				part.write(request.getServletContext().getRealPath("/upload/") + newfile);
			}
		}
		book.setPhoto(newfile);
		BookBiz bookBiz=new BookBizImpl();
		boolean ret=bookBiz.save(book);
		if (ret) {
			response.sendRedirect("bookList");
		} else {
			request.setAttribute("book", book);
            request.getRequestDispatcher("/bookAdd.jsp").forward(request, response);
		}
	}

}
