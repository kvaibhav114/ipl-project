import java.util.*;

public class EconomicalBowlers {
    void getEconomicalBowlers(Map<Integer, Match> matches, Map<Integer, List<Deliveries>> deliveries){
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
}