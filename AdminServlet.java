package com.bookstore;

import java.io.IOException;
import java.util.ArrayList;

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

@WebServlet(urlPatterns = "/admin")

public class AdminServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		
		HttpSession session = request.getSession();
		String sessionName = (String) session.getAttribute("name");
		if(sessionName!= null)
		{	
			ArrayList<ArrayList<String>> allCustRecords = new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<String>> sellerCustRecords = new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<String>> buyerCustRecords = new ArrayList<ArrayList<String>>();

			String loggedInName = null;
			Connection connection = null;
			java.sql.Statement stmt = null;
			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookecomdb","root", "root");
				System.out.println("Opened database successfully");

				stmt =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery("select * from usertable;");

				for(int x=0; rs.next();x++ ) 
				{
					ArrayList<String> custDetails = new ArrayList<String>();
					custDetails.add(0,rs.getString(1));
					custDetails.add(1,rs.getString(2));
					custDetails.add(2,rs.getString(3));
					custDetails.add(3,rs.getString(4));
					custDetails.add(4,rs.getString(5));
					custDetails.add(5,rs.getString(6));
					custDetails.add(6,rs.getString(7));
					custDetails.add(7,rs.getString(8));
					allCustRecords.add(custDetails);
				}
			     //System.out.println("before for loop");
				for(int x = 0;x<allCustRecords.size();x++)
				{
					ArrayList<String> custDetailsArr = allCustRecords.get(x);
					//System.out.println("iteration: "+custDetailsArr.get(0));
					String query = "select userrole from userroletable where userid_fk ='"+custDetailsArr.get(0)+"';";

					rs = stmt.executeQuery(query);
					rs.next();
					if(rs.getString(1).equals("seller"))
					{
						sellerCustRecords.add(custDetailsArr);
					}else if(rs.getString(1).equals("buyer"))
					{
						buyerCustRecords.add(custDetailsArr);
					}
				}
				//System.out.println("after for loop");

				//			HttpSession session = request.getSession();
				//			session.setAttribute("role","admin");
				//			session.setAttribute("name", loggedInName);
				
				

				request.setAttribute("loggedInName",sessionName);
				request.setAttribute("sellerRecords", sellerCustRecords);
				request.setAttribute("buyerRecords", buyerCustRecords);	



				request.getRequestDispatcher("/WEB-INF/views/admin.jsp").forward(request, response);

			}catch (Exception e)
			{
				e.printStackTrace();

			}
		}

	}

}
