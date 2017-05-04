package com.instacart.challenge.services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.instacart.challenge.model.ShopperApplication;

/**
 * Servlet implementation class UpdateSessionData
 */
@WebServlet("/UpdateSessionData")
public class UpdateSessionData extends HttpServlet {
	
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 3662217651620482727L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateSessionData() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
    	
    	HttpSession httpSession = arg0.getSession();
    	ShopperApplication application = (ShopperApplication) httpSession.getAttribute("sessionData");
    	
    	if(application == null){
    		return; 
    	}
    	
    	String paramName = arg0.getParameter("name");
    	String paramValue = arg0.getParameter("value");
    	
		if (paramName.equals("fname")) {
			application.setFirstName(paramValue);
		} else if (paramName.equals("lname")) {
			application.setLastName(paramValue);
		} else if (paramName.equals("email")) {
			application.setEmail(paramValue);
		} else if (paramName.equals("number")) {
			application.setPhoneNumber(paramValue);
		} else {
			application.setReferralCode(paramValue);
		}
    	
    	arg1.setStatus(HttpServletResponse.SC_OK);
    }

}
