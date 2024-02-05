import java.lang.Math;
import java.util.Comparator;
import java.util.*;

public class GameLogic implements PlayableLogic {
    private ConcretePiece[][] board = new ConcretePiece[11][11];
    private Position[][] pieces = new Position[11][11];

    private ArrayList<ConcretePiece> ARR = new ArrayList<>();

    public ConcretePlayer player1 = new ConcretePlayer(true, 0);
    public ConcretePlayer player2 = new ConcretePlayer(false, 0);
    public boolean Turn = false;
    private King king;
    static private ConcretePlayer winner1 = null;

    public GameLogic() {
        initBoard();
    }

    static ArrayList<ConcretePiece> arr = new ArrayList<>();

    private void initBoard() {
        arr.clear();
        ARR.clear();
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                board[i][j] = null;
                pieces[i][j] = null;


            }
        }
        Turn = false;
        board[3][0] = new Pawn(player2, 1, 0, 0);
        board[4][0] = new Pawn(player2, 2, 0, 0);
        board[5][0] = new Pawn(player2, 3, 0, 0);
        board[6][0] = new Pawn(player2, 4, 0, 0);
        board[7][0] = new Pawn(player2, 5, 0, 0);
        board[5][1] = new Pawn(player2, 6, 0, 0);
        board[0][3] = new Pawn(player2, 7, 0, 0);
        board[10][3] = new Pawn(player2, 8, 0, 0);
        board[0][4] = new Pawn(player2, 9, 0, 0);
        board[10][4] = new Pawn(player2, 10, 0, 0);
        board[0][5] = new Pawn(player2, 11, 0, 0);
        board[1][5] = new Pawn(player2, 12, 0, 0);
        board[9][5] = new Pawn(player2, 13, 0, 0);
        board[10][5] = new Pawn(player2, 14, 0, 0);
        board[0][6] = new Pawn(player2, 15, 0, 0);
        board[10][6] = new Pawn(player2, 16, 0, 0);
        board[0][7] = new Pawn(player2, 17, 0, 0);
        board[10][7] = new Pawn(player2, 18, 0, 0);
        board[5][9] = new Pawn(player2, 19, 0, 0);
        board[3][10] = new Pawn(player2, 20, 0, 0);
        board[4][10] = new Pawn(player2, 21, 0, 0);
        board[5][10] = new Pawn(player2, 22, 0, 0);
        board[6][10] = new Pawn(player2, 23, 0, 0);
        board[7][10] = new Pawn(player2, 24, 0, 0);
        board[5][3] = new Pawn(player1, 1, 0, 0);
        board[4][4] = new Pawn(player1, 2, 0, 0);
        board[5][4] = new Pawn(player1, 3, 0, 0);
        board[6][4] = new Pawn(player1, 4, 0, 0);
        board[3][5] = new Pawn(player1, 5, 0, 0);
        board[4][5] = new Pawn(player1, 6, 0, 0);
        board[5][5] = new King(player1, new Position(5, 5), 0);
        king = (King) board[5][5];
        board[6][5] = new Pawn(player1, 8, 0, 0);
        board[7][5] = new Pawn(player1, 9, 0, 0);
        board[4][6] = new Pawn(player1, 10, 0, 0);
        board[5][6] = new Pawn(player1, 11, 0, 0);
        board[6][6] = new Pawn(player1, 12, 0, 0);
        board[5][7] = new Pawn(player1, 13, 0, 0);

        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                if (board[i][j] != null) {
                    arr.add(board[i][j]);
                    board[i][j].getSteps().clear();
                    Position p = new Position(i, j);
                    ARR.add(board[i][j]);
                    board[i][j].Add(p);
                    pieces[i][j] = p;
                    pieces[i][j].setCountSteps(1);


                }

            }

        }
    }

    public boolean isPathClear(Position a, Position b) {

        if (a.getX() != b.getX() && a.getY() != b.getY())
            return false;
        if (a.getY() == b.getY()) {
            if (a.getX() > b.getX()) {
                for (int i = a.getX() - 1; i > b.getX(); i--) {
                    if (board[i][a.getY()] != null)
                        return false;
                }
            } else
                for (int i = a.getX() + 1; i < b.getX(); i++) {
                    if (board[i][a.getY()] != null)
                        return false;
                }
        }
        if (a.getX() == b.getX()) {
            if (a.getY() > b.getY()) {
                for (int i = a.getY() - 1; i > b.getY(); i--) {
                    if (board[a.getX()][i] != null)
                        return false;
                }
            } else for (int i = a.getY() + 1; i < b.getY(); i++) {
                if (board[a.getX()][i] != null)
                    return false;
            }
        }

        return true;
    }

    public boolean Equals(Position a, Position b) {

        return a.getX() == b.getX() && a.getY() == b.getY();

    }

    public int way(Position a, Position b) {
        if (a.getX() == b.getX()) {
            if (a.getY() > b.getY()) {
                return a.getY() - b.getY();
            } else {
                return b.getY() - a.getY();
            }
        } else if (a.getX() > b.getX()) {
            return a.getX() - b.getX();
        } else {
            return b.getX() - a.getX();
        }
    }

    @Override
    public boolean move(Position a, Position b) {

        Position side1 = new Position(0, 0);
        Position side2 = new Position(0, 10);
        Position side3 = new Position(10, 0);
        Position side4 = new Position(10, 10);
        // if the place is already taken

        if (getPieceAtPosition(b) != null) {
            return false;
        }

        //if u r not the king u cant move to the edge of the map
        if (!(getPieceAtPosition(a).getType().equals("♔"))) {
            if (Equals(b, side1) || Equals(b, side2) || Equals(b, side3) || Equals(b, side4)) {
                return false;
            }
        }

        //if u r choosing the other player pieces return false
        if (isSecondPlayerTurn() && this.getPieceAtPosition(a).getOwner().isPlayerOne() || !isSecondPlayerTurn() && !(this.getPieceAtPosition(a).getOwner().isPlayerOne()))
            return false;


        if (!isPathClear(a, b)) return false;


        board[b.getX()][b.getY()] = board[a.getX()][a.getY()];
        board[a.getX()][a.getY()] = null;

        if (!getPieceAtPosition(b).getType().equals("♔")) {
            checkingKill(b);

        }
        if (getPieceAtPosition(b).getType().equals("♔")) {
            king.setPos(b);
        }
        piece(board[b.getX()][b.getY()], b);

        board[b.getX()][b.getY()].Add(b);


        board[b.getX()][b.getY()].setSquares(way(a, b));

        Turn = !Turn;
        printResults();
        return true;

    }

    public void printResults() {
        if (isGameFinished()) {
            stats();
        }
    }

    public boolean outOfBounds(Position a) {
        Position side1 = new Position(0, 0);
        Position side2 = new Position(0, 10);
        Position side3 = new Position(10, 0);
        Position side4 = new Position(10, 10);
        return a.getX() > 10 || a.getX() < 0 || a.getY() > 10 || a.getY() < 0 || Equals(a, side1) || Equals(a, side2) || Equals(a, side3) || Equals(a, side4);
    }

    public void checkingKill(Position a) {
        Position side1 = new Position(a.getX() + 1, a.getY());//right
        Position side2 = new Position(a.getX() - 1, a.getY());//left
        Position side3 = new Position(a.getX(), a.getY() + 1);//up
        Position side4 = new Position(a.getX(), a.getY() - 1);//down
        Position side5 = new Position(a.getX() + 2, a.getY());//right
        Position side6 = new Position(a.getX() - 2, a.getY());//left
        Position side7 = new Position(a.getX(), a.getY() + 2);//up
        Position side8 = new Position(a.getX(), a.getY() - 2);//down


        if (!(!outOfBounds(side5) && getPieceAtPosition(side5) != null && getPieceAtPosition(side5).getType().equals("♔")) && (!outOfBounds(side1) && getPieceAtPosition(side1) != null && getPieceAtPosition(side1).getOwner().isPlayerOne() && !Turn)) {
            if (outOfBounds(side5) || ((getPieceAtPosition(side5) != null && !getPieceAtPosition(side5).getOwner().isPlayerOne()) && !Turn)) {
                if (!(board[a.getX() + 1][a.getY()].getId_piece().equals("K7"))) {
                    board[a.getX() + 1][a.getY()] = null;
                    board[a.getX()][a.getY()].setKills(board[a.getX()][a.getY()].getKills() + 1);
                }
            }
        }

        if (!(!outOfBounds(side6) && getPieceAtPosition(side6) != null && getPieceAtPosition(side6).getType().equals("♔")) && (!outOfBounds(side2) && getPieceAtPosition(side2) != null && getPieceAtPosition(side2).getOwner().isPlayerOne() && !Turn)) {
            if (outOfBounds(side6) || ((getPieceAtPosition(side6) != null && !getPieceAtPosition(side6).getOwner().isPlayerOne()) && !Turn)) {
                if (!(board[a.getX() - 1][a.getY()].getId_piece().equals("K7"))) {
                    board[a.getX() - 1][a.getY()] = null;
                    board[a.getX()][a.getY()].setKills(board[a.getX()][a.getY()].getKills() + 1);
                }

            }
        }


        if (!(!outOfBounds(side7) && getPieceAtPosition(side7) != null && getPieceAtPosition(side7).getType().equals("♔")) && (!outOfBounds(side3) && getPieceAtPosition(side3) != null && getPieceAtPosition(side3).getOwner().isPlayerOne() && !Turn)) {
            if (outOfBounds(side7) || ((getPieceAtPosition(side7) != null && !getPieceAtPosition(side7).getOwner().isPlayerOne()) && !Turn)) {
                if (!(board[a.getX()][a.getY() + 1].getId_piece().equals("K7"))) {
                    board[a.getX()][a.getY() + 1] = null;
                    board[a.getX()][a.getY()].setKills(board[a.getX()][a.getY()].getKills() + 1);
                }

            }
        }


        if (!(!outOfBounds(side8) && getPieceAtPosition(side8) != null && getPieceAtPosition(side8).getType().equals("♔")) && (!outOfBounds(side4) && getPieceAtPosition(side4) != null && getPieceAtPosition(side4).getOwner().isPlayerOne() && !Turn)) {
            if (outOfBounds(side8) || ((getPieceAtPosition(side8) != null && !getPieceAtPosition(side8).getOwner().isPlayerOne()) && !Turn)) {
                if (!(board[a.getX()][a.getY() - 1].getId_piece().equals("K7"))) {
                    board[a.getX()][a.getY() - 1] = null;
                    board[a.getX()][a.getY()].setKills(board[a.getX()][a.getY()].getKills() + 1);
                }

            }
        }

        ///////////////////////////////////
        if (!(!outOfBounds(side5) && getPieceAtPosition(side5) != null && getPieceAtPosition(side5).getType().equals("♔")) && (!outOfBounds(side1) && getPieceAtPosition(side1) != null && !getPieceAtPosition(side1).getOwner().isPlayerOne() && Turn)) {
            if (outOfBounds(side5) || ((getPieceAtPosition(side5) != null && getPieceAtPosition(side5).getOwner().isPlayerOne()) && Turn)) {
                if (!(board[a.getX() + 1][a.getY()].getId_piece().equals("K7"))) {
                    board[a.getX() + 1][a.getY()] = null;
                    board[a.getX()][a.getY()].setKills(board[a.getX()][a.getY()].getKills() + 1);
                }

            }
        }

        if (!(!outOfBounds(side6) && getPieceAtPosition(side6) != null && getPieceAtPosition(side6).getType().equals("♔")) && (!outOfBounds(side2) && getPieceAtPosition(side2) != null && !getPieceAtPosition(side2).getOwner().isPlayerOne() && Turn)) {
            if (outOfBounds(side6) || ((getPieceAtPosition(side6) != null && getPieceAtPosition(side6).getOwner().isPlayerOne()) && Turn)) {
                if (!(board[a.getX() - 1][a.getY()].getId_piece().equals("K7"))) {
                    board[a.getX() - 1][a.getY()] = null;
                    board[a.getX()][a.getY()].setKills(board[a.getX()][a.getY()].getKills() + 1);
                }

            }
        }


        if (!(!outOfBounds(side7) && getPieceAtPosition(side7) != null && getPieceAtPosition(side7).getType().equals("♔")) && (!outOfBounds(side3) && getPieceAtPosition(side3) != null && !getPieceAtPosition(side3).getOwner().isPlayerOne() && Turn)) {
            if (outOfBounds(side7) || ((getPieceAtPosition(side7) != null && getPieceAtPosition(side7).getOwner().isPlayerOne()) && Turn)) {
                if (!(board[a.getX()][a.getY() + 1].getId_piece().equals("K7"))) {
                    board[a.getX()][a.getY() + 1] = null;
                    board[a.getX()][a.getY()].setKills(board[a.getX()][a.getY()].getKills() + 1);
                }

            }
        }


        if (!(!outOfBounds(side8) && getPieceAtPosition(side8) != null && getPieceAtPosition(side8).getType().equals("♔")) && (!outOfBounds(side4) && getPieceAtPosition(side4) != null && !getPieceAtPosition(side4).getOwner().isPlayerOne() && Turn)) {
            if (outOfBounds(side8) || ((getPieceAtPosition(side8) != null && getPieceAtPosition(side8).getOwner().isPlayerOne()) && Turn)) {
                if (!(board[a.getX()][a.getY() - 1].getId_piece().equals("K7"))) {
                    board[a.getX()][a.getY() - 1] = null;
                    board[a.getX()][a.getY()].setKills(board[a.getX()][a.getY()].getKills() + 1);
                }

            }
        }

    }

    @Override
    public Piece getPieceAtPosition(Position position) {

        return board[position.getX()][position.getY()];
    }

    @Override
    public Player getFirstPlayer() {

        return player1;
    }

    @Override
    public Player getSecondPlayer() {
        return player2;
    }

    @Override
    public boolean isGameFinished() {

        int AttackPlayers = 0;
        if (board[0][0] != null || board[10][10] != null || board[10][0] != null || board[0][10] != null) {
            winner1 = player1;
            return true;
        }
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j] != null) {
                    if (board[i][j].getType().equals("♟")) {
                        AttackPlayers++;
                    }
                }

            }
        }
        if (AttackPlayers == 0) {
            winner1 = player1;

            return true;

        }
        if (atck_won1()) {
            winner1 = player2;

            return true;

        }

        return false;
    }

    private boolean atck_won1() {

        int[] x = {1, 0, -1, 0};
        int[] y = {0, 1, 0, -1};
        for (int i = 0; i < 4; i++) {
            Position a = new Position(king.getPos().getX() + x[i], king.getPos().getY() + y[i]);
            if (!inBoard(a)) {
                continue;
            }
            if (getPieceAtPosition(a) == null || getPieceAtPosition(a).getOwner() == king.getOwner()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean isSecondPlayerTurn() {
        return !Turn;
    }

    @Override
    public void reset() {
        initBoard();
    }

    @Override
    public void undoLastMove() {

    }

    @Override
    public int getBoardSize() {
        return 11;
    }

    private boolean inBoard(Position a) {
        return a.getX() <= 10 && a.getX() >= 0 && a.getY() <= 10 && a.getY() >= 0;
    }

    public void stats() {
        if (winner1==player1)
            player1.setCoutWins(player1.getWins());
        else
            player2.setCoutWins(player2.getWins());

        ArrayList<ConcretePiece> squares = new ArrayList<>();
        ArrayList<ConcretePiece> kills = new ArrayList<>();
        ArrayList<Position> piecesss = new ArrayList<>();


        for (ConcretePiece a : arr) {
            if (a.getSquares() > 0) {
                squares.add(a);
            }
        }

        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                if (pieces[i][j] != null && pieces[i][j].getCountSteps() >= 2) {
                    piecesss.add(pieces[i][j]);
                }
            }
        }

        for (ConcretePiece a : arr) {
            if (a.getKills() > 0) {
                kills.add(a);
            }
        }

        ARR.sort(new sortByMoves());


        for (int i = 0; i < ARR.size(); i++) {
            if (ARR.get(i).getSteps().size() > 1) {
                System.out.print(ARR.get(i).getId_piece() + ": ");
                System.out.print("[");
                for (int j = 0; j < ARR.get(i).getSteps().size(); j++) {

                    System.out.print(ARR.get(i).getSteps().get(j).toString());
                    if (j < ARR.get(i).getSteps().size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.print("]");
                System.out.println();

            }
        }
        System.out.print("***************************************************************************");
        System.out.println();
        kills.sort(new sortByKills());
        squares.sort(new sortBySquares());


        for (int i = 0; i < kills.size(); i++) {
            System.out.print(kills.get(i).getId_piece() + ": " + kills.get(i).getKills() + " kills");
            System.out.println();

        }
        System.out.print("***************************************************************************");
        System.out.println();
        for (int i = 0; i < squares.size(); i++) {
            System.out.print(squares.get(i).getId_piece() + ": " + squares.get(i).getSquares() + " squares");
            System.out.println();

        }
        System.out.print("***************************************************************************");
        System.out.println();
        piecesss.sort(new sortBypices());

        for (int i = 0; i < piecesss.size(); i++) {
            System.out.print(piecesss.get(i).toString() + piecesss.get(i).getCountSteps() + " pieces");
            System.out.println();

        }
        System.out.print("***************************************************************************");
        System.out.println();


    }


    static class sortByMoves implements Comparator<ConcretePiece> {
        public int compare(ConcretePiece a, ConcretePiece b) {
            if (a.getOwner() != b.getOwner()) {
                return a.getOwner() == winner1 ? -1 : 1;
            } else if (a.getSteps().size() == b.getSteps().size()) {
                return a.getId_num() - b.getId_num();
            }
            return a.getSteps().size() - b.getSteps().size();
        }
    }


    static class sortByKills implements Comparator<ConcretePiece> {
        public int compare(ConcretePiece a, ConcretePiece b) {
            if (a.getKills() != b.getKills()) {
                return a.getKills() > b.getKills() ? -1 : 1;
            } else if (a.getId_num() != b.getId_num()) {
                return a.getId_num() < b.getId_num() ? -1 : 1;
            }
            return winner1 == a.getOwner() ? -1 : 1;
        }

    }


    static class sortBySquares implements Comparator<ConcretePiece> {
        public int compare(ConcretePiece a, ConcretePiece b) {
            if (a.getSquares() != b.getSquares()) {
                return a.getSquares() > b.getSquares() ? -1 : 1;
            } else if (a.getId_num() != b.getId_num()) {
                return a.getId_num() < b.getId_num() ? -1 : 1;
            }
            return winner1 == a.getOwner() ? -1 : 1;
        }

    }

    static class sortBypices implements Comparator<Position> {
        public int compare(Position a, Position b) {
            if (a.getCountSteps() != b.getCountSteps()) {
                return a.getCountSteps() > b.getCountSteps() ? -1 : 1;
            } else if (a.getX() != b.getX()) {
                return a.getX() < b.getX() ? -1 : 1;
            }
            return a.getY() < b.getY() ? -1 : 1;
        }

    }
    public void piece(ConcretePiece a, Position b) {
        for (int i = 0; i < a.getSteps().size(); i++) {
            if (Equals(b,a.getSteps().get(i))) {
                return;
            }
        }
        if (pieces[b.getX()][b.getY()] == null) {
            pieces[b.getX()][b.getY()] = b;
        }
        pieces[b.getX()][b.getY()].setCountSteps();

    }
}