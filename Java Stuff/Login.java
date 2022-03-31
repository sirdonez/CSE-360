//This program demonstrates some most commonly used GUI components in JavaFX

import java.util.ArrayList;

import javafx.event.ActionEvent; //**Need to import
import javafx.event.EventHandler; //**Need to import

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;		//Button, Label, TextField is inside this pkg
import javafx.scene.paint.Color;
//This is used to set up the layout
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;


public class Login extends Application
{
	private Label incorrectLoginInfoLabel;
	private TextField usernameTextField;
    private TextField passwordTextField;
    private Button loginButton;
    
    //Find a way to share one accountList object amongst all the classes
	private ArrayList<Account> accountList;
    
    public void start(Stage stage) {
        StackPane root = new StackPane();
        
        //Tab and Tabpanes
        /*TabPane tabPane = new TabPane();

        Tab loginTab = new Tab();
        loginTab.setText("Login");
        
        tabPane.getSelectionModel().select(0);
        tabPane.getTabs().addAll(loginTab);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);*/
        
        Account newAccount = new Account("Louis", "Mendoza", "deezNuts@gmail.com", true);
        
        this.accountList = new ArrayList<Account>();
        this.accountList.add(newAccount);
        
        //Main VBox
        VBox mainVBox = new VBox();
        
	        //Incorrect Username/Password
        	incorrectLoginInfoLabel = new Label("");
			incorrectLoginInfoLabel.setTextFill(Color.RED);
			
			//LoginPane
			GridPane loginPane = new GridPane();
			
				//Login Info and textfields
				Label usernameLabel = new Label("Username:");
					this.usernameTextField = new TextField();
				Label passwordLabel = new Label("Password:");
					this.passwordTextField = new TextField();
			
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
    
    //Login Button Handler
    private class LoginButtonHandler implements EventHandler<ActionEvent> 
    {
    	 public void handle(ActionEvent buttonClick) 
    	 {
    		 if(usernameTextField.getText().equals("") || passwordTextField.getText().equals(""))
             {
    			 incorrectLoginInfoLabel.setText("Please fill all fields.");
             }
    		 
             else if(searchAccounts(usernameTextField.getText(), passwordTextField.getText()) == true)
             {
            	 //Login and go to some page
            	 incorrectLoginInfoLabel.setText("Correct Password");
             }            
    		 
             else
             {
            	 incorrectLoginInfoLabel.setText("Incorrect username and or password."); 
             }

    	 }
    	 
    	 
    	 public boolean searchAccounts(String username, String password)
    		{
    			boolean isFound = false;
    			
    			int i = 0;
    			
    			while(i < accountList.size() || isFound != true)
    			{
    				
    				if(accountList.get(i).getUsername().equals(username) && accountList.get(i).getPassword().equals(password))
    				{
    					isFound = true;
    				}
    				
    				i++;
    			}
    			
    			return isFound;
    		}
    }
}