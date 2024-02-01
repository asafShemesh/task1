public class ConcretePlayer implements Player {
    private boolean defender;
    public int countWins;

    public ConcretePlayer(boolean defender, int countWins) {
        this.countWins = countWins;
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
        return countWins;
    }

    public void CountWins(int countWins) {
        this.countWins += 1;
    }

}
