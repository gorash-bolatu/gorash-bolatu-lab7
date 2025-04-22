import java.util.Random;

public class TypeC extends Thing {

    private boolean last_turn_is_left = false;

    public TypeC(int row, int col) {
        super(row, col);
        lab = 'y';
    }

    public void maybeTurn(Random rand) {
        int i = rand.nextInt(9);
        if (i == 0)
            last_turn_is_left = !last_turn_is_left;
        if (last_turn_is_left) {
            rightTurn();
            last_turn_is_left = false;
        } else {
            leftTurn();
            last_turn_is_left = true;
        }
    }
}
