package com.instacart.challenge.services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DestroySession
 */
@WebServlet("/DestroySession")
public class DestroySession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestroySession() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
    	if(arg0.getSession() != null){
    		arg0.getSession().invalidate();
    	}
    	
    	arg1.sendRedirect("/InstacartChallenge/");
    }

}
