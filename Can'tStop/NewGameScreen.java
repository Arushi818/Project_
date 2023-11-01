import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;


public class NewGameScreen implements ActionListener{

    private ScreenManager screenManager;

    private JPanel mainp;
    private JPanel mainPanel;
    private Buttons buttons = new Buttons();

    private JPanel playerNoPanel;
    private JLabel playerNoLabel;
    private JComboBox<Integer> playerNocomboBox;

    private JLabel pictureLabel1, pictureLabel2, pictureLabel3, pictureLabel4;
    private JTextField playerTextField1, playerTextField2, playerTextField3, playerTextField4;

    private JPanel computerPanel;
    private JLabel computerLabel;
    private JComboBox<Integer> computerPlayersComboBox;

    private JPanel difficultyPanel;
    private JLabel difficultyLabel;
    private JComboBox<String> difficultyComboBox;

    private Font font = new Font("OCR A Extended", Font.PLAIN, 25);
    

    private JPanel helpPanel, startGamePanel, backPanel;
    private JButton helpButton, startGameButton, backButton;
   


    public NewGameScreen(ScreenManager screenManager) {
        this.screenManager = screenManager;
        mainp = ColorThemes.BLACK_BLUE.createGradientPanel();;
        mainp.setLayout(null);

        createSelectionPanel();
        createBackPanel();
        createHelpPanel();
        createStartGamePanel();
    }

    public void createSelectionPanel(){
        mainPanel = new JPanel();
        mainPanel.setBounds(100, 225, 1000, 500);
        mainPanel.setOpaque(false);
        mainPanel.setLayout(null);
        mainp.add(mainPanel);

        playerNoLabel = new JLabel("Select the Number of Players");
        playerNoLabel.setForeground(Color.white);
        playerNoLabel.setFont(font);

        playerNoPanel = new JPanel();
        playerNoPanel.add(playerNoLabel);
        playerNoPanel.setBounds(0,0,450,35);
        playerNoPanel.setOpaque(false);;
        mainPanel.add(playerNoPanel);

         // create a drop-down list with options 1-4
         playerNocomboBox = new JComboBox<>(new Integer[] {null, 2, 3, 4});
         
         playerNocomboBox.setBackground(Color.LIGHT_GRAY);
         playerNocomboBox.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
         //comboBox.setPreferredSize(new Dimension(50, 30));
         playerNocomboBox.setBounds(600, 0, 175, 35);
         playerNocomboBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
         playerNocomboBox.setFont(font);
         playerNocomboBox.addActionListener(this);
         mainPanel.add(playerNocomboBox);

        pictureLabel1 = new JLabel("1");
        pictureLabel1.setBackground(new Color(0,0,0));
        pictureLabel1.setOpaque(true);
        pictureLabel1.setBounds(50,70,200,175);
        mainPanel.add(pictureLabel1);

        pictureLabel2 = new JLabel("2");
        pictureLabel2.setBackground(new Color(0,0,0));
        pictureLabel2.setOpaque(true);
        pictureLabel2.setBounds(270,70,200,175);
        mainPanel.add(pictureLabel2);

        pictureLabel3 = new JLabel("3");
        pictureLabel3.setBackground(new Color(0,0,0));
        pictureLabel3.setOpaque(true);
        pictureLabel3.setBounds(490,70,200,175);
        mainPanel.add(pictureLabel3);

        pictureLabel4 = new JLabel("4");
        pictureLabel4.setBackground(new Color(0,0,0));
        pictureLabel4.setOpaque(true);
        pictureLabel4.setBounds(710,70,200,175);
        mainPanel.add(pictureLabel4);

        playerTextField1 = new JTextField("Player 1");
        playerTextField1.setBounds(50,265,200,35);
        playerTextField1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        playerTextField1.setFont(font);
        mainPanel.add(playerTextField1);

        playerTextField2 = new JTextField("Player 2");
        playerTextField2.setBounds(270,265,200,35);
        playerTextField2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        playerTextField2.setFont(font);
        mainPanel.add(playerTextField2);

        playerTextField3 = new JTextField("Player 3");
        playerTextField3.setBounds(490,265,200,35);
        playerTextField3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        playerTextField3.setFont(font);
        mainPanel.add(playerTextField3);

        playerTextField4 = new JTextField("Player 4");
        playerTextField4.setBounds(710,265,200,35);
        playerTextField4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        playerTextField4.setFont(font);
        mainPanel.add(playerTextField4);

        computerLabel = new JLabel("Select the Number of Computer Players");
        computerLabel.setForeground(Color.white);
        computerLabel.setFont(font);

        computerPanel = new JPanel();
        computerPanel.add(computerLabel);
        computerPanel.setBounds(0,335,590,35);
        computerPanel.setOpaque(false);
        mainPanel.add(computerPanel);

        computerPlayersComboBox = new JComboBox<>(new Integer[] {null});
        computerPlayersComboBox.setBackground(Color.LIGHT_GRAY);
        computerPlayersComboBox.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
        computerPlayersComboBox.setBounds(600, 335, 175, 35);
        computerPlayersComboBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        computerPlayersComboBox.setFont(font);
        computerPlayersComboBox.addActionListener(this);
        mainPanel.add(computerPlayersComboBox);

        difficultyLabel = new JLabel("Select the Difficulty Level");
        difficultyLabel.setForeground(Color.white);
        difficultyLabel.setFont(font);

        difficultyPanel = new JPanel();
        difficultyPanel.add(difficultyLabel);
        difficultyPanel.setBounds(0,390,440,35);
        difficultyPanel.setOpaque(false);
        mainPanel.add(difficultyPanel);

        difficultyComboBox = new JComboBox<>(new String[] {null, "Easy","Hard"});
        difficultyComboBox.setBackground(Color.LIGHT_GRAY);
        difficultyComboBox.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
        difficultyComboBox.setBounds(600, 390, 175, 35);
        difficultyComboBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        difficultyComboBox.setFont(font);
        difficultyComboBox.addActionListener(this);
        
        playerNocomboBox.addActionListener(e -> {
                int x = (int) playerNocomboBox.getSelectedItem();
                
                computerPlayersComboBox.removeAllItems();
                
                for (int j = 0; j < x; j++) {
                        computerPlayersComboBox.addItem(j);
                }
        });
        
        mainPanel.add(difficultyComboBox);
    }

