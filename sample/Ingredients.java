package sample;

import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Ingredients extends BorderPane {

    ArrayList<String> ingredientsList;
    String name;


    public Ingredients(String name) {

        this.ingredientsList = new ArrayList<String>();
        this.name = name;

        VBox vbox = new VBox();
        //vbox.


    }

    public String getName() {

        return name;

    }

    public ArrayList<String> getIngredientsList() {

        return ingredientsList;

    }

    public void addItem(String item) {

        ingredientsList.add(item);

    }

    public void removeItem(String item) {

        ingredientsList.remove(item);

    }


    public String toString() {

        String items = "";

        for(int i = 0; i < ingredientsList.size(); i++) {

            items = items + ingredientsList.get(i) + "\n";

        }
        return "Ingredients:\n\t" + items;
    }

}
