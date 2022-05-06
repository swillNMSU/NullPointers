package src;
import java.io.File;
import java.util.*;

/**
 * This class hold static variables accessed by all other classes.
 */
public class Driver {

  public static List<Owner> owners = new ArrayList<>();
  public static List<Owner> currentArchives = new ArrayList<>();
  public static File writeFile = new File("src/testReset.csv");
  public static String errorMessage;


}
