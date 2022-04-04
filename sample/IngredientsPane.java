package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;

import java.awt.*;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class IngredientsPane extends BorderPane {

    private Stage mainStage;
    private ArrayList<MenuList> menuLists;
    private int index;

    private ArrayList<Account> accountList;
    private Account logIn;
    private ArrayList<MenuList> shoppingCart;

    private Button backButton;
    private ScrollPane scrollPane;
    private VBox vBox;
    private VBox container;


    private Text text;

    public IngredientsPane(Stage stage, ArrayList<MenuList> menuLists, int index, ArrayList<Account> accountList, Account logIn, ArrayList<MenuList> shoppingCart) {

        this.mainStage = stage;
        this.menuLists = menuLists;
        this.index = index;
        this.accountList = accountList;
        this.logIn = logIn;
        this.shoppingCart = shoppingCart;

        backButton = new Button("Back");
        backButton.setFont(Font.font("Cambria",10));
        this.setBottom(backButton);
        backButton.setOnAction(new backButtonHandler());

        scrollPane = new ScrollPane();
        scrollPane.setMinWidth(400);
        scrollPane.setMinHeight(200);
        vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setMinWidth(400);

        scrollPane.setContent(vBox);

            MenuList menuList = new MenuList();

            for (int i = 0; i < menuLists.size(); i++) {

                if (index == i) {

                    menuList = menuLists.get(i);

                }
            }

            text = new Text(menuList.getName());
            text.setFont(Font.font("Cambria",40));

            ArrayList<Text> list = new ArrayList<Text>();
            ArrayList<Text> list1 = new ArrayList<Text>();



            for (int i = 0; i < menuList.getFlavors().size(); i++) {

                list.add(new Text(menuList.getFlavors().get(i).getName()));
                list.get(i).setFont(Font.font("Cambria",20));

                vBox.getChildren().addAll(list.get(i));

                list1.add(new Text(menuList.getFlavors().get(i).toString()));
                list1.get(i).setFont(Font.font("Cambria", 20));

                vBox.getChildren().addAll(list1.get(i));



            }


            container = new VBox();
            container.getChildren().add(scrollPane);
            this.setTop(text);
            this.setCenter(container);
        }





    private class backButtonHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent buttonClick) {

            Menu menu = null;

            try {
                menu = new Menu(mainStage, accountList, logIn, shoppingCart);
            } catch (FileNotFoundException | URISyntaxException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(menu, 900, 400);

            javafx.scene.paint.Color color = Color.rgb(186, 255, 245);
            BackgroundFill backgroundFill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
            Background background = new Background(backgroundFill);
            menu.setBackground(background);

            mainStage.setScene(scene);

        }

    }

}
