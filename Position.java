public class Position {
    private int x,y;
    private int CountSteps;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        this.CountSteps=0;
    }


    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return ("("+x+ ", " + y+")");
    }

    public int getCountSteps() {
        return CountSteps;
    }

    public void setCountSteps() {
        CountSteps += 1;
    }

    public void setCountSteps(int countSteps) {
        CountSteps = countSteps;
    }
}
