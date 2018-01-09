package com.bookstore;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/sellerOrderTrackUpdate")

public class SellerBookOrderTrackUpdate extends HttpServlet{

	public enum ordtrack {
		packed,
		arrived,
		delivered,
		returned,
		placed
	}
	
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		String ordStatus = request.getParameter("statusCmb");
		
		Connection connection = null;
		java.sql.Statement stmt = null;

		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookecomdb","root", "root");
			System.out.println("Opened database successfully");

			
			String insertQuery = "UPDATE orderdetailtable SET ordertrack = ? WHERE orderid_pk = ?";
		         PreparedStatement prepStmt = connection.prepareStatement(insertQuery);
		        
		         prepStmt.setString(1, ordStatus);
		         prepStmt.setInt(2, orderId);
		         
		         prepStmt.executeUpdate();
		         connection.close();
		         
		         response.sendRedirect("sellerOrderTrack");

		}catch(Exception e)
		{
			e.printStackTrace();
		}	
	}


	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		HttpSession session = request.getSession();
		String sessionName = (String) session.getAttribute("name");
		String sessionId = (String) session.getAttribute("id");


		if(sessionName!=null && sessionId!= null)
		{

			int orderId = Integer.parseInt(request.getParameter("orderId"));
			String orderTrack = null;
			System.out.println("inside track update do:"+orderId);

			Connection connection = null;
			java.sql.Statement stmt = null;

			try
			{
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookecomdb","root", "root");

				System.out.println("Opened database successfully");

				stmt =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

				ResultSet rs = stmt.executeQuery("select ordertrack from orderdetailtable where orderid_pk ="+orderId+";");
				rs.next();
				orderTrack = rs.getString(1);
				connection.close();
				request.setAttribute("orderId", orderId);
				request.setAttribute("orderStat", orderTrack);
				request.setAttribute("loggedInName",sessionName);
				request.getRequestDispatcher("/WEB-INF/views/booksOrdersTrackUpdate.jsp").forward(request, response);

			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
