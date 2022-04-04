package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;


public class Menu extends BorderPane {

    private ArrayList<MenuList> foodItems;
    private ArrayList<MenuList> drinkItems;
    
    
    private ArrayList<Account> accountList;
	private Account logIn;
	private ArrayList<MenuList> shoppingCart; 
	private ArrayList<CouponList> couponList;

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
    private Button ingredientsButton;


    Stage mainStage;

    Ingredients currentIngredient;




    public Menu(Stage mainStage, ArrayList<Account> accountList, Account logIn, ArrayList<MenuList> shoppingCart, ArrayList<CouponList> couponList) throws FileNotFoundException, URISyntaxException {


        this.mainStage = mainStage;
        this.accountList = accountList;
        this.shoppingCart = shoppingCart;
        this.logIn = logIn;
        //main box
        mainPaneInScroll = new VBox();
        mainPaneInScroll.setMinWidth(1000);


        //fill item lists
        foodItems = new ArrayList<MenuList>();
        drinkItems = new ArrayList<MenuList>();
        this.couponList = couponList;

        food();
        drink();


        //buttons
        addFoodButton = new Button("Add Meals");
        checkOutButton = new Button("Go to Checkout");
        checkOutButton.setOnAction(new checkOutButtonHandler());
        searchButton = new Button("Search");
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
        scrollPane.setMaxHeight(800);


        //search pane
        selectAndSearchPane = new HBox(searchButton, searchTextField);
        selectAndSearchPane.setSpacing(20);




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
        accountButtonBox.setSpacing(900);
        accountButtonBox.setPadding(new Insets(10,0, 15, 50));

        this.setTop(menuTitle);
        menuTitle.setPadding(new Insets(10, 0,0,900));

        menuTitle.setFont(Font.font("Cambria", 42));
        endingLabel.setFont(Font.font("Cambria", 30));


        //bottom
        HBox bottom = new HBox(addFoodButton, endingLabel, checkOutButton);
        bottom.setSpacing(570);

        addFoodButton.setFont(Font.font("Cambria", 20));
        addFoodButton.setShape(new Circle(1,1,1));
        addFoodButton.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        checkOutButton.setFont(Font.font("Cambria", 20));
        checkOutButton.setShape(new Circle(1,1,1));
        checkOutButton.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        bottom.setPadding(new Insets(5,0,0,5));

        //middle
        VBox container1 = new VBox(accountButtonBox, scrollPane, bottom);

        HBox container2 = new HBox(swirlsLeft, container1, swirlsRight);

        container2.setPadding(new Insets(0,0,0,10));
        container2.setSpacing(20);

        this.setCenter(container2);

    }



