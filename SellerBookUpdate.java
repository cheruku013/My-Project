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

@WebServlet(urlPatterns = "/sellerBookUpdate")


public class SellerBookUpdate extends HttpServlet{

	
	public enum bkgenere {
		drama,
		action,
		science,
		horror,
		health,
		guide,
		travel,
		math,
		art,
		comic,
		poetry,
		cook,
		history,
		prayer,
		fantasy,
		diaries,
		biography,
		children
	}
	public enum bkstat {
		Old,
		New
		
	}
	
	
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{		
		HttpSession session = request.getSession();
		String sessionName = (String) session.getAttribute("name");
		
		String bookId = request.getParameter("bookId");
		String bookName = null;
		String bookDesc = null;
		String bookAuthor = null;
		String bookStatus = null;
		String bookCategory = null;
		
		
		Connection connection = null;
		java.sql.Statement stmt = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookecomdb","root", "root");
			System.out.println("Opened database successfully");

			stmt =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery("select * from booktable where bookid_pk ='"+bookId+"';");
			
			while(rs.next())
			{
				bookName = rs.getString(3);
				bookDesc = rs.getString(4);
				bookAuthor = rs.getString(5);
				bookStatus = rs.getString(6);
				bookCategory = rs.getString(8);
			}
			connection.close();
			
			request.setAttribute("bookID",bookId);
			request.setAttribute("bookName",bookName);
			request.setAttribute("bookDesc",bookDesc);
			request.setAttribute("bookAuthor",bookAuthor);
			request.setAttribute("bookStatus",bookStatus);
			request.setAttribute("bookCategory",bookCategory);
			request.setAttribute("loggedInName",sessionName);
			request.getRequestDispatcher("/WEB-INF/views/bookUpdate.jsp").forward(request, response);
			
			System.out.println(bookName+":"+bookDesc+":"+bookAuthor+":"+bookStatus+":"+bookCategory);
		}catch (Exception e)
		{
			e.printStackTrace();			
		}

		
		//String buyerId = request.getParameter("bookId");
	                                           		
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{		
		System.out.println("inside update constructor do post"+ request.getParameter("bookId"));
		
		String bookId = request.getParameter("bookId");
		String bookName = request.getParameter("name");
		String bookDesc = request.getParameter("desc");
		String bookAuthor = request.getParameter("author");
		String bookStatus = request.getParameter("stat");
		String bookCategory = request.getParameter("genere");
		
		Connection connection = null;
		java.sql.Statement stmt = null;
		
		try {
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookecomdb","root", "root");
			System.out.println("Opened database successfully");

			
			String insertQuery = "UPDATE booktable SET bookname = ?,bookdesc = ?,bookauthor = ?,booknewstatus = ?,bookcategory = ? " + " WHERE bookid_pk = ?";
		         PreparedStatement prepStmt = connection.prepareStatement(insertQuery);
		        
		         prepStmt.setString(1, bookName);
		         prepStmt.setString(2, bookDesc);
		         prepStmt.setString(3, bookAuthor);
		         prepStmt.setString(4, bookStatus);
		         prepStmt.setString(5, bookCategory);
		         prepStmt.setInt(6, Integer.parseInt(bookId));
		         prepStmt.executeUpdate();
		         connection.close();
		         
		         response.sendRedirect("sellerBookAlter");
		}catch(Exception e)
		{			
		   e.printStackTrace();
		}
		

		//String buyerId = request.getParameter("bookId");
	                                           		
	}
}
