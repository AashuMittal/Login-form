package com.springrest.rest.sign_up;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springrest.rest.sign.Signup;
@Service
public class signupImple implements Sign_up_Detail {

	 private static final String JDBC_URL = "jdbc:mysql://localhost:3306/user";
	    private static final String USERNAME = "root";
	    private static final String PASSWORD = "Aashu";

	    @Override
	    public List<Signup> setdata() {
	        List<Signup> students = new ArrayList<>();

	        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
	            String query = "SELECT * FROM login";
	            try (PreparedStatement statement = connection.prepareStatement(query);
	                 ResultSet resultSet = statement.executeQuery()) {

	                while (resultSet.next()) {
	                   String name= resultSet.getString("name");
	                    String password = resultSet.getString("password");
	                    String email = resultSet.getString("email");

	                    Signup student = new Signup( name,password, email);
	                    students.add(student);
	                }
	            }
	        } catch (SQLException e) {
	        	
	            e.printStackTrace();
	        }

	        return students;
	    }

	    @Override
	    public Signup adddetail(Signup user) {
	        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
	            String query = "INSERT INTO login (name, password, email) VALUES (?, ?, ?)";
	            try (PreparedStatement statement = connection.prepareStatement(query)) {
	            	statement.setString(1, user.getName());
	                statement.setString(2, user.getPassword());
	                statement.setString(3, user.getEmail());

	                statement.executeUpdate();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return user;
	    }

}
