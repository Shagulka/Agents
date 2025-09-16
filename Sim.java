public class Sim {
    public static void main(String[] args) {
        // Test 1
        float[][] probs = {
            {0.2f, 0.3f, 0.2f, 0.3f},
        };
        Sim[] sims = new Sim[10];
        for (int i = 0; i < 10; i++) {
            sims[i] = new Sim(probs);
        }
        // Test 2
        float[][] probs2 = {
            {0.3f, 0.2f, 0.2f, 0.3f},
            {0.25f, 0.25f, 0.25f, 0.25f},
            {0.25f, 0.25f, 0.25f, 0.25f},
        };
        Sim[] sims2 = new Sim[10];
        for (int i = 0; i < 10; i++) {
            sims2[i] = new Sim(probs2);
        }
    }
    public Sim(float[][] probs) {
        Board board = new Board(probs);
        while (board.finished() == false) {
            board.move_agents();
        }
    }
}
