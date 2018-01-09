package com.bookstore;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/buyerBookSearch")

public class BuyerBookSearch extends HttpServlet{

	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		System.out.println("inside search dopost");
		String bookName = request.getParameter("searchbar");
		
		Connection connection = null;
		java.sql.Statement stmt = null;
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookecomdb","root", "root");
			
			System.out.println("Opened database successfully");

			stmt =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			ResultSet rs = stmt.executeQuery("select bookid_pk from booktable where bookname ='"+bookName+"';");
			
			rs.next();
			
			if(rs.getRow()>0)
			{
				String bookId = String.valueOf(rs.getInt(1));
				System.out.println(bookId);
				request.setAttribute("bookId", bookId);
				RequestDispatcher rd = request.getRequestDispatcher("buyerBookDetails");
				rd.forward(request,response);
			}else
			{
				response.sendRedirect("buybooks");
			}
			connection.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		
	}
}