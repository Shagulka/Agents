public class Agent {
    float[] prob = {0,0,0,0};
    int x;
    int y;
    int prevX;
    int prevY;
    int id;
    int rvalue;
    public Agent(int id, int x, int y, float[] prob) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.prob = prob;
    }
    public int[] getPos() {
        int[] pos = {x, y};
        return pos;
    }
    public int[] move(){
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
    public void addRvalue(int val){
        rvalue += val;
    }
    public void revert(){
        x = prevX;
        y = prevY;
    }
}
