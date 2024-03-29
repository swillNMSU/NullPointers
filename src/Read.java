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
     * Call to read a CSV file.
     * CSV reading is strucured as follows:
     * 
     * ':' seperates all owner and pet objects into their own substring,
     * with owners being stored in arr[0], pets in arr[1], arr[2]...
     * 
     * ',' seperates these substrings into the objects data members,
     * which are then converted to the appropriate type and stored in the objects
     */
    public static void readCSV(String pathtoFile, boolean toDriver) {
        try {

            File theFile = new File(pathtoFile);
            FileReader fr = new FileReader(theFile);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            line = br.readLine(); // skip first line

            while (line != null) {

                String[] ownerData = line.split(",");
                Owner nOwner = new Owner(ownerData[0]);
                nOwner.setIncomeProof(Boolean.parseBoolean(ownerData[1]));
                nOwner.setStrikes(Integer.parseInt(ownerData[2]));
                nOwner.setNumRecieved(Integer.parseInt(ownerData[3]));
                nOwner.setNumPets(Integer.parseInt(ownerData[4]));
                nOwner.setIsFixed(Boolean.parseBoolean(ownerData[5]));
                nOwner.setAddress(ownerData[6]);
                nOwner.setQualifiedForService();
                if (toDriver)
                    Driver.owners.add(nOwner);
                else {
                    Driver.currentArchives.add(nOwner);
                }
                line = br.readLine();
            }
            br.close();
            if (toDriver) {
                Collections.sort(Driver.owners, new OwnerComparator());
                emitReadAction("Main CSV read to driver.");
            }

            else {
                Collections.sort(Driver.currentArchives, new OwnerComparator());
                emitReadAction("Archive CSV read to driver.");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Returns a arraylist containing all possible search results. This allows for
     * dynamic searching (i.e. this can
     * be called each time a letter is entered/removed in the search bar and
     * possible results can be reported.)
     * 
     * @param search Strings from the GUI's search bar.
     * @return list of possible results.
     */
    public static List<Owner> searchByName(String search) {
        List<Owner> result = new ArrayList<Owner>();
        for (Owner ow : Driver.owners)
            if (ow.getLastName().toLowerCase().startsWith(search.toLowerCase()) ||
                    ow.getName().toLowerCase().startsWith(search.toLowerCase()))
                result.add(ow);
            else if (ow.getAddress().contains(search) || ow.getAddress().contains(search))
                result.add(ow);
        return result;
    }

    /**
     * Assembles a new list of owners with as a potential search result.
     * 
     * @param search
     * @return List of possible owners with a matching query.
     */
    public static List<Owner> searchByAddr(String search) {
        List<Owner> result = new ArrayList<Owner>();
        for (Owner ow : Driver.owners)
            if (ow.getAddress().startsWith(search) || ow.getAddress().startsWith(search))
                result.add(ow);
        return result;
    }

    /**
     * If the current month is august, a reset is available for withdrawls. This
     * method checks for this event.
     * 
     * @return - boolean signifying whether a reset should be called.
     */
    public static boolean checkForReset() {
        String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        Date currDate = new Date();
        Boolean newYear = false;
        int currMonth = -1, monthSinceReset = -1;
        BufferedReader br = null;
        try {
            File metaFile = new File("meta/.metadata.csv");
            FileReader fr = new FileReader(metaFile);
            br = new BufferedReader(fr);
            String line = br.readLine();

            while (line != null) {
                if (line.charAt(0) == '#') {
                    line = br.readLine();
                    continue;
                } // skip lines with #

                String[] lineData = line.split(":");
                if (lineData[0].equals("ResetWithdrawls")) { // handle withdrawls and dates.
                    String[] dateData = lineData[1].split(",");

                    if (dateData[0] == "Aug")

                        for (int i = 0; i < months.length; i++) {
                            if (months[i].equals(dateData[0])) {
                                currMonth = i;
                            }
                            if (months[i].equals(dateData[1])) {
                                monthSinceReset = i;
                            }
                        }
                    if (currDate.getYear() > Integer.parseInt(dateData[2])) {
                        newYear = true;
                    }

                    if (newYear) {
                        if (currMonth < monthSinceReset)
                            return true;
                    } else {
                        if (currMonth > monthSinceReset)
                            return true;
                    }
                    return false;
                }
                line = br.readLine();
            }
            emitReadAction("Checked for reset event.");

        }

        catch (IOException ioe) {
            ioe.printStackTrace();
        }

        finally {
            try {
                br.close();
            } catch (Exception e) {
                emitReadAction("ERROR: " + e);
            }
        }
        return false;

    }

    /**
     * Retrieve an list of files from on the archives directory.
     */
    public static List<File> getArchives() {
        List<File> archs = new ArrayList<>();
        String[] pathnames;
        File f = new File("archive");
        pathnames = f.list();
        for (String path : pathnames) {
            File archiveFile = new File("archive/" + path);
            archs.add(archiveFile);
        }
        emitReadAction("Archives retrieved.");
        return archs;
    }

    /**
     * Prints important events to the console.
     * 
     * @param action
     */
    public static void emitReadAction(String action) {
        System.out.println("Read Event: " + action);
    }
}
