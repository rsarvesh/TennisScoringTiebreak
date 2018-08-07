public class ScorerUtils {

    private static final char PLAYER_A = 'A';
    private static final char PLAYER_B = 'B';

    public static String reduce(String points, int minGames){
        StringBuilder games = new StringBuilder();

        int aPoints = 0;
        int bPoints = 0;

        int aCount = 0;
        int bCount = 0;

        int cutoff = -1;
        char[] pointsArr = points.toCharArray();

        for(int i = 0; i < pointsArr.length; i++){
            if(pointsArr[i] == PLAYER_A)
                aPoints++;
            else
                bPoints++;

            if(Math.max(aPoints, bPoints) >= minGames && Math.abs(aPoints - bPoints) > 1){
                cutoff = i;
                if(aPoints > bPoints) {
                    games.append("A");
                    aCount++;
                }
                else {
                    games.append("B");
                    bCount++;
                }

                if(aCount == 6 && bCount == 6) {
                    int idx = calculateTiebreak(points.substring(i + 1));
                    if(i+idx+1 < pointsArr.length && pointsArr[i+idx+1] == 'A') {
                        games.append("AA");
                        cutoff = i+idx+1;
                    }
                    else if(i+idx+1 < pointsArr.length && pointsArr[i+idx+1] == 'B') {
                        games.append("BB");
                        cutoff = i+idx+1;
                    }
                    i += idx+1;
                }
                aPoints = bPoints = 0;
            }
        }
        return games.toString() + "#" + points.substring(cutoff + 1);
    }

    public static int calculateTiebreak(String pts) {
        int aPoints = 0;
        int bPoints = 0;
        int i = 0;

        char[] pointsArr = pts.toCharArray();

        for(i = 0; i < pointsArr.length; i++){
            if(pointsArr[i] == PLAYER_A)
                aPoints++;
            else
                bPoints++;

            if(Math.max(aPoints, bPoints) >= 7 && Math.abs(aPoints - bPoints) > 1) {
                return i;
            }
//            else if(bPoints >= 7 && bPoints - aPoints > 1) {
//                return "BB";
//            }
        }
        return i;
    }


    public static String reducePointsToGames(String points){
        return reduce(points, 4);
    }

    public static String reduceGamesToSets(String games){
        String[] gamesList = games.split("#");
        String sets = reduce(gamesList[0], 6) + "#";

        if(gamesList.length > 1)
            sets += gamesList[1];

        return sets;
    }

    public static void printPoints(String points, boolean isTiebreak){
        String[] arr={"0","15","30","40", "4", "5", "6", "7", "8"};

        int aPoints = 0;
        int bPoints = 0;

        for(char p : points.toCharArray()) {
            if (p == PLAYER_A)
                aPoints++;
            else
                bPoints++;
        }

        if(isTiebreak) {
            System.out.println(aPoints + " " + bPoints + " " + "Tiebreak");
            return;
        }

        if(Math.min(aPoints, bPoints) >= 4) {
            if (aPoints == bPoints)
                System.out.println("DEUCE");
            else if (Math.abs(bPoints - aPoints) == 1)
                System.out.println((aPoints > bPoints) ? "ADV" : "   ADV");
        }
        else
            System.out.println(arr[aPoints] + " " + arr[bPoints]);
    }


    public static boolean printScores(String games){
        int aGames = 0;
        int bGames = 0;

        for(char g : games.toCharArray()){
            if(g == PLAYER_A)
                aGames++;
            else
                bGames++;
        }
        boolean isTiebreak = ((aGames==6 && bGames==6) ? true : false);

        System.out.println(aGames + " " + bGames);
        return isTiebreak;
    }
}