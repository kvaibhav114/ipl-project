import java.util.*;

public class MatchPerYear {
    public void getMatchesPerYear(Map<Integer, Match> matches){
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
}
