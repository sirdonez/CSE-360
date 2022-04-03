package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;


public class Menu extends BorderPane {

    private ArrayList<MenuList> foodItems;
    private ArrayList<MenuList> drinkItems;

    private VBox mainPaneInScroll;
    private HBox selectAndSearchPane;
    private VBox vertical;

    private Label menuTitle;
    private Label endingLabel;

    private TextField searchTextField;

    private ScrollPane scrollPane;

    private Button addFoodButton;
    private Button checkOutButton;
    private Button searchButton;



    public Menu() throws FileNotFoundException, URISyntaxException {

        //main box
        mainPaneInScroll = new VBox();
        mainPaneInScroll.setMinWidth(1400);


        //fill item lists
        foodItems = new ArrayList<MenuList>();
        drinkItems = new ArrayList<MenuList>();

        food();
        drink();


        //buttons
        addFoodButton = new Button("Add Meals");
        checkOutButton = new Button("Go to Checkout");
        searchButton =  new Button("Search");
        searchButton.setOnAction(new searchButtonHandler());


        //labels
        menuTitle = new Label("~The Little Bakery~");
        endingLabel = new Label("~Order Now~");


        //text field
        searchTextField = new TextField();
        searchTextField.setPrefWidth(500);


        //colors
        BackgroundFill backgroundFill1 = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        Background black = new Background(backgroundFill1);

        Color color = Color.rgb(255,209,239);
        BackgroundFill backgroundFill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
        Background pink = new Background(backgroundFill);


        //scroll pane
        scrollPane = new ScrollPane();
        scrollPane.setContent(mainPaneInScroll);
        scrollPane.setBackground(black);


        //search pane
        selectAndSearchPane = new HBox(searchButton, searchTextField);
        selectAndSearchPane.setSpacing(20);
        selectAndSearchPane.setPadding(new Insets(10,10, 15, 90));



        //spinners, adding food items, price/add button
        display();
        vertical.setSpacing(40);
        mainPaneInScroll.setBackground(pink);


        //Side pictures//

        //left
        VBox swirlsLeft = new VBox();

        Image imageLUSwirl = new Image(getClass().getResource("/sample/SwirlLeftUp.png").toURI().toString());
        ImageView imageViewLUSwirl = new ImageView(imageLUSwirl);
        Image imageLDSwirl = new Image(getClass().getResource("/sample/SwirlLeftDown.png").toURI().toString());
        ImageView imageViewLDSwirl = new ImageView(imageLDSwirl);

        swirlsLeft.getChildren().addAll(imageViewLUSwirl, imageViewLDSwirl);
        swirlsLeft.setPadding(new Insets(50,0,0,0));

        //right
        VBox swirlsRight = new VBox();

        Image imageRUSwirl = new Image(getClass().getResource("/sample/SwirlRightUp.png").toURI().toString());
        ImageView imageViewRUSwirl = new ImageView(imageRUSwirl);
        Image imageRDSwirl = new Image(getClass().getResource("/sample/SwirlRightDown.png").toURI().toString());
        ImageView imageViewRDSwirl = new ImageView(imageRDSwirl);

        swirlsRight.getChildren().addAll(imageViewRUSwirl, imageViewRDSwirl);
        swirlsRight.setPadding(new Insets(50,0,0,0));


        //top of menu
        HBox accountButtonBox = new HBox(selectAndSearchPane, new Button("Account"));
        accountButtonBox.setSpacing(850);

        this.setTop(menuTitle);
        this.setAlignment(menuTitle, Pos.CENTER);

        menuTitle.setFont(Font.font("Cambria", 42));
        endingLabel.setFont(Font.font("Cambria", 30));


        //middle
        VBox container1 = new VBox(accountButtonBox, scrollPane);
        HBox container2 = new HBox(swirlsLeft, container1, swirlsRight);

        container2.setPadding(new Insets(0,0,0,50));
        container2.setSpacing(20);

        this.setCenter(container2);


        //bottom
        HBox bottom = new HBox(endingLabel, addFoodButton);
        HBox bottomAgain = new HBox(bottom, checkOutButton);

        bottom.setSpacing(600);
        bottomAgain.setSpacing(100);

        checkOutButton.setFont(Font.font("Cambria", 20));
        checkOutButton.setShape(new Circle(1,1,1));
        checkOutButton.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        bottom.setPadding(new Insets(0,0,0,1200));
        bottomAgain.setPadding(new Insets(5,0,0,0));

        this.setBottom(bottomAgain);
        this.setAlignment(bottom, Pos.CENTER);

    }



