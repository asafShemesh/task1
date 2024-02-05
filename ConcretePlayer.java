public class ConcretePlayer implements Player {
    private boolean defender;

    public int coutWins;

    public ConcretePlayer(boolean defender, int countWins) {
        this.coutWins = countWins;
        this.defender = defender;

    }


    @Override
    public boolean isPlayerOne() {
        if (defender == true)
            return true;
        else
            return false;
    }

    @Override
    public int getWins() {
        return coutWins;
    }

    public void setCoutWins(int coutWins) {
        this.coutWins = coutWins+1;
    }

}