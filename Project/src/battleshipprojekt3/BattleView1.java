/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleshipprojekt3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.Random;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 * Class describes the main view in the MVC theory.
 * @author didrik
 */
public class BattleView1 extends JPanel implements PropertyChangeListener{
    
    private final int ONE_SECOND_INTERVAL = 1000;
    private final int ROWS;
    private final int COLONS;
    
    private  BattleSquareView[][] viewBoard;
    private JPanel cPanel, sPanel, ePanel, wPanel, minimapPanel, timePanel, soundPanel, nPanel;
    private JButton resetB, colorB;
    private JLabel clockLabel, timerLabel;
    private JLabel[][] minimapLabels;
    private Timer timer1;
    private int timePlayed;
    private JTextField hitText, missText, sunkText, titleText;
    private final Random rand = new Random();
    private float rColor, bColor, gColor;
    private javax.swing.border.Border border;

    private BattleModelInterface modelInterface;
    
    /**
     *constructor can take the {@link BattleModelInterface} in the
     * future to add itself as an listener to the model. For now it
     * only initiate the fields {@link #ROWS} and {@link COLONS}, getting the
     * values from {@link battleshipprojekt3.BattleModelInterface} and
     * also it invokes the method {@link #initGUI()}.
     * @param modelInterface object (reference) to {@link  battleshipprojekt3.BattleModel} which
     * can only reach the methods and fields declared in {@link battleshipprojekt3.BattleModelInterface}.
     */
    public BattleView1(BattleModelInterface modelInterface)
    {
        setTheModelInterface(modelInterface);
        ROWS = modelInterface.ROWS;
        COLONS = modelInterface.COLONS;
        initGUI();
    }
    
    /**
     *
     * @param modelInterface object (reference) to 
     * {@link  battleshipprojekt3.BattleModel}
     */
    public void setTheModelInterface(BattleModelInterface modelInterface)
    {
        this.modelInterface = modelInterface;
        modelInterface.addPropertyChangeListener(this);
    }
    
    /**
     *
     * @param pce PropertyChangeEvent
     */
    @Override
    public void propertyChange(PropertyChangeEvent pce) 
    {
        if(pce.getPropertyName().equals("reset"))
        {
            int[][] playerBoardCopy = (int[][]) pce.getNewValue();
            updateMiniMap(playerBoardCopy);
            System.out.println("reset bro");
            clearBattleBoard();
            updateMiniMap(playerBoardCopy);
        } 
    }
    
    /**
     * initiate the GUI swing component.
     */
    private void initGUI()
    {
        setLayout(new BorderLayout());
        
        rColor = rand.nextFloat();
        bColor = rand.nextFloat();
        gColor = rand.nextFloat();
        viewBoard = 
        new BattleSquareView[ROWS][COLONS];
       
        makeBattleBoard();
        makeSouthPanel();
        makeEastPanel();
        makeWestPanel();
        makeNorthPanel();
        timer1 = new Timer(ONE_SECOND_INTERVAL, new TimerClockClass());
        timer1.start();
    }
    
    /**
     * 
     */
    private void makeBattleBoard()
    {
        cPanel = new JPanel();
        cPanel.setBackground(new Color(rColor, bColor, gColor));
        cPanel.setPreferredSize(new Dimension(400, 400));
        //cPanel.setMaximumSize(new Dimension(400,400));
        cPanel.setLayout(new GridLayout(ROWS, COLONS, 1, 1));
        for(int r=0;r<ROWS;r=r+1)
        {
            for(int c=0;c<COLONS;c=c+1)
            {
                viewBoard[r][c] = new BattleSquareView(r,c);
                viewBoard[r][c].setIcon(new ImageIcon("C:\\Users\\didrik\\"
                        + "Google Drive\\JavaProjekt\\NetBeansProjects\\"
                        + "BattleShipProjekt2\\src\\battleshipprojekt2\\"
                        + "bomb.png"));
                viewBoard[r][c].setBackground(new Color(rColor,bColor,gColor));
                
                cPanel.add(viewBoard[r][c]);
            }
        }
        add(cPanel, BorderLayout.CENTER);
    }
    
