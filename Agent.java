public class Agent {
    float[] prob = {0,0,0,0};
    int x;
    int y;
    int prevX;
    int prevY;
    int id;
    int rvalue;
    boolean done;
    int steps;

    public Agent(int id, int x, int y, float[] prob) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.prob = prob;
        this.done = false;
        this.rvalue = 0;
        this.steps = 0;
    }

    public int[] getPos() {
        int[] pos = {x, y};
        return pos;
    }

    public int[] move() {
        if (!done) {
            steps += 1;
            float rand = (float)Math.random();
            prevX = x;
            prevY = y;
            if(rand < prob[0]){
                if(x > 0){
                    y -= 1;
                }
            } else if(rand < prob[0] + prob[1]){
                if(x < 24){
                    y += 1;
                }
            } else if(rand < prob[0] + prob[1] + prob[2]){
                if(y > 0){
                    x -= 1;
                }
            } else {
                if(y < 24){
                    x += 1;
                }
            }
            int[] pos = {x, y};
            return pos;
        }
        return new int[] {x,y};
    }

    public void addRvalue(int val) {
        rvalue += val;
    }
    public void revert(){
        steps -= 1;
        x = prevX;
        y = prevY;
    }

    public void finish() {
        this.done = true;
    }

    public int getSteps() {
        return this.steps;
    }
}
