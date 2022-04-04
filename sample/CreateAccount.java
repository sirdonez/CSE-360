package sample;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;

public class CreateAccount extends BorderPane {
	
	private Label userNameLabel;
	private Label passWordLabel;
	private Label emailLabel;
	private Label cardNumberLabel;
	private Label incorrectLabel;
	private Label createAccountLabel;
	
	private TextField userNameTextField;
	private TextField passWordTextField;
	private TextField emailTextField;
	private TextField cardNumberTextField;
	
	private BorderPane base;
	
	private GridPane dataInput;
	
	private HBox title;    
	private HBox error; 
	
	private TilePane optionButtons;
	private Button createAccountButton;
	private Button cancelButton;
	
	private Login test;
	private ArrayList<Account> aList;
	private Account aTest;

	
	private ArrayList<Account> accountList;
	private Account logIn;
	private ArrayList<MenuList> shoppingCart; 

	private Stage primaryStage;
	
	public CreateAccount(Stage stage, ArrayList<Account> accountList, Account logIn, ArrayList<MenuList> shoppingCart)
	{
		this.primaryStage = stage;
		
		test = new Login();
		aList = new ArrayList<Account>();
		aTest = new Account("","","",false);
		
		this.accountList = accountList;
		
		error = new HBox(4);   
		title = new HBox(4);   
		//base = new BorderPane();
		optionButtons = new TilePane(Orientation.HORIZONTAL);
		dataInput = new GridPane();
		createAccountLabel = new Label("Create Account");
		createAccountButton = new Button("Create Account");
		cancelButton = new Button("Guest");
	
		userNameLabel = new Label("Please enter username below:");
		userNameTextField = new TextField();
		passWordLabel = new Label("Please enter password below: (Max of 8 characters?)");
		passWordTextField = new TextField();
		emailLabel = new Label("Please enter your email below:");
		emailTextField = new TextField();
		cardNumberLabel = new Label("Please enter your card number below(consisting of 12 numbers):");
		cardNumberTextField= new TextField();
		
		incorrectLabel = new Label("");
		
		createAccountButton.setOnAction(new createAccountButtonHandler());

		cancelButton.setOnAction(new cancelButtonHandler());

		error.getChildren().addAll(title);
		
		dataInput.setAlignment(Pos.TOP_CENTER);
		dataInput.setPadding(new Insets(10, 10, 10, 10));
		dataInput.setHgap(5);
		dataInput.setVgap(10);
	    dataInput.add(userNameLabel,0,0);
	    dataInput.add(userNameTextField,1,0);
	    dataInput.add(passWordLabel,0,1);
	    dataInput.add(passWordTextField,1,1);
	    dataInput.add(emailLabel,0,2);
	    dataInput.add(emailTextField,1,2);
	    dataInput.add(cardNumberLabel,0,3);
	    dataInput.add(cardNumberTextField,1,3);
	    
	        
	    error.getChildren().addAll(incorrectLabel);
	
		optionButtons.setAlignment(Pos.BASELINE_RIGHT);
		optionButtons.setHgap(15);
		optionButtons.getChildren().addAll(incorrectLabel,createAccountButton,cancelButton);
		
		this.setTop(title);
		this.setCenter(dataInput);
		error.setAlignment(Pos.BASELINE_LEFT);
		this.setBottom(optionButtons);
		optionButtons.setAlignment(Pos.BASELINE_RIGHT);

	}
	
	

	private class createAccountButtonHandler implements EventHandler<ActionEvent> 
	  {
	  	 public void handle(ActionEvent buttonClick) 
	  	 {
	  		 if(userNameTextField.getText().equals("") || passWordTextField.getText().equals("") || emailTextField.getText().equals("") 
	  			|| cardNumberTextField.getText().equals(""))
	           {
	  			 incorrectLabel.setText("Please fill all fields.");
	           }
	           else
	           {
	        	   String username = userNameTextField.getText();
	        	   String password = passWordTextField.getText();
	        	   String email = emailTextField.getText();
	        	   int cardNumber = Integer.parseInt(cardNumberTextField.getText());
	        	   
	        	   aTest.setUserName(username);
	        	   aTest.setPassword(password);
	        	   aTest.setEmail(email);
	        	   aTest.setCreditCardNumber(cardNumber);
	        	   aList.add(aTest);
	        	   incorrectLabel.setText("Account has been created!"); 
	        	   Menu menu = null;
	  			   try {
	  				   menu = new Menu(primaryStage, accountList, logIn, shoppingCart);
	  			   } catch (FileNotFoundException | URISyntaxException e) {
	  				 e.printStackTrace();
	  			   }
	  			   Scene scene = new Scene(menu, 900, 400);
 
   	  			   Color color = Color.rgb(186,255,245);
	  			   BackgroundFill backgroundFill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
	  			   Background background = new Background(backgroundFill);
	  			   menu.setBackground(background);
	  			   primaryStage.setScene(scene);
	  			   
	  			   
	  			// First create an object input stream with the readObject method
	  			    FileInputStream diskToStreamOfBytes
	  			        = new FileInputStream( "AccountList.object" );
	  			    // Construct an objectNow with the readObject method
	  			    ObjectInputStream objectToBytes
	  			        = new ObjectInputStream( diskToStreamOfBytes );
	  			    // Read the entire object with the ObjectInputStream. The checked
	  			    // exception must be caught (even though Object is a known class).
	  			    
	  			    try
	  			    {
	  			    	aList = objectToBytes.readObject( );
	  			    }
	  			    catch( ClassNotFoundException cnfe )
	  			    {
	  			      System.out.println( cnfe );
	  			    }
	  			    // Now cast from Object to the class that it is known to be.
	  			    BankAccount singleAccount = (BankAccount)anyObject;
	  			    // Close input files also.
	  			    objectToBytes.close( );
	  			   
	           }

	  	 }
	  }
	  
	  private class cancelButtonHandler implements EventHandler<ActionEvent> 
	  {
	  	 public void handle(ActionEvent buttonClick) 
	  	 {
	  		 Menu menu = null;
			 try {

				 menu = new Menu(primaryStage, accountList, logIn, shoppingCart);
			 } catch (FileNotFoundException | URISyntaxException e) {
				 e.printStackTrace();
			 }
			 Scene scene = new Scene(menu, 900, 400);

			 Color color = Color.rgb(186,255,245);
			 BackgroundFill backgroundFill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
			 Background background = new Background(backgroundFill);
			 menu.setBackground(background);

			primaryStage.setScene(scene);
	  	 }
	  }
	}


