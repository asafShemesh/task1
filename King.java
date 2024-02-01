import java.util.ArrayList;

public class King extends ConcretePiece {
    private Position pos;

    public King(Player Owner, Position position, int squares) {
        super(Owner, "♔", "K7", 7);
        this.pos = position;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }


}
