
/**
 * Write a description of class Die here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
public class Die extends JFrame {
    private int diceOne, diceTwo, diceThree, diceFour;
    private int c1, c2, c1Count = -1;
    private boolean d1, d2, d3, d4;
    private Buttons buttons = new Buttons();
    private JButton rollButton;
    private JLabel diceOneImg, diceTwoImg, diceThreeImg, diceFourImg;
    private JPanel dicePanel, Upanel;
    private JPanel continuePanel;
    private JPanel passPanel;
    private JLabel Ulabel;
    private Font font2 = new Font("OCR A Extended", Font.BOLD, 25);
    
    private boolean diceOneSelected;
    private boolean diceTwoSelected;
    private boolean diceThreeSelected;
    private boolean diceFourSelected;

    public Die() {
        
        d1 = false;
        d2 = false;
        d3 = false;
        d4 = false;
        diceOneSelected = false;
        diceTwoSelected = false;
        diceThreeSelected = false;
        diceFourSelected = false;
        createDiceAndComboPanel();
        diceOneImg.setEnabled(true);
        diceTwoImg.setEnabled(true);
        diceThreeImg.setEnabled(true);
        diceFourImg.setEnabled(true);
    }

    public void createDiceAndComboPanel() {
        createDicePanel();
        createRollButton();
        createDiceImagesAndListeners();
        
        createComboPanel();
    }

    private void createDicePanel() {
        dicePanel = new JPanel();
        dicePanel.setBounds(1200, 350, 250, 235);
        dicePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        dicePanel.setBackground(Color.WHITE);
    }

    private void createDiceImagesAndListeners() {
        createDiceOneImgAndListener();
        createDiceTwoImgAndListener();
        createDiceThreeImgAndListener();
        createDiceFourImgAndListener();
    }

    private void createDiceOneImgAndListener() {
        diceOneImg = ImgService.loadImage("dice1.png");
        diceOneImg.setBounds(1250, 350, 50, 50);
        
        diceOneImg.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e){
                //diceOneSelected = false;
                if(c1Count < 2 & c1Count > -1 & !diceOneSelected){
                    c1 = c1 + diceOne;
                    System.out.println(diceOneSelected);
                    Ulabel.setText("Your combination: " + c1 + " & "  + c2);
                    diceOneImg.setEnabled(false);
                    c1Count = c1Count +1;
                    d1 = true;
                    diceOneSelected = true;
                }
                 
                else if(diceOneSelected){
                    System.out.println(diceOneSelected);
                    c1 = c1 - diceOne;
                    c2 = 0;
                    Ulabel.setText("Your combination: " + c1 + " & "  + c2);
                    diceOneImg.setEnabled(true);
                    c1Count = c1Count - 1;
                    d1 = false;
                    diceOneSelected = false;
                    if(diceTwoSelected){
                        diceThreeImg.setEnabled(true);
                        diceFourImg.setEnabled(true);
                    }
                    
                    if(diceThreeSelected){
                        diceTwoImg.setEnabled(true);
                        diceFourImg.setEnabled(true);
                    }
                    
                    if(diceFourSelected){
                        diceTwoImg.setEnabled(true);
                        diceThreeImg.setEnabled(true);
                    }
                }
                 
                if (c1Count == 2){
                
                    if (d1 & d2){
                    
                        c2 = diceThree + diceFour;
                        
                        Ulabel.setText("Your combination: " + c1 + " & "  + c2);
                    
                    } else if (d1 & d3){
                    
                        c2 = diceTwo + diceFour;
                        
                        Ulabel.setText("Your combination: " + c1 + " & "  + c2);
                    
                    }
                    
                    if (d1 & d4){
                    
                        c2 = diceThree + diceTwo;
                        
                        Ulabel.setText("Your combination: " + c1 + " & "  + c2);
                    
                    }
                    
                    
                    diceOneImg.setEnabled(false);
                    diceTwoImg.setEnabled(false);
                    diceThreeImg.setEnabled(false);
                    diceFourImg.setEnabled(false);
                    /*diceOneSelected = false;
                    diceTwoSelected = false;
                    diceThreeSelected = false;
                    diceFourSelected = false;*/
                    
                }
                
                
                
            }
            
            public void mousePressed(MouseEvent e){}
            public void mouseEntered(MouseEvent e){}
            public void mouseExited(MouseEvent e){}
            public void mouseReleased(MouseEvent e){}
        
        });
        dicePanel.add(diceOneImg);
    }
    
    private void createDiceTwoImgAndListener() {
        diceTwoImg = ImgService.loadImage("dice1.png");
        diceTwoImg.setBounds(1540, 350, 50, 50);
        diceTwoSelected = false;
        diceTwoImg.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e){
                if(c1Count < 2 & c1Count > -1 & !diceTwoSelected){
                    c1 = c1 + diceTwo;
                    Ulabel.setText("Your combination: " + c1 + " & "  + c2);
                    diceTwoImg.setEnabled(false);
                    c1Count = c1Count +1;
                    d2 = true;
                    diceTwoSelected = true;
            }
                else if(diceTwoSelected){
                    
                    c1 = c1 - diceTwo;
                    c2 = 0;
                    Ulabel.setText("Your combination: " + c1 + " & "  + c2);
                    diceTwoImg.setEnabled(true);
                    c1Count = c1Count - 1;
                    d2 = false;
                    diceTwoSelected = false;
                    if(diceOneSelected){
                        diceThreeImg.setEnabled(true);
                        diceFourImg.setEnabled(true);
                    }
                    
                    if(diceThreeSelected){
                        diceOneImg.setEnabled(true);
                        diceFourImg.setEnabled(true);
                    }
                    
                    if(diceFourSelected){
                        diceOneImg.setEnabled(true);
                        diceThreeImg.setEnabled(true);
                    }
                }
                
                if (c1Count == 2){
                    if (d2 & d3){
                    
                        c2 = diceOne + diceFour;
                        
                        Ulabel.setText("Your combination: " + c1 + " & "  + c2);
                    
                    } else if (d2 & d1){
                    
                        c2 = diceThree + diceFour;
                        
                        Ulabel.setText("Your combination: " + c1 + " & "  + c2);
                    
                    }
                    
                    if (d2 & d4){
                    
                        c2 = diceOne + diceThree;
                        
                        Ulabel.setText("Your combination: " + c1 + " & "  + c2);
                    
                    }
                    
                    diceOneImg.setEnabled(false);
                    diceTwoImg.setEnabled(false);
                    diceThreeImg.setEnabled(false);
                    diceFourImg.setEnabled(false);
                    /*diceOneSelected = false;
                    diceTwoSelected = false;
                    diceThreeSelected = false;
                    diceFourSelected = false;*/
                
                }
                
                
            }
            
            public void mousePressed(MouseEvent e){}
            public void mouseEntered(MouseEvent e){}
            public void mouseExited(MouseEvent e){}
            public void mouseReleased(MouseEvent e){}
        });
        dicePanel.add(diceTwoImg);
    }
    
    private void createDiceThreeImgAndListener() {
        diceThreeImg = ImgService.loadImage("dice1.png");
        diceTwoImg.setBounds(1240, 700, 50, 50);
        diceThreeSelected = false;
        diceThreeImg.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e){
                if(c1Count < 2 & c1Count > -1 & !diceThreeSelected){
                c1 = c1 + diceThree;
                Ulabel.setText("Your combination: " + c1 + " & "  + c2);
                diceThreeImg.setEnabled(false);
                c1Count = c1Count +1;
                d3 = true;
                diceThreeSelected = true;
            }
                else if(diceThreeSelected){
                    
                    c1 = c1 - diceThree;
                    c2 = 0;
                    Ulabel.setText("Your combination: " + c1 + " & "  + c2);
                    diceThreeImg.setEnabled(true);
                    c1Count = c1Count - 1;
                    d3 = false;
                    diceThreeSelected = false;
                    if(diceOneSelected){
                        diceTwoImg.setEnabled(true);
                        diceFourImg.setEnabled(true);
                    }
                    
                    if(diceTwoSelected){
                        diceOneImg.setEnabled(true);
                        diceFourImg.setEnabled(true);
                    }
                    
                    if(diceFourSelected){
                        diceTwoImg.setEnabled(true);
                        diceOneImg.setEnabled(true);
                    }
                }
                
            
                if (c1Count == 2){
                    if (d3 & d1){
                    
                        c2 = diceTwo + diceFour;
                        
                        Ulabel.setText("Your combination: " + c1 + " & "  + c2);
                    
                    } else if (d3 & d2){
                    
                        c2 = diceOne + diceFour;
                        
                        Ulabel.setText("Your combination: " + c1 + " & "  + c2);
                    
                    }
                    
                    else if (d3 & d4){
                    
                        c2 = diceOne + diceTwo;
                        
                        Ulabel.setText("Your combination: " + c1 + " & "  + c2);
                    
                    }
                    
                    diceOneImg.setEnabled(false);
                    diceTwoImg.setEnabled(false);
                    diceThreeImg.setEnabled(false);
                    diceFourImg.setEnabled(false);
                    /*diceOneSelected = false;
                    diceTwoSelected = false;
                    diceThreeSelected = false;
                    diceFourSelected = false;*/
                }
                
            
                
            }
            
            public void mousePressed(MouseEvent e){}
            public void mouseEntered(MouseEvent e){}
            public void mouseExited(MouseEvent e){}
            public void mouseReleased(MouseEvent e){}
        
        });
        dicePanel.add(diceThreeImg);
    }
    
    private void createDiceFourImgAndListener() {
        diceFourImg = ImgService.loadImage("dice1.png");
        diceTwoImg.setBounds(1540, 700, 50, 50);
        diceFourSelected = false;
        diceFourImg.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e){
                if(c1Count < 2 & c1Count > -1 & !diceFourSelected){
                c1 = c1 + diceFour;
                
                Ulabel.setText("Your combination: " + c1 + " & "  + c2);
                diceFourImg.setEnabled(false);
                c1Count = c1Count +1;
                d4 = true;
                diceFourSelected = true;
            }
                else if(diceFourSelected){
                    
                    c1 = c1 - diceFour;
                    c2 = 0;
                    Ulabel.setText("Your combination: " + c1 + " & "  + c2);
                    diceFourImg.setEnabled(true);
                    c1Count = c1Count - 1;
                    d4 = false;
                    diceFourSelected = false;
                    if(diceOneSelected){
                        diceThreeImg.setEnabled(true);
                        diceTwoImg.setEnabled(true);
                    }
                    
                    if(diceTwoSelected){
                        diceOneImg.setEnabled(true);
                        diceThreeImg.setEnabled(true);
                    }
                    
                    if(diceThreeSelected){
                        diceTwoImg.setEnabled(true);
                        diceOneImg.setEnabled(true);
                    }
                }
            
                if (c1Count == 2){
                    if (d4 & d1){
                    
                        c2 = diceTwo + diceThree;
                        
                        Ulabel.setText("Your combination: " + c1 + " & "  + c2);
                    
                    } else if (d4 & d2){
                    
                        c2 = diceOne + diceThree;
                        
                        Ulabel.setText("Your combination: " + c1 + " & "  + c2);
                    
                    }
                    
                    if (d4 & d3){
                    
                        c2 = diceOne + diceTwo;
                        
                        Ulabel.setText("Your combination: " + c1 + " & "  + c2);
                    
                    }
                    diceOneImg.setEnabled(false);
                    diceTwoImg.setEnabled(false);
                    diceThreeImg.setEnabled(false);
                    diceFourImg.setEnabled(false);
                    /*diceOneSelected = false;
                    diceTwoSelected = false;
                    diceThreeSelected = false;
                    diceFourSelected = false;*/
                
                }
                
            }
            
            public void mousePressed(MouseEvent e){}
            public void mouseEntered(MouseEvent e){}
            public void mouseExited(MouseEvent e){}
            public void mouseReleased(MouseEvent e){}
        
        });
        dicePanel.add(diceFourImg);
    }

    private void createRollButton() {
        Random rand = new Random();
        rollButton = buttons.getRollButton();
        rollButton.setBounds(550, 700, 200, 50);
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollButton.setEnabled(false);
                c1Count = 0;
    
                // roll for 3 seconds
                long startTime = System.currentTimeMillis();
                Thread rollThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
    
                        long endTime = System.currentTimeMillis();
                        try{
                            while((endTime - startTime)/1000F < 3){
                                // roll dice
                                diceOne = rand.nextInt(1, 7);
                                diceTwo = rand.nextInt(1, 7);
                                diceThree = rand.nextInt(1, 7);
                                diceFour = rand.nextInt(1, 7);
    
                                // update dice images
                                ImgService.updateImage(diceOneImg, "dice" + diceOne + ".png");
                                ImgService.updateImage(diceTwoImg, "dice" + diceTwo + ".png");
                                ImgService.updateImage(diceThreeImg, "dice" + diceThree + ".png");
                                ImgService.updateImage(diceFourImg, "dice" + diceFour + ".png");
    
                                repaint();
                                revalidate();
    
                                // sleep thread
                                Thread.sleep(60);
    
                                endTime = System.currentTimeMillis();
    
                            }
    
                            //rollButton.setEnabled(true);
                        }catch(InterruptedException e){
                            System.out.println("Threading Error: " + e);
                        }
                    }
                    
                    
                });
                rollThread.start();
            } 
        });
    }
    
    private void createComboPanel() {
        Upanel = new JPanel();
        Upanel.setBounds(1000, 300, 650, 80);
        Upanel.setOpaque(false);
    
        Ulabel = new JLabel("Roll the Die ");
        Ulabel.setForeground(Color.white);
        Ulabel.setFont(font2);
        Upanel.add(Ulabel);
    }
    
    public void updateULabel(String s){
        Ulabel.setText(s);
    }
    
    

    public ArrayList<Integer> getFourIntegers() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(c1);
        list.add(c2);
        return list;
    }
    
    public int getDice1(){
        return diceOne;
    }
    
    public int getC1(){
        return c1;
    }
    
    public int getC2(){
        return c2;
    }
    
    
    public int getDice2(){
        return diceTwo;
    }    
    
    public int getDice3(){
        return diceThree;
    }
    
    public int getDice4(){
        return diceFour;
    }
    
    public boolean getD1(){
        return d1;
    }
    
    public boolean getD2(){
        return d2;
    }
    
    public boolean getD3(){
        return d3;
    }
    
    public boolean getD4(){
        return d4;
    }
    
    public int getC1Count(){
        return c1Count;
    }
    
    public JPanel getUPanel() {
        return Upanel;
    }
    
    public JPanel getDicePanel() {
        return dicePanel;
    }

    public JButton getRollButton(){
        return rollButton;
    }
    
    public void halt(){
        dicePanel.setVisible(false);
    }
    
    public void unHalt(){
        dicePanel.setVisible(true);
    }
    
    public void reset(){
        c1Count = -1;
        c1 = 0;
        c2 = 0;
        
        d1 = false;
        d2 = false;
        d3 = false;
        d4 = false;
        diceOneSelected = false;
        diceTwoSelected = false;
        diceThreeSelected = false;
        diceFourSelected = false;
        rollButton.setEnabled(true);
        diceOneImg.setEnabled(true);
        diceTwoImg.setEnabled(true);
        diceThreeImg.setEnabled(true);
        diceFourImg.setEnabled(true);
        ImgService.updateImage(diceOneImg, "dice1.png");
        ImgService.updateImage(diceTwoImg, "dice1.png");
        ImgService.updateImage(diceThreeImg, "dice1.png");
        ImgService.updateImage(diceFourImg, "dice1.png");
        
    }

    public void setRollButtonEnabled(boolean enabled) {
        rollButton.setEnabled(enabled);
    }
}


