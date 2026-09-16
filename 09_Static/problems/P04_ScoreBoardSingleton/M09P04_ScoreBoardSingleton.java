public class M09P04_ScoreBoardSingleton {

    static class ScoreBoard {
        private static ScoreBoard instance;
        static int created = 0;

        private int total = 0;
        private int best = 0;

        private ScoreBoard() {
            created++;
        }

        // lazy: build the one object on first request
        static ScoreBoard getInstance() {
            if (instance == null) {
                instance = new ScoreBoard();
            }
            return instance;
        }

        void addPoints(int points) {
            total += points;
            if (points > best) {
                best = points;
            }
        }

        int getTotal() {
            return total;
        }

        int getBest() {
            return best;
        }
    }

    static void check(String input, String actual, String expected) {
        String status = actual.equals(expected) ? "PASS" : "FAIL";
        System.out.println(input + " -> " + actual + "  " + status);
    }

    public static void main(String[] args) {
        check("created before use", String.valueOf(ScoreBoard.created), "0");

        ScoreBoard.getInstance().addPoints(10);
        ScoreBoard.getInstance().addPoints(25);
        ScoreBoard board = ScoreBoard.getInstance();
        board.addPoints(5);

        check("total", String.valueOf(board.getTotal()), "40");
        check("best", String.valueOf(board.getBest()), "25");
        check("same object", String.valueOf(board == ScoreBoard.getInstance()), "true");
        check("created after use", String.valueOf(ScoreBoard.created), "1");
    }
}
