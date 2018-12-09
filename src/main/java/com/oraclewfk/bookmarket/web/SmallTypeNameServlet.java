package com.oraclewfk.bookmarket.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oraclewfk.bookmarket.biz.SmallTypeBiz;
import com.oraclewfk.bookmarket.bizimpl.SmallTypeBizImpl;
import com.oraclewfk.bookmarket.model.SmallType;
import com.oraclewfk.bookmarket.util.MyBeanUtils;

@WebServlet(name="smallTypeName",value="/smallTypeName")
public class SmallTypeNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SmallTypeNameServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SmallType smallType=new SmallType();
		MyBeanUtils.populate(smallType, request.getParameterMap());
		SmallTypeBiz smallTypeBiz=new SmallTypeBizImpl();
		boolean b=smallTypeBiz.save(smallType);
		if (b) {
			response.sendRedirect("main.jsp");
		} else {
			request.setAttribute("smallType", smallType);
			request.getRequestDispatcher("/smalltypename.jsp").forward(request,response);
		}
	}

}
