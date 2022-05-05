package src.datavalidation;

import java.util.*;
public class driver{
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

    public static void getPersontName(Scanner name,String message, owner personsInfoOwner){
        System.out.println(message);
        String inputname = name.nextLine();
        while(DataTester.checkNameFields(inputname)){
            System.out.println(message);
            inputname = name.nextLine();
            
        }
        personsInfoOwner.setName(inputname);
    }

    
    public static void getPersonIncome(Scanner incom, String message,owner personInfo){
        System.out.println(message);
        String incomeInput = incom.nextLine();
        while(DataTest.checkNameFields(incomeInput)){
            System.out.println(message);
            incomeInput = incom.nextLine();
        }
        personInfo.setIncomeProof(incomeInput);
    }

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


    public static void getAddress(Scanner addressInput, String message,owner personInfo){
        System.out.println(message);
        String input = addressInput.nextLine();
        while(DataTest.checkAddress(input)){
            System.out.println(message);
            input = addressInput.nextLine();
        }
    }

    public static void getStrikes(Scanner strikesInput, String message){
        System.out.println(message);
        String input = strikesInput.nextLine();
        while(DataTest.checkStrikes(input)){
            System.out.println(message);
            input = strikesInput.nextLine();
        }
    }
}