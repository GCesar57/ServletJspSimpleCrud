package org.phoenix.model;
 
//import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.phoenix.entity.User;

public class UsersModels {
	//
	public List<User> listUsers(DataSource dataSource) {
		List<User>listUsers = new ArrayList<>();
		//Initialize connection objects
		//PrintWriter out = response.getWriter();
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
				//out.print("<br/>"+rs.getString("email"));
				listUsers.add(new User(rs.getInt("userId"), rs.getString("username"), rs.getString("email")));
			}//
		} catch (SQLException e) {
			e.printStackTrace();
			e.getMessage();
		}
		return listUsers;
	}//

	public void addUser(DataSource dataSource, User newUser) {
		// TODO Auto-generated method stub
		Connection connect = null;
		PreparedStatement statement = null;
		try {
			connect = dataSource.getConnection();
			String username = newUser.getUsername();
			String email = newUser.getEmail();
			String query = "INSERT INTO users(username, email)VALUES(?,?)";
			statement = connect.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, email);
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getMessage();
		}finally {
			try { 
				connect.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				e.getMessage();
			}
		}
		
	}

	public void updateUser(DataSource dataSource, User updatedUser) {
		// TODO Auto-generated method stub
		Connection connect = null;
		PreparedStatement statement = null;
		try {
			connect = dataSource.getConnection();
			int usersId = updatedUser.getUserId();
			String username = updatedUser.getUsername();
			String email = updatedUser.getEmail();
			String query = "Update users SET username=?, email=? WHERE userId=?";
			statement = connect.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, email);
			statement.setInt(3, usersId);
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getMessage();
		}
		
	}

	public void deleteUser(DataSource dataSource, int usersId) {
		// TODO Auto-generated method stub
		Connection connect = null;
		PreparedStatement statement = null;
		try {
			connect = dataSource.getConnection();
			
			String query = "Delete FROM users WHERE userId=?";
			statement = connect.prepareStatement(query);
			statement.setInt(1, usersId);
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getMessage();
		}
		
	}
}