    public void food() throws URISyntaxException {

        //breads
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

        foodItems.add(new MenuList("Bread Loafs", bread, 2.00, image, "No Button"));


        //pretzels
        ArrayList<Ingredients> pretzel = new ArrayList<Ingredients>();

        image = new Image(getClass().getResource("/sample/Pretzel.jpg").toURI().toString());

        pretzel.add(new Ingredients("Salt"));
        pretzel.get(0).addItem("Salt");
        pretzel.get(0).addItem("Butter");
        pretzel.get(0).addItem("Flour");
        pretzel.get(0).addItem("Baker's yeast");
        pretzel.get(0).addItem("Brown Sugar");
        pretzel.get(0).addItem("Egg");
        pretzel.get(0).addItem("Sugar");

        foodItems.add(new MenuList("Pretzel", pretzel, 2.50, image, "No Button"));


        //scones
        ArrayList<Ingredients> scones = new ArrayList<Ingredients>();

        image = new Image(getClass().getResource("/sample/Scone.jpg").toURI().toString());

        scones.add(new Ingredients("Blueberry"));
        scones.get(0).addItem("Cream");
        scones.get(0).addItem("Butter");
        scones.get(0).addItem("Flour");
        scones.get(0).addItem("Baking Powder");
        scones.get(0).addItem("Egg");
        scones.get(0).addItem("Sugar");
        scones.get(0).addItem("Salt");
        scones.get(0).addItem("Blueberry");
        scones.get(0).addItem("Vanilla Extract");
        scones.get(0).addItem("Cinnamon");

        scones.add(new Ingredients("Cinnamon"));
        scones.get(1).addItem("Cream");
        scones.get(1).addItem("Butter");
        scones.get(1).addItem("Flour");
        scones.get(1).addItem("Baking Powder");
        scones.get(1).addItem("Egg");
        scones.get(1).addItem("Salt");
        scones.get(1).addItem("Sugar");
        scones.get(1).addItem("Vanilla Extract");
        scones.get(1).addItem("Cinnamon");

        foodItems.add(new MenuList("Scones", scones, 1.25, image, "No Button"));


        //muffins
        ArrayList<Ingredients> Muffins = new ArrayList<Ingredients>();

        image = new Image(getClass().getResource("/sample/Muffin.jpg").toURI().toString());

        Muffins.add(new Ingredients("Blueberry"));
        Muffins.get(0).addItem("Flour");
        Muffins.get(0).addItem("Sugar");
        Muffins.get(0).addItem("Baking Powder");
        Muffins.get(0).addItem("Salt");
        Muffins.get(0).addItem("Vegetable Oil");
        Muffins.get(0).addItem("Egg");
        Muffins.get(0).addItem("Milk");
        Muffins.get(0).addItem("Blueberries");
        Muffins.get(0).addItem("Ground Cinnamon");

        Muffins.add(new Ingredients("Lemon Poppy Seed"));
        Muffins.get(1).addItem("Flour");
        Muffins.get(1).addItem("Sugar");
        Muffins.get(1).addItem("Poppy Seeds");
        Muffins.get(1).addItem("Baking Powder");
        Muffins.get(1).addItem("Baking Soda");
        Muffins.get(1).addItem("Salt");
        Muffins.get(1).addItem("Eggs");
        Muffins.get(1).addItem("Lemon Juice");
        Muffins.get(1).addItem("Lemon Zest");
        Muffins.get(1).addItem("Butter");
        Muffins.get(1).addItem("Yogurt");

        Muffins.add(new Ingredients("Chocolate Chip"));
        Muffins.get(2).addItem("Flour");
        Muffins.get(2).addItem("Sugar");
        Muffins.get(2).addItem("Baking Powder");
        Muffins.get(2).addItem("Salt");
        Muffins.get(2).addItem("Milk");
        Muffins.get(2).addItem("Vegetable Oil");
        Muffins.get(2).addItem("Egg");
        Muffins.get(2).addItem("Chocolate Chip");
        Muffins.get(2).addItem("Brown Sugar");

        foodItems.add(new MenuList("Muffins", Muffins, 4.50, image, "No Button"));


        //cake
        ArrayList<Ingredients> cake = new ArrayList<Ingredients>();

        image = new Image(getClass().getResource("/sample/Cupcake.jpg").toURI().toString());
        cake.add(new Ingredients("Chocolate"));
        cake.get(0).addItem("Cocoa Powder");
        cake.get(0).addItem("Flour");
        cake.get(0).addItem("Sugar");
        cake.get(0).addItem("Baking Soda");
        cake.get(0).addItem("Salt");
        cake.get(0).addItem("Egg");
        cake.get(0).addItem("Vegetable Oil");
        cake.get(0).addItem("Sour Cream");
        cake.get(0).addItem("Vanilla Extract");

        cake.add(new Ingredients("Vanilla"));
        cake.get(1).addItem("Butter");
        cake.get(1).addItem("Flour");
        cake.get(1).addItem("Baking Powder");
        cake.get(1).addItem("Salt");
        cake.get(1).addItem("Sugar");
        cake.get(1).addItem("Milk");
        cake.get(1).addItem("Vanilla Extract");

        cake.add(new Ingredients("Red Velvet"));
        cake.get(2).addItem("Flour");
        cake.get(2).addItem("Cocoa Powder");
        cake.get(2).addItem("Baking Soda");
        cake.get(2).addItem("Salt");
        cake.get(2).addItem("Butter");
        cake.get(2).addItem("Eggs");
        cake.get(2).addItem("Granulated Sugar");
        cake.get(2).addItem("Red Food Color");
        cake.get(2).addItem("Vegetable Oil");
        cake.get(2).addItem("Vanilla Extract");
        cake.get(2).addItem("White Vinegar");
        cake.get(2).addItem("Buttermilk");

        foodItems.add(new MenuList("Cake Slice or Cupcake", cake, 7.00, image, "No Button"));


        //cookies
        ArrayList<Ingredients> cookies = new ArrayList<Ingredients>();

        image = new Image(getClass().getResource("/sample/Cookies.jpg").toURI().toString());

        cookies.add(new Ingredients("Frosted Sugar"));
        cookies.get(0).addItem("Vanilla Frosting");
        cookies.get(0).addItem("Butter");
        cookies.get(0).addItem("Graduated Sugar");
        cookies.get(0).addItem("Vanilla Extract");
        cookies.get(0).addItem("Flour");
        cookies.get(0).addItem("Baking Powder");
        cookies.get(0).addItem("Baking Soda");
        cookies.get(0).addItem("Salt");

        cookies.add(new Ingredients("Chocolate Chip"));
        cookies.get(1).addItem("Flour");
        cookies.get(1).addItem("Baking Soda");
        cookies.get(1).addItem("Salt");
        cookies.get(1).addItem("Butter");
        cookies.get(1).addItem("Granulated Sugar");
        cookies.get(1).addItem("Brown Sugar");
        cookies.get(1).addItem("Egg");
        cookies.get(1).addItem("Vanilla");
        cookies.get(1).addItem("Chocolate chips");
        cookies.get(1).addItem("Nuts");

        cookies.add(new Ingredients("Peanut Butter"));
        cookies.get(2).addItem("Flour");
        cookies.get(2).addItem("Vanilla Extract");
        cookies.get(2).addItem("Butter");
        cookies.get(2).addItem("Egg");
        cookies.get(2).addItem("Peanut Butter");
        cookies.get(2).addItem("Sugar");
        cookies.get(2).addItem("Brown Sugar");
        cookies.get(2).addItem("Baking Powder");

        cookies.add(new Ingredients("M&M"));
        cookies.get(3).addItem("Flour");
        cookies.get(3).addItem("Baking Powder");
        cookies.get(3).addItem("Baking Soda");
        cookies.get(3).addItem("Salt");
        cookies.get(3).addItem("Butter");
        cookies.get(3).addItem("Granulated Sugar");
        cookies.get(3).addItem("Brown");
        cookies.get(3).addItem("Egg");
        cookies.get(3).addItem("M&M's");
        cookies.get(3).addItem("Pure Vanilla Extract");

        foodItems.add(new MenuList("Cookies", cookies, 1.50, image, "No Button"));


        //macaroons
        ArrayList<Ingredients> macaroons = new ArrayList<Ingredients>();

        image = new Image(getClass().getResource("/sample/Macaroon.jpg").toURI().toString());

        macaroons.add(new Ingredients("Pink"));
        macaroons.get(0).addItem("Egg Whites");
        macaroons.get(0).addItem("White Sugar");
        macaroons.get(0).addItem("Powdered Sugar");
        macaroons.get(0).addItem("Almond Flour");
        macaroons.get(0).addItem("Salt");
        macaroons.get(0).addItem("Cream of Tartar");
        macaroons.get(0).addItem("Pink Gel Food Coloring");
        macaroons.get(0).addItem("White Chocolate Raspberry filling");
        macaroons.get(0).addItem("Raspberry Extract");
        macaroons.get(0).addItem("Pink Food Coloring");
        macaroons.get(0).addItem("White Chocolate Chips");
        macaroons.get(0).addItem("Whipped Cream");

        macaroons.add(new Ingredients("Green"));
        macaroons.get(1).addItem("Sugar");
        macaroons.get(1).addItem("Almond Flour");
        macaroons.get(1).addItem("Matcha Powder");
        macaroons.get(1).addItem("Egg Whites");
        macaroons.get(1).addItem("SUgar");
        macaroons.get(1).addItem("Egg White Powder");
        macaroons.get(1).addItem("Green Food Coloring");
        macaroons.get(1).addItem("Matcha White Chocolate Ganache");
        macaroons.get(1).addItem("Heavy Cream");
        macaroons.get(1).addItem("White Chocolate");
        macaroons.get(1).addItem("Matcha Powder");

        macaroons.add(new Ingredients("Purple"));
        macaroons.get(2).addItem("Egg White");
        macaroons.get(2).addItem("Granulated Sugar");
        macaroons.get(2).addItem("Almond Flour");
        macaroons.get(2).addItem("Powdered Sugar");
        macaroons.get(2).addItem("Purple Food Coloring");
        macaroons.get(2).addItem("Blackberry Jam Filling");
        macaroons.get(2).addItem("Blackberries");
        macaroons.get(2).addItem("Maple Syrup");
        macaroons.get(2).addItem("Cornstrach");
        macaroons.get(2).addItem("Almond Buttercream");
        macaroons.get(2).addItem("Lemon Juice");
        macaroons.get(2).addItem("Almond Extract");
        macaroons.get(2).addItem("Milk");

        foodItems.add(new MenuList("Macaroons", macaroons, 0.50, image, "No Button"));


        //donuts
        ArrayList<Ingredients> donuts = new ArrayList<Ingredients>();

        image = new Image(getClass().getResource("/sample/Donut.jpg").toURI().toString());

        donuts.add(new Ingredients("Chocolate Sprinkled"));
        donuts.get(0).addItem("Flour");
        donuts.get(0).addItem("Sugar");
        donuts.get(0).addItem("Cocoa Powder");
        donuts.get(0).addItem("Baking Soda");
        donuts.get(0).addItem("Espresso Powder");
        donuts.get(0).addItem("Vanilla Extract");
        donuts.get(0).addItem("Egg");
        donuts.get(0).addItem("Sour Cream");
        donuts.get(0).addItem("Milk");
        donuts.get(0).addItem("Vegetable Oil");
        donuts.get(0).addItem("Chocolate Frosting:");
        donuts.get(0).addItem("White Sugar");
        donuts.get(0).addItem("Butter");
        donuts.get(0).addItem("Chocolate Chips");
        donuts.get(0).addItem("Sprinkles");

        donuts.add(new Ingredients("Vanilla Sprinkled"));
        donuts.get(1).addItem("Baking Soda");
        donuts.get(1).addItem("Nutmeg");
        donuts.get(1).addItem("Salt");
        donuts.get(1).addItem("Sugar");
        donuts.get(1).addItem("Milk");
        donuts.get(1).addItem("Sour Cream");
        donuts.get(1).addItem("Egg");
        donuts.get(1).addItem("Butter");
        donuts.get(1).addItem("Vegetable Oil");
        donuts.get(1).addItem("Vanilla Extract");
        donuts.get(1).addItem("Vanilla Glaze:");
        donuts.get(1).addItem("Milk");
        donuts.get(1).addItem("Powdered Sugar");
        donuts.get(1).addItem("Vanilla Extract");
        donuts.get(1).addItem("Sprinkles");

        donuts.add(new Ingredients("Jelly Filled"));
        donuts.get(2).addItem("Yeast");
        donuts.get(2).addItem("Milk");
        donuts.get(2).addItem("Egg");
        donuts.get(2).addItem("Sugar");
        donuts.get(2).addItem("Butter");
        donuts.get(2).addItem("Salt");
        donuts.get(2).addItem("Flour");
        donuts.get(2).addItem("Sunflower Oil");
        donuts.get(2).addItem("Raspberry Jelly");


        donuts.add(new Ingredients("Boston Creme"));
        donuts.get(3).addItem("Flour");
        donuts.get(3).addItem("Yeast");
        donuts.get(3).addItem("Granulated Sugar");
        donuts.get(3).addItem("Whole");
        donuts.get(3).addItem("Butter");
        donuts.get(3).addItem("Egg");
        donuts.get(3).addItem("Salt");
        donuts.get(3).addItem("Shortening");
        donuts.get(3).addItem("Custard:");
        donuts.get(3).addItem("Milk");
        donuts.get(3).addItem("Flour");
        donuts.get(3).addItem("Sugar");
        donuts.get(3).addItem("Egg Yolk");
        donuts.get(3).addItem("Vanilla Paste");
        donuts.get(3).addItem("Salt");
        donuts.get(3).addItem("Topping:");
        donuts.get(3).addItem("Chocolate Chip");
        donuts.get(3).addItem("Heavy Cream");
        donuts.get(3).addItem("Salt");

        donuts.add(new Ingredients("Glazed"));
        donuts.get(4).addItem("Milk");
        donuts.get(4).addItem("Yeast");
        donuts.get(4).addItem("Granulated Sugar");
        donuts.get(4).addItem("Egg");
        donuts.get(4).addItem("Butter");
        donuts.get(4).addItem("Pure Vanilla Extract");
        donuts.get(4).addItem("Ground Nutmeg");
        donuts.get(4).addItem("Salt");
        donuts.get(4).addItem("Flour");
        donuts.get(4).addItem("Vegetable Oil");
        donuts.get(4).addItem("Glaze:");
        donuts.get(4).addItem("Sugar");
        donuts.get(4).addItem("Heavy Cream");
        donuts.get(4).addItem("Pure Vanilla Extract");

        foodItems.add(new MenuList("Donuts", donuts, 1.50, image, "No Button"));


        //brownies
        ArrayList<Ingredients> brownies = new ArrayList<Ingredients>();

        image = new Image(getClass().getResource("/sample/Brownies.jpg").toURI().toString());

        brownies.add(new Ingredients("Chocolate Brownies"));
        brownies.get(0).addItem("Sugar");
        brownies.get(0).addItem("Butter");
        brownies.get(0).addItem("Coca Powder");
        brownies.get(0).addItem("Vanilla Extract");
        brownies.get(0).addItem("Eggs");
        brownies.get(0).addItem("Salt");
        brownies.get(0).addItem("Baking Powder");
        brownies.get(0).addItem("Flour");
        brownies.get(0).addItem("Chocolate Frosting");
        brownies.get(0).addItem("Chocolate Chips");

        foodItems.add(new MenuList("Brownies", brownies, 2.00, image, "No Button"));


        //flan
        ArrayList<Ingredients> flan = new ArrayList<Ingredients>();

        image = new Image(getClass().getResource("/sample/Flan.jpg").toURI().toString());

        flan.add(new Ingredients("House Special"));
        flan.get(0).addItem("Sugar");
        flan.get(0).addItem("Evaporated Milk");
        flan.get(0).addItem("Sweetened Condensed Milk");
        flan.get(0).addItem("Vanilla Extract");
        flan.get(0).addItem("Eggs");
        flan.get(0).addItem("Hope");

        foodItems.add(new MenuList("Flan", flan, 5.50, image, "No Button"));


        //pancakes
        ArrayList<Ingredients> pancakes = new ArrayList<Ingredients>();

        image = new Image(getClass().getResource("/sample/Pancakes.jpg").toURI().toString());

        pancakes.add(new Ingredients("Fluffy Pancakes"));
        pancakes.get(0).addItem("Flour");
        pancakes.get(0).addItem("Baking Powder");
        pancakes.get(0).addItem("Salt");
        pancakes.get(0).addItem("Sugar");
        pancakes.get(0).addItem("Eggs");
        pancakes.get(0).addItem("Butter");
        pancakes.get(0).addItem("Chocolate Chips");
        pancakes.get(0).addItem("Syrup");

        foodItems.add(new MenuList("Pancakes", pancakes, 9.25, image, "No Button"));


        //french toast
        ArrayList<Ingredients> frenchToast = new ArrayList<Ingredients>();

        image = new Image(getClass().getResource("/sample/FrenchToast.jpg").toURI().toString());

        frenchToast.add(new Ingredients("Regular French Toast"));
        frenchToast.get(0).addItem("Cinnamon");
        frenchToast.get(0).addItem("Nutmeg");
        frenchToast.get(0).addItem("Sugar");
        frenchToast.get(0).addItem("Butter");
        frenchToast.get(0).addItem("Eggs");
        frenchToast.get(0).addItem("Milk");
        frenchToast.get(0).addItem("Vanilla Extract");
        frenchToast.get(0).addItem("White Bread");
        frenchToast.get(0).addItem("Syrup");

        foodItems.add(new MenuList("French Toast", frenchToast, 9.25, image, "No Button"));


        //sandwich
        ArrayList<Ingredients> sandwich = new ArrayList<Ingredients>();

        image = new Image(getClass().getResource("/sample/Sandwhich.jpg").toURI().toString());

        sandwich.add(new Ingredients("Breakfast Croissant"));
        sandwich.get(0).addItem("Bread");
        sandwich.get(0).addItem("Egg");
        sandwich.get(0).addItem("Bacon");

        foodItems.add(new MenuList("Sandwich", sandwich, 8.75, image, "No Button"));

    }

