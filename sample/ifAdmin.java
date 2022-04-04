package sample;
	
import java.awt.Label;
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;



public class ifAdmin extends Vbox {

	private button removeCoupon;
	private button addCoupon;
	private button addMenuItem;
	
	private TextField couponDiscount;
	private TextField couponName;
	private TextField itemName;
	private TextField itemPrice;
	private TextField removeLabelText;
	
	private ArrayList<CouponList> changeCoupons;
	private ArrayLits<MenuList> changeMenu;
	
	private Text title;
	private String items;
	private Sting coupon;
	
	private Label editMenuTitle;
	private Label couponsList;
	private Label coupon1;
	private Label coupon5;
	private Label coupon10;
	private Label couponAdded;
	private Label itemAdded;
	private Label couponNameLabel;
	private Label couponDiscountLabel;
	private Label menuItemLabel;
	private Label menuPriceLabel;
	private Label removeSuccess;
	private Label incorrectLabel;
	private Label removeLabel;
	
	private CouponList test;
	private MenuList test2;
	private ArrayList<Account> accountList;
	private Stage primaryStage;
	//ArrayList<Ingredients> newFood = new ArrayList<Ingredients>();
	
	public ifAdmin(Stage stage, ArrayList<MenuList> Menulist, ArrayList<CouponList> changeCoupons) {
		
		primaryStage = stage;
		title = new Text("Edit Menu");
		//editMenuTitle = new Label("Edit Menu");
		
		
		changeCoupons = new ArrayList<CouponList>();
		
		removeCoupon = new Button("Remove Coupon");
		addCoupon = new Button("Add Coupon");
		addMenuItem = new Button("Add Menu Item");
		
		removeCoupon.setOnAction(new removeCouponButtonHandler());
		addCoupon.setOnAction(new addCouponButtonHandler());
		addMenuItem.setOnAction(new addMenuItemButtonHandler());
		
		couponsList = new Label("Coupons");
		coupon1 = new Label("USE CODE coupon1 TO GET $1 OFF YOUR PUCHASE");
		coupon5 = new Label("USE CODE coupon5 GET $5 OFF YOUR PUCHASE");
		coupon10 = new Label("USE CODE coupon10 GET $10 OFF YOUR PUCHASE");
		couponName = new Label("");
		couponDiscount = new Label("");
		itemAdded = new Label("");
		couponAdded = new Label("");
		removeLabel = new Label("");
		
		
		
		Vbox root = new Vbox();
		root.setSpacing(8);
		root.setAlignment(Pos.CENTER);
		//root.getChildren().add(couponsList);
		
		VBox removal = new VBox();
		
		
		GridPane couponsLine = new GridPane();
		couponsLine.setHgap(10);
		couponsLine.setVgap(5);
		couponsLine.setPadding(new Insets(10,10,10,10));
		
			for(int i = 0; i < changeCoupons.size(); i++) {
				couponsLine.add(removeCoupon, 0, 0, 1, 1);
				couponsLine.add(changeCoupons.get(i).getName(), 1, 0, 1, 1);
			}
		
		HBox align = new HBox();
		align.setPadding(new Insets(10));
		align.getChildren().addall(removeCoupon, removeSuccess);

		
		HBox addCoupon = new Hbox();
		addCoupon.setPadding(new Insets(10));
		addCoupon.getChildren().addAll(addCoupon, couponName, couponDiscount, couponAdded);
		
		HBox bottom = new Hbox();
		bottom.setPadding(new Insets(10));
		bottom.getChildren().addAll(addMenuItem, itemAdded);
		
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
			
			removeLabel.setText("Confirm coupon name to remove: ")
			removeLabelText = new TextField();
			
			String removeCouponName = removeLabelText;
			
			for(int i = 0; i < changeCoupons.size; i++) {
				
				if(changeCoupons.get(i).getName().equals(removeCouponName)){
					
					changeCoupons.remove(i);
					removeSuccess.setText("Removal Successful!");
					
				}
			}
				
			removeSuccess.setText("No coupon was removed.");

		}
	}
	
	private class addCouponButtonHandler implements EventHandler<ActionEvent> {
		
		public void handle(ActionEvent buttonClick) {
			
			couponNameLabel = new Label("Enter coupon name: ");
			couponName = new TextField();
			
			couponDiscountLabel = new Label("Enter coupon discount amount: ");
			couponDiscount = new TextField();
			
			if(couponName.getText().equals("") || couponDiscount.getText().equals("")){
				
		  			 incorrectLabel.setText("Please fill add coupon name and/or discount amount.");
		  			 
		    }
			
			else {
				
				String name = couponName;
				double price = couponDiscount;
				
				test.setName(name);
				test.setDiscount(price);
				
				changeCoupons.add(test);
				couponAdded.setText("Coupon Successfully Added!");
				
				
			}
			
		}
	}
	
	private class addMenuItemButtonHandler implements EventHandler<ActionEvent> {
		
		public void handle(ActionEvent buttonClick) {
			
			menuItemLabel.setText("Add name of item here: ");
			itemName = new TextField();
			
			menuPriceLabel.setText("Add price of item here: ");
			itemPrice = new TextField();
			
			
			
			String name = itemName;
			double price = itemPrice;
			Image image = new Image(getClass().getResource("/sample/newitemm.jpg").toURI().toString());
			//Add ingredients somehow
			
			
			test2.setName(name);
			test2.setPrice(price);
			test2.setIngredients(null);
			test2.setPicture(image);
			
			changeMenu.add(test2);
			
			itemAdded.setText("Item Successfully Added!");
			
			
		}
	}
	
}
}
