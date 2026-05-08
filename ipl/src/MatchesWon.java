import java.util.*;

public class MatchesWon {
    public void getMatchesWon(Map<Integer, Match> matches){
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
