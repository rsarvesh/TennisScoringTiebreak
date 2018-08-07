import java.util.List;

public class Main {

    public static void main(String[] args) {
        String points = "AAAABBBBAAAABBBBAAAABBBBAAAABBBBAAAABBBBAAAABBBBAAAABBAABBBBB";
        String gamesWithPoints = ScorerUtils.reducePointsToGames(points);
        String setsWithGames = ScorerUtils.reduceGamesToSets(gamesWithPoints);

        String[] gamesList = setsWithGames.split("#");

        ScorerUtils.printScores(gamesList[0]);
        boolean isTiebreak = ScorerUtils.printScores(gamesList.length > 1 ? gamesList[1] : "");
        ScorerUtils.printPoints(gamesList.length > 2 ? gamesList[2] : "", isTiebreak);
    }
}
