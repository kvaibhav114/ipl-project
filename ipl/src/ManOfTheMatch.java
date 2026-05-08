import java.util.*;

public class ManOfTheMatch {
    void manOfTheMatch(Map<Integer, Match> matchMap){
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
}
