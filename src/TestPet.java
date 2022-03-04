package src;

public class TestPet {

    public static void main (String[] args) {

        System.out.println("Testing Pet Class:");
        pet test1 = new pet("Daisy", "Pitbull", true);

        System.out.println("First Test: " + test1.toString());

        test1.setName("Caliber");
        test1.setBreed("Poodle");
        test1.setFixedPet(false);

        System.out.println("\nUpdating name, breed and if fixed:" + test1.toString());

        System.out.println("\nTesting Get Methods: " + "\n\tName: " + test1.getName() + "\n\tBreed: " + test1.getBreed() + "\n\tIf Fixed: " + test1.getFixedPet());
    }
}