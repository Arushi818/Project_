import javax.swing.*;
import java.awt.*;
public class Buttons {
    private JButton button;
    private Font font1 = new Font("Cascadia Mono SemiBold", Font.BOLD, 30);
    private Font font2 = new Font("Cascadia Mono SemiBold", Font.BOLD, 15);
    private Font font3 = new Font("Cascadia Mono SemiBold", Font.BOLD, 25);
    public Buttons(){}

    // Creating JButton with text, background color, foreground color and font
    public JButton getButton(String buttonText, Color background, Color foreground, Font font) {
        button = new JButton(buttonText);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFont(font);
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        return button;
    }

    // Methods to create buttons using the getButton method
    public JButton getNewGameButton() {
        return getButton("New Game", Color.black, Color.white, font1);
    }
    
    public JButton getLoadGameButton() {
        return getButton("Load Game", Color.black, Color.white, font1);
    }
    
    public JButton getQuitButton() {
        return getButton("Quit", Color.black, Color.white, font1);
    }
    
    public JButton getSettingsButton() {
        return getButton("Settings", Color.black, Color.white, font2);
    }
    
    public JButton getHelpButton() {
        return getButton("Help", Color.black, Color.white, font2);
    }

    public JButton getBackButton() {
        return getButton("Back", Color.black, Color.white, font2);
    }
    public JButton getChangeDisplayButton() {
        return getButton("Change Display", Color.black, Color.white, font3);
    }
    public JButton getstartGameButton() {
        return getButton("Start Game", Color.black, Color.white, font1);
    }
    public JButton getSaveButton() {
        return getButton("Save Game", Color.black, Color.white, font1);
    }

    public JButton getColorOption1Button(){
        return getButton("Option1", Color.black, Color.white, font3);   
    }

    public JButton getColorOption2Button(){
        return getButton("Option 2", Color.black, Color.white, font3);   
    }

    public JButton getColorOption3Button(){
        return getButton("Option 3", Color.black, Color.white, font3);   
    }

    public JButton getColorOption4Button(){
        return getButton("Option 4", Color.black, Color.white, font3);   
    }

    public JButton getRollButton(){
        return getButton("Roll", Color.black, Color.white, font3);   
    }
    
    public JButton getContinueButton(){
        return getButton("Continue", Color.black, Color.white, font3);   
    }

    public Font getFont3(){
        return font3;
    }

    public JButton getPassButton() {
        return getButton("Pass", Color.black, Color.white, font3);  
    }

}
