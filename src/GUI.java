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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
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
import src.datavalidation.driver;

import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;

/**
 * TODO:
 *      
 *      Color each owner in table red if they dont have proof of income, as well
 *      Search by address?
 *      Display archives:
 *              Scrollpane showing available archives. Scrollpane showing 
 *
 *      
 *      
 *    
 *      Reset withdrawls at the first of every YEAR (AUGUST TO AUGUST)
 *      Add Statistics page
 *      Add Settings/Info Page
 *      Resize and clean windows to properties that fit best
 *      Color coding
 *      
 *      Change question mark to gear icon
 * 
 *      Maybe have an option to flag an owner as already banned
 *      If searching, select top result
 * 
 *      Click on table elemnt and have that highlighted on edit screen
 *      
 */


public class GUI extends Application {
    
    Owner selectedOwner, delOwner;
    static Stage ps;
    static Scene mainMenu;
    Scene editSc;
    Scene addSc;
    static Scene archiveScene;
    ScrollPane sp = new ScrollPane();
    final double width = 400, height = 600; // global sizes for scenes.
    Validator dVal = new Validator();
    boolean addingNew = false;
    static TableView<Owner> owTable;
    static TableView<Owner> archivedOwTable;
    boolean canSave = true, searching = false;
    boolean withdrawlReset = Read.checkForReset();
    static Insets insets = new Insets(20);
    static Pos align = Pos.CENTER_LEFT;
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
        ps = primaryStage;
        ps.setHeight(height);
        ps.setWidth(width);
        initializeEditScene();
        
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

        Read.readCSV("src/testReset.csv", true);    
        updateOwnerTable(true);
        owTable.refresh();

        VBox vBox = new VBox(owTable);
        vBox.setAlignment(Pos.CENTER);
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
        scenetitle.setFont(Font.font("Telugu MN", 34));
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
        grid.add(infoBtn, 2, 10);

        Button archiveBtn = new Button("Archive");
        HBox archHB = new HBox(10);
        archHB.setAlignment(align);
        archHB.getChildren().add(archiveBtn);
        grid.add(archiveBtn, 1, 10);

        Label searchL = new Label("Search");
        grid.add(searchL, 1, 7);

        TextField searchTextField = new TextField();
        grid.add(searchTextField, 2, 7);

        // add listener if row is selected.
        Button editButton = new Button("Edit");
        HBox editHb = new HBox(10);
        editHb.setAlignment(align);
        editHb.getChildren().add(editButton);
        grid.add(editHb, 2, 5);
        editButton.setDisable(true);

