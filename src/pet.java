package src;
/*
Class that represents pets.
VARS:
    bool spayed/neutered
    string name
    string breed?
*/

public class pet {
    private boolean fixedPet;
    private String name;
    private String breed;


    public pet(String nm, String brd){
        this.name = nm;
        this.breed = brd;
        this.fixedPet = false;
    }

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

    

}