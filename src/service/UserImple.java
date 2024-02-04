package com.springrest.rest.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springrest.rest.entities.User;

@Service
public class UserImple implements User_Detail {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/user";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Aashu";

    @Override
    public List<User> getdata(User user) {
        List<User> users = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM login WHERE email = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, user.getEmail());
                statement.setString(2, user.getPassword());

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String password = resultSet.getString("password");
                        String email = resultSet.getString("email");

                        User retrievedUser = new User(password, email);
                        users.add(retrievedUser);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error in getdata method: " + e.getMessage());
            e.printStackTrace();
        }

  

        return users;
    }
}
