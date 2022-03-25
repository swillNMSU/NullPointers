package src;
import java.io.File;
import java.util.*;

public class driver {

    // global vars, for now just a list of all owners.
    public static List<owner> owners = new ArrayList<>();
    public static File writeFile = new File("src/csvWriteTest.csv");

    enum menu {
        add,
        remove,
        display,
        search
    }

    /**
     * Test driver
     * @param args
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        // draw out all owners from the csv
        read.readCSV();
        for (owner owner : owners){
            System.out.println("\n" + owner);
        }
        System.out.println("Total number of owners: " + owners.size());
        System.out.println("Add owner: ");
        
        write.writeToCSV(writeFile);
    }
}
