package src;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;

/**
 * All prints will be handled by the GUI in the future.
 */
public class Write {

    static Validator dVal = new Validator(); // initiate data validation object.
    static Scanner sc = new Scanner(System.in);

    /**
     * Constructs a new owner based on input and adds it to the list of owners.
     * TODO: Sort the list after adding.
     */
    public static void addOwner(String name, String addr, boolean incProof, boolean fixed, int numP, int strikes,
            int numR) {
        Owner newOwner = new Owner();
        newOwner.setName(name);
        newOwner.setAddress(addr);
        newOwner.setIncomeProof(incProof); // TODO: defaults to true, still need to decide on ui execution.
        newOwner.setIsFixed(fixed); // same as above
        newOwner.setNumPets(numP);
        newOwner.setStrikes(strikes);
        newOwner.setNumRecieved(numR);
        newOwner.setQualifiedForService();
        Driver.owners.add(newOwner);
        Collections.sort(Driver.owners, new OwnerComparator());

        // possibly want to archive file before rewriting.
        writeToCSV(Driver.writeFile);
        emitWriteAction("Owner " + name + " successfully ADDED to database");
    }

    public static void delete(Owner owner) {
        Driver.owners.remove(owner);
        writeToCSV(Driver.writeFile);
        emitWriteAction("Owner " + owner.getName() + " successfully DELETED from database.");
    }

    /**
     * Collects name with data validation. Split boolean indicates if this method
     * needs to split the string TODO
     * 
     * @return
     */
    public static String newName() {
        String name = "", fName = "", lName = "";
        boolean acceptF = false, acceptL = false;
        while (!acceptF) {
            fName = sc.nextLine();
            acceptF = !dVal.checkNameFields(fName);
        }
        while (!acceptL) {
            lName = sc.nextLine();
            acceptL = !dVal.checkNameFields(fName);
        }
        name = fName + " " + lName;
        emitWriteAction("NAME feild checked for valid entry.");
        return name;
    }

    /**
     * Collects number of pets with data validation.
     * 
     * @return
     */
    public static int newPetNum() {
        boolean accept = false;
        String input = "";
        while (!accept) {
            input = sc.nextLine();
            accept = !dVal.checkNumPets(input);

        }
        return Integer.parseInt(input);
    }

    /**
     * This method takes all owners stored and writes them to a csv file.
     * 
     * @param f, csv file to write owners to.
     */
    public static void writeToCSV(File f) {

        try {
            FileWriter fw = new FileWriter(f);
            fw.write("Name,Address,Pets,Strikes,Withdrawls,Fixed Status,Income Qualification,Overall Qualification\n");
            for (Owner ow : Driver.owners) {

                String line = ow.getName() + "," + ow.getIncomeProof() + "," +
                        ow.getStrikes() + "," + ow.getNumRecieved() + "," + ow.getNumPets() + "," + ow.getIsFixed()
                        + "," +
                        ow.getAddress();

                try {
                    fw.write(line + "\n");
                    fw.flush();
                    emitWriteAction("Line successfully added.");
                } catch (Exception e) {
                    emitWriteAction("Line not written: " + e);
                }
            }
            fw.close();
        } catch (Exception e) {
            emitWriteAction("NOT successfully writte: " + e);
        }
    }

    /**
     * Method creates and archive of the current log. Repeated code in writetoCSV,
     * could stand to implement better.
     * File saved as CSV, titled todays date and time. TODO: make a directory.
     */
    public static void archiveCurrent(String customName) {
        Date date = new Date();
        String[] title = date.toString().split(" ");
        String[] time = title[3].split(":");
        String fName;
        System.out.println(date.toString() + "\n\n\n");
        if (customName != "")
            fName = customName + ".csv";
        else
            fName = title[0] + "-" + title[1] + "-" + title[2] + "-" + time[0] + "-" + time[1] + "-" + time[2] + "-"
                    + title[5] + ".csv";

        try {
            File f = new File("archive/" + fName);
            if (f.createNewFile()) {
                try {
                    FileWriter fw = new FileWriter(f);
                    fw.write(
                            "Name,Address,Pets,Strikes,Withdrawls,Fixed Status,Income Qualification,Overall Qualification\n");
                    for (Owner ow : Driver.owners) {

                        String line = ow.getName() + "," + ow.getIncomeProof() + "," +
                                ow.getStrikes() + "," + ow.getNumRecieved() + "," + ow.getNumPets() + ","
                                + ow.getIsFixed() + "," +
                                ow.getAddress();
                        try {
                            fw.write(line + "\n");
                            fw.flush();
                        } catch (Exception e) {
                            System.out.println("Write.java: Line not written!");
                        }
                    }
                    GUI.emitGUIAction("Archive created with title: " + fName);
                    fw.close();
                } catch (Exception e) {
                    System.err.println("File not found.");
                }
            } else {
                System.out.println("Write.java: File not created, already exists.");
            }
        } catch (IOException e) {
            System.out.println("Error: Write.java: Error in file creation:");
            e.printStackTrace();
        }

    }

    public static void save() {
        writeToCSV(Driver.writeFile);
    }

    public static boolean updateDateMetadata() {
        String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        Date currDate = new Date();
        File tempFile = new File("meta/temp.csv");

        try {
            File metaFile = new File("meta/.metadata.csv");
            FileReader fr = new FileReader(metaFile);
            FileWriter fw = new FileWriter(tempFile);
            BufferedReader br = new BufferedReader(fr);
            BufferedWriter bw = new BufferedWriter(fw);
            String line = br.readLine();

            while (line != null) {

                if (line.charAt(0) == '#') {
                    bw.write(line + "\n");
                    line = br.readLine();
                    continue;
                }
                String[] lineData = line.split(":");
                if (lineData[0].equals("ResetWithdrawls")) { // handle withdrawls and dates.
                    // String[] dateData = lineData[1].split(",");

                    String newLine = "ResetWithdrawls:" + months[currDate.getMonth()] + ","
                            + months[currDate.getMonth()] + ",2022\n";
                    bw.write(newLine);
                }
                line = br.readLine();
            }
            br.close();
            bw.close();
            boolean successful = tempFile.renameTo(metaFile);
            return successful;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        }
    }

    public static void emitReport(String report) {

        try {
            File commentF = new File("meta/comments.txt");
            FileWriter fw = new FileWriter(commentF, true);
            BufferedWriter bw = new BufferedWriter(fw);
            Date currDate = new Date();
            bw.write(currDate.toString());
            bw.write("\n" + report + "\n\n");
            bw.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();

        }
    }

    /**
     * Prints important events to the console.
     * @param action
     */
    public static void emitWriteAction(String action) {
        System.out.println("Write Event: " + action);
    }

}
