package com.bookstore;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(urlPatterns = "/buyerPayments")
public class BuyerPayments extends HttpServlet{

	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		HttpSession session = request.getSession();
		String sessionName = (String) session.getAttribute("name");
		String sessionId = (String) session.getAttribute("id");
		if(sessionName!=null && sessionId!= null)
		{
			String bookId = request.getParameter("bookId");
			System.out.println("bookid:"+bookId);
			request.setAttribute("bkId",bookId);
			request.setAttribute(sessionName, "name");
			request.getRequestDispatcher("/WEB-INF/views/buyerPayments.jsp").forward(request, response);
		}
	}
}
