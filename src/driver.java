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
        System.out.println("Begin, type anything.");
        sc.nextLine();
        Read.readCSV();
       // for (Owner owner : owners) System.out.println("\n" + owner);
        
         System.out.println("Total number of owners: " + owners.size());
        // System.out.println("Add owner: ");
        
        Write.writeToCSV(writeFile);
        // Write.archiveCurrent();
       
       
// TEST: Edit method.




// TEST: Search method and edit method.  
        while(true){
            System.out.print("Enter a name to search: ");
            String s = sc.nextLine();
            
            List<Owner> r = Read.searchByName(s);
            System.out.println("Possible results: ");
            for (Owner x: r)
                System.out.println("\t" + x.getName());
        }
      
    }
}
