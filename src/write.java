package src;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.*;
import java.util.Collections;
import java.util.Date;
import java.lang.*;


enum Edit{
    name,
    address,
    numPets,
    fixed,
    incomeStatus,
    strikes,
    numRecieved
}

/**
 * All prints will be handled by the GUI in the future.
 */
public class Write {

    static DVO dVal = new DVO(); // initiate data validation object.
    static Scanner sc = new Scanner(System.in);

    /**
     * Constructs a new owner based on input and adds it to the list of owners. 
     * TODO: Sort the list after adding.
     */
    public static void addOwner(){
        Owner newOwner = new Owner();
        newOwner.setName(newName());
        newOwner.setIncomeProof(true); // TODO: defaults to true, still need to decide on ui execution.
        newOwner.setIsFixed(true);  // same as above
        newOwner.setNumPets(newPetNum());
        newOwner.setStrikes(0);
        newOwner.setNumRecieved(0);
        Driver.owners.add(newOwner); 
        Collections.sort(Driver.owners, new OwnerComparator());

        // possibly want to archive file before rewriting.
        writeToCSV(Driver.writeFile);
    }

    public static void delete(Owner owner){
        Driver.owners.remove(owner);
        writeToCSV(Driver.writeFile);
    }

    /**
     * Collects name with data validation.
     * @return
     */
    public static String newName(){
        String name = "", fName = "", lName = "";
        boolean acceptF = false, acceptL = false;
        while (!acceptF) {
            System.out.print("Please enter first name: ");
            fName = sc.nextLine();
            acceptF = !dVal.checkNameFields(fName);
        }
        while (!acceptL) {
            System.out.print("Please enter last name: ");
            lName = sc.nextLine();
            acceptL = !dVal.checkNameFields(fName);
        }
        name = fName + " " + lName;
        return name;
    }

    /**
     * Collects number of pets with data validation.
     * @return
     */
    public static int newPetNum(){
        System.out.print("Please enter the nunber of pets: ");
        boolean accept = false;
        String input = "";
        while(!accept){
            input = sc.nextLine();
            accept = !dVal.checkNumPets(input);
            if (!accept) System.out.println("Invalid number, please enter again.");
        }
        return Integer.parseInt(input);
    }

   /**
    * Changes data depending on what attribure is added to the parameters.
    * @param ow the owner we will change the data of.
    * @param edit case of data to be changed.
    * @param newString 
    * @param newNum
    * @param newBool
    */
    public static void edit(Owner ow, Edit edit, String newString, int newNum, boolean newBool){
        
        switch (edit){
            case name : 
                ow.setName(newString);
                break;
            case address : 
                ow.setAddress(newString);
                break;
            case numPets :
                ow.setNumPets(newNum);
                break;
            case fixed :
                ow.setIsFixed(newBool);
                break;
            case incomeStatus :
                ow.setIncomeProof(newBool);
                break;
            case strikes :
                ow.setStrikes(newNum);
                break;
            case numRecieved :
                ow.setNumRecieved(newNum);
                break;
            default :
                System.out.println("Error: Improper args."); //TODO: Send something to the GUI
        }
        System.out.println("Save?  (Type exactly 'Yes')");
        Scanner sc = new Scanner(System.in);
        if (sc.nextLine().equals("Yes")) {
            writeToCSV(Driver.writeFile); // This should not be called here. Testing purposes only
            System.out.println("saved");
        }
        sc.close();
    }

    /**
     * This method takes all owners stored and writes them to a csv file.
     * @param f, csv file to write owners to.
     */
    public static void writeToCSV(File f){
        
        // TODO: sort arraylist
        
        try {
            FileWriter fw = new FileWriter(f);
            for (Owner ow : Driver.owners){
                
                String line = ow.getName() + "," + ow.getIncomeProof() + "," +
                    ow.getStrikes() + "," + ow.getNumRecieved() + "," + ow.getNumPets();
                
                try {
                    fw.write(line + "\n");
                    fw.flush();
                    System.out.println("Line added to CSV:  " + line);
                }
                catch (Exception e){
                    System.out.println("Line not written!");
                }
            }
            fw.close();
        }
        catch (Exception e){
            System.err.println("File not found.");
        }
    }

    /**
     * Method creates and archive of the current log. Repeated code in writetoCSV, could stand to implement better.
     * File saved as CSV, titled todays date and time. TODO: make a directory.
     */
    public static void archiveCurrent(){
        Date date = new Date();
        String fName = date.toString() + "csv";
        try {
            File f = new File(fName);
            if (f.createNewFile()){
                System.out.println("New archive csv created: " + fName);
                try {
                    FileWriter fw = new FileWriter(f);
                    for (Owner ow : Driver.owners){
                        
                        String line = ow.getLastThenFirst() + "," + ow.getIncomeProof() + "," +
                            ow.getStrikes() + "," + ow.getNumRecieved() + "," + ow.getNumPets();
                        try {
                            fw.write(line + "\n");
                            fw.flush();
                            System.out.println("Line added to CSV");
                        }
                        catch (Exception e){
                            System.out.println("Line not written!");
                        }
                    }
                    fw.close();
                }
                catch (Exception e){
                    System.err.println("File not found.");
                }
            }
            else { System.out.println("File not created, already exists.");}
        }
        catch (IOException e){
            System.out.println("Error in file creation:");
            e.printStackTrace();
        }
        
    }

    public static void save(){
        writeToCSV(Driver.writeFile);
    }

}
