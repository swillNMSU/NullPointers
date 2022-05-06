//package datavalidation;
/**
 *  This is the Pet class that creats pet objects
 */
public class Pet {
    private boolean fixedPet;
    private String name;
    private String breed;


    /**
     *  The constructer that creats a new Pet object
     */
    public Pet(String nm, String brd, boolean isFixed){
        this.name = nm;
        this.breed = brd;
        this.fixedPet = isFixed;
    }

    /**
     *  The set and getters for the Pet Object
     */
    public void setName(String nm) {
        this.name = nm;
    }

    public String getName() {
        return name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
    public String getBreed() {
        return breed;
    }

    public void setFixedPet(boolean fixedPet) {
        this.fixedPet = fixedPet;
    }
    
    public boolean getFixedPet(){
        return fixedPet;
    }

    @Override
    public String toString() {
        return  "\n\tName: " + this.name + "\n\tType: " + this.breed + "\n\tFixed: " + this.fixedPet + "\n";
    }
}
