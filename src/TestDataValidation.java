package src;
import java.util.*;
public class TestDataValidation{
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
     *  Post: This method displays an error message and returns true if the data is invalid and 
     *  false if the data is correct.
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

    /** This methods paremeter is a Scanner type called input. This method ask the user to input the status
     *  of the pet Y for fixed and N for not fixed. This method used a helper method to check if the input
     *  is correct. If the input is not correct then in the while loop the user is prompted to enter the same
     *  input that was asked if the input is correct then the method will return a charcter y for fix and n
     *  for not fixed.
     * 
     *  Precondition: This method uses a scanner for a string input and the method isValidYesNo checks to see
     *  if the user input is correct
     *  
     *  Postcondition: Once the method isValidYesNo make sure that the input is correct then this method returns
     *  the charcter Y for yes and N for no.
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

    /** The method isValidYesNo perameter is a string called inputYOrN from the user. This method checks to see
     *  if the perameter is valid. If it is not valid then this method will print the specific error that it got
     *  and return true. If the input is valid then then the method will return false.
     * 
     * Precondition: The user input called inputYOrN might not be valid
     * 
     * Postcondition: If the input form the user is not valid then the method will display the error message
     * and also return true. If valid then the method will return false.
     * 
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

    /** This methods perameters is a Scanner called numInput. This method ask the user to input a number and the
     *  helper method call isValidNumInput checks to see if the number that they put is a valid number. If the 
     *  input is not valid then in the while loop the user will be promped to enter another valid number. If the 
     *  input is valid then this method will return an interger that was valid.
     * 
     *  Precondition: This methods perametor is a Scanner called numInput we do not know what the user is going to
     *  put, so we call isValidNumInput to check if the user input is correct.
     *  Postcondition: If the input is valid through isValidNumInput then this method will return an integer 
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

    /** This method perameter is an interger that was input from the user. This method checks to see if the user input
     *  is valid. If the input is not valid then an error message will go with what the invalid input was, and it will
     *  return true. If the input is valid then this method will return false.
     * 
     *  Precondition: The perameter isValidNumVisit we do not know if what the user put is a valid number.
     *  Postcondition: If the user inputs a valid number then this method returns false, if not valid then
     *  the method will display an error message as well as return true.
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