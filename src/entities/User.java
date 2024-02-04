package com.springrest.rest.entities;

public class User{
	
	String password;
	@Override
	public String toString() {
		return "User [ password=" + password + ", Email=" + Email + "]";
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	String Email;
	public User(String password,String email) {
		super();
		this.password = password;
		this.Email = email;
	}
	public String getEmail() {
		// TODO Auto-generated method stub
		return Email;
	}
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
}