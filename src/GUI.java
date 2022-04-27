package src;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * TODO:
 *      
 *      Color each owner in table red if they dont have proof of income, as well
 *      Search by address?
 *      Display archives:
 *              Scrollpane showing available archives. Scrollpane showing 
 *
 *      export as excel sheet
 *      add withdrawl reset button
 *    
 *      Reset withdrawls at the first of every YEAR (AUGUST TO AUGUST)
 *      Add Statistics page
 *      Add Settings/Info Page
 *      Resize and clean windows to properties that fit best
 *      Color coding
 *      
 *      Click on table elemnt and have that highlighted on edit screen
 *      
 */


public class GUI extends Application {

    
    Owner selectedOwner, delOwner;
    Stage ps;
    Scene mainMenu, editSc, addSc;
    ScrollPane sp = new ScrollPane();
    double width = 500, height = 600; // global sizes for scenes.
    Validator dVal = new Validator();
    boolean addingNew = false;
    static TableView<Owner> owTable;
    boolean canSave = true;
    boolean withdrawlReset = Read.checkForReset();
    Insets insets = new Insets(20);

    Pos align = Pos.CENTER_LEFT;


    // debugging feilds
    final boolean debug = true;
    Scene currScene;
    boolean hasSaved = true;
    static boolean noSave = false;

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
        // testOwner.setAddress("1115 Branson Ave");
        // testOwner.setIncomeProof(true);
        // testOwner.setNumPets(2);
        // testOwner.setNumRecieved(1);
        // testOwner.setIsFixed(true);
        // testOwner.setStrikes(0);
        //#endregion
        
        ps = primaryStage;
        ps.setHeight(height);
        ps.setWidth(width);
        initializeScenes();

         //#region mainMenu

         //#region Table
        owTable = new TableView<>();
        
        owTable.prefHeightProperty().bind(ps.heightProperty());
        owTable.prefWidthProperty().bind(ps.widthProperty());
        TableColumn<Owner, String> column1 = new TableColumn<>("Name");
        TableColumn<Owner, String> column2 = new TableColumn<>("Address");
        TableColumn<Owner, String> column3 = new TableColumn<>("Pets");
        TableColumn<Owner, String> column4 = new TableColumn<>("Strikes");
        TableColumn<Owner, String> column5 = new TableColumn<>("Withdrawls");
        TableColumn<Owner, Boolean> column6 = new TableColumn<>("Fixed");
        TableColumn<Owner, Boolean> column7 = new TableColumn<>("Income");
        TableColumn<Owner, Boolean> column8 = new TableColumn<>("Qualified");

        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        column2.setCellValueFactory(new PropertyValueFactory<>("address"));
        column3.setCellValueFactory(new PropertyValueFactory<>("numPets"));
        column4.setCellValueFactory(new PropertyValueFactory<>("strikes"));
        column5.setCellValueFactory(new PropertyValueFactory<>("numRecieved"));
        column6.setCellValueFactory(new PropertyValueFactory<>("isFixed"));
        column7.setCellValueFactory(new PropertyValueFactory<>("incomeProof")); // change to string maybe
        column8.setCellValueFactory(new PropertyValueFactory<>("qualifiedForService"));

        owTable.getColumns().add(column1);
        owTable.getColumns().add(column2);
        owTable.getColumns().add(column3);
        owTable.getColumns().add(column4);
        owTable.getColumns().add(column5);
        owTable.getColumns().add(column6);
        owTable.getColumns().add(column7);
        owTable.getColumns().add(column8);

       // owTable.getSelectionModel().setCellSelectionEnabled(true);

        Read.readCSV();

        
        updateOwnerTable();

        
        

        
        owTable.refresh();

        VBox vBox = new VBox(owTable);
        vBox.setAlignment(Pos.CENTER_RIGHT);
        vBox.setPrefWidth(10);

        //#endregion

        GridPane grid = new GridPane();
        grid.setAlignment(align);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        
        sp.setFitToWidth(true);
        sp.setFitToHeight(true);

