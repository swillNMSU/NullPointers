package src;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
