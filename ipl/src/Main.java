import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("matches.csv"));
        String line = br.readLine();

        Map<Integer, Match> matchMap = new HashMap<>();
        while((line = br.readLine()) != null){
            String[] data = line.split(",");
            int season = Integer.parseInt(data[1]);
            String city = data[2];
            String team1 = data[4];
            String team2 = data[5];
            String winner = data[10];
            String tossWinner = data[6];
            String playerOfMatch = data[13];

            Match match = new Match(season, city, team1, team2, winner, tossWinner, playerOfMatch);
            matchMap.put(Integer.parseInt(data[0]), match);
        }

        BufferedReader br2 = new BufferedReader(new FileReader("deliveries.csv"));
        String delivery = br2.readLine();
        Map<Integer, List<Deliveries>> deliveriesMap = new HashMap<>();
        while((delivery = br2.readLine()) != null){
            String[] data = delivery.split(",");
            int id = Integer.parseInt(data[0]);
            String battingTeam = data[2];
            String bowlingTeam = data[3];
            int extraRuns = data[16].isEmpty() ? 0 : Integer.parseInt(data[16]);
            String bowler = data[8];
            int totalRuns = data[17].isEmpty() ? 0 : Integer.parseInt(data[17]);
            int over = data[4].isEmpty() ? 0 : Integer.parseInt(data[4]);
            String batsman = data[6];
            int batsmanRuns = Integer.parseInt(data[15]);
            int noBall = Integer.parseInt(data[13]);
            Deliveries d = new Deliveries(battingTeam, bowlingTeam, extraRuns, bowler, totalRuns, over, batsman, batsmanRuns, noBall);
            List<Deliveries> list = deliveriesMap.get(id);
            if(list == null) {
                list = new ArrayList<>();
                deliveriesMap.put(id, list);
            }
            list.add(d);
        }

        getNoBalls(matchMap, deliveriesMap);
        br.close();
        br2.close();
    }

    static void getDeathOverBowler(Map<Integer, List<Deliveries>> deliverylist){
        Map<String, Integer> balls = new HashMap<>();
        Map<String, Integer> runs = new HashMap<>();
        for(int i : deliverylist.keySet()){
            for(Deliveries d : deliverylist.get(i)){
                if(d.over >= 16 && d.over <= 20){
                    balls.put(d.bowler, balls.getOrDefault(d.bowler, 0) + 1);
                    runs.put(d.bowler, runs.getOrDefault(d.bowler, 0) + d.total_runs);
                }
            }
        }

        Map<String, Double> economies = new HashMap<>();
        for(String s : balls.keySet()){
            double overs = balls.get(s) / 6.0;
            double eco = runs.get(s) / overs;
            economies.put(s, eco);
        }

        List<Map.Entry<String, Double>> list = new ArrayList<>(economies.entrySet());
        list.sort(Map.Entry.comparingByValue());
        System.out.println("Death Over Stats");
        for(int i = 0; i < 5; i++){
            System.out.println(list.get(i).getKey() + ": " + list.get(i).getValue());
        }
    }
    static void getNoBalls(Map<Integer, Match> matchesMap, Map<Integer, List<Deliveries>> deliveriesMap){
        Map<Integer, Map<String, Map<String, Integer>>> noBalls = new HashMap<>();
        for(int i : matchesMap.keySet()){
            String team = "";
            int season = matchesMap.get(i).season;
            Map<String, Integer> playersNoBalls = new HashMap<>();
            for(Deliveries d : deliveriesMap.get(i)){
                noBalls.putIfAbsent(matchesMap.get(i).season, new HashMap<>());
                if(d.batting_team.equals("Royal Challengers Bangalore")){
                    if(d.noball_runs != 0) {
                        team = d.bowling_team;
                        playersNoBalls.put(d.bowler, playersNoBalls.getOrDefault(d.bowler, 0) + d.noball_runs);
                    }
                }
            }
            noBalls.get(matchesMap.get(i).season).putIfAbsent(team, new HashMap<>());
            List<Map.Entry<String, Integer>> list = new ArrayList<>(playersNoBalls.entrySet());
            list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
            if(!list.isEmpty()) {
                String bowler = list.get(0).getKey();
                int noBallCount = list.get(0).getValue();
                System.out.println(season + ", " + team + ": ");
                System.out.println(bowler + " : " + noBallCount);
            }
        }

    }
    static void manOfTheMatch(Map<Integer, Match> matchMap){
        Map<Integer, List<String>> seasonMap = new HashMap<>();
        for(Match m : matchMap.values()){
            List<String> list = seasonMap.get(m.season);
            if(list == null){
                list = new ArrayList<>();
                seasonMap.put(m.season, list);
            }
            list.add(m.playerOfMatch);
        }
        Map<Integer, String> result = new TreeMap<>();
        for(int i : seasonMap.keySet()) {
            Map<String, Integer> mansInThisYear = new HashMap<>();
            for (String s : seasonMap.get(i)) {
                mansInThisYear.put(s, mansInThisYear.getOrDefault(s, 0) + 1);
            }
            List<Map.Entry<String, Integer>> list = new ArrayList<>(mansInThisYear.entrySet());
            list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
            result.put(i, list.get(0).getKey());
        }

        System.out.println("Man of the Match per year:");
        for (int i : result.keySet()){
            System.out.println("Season " + i + " " + result.get(i));
        }
    }
    static void computeExtraRuns(Map<Integer, List<Deliveries>> deliveriesMap){
        Map<String, Integer> extraRuns = new HashMap<>();
        for(int i : deliveriesMap.keySet()){
            for(Deliveries d : deliveriesMap.get(i)){
                extraRuns.put(d.bowling_team, extraRuns.getOrDefault(d.bowling_team, 0) + d.extra_runs);
            }
        }

        System.out.println("Q3: Extra Runs Conceded by teams");
        for(String s : extraRuns.keySet()){
            System.out.println("Team: " + s + " Runs Conceded: " + extraRuns.get(s));
        }
    }
    static void getMatchesPerYear(Map<Integer, Match> matches){
        Map<Integer, Integer> matchesWithYear = new HashMap<>();
        for(int i : matches.keySet()){
            int year = matches.get(i).season;
            matchesWithYear.put(year, matchesWithYear.getOrDefault(year, 0) + 1);
        }
        System.out.println("Question 1");
        for (int i : matchesWithYear.keySet()){
            System.out.println("Year: " + i + ", No of matches: " + matchesWithYear.get(i));
        }
    }
    static void getEconomicalBowlers(Map<Integer, Match> matches, Map<Integer, List<Deliveries>> deliveries){
        Set<Integer> matchId = new HashSet<>();
        for(int i : matches.keySet()){
            if(matches.get(i).season == 2015) matchId.add(i);
        }
        Map<String, Integer> balls = new HashMap<>();
        Map<String, Integer> runs = new HashMap<>();
        for(int i : matchId){
            for(Deliveries d : deliveries.get(i)){
                balls.put(d.bowler, balls.getOrDefault(d.bowler, 0) + 1);
                runs.put(d.bowler, runs.getOrDefault(d.bowler, 0) + d.total_runs);
            }
        }

        Map<String, Double> economies = new HashMap<>();
        for(String bowler : balls.keySet()){
            double overs = balls.get(bowler) / 6.0;
            double economy = runs.get(bowler) / overs;
            economies.put(bowler, economy);
        }
        List<Map.Entry<String, Double>> list = new ArrayList<>(economies.entrySet());
        list.sort(Map.Entry.comparingByValue());

        System.out.println("Q4: 10 Economical Bowlers");
        for (int i = 0; i < 10 && i < list.size(); i++) {
            System.out.println(list.get(i).getKey() + " → " + list.get(i).getValue());
        }

    }
    static void tossWin(Map<Integer, Match> matchMap){

        Map<String, Integer> wins = new HashMap<>();
        for(Match m : matchMap.values()){
            if(m.toss_winner.equals(m.winner)){
                wins.put(m.winner, wins.getOrDefault(m.winner, 0 ) + 1);
            }
        }

        System.out.println("Toss and Match Wins");
        for(String i : wins.keySet()){
            System.out.println(i + ": " + wins.get(i));
        }
    }
    static void getMatchesWon(Map<Integer, Match> matches){
        Map<String, Integer> teamNames = new HashMap<>();
        for(int i : matches.keySet()){
            if(matches.get(i).winner.isEmpty()) continue;
            String winner = matches.get(i).winner;
            teamNames.put(winner, teamNames.getOrDefault(winner, 0) + 1);
        }
        System.out.println("Question 2");
        for(String i : teamNames.keySet()){
            System.out.println("Team Name: " + i + ", Matches Won: " + teamNames.get(i));
        }
    }
}