        sp.setPrefSize(ps.getWidth()*.7, ps.getHeight()*.6);
        sp.setContent(vBox);
        grid.add(sp, 2, 8);

        grid.setStyle("-fx-background-color: light grey;");

        Text scenetitle = new Text("Patron List");
        grid.setAlignment(align);
        scenetitle.setFont(Font.font("Telugu MN", 20));
        grid.add(scenetitle, 1, 0, 2, 1);

        Button addBtn = new Button("Add");
        HBox tAddHB = new HBox(10);
        tAddHB.setAlignment(align);
        tAddHB.getChildren().add(addBtn);
        grid.add(tAddHB, 1, 5);

        Button deleteBtn = new Button("Delete");
        HBox delHb = new HBox(10);
        delHb.setAlignment(align);
        delHb.getChildren().add(deleteBtn);
        grid.add(delHb, 1, 9);
        deleteBtn.setDisable(true);

        Button infoBtn = new Button("?");
        HBox infoHB = new HBox(10);
        infoHB.setAlignment(align);
        infoHB.getChildren().add(infoBtn);
        grid.add(infoBtn, 4, 11);

        Button archiveBtn = new Button("Archive");
        HBox archHB = new HBox(10);
        archHB.setAlignment(align);
        archHB.getChildren().add(archiveBtn);
        grid.add(archiveBtn, 3, 10);

        Label searchL = new Label("Search");
        grid.add(searchL, 1, 7);

        TextField searchTextField = new TextField();
        grid.add(searchTextField, 2, 7);

