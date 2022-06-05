package ch.makery.todo.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ch.makery.todo.model.Tasks;
import javafx.collections.ObservableList;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.text.ParseException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class TaskSceneController implements Initializable{

	private String[] Category = {"Important!!!","In Progress...","Someday"};

	@FXML
    private TextField tf_taskName;

    @FXML
    private DatePicker dateTask;

    @FXML
    private TextField tf_taskDescription;

    @FXML
    private TextField tf_taskDetails;

    @FXML
    private TextField tf_hour;

    @FXML
    private TextField tf_minutes;

    @FXML
    private ChoiceBox<String> cb_category;

    @FXML
    private Button createTask;

    @FXML
    private Button cancelTask;

    @FXML
    void cancelBtnHandler(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("BaseScene.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.setScene(new Scene(root));
    	stage.setTitle("To Do");
    	stage.show();
    	/* afterwards we going to do a test to check if the user is certain that he wants to quit without saving his modifications */
    }


    @FXML
    void createBtnHandler(ActionEvent event) throws IOException {
    	Tasks NewTask = new Tasks(
    					tf_taskName.getText(),
    					dateTask.getValue().toString()+ " at " + tf_hour.getText() + " h " + tf_minutes.getText() + " min.",
    					tf_taskDescription.getText(),
    					tf_taskDetails.getText(),
    					cb_category.getValue()
    		);

//    	System.out.println(NewTask);

    	FXMLLoader loader = new FXMLLoader(getClass().getResource("BaseScene.fxml"));
    	Parent root = loader.load();
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.setScene(new Scene(root));
    	stage.setTitle("To Do");
    	BaseSceneController BSC = loader.getController();

//    	System.out.println(NewTask);

    	ObservableList<Tasks> Data = BSC.getData();
    	Data.add(NewTask);
    	BSC.setData(Data);
    	stage.show();

    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cb_category.getItems().addAll(Category);
	}



//    public Connection getConnection(){
//    	Connection conn;
//    	try {
//    		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tasks? useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&zeroDateTimeBehavior=convertToNull&zeroDateTimeBehavior=convertToNull&zeroDateTimeBehavior=convertToNull&zeroDateTimeBehavior=convertToNull&zeroDateTimeBehavior=convertToNull&zeroDateTimeBehavior=convertToNull", "root", "");
//    		return conn;
//		} catch (Exception e) {
//			System.out.println("Error : " + e.getMessage());
//			return null;
//		}
//    }
//
//    public void executeQuery(String query, Connection conn){
//		conn = getConnection();
//		Statement st;
//		try {
//			st = conn.createStatement();
//			st.execute(query);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//    @FXML
//    void cancelBtnHandler(ActionEvent event) {
//    	Stage stage = (Stage) cancelTask.getScene().getWindow();
//    	stage.close();
//    }
//
//    @FXML
//    void createBtnHandler(ActionEvent event) throws SQLException, ParseException {
//    	Connection conn = getConnection();
//    	//System.out.println(date);
//    	String query = "INSERT INTO tasks VALUES ('" + tf_taskName.getText() + " ', '" + dateTask.getValue() + "', '" + tf_taskDescription.getText() + "' , '" + tf_taskDetails.getText() + "')";
//    	executeQuery(query, conn);
//    	Stage stage = (Stage) createTask.getScene().getWindow();
//    	stage.close();
//    	BaseSceneController.showTasks();
//    }

}
