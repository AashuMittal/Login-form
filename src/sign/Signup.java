package com.springrest.rest.sign;

public class Signup {
		String name;
		String password;
		String email;
		public Signup(String name, String password, String email) {
			super();
			this.name = name;
			this.password = password;
			this.email = email;
		}
		@Override
		public String toString() {
			return "Signup [name=" + name + ", password=" + password + ", email=" + email + "]";
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
}