    public void food() throws URISyntaxException {

        ArrayList<Ingredients> bread = new ArrayList<Ingredients>();
        Image image = new Image(getClass().getResource("/sample/FrenchBread.jpg").toURI().toString());

        bread.add(new Ingredients("French"));
        bread.get(0).addItem("Water");
        bread.get(0).addItem("Yeast");
        bread.get(0).addItem("Salt");
        bread.get(0).addItem("Sugar");
        bread.get(0).addItem("Flour");
        bread.get(0).addItem("Oil");

        bread.add(new Ingredients("Blueberry"));
        bread.get(1).addItem("Blueberries");
        bread.get(1).addItem("Baking Soda");
        bread.get(1).addItem("Eggs");
        bread.get(1).addItem("Granulated Sugar");
        bread.get(1).addItem("Salt");
        bread.get(1).addItem("Vanilla Extract");
        bread.get(1).addItem("White Sugar");
        bread.get(1).addItem("Flour");
        bread.get(1).addItem("Milk");
        bread.get(1).addItem("Oil");
        bread.get(1).addItem("Icing Glaze");

        bread.add(new Ingredients("Lemon"));
        bread.get(2).addItem("Lemon Zest");
        bread.get(2).addItem("Baking Soda");
        bread.get(2).addItem("Baking Powder");
        bread.get(2).addItem("Lemon Juice");
        bread.get(2).addItem("Salt");
        bread.get(2).addItem("Vanilla Extract");
        bread.get(2).addItem("White Sugar");
        bread.get(2).addItem("Flour");
        bread.get(2).addItem("Butter");
        bread.get(2).addItem("Eggs");
        bread.get(2).addItem("Icing Glaze");

        bread.add(new Ingredients("Banana"));
        bread.get(3).addItem("Bananas");
        bread.get(3).addItem("Baking Soda");
        bread.get(3).addItem("Egg");
        bread.get(3).addItem("Butter");
        bread.get(3).addItem("Salt");
        bread.get(3).addItem("Vanilla Extract");
        bread.get(3).addItem("Sugar");
        bread.get(3).addItem("Flour");

        foodItems.add(new MenuList("Bread Loafs", bread, 2.00, image));



        ArrayList<Ingredients> pretzel = new ArrayList<Ingredients>();
        image = new Image(getClass().getResource("/sample/Pretzel.jpg").toURI().toString());
        pretzel.add(new Ingredients("Salt"));
        foodItems.add(new MenuList("Pretzel", pretzel, 2.50, image));

        ArrayList<Ingredients> scones = new ArrayList<Ingredients>();
        image = new Image(getClass().getResource("/sample/Scone.jpg").toURI().toString());
        scones.add(new Ingredients("Blueberry"));
        scones.add(new Ingredients("Cinnamon"));
        foodItems.add(new MenuList("Scones", scones, 1.25, image));

        ArrayList<Ingredients> Muffins = new ArrayList<Ingredients>();
        image = new Image(getClass().getResource("/sample/Muffin.jpg").toURI().toString());
        Muffins.add(new Ingredients("Blueberry"));
        Muffins.add(new Ingredients("Lemon Poppy Seed"));
        Muffins.add(new Ingredients("Chocolate Chip"));
        foodItems.add(new MenuList("Muffins", Muffins, 4.50, image));

        ArrayList<Ingredients> cake = new ArrayList<Ingredients>();
        image = new Image(getClass().getResource("/sample/Cupcake.jpg").toURI().toString());
        cake.add(new Ingredients("Chocolate"));
        cake.add(new Ingredients("Vanilla"));
        cake.add(new Ingredients("Red Velvet"));
        foodItems.add(new MenuList("Cake Slice or Cupcake", cake, 7.00, image));

        ArrayList<Ingredients> cookies = new ArrayList<Ingredients>();
        image = new Image(getClass().getResource("/sample/Cookies.jpg").toURI().toString());
        cookies.add(new Ingredients("Frosted Sugar"));
        cookies.add(new Ingredients("Chocolate Chip"));
        cookies.add(new Ingredients("Peanut Butter"));
        cookies.add(new Ingredients("M&M"));
        foodItems.add(new MenuList("Cookies", cookies, 1.50, image));

        ArrayList<Ingredients> macaroons = new ArrayList<Ingredients>();
        image = new Image(getClass().getResource("/sample/Macaroon.jpg").toURI().toString());
        macaroons.add(new Ingredients("Pink"));
        macaroons.add(new Ingredients("Green"));
        macaroons.add(new Ingredients("Purple"));
        foodItems.add(new MenuList("Macaroons", macaroons, 0.50, image));

        ArrayList<Ingredients> donuts = new ArrayList<Ingredients>();
        image = new Image(getClass().getResource("/sample/Donut.jpg").toURI().toString());
        donuts.add(new Ingredients("Chocolate Sprinkled"));
        donuts.add(new Ingredients("Vanilla Sprinkled"));
        donuts.add(new Ingredients("Jelly Filled"));
        donuts.add(new Ingredients("Boston Creme"));
        donuts.add(new Ingredients("Glazed"));
        foodItems.add(new MenuList("Donuts", donuts, 1.50, image));

        ArrayList<Ingredients> brownies = new ArrayList<Ingredients>();
        image = new Image(getClass().getResource("/sample/Brownies.jpg").toURI().toString());
        foodItems.add(new MenuList("Brownies", brownies, 2.00, image));

        ArrayList<Ingredients> flan = new ArrayList<Ingredients>();
        image = new Image(getClass().getResource("/sample/Flan.jpg").toURI().toString());
        foodItems.add(new MenuList("Flan", flan, 5.50, image));

        ArrayList<Ingredients> pancakes = new ArrayList<Ingredients>();
        image = new Image(getClass().getResource("/sample/Pancakes.jpg").toURI().toString());
        foodItems.add(new MenuList("Pancakes", pancakes, 9.25, image));

        ArrayList<Ingredients> frenchToast = new ArrayList<Ingredients>();
        image = new Image(getClass().getResource("/sample/FrenchToast.jpg").toURI().toString());
        foodItems.add(new MenuList("French Toast", frenchToast, 9.25, image));

        ArrayList<Ingredients> sandwich = new ArrayList<Ingredients>();
        image = new Image(getClass().getResource("/sample/Sandwhich.jpg").toURI().toString());
        donuts.add(new Ingredients("Breakfast Croissant"));
        foodItems.add(new MenuList("Sandwich", sandwich, 8.75, image));

    }