    /**
     *
     */
    public void clearBattleBoard()
    {
        for(int r=0;r<ROWS;r=r+1)
        {
            for(int c=0;c<COLONS;c=c+1)
            {
                viewBoard[r][c].setEnabled(true);
                viewBoard[r][c].setIcon(new ImageIcon("C:\\Users\\didrik\\"
                        + "Google Drive\\JavaProjekt\\NetBeansProjects\\"
                        + "BattleShipProjekt2\\src\\battleshipprojekt2\\"
                        + "bomb.png"));
                viewBoard[r][c].setBackground(new Color(rColor,bColor,gColor));
            }
        }
        timePlayed = 0;
    }
    
    /**
     * 
     */
    private void makeSouthPanel()
    {
        sPanel = new JPanel();
        sPanel.setBackground(new Color(rColor, bColor, gColor));
        resetB = new JButton("*<<*-- R £ $ £ T - G @ M £ --*>>*");
        resetB.setBackground(new Color(255,215,0));
        sPanel.add(resetB);
        
        add(sPanel, BorderLayout.SOUTH);
    }
    
    /**
     * 
     */
    private void makeEastPanel()
    {
        ePanel = new JPanel();
        ePanel.setBackground(new Color(rColor, bColor, gColor));
        ePanel.setPreferredSize(new Dimension(170,400));
        
        minimapPanel = new JPanel();
        minimapPanel.setBackground(Color.BLACK);
        minimapPanel.setPreferredSize(new Dimension(170,170));
        minimapPanel.setLayout(new GridLayout(ROWS, COLONS, 1, 1));
        minimapLabels = new JLabel[ROWS][COLONS];
        for(int r=0;r<ROWS;r=r+1)
        {
            for(int c=0;c<COLONS;c=c+1)
            {
                minimapLabels[r][c] = new JLabel();
                minimapPanel.add(minimapLabels[r][c]);
                minimapLabels[r][c].setOpaque(true);
                minimapLabels[r][c].setBackground(new Color(rColor, bColor, gColor));  
            }
        }
        ePanel.add(minimapPanel);
        
        timePanel = new JPanel();
        timePanel.setPreferredSize(new Dimension(170,70));
        timePanel.setBackground(new Color(rColor,bColor,gColor));
        clockLabel = new JLabel("CLOCK");
        clockLabel.setForeground(new Color(255,215,0));
        timePanel.add(clockLabel);
        timerLabel = new JLabel("Timer");
        timerLabel.setForeground(new Color(255,215,0));
        timePanel.add(timerLabel);        
        ePanel.add(timePanel);
        
        soundPanel = new JPanel();
        soundPanel.setBackground(new Color(255,215,0));
        colorB = new JButton(new ImageIcon("C:\\Users\\didrik\\Google Drive\\"
                + "JavaProjekt\\NetBeansProjects\\BattleShipProjekt3\\src\\"
                + "battleshipprojekt3\\color.png"));
        colorB.setBackground(new Color(255,215,0));
        soundPanel.add(colorB);
        ePanel.add(soundPanel);
        
        add(ePanel, BorderLayout.EAST);
    }
    
    /**
     *
     */
    public void makeWestPanel()
    {
        wPanel = new JPanel();
        wPanel.setBackground(new Color(rColor, bColor, gColor));
        wPanel.setPreferredSize(new Dimension(170,100));
        border = BorderFactory.createLineBorder(new Color(255,215,0), 2);
        hitText = new TextFieldClass("Num of hits: ");
        hitText.setBorder(border);
        wPanel.add(hitText);
        missText = new TextFieldClass("Num of misses: ");
        missText.setBorder(border);
        wPanel.add(missText);
        sunkText = new TextFieldClass("Num of sunken ships: ");
        sunkText.setBorder(border);
        wPanel.add(sunkText);

        add(wPanel, BorderLayout.WEST);
    }
    
    /**
     *
     */
    public void makeNorthPanel()
    {
        nPanel = new JPanel();
        nPanel.setBackground(new Color(rColor, bColor, gColor));
        border = BorderFactory.createLineBorder(new Color(255,215,0), 2);
        titleText = new TextFieldClass("* * * * B a t t l e  @   S h i p * * * *");
        titleText.setBorder(border);
        titleText.setPreferredSize(new Dimension(400,30));
        titleText.setHorizontalAlignment(JTextField.CENTER);
        titleText.setFont(new Font("Courier", Font.BOLD,20));
        nPanel.add(titleText);
        
        add(nPanel, BorderLayout.NORTH);    
    }
    
