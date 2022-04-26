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
    
    public boolean checkNameFields(String isValidName){
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
            System.out.print("YOU ENTERED A VALUE THAT WAS NOT \"A-Z\" or \"a-z\": ");
            System.out.println("The error is at " + invalidIntex);
            return true;
        }
        return false;
    }

    public boolean checkPickUps(String isValidNum) {
        boolean notValidPickUps = true;
        int errorIndex = 1;
        if(isEmptyInput(isValidNum))
            return true;

        for(int i = 0; i < isValidNum.length() && notValidPickUps;i++){
            if(isValidNum.charAt(0) == '-'){
                System.out.print("No negative numbers are allowed: ");
                System.out.println("Error at " + errorIndex);
                return true;
            }

            if(isValidNum.charAt(i) < '0' || isValidNum.charAt(i) > '9'){
                errorIndex = errorIndex + i;
                notValidPickUps = false;
            }
        }

        if(!notValidPickUps){
            System.out.print("You can not have letter or any symboles in this field: ");
            System.out.println("Error at " + errorIndex);
            return true;
        }
    
        int isOkayNumInput = Integer.parseInt(isValidNum);
        if(isOkayNumInput > 8){
            System.out.println("You can no longer get food until next year");
            return true;
        }
        return false;
    }

    public boolean checkNumPets(String isValidNumPets){
        boolean notValidPetNum = true;
        int errorIndex = 1;
        if(isEmptyInput(isValidNumPets))
            return true;

        for(int i = 0; i < isValidNumPets.length() && notValidPetNum;i++){
            if(isValidNumPets.charAt(0) == '-'){
                System.out.print("Negative numbers are not allowed: ");
                System.out.println("Error at " + errorIndex);
                return true;
            }
            if(isValidNumPets.charAt(i) < '0' || isValidNumPets.charAt(i) > '9'){
                notValidPetNum = false;
                errorIndex = errorIndex + i;
            }
        }
        if(!notValidPetNum){
            System.out.print("Invalid input can not have letters or characters: ");
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

    public boolean checkAddress(String isValidAddress){
        boolean notValidAddress = true;
        int invalidIndex = 1;
        if(isEmptyInput(isValidAddress))
            return true;

        for(int i = 0; i < isValidAddress.length() && notValidAddress; i++){
            if(isValidAddress.charAt(i) != ' ' && (isValidAddress.charAt(i) < 'A' || isValidAddress.charAt(i) > 'Z')
                && (isValidAddress.charAt(i) < 'a' || isValidAddress.charAt(i) > 'z') && (isValidAddress.charAt(i) < '0' || 
                isValidAddress.charAt(i) > '9') && isValidAddress.charAt(i) != '.'
            ){
                notValidAddress = false;
                invalidIndex = i + invalidIndex;
            }
        }

        if(!notValidAddress){
            System.out.print("Invalid Addres put: ");
            System.out.println("Error at " + invalidIndex);
            return true;
        }

        return false;
    }
}
