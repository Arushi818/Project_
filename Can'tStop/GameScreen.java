import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import javax.swing.JOptionPane;

public class GameScreen implements ActionListener{
    private ScreenManager screenManager;
    

    private JPanel mainPanel;

    private Buttons buttons = new Buttons();
     
    private JPanel settingsPanel, helpPanel;
    private JButton settingsButton, helpButton, rollButton;
    
    private Board board;
    private Die die;
    private Player winner;

    private JPanel dicePanel, comboPanel;

    private JPanel scorePanel, scoreboardPanel;
    private JLabel score;

    public int playerCount;
    private JButton continueButton;
    private JButton passButton;
    int activeInitial = 0;

    private Font font1 = new Font("Cascadia Mono SemiBold", Font.BOLD, 25);
    private Font font2 = new Font("Cascadia Mono SemiBold", Font.BOLD, 30);

    public List<Player> players;
    public ArrayList<Boolean> fullFilled; 

    int columnIndex1, playerIndex1, innerPanelStart1 = 0, innerPanelEnd1 = 0;
    int columnIndex2, playerIndex2, innerPanelStart2 = 0, innerPanelEnd2 = 0;
    int pcolumnIndex1, pplayerIndex1, pinnerPanelStart1 = 0, pinnerPanelEnd1 = 0;
    int pcolumnIndex2, pplayerIndex2, pinnerPanelStart2 = 0, pinnerPanelEnd2 = 0;
    ArrayList<Integer> list = new ArrayList<Integer>(Collections.nCopies(11, 0));
    int ruler = 0;
    boolean boardReset = false;


    public GameScreen(ScreenManager screenManager) {
        this.screenManager = screenManager;
        
        playerCount = screenManager.getNewGameScreen().getSelectedPlayers();
        mainPanel = ColorThemes.BLACK_BLUE.createGradientPanel();
        mainPanel.setBounds(0, 0, 1400, 900);
        
        mainPanel.setLayout(null);
        
        createSettingsPanel();
        createHelpPanel();
        createBoard();
        createDie();
        createRollButton();
        createComboPanel();
        
        
        createScoreBoard();
        createContinueButton();
        createPassButton();
        continueButton.setEnabled(false);
        applyColorGradient(screenManager.getColor());
    }
    
    public Player getActivePlayer() {
        return players.get(activeInitial);
    }
    
    public Player nextActivePlayer() {
        activeInitial = (players.indexOf(getActivePlayer()) + 1) % playerCount;
        return players.get(activeInitial);
    }