    /**
     *
     * @param playerBoardCopy
     */
    public void updateMiniMap(int[][] playerBoardCopy)
    {
        for(int r=0;r<10;r=r+1)
        {
            for(int c=0;c<10;c=c+1)
            {
                if(playerBoardCopy[r][c]==1)
                {
                    minimapLabels[r][c].setBackground(Color.BLACK);
                }
                else
                {
                    minimapLabels[r][c].setBackground(Color.WHITE);
                }
            }
        }  
    }
    
    /**
     *
     * @param row given row.
     * @param col given colon.
     * @return specific element in {@link #minimapLabels}.
     */
    public JLabel getMiniMapLabel(int row, int col)
    {
        return minimapLabels[row][col];
    }
    
    /**
     *
     * @param row given row.
     * @param col given colon.
     * @return specific element in {@link #viewBoard}.
     */
    public BattleSquareView getBattleSquareView(int row, int col)
    {
        return viewBoard[row][col];
    }
    
    /**
     *
     * @param hits
     */
    public void updateHitText(int hits)
    {
        hitText.setText("Num of hits: " + hits);
    }
    
    /**
     *
     * @param misses
     */
    public void updateMissText(int misses)
    {
        missText.setText("Num of misses: "  + misses); 
    }
    
    /**
     *
     * @param numOfShipsDown
     */
    public void updateShipsDownText(int numOfShipsDown)
    {
        sunkText.setText("Num of sunken ships:" + numOfShipsDown);   
    }
    
    /**
     *
     */
    public void randomizeColorTheme()
    {
        rColor= rand.nextFloat();
        bColor = rand.nextFloat();
        gColor = rand.nextFloat();
        Color newColor = new Color(rColor ,bColor, gColor);
       //cPanel.setBackground(newColor);
        nPanel.setBackground(newColor);
        ePanel.setBackground(newColor);
        wPanel.setBackground(newColor);
        timePanel.setBackground(newColor);
        missText.setBackground(newColor);
        hitText.setBackground(newColor);
        sunkText.setBackground(newColor);
        titleText.setBackground(newColor); 
        for(int r=0; r<ROWS; r=r+1)
        {
            for(int c=0; c<COLONS; c=c+1)
            {
                viewBoard[r][c].setBackground(newColor);
            }
        }
        
       // nPanel.setBackground(newColor);
        sPanel.setBackground(newColor); 
    }
        
    /**
     *this method is used in {@link battleshipprojekt3.BattleController}
     *together with  the nested class 
     *{@link battleshipprojekt3.BattleController.BoardListener}.
     * @param listenTo ActionListener
     */
    public void addBoardListener(ActionListener listenTo)
    {
        for(int r=0;r<ROWS;r=r+1)
        {
            for(int c=0;c<COLONS;c=c+1)
            {
                viewBoard[r][c].addActionListener(listenTo);
            }
        }
    }
    
    /**
     *this method is used in {@link battleshipprojekt3.BattleController}
     *together with  the nested class 
     *{@link battleshipprojekt3.BattleController.ResetButtonListener}.
     * @param listenTo ActionListener
     */
    public void addResetButtonListener(ActionListener listenTo)
    {
        resetB.addActionListener(listenTo);
    }
    
    /**
     *this method is used in {@link battleshipprojekt3.BattleController}
     *together with  the nested class 
     *{@link battleshipprojekt3.BattleController.ColorButtonListener}.
     * @param listenTo ActionListener
     */
    public void addColorButtonListener(ActionListener listenTo)
    {
        colorB.addActionListener(listenTo);
    }
    
    /**
     * handles the time shown on the GUI.
     */
    class TimerClockClass implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent aevt) 
        {
            
            clockLabel.setText(new Date().toString());
            timerLabel.setText("Time played in seconds: " + timePlayed);
            timePlayed = timePlayed + 1;  
        }
    }
    
    /**
     *used for {@link #titleText}, {@link #hitText},
     * {@link #missText} and {@link #sunkText}
     */
    class TextFieldClass extends JTextField
    {
        private String text;
        
        public TextFieldClass(String text)
        {
            this.text = text;
            setText(text);
            setEnabled(false);
            setStandardDesign(rColor, bColor, gColor);
        }
        
        /**
         * sets the standard design for a object of this class.
         * @param r
         * @param b
         * @param g 
         */
        public void setStandardDesign(float r, float b, float g)
        {
            setPreferredSize(new Dimension(160,30));
            setBackground(new Color(r,b,g));
            setDisabledTextColor(new Color(255,215,0));
            setForeground(new Color(r,b,g));
        }
    }  
}
