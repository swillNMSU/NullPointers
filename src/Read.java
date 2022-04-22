package src;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.*;
import java.time.format.DateTimeFormatter;


/*
Tools to implement our CSV file.

*/

public class Read {

    /**
     *  Call to read a CSV file.
     *     CSV reading is strucured as follows:
     * 
     *      ':' seperates all owner and pet objects into their own substring,
     *       with owners being stored in arr[0], pets in arr[1], arr[2]...
     * 
     *      ',' seperates these substrings into the objects data members,
     *      which are then converted to the appropriate type and stored in the objects
     */
    public static void readCSV(){
        try{
            File theFile = new File("src/testReset.csv");
            FileReader fr = new FileReader(theFile);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            
            while(line != null){    
                
                String[] ownerData = line.split(",");
                Owner nOwner = new Owner(ownerData[0]);
                nOwner.setIncomeProof(Boolean.parseBoolean(ownerData[1]));
                nOwner.setStrikes(Integer.parseInt(ownerData[2])); 
                nOwner.setNumRecieved(Integer.parseInt(ownerData[3]));
                nOwner.setNumPets(Integer.parseInt(ownerData[4]));
                nOwner.setIsFixed(Boolean.parseBoolean(ownerData[5]));
                nOwner.setAddress(ownerData[6]);
                nOwner.setQualifiedForService();
                Driver.owners.add(nOwner); 
                line = br.readLine();      
            }
            br.close();
            Collections.sort(Driver.owners, new OwnerComparator());
            
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Returns a arraylist containing all possible search results. This allows for dynamic searching (i.e. this can
     * be called each time a letter is entered/removed in the search bar and possible results can be reported.)
     * @param search Strings from the GUI's search bar.
     * @return list of possible results.
     * TODO: Need to factor in lower/upper comparisons.
     */
    public static List<Owner> searchByName(String search){
        List<Owner> result = new ArrayList<Owner>();
        for (Owner ow : Driver.owners)
            if (ow.getLastName().toLowerCase().startsWith(search.toLowerCase()) || 
                ow.getName().toLowerCase().startsWith(search.toLowerCase()))
                result.add(ow);                            
        return result;
    }

    /**
     * Assembles a new list of owners with as a potential search result.
     * @param search
     * @return List of possible owners with a matching query.
     */
    public static List<Owner> searchByAddr(String search){
        List<Owner> result = new ArrayList<Owner>();
        for (Owner ow : Driver.owners)
            if (ow.getAddress().startsWith(search) || ow.getAddress().startsWith(search))
                result.add(ow);                            
        return result;
    }

    public static boolean checkForReset() {
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        Date currDate = new Date();
        Boolean newYear = false;
        int currMonth = -1, monthSinceReset = -1;

        try{
            File metaFile = new File("meta/.metadata.csv");
            FileReader fr = new FileReader(metaFile);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            
            while(line != null){    
                System.out.println("HERE");
                if (line.charAt(0) == '#') { line = br.readLine(); continue; }
                System.out.println(line);

                String[] lineData = line.split(":");
                if (lineData[0].equals("ResetWithdrawls")) { // handle withdrawls and dates.
                    String[] dateData = lineData[1].split(",");
                    
                    for (int i = 0; i < months.length; i++) {
                        if (months[i].equals(dateData[0])) {
                            currMonth = i;
                            System.out.println(currMonth);
                        }
                        if (months[i].equals(dateData[1])) {
                            monthSinceReset = i;
                            System.out.println(monthSinceReset);
                        }
                    }
                    if (currDate.getYear() > Integer.parseInt(dateData[2])) {
                            newYear = true;
                    }

                    if (newYear) {
                        if(currMonth < monthSinceReset) 
                            return true;
                    }
                    else {
                        if (currMonth > monthSinceReset)
                            return true;
                    }
                    return false;
                }
                line = br.readLine();      
            }
            br.close();

        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return false;

    }

}
