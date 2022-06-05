package ch.makery.todo.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ch.makery.todo.MainApp;
import ch.makery.todo.model.*;
import ch.makery.todo.model.Tasks;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Node;

public class BaseSceneController implements Initializable{



	public BaseSceneController() {
	}

	private ObservableList<Tasks> Data = FXCollections.observableArrayList(
			new Tasks("Task1", "18-05-1985", "Desc1", "Details1", "important")
	);

	@FXML
    private AnchorPane rootPane;

	@FXML
    private TableView<Tasks> tv_tasks;

    @FXML
    private TableColumn<Tasks, String> col_taskName;

    @FXML
    private TableColumn<Tasks, String> col_date;

    @FXML
    private TableColumn<Tasks, String> col_taskDescription;

    @FXML
    private TableColumn<Tasks, String> col_Details;

    @FXML
    private TableColumn<Tasks, String> col_category;

    @FXML
    private Button stratNewTask;

    @FXML
    private Button pomodoro_btn;

    @FXML
    private Button todo_btn;

    @FXML
    private Button saveTasks;

    @FXML
    private Button flash_cards_btn;

    @FXML
    private Button openTasksFile;

    @FXML
    private Button logout_btn;

    public Stage getPrimaryStage() {
		Stage primaryStage = (Stage) tv_tasks.getScene().getWindow();
		return primaryStage;
	}


    @FXML
    void handleSaveTasks(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showSaveDialog(getPrimaryStage());

		if (file != null) {
			// Make sure it has the correct extension
			if (!file.getPath().endsWith(".xml")) {
				file = new File(file.getPath() + ".xml");
			}
			saveTasksToFile(file);
		}
    }

    @FXML
    void handleOpenTasksFile(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(getPrimaryStage());

        if (file != null) {
            loadTasksFromFile(file);
        }
    }

