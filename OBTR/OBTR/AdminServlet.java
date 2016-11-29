package com.svecw.obtr.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.svecw.obtr.dto.City;
import com.svecw.obtr.dto.CityList;
import com.svecw.obtr.dto.DestinationStations;
import com.svecw.obtr.dto.Service;
import com.svecw.obtr.dto.SourceStations;
import com.svecw.obtr.dto.Station;
import com.svecw.obtr.dto.ServiceStation;
import com.svecw.obtr.service.AdminService;
import com.svecw.obtr.service.IAdminService;


/**
 * Servlet implementation class Admin
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		String path = request.getHeader("referer");
		if (path.contains("addCity")) {
			City cityDTO = new City();
			cityDTO.setCityName(request.getParameter("cityName"));
			IAdminService iAdminService = new AdminService();
			int status = iAdminService.addCity(cityDTO);
			if (status != 0) {
				request.setAttribute("message", "City Added Successfully");
				request.getRequestDispatcher("addCity.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "Fails to add parameter");
				request.getRequestDispatcher("addCity.jsp").forward(request, response);
			}
		} else if (path.contains("adminDataEntryForm")){
			IAdminService iAdminService = new AdminService();
			List<CityList> citiesListDTO = iAdminService.getCities();
			request.setAttribute("citiesList", citiesListDTO);
			String trace = request.getParameter("uId");
			if (trace.equals("'addStation'"))
				request.getRequestDispatcher("addStation.jsp").forward(request, response);
			else if(trace.equals("'addService'"))
				request.getRequestDispatcher("addService.jsp").forward(request, response);
		} else if (path.contains("AdminServlet")) {
			String trace = request.getParameter("uId");
			out.println(trace);
			if (trace.equals("'addStation'")) {
				Station stationDTO = new Station();
				stationDTO.setStationName(request.getParameter("stationName"));
				stationDTO.setCityId(Integer.parseInt(request.getParameter("cityId")));
				IAdminService iAdminService = new AdminService();
				int status = iAdminService.addStation(stationDTO);
				if (status != 0) {
					request.setAttribute("message", "Station Added Successfully");
					request.getRequestDispatcher("addStation.jsp").forward(request, response);
				} else {
					request.setAttribute("message", "Fails to add parameter");
					request.getRequestDispatcher("addStation.jsp").forward(request, response);
				}
			}  else {
			if (trace.equals("'addService'")) {
				Service serviceDTO = new Service();
				serviceDTO.setSourceId(Integer.parseInt(request.getParameter("sourceId")));
				serviceDTO.setDestinationId(Integer.parseInt(request.getParameter("destinationId")));
				serviceDTO.setNoOfSeats(Integer.parseInt(request.getParameter("noOfSeats")));
				serviceDTO.setFare(Integer.parseInt(request.getParameter("fare")));
				serviceDTO.setDistance(Integer.parseInt(request.getParameter("distance")));
				serviceDTO.setJourneyDate(request.getParameter("journeyDate"));
				serviceDTO.setDepartureTime(request.getParameter("departureTime"));
				serviceDTO.setArrivalTime(request.getParameter("arrivalTime"));
				serviceDTO.setStatus(request.getParameter("status"));
				IAdminService iAdminService = new AdminService();
				int serviceId = iAdminService.addService(serviceDTO);
				if (serviceId != 0) {
					HttpSession session = request.getSession();
					session.setAttribute("serviceId", serviceId);
					ServiceStation serviceStationDTO = new ServiceStation();
					serviceStationDTO.setSourceId(Integer.parseInt(request.getParameter("sourceId")));
					serviceStationDTO.setDestinationId(Integer.parseInt(request.getParameter("destinationId")));
					List<SourceStations> sourceStationList = iAdminService.sourceStation(serviceStationDTO);
					List<DestinationStations> destinationStationList = iAdminService.destinationStation(serviceStationDTO);
					request.setAttribute("sourceStationList", sourceStationList);
					request.setAttribute("destinationStationList", destinationStationList);
					request.setAttribute("serviceId", serviceId);
					request.getRequestDispatcher("serviceStation.jsp").forward(request, response);
				} else {
					request.setAttribute("message", "Fails to add service");
					request.getRequestDispatcher("addService.jsp").forward(request, response);
				}
			} else if(trace.equals("'serviceStation'")) {
				Service serviceDTO = new Service();
				serviceDTO.setSourceStationId(Integer.parseInt(request.getParameter("sourceStation")));
				serviceDTO.setDestinationStationId(Integer.parseInt(request.getParameter("destinationStation")));
				HttpSession session = request.getSession();
				int serviceId = Integer.parseInt(session.getAttribute("serviceId").toString());
				serviceDTO.setServiceId(serviceId);
				IAdminService iAdminService = new AdminService();
				int status = iAdminService.addStationstoServices(serviceDTO);
				if (status != 0) {
					request.setAttribute("message", "Success to add service");
					request.getRequestDispatcher("serviceStation.jsp").forward(request, response);
				} else {
					request.setAttribute("message", "Fails to add service");
					request.getRequestDispatcher("serviceStation.jsp").forward(request, response);
				}

				
			}
		}
	}

}
}
