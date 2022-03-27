package src;
import java.io.File;
import java.util.*;

public class Driver {

    public static List<Owner> owners = new ArrayList<>();
    public static File writeFile = new File("src/csvWriteTest.csv");

    enum Menu {
        mMenu,
        add,
        remove,
        display,
        search,
        archive
    }

    /**
     * Test driver
     * @param args
     */
    public static void main(String[] args){
       
       
        Scanner sc = new Scanner(System.in);
       
        Read.readCSV();
        for (Owner owner : owners) System.out.println("\n" + owner);
        
        System.out.println("Total number of owners: " + owners.size());
        System.out.println("Add owner: ");
        
        Write.writeToCSV(writeFile);
        Write.archiveCurrent();
      
    }
}
