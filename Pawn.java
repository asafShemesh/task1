import java.util.ArrayList;

public class Pawn extends ConcretePiece {


    public Pawn(Player Owner, int Id, int kills, int squares) {
        super(Owner,
                Owner.isPlayerOne() ? "♙" : "♟",
                Owner.isPlayerOne() ? "D" + Id : "A" + Id
                , Id);

    }


}
