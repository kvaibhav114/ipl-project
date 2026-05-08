import java.util.*;
public class DeathOver {
    void getDeathOverBowler(Map<Integer, List<Deliveries>> deliverylist){
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
}
