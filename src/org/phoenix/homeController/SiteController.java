package org.phoenix.homeController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/site")
public class SiteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		//Convert the page to lower case
		page =page.toLowerCase();
		//
		switch (page) {
		case "home":
			homePage(request, response);
			break;
			
		default:
			errorPage(request, response);
			break;
		}//
	}
	//
	//
	//Home page
		public void homePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//
			request.setAttribute("title", "Home page");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		//
		//Error page
		public void errorPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setAttribute("title", "Error page");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}

}
