package com.bookstore;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/sellerDetails")

public class SellerDetailsServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		
		HttpSession session = request.getSession();
		
		
		
		ArrayList<ArrayList<String>> sellerTransRecords = new ArrayList<ArrayList<String>>();
		
		String sellerId = request.getParameter("data");
		
		Connection connection = null;
		java.sql.Statement stmt = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookecomdb","root", "root");
			System.out.println("Opened database successfully");

			stmt =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery("select * from booktable where userid_fk ='"+sellerId+"';");
			
			while(rs.next())
			{
				ArrayList<String> eachSellerTrans = new ArrayList<>();
				eachSellerTrans.add(rs.getString(1));
				eachSellerTrans.add(rs.getString(3));
				eachSellerTrans.add(rs.getString(6));
				eachSellerTrans.add(rs.getString(7));
				eachSellerTrans.add(rs.getString(8));	
				sellerTransRecords.add(eachSellerTrans);
			}
			
			for(int x=0; x<sellerTransRecords.size();x++)
			{
				String tempBookID = sellerTransRecords.get(x).get(0);
				rs = stmt.executeQuery("select * from ordertable where bookid_fk ='"+tempBookID+"';");
				rs.last();
				if(rs.getRow()>0)
				{
					sellerTransRecords.get(x).add("Yes");
				}
				else
				{
					sellerTransRecords.get(x).add("No");
				}
			}
			
			stmt.close();
			connection.close();
		}catch (Exception e)
		{
			e.printStackTrace();	
		}
		session = request.getSession();
		String sessionName = (String) session.getAttribute("name");
		request.setAttribute("loggedInName",sessionName);
		request.setAttribute("userId",sellerId);
		request.setAttribute("sellerDetails",sellerTransRecords);
		
		
		request.getRequestDispatcher("/WEB-INF/views/sellerDetails.jsp").forward(request, response);
	}

	
	
	
}
