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
import javafx.scene.Parent;
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
    private Button createAccButton;
    private Button cancelAccountButton;
    
    private ArrayList<Account> accountList;
	private Account logIn;
	private ArrayList<MenuList> shoppingCart; 
	private ArrayList<CouponList> couponList;
	
	private CouponList coupon1;
	private CouponList coupon5;
	private CouponList coupon10;
	
	private Stage mainStage;


    public void start(Stage stage) {

    	mainStage = stage;
    	StackPane root = new StackPane();
    	couponList = new ArrayList<CouponList>();
    	
    	coupon1 = new CouponList("coupon1", 1.00);
		coupon5 = new CouponList("coupon5", 5.00);
		coupon10 = new CouponList("coupon10", 10.00);
    	this.couponList.add(coupon1);
		this.couponList.add(coupon5);
		this.couponList.add(coupon10);

    	//for test
        Account newAccount = new Account("S", "S", "deezNuts@gmail.com", false);
        this.accountList = new ArrayList<Account>();
        this.accountList.add(newAccount);

        Account adminAccount = new Account("N", "N", "deezNuts@gmail.com", true);
        this.accountList.add(adminAccount);
        
        shoppingCart = new ArrayList<MenuList>();
        

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
		HBox buttonPane = new HBox();
		loginButton = new Button("Login");
		loginButton.setOnAction(new LoginButtonHandler());
		
		createAccButton = new Button("Create Account");
		createAccButton.setOnAction(new createAccButtonHandler());

		cancelAccountButton = new Button("Guest");
		cancelAccountButton.setOnAction(new cancelButtonHandler());

		buttonPane.getChildren().addAll(loginButton, cancelAccountButton, createAccButton);
		buttonPane.setSpacing(30);

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
    		 
             else if(searchAccounts(usernameTextField.getText(), passwordTextField.getText())!=null) {

            	 //Login and go to some page
            	 incorrectLoginInfoLabel.setText("Correct Password");
            	 
				 Menu menu = null;
				 ifAdmin admin = null;
				 
				 logIn = searchAccounts(usernameTextField.getText(), passwordTextField.getText());
				 
				 if(logIn.getAccountType() == true){
					 
					 admin = new ifAdmin(mainStage, accountList, shoppingCart, couponList);
					 //Scene scene = new Scene(admin, 900, 400);
					 //mainStage.setScene(scene);
					 
				 }
				 else {
					 
					 try {
						 menu = new Menu(mainStage, accountList, logIn, shoppingCart, couponList);
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

             }
    		 
             else {

            	 incorrectLoginInfoLabel.setText("Incorrect username and or password.");

             }

    	 }
    	 
    }
    	 
    private class createAccButtonHandler implements EventHandler<ActionEvent> 
    	 {
    		 public void handle(ActionEvent buttonClick) 
    	  	 {
    			 logIn = searchAccounts(usernameTextField.getText(), passwordTextField.getText());
    			 CreateAccount testing = new CreateAccount(mainStage, accountList, logIn, shoppingCart, couponList);
    			 Scene scene = new Scene(testing, 900, 400);
    			 mainStage.setScene(scene); 
    			 
    	  	 }
    		 
    	 }


	private class cancelButtonHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent buttonClick)
		{
			Menu menu = null;

			try {
				menu = new Menu(mainStage, accountList, logIn, shoppingCart, couponList);
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
	}


    	 public Account searchAccounts(String username, String password) {

    			boolean isFound = false;
    			int i = 0;
    			
    			while(i < accountList.size() || !isFound) {
    				
    				if(accountList.get(i).getUsername().equals(username) && accountList.get(i).getPassword().equals(password)) {

    					isFound = true;
    					return accountList.get(i);

    				}
    				
    				i++;

    			}
    			return null;
    	 }

    

    public ArrayList<Account> getLogin()
    {
    	return accountList;
    }
    
    
}