    public void drink() throws URISyntaxException {

        //milk
        ArrayList<Ingredients> milk = new ArrayList<Ingredients>();

        Image image = new Image(getClass().getResource("/sample/Milk.jpg").toURI().toString());

        milk.add(new Ingredients("Whole"));
        milk.get(0).addItem("Regular Whole Milk");

        milk.add(new Ingredients("Skim"));
        milk.get(1).addItem("Regular Skim Milk");

        milk.add(new Ingredients("Almond"));
        milk.get(2).addItem("Regular Almond Milk");

        milk.add(new Ingredients("Lactaid"));
        milk.get(3).addItem("Milk but with no lactose");

        milk.add(new Ingredients("Chocolate (Hot or Cold)"));
        milk.get(4).addItem("Regular (Chosen) Milk");
        milk.get(4).addItem("Chocolate Powder");

        milk.add(new Ingredients("Strawberry"));
        milk.get(5).addItem("Regular (Chosen) Milk");
        milk.get(5).addItem("Strawberry Syrup");

        drinkItems.add(new MenuList("Milk", milk, 1.00, image, "No Button"));


        //juice
        ArrayList<Ingredients> juice = new ArrayList<Ingredients>();

        image = new Image(getClass().getResource("/sample/juice.jpg").toURI().toString());

        juice.add(new Ingredients("Apple"));
        juice.get(0).addItem("Apples");
        juice.get(0).addItem("Sugar");
        juice.get(0).addItem("Water");

        juice.add(new Ingredients("Orange"));
        juice.get(1).addItem("Orange");
        juice.get(1).addItem("Sugar");

        juice.add(new Ingredients("Cranberry"));
        juice.get(2).addItem("Cranberries");
        juice.get(2).addItem("Sugar");
        juice.get(2).addItem("Water");

        drinkItems.add(new MenuList("Juice", juice, 0.75, image, "No Button"));


        //tea
        ArrayList<Ingredients> tea = new ArrayList<Ingredients>();

        image = new Image(getClass().getResource("/sample/Tea.jpg").toURI().toString());

        tea.add(new Ingredients("Sweetened"));
        tea.get(0).addItem("Cool Water");
        tea.get(0).addItem("Boiling Water");
        tea.get(0).addItem("White Sugar");
        tea.get(0).addItem("Tea Bags");
        tea.get(0).addItem("White Sugar");

        tea.add(new Ingredients("Unsweetened"));
        tea.get(1).addItem("Cool Water");
        tea.get(1).addItem("Boiling Water");
        tea.get(1).addItem("White Sugar");
        tea.get(1).addItem("Tea Bags");

        drinkItems.add(new MenuList("Tea", tea, 1.25, image, "No Button"));


        //coffee
        ArrayList<Ingredients> coffee = new ArrayList<Ingredients>();

        image = new Image(getClass().getResource("/sample/coffee.JPG").toURI().toString());

        milk.add(new Ingredients("Black"));
        milk.get(0).addItem("Water");
        milk.get(0).addItem("Ground Coffee Beans");

        milk.add(new Ingredients("Latte"));
        milk.get(1).addItem("Milk");
        milk.get(1).addItem("Espresso");
        milk.get(1).addItem("Milk Foam");

        milk.add(new Ingredients(("Espresso")));
        milk.get(2).addItem("Water");
        milk.get(2).addItem("Ground Espresso Beans");

        milk.add(new Ingredients(("Frappe")));milk.get(2).addItem("Water");
        milk.get(3).addItem("Espresso");
        milk.get(3).addItem("Milk");
        milk.get(3).addItem("Ice");
        milk.get(3).addItem("Whipped Cream");
        milk.get(3).addItem("Chocolate");

        drinkItems.add(new MenuList("Coffee", coffee, 1.50, image, "No Button"));


        //cider
        ArrayList<Ingredients> cider = new ArrayList<Ingredients>();

        image = new Image(getClass().getResource("/sample/Cider.jpg").toURI().toString());

        cider.add(new Ingredients(("Apple")));
        cider.get(0).addItem("Apple");
        cider.get(0).addItem("Water");
        cider.get(0).addItem("Sugar");
        cider.get(0).addItem("Cinnamon");
        cider.get(0).addItem("Ground Allspice");

        drinkItems.add(new MenuList("Cider", cider, 1.25, image, "No Button"));;


        //water
        ArrayList<Ingredients> water = new ArrayList<Ingredients>();

        image = new Image(getClass().getResource("/sample/Water.jpg").toURI().toString());

        water.add(new Ingredients(("Cup of Water")));
        water.get(0).addItem("Water");

        drinkItems.add(new MenuList("Water", water, 0, image, "No Button"));


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


        int count = 0;

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
            ingredientsButton = new Button("Ingredients");
            ingredientsButton.setFont(Font.font("Cambria",20));
            ingredientsButton.setShape(new Circle(1,1,1));
            ingredientsButton.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            ingredientsButton.setOnAction(new ingredientsButtonHandler());

            ingredientsButton.setId(String.valueOf(count));
            String id = ingredientsButton.getId();
            foodItems.get(i).setButton(id);
            count++;


            //price
            priceBox = new VBox();
            priceBox.getChildren().addAll(ingredientsButton, price.get(i));
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
            ingredientsButton = new Button("Ingredients");
            ingredientsButton.setFont(Font.font("Cambria",20));
            ingredientsButton.setShape(new Circle(1,1,1));
            ingredientsButton.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            ingredientsButton.setOnAction(new ingredientsButtonHandler());

            ingredientsButton.setId(String.valueOf(count));
            String id = ingredientsButton.getId();
            drinkItems.get(i).setButton(id);

            count++;


            //price
            priceBox = new VBox();
            priceBox.getChildren().addAll(ingredientsButton, priceDrinks.get(i));
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

                foodItems = found;

                search = drinkItems;


                for (int i = 0; i < search.size(); i++) {

                    if (search.get(i).toString().contains(searchTextField.getText())) {

                        found1.add(search.get(i));

                    }
                }

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
    
    private class checkOutButtonHandler implements EventHandler<ActionEvent>{

    	public void handle(ActionEvent buttonClick) {


    		try {
				ReviewOrder checkOut = new ReviewOrder(mainStage, accountList, logIn, shoppingCart, couponList);
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    	}
    	 
    }



    private class ingredientsButtonHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent buttonClick) {

            Button btn = (Button) buttonClick.getSource();
            String id = btn.getId();

            ArrayList<MenuList> menuLists = new ArrayList<MenuList>();

            int index = -1;

            for (int i = 0; i < foodItems.size(); i++) {

                if (id.equals(foodItems.get(i).getButton())) {

                    menuLists = foodItems;
                    index = i;
                }
            }
            for (int i = 0; i < drinkItems.size(); i++) {

                if (id.equals(drinkItems.get(i).getButton())) {

                    menuLists = drinkItems;
                    index = i;
                }
            }

            IngredientsPane ingredientsPane = new IngredientsPane(mainStage, menuLists, index, accountList, logIn, shoppingCart, couponList);
            Scene scene = new Scene(ingredientsPane, 900, 400);

            Color color = Color.rgb(186,255,245);
            BackgroundFill backgroundFill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
            Background background = new Background(backgroundFill);
            ingredientsPane.setBackground(background);

            mainStage.setScene(scene);

        }

    }

}
