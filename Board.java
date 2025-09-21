import java.util.Random;

public class Board {
  private char[][] board;
  private Agent[] agents;

  public Board(float[][] probs) {
    int n = probs.length;
    this.board = new char[][] {
      {'O','O','O','O','O','O',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','O','O','O','O','O','O'},
      {'O',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','O'},
      {'O',' ',' ','O','O',' ',' ',' ','O','O',' ','O','O','O',' ','O','O',' ',' ',' ','O','O',' ',' ','O'},
      {'O',' ','O','O','O',' ',' ',' ',' ','$',' ',' ',' ',' ',' ',' ',' ',' ',' ','$','O','O','O',' ','O'},
      {'O',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','$',' ',' ',' ','O'},
      {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
      {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
      {' ',' ','O','O',' ',' ',' ',' ','O','O','O','O',' ','O','O','O','O',' ',' ',' ',' ','O','O',' ',' '},
      {' ',' ','O','O',' ',' ',' ',' ','O','O','O',' ',' ',' ','O','O','O',' ',' ',' ',' ','O','O',' ',' '},
      {' ',' ',' ',' ',' ',' ',' ',' ','O','O',' ',' ',' ',' ','$','O','O','$',' ',' ',' ',' ','$',' ',' '},
      {'$',' ','O','O',' ',' ',' ',' ','O',' ',' ',' ',' ',' ',' ',' ','O',' ',' ',' ',' ','O','O',' ',' '},
      {' ',' ','O',' ',' ',' ',' ',' ',' ',' ',' ',' ','O',' ',' ',' ',' ',' ',' ',' ',' ',' ','O',' ',' '},
      {'$',' ','O','O',' ',' ',' ',' ','O',' ',' ',' ',' ',' ','$',' ','O',' ',' ',' ',' ','O','O',' ',' '},
      {' ',' ','$',' ','$',' ',' ',' ','O','O',' ',' ','$',' ',' ','O','O',' ',' ',' ',' ',' ',' ',' ',' '},
      {' ',' ','O','O',' ',' ',' ',' ','O','O','O',' ',' ',' ','O','O','O',' ',' ',' ',' ','O','O',' ',' '},
      {' ',' ','O','O',' ',' ',' ',' ','O','O','O','O',' ','O','O','O','O',' ',' ',' ',' ','O','O',' ',' '},
      {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
      {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
      {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
      {'O',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','O'},
      {'O',' ','O','O','O','$',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','O','O','O',' ','O'},
      {'O',' ','O','O','O',' ',' ',' ','O','O',' ','O',' ','O',' ','O','O',' ',' ',' ','O','O','O',' ','O'},
      {'O',' ','F','O','O',' ',' ',' ','O','O',' ','O','O','O',' ','O','O',' ',' ',' ','O','O',' ',' ','O'},
      {'O','$',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','$','$','O'},
      {'O','O','O','O','O','O',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','O','O','O','O','O','O'}
    };
    this.agents = new Agent[n];
    Random rand = new Random();
    for (int i = 0; i < n; i++) {
      int x = rand.nextInt(25);
      int y = rand.nextInt(25);
      while (board[x][y] == 'O' && board[x][y] == 'F') {
        x = rand.nextInt(25);
        y = rand.nextInt(25);
      }
      this.agents[i] = new Agent(i, x, y, probs[i]);
      this.board[x][y] = 'I';
    }
  }

  public void move_agents() {
      for (Agent agent : this.agents) {
          boolean food = false;
          int[] og_pos = agent.getPos();
          int[] pos = agent.move();
          if (this.board[pos[0]][pos[1]] == '$') {
              food = true;
          }   if (this.board[pos[0]][pos[1]] == 'O' || this.board[pos[0]][pos[1]] == 'I') {
              agent.revert();
              continue;
          }
          if (this.board[pos[0]][pos[1]] == 'F') {
            agent.finish();
          } else {
            this.board[pos[0]][pos[1]] = 'I';
          }
          if (agent.isDone() == false) {
            this.board[og_pos[0]][og_pos[1]] = ' ';
          }

          if (food) {
              agent.addRvalue(1);
          }
      }
  }

  //report method
  // should return board (as a String?)
  // should return steps taken by each agent (use agent.getSteps())
  // should return amount food consumed by each agent (can just use rvalue for that)
  // should return optimal steps for agents (x - x, y - y, no path finding, screw it)
  public String report() {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < 25; i++) {
          for (int j = 0; j < 25; j++) {
              sb.append(this.board[i][j]);
          }
          sb.append("\n");
      }
      sb.append("\n");
      for (Agent agent : this.agents) {
          int[] pos = agent.getPos();
          sb.append("Agent " + agent.getId() + ": Position (" + pos[0] + ", " + pos[1] + "), Steps taken: " + agent.getSteps() + ", Food consumed: " + agent.getRvalue() + "\n");
      }
      return sb.toString();
  }


  public boolean finished() {
    for (Agent agent : this.agents) {
      int[] pos = agent.getPos();
      ///print position of each agent
      StringBuilder sb = new StringBuilder();
      sb.append("Agent " + agent.getId() + ": Position (" + pos[0
] + ", " + pos[1] + ")\n");
    
      System.out.println(sb.toString());
      if (!(agent.isDone())) {
          return false;
      }
    }
    return true;
  }
}
