package src;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI extends Application {

    
    Owner testOwner = new Owner("tFName tLName");
    int count = 0;

    Validator dv = new Validator();
    Stage ps;

    ScrollPane sp = new ScrollPane();

    Scene mainMenu, editSc;

    double width = 500, height = 600; // global sizes for scenes.
    int errNum = 0;//maybe not necessary

    // debugging feilds, switch to false to view the gui in full.
    final boolean debug = true;
    Scene currScene = mainMenu;

    Text nameErr, addressErr, numPetErr, strikesErr, withdrawlErr = new Text();
    

    public static void main(String[] args) {
        launch(args);
    }
/**
 * This sets the scene for the main menu.
 * FYI I'm using regions to make scrolling easier. In case you havent encountered them, 
 * they can be collapsed and expanded.
 */
    @Override
    public void start(Stage primaryStage) {
        
        // #region test owner - delete when done
        testOwner.setAddress("1115 Branson Ave");
        testOwner.setIncomeProof(true);
        testOwner.setNumPets(2);
        testOwner.setNumRecieved(1);
        testOwner.setIsFixed(true);
        testOwner.setStrikes(0);
        //#endregion
        
        ps = primaryStage;
        initializeScenes();

        VBox root = new VBox();
        //TableView<TableObject> table = new TODO: do this shit next.

        //#region mainMenu
        // Begin main Menu initialization
        //primaryStage.setTitle("Welcome");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        sp.setPrefSize(580, 500);
        //sp.setContent();
        grid.add(sp, 5, 8);

        // add scrollpane
        
        

        Text scenetitle = new Text("Search");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button btn = new Button("Search");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        // I've added a temporary button to get the the Edit scene I'm building.
        Button tEdit = new Button("tEdit");
        HBox tEditHB = new HBox(10);
        tEditHB.setAlignment(Pos.BOTTOM_RIGHT);
        tEditHB.getChildren().add(tEdit);
        grid.add(tEditHB, 1, 5);

        tEdit.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent e){
                primaryStage.setScene(editSc);
            }
        });

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        GridPane actions = new GridPane();
        Label test = new Label("Test");
        actions.add(test, 1, 4);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Sign in button pressed");
                Scene newScene = new Scene(actions, 300, 275);
                primaryStage.setScene(newScene);
                primaryStage.show();
            }
        });

        mainMenu = new Scene(grid, 300, 275);
