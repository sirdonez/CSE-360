package sample;

import javafx.scene.image.Image;

import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;

public class MenuList {

    private String name;
    private ArrayList<Ingredients> flavors;
    private ArrayList<Integer> orderNumber;
    private double price;
    private Image picture;
    private String button;


    public MenuList() {

        this.name = null;
        this.flavors = new ArrayList<>();
        this.price = -1;
        this.picture = null;
        this.button = null;

    }
    public MenuList(String name, ArrayList<Ingredients> flavors, double price, Image picture, String button) {

        this.name = name;
        this.flavors = flavors;
        this.price = price;
        this.picture = picture;
        this.button = button;

    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;

    }

    public void setFlavors(ArrayList<Ingredients> flavors) {

        this.flavors = flavors;

    }

    public ArrayList<Ingredients> getFlavors() {

        return flavors;

    }



    public double getPrice() {

        return price;

    }

    public void setPrice(double price) {

        this.price = price;

    }



    public Image getPicture() {

        return picture;

    }

    public void setPicture(Image picture) {

        this.picture = picture;

    }



    public String printPrice() {

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String moneyString = formatter.format(price);
        return "\t" + moneyString;

    }

    public String toString() {

        String flavorsString = "";
        for (Ingredients flavor : flavors) {

            flavorsString = flavorsString + flavor.getName() + "\n\t";

        }
        return name + "\n\t" + flavorsString + "\n";
    }
    
    
    
    public void setIngredient(ArrayList<Ingredients> in) {
    	this.flavors = in;
    	
    }
    

}
