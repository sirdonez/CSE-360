package sample;
	
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;		//Button, Label, TextField is inside this pkg
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
//This is used to set up the layout
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class reviewOrder extends VBox{
	
	
	private Label linePositionLabel;
	private Label waitTimeLabel;
	private Label orderListLabel;
	private Label itemListLabel;
	private Label totalPriceLabel;
	private Label couponsLabel;
	private Label couponSuccess;
	private Label paySuccess;
	
	private TextField couponsInput;
	private TextField paymentInfoInput;
	private TextField phoneNumberInput;
	
	private Button payButton;
	private Button deleteButton;
	private Button backButton; 
	private Button addCoupon;
	//private orderItems ArrayList<MenuItem>
	private double totalPrice;
	//private int linePosition;
	private int waitTime;
	
	private ArrayList<MenuList> orderList; //find a way to share a menuList amongst all classes... static?
	private Text title;
	private String items;
	private String coupon;
	
	private Stage primaryStage;
	
	
	public reviewOrder() {
		title = new Text("Check Out");
		ArrayList<Ingredients> bread = new ArrayList<Ingredients>();
		/*Image image1 = new Image(new File("C:\\Users\\Nico\\Desktop\\x.jpg").toURI().toURL().toExternalForm());
		orderList.add(new MenuList("Bread Loafs", bread, 2.00, image));
		
		for(int i = 0; i < orderList.size(); i++) {
			
		}*/
		coupon = "1234";
		totalPrice = 3.43;
		items = "2 Number nine(s)\n1 Number nine(s) Large";
		orderListLabel = new Label(items);
		linePositionLabel = new Label("Spot in Line: ");
		totalPriceLabel= new Label("Total Price: $" + totalPrice);
		waitTimeLabel = new Label("Wait Time: ");
		itemListLabel = new Label("Items:");
		couponsLabel = new Label("Enter Coupon:");
		couponSuccess = new Label("");
		couponsInput = new TextField("Ex: 1234");
		payButton = new Button("PAY");
		this.payButton.setOnAction(new payButtonHandler());
		paySuccess = new Label("");
		deleteButton = new Button("Delete Item(s)");
		backButton = new Button("Back");
		addCoupon = new Button("Add Coupon");
		this.addCoupon.setOnAction(new addCouponHandler());
		primaryStage = new Stage();
		
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
		timeBox.add(linePositionLabel, 0, 0);
		timeBox.add(waitTimeLabel, 0,1);
		
		HBox payBox = new HBox();
		payBox.setPadding(new Insets(10));
		payBox.getChildren().addAll(payButton,paySuccess);
		payBox.setSpacing(20);
		
		root.getChildren().addAll(title,itemLabelBox,orderListBox, couponBox, timeBox, payBox);
		
		Scene scene = new Scene(root, 900, 400);
		primaryStage.setTitle("Review Order");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	
	
	private class addCouponHandler implements EventHandler<ActionEvent> 
    {
    	 public void handle(ActionEvent buttonClick) 
    	 {
    		 if(couponsInput.getText().equals(""))
             {
    			 couponSuccess.setText("Invalid Coupon or Empty Field!");
             }
    		 
             else if(couponsInput.getText().equals(coupon))
             {
            	 //Login and go to some page
            	 couponSuccess.setText("Coupon Succesfully Added!");
            	 totalPriceLabel.setText("Total Price: $" + (totalPrice-1));
             }            
    		 
             else
             {
            	 couponSuccess.setText("Invalid Coupon or Empty Field!");
             }

    	 }
    	 
    }
	
	private class payButtonHandler implements EventHandler<ActionEvent> 
    	    {
    	    	 public void handle(ActionEvent buttonClick) 
    	    	 {
    	    		 paySuccess.setText("Payment Successful!");

    	    	 }
    	    }
}
