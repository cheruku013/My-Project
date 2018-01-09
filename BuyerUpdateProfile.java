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

@WebServlet(urlPatterns = "/buyerProfileUpdate")

public class BuyerUpdateProfile extends HttpServlet{

	
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		HttpSession session = request.getSession();
		String sessionId = (String) session.getAttribute("id");
		String sessionName = (String) session.getAttribute("name");
		Connection connection = null;
		java.sql.Statement stmt = null;

		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookecomdb","root", "root");
			
			System.out.println("Opened database successfully");

			stmt =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			ResultSet rs = stmt.executeQuery("select * from usertable where userid_pk ='"+sessionId+"';");
			
			rs.next();
			
			String userPwd = rs.getString(2);
			String userName = rs.getString(3);
			String userAge = String.valueOf(rs.getInt(4));
			String userGender = rs.getString(5);
			String userAddr = rs.getString(6);
			String userMail = rs.getString(7);
			String userPhone = rs.getString(8);
			
			connection.close();
			
			request.setAttribute("loggedInName",sessionName);
			request.setAttribute("userPwd",userPwd);
			request.setAttribute("userName",userName);
			request.setAttribute("userAge",userAge);
			request.setAttribute("userGender",userGender);
			request.setAttribute("userAddr",userAddr);
			request.setAttribute("userMail",userMail);
			request.setAttribute("userPhone",userPhone);
			
			request.getRequestDispatcher("/WEB-INF/views/buyerProfileUpdate.jsp").forward(request, response);

			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		
	}
	
	public enum gender {
		male,
		female
		
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		HttpSession session = request.getSession();
		String sessionId = (String) session.getAttribute("id");
		
		String userName = request.getParameter("name");
		String userPwd = request.getParameter("pwd");
		String userAge = request.getParameter("age");
		String userGender = request.getParameter("gender");
		String userAddress = request.getParameter("address");
		String userMail = request.getParameter("email");
		String userPhone = request.getParameter("phno");
		
		session.setAttribute("name", userName);
		
		Connection connection = null;
		java.sql.Statement stmt = null;

		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookecomdb","root", "root");
			
			System.out.println("Opened database successfully");

			String insertQuery = "UPDATE usertable SET userpwd = ?,username = ?,userage = ?,usergender = ?,useraddr = ?,usremailid=?,phone = ?" + " WHERE userid_pk = ?";
	        
			PreparedStatement prepStmt = connection.prepareStatement(insertQuery);
	        System.out.println("address user: "+userAddress);
	         prepStmt.setString(1, userPwd);
	         prepStmt.setString(2, userName);
	         prepStmt.setInt(3, Integer.parseInt(userAge));
	         prepStmt.setString(4, userGender);
	         prepStmt.setString(5, userAddress);
	         prepStmt.setString(6, userMail);
	         prepStmt.setString(7, userPhone);
	         prepStmt.setString(8, sessionId);
	         prepStmt.executeUpdate();
	         connection.close();
	         
	         
	         
	         response.sendRedirect("buyerProfileUpdate");

			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		
	}
	
	
}
