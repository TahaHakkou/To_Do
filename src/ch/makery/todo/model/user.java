package ch.makery.todo.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class user {
	private  String username;
	private  String email;
	private  String phone;
	private  String password;




	public user(){
		this(null,null,null,null);
	}

	public user(String username,String email,String phone,String password) {
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public String getUserName() {
		return username;
	}

	public  void setUserName(String username) {
		this.username = username;
	}




	public String getEmail() {
		return email;
	}

	public  void setEmail(String email) {
		this.email = email;
	}



	public String getPhone() {
		return phone;
	}

	public  void setPhone(String phone) {
		this.phone = phone;
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
