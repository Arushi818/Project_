import javax.swing.*;

import java.awt.Color;
import java.util.Stack;
public class ScreenManager {
    private JFrame window;

    private HomeScreen homeScreen;
    private NewGameScreen newGameScreen;
    private SettingsScreen settingsScreen;
    private ChangeDisplayScreen changeDisplayScreen;
    public GameScreen gameScreen;

    private Stack<String> screenHistory;
    private ColorThemes currentTheme;
    

    public ScreenManager(JFrame window) {
        this.window = window;

        screenHistory = new Stack<String>();

        homeScreen = new HomeScreen(this);
        newGameScreen = new NewGameScreen(this);
        settingsScreen = new SettingsScreen(this);
        changeDisplayScreen = new ChangeDisplayScreen(this);
        //gameScreen = new GameScreen(this);

        currentTheme = ColorThemes.BLACK_BLUE;

        showScreen("home");
    }


    public void showScreen(String screenName) {
        if (screenName.equals("home")) {
            screenHistory.push("home");
            setScreen(homeScreen.getPanel());
        } else if (screenName.equals("newGame")) {
            screenHistory.push("newGame");
            setScreen(newGameScreen.getPanel());
        } else if (screenName.equals("settings")) {
            screenHistory.push("settings");
            setScreen(settingsScreen.getPanel());
        } else if (screenName.equals("changeDisplay")) {
            screenHistory.push("changeDisplay");
            setScreen(changeDisplayScreen.getPanel());
        } else if (screenName.equals("game")) {
            if (gameScreen == null) {
                gameScreen = new GameScreen(this);
            }
            screenHistory.push("game");
            setScreen(gameScreen.getPanel());
        }
    }

    public HomeScreen getHomeScreen() {
        return homeScreen;
    }
    
    public NewGameScreen getNewGameScreen() {
        return newGameScreen;
    }
    
    public SettingsScreen getSettingsScreen() {
        return settingsScreen;
    }
    
    public ChangeDisplayScreen getChangeDisplayScreen() {
        return changeDisplayScreen;
    }


    public void goBack() {
        if (!screenHistory.isEmpty()) {
            screenHistory.pop(); // Remove current screen from the stack
            if (!screenHistory.isEmpty()) {
                String previousScreen = screenHistory.pop(); // Get the previous screen
                showScreen(previousScreen);
            }
        }
    }

    public void setColor(ColorThemes theme){
        currentTheme = theme;        
    }

    public ColorThemes getColor(){
        return currentTheme;       
    }    

    public void setScreen(JPanel panel) {
        window.setContentPane(panel);
        window.revalidate();
        window.repaint();
    }
}
