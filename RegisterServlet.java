package com.bookstore;

import java.beans.Statement;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@WebServlet(urlPatterns = "/register")

public class RegisterServlet extends HttpServlet{
	public enum gender {
	    male,
	    female
	 }
	
	public enum role {
	    seller,
	    buyer,
	    admin
	 }

	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{

		request.getRequestDispatcher("/WEB-INF/views/signUp.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		
		
		
		String inputRole = request.getParameter("role");
		String inputId = request.getParameter("id");
		String inputPwd = request.getParameter("pwd");
		String inputName = request.getParameter("name");
		int inputAge =Integer.parseInt(request.getParameter("age"));
		String inputGender = request.getParameter("gender");
		String inputAddr = request.getParameter("addr");
		String inputEmail = request.getParameter("email");
		String inputPhno = request.getParameter("phno");

		int duplicateIdStat = 0;
		Connection connection = null;
		java.sql.Statement stmt = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookecomdb","root", "root");
			System.out.println("Opened database successfully");

			stmt =connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from usertable;");
			while ( rs.next() ) 
			{
				String dbId = rs.getString(1);
				if(dbId.equals(inputId))
				{
					duplicateIdStat = 1;
				}
			}
			
			
			if(duplicateIdStat == 1)
			{
				request.setAttribute("duplicateId","Id already exist. Please try another ID");
				request.getRequestDispatcher("/WEB-INF/views/signUp.jsp").forward(request, response);
			}else
			{
				/* to insert into user table */
				
		         String insertQuery = "insert into usertable(userid_pk,userpwd,username,userage,usergender,useraddr,usremailid,phone)"
		               + " values(?,?,?,?,?,?,?,?)";
		         PreparedStatement prepStmt = connection.prepareStatement(insertQuery);
		        
		         prepStmt.setString(1, inputId);
		         prepStmt.setString(2, inputPwd);
		         prepStmt.setString(3, inputName);
		         prepStmt.setInt(4, inputAge);
		         prepStmt.setString(5,inputGender);
		         prepStmt.setString(6, inputAddr);
		         prepStmt.setString(7, inputEmail);
		         prepStmt.setString(8, inputPhno);
		         prepStmt.executeUpdate();
		         
		         /* to insert into role table */
		         
		         insertQuery = "insert into userroletable(userid_fk,userrole) values(?,?)";
			     prepStmt = connection.prepareStatement(insertQuery);
			     prepStmt.setString(1, inputId);
		         prepStmt.setString(2, inputRole);
		         prepStmt.executeUpdate();
		         System.out.println("registration success");
		         request.setAttribute("success","Registration success.Please login");
		         request.getRequestDispatcher("/WEB-INF/views/signIn.jsp").forward(request, response);
			}
			
			stmt.close();
			connection.close();
			System.out.println("closed data base connection");			
		
		} catch (Exception e)
		{
			e.printStackTrace();			
		}
			
	}

}
