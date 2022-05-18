package org.phoenix.homeController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.catalina.mbeans.UserMBean;
import org.phoenix.entity.User;
import org.phoenix.model.UsersModels;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/operation")
public class OperationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/project")
	private DataSource dataSource; //This dataSource will be sent to the model
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String page = request.getParameter("page");
		//Convert the page to lower case
		page =page.toLowerCase();
		//
		switch (page) {
		case "listusers": //
			listUsers(request, response);
			break;
		case "adduser":
			addUserFormLoader(request, response);
			break;
		case "updateuser":
			updateUserFormLoader(request, response);
			break;
		case "deleteuser":
			deleteUser(Integer.parseInt(request.getParameter("usersId")));
			listUsers(request, response);
			break;
		default:
			errorPage(request, response);
		}
	}
	private void deleteUser(int usersId) {
		// TODO Auto-generated method stub
		//call the method from the model
		new UsersModels().deleteUser(dataSource ,usersId); 
		return;
	}
	//
	//
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String operation = request.getParameter("form");
		operation = operation.toLowerCase();
		switch (operation) {
		case "adduseroperation":
			User newUser = new User(request.getParameter("username"), request.getParameter("email"));
			addUserOpperation(newUser);
			listUsers(request, response);
			break;
		case "updateuseroperation":
			User updatedUser = new User(Integer.parseInt(request.getParameter("usersId")), request.getParameter("username"), request.getParameter("email"));
			UpdateUserOperation(dataSource, updatedUser);
			listUsers(request, response);
			break;

		default:
			errorPage(request, response);
			break;
		}
	}
	
	private void UpdateUserOperation(DataSource dataSource, User updatedUser) {
		// TODO Auto-generated method stub
		new UsersModels().updateUser(dataSource, updatedUser);
		return;
		
	}
	private void addUserOpperation(User newUser) {
		new UsersModels().addUser(dataSource, newUser);
		return;
		
	}
	//
	//Method to list users.
	public  void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		List<User>listUsers = new ArrayList<>();
		//Receive the info from the DB
		listUsers = new UsersModels().listUsers(dataSource);
		//Send the information to "listUser.jsp"...
		request.setAttribute("listUsers", listUsers);
		request.setAttribute("title", "List of Users");
		request.getRequestDispatcher("listUser.jsp").forward(request, response);
	}
	//
	//Method to add Users.
	public void addUserFormLoader(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		request.setAttribute("title", "Add User");
		request.getRequestDispatcher("adduser.jsp").forward(request, response);
	}//
	//
	//Error page
	public void errorPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "Error page");
		request.getRequestDispatcher("error.jsp").forward(request, response);
	}
	//
	//Update Method
	private void updateUserFormLoader(HttpServletRequest request, HttpServletResponse response) {
		// 
		request.setAttribute("title", "Update User");
		try {
			request.getRequestDispatcher("updateUser.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