    private void createContinueButton(){
    
        continueButton = buttons.getContinueButton();
        continueButton.setBounds(1330, 600, 115, 35);
        
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passButton.setEnabled(false);  
                int C1 = die.getC1() - 2;
                int C2 = die.getC2() - 2;
                int curMarkersOfC1 = getActivePlayer().currentMarkerRows.get(C1);
                int curRunnersOfC2 = getActivePlayer().currentRunnerRows.get(C2);
                int curMarkersOfC2 = getActivePlayer().currentMarkerRows.get(C2);
                int curRunnersOfC1 = getActivePlayer().currentRunnerRows.get(C1);
                if (C1 != C2) {
                    if (getActivePlayer().runners == 3) {
                        if (curRunnersOfC1 > curMarkersOfC1) {
                            getActivePlayer().moveRunner(C1);
                        }
                        if (curRunnersOfC2 > curMarkersOfC2) {
                            getActivePlayer().moveRunner(C2);
                        }
                        if (curMarkersOfC1 == curRunnersOfC1 && curMarkersOfC2 == curRunnersOfC2) {
                            getActivePlayer().failPass();
                        }
                    }
                    else if (getActivePlayer().runners == 2) {
                        if (curRunnersOfC1 > curMarkersOfC1) {
                            getActivePlayer().moveRunner(C1);
                            
                            if (curRunnersOfC2 > curMarkersOfC2) {
                                getActivePlayer().moveRunner(C2);
                            }
                            else {
                                if (! isFullHouse(C1)) {
                                    getActivePlayer().addRunner(C2);
                                }
                            }
                        }
                        else {
                            if (curRunnersOfC2 > curMarkersOfC2) {
                                getActivePlayer().moveRunner(C2);
                                if (! isFullHouse(C2)) {
                                    getActivePlayer().addRunner(C1);
                                }
                            }
                            else {
                                if (! isFullHouse(C2)) {
                                    getActivePlayer().addRunner(C1);
                                }
                            }
                        }
                    }
                    else if (getActivePlayer().runners < 2) {
                        if (curRunnersOfC1 > curMarkersOfC1) {
                            getActivePlayer().moveRunner(C1);
                        }
                        else {
                            if (! isFullHouse(C2)) {
                                getActivePlayer().addRunner(C1);
                            }
                        }
                        if (curRunnersOfC2 > curMarkersOfC2) {
                            getActivePlayer().moveRunner(C2);
                        }
                        else {
                            if (! isFullHouse(C1)) {
                                getActivePlayer().addRunner(C2);
                            }
                        }
                    }
                }
                
                else {
                    if (getActivePlayer().runners == 3) {
                        if (curRunnersOfC1 > curMarkersOfC1) {
                            if (getActivePlayer().currentRunnerRows.get(C1) <= getActivePlayer().maxRows.get(C1)-1) {
                                getActivePlayer().moveRunner(C1);
                                getActivePlayer().moveRunner(C2);
                            }
                            else {
                                getActivePlayer().moveRunner(C1);
                            }
                        }
                        if (curRunnersOfC1 == curMarkersOfC1) {
                            getActivePlayer().failPass();
                        }
                    }
                    else if (getActivePlayer().runners <= 2) {
                        if (curRunnersOfC1 > curMarkersOfC1) {
                            if (getActivePlayer().currentRunnerRows.get(C1) <= getActivePlayer().maxRows.get(C1)-1) {
                                getActivePlayer().moveRunner(C1);
                                getActivePlayer().moveRunner(C2);
                            }
                            else {
                                getActivePlayer().moveRunner(C1);
                            }
                        }
                        else {
                            getActivePlayer().addRunner(C2);
                            getActivePlayer().moveRunner(C2);
                        }
                    }
                }
                passButton.setEnabled(true);
                //rollButton.setEnabled(true);
                die.reset();
                continueButton.setEnabled(false);
            }
        });
        mainPanel.add(continueButton);
    }
     
    public Boolean isFullHouse(int col_num) {;
        
        if ( getActivePlayer().currentRunnerRows.get(col_num) == getActivePlayer().maxRows.get(col_num)-1) {
            getActivePlayer().fullHouse ++;
            fullFilled.set(col_num,true);
            // clear column for ever
            int currRow = getActivePlayer().currentRunnerRows.get(col_num);
            //passMethod();
            for (int i = 0; i<11; i++) {
                getActivePlayer().currentMarkerRows.set(col_num, currRow);
                // if (currRow > getActivePlayer().currentMarkerRows.get(i)) {
                for (int j = 0; j<=currRow; j++) {
                    board.setPlayerColumnColor(col_num, j, activeInitial, getActivePlayer().color);
                }
                //}
            }
            for (int playerCount = 0; playerCount<players.size(); playerCount++) {
                for (int j = 0; j<=currRow; j++) {
                    board.setPlayerColumnColor(col_num, j, playerCount, getActivePlayer().color);
                    if (players.get(playerCount).currentRunnerRows.get(col_num) > players.get(playerCount).currentMarkerRows.get(col_num) ) {
                        players.get(playerCount).runners --;
                    } 
                    players.get(playerCount).currentMarkerRows.set(col_num, -1);
                    players.get(playerCount).currentRunnerRows.set(col_num, players.get(playerCount).currentMarkerRows.get(col_num)); 
                    
                }
            }
            getActivePlayer().runners = 0;
            for (int i = 0; i<11; i++) {
                for (int j = getActivePlayer().currentMarkerRows.get(i)+1; j<=getActivePlayer().currentRunnerRows.get(i); j++) {
                    board.setPlayerColumnColor(i, j, activeInitial, Color.WHITE);
                }
                getActivePlayer().currentRunnerRows.set(i, getActivePlayer().currentMarkerRows.get(i));
            }
            nextActivePlayer();
            die.reset();
            continueButton.setEnabled(false);
        }
        return fullFilled.get(col_num);
}

    private void createPassButton(){
    
        passButton = buttons.getPassButton();
        passButton.setBounds(1330, 650, 115, 35);
        
        passButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passMethod();
            }
        });
        mainPanel.add(passButton);
    }

    public void passMethod() {
        for (int i = 0; i<11; i++) {
            int currRow = getActivePlayer().currentRunnerRows.get(i);
            getActivePlayer().currentMarkerRows.set(i, currRow);
            // if (currRow > getActivePlayer().currentMarkerRows.get(i)) {
            for (int j = 0; j<=currRow; j++) {
                board.setPlayerColumnColor(i, j, activeInitial, getActivePlayer().color);
            }
            //}
        }
        getActivePlayer().runners = 0;
        nextActivePlayer();
        die.reset();
        continueButton.setEnabled(false);
    }

    private void createSettingsPanel(){
        //Create Settings Panel
        settingsPanel = new JPanel();
        settingsPanel.setBounds(1300, 50, 150, 45);
        settingsPanel.setOpaque(false);
        mainPanel.add(settingsPanel);

        //Create Settings Button 
        settingsButton = buttons.getSettingsButton();
        settingsPanel.add(settingsButton);
        settingsButton.addActionListener(this);

    }

    private void createHelpPanel(){
        //Create Help Panel
        helpPanel = new JPanel();
        helpPanel.setBounds(1300, 125, 150, 45);
        helpPanel.setOpaque(false);
        mainPanel.add(helpPanel);

        //Create Help Button
        helpButton = buttons.getHelpButton();
        helpPanel.add(helpButton);
    }

    public void createBoard(){
        board = new Board();
        board.setBounds(275, 180, 780, 580);
        mainPanel.add(board);
    }

    public void createDie(){
        die = new Die();
        dicePanel = die.getDicePanel();
        mainPanel.add(dicePanel);
    }

    public void createRollButton(){
    rollButton = die.getRollButton();
    rollButton.setBounds(1200, 600, 115, 35);
    mainPanel.add(rollButton);

    
    ActionListener[] currentListeners = rollButton.getActionListeners();

    
    for (ActionListener listener : currentListeners) {
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passButton.setEnabled(false);
                continueButton.setEnabled(true);
                
            }
        });
    }
}


    public void createComboPanel(){
        comboPanel = die.getUPanel();
        mainPanel.add(comboPanel);

    }

    
