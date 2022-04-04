package sample;
	
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import sample.Menu;
import javafx.scene.Scene;
import javafx.scene.control.*;		//Button, Label, TextField is inside this pkg
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
//This is used to set up the layout
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class ReviewOrder extends VBox{
	
	
	private Label linePositionLabel;
	private Label waitTimeLabel;
	private Label orderListLabel;
	private Label itemListLabel;
	private Label totalPriceLabel;
	private Label couponsLabel;
	private Label couponSuccess;
	private Label paySuccess;
	private Label paymentLabel;
	private Label phoneNumberLabel;
	private Label payInfoSuccess;
	private Label phoneNumberSuccess;
	
	
	private TextField couponsInput;
	private TextField paymentInfoInput;
	private TextField phoneNumberInput;
	
	private Button payButton;
	private Button deleteButton;
	private Button backButton; 
	private Button addCoupon;
	private Button quitButton;
	private Button enterPayButton;
	private Button enterPhoneButton;
	
	private double totalPrice;
	private int waitTime;
	
	private ArrayList<Account> accountList;
	private Account logIn;
	private ArrayList<MenuList> shoppingCart; 
	private ArrayList<CouponList> couponList;
	private MenuList temp;
	
	private Text title;
	private String items;
	private String coupon;
	
	private Stage primaryStage;
	
	
	public ReviewOrder(Stage stage, ArrayList<Account> accountList, Account logIn, ArrayList<MenuList> shoppingCart, ArrayList<CouponList> couponList) throws URISyntaxException { // NEW

		primaryStage = stage;

		this.shoppingCart = shoppingCart;
		this.accountList = accountList;
		this.logIn = logIn;
		Image image = new Image(getClass().getResource("/sample/newitemm.png").toURI().toString());
		this.temp = new MenuList("flan", null, 22, image);
		this.shoppingCart.add(temp);
		
		this.couponList = couponList;
		
		
	    title = new Text("Check Out");
		coupon = "coupon1";
		totalPrice = 0;
		int rand = (int)Math.floor(Math.random()*(20-15+1)+15);
		int position = (int)Math.floor(Math.random()*(20-0+1)+0);
		items = "";
		
		for(int i = 0; i < shoppingCart.size(); i++) {
			
			items += "1 " + shoppingCart.get(i).getName() + "\n";
			totalPrice += shoppingCart.get(i).getPrice();
		}
		
		
		//items = "2 Number nine(s)\n1 Number nine(s) Large";
		orderListLabel = new Label(items);
		linePositionLabel = new Label("Spot in Line: "+position);
		waitTimeLabel = new Label("Wait Time: "+rand+" min");
		totalPriceLabel= new Label("Total Price: $" + totalPrice);
		itemListLabel = new Label("Items:");
		
		couponsLabel = new Label("Enter Coupon:");
		phoneNumberLabel = new Label("Enter Phone # ");
		paymentLabel = new Label("Enter Payment Information:");
		payInfoSuccess = new Label("");
		phoneNumberSuccess = new Label("");
		couponSuccess = new Label("");
		paymentInfoInput = new TextField();
		phoneNumberInput = new TextField();
		couponsInput = new TextField("Ex: 1234");
		
		
		enterPayButton = new Button("Enter");
		enterPhoneButton = new Button("Enter");
		payButton = new Button("PAY");
		this.payButton.setOnAction(new payButtonHandler());
		paySuccess = new Label("");
		deleteButton = new Button("Delete Item(s)");
		this.deleteButton.setOnAction(new deleteButtonHandler());
		backButton = new Button("Back");
		this.backButton.setOnAction(new backButtonHandler());
		addCoupon = new Button("Add Coupon");
		this.addCoupon.setOnAction(new addCouponHandler());
		quitButton = new Button("QUIT");
		this.quitButton.setOnAction(new quitButtonHandler());
		
		
		
		

		
		VBox root = new VBox();
		root.setSpacing(8);
		root.setAlignment(Pos.CENTER);
		
		HBox itemLabelBox = new HBox();
		itemLabelBox.setPadding(new Insets(5,0, 0, 10));
		itemLabelBox.getChildren().addAll(itemListLabel,deleteButton);
		itemLabelBox.setSpacing(40);
		
		
		GridPane orderListBox = new GridPane();
		orderListBox.setHgap(10);
	    orderListBox.setVgap(7);
	    orderListBox.setPadding(new Insets(10,0,5,15));
		orderListBox.add(orderListLabel, 0, 0);
		orderListBox.add(totalPriceLabel, 0,3);
		
		HBox couponBox = new HBox();
		couponBox.setPadding(new Insets(10));
		couponBox.getChildren().addAll(couponsLabel, couponsInput, addCoupon, couponSuccess);
		couponBox.setSpacing(20);
		
		GridPane timeBox = new GridPane();
		timeBox.setHgap(10);
	    timeBox.setVgap(2);
	    timeBox.setPadding(new Insets(10));
	    timeBox.add(phoneNumberLabel, 0, 0);
	    timeBox.add(phoneNumberInput, 1, 0);
	    timeBox.add(phoneNumberSuccess, 2, 0);
	    timeBox.add(paymentLabel, 0, 1);
	    timeBox.add(paymentInfoInput, 1, 1);
	    timeBox.add(paySuccess, 2, 1);
		timeBox.add(linePositionLabel, 0, 2);
		timeBox.add(waitTimeLabel, 0,3);
		
		
		HBox payBox = new HBox();
		payBox.setPadding(new Insets(10));
		payBox.getChildren().addAll(backButton,payButton,paySuccess);
		payBox.setSpacing(20);
		
		root.getChildren().addAll(title,quitButton,itemLabelBox,orderListBox, couponBox, timeBox, payBox);
		
		Scene scene = new Scene(root, 900, 400);
		primaryStage.setTitle("Check Out");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	
	
	private class addCouponHandler implements EventHandler<ActionEvent> 
    {
    	 public void handle(ActionEvent buttonClick) 
    	 {
    		 
				String coupon = "";
				boolean found = false;
				double discount = 0;
				for (int i = 0; i < couponList.size(); i++) {

					coupon = couponList.get(i).getName();
					// System.out.println(coupon);
					if (coupon.equals(couponsInput.getText())) {
						found = true;
						discount = couponList.get(i).getdiscount();
						totalPrice -= discount;
					}
				}

				if (found == false) {
					couponSuccess.setText("Invalid Coupon or Empty Field!");
				}

				else if (found == true) {
					// Login and go to some page
					couponSuccess.setText("Coupon Succesfully Added!");
					totalPriceLabel.setText("Total Price: $" + (totalPrice));
				}

				else {
					couponSuccess.setText("Invalid Coupon or Empty Field!");
				}

			}

		}
	
	private class payButtonHandler implements EventHandler<ActionEvent> 
    	    {
    	    	 public void handle(ActionEvent buttonClick) 
    	    	 {
    	    		 paySuccess.setText("Payment Successful!");
    	    		 items = "";
    	    		 
    	    		 shoppingCart.clear();
    	    		 orderListLabel.setText("");
    	    		 totalPriceLabel.setText(""+totalPrice);
    	    	 }
    	    }
	
	private class deleteButtonHandler implements EventHandler<ActionEvent> 
    {
    	 public void handle(ActionEvent buttonClick) 
    	 {
    		 totalPrice = 0;
    		 items = "";
    		 shoppingCart.remove(0);
    		 for(int i = 0; i < shoppingCart.size(); i++) {
    				
    				items += "1 " + shoppingCart.get(i).getName() + "\n";
    				totalPrice += shoppingCart.get(i).getPrice();
    			}
    		 orderListLabel.setText(items);
    		 totalPriceLabel.setText(""+totalPrice);
    	 }
    }
	
			
	private class backButtonHandler implements EventHandler<ActionEvent> {

				public void handle(ActionEvent buttonClick) {

					Menu menu = null;

					try {
						menu = new Menu(primaryStage, accountList, logIn, shoppingCart, couponList);
					} catch (FileNotFoundException | URISyntaxException e) {
						e.printStackTrace();
					}
					
					Scene scene = new Scene(menu, 900, 400);

					Color color = Color.rgb(186, 255, 245);
					BackgroundFill backgroundFill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
					Background background = new Background(backgroundFill);
					menu.setBackground(background);

					primaryStage.setScene(scene);

				}

			}
	
	
	
	private class quitButtonHandler implements EventHandler<ActionEvent> {
		
		public void handle(ActionEvent buttonClick) {
			try
		    {
		      // Build the stream that can write objects (not text) to a disk
		      FileOutputStream bytesToDisk
		            = new FileOutputStream( "AccountList.object");
		      ObjectOutputStream objectToBytes
		            = new ObjectOutputStream( bytesToDisk );
		      // Show the state of the account before saving it
		      // Now objectToBytes will understand the writeObject message.
		      // Make the object persist on disk, so it can be read later.
		      objectToBytes.writeObject( accountList );
		      // Do NOT forget to close the output stream
		      objectToBytes.close( );
		    }
		    catch( IOException ioe )
		    {
		      System.out.println ( ioe.toString( ) );
		    }
		}
	}
			
}
