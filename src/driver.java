package src;
import java.io.File;
import java.util.*;

public class Driver {

    public static List<Owner> owners = new ArrayList<>();
    public static File writeFile = new File("src/csvWriteTest.csv");

    enum Menu {
        Add,
        Delete,
        Display,
        Search,
        Archive
    }


    /**
     * Test driver
     * @param args
     */
    public static void main(String[] args){
        Menu menu;
       
        Scanner sc = new Scanner(System.in);
        Read.readCSV();
        
        System.out.println("Total number of owners: " + owners.size());
        
        Write.writeToCSV(writeFile);
        //Write.archiveCurrent();

// TEST: Search method and edit method.  
        while(true){
           displayMenu();          
            
            int opt = Integer.parseInt(sc.nextLine());
            
            if (opt == 1)
                for (Owner x : owners) System.out.println(x + "\n");
            if (opt == 2){
                System.out.println("Enter a name. press 2 if found, type '1' to end.");
                String s = " ";
                List<Owner> results = new ArrayList<>();
                while (true){
                    s = sc.nextLine();
                    if (s.equals("1")) break;
                    results = Read.searchByName(s);
                    for (Owner ows : results) System.out.println("\t" + ows.getName());
                }
                if (results.size() == 1) System.out.println(results.get(0));
                else { System.out.println("Not found.");}
            }
            if (opt == 3){
                Write.addOwner();
            }
            if (opt == 4){
                System.out.println("Not working just yet");
            }
            if (opt == 5){
                Write.archiveCurrent();
            }
            else { System.out.println("Not an option"); }
        }
      
    }

    public static void displayMenu(){
        System.out.println("OPTIONS:  Type number");
        System.out.println("\t1. Display All\n\t2. Search\n\t3. Add\n\t4. Delete\n\t5. Archive");
    }
}
