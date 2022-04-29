package src.datavalidation;

import src.Owner;

public class DVO {
    Owner ownerInformation;
    static boolean isEmptyInput(String isItEmpty){
        if(isItEmpty.isEmpty() || isItEmpty.isBlank()){
            System.out.println("EMPTY INPUT NOT ALLOWED");
            return true;
        }
        return false;
    }

    static boolean isNegative(String isItNegative){
        int checkStrtoNum = Integer.parseInt(isItNegative);
        if(checkStrtoNum < 0){
            System.out.println("Negative numbers are not allowed");
            return true;
        }
        return false;
    }
    
    static boolean checkNameFields(String isValidName){
        boolean notValid = true;
        int invalidIntex = 1;

        if(isEmptyInput(isValidName))
            return true;

        for(int i = 0; i < isValidName.length() && notValid;i++){
            if(isValidName.charAt(i) != ' ' && (isValidName.charAt(i) < 'A' || isValidName.charAt(i) > 'Z') && 
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

    static boolean checkPickUps(String isValidNum){
        boolean NotValidPickUps = true;
        int errorIndex = 1;
        if(isEmptyInput(isValidNum))
            return true;

        for(int i = 0; i < isValidNum.length() && NotValidPickUps;i++){
            if(isValidNum.charAt(0) == '-'){
                System.out.print("No negative numbers are allowed: ");
                System.out.println("Error at " + errorIndex);
                return true;
            }
            if(isValidNum.charAt(i) < '0' || isValidNum.charAt(i) > '9'){
                
                errorIndex = errorIndex + i;
                NotValidPickUps = false;
                
            }
        }

        if(!NotValidPickUps){
            System.out.print("You can not have letter or any symboles in this field: ");
            System.out.println("Error at " + errorIndex);
            return true;
        }
       
        int isOkayNumInput = Integer.parseInt(isValidNum);
        if(isOkayNumInput > 7){
            System.out.println("You can no longer get food until next year");
            return true;
        }    
        return false;
    }

    static boolean checkNumPets(String isValidNumPets){
        boolean NotValidPetNum = true;
        int errorIndex = 1;
        if(isEmptyInput(isValidNumPets))
            return true;
       
        for(int i = 0; i < isValidNumPets.length() && NotValidPetNum ;i++){
            if(isValidNumPets.charAt(0) == '-'){
                System.out.print("Negative numbers are not allowed: ");
                System.out.println("Error at " + errorIndex);
                return true;
            }
            if(isValidNumPets.charAt(i) < '0' || isValidNumPets.charAt(i) > '9'){
                NotValidPetNum = false;
                errorIndex = errorIndex + i;
            }
        }

        if(!NotValidPetNum){
            System.out.print("Invalid input can not have letter or characters: ");
            System.out.println("Error at " + errorIndex);
            return true;
        }
        int isOkayNumInput = Integer.parseInt(isValidNumPets);
        if(isOkayNumInput == 0){
            System.out.println("You can not have 0 pets");
            return true;
        }
        return false;
    }

    static boolean checkAddress(String isValidAddress){
        boolean notValidAddress = true;
        int invalidIndex = 1;
        if(isEmptyInput(isValidAddress))
            return true;

        for(int i = 0; i < isValidAddress.length() && notValidAddress; i++){
            if(isValidAddress.charAt(i) != ' ' && (isValidAddress.charAt(i) < 'A' || isValidAddress.charAt(i) > 'Z')
                && (isValidAddress.charAt(i) < 'a' || isValidAddress.charAt(i) > 'z') && ((isValidAddress.charAt(i) < '0' || 
                isValidAddress.charAt(i) > '9') && isValidAddress.charAt(i) != '.')
            ){
                notValidAddress = false;
                invalidIndex = i + invalidIndex;
            }
        }

        if(!notValidAddress){
            System.out.print("Invalid Addres put");
            System.out.println("The error is at " + invalidIndex);
            return true;
        }

        return false;
    }  

    // this is what is needed for checking the strikes
    static boolean checkStrikes(String isValidStrikes){
        boolean isInValid = true;
        int invalidInput = 1;

        // need to check if empty
        if(isValidStrikes.isEmpty()){
            System.out.println("You can not have an empty value");
            return true;

        }
        // if string not empty then go through each index find the error
        for(int i = 0; i < isValidStrikes.length()  && isInValid;i++){

            if(isValidStrikes.charAt(0) == '-'){
                System.out.print("Negative numbers are not allowed: ");
                System.out.println("Error at " + invalidInput);
                return true;
            }

            if(isValidStrikes.charAt(i) < '0' || isValidStrikes.charAt(i) > '9'){
                invalidInput = i + invalidInput;
                isInValid = false;
            }
        }

        // first if to check if the method is invalid from the string.
        if(!isInValid){
            System.out.print("You have an invalid input at " + invalidInput);
            return true;
        }

        // The string of input is okay
        int checkNumber = Integer.parseInt(isValidStrikes);

        if(checkNumber < 0){
            System.out.println("You can not have a negative number of strikes");
            return true;
        }

        else if(checkNumber > 3){
            System.out.println("You can not get any pet food after you show proof");
            return true;
        }
        return false;
    }

    /*static boolean checkStrikes(String isValidYOrN,owner personInfo){
        if(isEmptyInput(isValidYOrN))
            return true;
        for(int i = 0; i < isValidYOrN.length();i++){
            if(isValidYOrN.charAt(0) != 'y' || isValidYOrN.charAt(0) == 'n'){
                System.out.println("Invalid input for input status please change");
                return true;
            }
        }

        if(personInfo.getStrikes() >= 3){
            System.out.println("This owener has meet thier strikes");
            return true;
        }

        return false;
    }*/
}
