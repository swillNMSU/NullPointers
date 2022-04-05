package src;

public class Validator {
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
        if(isEmptyInput(isValidNum))
            return true;
        else if(isNegative(isValidNum))
            return true;
        int isOkayNumInput = Integer.parseInt(isValidNum);
        if(isOkayNumInput > 7){
            System.out.println("You can no longer get food until next year");
            return true;
        }
        return false;
    }

    static boolean checkNumPets(String isValidNumPets){
        if(isEmptyInput(isValidNumPets))
            return true;
        else if(isNegative(isValidNumPets))
            return true;
        int isOkayNumInput = Integer.parseInt(isValidNumPets);
        if(isOkayNumInput == 0){
            System.out.println("You can not have 0 pets");
            return true;
        }
        return false;
    }
}
