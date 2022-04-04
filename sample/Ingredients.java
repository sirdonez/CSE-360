package sample;

import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;

public class Ingredients  {

    private ArrayList<String> ingredientsList;
    private String name;
    private Stage mainStage;


    public Ingredients(String name) {


        this.ingredientsList = new ArrayList<>();
        this.name = name;


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

        String items = "\t";

        for(int i = 0; i < ingredientsList.size(); i++) {

            items = items + ingredientsList.get(i) + "\n\t";

        }
        return items;
    }

}
