import java.util.ArrayList;

public abstract class ConcretePiece implements Piece {
    private final String type;
    private final Player Owner;
    private String id_piece;
    private int id_num;
    private int kills;

    private int squares;
    private ArrayList<Position> steps = new ArrayList<>();

    public ConcretePiece(Player Owner, String type, String id, int newId) {
        this.Owner = Owner;
        this.type = type;
        this.id_piece = id;
        this.id_num = newId;
        this.kills = kills;
        this.squares = squares;

    }

    public String getId_piece() {
        return this.id_piece;
    }

    @Override
    public Player getOwner() {
        return Owner;
    }

    @Override
    public String getType() {
        return this.type;
    }

    public void Add(Position position) {
        steps.add(position);

    }

    public int getSquares() {
        return squares;
    }

    public void setSquares(int squares) {
        this.squares += squares;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public ArrayList<Position> getSteps() {
        return steps;
    }


    public int getId_num() {
        return id_num;
    }

    public void setSteps(ArrayList<Position> steps) {
        this.steps = steps;
    }

}