public void createScoreBoard(){
    
    score =new JLabel("Score Board");
    score.setFont(font2);
    score.setForeground(Color.WHITE);

    scorePanel = new JPanel();
    scorePanel.setOpaque(false);
    scorePanel.setBounds(25, 25, 200, 250);
    scorePanel.add(score);
    mainPanel.add(scorePanel);

    scoreboardPanel = new JPanel();
    scoreboardPanel.setLayout(new BoxLayout(scoreboardPanel, BoxLayout.Y_AXIS));
    scoreboardPanel.setBounds(25, 80, 200, 250);
    scoreboardPanel.setBackground(Color.WHITE);
    scoreboardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

    int count = playerCount;
    List<String> playerNames = screenManager.getNewGameScreen().getPlayerNames();
    ArrayList<Color> colorPlayer = new ArrayList<>();
    fullFilled = new ArrayList<>();
    colorPlayer.add(Color.RED);
    colorPlayer.add(Color.GREEN);
    colorPlayer.add(Color.BLACK);
    colorPlayer.add(Color.BLUE);
    colorPlayer.add(Color.PINK);

    for (int i = 0; i < count; i++) {
        String playerName = playerNames.get(i);
        JLabel label = new JLabel(playerName + ": 0" );
        label.setFont(font1);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        scoreboardPanel.add(label);
    }
    for (int j = 0; j<11; j++) {
        fullFilled.add(false);
    }

    mainPanel.add(scoreboardPanel);

    players = createPlayers(count, playerNames, colorPlayer);
}

public void updateScoreboard() {
    for (int i = 0; i < players.size(); i++) {
        JLabel label = (JLabel) scoreboardPanel.getComponent(i);
        label.setText(players.get(i).getName() + ": " + players.get(i).fullHouse);
    }
}

public List<Player> createPlayers(int count, List<String> playerNames, ArrayList<Color> colorPlayer) {
    List<Player> players = new ArrayList<>();

    for (int i = 0; i < count; i++) {
        String playerName = playerNames.get(i);
        Color color = colorPlayer.get(i);
        Player player = new Player(playerName, screenManager, color, board); // Pass the screenManager instance
        players.add(player);
    }

    return players;
}

    public void actionPerformed(ActionEvent e) {
        // Handling the buttons
        if (e.getSource() == helpButton) {
            
        } else if (e.getSource() == settingsButton) {
            screenManager.showScreen("settings");
        }
    }

    public JButton getHelpButton(){
        return helpButton;
    }

    public JButton getSettingsButton(){
        return settingsButton;
    }

    public JButton getRollButton(){
        return rollButton;
    }
    
    public JButton getContinueButton(){
        return continueButton;
    }
    
    public void setPassButton(boolean B){
        passButton.setEnabled(B);
    }

    public void applyColorGradient(ColorThemes theme) {
        mainPanel.removeAll();
        mainPanel = theme.createGradientPanel();
        mainPanel.setLayout(null);

        mainPanel.add(helpPanel);
        mainPanel.add(settingsPanel);
        mainPanel.add(comboPanel);
        mainPanel.add(dicePanel);
        mainPanel.add(board);
        mainPanel.add(rollButton);
        mainPanel.add(scoreboardPanel);
        mainPanel.add(scorePanel);
        mainPanel.add(continueButton);
        mainPanel.add(passButton);
    }

    public JPanel getPanel() {
        return mainPanel;
    }

    public void checkWinner() {
        for (int playerCount = 0; playerCount < players.size(); playerCount++) {
            if (players.get(playerCount).fullHouse == 3) {
                winner = players.get(playerCount);
                JOptionPane.showMessageDialog(null, winner + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        }
    }
}
