package ch.makery.todo.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ch.makery.todo.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import ch.makery.todo.view.flashcardsCUController;

import ch.makery.todo.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class flashcardsController implements Initializable{

	@FXML
	private flashcardsCUController childController;

	public ObservableList<Flashcards> Data = FXCollections.observableArrayList(
			new Flashcards("flashcard 1","front 1","back 1"),
			new Flashcards("flashcard 2","front 2","back 2"),
			new Flashcards("flashcard 3","front 3","back 3"),
			new Flashcards("flashcard 4","front 4","back 4"),
			new Flashcards("flashcard 5","front 5","back 5"));

    private int index = 0;

    public int getIndex(){
    	return index;
    }

    public void setIndex(int x){
    	this.index = x;
    }

    private int createOrUpdate = 0;

    public int getCreateOrUpdate(){
    	return createOrUpdate;
    }

    private Flashcards Card = Data.get(index);

    private String face = "front";

    @FXML
    private AnchorPane flashscene;

	@FXML
    private Button create;

    @FXML
    private Button delete;

    @FXML
    private Button f_card;

    @FXML
    private Text f_title;

    @FXML
    private Button flash_cards_btn;

    @FXML
    private Button left;

    @FXML
    private Button logout_btn;

    @FXML
    private Button pomodoro_btn;

    @FXML
    private Button right;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button save_btn;

    @FXML
    private Button todo_btn;

    @FXML
    private Button update;

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	showCard(index,face);
    	//childController.setParentController(this);
	}

    @FXML
    void createFlashcards(ActionEvent event) {
    	createOrUpdate = 0;
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("flashcardsCU.fxml"));
	    	Parent root = (Parent) loader.load();
	    	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
	    	Scene scene = new Scene(root);
	    	stage.setScene(scene);
	    	stage.setTitle("New Flashcard...");
	    	stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot Load The Window...");
		}
    }

    @FXML
    void updateFlashcards(ActionEvent event) {
    	if (Data.isEmpty() == false){
    		createOrUpdate = 1;
	    	try {
	    		FXMLLoader loader = new FXMLLoader(getClass().getResource("flashcardsCU.fxml"));
		    	Parent root = (Parent) loader.load();
		    	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		    	Scene scene = new Scene(root);
		    	stage.setScene(scene);
		    	stage.setTitle("New Flashcard...");
		    	stage.show();

	            flashcardsCUController flash_cu = loader.getController();
	            flash_cu.getCard(Data.get(index));
	        } catch (IOException e) {
	        	// TODO Auto-generated catch block
				System.out.println("Cannot Load The Window...");
	        }
    	}
    }

    public void setCard(Flashcards card){
    	Data.get(index).setFlashcardTitle(card.getFlashcardTitle());
    	Data.get(index).setFlashcardFront(card.getFlashcardFront());
    	Data.get(index).setFlashcardBack(card.getFlashcardBack());
    }

    public void getCardFromSubmit(Flashcards card){
    	if (createOrUpdate == 1){
    		setCard(card);
		}else if (createOrUpdate == 0){
	    	Data.add(card);
	    	setIndex(Data.size()-1);
		}
    }

	@FXML
    void deleteFlashcards(ActionEvent event) {
		if (Data.isEmpty() == false){
			Data.remove(index);
			index-- ;
			showCard(index,face);
		}

    }

    @FXML
    void flipCard(ActionEvent event) {
    	if (face == "front"){
    		face = "back";
    	}else if (face == "back"){
    		face = "front";
    	}
    	showCard(index,face);
    }

    public void showCard(int index,String face){
    	this.index = index;
    	this.face = face;
    	f_title.setText(Data.get(index).getFlashcardTitle());
    	if (face == "front"){
    		f_card.setText(Data.get(index).getFlashcardFront());
    	}else if (face == "back"){
    		f_card.setText(Data.get(index).getFlashcardBack());
    	}
    }

    @FXML
    void followingCard(ActionEvent event) {
    	if (index != Data.size()-1){
    		index++ ;
	    	face = "front";
	    	showCard(index,face);
    	}
    }

    @FXML
    void handleSaveFlashcards(ActionEvent event) {

    }

    @FXML
    void precedingCard(ActionEvent event) {
    	if (index != 0){
	    	index-- ;
	    	face = "front";
	    	showCard(index,face);
    	}
    }

    /////////////////////////////////////////////////////////////

    @FXML
    void pomodoroAction(ActionEvent event){
    	todo_btn.setStyle("-fx-background-color: none;-fx-font-size: 20px;");
    	pomodoro_btn.setStyle("-fx-background-color: #70ca47;-fx-font-size:20px;");
    	flash_cards_btn.setStyle("-fx-background-color: none;-fx-font-size:20px;");

    	try {
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/one.fxml"));
            AnchorPane PomPanel = (AnchorPane) loader.load();
            rootPane.getChildren().setAll(PomPanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ToDoAction(ActionEvent event){
    	todo_btn.setStyle("-fx-background-color: #70ca47;-fx-font-size: 20px;");
    	pomodoro_btn.setStyle("-fx-background-color: none;-fx-font-size: 20px;");
    	flash_cards_btn.setStyle("-fx-background-color: none;-fx-font-size:20px;");

    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/BaseScene.fxml"));
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

    ///////////////////////////////////////////////////////

    public void setData(ObservableList<Flashcards> D){
    	Data.addAll(0,D);
    }

    public ObservableList<Flashcards> getData(){
    	return Data;
    }

}
