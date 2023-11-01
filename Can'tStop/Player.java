import java.awt.Color;
import java.util.ArrayList;

public class Player {
    private String name;
    public int score;
    public ArrayList<Integer> maxRows;
    public ArrayList<Integer> currentMarkerRows;
    public int runners;
    public ArrayList<Integer> currentRunnerRows;
    public int fullHouse;
    public Color color;
    public Board board;

    private ScreenManager screenManager;

    public Player(String name, ScreenManager screenManager, Color color, Board board) {
        this.name = name;
        this.screenManager = screenManager;
        this.board = board;
        this.score = 0;
        this.color = color;
        fullHouse = 0;
        runners = 0;

        maxRows = new ArrayList<>();
        currentMarkerRows = new ArrayList<>();
        currentRunnerRows = new ArrayList<>();

        maxRows.add(3);
        maxRows.add(5);
        maxRows.add(7);
        maxRows.add(9);
        maxRows.add(11);
        maxRows.add(13);
        maxRows.add(11);
        maxRows.add(9);
        maxRows.add(7);
        maxRows.add(5);
        maxRows.add(3);

        currentMarkerRows.add(-1);
        currentMarkerRows.add(-1);
        currentMarkerRows.add(-1);
        currentMarkerRows.add(-1);
        currentMarkerRows.add(-1);
        currentMarkerRows.add(-1);
        currentMarkerRows.add(-1);
        currentMarkerRows.add(-1);
        currentMarkerRows.add(-1);
        currentMarkerRows.add(-1);
        currentMarkerRows.add(-1);

        currentRunnerRows.add(-1);
        currentRunnerRows.add(-1);
        currentRunnerRows.add(-1);
        currentRunnerRows.add(-1);
        currentRunnerRows.add(-1);
        currentRunnerRows.add(-1);
        currentRunnerRows.add(-1);
        currentRunnerRows.add(-1);
        currentRunnerRows.add(-1);
        currentRunnerRows.add(-1);
        currentRunnerRows.add(-1);

    }

    public String getName() {
        return name;
    }

    public void setRunner(int i) {
        runners = i;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int points) {
        this.score += points;
    }   

    public void addRunner(int col_num) {
        boolean is_full_house = screenManager.gameScreen.isFullHouse(col_num);
        int playerNum = screenManager.gameScreen.activeInitial;

        if (runners < 3 && !is_full_house) {
            currentRunnerRows.set(col_num, currentMarkerRows.get(col_num) + 1);
            board.setPlayerColumnColor(col_num, currentRunnerRows.get(col_num), playerNum, color.GRAY);
            runners ++;
        }
    }

    public void moveRunner (int col_num) {
        int playerNum = screenManager.gameScreen.activeInitial;

        if (currentRunnerRows.get(col_num) < maxRows.get(col_num)-1) {
            //board.setPlayerColumnColor(col_num, currentRunnerRows.get(col_num), playerNum, color.WHITE);
            currentRunnerRows.set(col_num, currentRunnerRows.get(col_num)+1);
            board.setPlayerColumnColor(col_num, currentRunnerRows.get(col_num), playerNum, color.GRAY);
        }
        screenManager.gameScreen.isFullHouse(col_num);
        screenManager.gameScreen.checkWinner();
        screenManager.gameScreen.updateScoreboard();
    }

    
    
    public void failPass() {
        for (int i = 0; i < 11; i++) {
            //board.setPlayerColumnColor(i, currentRunnerRows.get(i), playerNum, color.WHITE);  // changed here
            for (int j = currentMarkerRows.get(i)+1; j<=currentRunnerRows.get(i); j++) {
                board.setPlayerColumnColor(i, j, screenManager.gameScreen.activeInitial, Color.WHITE);
            }
            currentRunnerRows.set(i, currentMarkerRows.get(i));
        }
        screenManager.gameScreen.getActivePlayer().runners = 0;
        screenManager.gameScreen.nextActivePlayer();
        runners=0;
    }
    //Marker level of each column, runners arraylist of size 3, score
}
