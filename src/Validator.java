package src;


/** This class validator is what was used to check the input from the user and make sure
 *  That the files that creat the Owner object have valid data. If the user ever inputs
 *  invalid data then these methods will repromp the user to renter input until it is 
 *  valid.
 */
public class Validator {
    /**
     * 
     * @param isItEmpty
     *  This parameter is the string input from the user
     * @return 
     *  returns a boolean true or false
     *  
     *  Precondition: The precondition for this method is that the parameter may be empty
     *  or not.
     * 
     *  Postcondition: The postcondition is that if the string is empty this method will
     *  return true. If the input is not empty then the method with return false
     * 
     *  This method responsabilty is to check if the users input is empty then it will return
     *  true, and if the input is not empty then it will return false. This method is a helper
     *  method that will be called in the other data validation method.
     */
    static boolean isEmptyInput(String isItEmpty) {
        if (isItEmpty.isEmpty() || isItEmpty.isBlank()) {
            System.out.println("EMPTY INPUT NOT ALLOWED");
            return true;
        }
        return false;
    }

    /**
     * 
     * @param isItNegative
     *  This parameter is the string input from the user
     * @return
     *  returns a boolean true or false
     * 
     *  Precondition: The precondition for this method is that the parameter may be empty 
     *  or not.
     * 
     *  Postcondition: If the number is negative it will return true, and if the number
     *  is positive then the method will return false.
     * 
     *  This method converts the user number input from a string to an integer. If the number
     *  is negative then it return true, and if the number is positive then it will return false
     */
    static boolean isNegative(String isItNegative) {
        int checkStrtoNum = Integer.parseInt(isItNegative);
        if (checkStrtoNum < 0) {
            System.out.println("Negative numbers are not allowed");
            return true;
        }
        return false;
    }

    /**
     * 
     * @param isValidName
     *  This parameter is the string input from the user
     * @return
     *  returns a boolean true or false
     * 
     *  Precondition: Is that the parameter may be empty or it might not be
     * 
     *  Postcondition: This method returns true if the users name input is not valid. Other wise
     *  this method will return false if the user name input is correct.
     * 
     *  This method main purpose is to check the users input to see if the name is valid or not
     *  The first check is to see if the user entered an empty string if the string is empty the
     *  input is invalid. The next check is to see if the name has any invalid charcters. If the name
     *  has any other chacter that is not A-Z or a-z then it will set notvalid to false and get the
     *  index were it was not valid. If the name input was invalid then it will print the index were
     *  it was invalid and it will display a message to tell the user that the input was invalid and were.
     *  At this point method will be true. If the input is valid then it will return false.
     *  
     */
    public boolean checkNameFields(String isValidName) {
        boolean notValid = true;
        int invalidIntex = 1;

        if (isEmptyInput(isValidName)) {
            Driver.errorMessage = "Name cannot be empty.";
            return true;
        }
        //name cannot start or end with space.
        if (isValidName.charAt(0) == ' ' || isValidName.charAt(isValidName.length() - 1) == ' ') {
            Driver.errorMessage = "Cannot start or end with space.";
            return true;
        }
        
        for (int i = 0; i < isValidName.length() && notValid; i++) {
            if (isValidName.charAt(i) != ' ' && (isValidName.charAt(i) < 'A' || isValidName.charAt(i) > 'Z') &&
                    (isValidName.charAt(i) < 'a' || isValidName.charAt(i) > 'z')) {
                notValid = false;
                invalidIntex = i + 1;
            }

        }

        if (!notValid) {
            Driver.errorMessage = "Names must only contain letters.";
            // System.out.print("YOU ENTERED A VALUE THAT WAS NOT \"A-Z\" or \"a-z\": ");
            // System.out.println("The error is at " + invalidIntex);
            return true;
        }
        return false;
    }


    /**
     * 
     * @param isValidNum
     *  Is a string input for the user that is a number
     * @return
     *  returns a boolean true or false
     * 
     *  Precondition: This method is parameter is a string that could be empty or not
     *  
     *  Postcondition: This method returns true if the method is invalid or false if
     *  the input is valid.
     * 
     *  This method main purpose is to check if the string that the user input is empty or
     *  not if it is then return true for invalid input. The next check is in the for loop
     *  this checks to see if the string input is just number and not anything else, it also
     *  looks for the symbole - for negative numebrs. If the numebr had some other charater it
     *  reurns true. Else the string is converted to a number and if the number enter is greater
     *  than 8 return true, else it is a valid input.
     */
    public boolean checkPickUps(String isValidNum) {
        boolean notValidPickUps = true;
        int errorIndex = 1;
        if (isEmptyInput(isValidNum))
            return true;

        for (int i = 0; i < isValidNum.length() && notValidPickUps; i++) {
            if (isValidNum.charAt(0) == '-') {
                Driver.errorMessage = "No negative numbers are allowed";
                System.out.println("Error in name at index " + errorIndex);
                return true;
            }

            if (isValidNum.charAt(i) < '0' || isValidNum.charAt(i) > '9') {
                Driver.errorMessage = "Only digits are allowed.";
                errorIndex = errorIndex + i;
                notValidPickUps = false;
            }
        }

        if (!notValidPickUps) {
            Driver.errorMessage = "You can not have letter or any symboles in this field: ";
            System.out.println("Error at " + errorIndex);
            return true;
        }

        int isOkayNumInput = Integer.parseInt(isValidNum);
        if (isOkayNumInput > 8) {
            System.out.println("You can no longer get food until next year");
            return true;
        }
        return false;
    }

