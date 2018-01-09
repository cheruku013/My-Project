package com.bookstore;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet(urlPatterns = "/login.do")

public class LoginServlet extends HttpServlet{
	

	
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
			{								
				
				String inputId = request.getParameter("id");
				String inputPwd = request.getParameter("pwd");
				
				 int idValid = 0;
				 
				 String loggedInName = null;
				 String loggedInID = null;
				Connection connection = null;
				java.sql.Statement stmt = null;
				try {
					connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookecomdb?useSSL=true","root", "root");
					System.out.println("Opened database successfully");
					System.out.println("before login query");
					stmt =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
					ResultSet rs = stmt.executeQuery("select * from usertable;");
					
					for(int x=0; rs.next();x++ ) 
					{
						String dbId = rs.getString(1);
						 
						String dbPwd = rs.getString(2);
						
						if(dbId.equals(inputId) && dbPwd.equals(inputPwd))
						{
							idValid = 1;
							loggedInID = dbId;
							loggedInName = rs.getString(3);
														
						}

					}
					System.out.println("after login query");					
					if(idValid == 1)
					{
						
						String query = "select userrole from userroletable where userid_fk ='"+inputId+"';";
						
						rs = stmt.executeQuery(query);
						rs.next();
						String role = rs.getString(1);
						
						
						if(role.equals("admin"))
						{							
							System.out.println("login name and id: "+loggedInName+" "+loggedInID);
							HttpSession session = request.getSession();
							session.setAttribute("role","admin");
							session.setAttribute("name", loggedInName);
							session.setAttribute("id", loggedInID);
							response.sendRedirect("admin");
							
						}else if(role.equals("seller"))
						{
							
							HttpSession session = request.getSession();
							session.setAttribute("role","seller");
							session.setAttribute("name", loggedInName);
							session.setAttribute("id", loggedInID);
							response.sendRedirect("seller");
							//request.getRequestDispatcher("/WEB-INF/views/seller.jsp").forward(request, response);							
						}else if(role.equals("buyer"))
						{
							HttpSession session = request.getSession();
							session.setAttribute("role","buyer");
							session.setAttribute("name", loggedInName);
							session.setAttribute("id", loggedInID);
							response.sendRedirect("buybooks");
						}
												
					}else
					{						
				         request.setAttribute("invalid","Invalid ID  or Password please try again");
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
