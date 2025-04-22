import java.util.Random;

public class DotChaser {
  private static Random rand = new Random(System.currentTimeMillis());

  /**
   * This static method is ok :)
   */
  public static void main(String[] args) {
    int N = 200;

    if (args.length != 0)
      N = Integer.parseInt(args[0]);

    ThingList list = new ThingList();
    int count = 0;

    while (true) {
      // Every N rounds, add another typeA and typeB Thing.
      if (count % N == 0) {

        // Create a new typeA thing and add to the list
        Thing tA = new TypeA(45, 50);
        list.addThing(tA);

        // New typeB thing
        Thing tB = new TypeB(55, 50);
        list.addThing(tB);
        
        // New typeC thing
        Thing tC = new TypeC(35, 60);
        list.addThing(tC);

      }
      list.printAll();
      list.moveAll(rand);
      count++;
    }
  }
}
