package com.bookstore;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/buybooks")

public class BuyerBuyBooks extends HttpServlet{

	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		
		
		HttpSession session = request.getSession();
		String sessionName = (String) session.getAttribute("name");
		String sessionId = (String) session.getAttribute("id");
		
		ArrayList<ArrayList<String>> booksRecords = new ArrayList<ArrayList<String>>();
		
		if(sessionName!=null && sessionId!= null)
		{
			Connection connection = null;
			java.sql.Statement stmt = null;
			
			try
			{
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookecomdb","root", "root");
				
				System.out.println("Opened database successfully");

				stmt =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				
				ResultSet rs = stmt.executeQuery("select bookid_pk,bookname,bookimg from booktable;");
				
				
				
				for(int x=0; rs.next();x++ ) 
				{
					ArrayList<String> eachbookRecord = new ArrayList<String>();	
					eachbookRecord.add(0, String.valueOf(rs.getInt(1)));
					eachbookRecord.add(1, rs.getString(2));
					eachbookRecord.add(2, rs.getString(3));
					booksRecords.add(x,eachbookRecord);
										
				}
				
				connection.close();
				
				request.setAttribute("bookRecords", booksRecords);
				request.getRequestDispatcher("/WEB-INF/views/buyerBuyBooks.jsp").forward(request, response);
				
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
		}
		
		
		
	}
	
	
}
