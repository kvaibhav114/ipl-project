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

        MostNoBalls m = new MostNoBalls();
        m.getNoBalls(matchMap, deliveriesMap);
        br.close();
        br2.close();
    }

}
