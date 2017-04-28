package com.instacart.challenge.services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.instacart.challenge.dao.ShopperApplicationsDAO;

/**
 * Servlet implementation class AddShopperApplication
 */
@WebServlet("/AddShopperApplication")
public class AddShopperApplication extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddShopperApplication() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		String email = arg0.getParameter("email");
		String fname = arg0.getParameter("fname");
		String lname = arg0.getParameter("lname");
		String rcode = arg0.getParameter("rcode");
		String phone = arg0.getParameter("phone");
		
		arg0.getSession().setAttribute("email", email);
		System.out.println("Email is " + email + " and name is " + fname + " " +lname );
		
		
	}

}
