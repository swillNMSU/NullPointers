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
public class Owner {

    private String name;
    public int numPets, strikes, numRecieved;
    public boolean isFixed, incomeProof;
    //public static pet[] ownersPets;

    public Owner(){}

    public Owner(String _name){
        setName(_name);
        setIncomeProof(false);
        setNumPets(0);
        setStrikes(0);
        setNumRecieved(0);
        setIsFixed(false);
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


    public boolean getIncomeProof() {
        return incomeProof;
    }


    public void setIncomeProof(boolean incomeProof) {
        this.incomeProof = incomeProof;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setIsFixed(boolean bool){
        this.isFixed=bool;
    }

    public boolean getIsFixed(){
        return isFixed;
    }

//#endregion

}