package com.cesar;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class demo
 */
@WebServlet("/demo")

public class demo extends HttpServlet {
	private static final long serialVersionUID = 1L; 
	@Resource(name="jdbc/project") 
	private DataSource dataSource; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public demo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Initialize connection objects
		PrintWriter out = response.getWriter();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			//Create SQL statements
			String query = "SELECT * FROM users";
			st = conn.createStatement();
			//Execute query
			rs = st.executeQuery(query);
			//Process results
			while(rs.next()) {
				out.print("<br/>"+rs.getString("email"));
			}//
		} catch (SQLException e) {
			e.printStackTrace();
			e.getMessage();
		}
	}

}