    public void drink() throws URISyntaxException {

        ArrayList<Ingredients> milk = new ArrayList<Ingredients>();
        Image image = new Image(getClass().getResource("/sample/Milk.jpg").toURI().toString());
        drinkItems.add(new MenuList("Milk", milk, 1.00, image));
        milk.add(new Ingredients(("Whole")));
        milk.add(new Ingredients(("Skim")));
        milk.add(new Ingredients(("Almond")));
        milk.add(new Ingredients(("Lactate")));
        milk.add(new Ingredients(("Chocolate (Hot or Cold)")));
        milk.add(new Ingredients(("Strawberry")));

        ArrayList<Ingredients> juice = new ArrayList<Ingredients>();
        image = new Image(getClass().getResource("/sample/juice.jpg").toURI().toString());
        drinkItems.add(new MenuList("Juice", juice, 0.75, image));
        juice.add(new Ingredients(("Apple")));
        juice.add(new Ingredients(("Orange")));
        juice.add(new Ingredients(("Cranberry")));

        ArrayList<Ingredients> tea = new ArrayList<Ingredients>();
        image = new Image(getClass().getResource("/sample/Tea.jpg").toURI().toString());
        drinkItems.add(new MenuList("Tea", tea, 1.25, image));
        tea.add(new Ingredients(("Sweetened")));
        tea.add(new Ingredients(("Unsweetened")));

        ArrayList<Ingredients> coffee = new ArrayList<Ingredients>();
        image = new Image(getClass().getResource("/sample/coffee.JPG").toURI().toString());
        drinkItems.add(new MenuList("Coffee", coffee, 1.50, image));
        milk.add(new Ingredients(("Black")));
        milk.add(new Ingredients(("Latte")));
        milk.add(new Ingredients(("Espresso")));
        milk.add(new Ingredients(("Frappe")));

        ArrayList<Ingredients> cider = new ArrayList<Ingredients>();
        image = new Image(getClass().getResource("/sample/Cider.jpg").toURI().toString());
        drinkItems.add(new MenuList("Sandwich", cider, 1.25, image));
        cider.add(new Ingredients(("Apple")));

        ArrayList<Ingredients> water = new ArrayList<Ingredients>();
        image = new Image(getClass().getResource("/sample/Water.jpg").toURI().toString());
        drinkItems.add(new MenuList("Water", water, 0, image));

    }


