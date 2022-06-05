package ch.makery.todo;

import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class MainApp extends Application {

    private Stage primaryStage;



    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("To Do");

        showlogin();


    }

    /**
     * Initializes the root layout.
     */
    public void showlogin() {
        try {
        	// Load login view.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/login.fxml"));
            AnchorPane login = (AnchorPane) loader.load();

            // Show the login scene.
            Scene scene = new Scene(login);
            primaryStage.setScene(scene);
            primaryStage.show();

            primaryStage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

    public static void main(String[] args) {
        launch(args);
    }
}