        // add listener if row is selected.

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
                addingNew = true;
                selectedOwner = null;
                ps.setScene(editSc);
                initializeScenes();
            }
        });

        deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                delOwner = owTable.getSelectionModel().getSelectedItem();
                displayPopup("Are you sure you wish to delete " + delOwner.getName() + " from you records? This action cannot be undone.",
                 "Delete", "delete");
                searchTextField.setText("");
            }
        });

        infoBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                displayInfoWindow();
            }
        });

        Button editButton = new Button("Edit");
        HBox editHb = new HBox(10);
        editHb.setAlignment(align);
        editHb.getChildren().add(editButton);
        grid.add(editHb, 3, 9);
        editButton.setDisable(true);

        owTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                editButton.setDisable(false);
                deleteBtn.setDisable(false);
            } else {
                editButton.setDisable(true);
                deleteBtn.setDisable(true);
            }
        });

        editButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                addingNew = false;
                if (owTable.getSelectionModel().getSelectedItem() != null) {
                    selectedOwner = owTable.getSelectionModel().getSelectedItem();
                    editButton.setDisable(false);
                    //error check before swinthing scenes.
                    System.out.println(selectedOwner);
                    ps.setScene(editSc);
                    initializeScenes();
                }
            }
        });

        archiveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                Write.archiveCurrent("");
                Text archSuccess = new Text("*");
                archSuccess.setFill(Color.GREEN);
                grid.add(archSuccess, 4, 10);
            }
        });


        owTable.setRowFactory( tv -> {
            TableRow<Owner> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    addingNew = false;
                    selectedOwner = row.getItem();
                    System.out.println(selectedOwner + "\n\n\n\n"); //BUG: checking
                    ps.setScene(editSc);
                    initializeScenes();
                }
            });
            return row ;
        });
        


        mainMenu = new Scene(grid, 300, 275);
        
        
        //mainMenu.setBorderpain

        primaryStage.setScene(mainMenu);
        primaryStage.show();
        if (withdrawlReset == true) {
            displayPopup("Would you like to reset withdrawls to zero?", "Reset Withdrawls", "resetWithdrawls");
        }

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
        grid.setAlignment(align);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Patron Information");
        scenetitle.setFont(Font.font("Telugu MN", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        // BUTTONS
        Button saveBtn = new Button("Save");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(align);
        hbBtn.getChildren().add(saveBtn);
        grid.add(hbBtn, 1, 8);

        Button backBtn = new Button("Back");
        HBox bHitB = new HBox(10);
        bHitB.setAlignment(align);
        bHitB.getChildren().add(backBtn);
        grid.add(bHitB, 0, 8);

        Label isFixLabel = new Label("Pet is Fixed");
        grid.add(isFixLabel, 0, 6);
        CheckBox isFixedBox = new CheckBox();
        HBox isFixedBH = new HBox(isFixedBox);
        isFixedBox.setSelected(ow.getIsFixed());
        grid.add(isFixedBH, 1, 6);

        isFixedBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                owTable.getSelectionModel().getSelectedItem().setIsFixed(isFixedBox.isSelected());
                hasSaved = false;            }
        });

        Label isProvenLabel = new Label("Proof of Income");
        grid.add(isProvenLabel, 0, 7);
        CheckBox isProvenBox = new CheckBox();
        HBox isProveHB = new HBox(isProvenBox);
        isProvenBox.setSelected(ow.getIncomeProof());
        grid.add(isProveHB, 1, 7);

        isProvenBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                owTable.getSelectionModel().getSelectedItem().setIsFixed(isProvenBox.isSelected());
                hasSaved = false;            }
        });


        //#region name
        Label ownerNameL = new Label("Name:");
        grid.add(ownerNameL, 0, 1);
        TextField ownerTextField = new TextField(ow.getName()); 
        grid.add(ownerTextField, 1, 1);
        if (addingNew) ownerTextField.setText("");
        Text nameErr = displayErr(grid, "*", 5, 1);

        ownerTextField.textProperty().addListener(new ChangeListener<String>()  {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                hasSaved = false;
            }
        });

        //#endregion

        //#region Address
        Label addrL = new Label("Address:");
        grid.add(addrL, 0, 2);
        TextField addressTextField = new TextField(ow.getAddress());
        grid.add(addressTextField, 1, 2);
        if (addingNew) addressTextField.setText("");
        Text addrErr = displayErr(grid, "*", 5, 2);

        addressTextField.textProperty().addListener(new ChangeListener<String>()  {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                hasSaved = false;
            }
        });


        //#endregion

        //#region NumPets
        Label numPetsL = new Label("Pets:");
        grid.add(numPetsL, 0, 3);
        TextField numPeTextField = new TextField(String.valueOf(ow.getNumPets()));
        grid.add(numPeTextField, 1, 3);
        if (addingNew) numPeTextField.setText("");
        Text petErr = displayErr(grid, "*", 5, 3);

        numPeTextField.textProperty().addListener(new ChangeListener<String>()  {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                hasSaved = false;
            }
        });

        //#endregion

        //#region Strikes
        Label numStrikesL = new Label("Strikes:");
        grid.add(numStrikesL, 0, 4);
        TextField numStrikesTextField = new TextField(String.valueOf(ow.getStrikes()));
        grid.add(numStrikesTextField, 1, 4);
        if (addingNew) numStrikesTextField.setText("");
        Text strikeErr = displayErr(grid, "*", 5, 4);

        numStrikesTextField.textProperty().addListener(new ChangeListener<String>()  {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                hasSaved = false;
            }
        });

        //#endregion

        //#region Withdrawls
        Label numWithdrawlsL = new Label("Withdrawls:");
        grid.add(numWithdrawlsL, 0, 5);
        TextField numWithdrawlsTextField = new TextField(String.valueOf(ow.getNumRecieved()));
        grid.add(numWithdrawlsTextField, 1, 5);
        if (addingNew) numWithdrawlsTextField.setText("");
        Text withdrawlErr = displayErr(grid, "*", 5, 5);

        numWithdrawlsTextField.textProperty().addListener(new ChangeListener<String>()  {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                hasSaved = false;
            }
        });

        //#endregion
        
        //#region save button
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                // Confirm  data hasn't been saved
                if (canSave && !hasSaved){
                    displayPopup("Information has been changed without saving.\n Are you sure you want to go back?",
                     "Are you sure?", "checkSave");
                     if (noSave) ps.setScene(mainMenu);
                     noSave = false;
                }
                else { // has been saved
                    System.out.println("Back to Main menu.");
                    ps.setScene(mainMenu);
                    owTable.getItems().clear();
                    for (Owner ows : Driver.owners)
                        owTable.getItems().add(ows);
                }
            }
        });
        //#endregion
        
        Text isSaved = new Text("Saved");
        grid.add(isSaved, 1, 9);
        isSaved.setVisible(false);
        isSaved.setFill(Color.BLUEVIOLET);

        saveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) { // IF there is at least a name entered.
                
                canSave = true;

                if (dVal.checkNameFields(ownerTextField.getText())){
                    nameErr.setVisible(true);
                    canSave = false;
                } else {nameErr.setVisible(false);}

                // no address validator 
                // if (dVal.checkAddress(addressTextField.getText())){
                //     addrErr.setVisible(true);
                //     canSave = false;
                // } else {addrErr.setVisible(false);}

                if (dVal.checkNumPets(numPeTextField.getText())){
                    petErr.setVisible(true);
                    canSave = false;
                } else {petErr.setVisible(false);}

                if (dVal.checkPickUps(numStrikesTextField.getText())){ //TODO
                    strikeErr.setVisible(true);
                    canSave = false;
                } else { strikeErr.setVisible(false);}
                
                if (dVal.checkPickUps(numWithdrawlsTextField.getText())){
                    withdrawlErr.setVisible(true);
                    canSave = false;
                } else { withdrawlErr.setVisible(false);}
                
                if (!addingNew) {
                    if (!canSave){
                        isSaved.setText("Invalid entry");
                        isSaved.setVisible(true);
                    }
                    else {
                        owTable.getSelectionModel().getSelectedItem().setAllFeilds(
                            ownerTextField.getText(),
                            addressTextField.getText(),
                            isFixedBox.isSelected(),
                            isProvenBox.isSelected(),
                            Integer.parseInt(numPeTextField.getText()),
                            Integer.parseInt(numWithdrawlsTextField.getText()),
                            Integer.parseInt(numStrikesTextField.getText())
                            );

                        // System.out.println(owTable.getSelectionModel().getSele
                        Write.writeToCSV(Driver.writeFile);
                        hasSaved = true;
                        isSaved.setText("Saved");
                        isSaved.setVisible(true);
                    }
                }
                else {
                    if(!canSave) {
                        isSaved.setText("Invalid entry");
                        isSaved.setVisible(true);
                    }
                    else {
                        Write.addOwner(
                            ownerTextField.getText(),
                            addressTextField.getText(),
                            isProvenBox.isSelected(),
                            isFixedBox.isSelected(),
                            Integer.parseInt(numPeTextField.getText()),
                            Integer.parseInt(numStrikesTextField.getText()),
                            Integer.parseInt(numWithdrawlsTextField.getText())
                        );
                        hasSaved = true;
                        Text newAdded = new Text("Saved");
                        newAdded.setFill(Color.BLUE);
                        grid.add(newAdded, 2, 8);
                    }
                }
            }
        });

        editSc = new Scene(grid, 300, 275);
        ownerTextField.requestFocus();
        ownerTextField.selectAll();
        ps.setScene(editSc);
        ps.show();    

        if (addingNew) {
            ownerTextField.requestFocus();
        }
        //#endregion

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

    public static void displayPopup(String message, String title, String arg){
        Stage popWindow = new Stage();
        popWindow.initModality(Modality.APPLICATION_MODAL);
        popWindow.setMinWidth(450);
        popWindow.setMinHeight(300);
        popWindow.setTitle(title);
        Label mess = new Label(message);
        Button nButton = new Button("No");

        Button yesBt = new Button("Yes");
        VBox layout = new VBox(10);
        
        layout.setAlignment(Pos.CENTER_LEFT);

        if (arg == "checkSave")
        {
            layout.getChildren().addAll(mess, yesBt, nButton); 
            yesBt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e){
                    popWindow.close();
                    noSave = true;
                }
            });
            nButton.requestFocus();
            nButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e){
                    popWindow.close();
                }
            });
        }
        if (arg == "delete"){
            popWindow.setMaxHeight(200);
            layout.getChildren().addAll(mess, nButton, yesBt);
            nButton.requestFocus();
            yesBt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e){
                    System.out.println(owTable.getSelectionModel().getSelectedItem());
                    Write.delete(owTable.getSelectionModel().getSelectedItem());
                    popWindow.close();
                }
            });
            nButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e){
                    popWindow.close();
                }
            });
        }

        if (arg == "resetWithdrawls") {
            popWindow.setMaxHeight(200);
            Label txt = new Label("It's the start of the month.");
            layout.getChildren().addAll(txt,mess, yesBt, nButton);

            nButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e){
                    // TODO: more actions likely
                    popWindow.close();
                }
            });

            yesBt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e){
                    System.out.println("Setting all owners withdrawls to 0");
                    Write.archiveCurrent("withdrawlReset");
                    for (Owner ows : Driver.owners) {
                        ows.setNumRecieved(0);
                    }
                    Write.writeToCSV(Driver.writeFile);
                    Write.updateDateMetadata();
                    popWindow.close();
                }
            });
        }

        Scene scene = new Scene(layout);
        popWindow.setScene(scene);
        popWindow.showAndWait();
    }

    public void displayInfoWindow() {
        Stage infoStage = new Stage();


        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        Tab stat = new Tab("Statistics");
        VBox statVB = new VBox();
        Tab sett = new Tab("Settings");
        VBox settVB = new VBox();
        Tab help = new Tab("Help");
        VBox helpVB = new VBox();

        //#region Statistics
        int petsFed = 0;
        for(Owner ow : Driver.owners) petsFed += ow.getNumPets();
        Label patrons = new Label("Total Patrons:\t"+Driver.owners.size());
        Label withD = new Label("Average monthly withdrawls:\t"+Driver.owners.size());
        Label pFed = new Label("Pets fed:\t"+petsFed);



        statVB.getChildren().addAll(patrons, withD, pFed);
        statVB.setPadding(insets);
        stat.setContent(statVB);

         //#endregion

         //#region Settings
        /**
         * Reset witdrawls
         * dark theme?
         */

        Button resetWithdrawlsButton = new Button("Reset Withdrawls to Zero");
        HBox rwHB = new HBox();
        rwHB.setAlignment(align);
        rwHB.getChildren().add(resetWithdrawlsButton);




        settVB.getChildren().addAll(rwHB);
        settVB.setPadding(insets);
        sett.setContent(settVB);

         //#endregion

         //#region help
         Label controls = new Label("Controls:");
         Label contExpl = new Label(
             "\tDouble click on patron list to edit.\n\t" +
             "TODO"
         );
         
         helpVB.getChildren().addAll(controls, contExpl);
         helpVB.setPadding(insets);
         help.setContent(helpVB);


         //#endregion


        tabPane.getTabs().add(stat);
        tabPane.getTabs().add(sett);
        tabPane.getTabs().add(help);

        VBox vBox = new VBox(tabPane);
        Scene scene = new Scene(vBox);
        

        infoStage.setScene(scene);
        infoStage.setTitle("JavaFX App");
        infoStage.initModality(Modality.APPLICATION_MODAL); //TODO: what is info modality?
        infoStage.setMinWidth(600);
        infoStage.setMinHeight(400);
        infoStage.setTitle("Information");
        Label mess = new Label("TEMP MESSAGE");
        Button nButton = new Button("No");

        

        infoStage.show();
    }

    public void updateOwnerTable() {
        for (Owner ows : Driver.owners) {
            owTable.getItems().add(ows);
        }
    }

}