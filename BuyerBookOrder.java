package com.bookstore;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Calendar;
import java.util.Date;

@WebServlet(urlPatterns = "/buyerBookOrder")
public class BuyerBookOrder extends HttpServlet{

	public enum ordtrack {
		packed,
		arrived,
		delivered,
		returned,
		placed
	}
	
	public enum ordpayment {
		online,
		cod
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		HttpSession session = request.getSession();
		String sessionName = (String) session.getAttribute("name");
		String sessionId = (String) session.getAttribute("id");
		String bookId = request.getParameter("bookId");
		
		java.sql.Date currDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		java.sql.Time currTime = new java.sql.Time(new java.util.Date().getTime());
		Calendar c = Calendar.getInstance();
		 c.set(Calendar.MILLISECOND, 0);
		currTime.setTime(c.getTimeInMillis());
		

		System.out.println("inside order:"+bookId);
		if(sessionName!=null && sessionId!= null)
		{
			Connection connection = null;
			java.sql.Statement stmt = null;
			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookecomdb","root", "root");
				System.out.println("Opened database successfully");

				stmt =connection.createStatement();

				/* to insert into user table */

				String insertQuery = "insert into orderdetailtable(userid_fk,orderdate,orderpayment,ordertrack,ordertime)"
						+ " values(?,?,?,?,?)";
				
				PreparedStatement prepStmt = connection.prepareStatement(insertQuery);

				prepStmt.setString(1, sessionId);
				prepStmt.setDate(2, currDate);
				prepStmt.setString(3, "online");
				prepStmt.setString(4, "placed");
				prepStmt.setTime(5,currTime);
				
				prepStmt.executeUpdate();
				
				
				ResultSet rs = stmt.executeQuery("select orderid_pk from orderdetailtable where orderdate ='"+currDate.toString()+"' AND ordertime='"+currTime.toString()+"';");
				
				System.out.println("currDate: "+currDate.toString()+" currTime: "+toString());
				
				rs.next();
				int orderId = rs.getInt(1);
				
				insertQuery = "insert into ordertable(orderid_fk,bookid_fk)"
						+ " values(?,?)";
				
				prepStmt = connection.prepareStatement(insertQuery);

				prepStmt.setInt(1, orderId);
				prepStmt.setInt(2, Integer.parseInt(bookId));
	
				prepStmt.executeUpdate();

					

				stmt.close();
				connection.close();
				
				response.sendRedirect("buybooks");

			}catch (Exception e)
			{
				e.printStackTrace();			
			}


		}

	}

}
