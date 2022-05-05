package src.datavalidation;

import java.util.*;

/**
 *  This Driver class was to test the DataTest to make sure that the methods are working
 *  correctly on catching invalid data.
 */
public class Driver{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        owner person = new owner(null);
        getPersontName(scan,"Please enter your First name",person);
        getPersonIncome(scan,"Please enter the income form",person);
        getNumPets(scan,"Please enter the number of pets you have",person);
        getNumPickUp(scan,"Please change number of pick up",person);
        getAddress(scan, "Please enter your address", person);
        getStrikes(scan, "Please enter a number of strikes");
        // need to update the owner class to have fixed status
        //addStrike(scan,"Please enter Y for yes or N for no", person);
        
    }

    /**
     * 
     * @param name
     *  input scanner that is a string
     * @param message
     *  The message for the user input thier name
     * @param personsInfoOwner
     *  The object that I was checking to make sure that the information is working correctly
     *  
     *  This method methods job is to make sure that the user is inputing correct data if the 
     *  data is incorrect then the while loop will keep running until the input is valid. The 
     *  DataTest.checkNameField method returns a booleen value true for invalid and false for
     *  valid. It will update the owners name filed.
     */
    public static void getPersontName(Scanner name,String message, owner personsInfoOwner){
        System.out.println(message);
        String inputname = name.nextLine();
        while(DataTest.checkNameFields(inputname)){
            System.out.println(message);
            inputname = name.nextLine();
            
        }
        personsInfoOwner.setName(inputname);
    }

    /**
     * 
     * @param incom
     *  String input from the user
     * @param message
     *  The message that tells the user what to up
     * @param personInfo
     *  The information that is being updated when the data is valid
     *  
     *  This methods purpose is to get the persons inforamtion and make sure that the user entered
     *  the correct inforamtion. The class DataTest with the method checkNameField will validat the
     *  user input. The loop will not break unitl false if false then the data is valid and update the 
     * income proof of the owner . If true it will keep on prompting the user to enter until valid.
     */
    public static void getPersonIncome(Scanner incom, String message,owner personInfo){
        System.out.println(message);
        String incomeInput = incom.nextLine();
        while(DataTest.checkNameFields(incomeInput)){
            System.out.println(message);
            incomeInput = incom.nextLine();
        }
        personInfo.setIncomeProof(incomeInput);
    }

    /**
     * 
     * @param numPet
     *  String input from user
     * @param message
     *  To tell the user what to put
     * @param personInfo
     *  Updating owners inforamtion
     * 
     *  This method purpose is to get user input for the number of pets. The class DataTest uses
     *  checkNumPets to check if the number that the user input is a valid input, if true then the
     *  input is invalid. If the data is valid then it will be false. Will update the number of pets
     *  in the owners pets filed
     */
    public static void getNumPets(Scanner numPet, String message, owner personInfo){
        System.out.println(message);
        String input = numPet.nextLine();
        while(DataTest.checkNumPets(input)){
            System.out.println(message);
            input = numPet.nextLine();
        }
        int validNumInput = Integer.parseInt(input);
        personInfo.setNumPets(validNumInput);
    }

    /**
     * 
     * @param petFoodGiven
     *  String input from the user
     * @param message
     *  Tells the user what to put
     * @param personInfo
     *  Updated the owners pick ups
     * 
     *  The purpose of this method is to make sure that the user is inputing the correct input
     *  for this field. If the the data input for this method is invalid then the while loop 
     *  will keep on running. If false then the input is valid. It will updat the recieved filed
     *  in the owner class
     */
    public static void getNumPickUp(Scanner petFoodGiven, String message, owner personInfo) {
        System.out.println(message);
        String input = petFoodGiven.nextLine();
        while(DataTest.checkPickUps(input)){
            System.out.println(message);
            input = petFoodGiven.nextLine();
        }
        int validNumInput = Integer.parseInt(input);
        personInfo.setNumRecieved(validNumInput);
    }


    /**
     * 
     * @param addressInput
     *  String input for the address
     * @param message
     *  Message for the user to input the address
     * @param personInfo
     *  Update the user information for thier address
     * 
     *  The purpose for this method to make sure that the information that the user is inputting
     *  is correct for this field. If the data is invalid then the while loop will keep on running
     *  if the data is valid then the loop breaks and updates the owners address field.
     */
    public static void getAddress(Scanner addressInput, String message,owner personInfo){
        System.out.println(message);
        String input = addressInput.nextLine();
        while(DataTest.checkAddress(input)){
            System.out.println(message);
            input = addressInput.nextLine();
        }
    }

    /**
     * 
     * @param strikesInput
     *  Input for strikes
     * @param message
     *  Message to tell the user what to input
     * 
     *  This methods purpos is to make sure that the input that the user is putting is correct
     *  if the the data is invalid then the loop will continu until input is valid. If valid then
     *  the loop will break.
     */
    public static void getStrikes(Scanner strikesInput, String message){
        System.out.println(message);
        String input = strikesInput.nextLine();
        while(DataTest.checkStrikes(input)){
            System.out.println(message);
            input = strikesInput.nextLine();
        }
    }
}