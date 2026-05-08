import java.util.*;

class ExtraRuns{
    public void computeExtraRuns(Map<Integer, List<Deliveries>> deliveriesMap){
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
}