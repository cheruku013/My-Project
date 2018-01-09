package com.bookstore;


import java.io.*;
import java.io.ObjectInputStream.GetField;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.nio.file.Path; 
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
@WebServlet(urlPatterns = "/book")
@MultipartConfig
public class BookServlet extends HttpServlet{

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


	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		HttpSession session = request.getSession();
		String sessionUserID = (String) session.getAttribute("id");

		String bkName = request.getParameter("name");
		String bkDesc = request.getParameter("desc");
		String bkAuthor = request.getParameter("author");
		String bkStat = request.getParameter("stat");
		String bkGenere = request.getParameter("genere");
		int copies = Integer.parseInt(request.getParameter("copies"));
		Part filePart = request.getPart("cover"); 
		String fileName = getFileName(filePart);

		OutputStream out = null;
		InputStream filecontent = null;

		String bookCvrPath = null;

		/******************** Copying image file from the request to a img\BookCovers\ folder project location ****************************/

		try {

			out = new FileOutputStream(new File(getServletContext().getRealPath("/")+"img/BookCovers/"+ fileName));
			filecontent = filePart.getInputStream();

			int read = 0;
			final byte[] bytes = new byte[1024];

			while ((read = filecontent.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			bookCvrPath = "../../img/BookCovers/"+ fileName;
		}catch(FileNotFoundException fne)
		{
			fne.printStackTrace();
		}
		out.close();
		/**********************************************************************************************************************************/

		/******************************************** inserting the retrived book details into database **********************************/

		//		System.out.println("name:"+bkName+" desc:"+bkDesc+" author:"+bkAuthor+" stat:"+bkStat+" Genere:"+bkGenere+" cover:"+fileName);
		//		System.out.println(bookCvrPath);

		Connection connection = null;
		java.sql.Statement stmt = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookecomdb","root", "root");
			System.out.println("Opened database successfully");

			stmt =connection.createStatement();

			/* to insert into user table */
			for(int x=0; x<copies;x++)
			{

				String insertQuery = "insert into booktable(userid_fk,bookname,bookdesc,bookauthor,booknewstatus,bookcategory,bookimg)"
						+ " values(?,?,?,?,?,?,?)";
				PreparedStatement prepStmt = connection.prepareStatement(insertQuery);

				prepStmt.setString(1, sessionUserID);
				prepStmt.setString(2, bkName);
				prepStmt.setString(3, bkDesc);
				prepStmt.setString(4, bkAuthor);
				prepStmt.setString(5, bkStat);
				prepStmt.setString(6, bkGenere);
				prepStmt.setString(7, bookCvrPath);
				prepStmt.executeUpdate();
			}

			stmt.close();
			connection.close();

			System.out.println("closed data base connection");	

			request.setAttribute("regStat",bkName+" registration success.");
			request.getRequestDispatcher("/WEB-INF/views/bookRegister.jsp").forward(request, response);

		} catch (Exception e)
		{
			e.printStackTrace();			
		}



	}

	private String getFileName(final Part part) {
		final String partHeader = part.getHeader("content-disposition");

		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(
						content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

}
