import java.util.Random;;

/**
 * This class is for linked lists of Thing's
 */
public class ThingList {
    private class Node {
        public Thing data;
        public Node next;

        public Node(Thing thing) {
          this.data = thing;
          this.next = null;
        }

        public String toString() {
            return this.data.row + " " + this.data.col + " " + this.data.lab;
        }
    }

    private Node head;

    public ThingList() {
        this.head = null;
    }

    public void addThing(Thing thing) {
        if (this.head != null) {
            Node link = head;
            while (link.next != null)
                link = link.next;
            link.next = new Node(thing);
        } else {
            this.head = new Node(thing);
        }
    }

    public void printAll() {
        for (Node T = head; T != null; T = T.next)
            System.out.println(T.toString());
        System.out.println("done");
        System.out.flush();
    }

    public void moveAll(Random rand) {
        for (Node T = head; T != null; T = T.next) {
            T.data.maybeTurn(rand);
            T.data.step();
        }
    }
}