        searchTextField.textProperty().addListener(new ChangeListener<String>()  {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                searching = true;
                //if length of search is increasing, pass back result. No need to search the entirety of the feild
                if (searchTextField.getText() == "")
                    for (Owner ows : Driver.owners)
                        owTable.getItems().add(ows);
                List<Owner> query = Read.searchByName(searchTextField.getText());
                owTable.getItems().clear();
                for (Owner ows : query)
                    owTable.getItems().add(ows);
                owTable.getSelectionModel().selectFirst();
                searching = false; // at the end
            }
        });

        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                addingNew = true;
                selectedOwner = null;
                ps.setScene(editSc);
                initializeEditScene();
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
                    initializeEditScene();
                }
            }
        });

        archiveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                Write.archiveCurrent("");
                //Text archSuccess = new Text("*");
                //archSuccess.setFill(Color.GREEN);
                //grid.add(archSuccess, 4, 10);
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
                    initializeEditScene();
                }
            });   
            return row ;
        });
    
        mainMenu = new Scene(grid, 300, 275);
        primaryStage.setScene(mainMenu);
        primaryStage.show();
        if (withdrawlReset == true) {
            displayPopup("Would you like to reset withdrawls to zero?", "Reset Withdrawls", "resetWithdrawls");
        }
    }
    
    public void initializeEditScene(){
        Owner ow = new Owner("");
    
        //#region EditScene
        if (selectedOwner != null) ow = selectedOwner;
        
        ps.setTitle("Patron List");
        ps.setHeight(800);
        ps.setWidth(950);
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
        grid.setAlignment(Pos.CENTER);
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
    } // end initializeEditScene

    public static void initializeArchiveScene() {
        List<File> archives = Read.getArchives();
        TableView<File> archiveTable = new TableView<>();
        archiveTable.prefHeight(200);
        archiveTable.prefWidth(200);
        archiveTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<File, String> col1 = new TableColumn<>("name");
        col1.setCellValueFactory(new PropertyValueFactory<>("name"));
        archiveTable.getColumns().add(col1);
        archiveTable.setRowFactory( tv -> {
            TableRow<File> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    displaySelectedArchive(row.getItem());
                    System.out.println("HERERER\n\n\n\n\n\n");
                    
                }
            });     
            return row ;
        });
        
        updateArchiveTable(archives, archiveTable);
        VBox layout = new VBox();
        layout.setPadding(insets);
        layout.getChildren().add(archiveTable);

        archives = null; // if we hit back 
        archiveScene = new Scene(layout);
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
        layout.setAlignment(Pos.CENTER);

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
            layout.getChildren().addAll(mess, yesBt, nButton);
            
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
            nButton.requestFocus();
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

        if (arg == "resetWithdrawlsFromSett") {
            popWindow.setMaxHeight(200);
            popWindow.setAlwaysOnTop(true);
            //Label txt = new Label("It's the start of the month.");
            mess.setAlignment(Pos.CENTER);
            layout.getChildren().addAll(mess, yesBt, nButton);

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

        if (arg == "report"){
            popWindow.setAlwaysOnTop(true);
            Label repLabel = new Label("Report an issue:");
            TextArea commentField = new TextArea();
            commentField.setWrapText(true);
            commentField.setPrefHeight(200); commentField.setPrefWidth(100);
            Button repBtn = new Button("Save Report");

            repBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e){
                    Write.emitReport(commentField.getText());
                    popWindow.close();
                }
            });
            layout.getChildren().addAll(repLabel, commentField, repBtn);
        }
        layout.setPadding(insets);
        Scene scene = new Scene(layout);
        popWindow.setScene(scene);
        popWindow.showAndWait();  
    }

    public static void displayInfoWindow() {
        Stage infoStage = new Stage();
        infoStage.setAlwaysOnTop(true);
        int prefHeight = 350;
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

        Button statbackBtn = new Button("Back");
        HBox statbkHB = new HBox();
        statbkHB.setAlignment(Pos.BOTTOM_LEFT);
        statbkHB.getChildren().add(statbackBtn);

        statbackBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
               infoStage.close();
            }
        });

        Button vArchiveButton = new Button("View Archives");
        HBox varcHB = new HBox();
        varcHB.setAlignment(align);
        varcHB.getChildren().add(vArchiveButton);

        vArchiveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                initializeArchiveScene();
                ps.setScene(archiveScene);
                infoStage.close();
            }
        });

        statVB.getChildren().addAll(patrons, withD, pFed, vArchiveButton);
        statVB.setPadding(insets);

        BorderPane statisticsRoot = new BorderPane();
        statisticsRoot.setPadding(insets);
        statisticsRoot.setCenter(statVB);
        statisticsRoot.setBottom(statbackBtn);
        statisticsRoot.setPrefHeight(prefHeight);
        //statisticsRoot.prefWidthProperty().bind(tabPane.widthProperty());
        stat.setContent(statisticsRoot);

         //#endregion

         //#region Settings
        Button resetWithdrawlsButton = new Button("Reset Withdrawls to Zero");
        HBox rwHB = new HBox();
        rwHB.setAlignment(align);
        rwHB.getChildren().add(resetWithdrawlsButton);

        resetWithdrawlsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
               displayPopup("Are you sure you would like to reset all Patron's yearly withdrawls to 0?\n" +
               "This action cannot be undone.", "Are you sure?", "resetWithdrawlsFromSett");
            }
        });

        Button settbackBtn = new Button("Back");
        HBox settbkHB = new HBox();
        settbkHB.setAlignment(align);
        settbkHB.getChildren().add(settbackBtn);
        settbackBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
               infoStage.close();
            }
        });

        settVB.getChildren().addAll(rwHB );
        settVB.setPadding(insets);

        BorderPane settingsRoot = new BorderPane();
        settingsRoot.setPadding(insets);
        settingsRoot.setCenter(settVB);
        settingsRoot.setBottom(settbackBtn);
        settingsRoot.setPrefHeight(prefHeight);
        sett.setContent(settingsRoot);
         //#endregion

         //#region help
         Label controls = new Label("Controls:");
         Label contExpl = new Label(
             "\tDouble click on patron list to edit.\n\t" +
             "TODO"
         );
         Button reportBtn = new Button("Report an Issue");
         HBox reportHB = new HBox();
         reportHB.setAlignment(align);
         reportHB.getChildren().add(reportBtn);

         Button helpbackBtn = new Button("Back");
         HBox hbkHB = new HBox();
         hbkHB.setAlignment(align);
         hbkHB.getChildren().add(helpbackBtn);

         helpbackBtn.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent e){
                infoStage.close();
             }
         });
         
         helpVB.getChildren().addAll(controls, contExpl, reportBtn);
         helpVB.setPadding(insets);
         help.setContent(helpVB);

         reportBtn.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent e){
                displayPopup("Please record and save your comment here:", "Report", "report");
             }
         });

        BorderPane helpBorderPane = new BorderPane();
        helpBorderPane.setPadding(insets);
        helpBorderPane.setCenter(helpVB);
        helpBorderPane.setBottom(helpbackBtn);
        helpBorderPane.setPrefHeight(prefHeight);
        help.setContent(helpBorderPane);
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
    } // end display popup

    public static void displaySelectedArchive(File selArc) {
        Stage archivestage = new Stage();
        try {
            
            FileReader fr = new FileReader(selArc);
            archivedOwTable = new TableView<>();
            archivedOwTable.setPrefHeight(200);
            archivedOwTable.setPrefWidth(200);
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

            archivedOwTable.getColumns().add(column1);
            archivedOwTable.getColumns().add(column2);
            archivedOwTable.getColumns().add(column3);
            archivedOwTable.getColumns().add(column4);
            archivedOwTable.getColumns().add(column5);
            archivedOwTable.getColumns().add(column6);
            archivedOwTable.getColumns().add(column7);
            archivedOwTable.getColumns().add(column8);
            
            archivedOwTable.setPrefHeight(400);
            
            Read.readCSV(selArc.getPath(), false);
            updateOwnerTable(false);
            archivedOwTable.setSelectionModel(null);
            archivedOwTable.refresh();

            VBox arVB =  new VBox();
            arVB.setPadding(insets);
            System.out.println(selArc.getName());

            Button backToSeButton = new Button("Back");
            HBox backTBox = new HBox();
            backTBox.setAlignment(align);
            backTBox.getChildren().add(backToSeButton);

            Button exportToExcelButton = new Button("Export to Excel");
            HBox exportHB = new HBox();
            exportHB.setAlignment(align);
            exportHB.getChildren().add(exportToExcelButton);

            backToSeButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e){
                    ps.setScene(mainMenu);
                    displayInfoWindow();
                }
            });
            exportToExcelButton.setOnAction(new EventHandler<ActionEvent>() { // TODO:
                @Override
                public void handle(ActionEvent e) {

                }
            });
            
            Label filName = new Label("Viewing archive from:  " + 
                selArc.getName().substring(0, selArc.getName().length() - 16));
            
            arVB.getChildren().addAll(filName,archivedOwTable,backToSeButton);  
            Scene selectedArchiveScene = new Scene(arVB);
            ps.setScene(selectedArchiveScene);    

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            displayPopup("Error: Unable to open file", "Error", "unabletoopenfile");
        }
    }

    public static void updateOwnerTable(boolean fromMain) {
        if (fromMain) 
            for (Owner ows : Driver.owners) owTable.getItems().add(ows);  
        else for (Owner ows : Driver.currentArchives) 
                archivedOwTable.getItems().add(ows);   
    }

    public static void updateArchiveTable(List<File> archs, TableView<File> table) {
        for (File ar : archs) 
            table.getItems().add(ar);
    }

}