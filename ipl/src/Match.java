public class Match {
    int season;
    String city;
    String team1;
    String team2;
    String winner;
    String toss_winner;
    String playerOfMatch;
    Match(int season, String city, String team1, String team2, String winner, String toss_winner, String playerOfMatch){
        this.season = season;
        this.city = city;
        this.team1 = team1;
        this.team2 = team2;
        this.winner = winner;
        this.toss_winner = toss_winner;
        this.playerOfMatch = playerOfMatch;
    }
    @Override
    public String toString(){
        return "Season:" + season + " City:" + city;
    }
}
