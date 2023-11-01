import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChangeDisplayScreen implements ActionListener {

    private JPanel mainPanel;
    private Buttons buttons = new Buttons();

    private ScreenManager screenManager;

    private JPanel screenTitlePanel, colorOption1Panel, colorOption2Panel, colorOption3Panel, colorOption4Panel;
    private JLabel screenTitleLabel;
    private JButton colorOption1Button, colorOption2Button, colorOption3Button, colorOption4Button;

    private Font screenTitleFont = new Font("OCR A Extended", Font.BOLD, 97);

    private JPanel helpPanel, backPanel;
    private JButton helpButton, backButton;

    public ChangeDisplayScreen(ScreenManager screenManager) {
        this.screenManager = screenManager;
        mainPanel = ColorThemes.BLACK_BLUE.createGradientPanel();
        mainPanel.setBounds(0, 0, 1400, 800);
        //mainPanel.setOpaque(false);
        mainPanel.setLayout(null);


        createTitlePanel();
        createButtonPanels();
        createHelpPanel();
        createBackPanel();

    }

    public void createTitlePanel(){

        // Create Screen title panel;
        screenTitlePanel = new JPanel();
        screenTitlePanel.setBounds(150, 100, 1000, 120);
        screenTitlePanel.setOpaque(false);
        mainPanel.add(screenTitlePanel);

        // Create title name label
        screenTitleLabel = new JLabel("Display Settings");
        screenTitleLabel.setForeground(Color.white);
        screenTitleLabel.setFont(screenTitleFont);

        screenTitlePanel.add(screenTitleLabel);

    }

    public void createButtonPanels(){
        colorOption1Panel = new JPanel();
        colorOption1Panel.setBounds(650, 350, 200, 65);
        colorOption1Panel.setOpaque(false);
        mainPanel.add(colorOption1Panel);

        colorOption1Button = buttons.getColorOption1Button(); 
        colorOption1Button.addActionListener(this);
        colorOption1Panel.add(colorOption1Button);

        colorOption2Panel = new JPanel();
        colorOption2Panel.setBounds(650, 450, 200, 65);
        colorOption2Panel.setOpaque(false);;
        mainPanel.add(colorOption2Panel);

        colorOption2Button = buttons.getColorOption2Button(); 
        colorOption2Button.addActionListener(this); 
        colorOption2Panel.add(colorOption2Button);

        colorOption3Panel = new JPanel();
        colorOption3Panel.setBounds(650, 550, 200, 65);
        colorOption3Panel.setOpaque(false);
        mainPanel.add(colorOption3Panel);

        colorOption3Button = buttons.getColorOption3Button(); 
        colorOption3Button.addActionListener(this); 
        colorOption3Panel.add(colorOption3Button);

        colorOption4Panel = new JPanel();
        colorOption4Panel.setBounds(650, 650, 200, 65);
        colorOption4Panel.setOpaque(false);
        mainPanel.add(colorOption4Panel);

        colorOption4Button = buttons.getColorOption4Button(); 
        colorOption4Button.addActionListener(this); 
        colorOption4Panel.add(colorOption4Button);
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
            
        } else if (e.getSource() == colorOption1Button) {
            applyColorTheme(ColorThemes.BLACK_BLUE);
            screenManager.getHomeScreen().applyColorGradient(ColorThemes.BLACK_BLUE);
            screenManager.getSettingsScreen().applyColorGradient(ColorThemes.BLACK_BLUE);
            screenManager.getNewGameScreen().applyColorGradient(ColorThemes.BLACK_BLUE);
            if (screenManager.gameScreen != null){
                screenManager.gameScreen.applyColorGradient(ColorThemes.BLACK_BLUE);
            }
            applyColorGradient(ColorThemes.BLACK_BLUE);

        } else if (e.getSource() == colorOption2Button) {
            applyColorTheme(ColorThemes.AMETHYST_DREAM_GRADIENT);
            screenManager.getHomeScreen().applyColorGradient(ColorThemes.AMETHYST_DREAM_GRADIENT);
            screenManager.getSettingsScreen().applyColorGradient(ColorThemes.AMETHYST_DREAM_GRADIENT);
            screenManager.getNewGameScreen().applyColorGradient(ColorThemes.AMETHYST_DREAM_GRADIENT);
            if (screenManager.gameScreen != null){
                screenManager.gameScreen.applyColorGradient(ColorThemes.AMETHYST_DREAM_GRADIENT);
            }
            applyColorGradient(ColorThemes.AMETHYST_DREAM_GRADIENT);


        } else if (e.getSource() == colorOption3Button) {
            applyColorTheme(ColorThemes.HARMONY_SPECTRUM_GRADIENT);
            screenManager.getHomeScreen().applyColorGradient(ColorThemes.HARMONY_SPECTRUM_GRADIENT);
            screenManager.getSettingsScreen().applyColorGradient(ColorThemes.HARMONY_SPECTRUM_GRADIENT);
            screenManager.getNewGameScreen().applyColorGradient(ColorThemes.HARMONY_SPECTRUM_GRADIENT);
            if (screenManager.gameScreen != null){
                screenManager.gameScreen.applyColorGradient(ColorThemes.HARMONY_SPECTRUM_GRADIENT);
            }
            applyColorGradient(ColorThemes.HARMONY_SPECTRUM_GRADIENT);
    
        } else if (e.getSource() == colorOption4Button) {
            applyColorTheme(ColorThemes.ROSE_GOLD);
            screenManager.getHomeScreen().applyColorGradient(ColorThemes.ROSE_GOLD);
            screenManager.getSettingsScreen().applyColorGradient(ColorThemes.ROSE_GOLD);
            screenManager.getNewGameScreen().applyColorGradient(ColorThemes.ROSE_GOLD);
            if (screenManager.gameScreen != null){
                screenManager.gameScreen.applyColorGradient(ColorThemes.ROSE_GOLD);
            }
            
            applyColorGradient(ColorThemes.ROSE_GOLD);
        }
    }

    public void applyColorTheme(ColorThemes colorTheme) {
        // HomeScreen buttons
        screenManager.getHomeScreen().getNewGameButton().setBackground(colorTheme.getButtonColor());
        screenManager.getHomeScreen().getLoadGameButton().setBackground(colorTheme.getButtonColor());
        screenManager.getHomeScreen().getSettingsButton().setBackground(colorTheme.getButtonColor());
        screenManager.getHomeScreen().getHelpButton().setBackground(colorTheme.getButtonColor());
        screenManager.getHomeScreen().getQuitButton().setBackground(colorTheme.getButtonColor());
    
        // SettingsScreen buttons
        screenManager.getSettingsScreen().getChangeDisplayButton().setBackground(colorTheme.getButtonColor());
        screenManager.getSettingsScreen().getLoadGameButton().setBackground(colorTheme.getButtonColor());
        screenManager.getSettingsScreen().getLoadGameButton().setBackground(colorTheme.getButtonColor());
        screenManager.getSettingsScreen().getBackButton().setBackground(colorTheme.getButtonColor());
        screenManager.getSettingsScreen().getHelpButton().setBackground(colorTheme.getButtonColor());
        screenManager.getSettingsScreen().getQuitButton().setBackground(colorTheme.getButtonColor());
        

        // NewGameSCreen buttons
        screenManager.getNewGameScreen().getBackButton().setBackground(colorTheme.getButtonColor());
        screenManager.getNewGameScreen().getHelpButton().setBackground(colorTheme.getButtonColor());
        screenManager.getNewGameScreen().getStartGameButton().setBackground(colorTheme.getButtonColor());

        //GameScreen

        if (screenManager.gameScreen != null){
            screenManager.gameScreen.getSettingsButton().setBackground(colorTheme.getButtonColor());
            screenManager.gameScreen.getHelpButton().setBackground(colorTheme.getButtonColor());
            screenManager.gameScreen.getRollButton().setBackground(colorTheme.getButtonColor());
        }

        screenManager.setColor(colorTheme);
        


        colorOption1Button.setBackground(colorTheme.getButtonColor());
        colorOption2Button.setBackground(colorTheme.getButtonColor());
        colorOption3Button.setBackground(colorTheme.getButtonColor());
        colorOption4Button.setBackground(colorTheme.getButtonColor());
        helpButton.setBackground(colorTheme.getButtonColor());
        backButton.setBackground(colorTheme.getButtonColor());



    }

    public void applyColorGradient(ColorThemes theme) {
        mainPanel.removeAll();
        mainPanel = theme.createGradientPanel();
        mainPanel.setLayout(null);

        mainPanel.add(backPanel);
        mainPanel.add(helpPanel);
        mainPanel.add(colorOption1Panel);
        mainPanel.add(colorOption2Panel);
        mainPanel.add(colorOption3Panel);
        mainPanel.add(colorOption4Panel);
        mainPanel.add(screenTitlePanel);

        screenManager.setScreen(mainPanel);
        
    }

    public JPanel getPanel() {
        return mainPanel;
    }

}

