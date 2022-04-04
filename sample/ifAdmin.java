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



public class ifAdmin extends VBox {

	private Button removeCoupon;
	private Button addCouponButton;
	private Button addMenuItem;
	
	private TextField couponDiscount;
	private TextField couponName;
	private TextField itemName;
	private TextField itemPrice;
	private TextField removeLabelText;
	
	private ArrayList<CouponList> couponList;
	private ArrayList<MenuList> menuList;
	
	private Text title;
	private String items;
	private String coupon;
	
	private Label editMenuTitle;
	private Label couponsList;
	private CouponList coupon1;
	private CouponList coupon5;
	private CouponList coupon10;
	private Label couponAdded;
	private Label itemAdded;
	private Label couponNameLabel;
	private Label couponDiscountLabel;
	private Label menuItemLabel;
	private Label menuPriceLabel;
	private Label removeSuccess;
	private Label incorrectLabel;
	private Label removeLabel;
	private Label tempLabel;
	
	private CouponList test;
	private MenuList test2;
	private ArrayList<Account> accountList;
	private Stage primaryStage;
	//ArrayList<Ingredients> newFood = new ArrayList<Ingredients>();
	
	public ifAdmin(Stage stage, ArrayList<Account> accountList, ArrayList<MenuList> shoppingCart, ArrayList<CouponList> couponList) {
		
		primaryStage = stage;
		title = new Text("Edit Menu");
		menuList = shoppingCart;
		
		//editMenuTitle = new Label("Edit Menu");
		
		
		this.couponList = couponList;
		
		
		
		removeCoupon = new Button("Remove Coupon");
		addCouponButton = new Button("Add Coupon");
		addMenuItem = new Button("Add Menu Item");
		
		removeCoupon.setOnAction(new removeCouponButtonHandler());
		addCouponButton.setOnAction(new addCouponButtonHandler());
		addMenuItem.setOnAction(new addMenuItemButtonHandler());
		
		couponsList = new Label("Coupons");
		/*coupon1 = new Label("USE CODE coupon1 TO GET $1 OFF YOUR PUCHASE");
		coupon5 = new Label("USE CODE coupon5 GET $5 OFF YOUR PUCHASE");
		coupon10 = new Label("USE CODE coupon10 GET $10 OFF YOUR PUCHASE");*/
		
		couponNameLabel = new Label("");
		couponDiscountLabel = new Label("");
		itemAdded = new Label("");
		couponAdded = new Label("");
		removeLabel = new Label("Confirm coupon name to remove: ");
		tempLabel = new Label("");
		removeSuccess = new Label("");
		menuItemLabel = new Label("Add name of item here: ");
		menuPriceLabel = new Label("Add price of item here: ");		
		couponNameLabel = new Label("Enter coupon name: ");
		couponDiscountLabel = new Label("Enter coupon discount amount: ");
		
		
		couponName = new TextField();
		couponDiscount = new TextField();
		itemName = new TextField();
		itemPrice = new TextField();
		removeLabelText = new TextField();
		
		VBox root = new VBox();
		root.setSpacing(8);
		root.setAlignment(Pos.CENTER);
		//root.getChildren().add(couponsList);
		
		VBox removal = new VBox();
		
		
		GridPane couponsLine = new GridPane();
		couponsLine.setHgap(10);
		couponsLine.setVgap(5);
		couponsLine.setPadding(new Insets(10,10,10,10));
		
		items = "";
			for(int i = 0; i < couponList.size(); i++) {
				items+=""+couponList.get(i).getName() + "\n";
			}
		tempLabel = new Label(items);
		couponsLine.add(tempLabel,0,0);
		
		
		HBox align = new HBox();
		
		align.getChildren().addAll(couponNameLabel, couponName,couponDiscountLabel, couponDiscount,addCouponButton,couponAdded);
		//align.getChildren().add(removeSuccess);
		//align.getChildren().addAll(removeCoupon, removeSuccess);
		align.setPadding(new Insets(10));
		

		
		HBox addCoupon = new HBox();
		addCoupon.setPadding(new Insets(10));
		//addCoupon.getChildren().add(couponAdded);
		addCoupon.getChildren().addAll(removeLabel, removeLabelText,removeCoupon,removeSuccess);
		
		
		HBox bottom = new HBox();
		bottom.setPadding(new Insets(10));
		bottom.getChildren().addAll(menuItemLabel,itemName,menuPriceLabel,itemPrice,addMenuItem, itemAdded); //, itemAdded
		
		root.getChildren().addAll(title, couponsLine, align, addCoupon, bottom);
		
		Scene scene = new Scene(root, 900, 400);
		primaryStage.setTitle("Admin Account");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		//Have images of coupons nearby the buttons or just labels(?)
		
		//set up couponsList class to add coupons
		
		//Add menu item button removes menu items.
		
	}
	
	private class removeCouponButtonHandler implements EventHandler<ActionEvent> {
		
		public void handle(ActionEvent buttonClick) {
			
			
			
			String coupon = "";
			boolean found = false;
			for(int i = 0; i < couponList.size(); i++) {
				
				coupon = couponList.get(i).getName();
				//System.out.println(coupon);
				if(coupon.equals(removeLabelText.getText())){
					found = true;
					System.out.println(coupon);
					couponList.remove(i);
					//removeSuccess.setText("Removal Successful!");
				}
				
				
					
			}
			
			if(found == true) {
				removeSuccess.setText("Removal Successful!");
			}
				else {
					removeSuccess.setText("No coupon was removed.");
				}
			
			items = "";
			for(int i = 0; i < couponList.size(); i++) {
				items+=""+couponList.get(i).getName() + "\n";
			}
			tempLabel.setText(items);

			
		}
	}
	
	private class addCouponButtonHandler implements EventHandler<ActionEvent> {
		
		public void handle(ActionEvent buttonClick) {
			
			
			
			if(couponName.getText().equals("") || couponDiscount.getText().equals("")){
				
		  			 incorrectLabel.setText("Please fill add coupon name and/or discount amount.");
		  			 
		    }
			
			else {
				
				String name = couponName.getText();
				double price = Double.parseDouble(couponDiscount.getText());
				test = new CouponList(name, price);
				
				couponList.add(test);
				couponAdded.setText("Coupon Successfully Added!");
				
				
			}
			
			items = "";
			for(int i = 0; i < couponList.size(); i++) {
				items+=""+couponList.get(i).getName() + "\n";
			}
			tempLabel.setText(items);
			
		}
	}
	
	private class addMenuItemButtonHandler implements EventHandler<ActionEvent> {
		
		public void handle(ActionEvent buttonClick) {
			
			String name = itemName.getText();
			double price = Double.parseDouble(itemPrice.getText());
			
			try {
				 
				 Image image = new Image(getClass().getResource("/sample/newitemm.png").toURI().toString());
				 test2 = new MenuList(name, null, price, image, "");
				 
			 } catch (URISyntaxException e) {
				 
				 e.printStackTrace();
				 
			 }
			
			//Add ingredients somehow
			
			menuList.add(test2);
			
			itemAdded.setText("Item Successfully Added!");
			
			
		}
	}
	
}
