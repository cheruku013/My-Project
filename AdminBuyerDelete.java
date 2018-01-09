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

@WebServlet(urlPatterns = "/adminBuyerDelete")
public class AdminBuyerDelete extends HttpServlet{

	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		String buyerId = request.getParameter("buyerID");

		Connection connection = null;
		java.sql.Statement stmt = null;
		try{
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookecomdb","root", "root");
			System.out.println("Opened database successfully");

			stmt =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery("select orderid_pk from orderdetailtable where userid_fk ='"+buyerId+"';");

			ArrayList<Integer> orderIds = new ArrayList<>();

			for(int x=0;rs.next();x++)
			{
				orderIds.add(x,rs.getInt(1));
			}
			for(int x = 0;x<orderIds.size();x++)
			{
				String deleteQuery = "delete from ordertable where orderid_fk =?";
				PreparedStatement prepStmt = connection.prepareStatement(deleteQuery);
				prepStmt.setInt(1,orderIds.get(x));				 
				prepStmt.executeUpdate();
			}

			String deleteQuery = "delete from orderdetailtable where userid_fk =?";
			PreparedStatement prepStmt = connection.prepareStatement(deleteQuery);
			prepStmt.setString(1,buyerId);				 
			prepStmt.executeUpdate();

			String deleteQuery1 = "delete from userroletable where userid_fk =?";
			PreparedStatement prepStmt1 = connection.prepareStatement(deleteQuery1);
			prepStmt1.setString(1,buyerId);				 
			prepStmt1.executeUpdate();
			
			String deleteQuery2 = "delete from usertable where userid_pk =?";
			PreparedStatement prepStmt2 = connection.prepareStatement(deleteQuery2);
			prepStmt2.setString(1,buyerId);				 
			prepStmt2.executeUpdate();

			connection.close();

			response.sendRedirect("admin");


		}catch (Exception e)
		{
			e.printStackTrace();			
		}

	}
}
