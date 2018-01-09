package com.bookstore;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(urlPatterns = "/buyerBookDetails")
public class BuyerBookDetails extends HttpServlet{

	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		System.out.println("inside details: "+request.getParameter("bookId"));
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		
		HttpSession session = request.getSession();
		String sessionName = (String) session.getAttribute("name");
		String sessionId = (String) session.getAttribute("id");
		
		if(sessionName!=null && sessionId!= null)
		{
			Connection connection = null;
			java.sql.Statement stmt = null;
			
			String bookName = null;
			String bookDesc = null;
			String bookAuthor = null;
			String bookStatus = null;
			String bookGenere = null;
			String bookImg = null;
			
			try
			{
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookecomdb","root", "root");
				
				System.out.println("Opened database successfully");

				stmt =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				
				ResultSet rs = stmt.executeQuery("select * from booktable where bookid_pk ="+bookId+";");
				
				rs.next();
				
				bookName = rs.getString(3);
				bookDesc = rs.getString(4);
				bookAuthor = rs.getString(5);				
				bookStatus = rs.getString(6);
				bookGenere = rs.getString(8);
				bookImg = rs.getString(9);
				
				connection.close();
				
				request.setAttribute("bkId", bookId);
				request.setAttribute("bkName", bookName);
				request.setAttribute("bkDesc", bookDesc);
				request.setAttribute("bkAuthor", bookAuthor);
				request.setAttribute("bkStat", bookStatus);
				request.setAttribute("bkGenere", bookGenere);
				request.setAttribute("bkImg", bookImg);
				request.setAttribute("price", "50");
				
				request.getRequestDispatcher("/WEB-INF/views/buyerBookDetails.jsp").forward(request, response);				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		System.out.println("Book details erveled bookID:"+bookId);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		System.out.println("inside details: "+request.getAttribute("bookId"));
		int bookId = Integer.parseInt(request.getAttribute("bookId").toString());
		
		HttpSession session = request.getSession();
		String sessionName = (String) session.getAttribute("name");
		String sessionId = (String) session.getAttribute("id");
		
		if(sessionName!=null && sessionId!= null)
		{
			Connection connection = null;
			java.sql.Statement stmt = null;
			
			String bookName = null;
			String bookDesc = null;
			String bookAuthor = null;
			String bookStatus = null;
			String bookGenere = null;
			String bookImg = null;
			
			try
			{
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookecomdb","root", "root");
				
				System.out.println("Opened database successfully");

				stmt =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				
				ResultSet rs = stmt.executeQuery("select * from booktable where bookid_pk ="+bookId+";");
				
				rs.next();
				
				bookName = rs.getString(3);
				bookDesc = rs.getString(4);
				bookAuthor = rs.getString(5);				
				bookStatus = rs.getString(6);
				bookGenere = rs.getString(8);
				bookImg = rs.getString(9);
				
				connection.close();
				
				request.setAttribute("bkId", bookId);
				request.setAttribute("bkName", bookName);
				request.setAttribute("bkDesc", bookDesc);
				request.setAttribute("bkAuthor", bookAuthor);
				request.setAttribute("bkStat", bookStatus);
				request.setAttribute("bkGenere", bookGenere);
				request.setAttribute("bkImg", bookImg);
				request.setAttribute("price", "50");
				
				request.getRequestDispatcher("/WEB-INF/views/buyerBookDetails.jsp").forward(request, response);				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		System.out.println("Book details erveled bookID:"+bookId);
	}
	
}
