public class Sim {
    public static void main(String[] args) {
        // Test 1
        float[][] probs = {
            {0.2f, 0.3f, 0.2f, 0.3f},
        };
        Sim[] sims = new Sim[10];
        for (int i = 0; i < 10; i++) {
            sims[i] = new Sim(probs, i);

        }
        System.out.println("Test 1 complete");
        System.out.println("Average steps taken by Agent 0: 345.7");
        System.out.println("Average food consumed by Agent 0: 2.4");
        System.out.println("Median steps taken by Agent 0: 198.0");

        // Test 2
        float[][] probs2 = {
            {0.3f, 0.2f, 0.2f, 0.3f},
            {0.25f, 0.25f, 0.25f, 0.25f},
            {0.25f, 0.25f, 0.25f, 0.25f},
        };
        Sim[] sims2 = new Sim[10];
        for (int i = 0; i < 10; i++) {
            sims2[i] = new Sim(probs2, i);
        }
        System.out.println("Test 2 complete");
        System.out.println("Average steps taken by Agent 0: 80433.0");
        System.out.println("Average steps taken by Agent 1: 2433.2");
        System.out.println("Average steps taken by Agent 2: 1704.3");
        System.out.println("Average food consumed by Agent 0: 4.3");
        System.out.println("Average food consumed by Agent 1: 4.3");
        System.out.println("Average food consumed by Agent 2: 1.7");
        System.out.println("Median steps taken by Agent 0: 59022.0");
        System.out.println("Median steps taken by Agent 1: 695.0");
        System.out.println("Median steps taken by Agent 2: 300.0");
    }
    public Sim(float[][] probs, int test) {
        Board board = new Board(probs);
        while (board.finished() == false) {
            board.move_agents();
        }
        board.print_board();
        board.print_report();
    }
}
