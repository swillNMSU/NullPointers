package src;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI extends Application {

    


    Stage ps;

    Scene mainMenu, editSc;

    double width = 500, height = 600; // global sizes for scenes.

    // debugging feilds, switch to false to view the gui in full.
    final boolean debug = true;
    Scene currScene;

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
        ps = primaryStage;
        initializeScenes();

        primaryStage.setTitle("Welcome");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

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

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Sign in button pressed");
            }
        });

        mainMenu = new Scene(grid, 300, 275);
        if (debug) {
            currScene = editSc;
            primaryStage.setScene(currScene);
        }
        else primaryStage.setScene(mainMenu);
        primaryStage.show();
    }

    public void initializeScenes(){
        
        
        //#region EditScene
        
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
        Label ownerName = new Label("Name:");
        grid.add(ownerName, 0, 1);

        TextField ownerTextField = new TextField();
        grid.add(ownerTextField, 1, 1);

        Button editBtn0 = new Button("Edit");
        HBox editHB0 = new HBox(10);
        editHB0.setAlignment(Pos.CENTER_LEFT);
        editHB0.getChildren().add(editBtn0);
        grid.add(editHB0, 3, 1);
        //#endregion

        //#region Address
        Label addr = new Label("Address:");
        grid.add(addr, 0, 2);

        TextField addressTextField = new TextField();
        grid.add(addressTextField, 1, 2);

        Button editBtn1 = new Button("Edit");
        HBox editHB1 = new HBox(10);
        editHB1.setAlignment(Pos.CENTER_LEFT);
        editHB1.getChildren().add(editBtn1);
        grid.add(editHB1, 3, 2);

        //#endregion

        //#region NumPets
        Label numPets = new Label("Number of Pets:");
        grid.add(numPets, 0, 3);

        TextField numPeTextField = new TextField();
        grid.add(numPeTextField, 1, 3);

        Button editBtn2 = new Button("Edit");
        HBox editHB2 = new HBox(10);
        editHB2.setAlignment(Pos.CENTER_LEFT);
        editHB2.getChildren().add(editBtn2);
        grid.add(editHB2, 3, 3);

        //#endregion

        //#region Strikes
        Label numStrikes = new Label("Strikes:");
        grid.add(numStrikes, 0, 4);

        TextField numStrikesTextField = new TextField();
        grid.add(numStrikesTextField, 1, 4);

        Button editBtn3 = new Button("Edit");
        HBox editHB3 = new HBox(10);
        editHB3.setAlignment(Pos.CENTER_LEFT);
        editHB3.getChildren().add(editBtn3);
        grid.add(editHB3, 3, 4);

        //#endregion

        //#region Withdrawls
        Label numWithdrawls = new Label("Withdrawls:");
        grid.add(numWithdrawls, 0, 5);

        TextField numWithdrawlsTextField = new TextField();
        grid.add(numWithdrawlsTextField, 1, 5);

        Button editBtn4 = new Button("Edit");
        HBox editHB4 = new HBox(10);
        editHB4.setAlignment(Pos.CENTER_LEFT);
        editHB4.getChildren().add(editBtn4);
        grid.add(editHB4, 3, 5);

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

        saveBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) { // IF there is at least a name entered.
                if (ownerTextField.getText() != null){
                    Owner tempOwn = new Owner();
                    tempOwn.setName(ownerTextField.getText());
                    tempOwn.setAddress(addressTextField.getText());
                    tempOwn.setNumPets(Integer.parseInt(numPeTextField.getText()));
                    tempOwn.setStrikes(Integer.parseInt(numStrikesTextField.getText()));
                    System.out.println(tempOwn);
                }


                
                ownerTextField.getText();

                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Saved");
            }
        });

        editSc = new Scene(grid, 300, 275);
        ps.setScene(editSc);
        ps.show();

        //#endregion
    }
}