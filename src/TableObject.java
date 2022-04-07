package src;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TableObject {
    private StringProperty column1;
    private StringProperty column2;

        public TableObject(String col1, String col2)
        {
            column1 = new SimpleStringProperty(col1);
            column2 = new SimpleStringProperty(col2);
        }

        public StringProperty column1Property()
        {
            return column1;
        }

        public StringProperty column2Property()
        {
            return column2;
        }
    }
