package com.oraclewfk.bookmarket.web;

import com.oraclewfk.bookmarket.biz.BookBiz;
import com.oraclewfk.bookmarket.bizimpl.BookBizImpl;
import com.oraclewfk.bookmarket.model.Book;
import com.oraclewfk.bookmarket.util.HtmlEcode;
import com.oraclewfk.bookmarket.util.MyBeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "doBookEdit",value="/dobookEdit")
@MultipartConfig
        public class DoBookEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取book类
        Book book = new Book();
        MyBeanUtils.populate(book, request.getParameterMap(), "yyyy-MM-dd");
        Part part = request.getPart("photo");
        String newfile = null;

        if(part.getHeader("Content-Disposition").contains("; filename=")){
            if (part.getSubmittedFileName() != null && !part.getSubmittedFileName().equals("")){
            String exe = part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf(".") + 1);
            newfile = UUID.randomUUID() + "." + exe;
            part.write(request.getServletContext().getRealPath("/upload/") + newfile);
        }
    }
    //因为修改简介 会有脚本注入就会有漏洞 为了避免恶意的攻击 添加转意字符 防止跨站脚本攻击
        book.setDescription(HtmlEcode.htmlEncode(book.getDescription()));
        book.setPhoto(newfile);
        BookBiz bookBiz = new BookBizImpl();
        boolean ret = bookBiz.update(book);
        if (ret) {
            response.sendRedirect("bookList");
        } else {
            request.setAttribute("book", book);
            request.getRequestDispatcher("/bookEdit.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
