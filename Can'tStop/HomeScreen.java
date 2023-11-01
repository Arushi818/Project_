import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class HomeScreen implements ActionListener {

    private ScreenManager screenManager;

    private JPanel mainPanel;
    private Buttons buttons = new Buttons();

    JPanel titlePanel;
    JLabel titleLabel;
    
    JPanel newGamePanel, loadGamePanel, quitPanel;
    JButton newGameButton, loadGameButton, quitButton;

    JPanel settingsPanel, helpPanel;
    JButton settingsButton, helpButton;

    Font titleFont = new Font("OCR A Extended", Font.BOLD, 90);

    public HomeScreen(ScreenManager screenManager) {
        this.screenManager = screenManager;
        //mainPanel = new JPanel();
        mainPanel = ColorThemes.BLACK_BLUE.createGradientPanel();
        mainPanel.setBounds(0, 0, 1400, 800);
        
        mainPanel.setLayout(null);

        createTitlePanel();
        createNewGamePanel();
        createLoadGamePanel();
        createQuitPanel();
        createHelpPanel();
        createSettingsPanel();
    }

    private void createTitlePanel(){
        //Create title panel;
        titlePanel = new JPanel();
        titlePanel.setBounds( 450, 100, 600, 120);
        titlePanel.setOpaque(false);
        mainPanel.add(titlePanel);

        //Create title name label s
        titleLabel = new JLabel("Can't Stop!");
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(titleFont);
        
        titlePanel.add(titleLabel);
    }

    private void  createNewGamePanel(){

        //Create New Game Panel
        newGamePanel = new JPanel();
        newGamePanel.setBounds(650, 350, 200, 65);
        newGamePanel.setOpaque(false);
        mainPanel.add(newGamePanel);

        //Create New Game Button
        newGameButton = buttons.getNewGameButton();
        newGameButton.addActionListener(this);
        newGamePanel.add(newGameButton);
    }

    private void  createLoadGamePanel(){
        //Create Load Game Panel;
        loadGamePanel = new JPanel();
        loadGamePanel.setBounds(650, 450, 200, 65);
        loadGamePanel.setOpaque(false);
        mainPanel.add(loadGamePanel);

        //Create loadGame Button 
        loadGameButton = buttons.getLoadGameButton();        
        loadGamePanel.add(loadGameButton);
        loadGameButton.addActionListener(this);
    }

    private void createQuitPanel(){
        //Create quit panel;
        quitPanel = new JPanel();
        quitPanel.setBounds(650, 550, 200, 65);
        quitPanel.setOpaque(false);
        mainPanel.add(quitPanel);

        //Create quit game Button 
        quitButton = buttons.getQuitButton(); 
        quitButton.addActionListener(this);  
        quitPanel.add(quitButton);
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

    

    public void actionPerformed(ActionEvent e) {
        // Handling the buttons
        if (e.getSource() == newGameButton) {
            screenManager.showScreen("newGame");
        } else if (e.getSource() == loadGameButton) {
            // Handle load game button action
        } else if (e.getSource() == quitButton) {
            System.exit(0);
        } else if (e.getSource() == settingsButton) {
            screenManager.showScreen("settings");
        } else if (e.getSource() == helpButton) {
            
        } 

    }

    public JButton getHelpButton(){
        return helpButton;
    }

    public JButton getSettingsButton(){
        return settingsButton;
    }

    public JButton getNewGameButton(){
        return newGameButton;
    }

    public JButton getLoadGameButton(){
        return loadGameButton;
    }

    public JButton getQuitButton(){
        return quitButton;
    }

    public void applyColorGradient(ColorThemes theme) {
        mainPanel.removeAll();
        mainPanel = theme.createGradientPanel();
        mainPanel.setLayout(null);

        mainPanel.add(helpPanel);
        mainPanel.add(settingsPanel);
        mainPanel.add(loadGamePanel);
        mainPanel.add(quitPanel);
        mainPanel.add(newGamePanel);
        mainPanel.add(titlePanel);
        
    }




    public JPanel getPanel() {
        return mainPanel;
    }
}