    /**
     * 
     * @param isValidStrikes
     *  Is a string that the user inputs
     * @return
     *  returns true if invalid and false if tehe input is valid
     * 
     *  Precondition: The string input from the parameter isValidStriked can be empty or not
     * 
     *  Postcondtion: The post condition for this method is that it return true if the date is
     *  invalid, and will return false if the data is valid.
     * 
     *  This method is used to check the strikes that each owner has gotten. The input is a string
     *  so we check to see if it is empty, if it is we return true, else false. The for loop goes
     *  through the input string and checks for the negative symbol or another character that is not
     *  a number. If it finds any of these it will return true. If none of these character are found and
     *  it is just numbers then it will return false for valid input.
     */
    public boolean checkStrikes(String isValidStrikes) {
        boolean isInValid = true;
        int invalidInput = 1;
        
        if (isValidStrikes.isEmpty()) {
            Driver.errorMessage = "You can not have an empty value";
            return true;
        }
        
        for (int i = 0; i < isValidStrikes.length() && isInValid; i++) {
            if (isValidStrikes.charAt(0) == '-') {
                Driver.errorMessage = "Negative numbers are not allowed.";
                System.out.println("Error at " + invalidInput);
                return true;
            }
            if (isValidStrikes.charAt(i) < '0' || isValidStrikes.charAt(i) > '9') {
                invalidInput = i + invalidInput;
                isInValid = false;
            }
        }
        
        if (!isInValid) {
            Driver.errorMessage = "You have an invalid input at character " + invalidInput;
            return true;
        }
        
        int checkNumber = Integer.parseInt(isValidStrikes);
        if (checkNumber < 0) {
            Driver.errorMessage = "Strikes cannot be negative.";
            return true;
        }
        else if (checkNumber > 3) {
            System.out.println("You can not get any pet food after you show proof");
            return true;
        }
        return false;
    }

    /**
     * 
     * @param isValidNumPets
     *  This parameter is a string input from the user
     * @return
     *  This method return true for invalid input and also false for invalid input
     * 
     *  Precondition: The string parameter might be empty or it may be valid
     *  Postcondition:  This method returns true if the data is invalid, and false if the data
     *  is valid.
     * 
     *  The first check in this method is to see if the string input is empty if it is return true.
     *  The next check is to go through the string input and check for a - sign and also make sure that 
     *  the input does not have an invalid charcter any where in the input. If all of the charcter dont mess up
     *  then the next step is to convert the string into a number. Then we check the number to make sure that 
     *  the number is greater then zero. 
     */
    public boolean checkNumPets(String isValidNumPets) {
        boolean notValidPetNum = true;
        int errorIndex = 1;
        if (isEmptyInput(isValidNumPets))
            return true;

        for (int i = 0; i < isValidNumPets.length() && notValidPetNum; i++) {
            if (isValidNumPets.charAt(0) == '-') {
                Driver.errorMessage = "Negative numbers are not allowed";
                System.out.println("Error at " + errorIndex);
                return true;
            }
            if (isValidNumPets.charAt(i) < '0' || isValidNumPets.charAt(i) > '9') {
                notValidPetNum = false;
                errorIndex = errorIndex + i;
            }
        }
        if (!notValidPetNum) {
            Driver.errorMessage = "Invalid input can not have letters or characters";
            System.out.println("Error at " + errorIndex);
            return true;
        }

        // We should allow zero pets. Just because someone doesn't qualify it doesn't mean they shouldn't be in the records.
        //int isOkayNumInput = Integer.parseInt(isValidNumPets);
        // if (isOkayNumInput == 0) {
        //     Driver.errorMessage = "You can not have 0 pets";
        //     return true;
        // }
        return false;
    }


    /**
     * 
     * @param isValidAddress
     *  This parameter is a string input from the user
     * @return
     *  This method return true for invalud data and false for valid data
     * 
     *  Precondition: This method precondition is that the parameter might be emptet or it
     *  might have characters in the string.
     * 
     *  Postcondition: Returns true for invalid data and false for valid data.
     * 
     *  This methods main purpose is tp check the address input strign from the user. If the input
     *  is empty return true. The for loop in this method checks to see if the characters in the string
     *  are valid. If they are not valid then this method will return true, else it will return false for valid input.
     */
    public boolean checkAddress(String isValidAddress) {
        boolean notValidAddress = true;
        int invalidIndex = 1;
        if (isEmptyInput(isValidAddress))
            return true;

        if (isValidAddress.contains(",")){
            Driver.errorMessage = "Cannot contain comma";
        }

        for (int i = 0; i < isValidAddress.length() && notValidAddress; i++) {
            if (isValidAddress.charAt(i) != ' ' && (isValidAddress.charAt(i) < 'A' || isValidAddress.charAt(i) > 'Z')
                    && (isValidAddress.charAt(i) < 'a' || isValidAddress.charAt(i) > 'z')
                    && (isValidAddress.charAt(i) < '0' ||
                            isValidAddress.charAt(i) > '9')
                    && isValidAddress.charAt(i) != '.') {
                notValidAddress = false;
                invalidIndex = i + invalidIndex;
            }
        }

        if (!notValidAddress) {
            System.out.print("Invalid Addres put: ");
            System.out.println("Error at " + invalidIndex);
            return true;
        }

        return false;
    }
}
