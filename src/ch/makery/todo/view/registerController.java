package ch.makery.todo.view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ch.makery.todo.MainApp;
import ch.makery.todo.model.Users;
import ch.makery.todo.model.user;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class registerController {
	@FXML
    private TextField userNameField;

	@FXML
    private TextField emailField;

	@FXML
    private TextField phoneField;

	@FXML
    private PasswordField passwordField;

	@FXML
    private PasswordField confirmPasswordField;

	@FXML
    private AnchorPane rootPane;

	user tempUser = new user();
	MainApp mainApp = new MainApp();

	Users users = new Users();


	private static Users unMarshalingExample() throws JAXBException
	{
	  JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
	  Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

	  //We had written this file in marshalling example
	  Users users = (Users) jaxbUnmarshaller.unmarshal( new File("ressources\\users.xml") );



	  return users;

	}


	@FXML
	private void handleRegister() throws IOException, JAXBException {
		tempUser.setUserName(userNameField.getText());
        if (isInputValid()) {
        	//tempUser.setUserName(userNameField.getText());
        	//tempUser.setEmail(emailField.getText());
        	//tempUser.setPhone(phoneField.getText());
        	//tempUser.setPassword(passwordField.getText());

        	List<user> user_list=new ArrayList<user>();

        	users.setUsers(user_list);

        	users = unMarshalingExample();

        	System.out.println(users.getUsers().isEmpty());

        	user user2 = new user();

        	user2.setUserName(userNameField.getText());
        	user2.setEmail(emailField.getText());
        	user2.setPhone(phoneField.getText());
        	user2.setPassword(passwordField.getText());

        	users.getUsers().add(user2);

        	JAXBContext jaxbContext_2 = JAXBContext.newInstance(Users.class);
        	  Marshaller jaxbMarshaller = jaxbContext_2.createMarshaller();

        	  jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);



        	  //Marshal the employees list in file
        	  jaxbMarshaller.marshal(users, new File("ressources\\users.xml"));

        	FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/login.fxml"));
            AnchorPane login = (AnchorPane) loader.load();

            rootPane.getChildren().setAll(login);
        }
    }


	@FXML
    private void toLogin() throws IOException {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/login.fxml"));
        AnchorPane login = (AnchorPane) loader.load();

        rootPane.getChildren().setAll(login);
    }


	private boolean isInputValid() {


        if (userNameField.getText() == null || userNameField.getText().length() == 0) {
        	Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("Invalid Username");
        	alert.setHeaderText(null);
        	alert.setContentText("Please Enter a Username");
        	alert.showAndWait();
        	return false;
        }
        if(!validateEmail()){
        	Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("Invalid Email");
        	alert.setHeaderText(null);
        	alert.setContentText("Please Enter a valid Email");
        	alert.showAndWait();
        	return false;
        }
        if (phoneField.getText() == null || phoneField.getText().length() == 0) {
        	Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("Invalid Phone Number");
        	alert.setHeaderText(null);
        	alert.setContentText("Please Enter a valid Phone Number");
        	alert.showAndWait();
        	return false;
        }
        if (passwordField.getText() == null || passwordField.getText().length() == 0) {
        	Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("Invalid Password");
        	alert.setHeaderText(null);
        	alert.setContentText("Please Enter a valid Password");
        	alert.showAndWait();
        	return false;
        }
        if(!passwordField.getText().equals(confirmPasswordField.getText())){
        	Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("Confirm password");
        	alert.setHeaderText(null);
        	alert.setContentText("Please Confirm password");
        	alert.showAndWait();
        	return false;
        }
        else{
        	return true;
        }
   }

	public boolean validateEmail(){
		Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
		Matcher m = p.matcher(emailField.getText());

		if(m.find() && m.group().equals(emailField.getText())){
			return true;
		}else{
			return false;
		}
	}
}
