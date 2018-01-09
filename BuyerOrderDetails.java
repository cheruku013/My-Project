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

@WebServlet(urlPatterns = "/buyerOrderDetails")

public class BuyerOrderDetails extends HttpServlet{
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		HttpSession session = request.getSession();
		String sessionName = (String) session.getAttribute("name");
		String sessionId = (String) session.getAttribute("id");

		ArrayList<ArrayList<String>> orderRecords = new ArrayList<ArrayList<String>>();

		if(sessionName!=null && sessionId!= null)
		{
			Connection connection = null;
			java.sql.Statement stmt = null;

			try
			{
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookecomdb","root", "root");
				
				//System.out.println("orderdetail breakpoint1");

				stmt =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				
				ResultSet rs = stmt.executeQuery("select orderid_pk,orderdate,ordertrack,ordertime from orderdetailtable where userid_fk ='"+sessionId+"';");
				
				ArrayList<String> orderIdList = new ArrayList<String>();
				ArrayList<String> orderDateList = new ArrayList<String>();
				ArrayList<String> orderTimeList = new ArrayList<String>();
				ArrayList<String> orderTrackList = new ArrayList<String>();
				ArrayList<String> bookIdList = new ArrayList<String>();
				ArrayList<String> bookNameList = new ArrayList<String>();
				
				for(int x=0; rs.next();x++ ) 
				{
					orderIdList.add(x,String.valueOf(rs.getInt(1)));
					orderDateList.add(x,rs.getDate(2).toString());
					orderTrackList.add(x,rs.getString(3));
					orderTimeList.add(x,rs.getTime(4).toString());
				}

				//System.out.println("orderdetail breakpoint2");
				for(int x=0; x<orderIdList.size();x++)
				{
					rs = stmt.executeQuery("select bookid_fk from ordertable where orderid_fk ="+orderIdList.get(x)+";");
					rs.next();
					bookIdList.add(x,String.valueOf(rs.getInt(1)));
					
				}
				
				for(int x=0; x<bookIdList.size();x++)
				{
					rs = stmt.executeQuery("select bookname from booktable where bookid_pk ="+bookIdList.get(x)+";");
					rs.next();
					bookNameList.add(x,rs.getString(1));	
				}
				System.out.println("orderdetail breakpoint4");
				System.out.println(" sizes:"+orderIdList.size()+" "+orderDateList.size()+" "+orderTrackList.size()+" "+orderTimeList.size()+" "+bookNameList.size());
				
				for(int x=0; x<bookIdList.size();x++)
				{
					ArrayList<String> eachRecord = new ArrayList<String>();
					
					eachRecord.add(0,bookNameList.get(x));
					
					eachRecord.add(1,"50 USD");
					
					eachRecord.add(2,orderIdList.get(x));
					
					eachRecord.add(3,orderDateList.get(x));
					
					eachRecord.add(4,orderTimeList.get(x));
					
					eachRecord.add(5,orderTrackList.get(x));
					
					orderRecords.add(x,eachRecord);
					
				}
				connection.close();
				
				request.setAttribute("orderRecords", orderRecords);
				request.setAttribute("loggedInName",sessionName);
				request.getRequestDispatcher("/WEB-INF/views/buyerOrderDetails.jsp").forward(request, response);
				
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}	
	}
}
