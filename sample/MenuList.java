package sample;

import javafx.scene.image.Image;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MenuList {

    private String name;
    private ArrayList<Ingredients> flavors;
    private double price;
    private Image picture;


    public MenuList(String name, ArrayList<Ingredients> flavors, double price, Image picture) {

        this.name = name;
        this.flavors = flavors;
        this.price = price;
        this.picture = picture;

    }

    public String getName() {

        return name;

    }

    public ArrayList<Ingredients> getIngredients() {

        return flavors;

    }

    public double getPrice() {

        return price;

    }

    public Image getPicture() {

        return picture;

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
    
    public void setName(String name) {

        this.name = name;

    }


    public void setPrice(double price) {

        this.price = price;

    }

    public void setPicture(Image image) {

        this.picture = image;

    }
    
    public void setIngredient(ArrayList<Ingredients> in) {
    	this.flavors = in;
    	
    }
    

}
