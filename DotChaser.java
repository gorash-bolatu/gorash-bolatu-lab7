import java.util.*;

public class DotChaser {
  public static Random rand = new Random(System.currentTimeMillis());

  /**
   * A "Thing" moves in a grid world. A TypeA Thing randomly
   * decides to turn left or right (or not turn) every "round",
   * and, afterward, takes a step forward. A TypeB Thing
   * only considers making a random turn every 10th round.
   */
  public static class Thing {
    // dir: 0=North, 1=East, 2=South, 3=West.
    // timeSinceLast: this is only important for "TypeB" Things.
    public int row, col, dir, timeSinceLast;
    public char lab = 'r';
    public boolean isTypeB;
  }

  private static class ThingNode {
    public Thing data;
    public ThingNode next;

    public ThingNode(Thing thing) {
      this.data = thing;
      this.next = null;
    }

    public String toString() {
      return this.data.row + " " + this.data.col + " " + this.data.lab;
    }
  }

  /**
   * This class is for linked lists of Thing's
   */
  public static class ThingList {
    private ThingNode head;

    public ThingList() {
      this.head = null;
    }

    public void add(Thing thing) {
      if (this.head != null) {
        ThingNode link = head;
        while (link.next != null)
          link = link.next;
        link.next = new ThingNode(thing);
      } else {
        this.head = new ThingNode(thing);
      }
    }

    public void printAll() {
      for (ThingNode T = head; T != null; T = T.next)
        System.out.println(T.toString());
      System.out.println("done");
      System.out.flush();
    }

    public void moveAll() {
      for (ThingNode T = head; T != null; T = T.next) {
        maybeTurn(T.data);
        step(T.data);
      }
    }
  }

  // EEEEEK! STATIC METHODS!!! PLEASE FIND THEM A BETTER HOME.
  public static void rightTurn(Thing t) {
    t.dir = (t.dir + 1) % 4;
  }

  public static void leftTurn(Thing t) {
    t.dir = (t.dir + 3) % 4;
  }

  public static void maybeTurn(Thing t) {
    int i = rand.nextInt(3);

    if (t.isTypeB) {
      t.timeSinceLast++;

      if (t.timeSinceLast == 10) {
        t.timeSinceLast = 0;

        if (i == 1) {
          rightTurn(t);
        }

        if (i == 2) {
          leftTurn(t);
        }
      }
    } else {
      if (i == 1) {
        rightTurn(t);
      }

      if (i == 2) {
        leftTurn(t);
      }
    }
  }

  public static void step(Thing t) {
    final int[] dc = {
        0, 1, 0, -1
    }, dr = {
        1, 0, -1, 0
    };
    t.row += dr[t.dir];
    t.col += dc[t.dir];
  }

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

        // Add a typeA thing to the list.
        // (GEE, THAT'S A LOT OF CODE FOR JUST CREATING ONE THING)
        Thing tA = new Thing();
        tA.row = 45;
        tA.col = 50;
        list.add(tA);

        // Add a typeB thing to the list
        Thing tB = new Thing();
        tB.row = 55;
        tB.col = 50;
        tB.lab = 'b';
        tB.isTypeB = true;
        list.add(tB);
      }

      count++;
    }
  }
}
