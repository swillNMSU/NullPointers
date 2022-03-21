package src;


/*
Owner class. 
Vars:
    String name
    Proof of low income
    pet(s)
    int numPets
    strikes
    Times they recieved petfood (7 or 8 in a year? Diego knows)
    ???

*/



/*
* Constructor. Generates a default owner object with only the string 'name'.
*/
public class owner {

    private String name, incomeProof;
    public static int numPets, strikes, numRecieved;
    //public static pet[] ownersPets;


    public owner(String _name){
        setName(_name);
        setIncomeProof(null);
        setNumPets(0);
        setStrikes(0);
        setNumRecieved(0);
        //ownersPets = new pet[10];
    }

    // currently allows for only one pet.
    // public void addPet(pet newPet){
    //     ownersPets[numPets++] = newPet;
    // }

    @Override 
    public String toString(){
        return "Name: " + getName() + "\nProof of Income Status: " + getIncomeProof()
        + "\nStrikes: " + getStrikes() + "\nNumber of food withdrawls: " + getNumRecieved() 
        + "\nNumber of Pets: " + getNumPets();
    } 


//#region GET/SET
    public int getNumRecieved() {
        return numRecieved;
    }


    public void setNumRecieved(int numRecieved) {
        this.numRecieved = numRecieved;
    }


    public int getStrikes() {
        return strikes;
    }


    public void setStrikes(int strikes) {
        this.strikes = strikes;
    }


    public int getNumPets() {
        return numPets;
    }


    public void setNumPets(int numPets) {
        this.numPets = numPets;
    }


    public String getIncomeProof() {
        return incomeProof;
    }


    public void setIncomeProof(String incomeProof) {
        this.incomeProof = incomeProof;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

//#endregion

}