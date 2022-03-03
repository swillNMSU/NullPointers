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
    private int numPets, strikes, numRecieved;
    private pet[] ownersPets = new pet[10];


    public owner(String _name){
        setName(_name);
        setIncomeProof(null);
        setNumPets(0);
        setStrikes(0);
        setNumRecieved(0);
    }

    // currently allows for only one pet.
    public void addPet(pet newPet){
        ownersPets[0] = newPet;
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