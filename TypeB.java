import java.util.Random;

public class TypeB extends Thing {
    private int timeSinceLast;

    public TypeB(int row, int col) {
        super(row, col);
        this.lab = 'b';
        this.timeSinceLast = 0;
    }
    
    public void maybeTurn(Random rand) {
        int i = rand.nextInt(3);
        this.timeSinceLast++;
        if (this.timeSinceLast == 10) {
            this.timeSinceLast = 0;
            if (i == 1) {
                this.rightTurn();
            }
            if (i == 2) {
                this.leftTurn();
            }
        }
    }
}
