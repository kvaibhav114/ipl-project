public class Deliveries {
    String batting_team;
    String bowling_team;
    int extra_runs;
    String bowler;
    int total_runs;
    int over;
    String batsman;
    int batsmanRuns;
    int noball_runs;

    public Deliveries(String batting_team, String bowling_team, int extra_runs, String bowler, int total_runs, int over, String batsman, int batsmanRuns, int noball_runs) {
        this.batting_team = batting_team;
        this.bowling_team = bowling_team;
        this.extra_runs = extra_runs;
        this.bowler = bowler;
        this.total_runs = total_runs;
        this.over = over;
        this.batsman = batsman;
        this.batsmanRuns = batsmanRuns;
        this.noball_runs = noball_runs;
    }
}
