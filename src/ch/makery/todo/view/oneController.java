package ch.makery.todo.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ch.makery.todo.MainApp;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class oneController implements Initializable{
	Media sound = new Media(getClass().getResource("sound.wav").toExternalForm());
	MediaPlayer mediaPlayer = new MediaPlayer(sound);
	Media sound1 = new Media(getClass().getResource("click.wav").toExternalForm());
	MediaPlayer mediaPlayer1 = new MediaPlayer(sound1);
	Media sound2 = new Media(getClass().getResource("save1.wav").toExternalForm());
	MediaPlayer mediaPlayer2 = new MediaPlayer(sound2);



	Timeline timeline=new Timeline();
	boolean onpom=true,ispom=false,isbreak=false;
	String pomtext="25",breaktext="5";

	@FXML
    private AnchorPane rootPane;

	@FXML
    private Button BreakBtn;

    @FXML
    private Text MinuteText;

    @FXML
    private AnchorPane PomPanel;

    @FXML
    private Button PomodoroBtn;

    @FXML
    private Button SaveBtn;

    @FXML
    private Text SecondeText;

    @FXML
    private AnchorPane SettingPanel;

    @FXML
    private Button SettingsBtn;

    @FXML
    private Button StartBtn;

    @FXML
    private ComboBox<Integer> bmcb;

    @FXML
    private Button flash_cards_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private ComboBox<Integer> pmcb;

    @FXML
    private Button pomodoro_btn;

    @FXML
    private Button todo_btn;

    @FXML
    void Break(ActionEvent event) {
    	//ktbfih_s();
    	timeline.stop();
    	breakstyle();
    	onpom=false;
    	isbreak=false;
    	ispom=false;
    	MinuteText.setText(breaktext);
    	SecondeText.setText("0");
    	StartBtn.setText("Start");
    }

    @FXML
    void Pomodoro(ActionEvent event) {
    	//ktbfih_c();
    	pomstyle();
    	timeline.stop();

    	onpom=true;
    	ispom=false;
    	isbreak=false;
    	MinuteText.setText(pomtext);
    	StartBtn.setText("Start");
    	SecondeText.setText("0");
    }

    @FXML
    void Save(ActionEvent event) {
    	ktbfih_s();
    	setvalue();

    	ispom=false;
    	isbreak=false;

    	StartBtn.setText("Start");
    	SecondeText.setText("0");
    	scrollUp();
    }
    void ktbfih(){
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
            	mediaPlayer.seek(Duration.ZERO);
            	mediaPlayer.stop();
              }
          });
        }
    void ktbfih_c(){
        mediaPlayer1.play();
        mediaPlayer1.setOnEndOfMedia(new Runnable() {
            public void run() {
            	mediaPlayer1.seek(Duration.ZERO);
            	mediaPlayer1.stop();
              }
          });
        }
    void ktbfih_s(){
        mediaPlayer2.play();
        mediaPlayer2.setOnEndOfMedia(new Runnable() {
            public void run() {
            	mediaPlayer2.seek(Duration.ZERO);
            	mediaPlayer2.stop();
              }
          });
        }



    @FXML
    void Settings(ActionEvent event) {
    	ktbfih_s();
    	timeline.stop();
    	scrolldown();
    }
    void setvalue(){
    	if(pmcb.getValue()!=null){
    		pomtext=pmcb.getValue().toString();

    	}
    	if(bmcb.getValue()!=null){
    		breaktext=bmcb.getValue().toString();
    	}
    	if(pmcb.getValue()==null){pomtext="25";}
    	if(bmcb.getValue()==null){breaktext="5";}
    	if(onpom){MinuteText.setText(pomtext);}else{MinuteText.setText(breaktext);}

    }
    void scrolldown(){
    	TranslateTransition tr1=new TranslateTransition();
    	tr1.setDuration(Duration.millis(100));
    	tr1.setToX(0);
    	tr1.setToY(-600);
    	tr1.setNode(PomPanel);
    	TranslateTransition tr2=new TranslateTransition();
    	tr2.setDuration(Duration.millis(100));
    	tr2.setToX(0);
    	tr2.setToY(0);
    	tr2.setNode(SettingPanel);
    	ParallelTransition pt=new ParallelTransition(tr1,tr2);
    	pt.play();
        };
        void scrollUp(){
        	TranslateTransition tr1=new TranslateTransition();
        	tr1.setDuration(Duration.millis(100));
        	tr1.setToX(0);
        	tr1.setToY(-600);
        	tr1.setNode(SettingPanel);
        	TranslateTransition tr2=new TranslateTransition();
        	tr2.setDuration(Duration.millis(100));
        	tr2.setToX(0);
        	tr2.setToY(0);
        	tr2.setNode(PomPanel);
        	ParallelTransition pt=new ParallelTransition(tr1,tr2);
        	pt.play();
            };
    @FXML
    void Start(ActionEvent event) {
    	ktbfih_c();
    	if(ispom | isbreak){
    		timeline.stop();
    		StartBtn.setText("start");
    		ispom=false;
    		isbreak=false;
    		return;
    	}
    	if(!(ispom | isbreak)){
    		StartBtn.setText("stop");
    		timeline=new  Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

        	    @Override
        	    public void handle(ActionEvent event) {
        	    	if(Integer.parseInt(SecondeText.getText())==0 & Integer.parseInt(MinuteText.getText())==0){System.out.println("FIN");return;}
        	    	if(Integer.parseInt(SecondeText.getText())==0){
        	    		int x=Integer.parseInt(MinuteText.getText());
        	    				x--;
        	    		MinuteText.setText(String.valueOf(x));
        	    		SecondeText.setText("60");
        	    	}
        	    	SecondeText.setText(SecondeText.getText());// display next string
        	    	int a=Integer.parseInt(SecondeText.getText());
        	    	a--;

        	    	SecondeText.setText(String.valueOf(a));




        	    }
        	}));

    		timeline.setCycleCount(Integer.parseInt(MinuteText.getText())*60+Integer.parseInt(SecondeText.getText())-1);
    		timeline.setOnFinished((event2)->{
    			StartBtn.setText("start");
    			ktbfih();
    			if(ispom==true){
    			ispom=false;
    			Break(event2);
    			}if(isbreak==true){
    				isbreak=false;

        			Pomodoro(event2);
    			}
        	});

        	timeline.play();
        	if(onpom)ispom=true;else isbreak=true;



    		return;
    	}

    }
    void breakstyle(){PomPanel.setStyle(null);
    PomPanel.setStyle("-fx-background-color: #A5BECC");
	BreakBtn.setStyle(" -fx-border-width: 1px;-fx-border-color: #ffffff;-fx-border-style: dashed;-fx-border-radius: 2px;");
	PomodoroBtn.setStyle(null);
	PomodoroBtn.setStyle("-fx-background-color: none;-fx-border-width: 1px;-fx-border-color: transparent;");}
    void pomstyle(){
    	PomPanel.setStyle(null);
    	PomPanel.setStyle("-fx-background-color:  #fef9a7");
    	PomodoroBtn.setStyle(" -fx-border-width: 1px;-fx-border-color: #ffffff;-fx-border-style: dashed;-fx-border-radius: 2px;");
    	BreakBtn.setStyle(null);
    	BreakBtn.setStyle("-fx-background-color: none;-fx-border-width: 1px;-fx-border-color: transparent;");
    	}
	@SuppressWarnings("removal")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<Integer>bmcblist =FXCollections.observableArrayList();
		ObservableList<Integer>pmcblist =FXCollections.observableArrayList();

		for(int i=1;i<100;i++){
			bmcblist.add(new Integer(i));
			pmcblist.add(new Integer(i));
		}
		bmcb.setItems(bmcblist);

		pmcb.setItems(pmcblist);


	}

	/////////////////////////////////////////////////////////////

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
}