    public void display() throws URISyntaxException {

        Label quantity = new Label("Quantity:");
        quantity.setFont(Font.font("Cambria", 30));

        Label items = new Label("Our Food Options:");
        items.setFont(Font.font("Cambria", 30));

        Label imageTitle = new Label("          ~~~~~~~~~~~~~");
        imageTitle.setFont(Font.font("Cambria", 30));

        Label buttonLabel = new Label("Details Here:     ");
        buttonLabel.setFont(Font.font("Cambria", 30));

        //Title section
        HBox titles = new HBox();
        titles.getChildren().addAll(quantity, items, imageTitle, buttonLabel);
        titles.setSpacing(210);
        titles.setPadding(new Insets(10,10,2,50));

        mainPaneInScroll.getChildren().addAll(titles);

        vertical = new VBox();

        VBox priceBox;
        VBox itemBox;
        VBox imageBox;
        HBox horizontal;

        ArrayList<Text> flavorsFood = new ArrayList<Text>();
        ArrayList<Text> price = new ArrayList<Text>();

        Spinner<Integer> spinner;
        SpinnerValueFactory<Integer> valueFactory;


        //format loop to display food items
        for (int i = 0; i < foodItems.size(); i++) {

            //get current index's info
            flavorsFood.add(new Text(foodItems.get(i).toString()));
            flavorsFood.get(i).setFont(Font.font("Cambria", 25));

            price.add(new Text(foodItems.get(i).printPrice()));
            price.get(i).setFont(Font.font("Cambria", 25));


            //set spinner (quantity)
            spinner = new Spinner<Integer>();
            valueFactory = //
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0);
            spinner.setValueFactory(valueFactory);


            //ingredients button
            Button ingredients = new Button("Ingredients");
            ingredients.setFont(Font.font("Cambria",20));
            ingredients.setShape(new Circle(1,1,1));
            ingredients.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));


            //price
            priceBox = new VBox();
            priceBox.getChildren().addAll(ingredients,price.get(i));
            priceBox.setSpacing(5);

            //items
            itemBox = new VBox();
            itemBox.getChildren().addAll(flavorsFood.get(i));
            itemBox.setMinWidth(300);

            //picture of items
            imageBox = new VBox();

            Image image = foodItems.get(i).getPicture();
            ImageView imageView = new ImageView(image);

            imageBox.getChildren().addAll(imageView);
            imageBox.setMinWidth(260);


            //set up each line going horizontal
            horizontal = new HBox();
            horizontal.getChildren().addAll(spinner, itemBox, imageBox, priceBox);
            horizontal.setSpacing(195);
            horizontal.setPadding(new Insets(10,20,10,90));


            //add new line to box
            vertical.getChildren().add(horizontal);

        }


        //reset up
        ArrayList<Text> flavorsDrinks = new ArrayList<Text>();
        ArrayList<Text> priceDrinks = new ArrayList<Text>();

        Spinner<Integer> spinner1;
        SpinnerValueFactory<Integer> valueFactory1;


        //format loop to display drink items
        for (int i = 0; i < drinkItems.size(); i++) {

            flavorsDrinks.add(new Text(drinkItems.get(i).toString()));
            flavorsDrinks.get(i).setFont(Font.font("Cambria", 25));

            priceDrinks.add(new Text(drinkItems.get(i).printPrice()));
            priceDrinks.get(i).setFont(Font.font("Cambria", 25));


            //set spinner (quantity)
            spinner1 = new Spinner<Integer>();
            valueFactory1 = //
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0);
            spinner1.setValueFactory(valueFactory1);


            //ingredients button
            Button ingredients = new Button("Ingredients");
            ingredients.setFont(Font.font("Cambria",20));
            ingredients.setShape(new Circle(1,1,1));
            ingredients.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));


            //price
            priceBox = new VBox();
            priceBox.getChildren().addAll(ingredients,priceDrinks.get(i));
            priceBox.setSpacing(5);

            //items
            itemBox = new VBox();
            itemBox.getChildren().addAll(flavorsDrinks.get(i));
            itemBox.setMinWidth(300);

            //picture of items
            imageBox = new VBox();

            Image image = drinkItems.get(i).getPicture();
            ImageView imageView = new ImageView(image);

            imageBox.getChildren().addAll(imageView);
            imageBox.setMinWidth(260);


            //set up each line going horizontal
            horizontal = new HBox();
            horizontal.getChildren().addAll(spinner1, itemBox, imageBox, priceBox);
            horizontal.setSpacing(195);
            horizontal.setPadding(new Insets(10,20,10,90));


            //add new line to box
            vertical.getChildren().add(horizontal);

        }

        //pane in scroll, headings
        mainPaneInScroll.getChildren().addAll(vertical);

    }



    private class searchButtonHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent buttonClick) {

            ArrayList<MenuList> search = new ArrayList<MenuList>();
            ArrayList<MenuList> found = new ArrayList<MenuList>();
            ArrayList<MenuList> found1 = new ArrayList<MenuList>();

            if (searchTextField.getText().equals("")) {

                mainPaneInScroll.getChildren().clear();
                foodItems.clear();
                drinkItems.clear();

                try {

                    food();
                    drink();
                    display();

                }

                catch (URISyntaxException e) {

                    e.printStackTrace();

                }

            }

            else {

                search = foodItems;

                for (int i = 0; i < search.size(); i++) {

                    if (search.get(i).toString().contains(searchTextField.getText())) {

                        found.add(search.get(i));

                    }
                }

                foodItems.clear();
                foodItems = found;

               
                search = drinkItems;


                for (int i = 0; i < search.size(); i++) {

                    if (search.get(i).toString().contains(searchTextField.getText())) {

                        found1.add(search.get(i));

                    }
                }

                drinkItems.clear();
                drinkItems = found1;


                mainPaneInScroll.getChildren().clear();

                try {
                    display();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }

            }


        }


    }

}
