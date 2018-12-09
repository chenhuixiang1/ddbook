package com.oraclewfk.bookmarket.web;

import com.oraclewfk.bookmarket.biz.AdminBiz;
import com.oraclewfk.bookmarket.bizimpl.AdminBizImpl;
import com.oraclewfk.bookmarket.model.Admin;
import com.oraclewfk.bookmarket.util.MyBeanUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "login",value = "/login")
public class LoginServlet extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           //获取Admin
            Admin admin=new Admin();
            MyBeanUtils.populate(admin,request.getParameterMap());
            //获取vcode
            String vcode=request.getParameter("vcode");
            String sercVcode= (String) request.getSession().getAttribute("validateCode");
            //判断输入取消大小
           /* if(!sercVcode.equalsIgnoreCase(vcode)){
                request.setAttribute("msg","验证码有误！！！");
                request.setAttribute("admin",admin);
                request.getRequestDispatcher("/login.jsp").forward(request,response);
                return;
            }
            //服务器校验
            ValidatorFactory validatorFactory= Validation.buildDefaultValidatorFactory();
            Validator validator=validatorFactory.getValidator();
            //违反的约束
            Set<ConstraintViolation<Admin>> cv=validator.validate(admin);
            if(cv.size()>0){
                Map<String,String> errors=new HashMap<>();
                for (ConstraintViolation<Admin> cc: cv) {
                     errors.put(cc.getPropertyPath().toString(),cc.getMessage());
                }
                request.setAttribute("errors",errors);
                request.setAttribute("admin",admin);
                request.getRequestDispatcher("/login.jsp").forward(request,response);
                return;
            }*/
            //判断输入取消大小
            Map<String,String> errors=new HashMap<>();
            if(!sercVcode.equalsIgnoreCase(vcode)){
                //如果输入的验证码不正确  就设置提示信息 验证码有误
                request.setAttribute("msg","验证码有误！！！");
            }
            //服务器校验
            ValidatorFactory validatorFactory= Validation.buildDefaultValidatorFactory();
            Validator validator=validatorFactory.getValidator();
            //违反的约束
            Set<ConstraintViolation<Admin>> cv=validator.validate(admin);
            if(cv.size()>0){
                for (ConstraintViolation<Admin> cc: cv) {
                    errors.put(cc.getPropertyPath().toString(),cc.getMessage());
                }
            }
            if(errors.size()>0){
                request.setAttribute("errors",errors);
                request.setAttribute("admin",admin);
                request.getRequestDispatcher("/login.jsp").forward(request,response);
                return;
            }

            AdminBiz adminBiz =new AdminBizImpl();
            boolean b=adminBiz.findNameAndPwd(admin);
            System.out.println(b);
            if(b){
                request.getSession().setAttribute("Logined",true);
                response.sendRedirect("main.jsp");
            }else{
                request.setAttribute("admin",admin);
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
