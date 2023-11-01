import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SettingsScreen implements ActionListener{
    private ScreenManager screenManager;


    private JPanel mainPanel;
    private Buttons buttons = new Buttons();

    private JPanel settingsTitlePanel, changeDisplayPanel, loadGamePanel, backPanel, helpPanel, quitPanel;
    private JButton changeDisplayButton, loadGameButton, backButton, helpButton, quitButton;
    private JLabel settingsTitleLabel;
    

    Font settingsTitleFont = new Font("OCR A Extended", Font.BOLD, 97);
    

    public SettingsScreen(ScreenManager screenManager) {
        this.screenManager = screenManager;
        mainPanel = ColorThemes.BLACK_BLUE.createGradientPanel();
        mainPanel.setBounds(0, 0, 1400, 800);
        mainPanel.setOpaque(false);
        mainPanel.setLayout(null);

        createSettingsTitlePanel();
        createBackPanel();
        createHelpPanel();
        createLoadGamePanel();
        createChangeDisplayPanel();
        createQuitPanel();

    }

    private void createSettingsTitlePanel(){
        //Create settings title panel;
        settingsTitlePanel = new JPanel();
        settingsTitlePanel.setBounds( 450, 100, 600, 120);
        settingsTitlePanel.setOpaque(false);
        mainPanel.add(settingsTitlePanel);

        //Create title name label s
        settingsTitleLabel = new JLabel("Settings");
        settingsTitleLabel.setForeground(Color.white);
        settingsTitleLabel.setFont(settingsTitleFont);

        settingsTitlePanel.add(settingsTitleLabel);
    }

    private void createChangeDisplayPanel(){
        // create change display panel
        changeDisplayPanel = new JPanel();
        changeDisplayPanel.setBounds(650, 350, 200, 65);
        changeDisplayPanel.setOpaque(false);
        mainPanel.add(changeDisplayPanel);

        changeDisplayButton = buttons.getChangeDisplayButton(); 
        changeDisplayButton.addActionListener(this);  
        changeDisplayPanel.add(changeDisplayButton);
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


    private void  createBackPanel(){
        //Create Back Panel
        backPanel = new JPanel();
        backPanel.setBounds(1300, 50, 150, 45);
        backPanel.setOpaque(false);
        mainPanel.add(backPanel);

        //Create Back Button 
        backButton = buttons.getBackButton();
        backPanel.add(backButton);
        backButton.addActionListener(this);
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
        if (e.getSource() == backButton) {
            screenManager.goBack();
        } else if (e.getSource() == helpButton) {
            //
        } else if (e.getSource() == changeDisplayButton) {
            screenManager.showScreen("changeDisplay");
        } else if (e.getSource() == loadGameButton) {
            
        } else if (e.getSource() == quitButton) {
            System.exit(0);
        } else if (e.getSource() == helpButton) {
            System.exit(0);
        }

    }

    public JButton getBackButton(){
        return backButton;
    }

    public JButton getLoadGameButton(){
        return loadGameButton;
    }

    public JButton getHelpButton(){
        return helpButton;
    }

    public JButton getChangeDisplayButton(){
        return changeDisplayButton;
    }

    public JButton getQuitButton(){
        return quitButton;
    }

    public void applyColorGradient(ColorThemes theme) {
        mainPanel.removeAll();
        mainPanel = theme.createGradientPanel();
        mainPanel.setLayout(null);

        mainPanel.add(backPanel);
        mainPanel.add(loadGamePanel);
        mainPanel.add(helpPanel);
        mainPanel.add(changeDisplayPanel);
        mainPanel.add(quitPanel);
        mainPanel.add(settingsTitlePanel);
        
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
