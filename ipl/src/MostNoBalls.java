import java.util.*;

public class MostNoBalls {
    void getNoBalls(Map<Integer, Match> matchesMap, Map<Integer, List<Deliveries>> deliveriesMap){
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
}
