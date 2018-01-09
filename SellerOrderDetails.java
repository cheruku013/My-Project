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

@WebServlet(urlPatterns = "/sellerOrderDetails")

public class SellerOrderDetails extends HttpServlet{

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
				
				System.out.println("Opened database successfully");

				stmt =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				
				ResultSet rs = stmt.executeQuery("select bookid_pk from booktable where userid_fk ='"+sessionId+"';");
				
				ArrayList<Integer> bookIdList = new ArrayList<Integer>();
				
				ArrayList<Integer> bookIdOrd = new ArrayList<Integer>();
				ArrayList<Integer> orderId = new ArrayList<Integer>();
				ArrayList<String> userId = new ArrayList<String>();
				
				
				for(int x=0; rs.next();x++ ) 
				{
					bookIdList.add(x, rs.getInt(1));						
				}
				
				for(int x=0;x<bookIdList.size();x++ ) 
				{
					rs = stmt.executeQuery("select orderid_fk from ordertable where bookid_fk="+bookIdList.get(x)+";");
					
					rs.next();
					/*********************** For getting orderid and book ids which were ordered *****************************/ 
					//System.out.println("size of fetch: "+rs.getRow()+" "+bookIdList.get(x));
					if(rs.getRow()>0)
					{						
						bookIdOrd.add(bookIdList.get(x));
						orderId.add(rs.getInt(1));						
					}
					
				}
								
				for(int x=0;x<orderId.size();x++)
				{
					rs = stmt.executeQuery("select userid_fk from orderdetailtable where orderid_pk="+orderId.get(x)+";");
					
					rs.next();
					
					userId.add(rs.getString(1));
					
				}
					
				for(int x=0;x<orderId.size();x++)
				{
					rs = stmt.executeQuery("select bookid_pk, bookname from booktable where bookid_pk="+bookIdOrd.get(x)+";");
					ArrayList<String> eachRecord = new ArrayList<String>();
					rs.next();
					
					eachRecord.add(String.valueOf(rs.getInt(1)));
					eachRecord.add(rs.getString(2));
					eachRecord.add(orderId.get(x).toString());
					
					rs = stmt.executeQuery("select userid_pk, username, useraddr, usremailid, phone  from usertable where userid_pk='"+userId.get(x)+"'");
					rs.next();
					
					eachRecord.add(rs.getString(1));
					eachRecord.add(rs.getString(2));
					eachRecord.add(rs.getString(3));
					eachRecord.add(rs.getString(4));
					eachRecord.add(rs.getString(5));
					
					System.out.println(eachRecord.get(0)+" "+eachRecord.get(1)+" "+eachRecord.get(2)+" "+eachRecord.get(3)+" "+eachRecord.get(4)+" "+eachRecord.get(5)+" "+eachRecord.get(6)+" "+eachRecord.get(7));
					orderRecords.add(eachRecord);
				
				}
				
				connection.close();
				
				request.setAttribute("orderRecords", orderRecords);
				request.setAttribute("loggedInName",sessionName);
				request.getRequestDispatcher("/WEB-INF/views/bookOrders.jsp").forward(request, response);
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
		}
		
	}
	
}