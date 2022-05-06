package datavalidation.src;
/**
 *  The class that helps creat an Owner object
 */
public class Owner {

    private String name, incomeProof;
    public int numPets, strikes, numRecieved;
    public boolean isFixed;

    /**
     *  The owners class constructer that calls the set methods
     */
    public Owner(String _name){
        setName(_name);
        setIncomeProof(null);
        setNumPets(0);
        setStrikes(0);
        setNumRecieved(0);
        setIsFixed(false);
    }

    /**
     *  The to string method that prints out the owners information
     */
   
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

    public void setIsFixed(boolean bool){
        this.isFixed=bool;
    }

    public boolean getIsFixed(){
        return isFixed;
    }


}
