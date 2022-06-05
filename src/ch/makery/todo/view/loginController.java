package ch.makery.todo.view;



import ch.makery.todo.model.Users;
import ch.makery.todo.model.user;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import ch.makery.todo.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class loginController {
	@FXML
    private TextField userNameField;

	@FXML
    private PasswordField passwordField;

	@FXML
    private AnchorPane rootPane;


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
    private void handleLogin() throws JAXBException, IOException {
        if (isInputValid()) {
        	FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/BaseScene.fxml"));
            AnchorPane BaseScene = (AnchorPane) loader.load();

            rootPane.getChildren().setAll(BaseScene);
        }

    }

	@FXML
    private void toRegister() throws IOException {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/register.fxml"));
        AnchorPane register = (AnchorPane) loader.load();

        rootPane.getChildren().setAll(register);
    }

	private boolean isInputValid() throws JAXBException {

			List<user> user_list=new ArrayList<user>();

			users.setUsers(user_list);

			users = unMarshalingExample();

			for (user user : users.getUsers()) {
		        if (user.getUserName().equals(userNameField.getText()) && user.getPassword().equals(passwordField.getText())) {
		            return true;
		        }
		    }
			Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("Invalid Username or Password");
        	alert.setHeaderText(null);
        	alert.setContentText("Username or Password is Invalid");
        	alert.showAndWait();
        	return false;
    }
}
