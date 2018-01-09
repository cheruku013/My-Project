package com.bookstore;

import javax.servlet.http.HttpServlet;
import java.beans.Statement;
import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet(urlPatterns = "/buyerDetails")

public class BuyerDetailsServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		ArrayList<ArrayList<String>> buyerTransRecords = new ArrayList<ArrayList<String>>();

		String buyerId = request.getParameter("data");

		Connection connection = null;
		java.sql.Statement stmt = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookecomdb","root", "root");
			System.out.println("Opened database successfully");

			stmt =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery("select * from orderdetailtable where userid_fk ='"+buyerId+"';");

			while(rs.next())
			{
				ArrayList<String> eachBuyerTrans = new ArrayList<>();
				eachBuyerTrans.add(rs.getString(1));
				eachBuyerTrans.add(rs.getString(3));
				eachBuyerTrans.add(rs.getString(4));
				eachBuyerTrans.add(rs.getString(5));
				buyerTransRecords.add(eachBuyerTrans);
			}

			for(int x=0; x<buyerTransRecords.size();x++)
			{
				String tempOrderID = buyerTransRecords.get(x).get(0);
				rs = stmt.executeQuery("select bookid_fk from ordertable where orderid_fk ='"+tempOrderID+"';");
				rs.next();
				buyerTransRecords.get(x).add(1,rs.getString(1));
			}

			stmt.close();
			connection.close();
		}catch (Exception e)
		{
			e.printStackTrace();	
		}
		
		HttpSession session = request.getSession();
		String sessionName = (String) session.getAttribute("name");
		request.setAttribute("loggedInName",sessionName);
		request.setAttribute("userId",buyerId);
		request.setAttribute("buyerDetails",buyerTransRecords);
		
		request.getRequestDispatcher("/WEB-INF/views/buyerDetails.jsp").forward(request, response);
	}

}
