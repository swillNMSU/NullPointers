package datavalidation.src;

import java.util.*;

public class DataValidation{

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        // need to test this method with input
        String validFirstName = getPersontName(scan,"Please enter your First name");
        //String valiMiddName = getPersontName(scan, "Please enter your Last name");
        //String validLastName = getPersontName(scan, "Please enter your middle name");
        // this method for last and middle name will be the same as 
        // need to test this method with input
        System.out.println(validFirstName);
        //char validYN = getFixedStatus(scan);
        getNumOfVisit(scan);
    }

    /** This method perameter Are a Scanner called name that will take the input for a string
     *  The other paramter is a string that holds the message for first, middle, and last name
     *  This method ask the user to put first, middle or last name. In the while loop the helper
     *  method is to check if the name that was put was valid. If the name is not valid then the
     *  helper method will return true with a specific method for the error that would have occured.
     *  If the input was correct then the helper method will return false and break out of the loop,
     *  and return the string that was entered by the user.
     * 
     *  Pre Condition: is handle with the helper function called checkname if data is entered invalid
     *  then the user will be notified by the message and be promped to renter the name.
     *  Post Condition: The postcondition for this methos is a string for first, middle, and last name.
    */
    public static String getPersontName(Scanner name,String nametype){
        System.out.println(nametype);
        String inputname = name.nextLine();
        while(checkName(inputname)){
            System.out.println(nametype);
            inputname = name.nextLine();
        }
        return inputname;
    }

    /** This method perameter is called isValidFirstName it got the input from the getPerson name method
     *  each if else if block returns a specif message with the import that has an error. This method checks
     *  to see if isValidName is a correct string input that the user put. If the input is true the while loop
     *  will prompt the user to enter another string. If the string is valid the method will return false and
     *  break out of the loop.
     * 
     *  Precondition: This method checks to see if the perameter isValidName is the correct input
     *  Post: This method returns true if the data is invalid and false if the data is correct.
    */
    public static boolean checkName(String isValidName){
        boolean notValid = true;
        int invalidIntex = 1;
        if(isValidName.isEmpty() || isValidName.isBlank()){
            System.out.println("EMPTY INPUT NOT ALLOWED");
            return true;
        }

        for(int i = 0; i < isValidName.length() && notValid;i++){
            if((isValidName.charAt(i) < 'A' || isValidName.charAt(i) > 'Z') && 
            (isValidName.charAt(i) < 'a' || isValidName.charAt(i) > 'z')){
                notValid = false;
                invalidIntex = i + 1;
            }
        }

        if(!notValid){
            System.out.println("YOU ENTERED A VALUE THAT WAS NOT \"A-Z\" or \"a-z\"");
            System.out.println("The INDEX OF ERROR is at " + invalidIntex);
            return true;
        }
        return false;



    }



    /**
     * This method first ask the user for input and the user puts the input. If the data is invalid then
     * it will return true and keep running the loop until the user inputs valid data. If the data is valid
     * then the loop will end becasue the boolean value return was false. This method would return a char
     * if the input was valid.
     * 
     * Precondition: The precondition is handeled with the help of isValidYesNo if this method return true
     * the value to while loop will keep on running until valid data is put in. If the method isValidYesNo
     * is false then the loop will end.
     * 
     * Postcondition: Is if the input is valid then the method will return a character.
     */
    public static char getFixedStatus(Scanner input){
        System.out.println("Is your dog or cat fixed \"Y\" for yes and \"N\" for No");
        String userInput = input.nextLine().toLowerCase();
        while(isValidYesNo(userInput)){
            System.out.println("Is your dog or cat fixed \"Y\" for yes and \"N\" for No");
            userInput = input.nextLine().toLowerCase();
        }
        return userInput.charAt(0);
    }

    /**
     *  The purpose of this method is to check what the input was a valid input either Yes or No
     *  This method checks to see if the input is empty, and the first character of the input to 
     *  make sure it is a : Y,y,N, or n. If it is not any of these the method will return true and
     *  the loop in getFixedStatus will keep on running unil false.
     * 
     *  Precondition: Is that the parametor inputYOrN my be invalid or not
     *  Postcondition: If the input is invalid then the method will return true, if the input is valid
     *  then the method will return false.
     */
    public static boolean isValidYesNo(String inputYOrN){
        
        if(inputYOrN.isEmpty() || inputYOrN.isBlank()){
            System.out.println("Empty INPUT NOT ALLOWED");
            return true;
        }

        else if(inputYOrN.charAt(0) != 'y' && inputYOrN.charAt(0) != 'n'){
            System.out.println("IN VALID INPUT PLEASE ENTER \"Y\" FOR YES OR \"N\"");
            return true;
        } 

        return false;
        
        

    }

    /**
     * This method purpose it get input from the user. If the input is invalid then the loop will
     * continue to run until proper input is put in. If the input is valid then it will return the
     * input.
     * 
     * Precondition: Is that the input may be invalid if it is then the isValidNumInput method will
     * check if the input is invalid befor sending it off.
     * 
     * Postcondition: This method will return an integer
     */
    public static int getNumOfVisit(Scanner numInput){
        System.out.println("Enter the number of visits for the owner");
        int inputNumber = numInput.nextInt();
        while(isValidNumInput(inputNumber)){
            System.out.println("Enter the number of visits for the owner");
            // going to need to manuplate the object right here
            inputNumber = numInput.nextInt();
        }
        return inputNumber;
    }

    /**
     * This method checks to see if the input is valid or it is not valid at all. This method returns
     * true if the input is not valid, else false if the input is valid.
     * 
     * Precondition: Is that the parametor isValidNumVisit may be invalid or valid
     * Postcondition: Is that the method returns true if invalid and false if the input is valid.
     */
    public static boolean isValidNumInput(int isValidNumVisit){
        if(isValidNumVisit < 0){
            System.out.println("You can not enter a value less then 0");
            return true;
        }

        if(isValidNumVisit > 7){
            System.out.println("You have reached your limit of dog and cat food");
            return true;
        }

        return false;
    }
}