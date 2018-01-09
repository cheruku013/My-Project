package com.bookstore;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/buyerOrderCancel")

public class BuyerOrderCancel extends HttpServlet{

	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		
		System.out.println("inside cancel ordId:"+request.getParameter("orderId"));
		
		HttpSession session = request.getSession();
		String sessionName = (String) session.getAttribute("name");
		String sessionId = (String) session.getAttribute("id");
		System.out.println("deleting orderid:"+request.getParameter("orderId"));
		int orderId = Integer.parseInt(request.getParameter("orderId"));

		
		if(sessionName!=null && sessionId!= null)
		{
			Connection connection = null;
			java.sql.Statement stmt = null;

			try
			{
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookecomdb","root", "root");
				
				System.out.println("Opened database successfully");

				stmt =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				
				String deleteQuery = "delete from ordertable where orderid_fk =?";
				
		         PreparedStatement prepStmt = connection.prepareStatement(deleteQuery);
		        
		         prepStmt.setInt(1, orderId);

		         prepStmt.executeUpdate();
		         /* delete recrd from order table */
		         deleteQuery = "delete from orderdetailtable where orderid_pk =?";
		         
		         prepStmt = connection.prepareStatement(deleteQuery);
			        
		         prepStmt.setInt(1, orderId);

		         prepStmt.executeUpdate();
		         
		         connection.close();
					
				 response.sendRedirect("buyerOrderDetails");
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}	
			
		}
		
	}
	
}
