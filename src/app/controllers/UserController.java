package app.controllers;

import app.helpers.Database;
import app.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {

	public void createUser(String username, String email, String name, String password) {

		System.out.println("Inside this : " + username);
		try {
			String query = " insert into tbl_user (username, email, name, password, admin)" + " values (?, ?, ?, ?, ?)";

			Database database = new Database();

			Connection connection = database.getConnection();

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = null;
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, username);
			preparedStmt.setString(2, email);
			preparedStmt.setString(3, name);
			preparedStmt.setString(4, password);
			preparedStmt.setInt(5, 0);
			preparedStmt.execute();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public User getUserData(String username, String password) {
		try {
			// create the mysql database connection
			
			String query = "SELECT * FROM TBL_USER WHERE USERNAME = ? and PASSWORD=?";

			Database database = new Database();

			Connection connection = database.getConnection();

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = null;
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, username);
			preparedStmt.setString(2, password);
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				User user = new User();

				user.setName(rs.getString("name")); 
				user.setUsername(rs.getString("username")); 
				user.setEmail(rs.getString("email")); 
				return user;

			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean getUser(String username, String password) {
		try {
			// create the mysql database connection
			String query = "SELECT * FROM TBL_USER WHERE USERNAME = ? and PASSWORD=?";

			Database database = new Database();

			Connection connection = database.getConnection();

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = null;
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, username);
			preparedStmt.setString(2, password);
			ResultSet rs = preparedStmt.executeQuery();
			if (rs.next()) {
				return true;
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/*public static void main(String[] args) {
		UserController userController = new UserController();
		// userController.createUser();
		//userController.getUser();
	}*/

}
