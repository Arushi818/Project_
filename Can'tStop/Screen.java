import javax.swing.*;

public class Screen {
    private JFrame window;
    private ScreenManager screenManager;

    public Screen() {
        window = new JFrame("Can't Stop!");
        window.setSize(1600, 900);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setResizable(false);
        window.setLocationRelativeTo(null);

        screenManager = new ScreenManager(window);

        window.setVisible(true);
    }
}