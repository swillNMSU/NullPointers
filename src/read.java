package src;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.*;

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
            File theFile = new File("src/csvWriteTest.csv");
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


}