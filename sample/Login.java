package sample;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;



public class Login extends Application {

	private Label incorrectLoginInfoLabel;
	private TextField usernameTextField;
    private TextField passwordTextField;
    private Button loginButton;
    
    //Find a way to share one accountList object amongst all the classes
	private ArrayList<Account> accountList;
	private Stage mainStage;


    public void start(Stage stage) {

    	mainStage = stage;
    	StackPane root = new StackPane();

    	//for test
        Account newAccount = new Account("S", "S", "deezNuts@gmail.com", true);
        this.accountList = new ArrayList<Account>();
        this.accountList.add(newAccount);


        //Main VBox
        VBox mainVBox = new VBox();
        
		//Incorrect Username/Password
		incorrectLoginInfoLabel = new Label("");
		incorrectLoginInfoLabel.setTextFill(Color.RED);


		//LoginPane
		GridPane loginPane = new GridPane();

		Label usernameLabel = new Label("Username:");
		usernameTextField = new TextField();
		Label passwordLabel = new Label("Password:");
		passwordTextField = new TextField();
			
		loginPane.add(usernameLabel, 0, 0);
		loginPane.add(this.usernameTextField, 1, 0);
		loginPane.add(passwordLabel, 0, 1);
		loginPane.add(this.passwordTextField, 1, 1);

		loginPane.setVgap(10);
		loginPane.setHgap(10);


		//Button Pane
		BorderPane buttonPane = new BorderPane();
		this.loginButton = new Button("Login");
		this.loginButton.setOnAction(new LoginButtonHandler());
		buttonPane.setLeft(this.loginButton);


		mainVBox.getChildren().addAll(incorrectLoginInfoLabel, loginPane, buttonPane);
		mainVBox.setSpacing(10);
		mainVBox.setPadding(new Insets(20, 0, 0, 20));		
		
        root.getChildren().addAll(mainVBox);

        Scene scene = new Scene(root, 900, 400);
        stage.setTitle("Restaurant Ordering App");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }



    private class LoginButtonHandler implements EventHandler<ActionEvent> {

    	 public void handle(ActionEvent buttonClick) {

    		 if(usernameTextField.getText().equals("") || passwordTextField.getText().equals("")) {

    			 incorrectLoginInfoLabel.setText("Please fill all fields.");

             }
    		 
             else if(searchAccounts(usernameTextField.getText(), passwordTextField.getText())) {

            	 //Login and go to some page
            	 incorrectLoginInfoLabel.setText("Correct Password");
				 Menu menu = null;
				 try {
					 menu = new Menu(mainStage);
				 } catch (FileNotFoundException | URISyntaxException e) {
					 e.printStackTrace();
				 }
				 Scene scene = new Scene(menu, 900, 400);

				 Color color = Color.rgb(186,255,245);
				 BackgroundFill backgroundFill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
				 Background background = new Background(backgroundFill);
				 menu.setBackground(background);

				 mainStage.setScene(scene);

             }
    		 
             else {

            	 incorrectLoginInfoLabel.setText("Incorrect username and or password.");

             }

    	 }
    	 
    	 
    	 public boolean searchAccounts(String username, String password) {

    			boolean isFound = false;
    			int i = 0;
    			
    			while(i < accountList.size() || !isFound) {
    				
    				if(accountList.get(i).getUsername().equals(username) && accountList.get(i).getPassword().equals(password)) {

    					isFound = true;

    				}
    				
    				i++;

    			}
    			
    			return isFound;

    	 }

    }


}