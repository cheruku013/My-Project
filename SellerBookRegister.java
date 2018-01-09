package com.bookstore;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/sellerBookRegister")

public class SellerBookRegister extends HttpServlet{
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		HttpSession session = request.getSession();
		String sessionName = (String) session.getAttribute("name");
		request.setAttribute("loggedInName",sessionName);
		
		request.getRequestDispatcher("/WEB-INF/views/bookRegister.jsp").forward(request, response);
	}
}
