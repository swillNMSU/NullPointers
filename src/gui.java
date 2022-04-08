package src;

import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    Owner selectedOwner;
    Stage ps;
    Scene mainMenu, editSc, addSc;
    ScrollPane sp = new ScrollPane();
    double width = 500, height = 600; // global sizes for scenes.

    // debugging feilds
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

         //#region mainMenu

         //#region Table
        TableView<Owner> owTable = new TableView<>();
        TableColumn<Owner, String> column1 = new TableColumn<>("Name");
        TableColumn<Owner, String> column2 = new TableColumn<>("Address");
        TableColumn<Owner, String> column3 = new TableColumn<>("Pets");
        TableColumn<Owner, String> column4 = new TableColumn<>("Strikes");
        TableColumn<Owner, String> column5 = new TableColumn<>("Withdrawls");
        TableColumn<Owner, Boolean> column6 = new TableColumn<>("Fixed");

        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        column2.setCellValueFactory(new PropertyValueFactory<>("address"));
        column3.setCellValueFactory(new PropertyValueFactory<>("numPets"));
        column4.setCellValueFactory(new PropertyValueFactory<>("strikes"));
        column5.setCellValueFactory(new PropertyValueFactory<>("numRecieved"));
        column6.setCellValueFactory(new PropertyValueFactory<>("isFixed"));

    //     private String name, address;
    // public int numPets, strikes, numRecieved;
    // public boolean isFixed, incomeProof;

        owTable.getColumns().add(column1);
        owTable.getColumns().add(column2);
        owTable.getColumns().add(column3);
        owTable.getColumns().add(column4);
        owTable.getColumns().add(column5);
        owTable.getColumns().add(column6);

        Read.readCSV();
        for (Owner ows : Driver.owners)
            owTable.getItems().add(ows);

        VBox vBox = new VBox(owTable);
        vBox.setPrefSize(580, 700);

        //#endregion

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        sp.setFitToWidth(true);
        sp.setFitToHeight(true);

        sp.setPrefSize(580, 400);
        sp.setContent(vBox);
        grid.add(sp, 2, 8);

        Text scenetitle = new Text("Patron List");
        grid.setAlignment(Pos.CENTER);
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 1, 0, 2, 1);

        // Button btn = new Button("Search");
        // HBox hbBtn = new HBox(10);
        // hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        // hbBtn.getChildren().add(btn);
        // grid.add(hbBtn, 1, 4);

        Button addBtn = new Button("Add New");
        HBox tAddHB = new HBox(10);
        tAddHB.setAlignment(Pos.BOTTOM_RIGHT);
        tAddHB.getChildren().add(addBtn);
        grid.add(tAddHB, 1, 5);

        Label searchL = new Label("Search");
        grid.add(searchL, 1, 7);

        TextField searchTextField = new TextField();
        grid.add(searchTextField, 2, 7);

        searchTextField.textProperty().addListener(new ChangeListener<String>()  {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (searchTextField.getText() == "")
                    for (Owner ows : Driver.owners)
                        owTable.getItems().add(ows);
                List<Owner> query = Read.searchByName(searchTextField.getText());
                owTable.getItems().clear();
                for (Owner ows : query)
                    owTable.getItems().add(ows);

            }
        });

        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                primaryStage.setScene(editSc);
            }
        });


        Button editButton = new Button("Edit");
        HBox editHb = new HBox(10);
        editHb.setAlignment(Pos.BOTTOM_RIGHT);
        editHb.getChildren().add(editButton);
        grid.add(editHb, 3, 8);

        editButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                selectedOwner = owTable.getSelectionModel().getSelectedItem();
                //error check before swinthing scenes.
                System.out.println(selectedOwner);
                ps.setScene(editSc);
                initializeScenes();
            }
        });


        mainMenu = new Scene(grid, 300, 275);
        if (debug) {
            currScene = editSc;
            primaryStage.setScene(mainMenu);
        }
        else primaryStage.setScene(mainMenu);
        primaryStage.show();
    }

    public void initializeScenes(){
        Owner ow = new Owner("");

        //#region EditScene
        if (selectedOwner != null) {
            System.out.println(selectedOwner);
            ow = selectedOwner;
        }

        ps.setTitle("Patron List");
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

        TextField numPeTextField = new TextField(String.valueOf(ow.getNumPets()));

        Button editBtn2 = new Button("Edit");
        HBox editHB2 = new HBox(10);
        editHB2.setAlignment(Pos.CENTER_LEFT);
        editHB2.getChildren().add(editBtn2);
        grid.add(editHB2, 3, 3);

        editBtn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                ownerNumPets.setVisible(false);
                grid.add(numPeTextField, 1, 3);
                // select text field
                //highlight text field
            }
        });

        //#endregion

        //#region Strikes
        Label numStrikesL = new Label("Strikes:");
        grid.add(numStrikesL, 0, 4);

        Label ownerStrikes = new Label(String.valueOf(ow.getStrikes()));

        TextField numStrikesTextField = new TextField();
        grid.add(numStrikesTextField, 1, 4);

        Button editBtn3 = new Button("Edit");
        HBox editHB3 = new HBox(10);
        editHB3.setAlignment(Pos.CENTER);
        editHB3.getChildren().add(editBtn3);
        grid.add(editHB3, 3, 4);

        editBtn2.setOnAction(new EventHandler<ActionEvent>() {
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
        
        //#region save button
        Button saveBtn = new Button("Save");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(saveBtn);
        grid.add(hbBtn, 1, 7);
        //#endregion
        //#region back button
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
        //#endregion
        
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

    public Text displayErr(GridPane gr, String err, int width, int height){
        Text er = new Text();
        er.setVisible(false);
        er.setFill(Color.FIREBRICK);
        er.setText(err);
        gr.add(er, width, height);
        return er;
    }

}


