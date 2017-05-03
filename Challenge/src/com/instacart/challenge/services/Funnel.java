package com.instacart.challenge.services;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.instacart.challenge.dao.ShopperApplicationsDAO;
import com.instacart.challenge.model.ApplicationStatistics;

/**
 * Servlet implementation class Funnel
 */
@WebServlet( 
		name = "Funnel", 
		urlPatterns = {
			"/funnels",				
		}, 
		displayName = "Funnel"
)

public class Funnel extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Gson gson =  new GsonBuilder().create();
	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Funnel() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		String startDate = arg0.getParameter("start_date");
		String endDate = arg0.getParameter("end_date");
		
		ShopperApplicationsDAO applicationsDAO = new ShopperApplicationsDAO();
		try {
			List<ApplicationStatistics> statistics = applicationsDAO.getApplicationStatistics(sdf.parse(startDate), sdf.parse(endDate));
			arg1.getWriter().write(formatResponse(statistics));
			arg1.setContentType("application/json");
			arg1.getWriter().close();
			arg1.setStatus(HttpServletResponse.SC_OK);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			arg1.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		
	}
	
	private String formatResponse(List<ApplicationStatistics> data){
		JsonObject container = new JsonObject();
		for(ApplicationStatistics statistics : data){
			
			JsonObject appStatObj = new JsonObject();
			appStatObj.addProperty("applied", statistics.getApplied());
			appStatObj.addProperty("hired", statistics.getHired());
			appStatObj.addProperty("rejected", statistics.getRejected());
			appStatObj.addProperty("quiz_started", statistics.getQuizStarted());
			appStatObj.addProperty("quiz_completed", statistics.getQuizFinished());
			appStatObj.addProperty("onboarding_requested", statistics.getOnBoardingRequested());
			appStatObj.addProperty("onboarding_completed", statistics.getOnBoardingCompleted());
			
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.set(Calendar.WEEK_OF_YEAR, statistics.getWeekOfYear());
			calendar.set(Calendar.YEAR, statistics.getYear());
			
			String initDate = sdf.format(calendar.getTime());
			
			calendar.add(Calendar.DAY_OF_YEAR, 6);
			
			String endDate = sdf.format(calendar.getTime());
			
			container.add(initDate+"-"+endDate, appStatObj);
		}
		
		return gson.toJson(container);
	}

}
