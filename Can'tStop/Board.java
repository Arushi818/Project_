import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {
    private ArrayList<JPanel> columns; 
    private ArrayList<ArrayList<JPanel>> innerColumns;
    private ArrayList<ArrayList<ArrayList<JPanel>>> playerColumns;
    private int x, y;
    private int height, width;
    private Font font = new Font("Cascadia Mono SemiBold", Font.BOLD, 25);

    public Board() {
        setPreferredSize(new Dimension(600, 580));
        setLayout(null);
        setBackground(Color.WHITE);
        setOpaque(false);

        createColumns();
        innerColumns();
        createPlayerColumns();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int marginX = 80;
        int marginY = 10;
        int width = getWidth() - 2 * marginX;
        int height = getHeight() - 2 * marginY;
        int quarterWidth = width / 4;
        int quarterHeight = height / 4;
    
        int[] xPoints = {
                marginX + quarterWidth,
                getWidth() - marginX - quarterWidth,
                getWidth() - marginX,
                getWidth() - marginX,
                getWidth() - marginX - quarterWidth,
                marginX + quarterWidth,
                marginX,
                marginX
        };
        int[] yPoints = {
                marginY,
                marginY,
                marginY + quarterHeight,
                getHeight() - marginY - quarterHeight,
                getHeight() - marginY,
                getHeight() - marginY,
                getHeight() - marginY - quarterHeight,
                marginY + quarterHeight
        };
    
        // Filling octagon with white background
        g.setColor(Color.WHITE);
        g.fillPolygon(xPoints, yPoints, 8);
    
        // Creating Graphics2D object
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setStroke(new BasicStroke(5));
    
        // Drawing octagon with the Graphics2D object
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(xPoints, yPoints, 8);
    }
    


        
    

    public void createColumns(){
        x = 90;
        //y = 35;  
        y = 240;    
        width = 50;
        height = 111;

        columns = new ArrayList<>();
        
        for (int i = 2; i <= 7; i++) {
            JPanel column = new JPanel();
            column.setPreferredSize(new Dimension(width, height));
            column.setBackground(Color.BLACK);
            column.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            column.setBounds(x, y, width, height);           
            add(column);
            columns.add(column);

            JLabel label = new JLabel(Integer.toString(i));
            label.setFont(font);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setBounds(x, y - 40, width, 20);
            label.setForeground(Color.BLACK);
            add(label);

            
            x += width + 5;
            y -=37;
            height +=74;
        } 

        
        y +=74;
        height -=148;        

        for (int i = 8; i <= 12; i++) {
            JPanel column = new JPanel();
            column.setPreferredSize(new Dimension(width, height));
            column.setBackground(Color.BLACK);
            column.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            column.setBounds(x, y, width, height);           
            add(column);
            columns.add(column);

            JLabel label = new JLabel(Integer.toString(i));
            label.setFont(font);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setBounds(x, y - 40, width, 20);
            label.setForeground(Color.BLACK);
            add(label);

            x += width + 5;
            y +=37;
            height -=74;
        } 
        



    }

     

    public void innerColumns() {
        innerColumns = new ArrayList<>();
        int panel = 3;

        for (int i = 0; i < 6; i++) {
            JPanel column = columns.get(i);
            int columnWidth = column.getWidth();
            int columnHeight = column.getHeight();
            int innerPanelWidth = columnWidth - 10;
            int innerPanelHeight = (columnHeight - (panel + 1) * 5) / panel;
            int y = 6;
            ArrayList<JPanel> columnPanels = new ArrayList();
            for (int j = 0; j < panel; j++) {
                JPanel innerPanel = new JPanel();
                innerPanel.setPreferredSize(new Dimension(innerPanelWidth, innerPanelHeight));
                innerPanel.setBackground(Color.WHITE);
                innerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                int x = (columnWidth - innerPanelWidth) / 2;

                innerPanel.setBounds(x, y, innerPanelWidth, innerPanelHeight);
                column.add(innerPanel);
                columnPanels.add(innerPanel);               
                y += innerPanelHeight + 6;

            }
            innerColumns.add(columnPanels);
            panel += 2;
        }
        panel = 11;
        for (int i = 6; i < 11; i++) {
            JPanel column = columns.get(i);
            int columnWidth = column.getWidth();
            int columnHeight = column.getHeight();
            int innerPanelWidth = columnWidth - 10;
            int innerPanelHeight = (columnHeight - (panel + 1) * 5) / panel;
            ArrayList<JPanel> columnPanels = new ArrayList();
            int y = 6;
            for (int j = 0; j < panel; j++) {
                JPanel innerPanel = new JPanel();
                innerPanel.setPreferredSize(new Dimension(innerPanelWidth, innerPanelHeight));
                innerPanel.setBackground(Color.WHITE);
                innerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                int x = (columnWidth - innerPanelWidth) / 2;

                innerPanel.setBounds(x, y, innerPanelWidth, innerPanelHeight);
                column.add(innerPanel);
                columnPanels.add(innerPanel); 
                y += innerPanelHeight + 6;

            }
            panel -= 2;
            innerColumns.add(columnPanels);
        }
    }

    public void createPlayerColumns() {
        playerColumns = new ArrayList<>();
        for (int i = 0; i < innerColumns.size(); i++) {
            ArrayList<ArrayList<JPanel>> columnPlayerPanels = new ArrayList<>();
            for (int j = 0; j < innerColumns.get(i).size(); j++) {
                JPanel innerPanel = innerColumns.get(i).get(j);
                innerPanel.setLayout(null);
    
                int playerPanelHeight = innerPanel.getHeight();
                int playerPanelWidth = innerPanel.getWidth()/4;
    
                ArrayList<JPanel> playerPanels = new ArrayList<>();
                for (int k = 0; k < 4; k++) {
                    JPanel playerPanel = new JPanel();
                    playerPanel.setBackground(Color.WHITE);
                    playerPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
    
                    int x = k * playerPanelWidth;
                    int y = 0;
                    int width = playerPanelWidth;
                    int height = playerPanelHeight;
                    playerPanel.setBounds(x, y, width, height);
    
                    innerPanel.add(playerPanel);
                    playerPanels.add(playerPanel);
                }
                columnPlayerPanels.add(playerPanels);
            }
            playerColumns.add(columnPlayerPanels);
        }
    }
    

    //Change playerColumn color
    public void setPlayerColumnColor(int columnIndex, int panelIndex, int playerIndex, Color color) {
        if (columnIndex >= 0 && columnIndex < playerColumns.size()) {
            ArrayList<ArrayList<JPanel>> columnPlayerPanels = playerColumns.get(columnIndex);
            if (panelIndex >= 0 && panelIndex < columnPlayerPanels.size()) {
                int reversedPanelIndex = columnPlayerPanels.size() - 1 - panelIndex; // Calculate the reversed panel index
                ArrayList<JPanel> playerPanels = columnPlayerPanels.get(reversedPanelIndex);
                if (playerIndex >= 0 && playerIndex < playerPanels.size()) {
                    JPanel playerPanel = playerPanels.get(playerIndex);
                    playerPanel.setBackground(color);
                }
            }
        }
    }
    

    //Change playerColumn color of the whole column
    public void setColumnPlayerPanelsColor(int columnIndex, Color color) {
        if (columnIndex >= 0 && columnIndex < playerColumns.size()) {
            ArrayList<ArrayList<JPanel>> columnPlayerPanels = playerColumns.get(columnIndex);
            for (ArrayList<JPanel> playerPanels : columnPlayerPanels) {
                for (JPanel playerPanel : playerPanels) {
                    playerPanel.setBackground(color);
                }
            }
        }
    }
    //Change playerColumn color in a given range
    public void setPlayerPanelsColorInRange(int columnIndex, int playerIndex, int innerPanelStart, int innerPanelEnd, Color color) {
        if (columnIndex >= 0 && columnIndex < playerColumns.size()) {
            ArrayList<ArrayList<JPanel>> columnPlayerPanels = playerColumns.get(columnIndex);
            for (int i = innerPanelStart; i <= innerPanelEnd; i++) {
                if (i >= 0 && i < columnPlayerPanels.size()) {
                    ArrayList<JPanel> playerPanels = columnPlayerPanels.get(i);
                    if (playerIndex >= 0 && playerIndex < playerPanels.size()) {
                        JPanel playerPanel = playerPanels.get(playerIndex);
                        playerPanel.setBackground(color);
                    }
                }
            }
        }
    }

}