    private void  createBackPanel(){
        //Create Back Panel
        backPanel = new JPanel();
        backPanel.setBounds(1300, 50, 150, 45);
        backPanel.setOpaque(false);
        mainp.add(backPanel);

        //Create Back Button 
        backButton = buttons.getBackButton();
        backPanel.add(backButton);
        backButton.addActionListener(this);
    }

    private void createStartGamePanel(){
        //Create StartGame Panel
        startGamePanel = new JPanel();
        startGamePanel.setBounds(1300, 600, 200, 65);
        startGamePanel.setOpaque(false);
        mainp.add(startGamePanel);

        //Create StartGame Button
        startGameButton = buttons.getstartGameButton();
        startGamePanel.add(startGameButton);
        startGameButton.addActionListener(this);
        //main.add(startGamePanel);
    }

    private void createHelpPanel(){
        //Create Help Panel
        helpPanel = new JPanel();
        helpPanel.setBounds(1300, 125, 150, 45);
        helpPanel.setOpaque(false);
        mainp.add(helpPanel);

        //Create Help Button
        helpButton = buttons.getHelpButton();
        helpPanel.add(helpButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            screenManager.showScreen("home");
        } else if (e.getSource() == startGameButton) {
            screenManager.showScreen("game");
    
        } else if (e.getSource() == helpButton) {

        } 
    }

    public int getSelectedPlayers() {
        return (int) playerNocomboBox.getSelectedItem();
    }
    
    public int getSelectedComputerPlayers() {
        return (int) computerPlayersComboBox.getSelectedItem();
    }
    
    public String getSelectedDifficulty() {
        return (String) difficultyComboBox.getSelectedItem();
    }

    public JButton getBackButton(){
        return backButton;
    }

    public JButton getStartGameButton(){
        return startGameButton;
    }

    public JButton getHelpButton(){
        return helpButton;
    }

    public List<String> getPlayerNames() {
        List<String> playerNames = new ArrayList<>();
        playerNames.add(playerTextField1.getText());
        playerNames.add(playerTextField2.getText());
        playerNames.add(playerTextField3.getText());
        playerNames.add(playerTextField4.getText());
        return playerNames;
    }
    

    public void applyColorGradient(ColorThemes theme) {
        mainp.removeAll();
        mainp = theme.createGradientPanel();
        mainp.setLayout(null);

        mainp.add(mainPanel);
        mainp.add(helpPanel);
        mainp.add(startGamePanel);
        mainp.add(backPanel);
    
        
    }

    public JPanel getPanel() {
        return mainp;
    }

    class NoSelectionException extends Exception {
        public NoSelectionException(String message) {
            super(message);
        }
    }
}
