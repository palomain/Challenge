package com.instacart.challenge.dao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.instacart.challenge.model.ShopperApplication;

/**
 * Servlet implementation class StoreSessionData
 */
@WebServlet("/StoreSessionData")
public class StoreSessionData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreSessionData() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
		
		String email = arg0.getParameter("email");
		String fname = arg0.getParameter("fname");
		String lname = arg0.getParameter("lname");
		String rcode = arg0.getParameter("rcode");
		String phone = arg0.getParameter("phone");
		
		arg0.getSession(true).setAttribute("sessionData", new ShopperApplication(lname, fname, email, phone, rcode));
		arg1.sendRedirect("/InstacartChallenge/backgroundcheck.html?email=" + email +"&fname="+fname +"&lname="+lname+"&rcode="+rcode+"&phone="+phone);
		
		
	}

}
