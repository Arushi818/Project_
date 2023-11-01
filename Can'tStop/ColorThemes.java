import java.awt.*;
import java.awt.Color;
import javax.swing.JPanel;

public class ColorThemes {
    
    private String name;
    private Font font;
    private Color backgroundColor1;
    private Color backgroundColor2;
    private Color buttonColor;

    public static final ColorThemes BLACK_BLUE = new ColorThemes("Black and Blue",  new Color(0x673AB7), new Color(0x2196F3), new Color(0x9C27B0));
    public static final ColorThemes WHITE_YELLOW = new ColorThemes("White and Yellow", new Color(0x1E88E5), new Color(0xFFA726), new Color(0x4CAF50));
    public static final ColorThemes CRIMSON_PINK = new ColorThemes("Crimson and Pink", new Color(0xDC143C), new Color(0xFFC0CB), new Color(0xDB7093));
    
    public static final ColorThemes ROSE_GOLD = new ColorThemes("Rose and Gold", new Color(0xF48FB1), new Color(0xFFD740), new Color(0xE91E63));
    
    public static final ColorThemes TWILIGHT = new ColorThemes("Twilight", new Color(0x7C4DFF), new Color(0x4A148C), new Color(0x9575CD));
    public static final ColorThemes TURQUOISE_SILVER = new ColorThemes("Turquoise and Silver", new Color(0x40E0D0), new Color(0xC0C0C0), new Color(0x00CED1));
    public static final ColorThemes INDIGO_WHEAT = new ColorThemes("Indigo and Wheat", new Color(0x4B0082), new Color(0xF5DEB3), new Color(0x9370DB));
    public static final ColorThemes MAROON_TAN = new ColorThemes("Maroon and Tan", new Color(0x800000), new Color(0xD2B48C), new Color(0xB22222));
    public static final ColorThemes SLATE_GRAY_CORAL = new ColorThemes("Slate Gray and Coral", new Color(0x708090), new Color(0xFF7F50), new Color(0x778899));
    
    public static final ColorThemes GREEN_BLUE = new ColorThemes("Green and Blue", new Color(0x4CAF50), new Color(0x00BCD4), new Color(0x8BC34A));
   

    public static final ColorThemes EMERALD_SEA_GRADIENT = new ColorThemes("Emerald Sea Gradient", new Color(0x0A7E8C), new Color(0x2E3A59), new Color(0x16DB93));

    public static final ColorThemes SAPPHIRE_SKY_GRADIENT = new ColorThemes("Sapphire Sky Gradient", new Color(0x2F4858), new Color(0x49759C), new Color(0x6798C3));
    public static final ColorThemes AMETHYST_DREAM_GRADIENT = new ColorThemes("Amethyst Dream Gradient", new Color(0x7B4397), new Color(0xDC2430), new Color(0xAB8CE5));
    public static final ColorThemes GOLDEN_TWILIGHT_GRADIENT = new ColorThemes("Golden Twilight Gradient", new Color(0xFF9F1C), new Color(0xFF6B6B), new Color(0x4ECDC4));

    public static final ColorThemes HARMONY_SPECTRUM_GRADIENT = new ColorThemes("Harmony Spectrum Gradient", new Color(0x3A96CC), new Color(0x84D0D0), new Color(0xFAC05E));



    // Add more color theme constants here

    private ColorThemes(String name, Color backgroundColor1, Color backgroundColor2, Color buttonColor) {
        this.name = name;
        this.backgroundColor1 = backgroundColor1;
        this.backgroundColor2 = backgroundColor2;
        this.buttonColor = buttonColor;
    }

    public String getName() {
        return name;
    }

    public Color getBackgroundColor1() {
        return backgroundColor1;
    }

    public Color getBackgroundColor2() {
        return backgroundColor2;
    }

    public Color getButtonColor() {
        return buttonColor;
    }

    public GradientPaint getGradientBackground(int width, int height) {
        return new GradientPaint(0, 0, backgroundColor1, width, height, backgroundColor2, true);
    }

    public JPanel createGradientPanel() {
        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                GradientPaint gradient = getGradientBackground(width, height);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, width, height);
            }
        };
        gradientPanel.setOpaque(false);
        return gradientPanel;
    }
}
