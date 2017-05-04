package com.instacart.challenge.services;

import java.io.IOException;
import java.util.Formatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.instacart.challenge.dao.ShopperApplicationsDAO;
import com.sun.javafx.binding.StringFormatter;

/**
 * Servlet implementation class SaveShopperApplication
 */
@WebServlet("/SaveShopperApplication")
public class SaveShopperApplication extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveShopperApplication() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		String email = arg0.getParameter("email");
		String fname = arg0.getParameter("fname");
		String lname = arg0.getParameter("lname");
		String rcode = arg0.getParameter("rcode");
		String phone = arg0.getParameter("number");
		
		arg0.getSession().invalidate();
		
		ShopperApplicationsDAO dao = new ShopperApplicationsDAO();

		Formatter formatter = new Formatter();
		try {
			
			dao.addShopper(fname, lname, email, phone, rcode);
			arg1.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			arg1.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}finally{
			formatter.close();
		}
		
		
	}

}
