import java.util.*;

public class TossWinMatchWin {
    void tossWin(Map<Integer, Match> matchMap){

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
}
