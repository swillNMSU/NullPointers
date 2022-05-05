package src;

public class TestOwner {
    public static void main (String[] args) {
        // Test the contructor of Owner
        // Should set all parameters to default values
        Owner test = new Owner();
        test.toString();

        // Testing the set All Fields method in Owner
        test.setAllFeilds("David Rogers", "313 South Lane, Albuquerque, NM", true, true, 2, 0, 0);
        test.toString();

        // After creating the profile we want to check if we can alter and access data
        test.setName("Robert Downey");
        System.out.println("Owner Name:" + test.getName());

        test.setAddress("400 Right Here, Las Cruces, NM");
        System.out.println("Owner Address:" + test.getAddress());

        test.setIsFixed(false);
        System.out.println("Owner if pet is fixed:" + test.getIsFixed());

        test.setIncomeProof(false);
        System.out.println("Owner if has proof of income:" + test.getIncomeProof());

        test.setNumPets(1);
        System.out.println("Owner Number of pets:" + test.getNumPets());

        test.setNumRecieved(2);
        System.out.println("Owner Amount Recieved:" + test.getNumRecieved());

        test.setStrikes(1);
        System.out.println("Owner Amount of Strikes:" + test.getStrikes());

        // Proof of income, strikes and if fixed also change 
        // eligibility so check Qualified for service method
        System.out.println("Is Owner Eligibile:" + test.getQualifiedForService());

        System.out.println("Print Owners Name (Last then First)" + test.getLastThenFirst());
    }
}