    @FXML
    void handleStartNewTask(ActionEvent event){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TaskScene.fxml"));
	    	Parent root = (Parent) loader.load();
	    	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
	    	Scene scene = new Scene(root);
	    	stage.setScene(scene);
	    	stage.setTitle("New Task...");
	    	stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot Load The Window...");
		}

    }

    @FXML
    void pomodoroAction(ActionEvent event){
    	todo_btn.setStyle("-fx-background-color: none;-fx-font-size: 20px;");
    	pomodoro_btn.setStyle("-fx-background-color: #70ca47;-fx-font-size:20px;");
    	flash_cards_btn.setStyle("-fx-background-color: none;-fx-font-size:20px;");

    	try {
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/one.fxml"));
            AnchorPane one = (AnchorPane) loader.load();
            rootPane.getChildren().setAll(one);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void flashCardsAction(ActionEvent event){
    	todo_btn.setStyle("-fx-background-color: none;-fx-font-size: 20px;");
    	pomodoro_btn.setStyle("-fx-background-color: none;-fx-font-size: 20px;");
    	flash_cards_btn.setStyle("-fx-background-color: #70ca47;-fx-font-size:20px;");

    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/flashcards.fxml"));
            AnchorPane flashscene = (AnchorPane) loader.load();
            rootPane.getChildren().setAll(flashscene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logoutAction(ActionEvent event) throws IOException{
    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/login.fxml"));
        AnchorPane login = (AnchorPane) loader.load();

        rootPane.getChildren().setAll(login);

    }




    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {

    		col_taskName.setCellValueFactory(new PropertyValueFactory<>("TaskName"));
	    	col_taskDescription.setCellValueFactory(new PropertyValueFactory<>("TaskDescription"));
	    	col_Details.setCellValueFactory(new PropertyValueFactory<>("TaskDetails"));
	    	col_date.setCellValueFactory(new PropertyValueFactory<>("TaskDate"));
	    	col_category.setCellValueFactory(new PropertyValueFactory<>("TaskCategory"));

	    	tv_tasks.setItems(Data);

	}

//    public void setData(ObservableList<Tasks> Data){
//    	this.Data = Data;
//    }

    public void pushDataToTableView(){

    	ObservableList<Tasks> Data = getData();

		col_taskName.setCellValueFactory(new PropertyValueFactory<>("TaskName"));
    	col_taskDescription.setCellValueFactory(new PropertyValueFactory<>("TaskDescription"));
    	col_Details.setCellValueFactory(new PropertyValueFactory<>("TaskDetails"));
    	col_date.setCellValueFactory(new PropertyValueFactory<>("TaskDate"));
    	col_category.setCellValueFactory(new PropertyValueFactory<>("TaskCategory"));

    	tv_tasks.setItems(Data);

    }


    public void setData(ObservableList<Tasks> D){
    	Data = D;
    	pushDataToTableView();
    }

    public ObservableList<Tasks> getData(){
    	return Data;
    }

    public File getTasksFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(BaseSceneController.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    public void setTasksFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(BaseSceneController.class);
        Stage primaryStage = (Stage) tv_tasks.getScene().getWindow();
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("Base Scene : " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("Base Scene...");
        }
    }

    //---------------------------------------------------------------------------------------------


    public void saveTasksToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(TasksModelForJAXB.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            TasksModelForJAXB model = new TasksModelForJAXB();
            model.setTasks(Data);

            // Marshalling and saving XML to the file.
            m.marshal(model, file);

            // Save the file path to the registry.
            setTasksFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    public void loadTasksFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(TasksModelForJAXB.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            TasksModelForJAXB model = (TasksModelForJAXB) um.unmarshal(file);

            Data.clear();
            List<Tasks> list = model.getTasks();
            System.out.println(list);
            Data.addAll(list);

            // Save the file path to the registry.
            setTasksFilePath(file);

        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }
    }


//    @FXML
//    void handleButtonAction(ActionEvent event) throws IOException {
//    	if (event.getSource() == btnNewTask) {
//    		addTask();
//		}
//    	if (event.getSource() == btnRemoveTask) {
//    		removeTask();
//    	}
//    }
//
//	private ObservableList<Tasks> getTaskList() {
//
//    	ObservableList<Tasks> taskList = FXCollections.observableArrayList();
//
//		Connection conn = getConnection();
//		String query = "SELECT * FROM tasks";
//		Statement st;
//		ResultSet rs;
//
//		try {
//			st = conn.createStatement();
//			rs = st.executeQuery(query);
//
//			Tasks tasks;
//			while (rs.next()) {
//				tasks = new Tasks(rs.getString("name"), rs.getDate("date"), rs.getString("description"), rs.getString("details"));
//				taskList.add(tasks);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return taskList;
//	}
//
//	private void showTasks() {
//    	ObservableList<Tasks> tasksList = getTaskList();
//
//    	col_taskName.setCellValueFactory(new PropertyValueFactory<Tasks,String>("taskName"));
//    	col_date.setCellValueFactory(new PropertyValueFactory<Tasks,Date>("taskDate"));
//    	col_taskDescription.setCellValueFactory(new PropertyValueFactory<Tasks,String>("taskDescription"));
//    	col_Details.setCellValueFactory(new PropertyValueFactory<Tasks,String>("taskDetails"));
//
//    	tv_tasks.setItems(tasksList);
//
////    	ObservableList<Tasks> list = tv_tasks.getItems();
////    	for (Tasks tasks : list) {
////			System.out.println(tasks.getTaskDate());
////		}
//
//	}
//
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	private void removeTask() {
//    	TablePosition pos = tv_tasks.getSelectionModel().getSelectedCells().get(0);
//    	int row = pos.getRow();
//    	Tasks task = tv_tasks.getItems().get(row);
//    	TableColumn col_taskName = pos.getTableColumn();
//    	String data = (String) col_taskName.getCellObservableValue(task).getValue();
//
//    	if (data != null && pos != null) {
//    		String query = "DELETE FROM tasks WHERE Name = '" + data + "'";
//        	executeQuery(query);
//        	showTasks();
//		}else{
//			showTasks();
//		}
//	}
//
//	private void addTask() throws IOException {
//		FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(Main.class.getResource("TaskScene.fxml"));
//		Scene scene = new Scene(loader.load());
//		Stage stage = new Stage();
//		stage.setScene(scene);
//		stage.setTitle("New Task...");
//		stage.show();
//
//		System.out.println("ggggg");
//		showTasks();
//	}
//
//	public Connection getConnection(){
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
//	public void executeQuery(String query){
//		Connection conn = getConnection();
//		Statement st;
//		try {
//			st = conn.createStatement();
//			st.execute(query);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}


}
