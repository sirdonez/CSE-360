package sample;

import javafx.scene.image.Image;

import java.text.NumberFormat;
import java.util.ArrayList;

public class CouponList {
	
	private String name;
	private double discount;
	//private Image couponPicture;
	
	public CouponList(String name, double discount) {
		
		this.name = name;
		this.discount = discount;
		//this.couponPicture = couponPicture;
	}
		
	public String getName() {

	    return this.name;

	 }


	public double getdiscount() {

	    return discount;

	    }

/*	public Image getCouponPicture() {

		return couponPicture;

	   } */

	public void setName(String name) {

	    this.name = name;

	 }


	public void setDiscount(double discount) {

	    this.discount = discount;

	    }

/*	public Image setCouponPicture(Image couponPicture) {

		this.couponPicture = couponPicture;

	   } */


	 public String printPrice() {

	        NumberFormat formatter = NumberFormat.getCurrencyInstance();
	        String moneyString = formatter.format(discount);
	        return "\t" + moneyString;

	    }

	   /* public String toString() {

	        String flavorsString = "";
	        for (Ingredients flavor : flavors) {

	            flavorsString = flavorsString + flavor.getName() + "\n\t";

	        }
	        return name + "\n\t" + flavorsString + "\n";
	    } */

		
}
	
	

