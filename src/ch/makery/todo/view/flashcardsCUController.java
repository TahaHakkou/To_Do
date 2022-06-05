package ch.makery.todo.view;

import java.io.IOException;

import ch.makery.todo.MainApp;
import ch.makery.todo.model.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class flashcardsCUController {

	@FXML
    private AnchorPane rootPane;

    @FXML
    private TextField title;

    @FXML
    private TextField front;

    @FXML
    private TextArea back;

    @FXML
    private Button submit_btn;

    @FXML
    private Button cancel_btn;

    @FXML
    void cancelFlashcards(ActionEvent event) throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("flashcards.fxml"));
    	Parent root = loader.load();
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.setScene(new Scene(root));
    	stage.setTitle("To Do");
    	flashcardsController fc = loader.getController();
    	fc.showCard(fc.getIndex(),"front");
    }

    @FXML
    public void handleSubmitFlashcards(ActionEvent event) throws IOException {
    	try {
	        Flashcards card = new Flashcards();
	    	card.setFlashcardTitle(title.getText());
	    	card.setFlashcardFront(front.getText());
	    	card.setFlashcardBack(back.getText());

	    	//System.out.print(fc.createOrUpdate);

	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("flashcards.fxml"));
	    	Parent root = loader.load();
	    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	stage.setScene(new Scene(root));
	    	stage.setTitle("To Do");
	    	flashcardsController fc = loader.getController();
	    	fc.getCardFromSubmit(card);
	        fc.showCard(fc.getIndex(),"front");
    	} catch (IOException e) {
            e.printStackTrace();
        }
    }

    ///////////////////////////////////////////////////

    public void getCard(Flashcards card) {
        this.title.setText(card.getFlashcardTitle());
        this.front.setText(card.getFlashcardFront());
        this.back.setText(card.getFlashcardBack());
    }

}