//#endregion

        if (debug) {
            currScene = mainMenu;
            primaryStage.setScene(currScene);
        }
        else primaryStage.setScene(mainMenu);
        primaryStage.show();
    }

    public void initializeScenes(){
        
        //#region EditScene
        Owner ow = testOwner;

        ps.setTitle("Edit Owner Information");
        ps.setHeight(800);
        ps.setWidth(1000);
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));



        Text scenetitle = new Text("Edit Owner Information");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        //#region name
        Label ownerNameL = new Label("Name:");
        grid.add(ownerNameL, 0, 1);

        Label ownerName = new Label(ow.getName());
        grid.add(ownerName, 1, 1);

        TextField ownerTextField = new TextField(ow.getName()); 
        

        Button editBtn0 = new Button("Edit");
        HBox editHB0 = new HBox(10);
        editHB0.setAlignment(Pos.CENTER_LEFT);
        editHB0.getChildren().add(editBtn0);
        grid.add(editHB0, 3, 1);

        editBtn0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                ownerName.setVisible(false);
                grid.add(ownerTextField, 1, 1);
                // select text field
                //highlight text field
            }
        });
        //#endregion

        //#region Address
        Label addrL = new Label("Address:");
        grid.add(addrL, 0, 2);

        Label ownerAddr = new Label(ow.getAddress());
        grid.add(ownerAddr, 1, 2);

        TextField addressTextField = new TextField(ow.getAddress());

        Button editBtn1 = new Button("Edit");
        HBox editHB1 = new HBox(10);
        editHB1.setAlignment(Pos.CENTER_LEFT);
        editHB1.getChildren().add(editBtn1);
        grid.add(editHB1, 3, 2);

        editBtn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                ownerAddr.setVisible(false);
                grid.add(addressTextField, 1, 2);
                // select text field
                //highlight text field
            }
        });

        //#endregion

        //#region NumPets
        Label numPetsL = new Label("Number of Pets:");
        grid.add(numPetsL, 0, 3);

        Label ownerNumPets = new Label(String.valueOf(ow.getNumPets()));
        grid.add(ownerNumPets, 1, 3);

        TextField numPetTextField = new TextField(String.valueOf(ow.getNumPets()));

        Button editBtn2 = new Button("Edit");
        HBox editHB2 = new HBox(10);
        editHB2.setAlignment(Pos.CENTER_LEFT);
        editHB2.getChildren().add(editBtn2);
        grid.add(editHB2, 3, 3);

        editBtn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                ownerNumPets.setVisible(false);
                grid.add(numPetTextField, 1, 3);
                // select text field
                //highlight text field
            }
        });

        //#endregion

        //#region Strikes
        Label numStrikesL = new Label("Strikes:");
        grid.add(numStrikesL, 0, 4);

        Label ownerStrikes = new Label(String.valueOf(ow.getStrikes()));
        grid.add(ownerStrikes, 1, 4);

        TextField numStrikesTextField = new TextField(String.valueOf(ow.getStrikes()));

        Button editBtn3 = new Button("Edit");
        HBox editHB3 = new HBox(10);
        editHB3.setAlignment(Pos.CENTER_LEFT);
        editHB3.getChildren().add(editBtn3);
        grid.add(editHB3, 3, 4);

        editBtn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                ownerStrikes.setVisible(false);
                grid.add(numStrikesTextField, 1, 4);
                // select text field
                //highlight text field
            }
        });

        //#endregion

        //#region Withdrawls
        Label numWithdrawlsL = new Label("Withdrawls:");
        grid.add(numWithdrawlsL, 0, 5);

        Label ownerWithdrawls = new Label(String.valueOf(ow.getNumRecieved()));
        grid.add(ownerWithdrawls, 1, 5);

        TextField numWithdrawlsTextField = new TextField(String.valueOf(ow.getNumRecieved()));

        Button editBtn4 = new Button("Edit");
        HBox editHB4 = new HBox(10);
        editHB4.setAlignment(Pos.CENTER_LEFT);
        editHB4.getChildren().add(editBtn4);
        grid.add(editHB4, 3, 5);

        editBtn4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                ownerWithdrawls.setVisible(false);
                grid.add(numWithdrawlsTextField, 1, 5);
                // select text field
                //highlight text field
            }
        });


        //#endregion
        
        Button saveBtn = new Button("Save");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(saveBtn);
        grid.add(hbBtn, 1, 7);


        //back button
        Button backBtn = new Button("Back");
        HBox bHitB = new HBox(10);
        bHitB.setAlignment(Pos.BOTTOM_LEFT);
        bHitB.getChildren().add(backBtn);
        grid.add(bHitB, 0, 7);

        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                System.out.println("Back to Main menu.");
                ps.setScene(mainMenu);
            }
        });

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        // errors
        nameErr = displayErr(grid, "Name not valid", 5, 1);
        addressErr = displayErr(grid, "Invalid address", 5, 2);
        numPetErr = displayErr(grid, "Invalid", 5, 3);
        strikesErr = displayErr(grid, "Invalid", 5, 4);
        withdrawlErr = displayErr(grid, "Invalid", 5, 5);
                



        //Data Validation goes in here.
        saveBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) { // IF there is at least a name entered.
                Owner tempOwn = new Owner();

                // nameErr.setDisable(true);
                // addressErr.setDisable(true);
                // numPetErr.setVisible(true);
                // strikesErr.setVisible(true);
                // withdrawlErr.setVisible(true);
                
                if (ownerTextField.getText() != ow.getName()){
                    if (Write.newName(ownerTextField.getText())){
                        nameErr.setVisible(false);
                        tempOwn.setName(ownerTextField.getText());
                    }
                    else {
                        nameErr.setVisible(true);
                        System.err.println("Name field not valid.");
                    }
                }
                    
                if (addressTextField.getText() != ow.getAddress()) {
                    if(Write.newAddress(addressTextField.getText())){
                        addressErr.setVisible(false);
                        tempOwn.setAddress(addressTextField.getText());
                    }
                    else {
                        addressErr.setVisible(true);
                        System.err.println("Address field not valid");
                    }
                        
                }

                if (numPetTextField.getText() != String.valueOf(ow.getNumPets())){
                    if (!dv.checkNumPets(numPetTextField.getText())) {
                        numPetErr.setVisible(false);
                        tempOwn.setNumPets(Integer.parseInt(numPetTextField.getText()));

                    }
                    else {        
                        numPetErr.setVisible(true);
                    }
                }

                if (numStrikesTextField.getText() != String.valueOf(ow.getStrikes())){
                    if (!dv.isNegative(numStrikesTextField.getText())) {
                        strikesErr.setVisible(false);
                        tempOwn.setStrikes(Integer.parseInt(numStrikesTextField.getText()));
                    }
                    else {
                        
                        strikesErr.setVisible(false);
                    }
                }

                if (numWithdrawlsTextField.getText() != String.valueOf(ow.getNumRecieved())){
                    if (!dv.isNegative(numWithdrawlsTextField.getText())) {
                        withdrawlErr.setVisible(false);
                        tempOwn.setStrikes(Integer.parseInt(numWithdrawlsTextField.getText()));
                    }
                    else {
                        withdrawlErr.setVisible(false);
                    }
                }
                    
                System.out.println(tempOwn);
                // if check and add owner
                
                ownerTextField.getText();

                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Saved " + count++);
            }
        });

        editSc = new Scene(grid, 300, 275);
        ps.setScene(editSc);
        ps.show();

        //#endregion
    }

    public Text displayErr(GridPane gr, String err, int width, int height){
        Text er = new Text();
        er.setVisible(false);
        er.setFill(Color.FIREBRICK);
        er.setText(err);
        gr.add(er, width, height);
        return er;
    }


}