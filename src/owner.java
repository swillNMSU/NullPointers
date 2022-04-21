package src;

import java.util.*;

/*
* Constructor. Generates a default owner object with only the string 'name'.
*/
public class Owner {

    private String name, address;
    public int numPets, strikes, numRecieved;
    public boolean isFixed, incomeProof, qualifiedForService;

    public Owner(){}

    public Owner(String _name){
        setName(_name);
        setIncomeProof(false);
        setNumPets(0);
        setStrikes(0);
        setNumRecieved(0);
        setIsFixed(false);
        setAddress(null);
        setQualifiedForService();
    }

    public void setQualifiedForService() {
        if (strikes > 3 || !incomeProof || !isFixed){
            qualifiedForService = false;
        } else qualifiedForService = true;
    }

    @Override 
    public String toString(){
        return "Name: " + getName() + "\nProof of Income Status: " + getIncomeProof()
        + "\nStrikes: " + getStrikes() + "\nNumber of food withdrawls: " + getNumRecieved() 
        + "\nNumber of Pets: " + getNumPets();
    } 

    public void setAllFeilds(String name, String addr, boolean fixed, boolean incProof, int numP, int numR, int strikes){
        setName(name);
        setAddress(addr);
        setIsFixed(fixed);
        setIncomeProof(incProof);
        setNumPets(numP);
        setNumRecieved(numR);
        setStrikes(strikes);
        setQualifiedForService();
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
        setQualifiedForService();
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
        setQualifiedForService();
    }

    public String getAddress(){
        return this.address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getLastName(){
        String[] nameSplit = this.name.split(" "); // TODO: error handle
        return nameSplit[1];
    }

    public Boolean getQualifiedForService() {
        return qualifiedForService;
    }

    /**
     * Returns a string, last name comma first.
     * @return Last, First
     */
    public String getLastThenFirst(){
        String[] nameSplit = this.name.split(" ");
        return nameSplit[1] + ", " + nameSplit[0];
        
    }

    public void setIsFixed(boolean bool){
        this.isFixed=bool;
        setQualifiedForService();
    }

    public boolean getIsFixed(){
        return isFixed;
    }


//#endregion

}



/**
 * Sorts overrides the compare method from Arraylists. Sorts objects alphebetically by last name.
 */
class OwnerComparator implements Comparator<Owner> {
    public int compare(Owner o1, Owner o2){
        if (o1.getLastName() == o2.getLastName())
            return 0;
        else if (o1.getLastName().compareTo(o2.getLastName()) > 0)
            return 1;
        else return -1;
    }
}
