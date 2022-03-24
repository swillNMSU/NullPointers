package src;

import java.util.Scanner;

/**
 * All prints will be handled by the GUI in the future.
 */
public class write {
    


    static DVO dVal = new DVO();
    static Scanner sc = new Scanner(System.in);

    public static void addOwner(){
        owner newOwner = new owner();


        newOwner.setName(newName());
        newOwner.setIncomeProof(true); // TODO: defaults to true, still need to decide on ui execution.
        newOwner.setIsFixed(true);  // same as above
        newOwner.setNumPets(newPetNum());
        newOwner.setStrikes(0);
        newOwner.setNumRecieved(0);
    }

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
}
