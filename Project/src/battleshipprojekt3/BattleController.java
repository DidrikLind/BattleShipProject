/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleshipprojekt3;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

/**
* Class describes the main controller in the MVC theory.
 * @author didrik
 */
public class BattleController 
{   /**
     * in the constructor {@link BattleController(BattleView1,BattleModelInterface2) BattleController}
     * ROWS will be initiated to the value of the the constant 
     * {@link battleshipprojekt3.BattleModelInterface2#ROWS ROWS} in
     * {@link battleshipprojekt3.BattleModelInterface2}
     */
    private final int ROWS;
    
    /**
     * in the constructor {@link BattleController(BattleView1,BattleModelInterface2) BattleController}
     * COLONS will be initiated to the value of the the constant 
     * {@link battleshipprojekt3.BattleModelInterface2#COLONS COLONS} in 
     * {@link battleshipprojekt3.BattleModelInterface2}
     */
    private final int COLONS;
    
    private BattleView1 theView;
    private BattleModelInterface2 modelInterface2;
    
    /**
     *the constructor adds listeners to the view components with help of the 
     *{@link #theView} field by using the classes: 
     * {@link  battleshipprojekt3.BattleController.BoardListener  BoardListener},
     * {@link  battleshipprojekt3.BattleController.ResetButtonListener  ResetButtonListener}
     * and {@link  battleshipprojekt3.BattleController.ColorButtonListener  ColorButtonListener}.
     *
     * Also with help of the {@link #modelInterface2} field
     * it initiates {@link #ROWS} and {@link #COLONS}.
     * @param theView object (reference) to {@link  battleshipprojekt3.BattleView1}
     * @param modelInterface2 object (reference) to {@link  battleshipprojekt3.BattleModel} which
     * can only reach the methods and fields declared in {@link battleshipprojekt3.BattleModelInterface2}.
     */
    public BattleController(BattleView1 theView, BattleModelInterface2 modelInterface2)
    {
        //view.
        setTheView(theView);
        //adding listener classes to the view components! :)
        theView.addBoardListener(new BoardListener());
        theView.addResetButtonListener(new ResetButtonListener());
        theView.addColorButtonListener(new ColorButtonListener());
        
        //model.
        setModelInterface2(modelInterface2);
        ROWS = modelInterface2.ROWS;
        COLONS = modelInterface2.COLONS;
    }
    
    /**
     *
     * @param theView instance of {@link BattleView1}
     */
    public void setTheView(BattleView1 theView)
    {
        this.theView = theView;
    }
    
    /**
     *
     * @param modelInterface2 instance of {@link BattleModelInterface2}
     */
    public void setModelInterface2(BattleModelInterface2 modelInterface2)
    {
        this.modelInterface2 =  modelInterface2;
    }

    /**
     * 
     *When user clicks on {@link BattleView1#viewBoard} the
     * {@link BoardListener#actionPerformed(java.awt.event.ActionEvent) }
     * method will use {@link #modelInterface2} to change and update the model
     * and use the updated information from the model to update the 
     * view with {@link #theView}.
     */
    class BoardListener implements ActionListener
    {
        /**
         * 
         * @param aevt ActionEvent from {@link BattleView1#viewBoard viewBoard}.
         */
        @Override
        public void actionPerformed(ActionEvent aevt) 
        {
            BattleSquareView temp = (BattleSquareView) aevt.getSource();
            System.out.println("Du klicka på :" + temp.getRow()+","+temp.getCol() );
            
            BattleComputerPlayer comp = modelInterface2.getComputerPlayer();
            
            int row = temp.getRow();
            int col = temp.getCol();
            int hitOrMiss = 0;
            if(comp.getBoardTile(row,col).isPart())
            {
                comp.setBoardState(row,col,"hit");  
                comp.setHits(comp.getHits()+1);
                comp.printPrintBoard();
                temp.setDisabledIcon(new ImageIcon("C:\\Users\\didrik\\Google Drive"
                        + "\\JavaProjekt\\NetBeansProjects\\BattleShipProjekt3\\src\\"
                        + "battleshipprojekt3\\bulleye.png"));
                theView.getMiniMapLabel(row,col).setBackground(Color.GREEN);
                theView.updateHitText(comp.getHits());
                temp.setBackground(Color.GREEN);
            }
            else
            {
                comp.setBoardState(row,col,"miss"); 
                comp.setMisses(comp.getMisses()+1);
                comp.printPrintBoard();
                temp.setDisabledIcon( new ImageIcon("C:\\Users\\didrik\\Google Drive"
                        + "\\JavaProjekt\\NetBeansProjects\\BattleShipProjekt2\\src\\"
                        + "battleshipprojekt2\\miss.png"));
                theView.getMiniMapLabel(row,col).setBackground(Color.RED);
                theView.updateMissText(comp.getMisses());
                temp.setBackground(Color.RED);
            }
            
            comp.checkShips();
            System.out.println("Ships down: " +comp.getShipsDown());
            theView.updateShipsDownText(comp.getShipsDown());
            temp.setEnabled(false);
        }
            
    }
    
    /**
     *when user clicks on the {@link BattleView1#resetB} the
     * {@link ResetButtonListener#actionPerformed(java.awt.event.ActionEvent) }
     * method will use {@link #modelInterface2} to change and update the model
     * and use the updated information from the model to update the 
     * view with {@link #theView}.
     * 
     */
    class ResetButtonListener implements ActionListener
    {
        /**
         * @param aevt ActionEvent from {@link BattleView1#resetB resetB}.
         */
        @Override
        public void actionPerformed(ActionEvent aevt) 
        {
            System.out.println("Du klicka på reset:!!!");
            BattleComputerPlayer comp = modelInterface2.getComputerPlayer();
            
            comp.resetComputerPlayer();
            theView.clearBattleBoard();
            theView.updateMiniMap(comp.getPrintBoard()); 
            theView.updateHitText(comp.getMisses());
            theView.updateMissText(comp.getMisses());
            theView.updateShipsDownText(comp.getShipsDown());
            
        }
        
    }
    
    /**
     * when user clicks on {@link BattleView1#colorB} the
     * {@link ResetButtonListener#actionPerformed(java.awt.event.ActionEvent) }
     * method will use {@link #theView} to update the view.
     */
    class ColorButtonListener implements ActionListener
    {
        /**
         * 
         * @param aevt ActionEvent from {@link BattleView1#colorB colorB}.
         */
        @Override
        public void actionPerformed(ActionEvent aevt) 
        {
            BattleComputerPlayer comp = modelInterface2.getComputerPlayer();
            theView.randomizeColorTheme();
            theView.updateMiniMap(comp.getPrintBoard());
            
            for(int r=0; r<ROWS; r=r+1)
            {
                for(int c=0; c<COLONS; c=c+1)
                {
                    if(comp.getBoardTile(r, c).isHit())
                    {
                        theView.getMiniMapLabel(r,c).setBackground(Color.GREEN);
                        theView.getBattleSquareView(r,c).setBackground(Color.GREEN);
                    }
                    else if(comp.getBoardTile(r,c).isMiss())
                    {
                        theView.getMiniMapLabel(r,c).setBackground(Color.RED);
                        theView.getBattleSquareView(r,c).setBackground(Color.RED);
                    }
                }
            }
            
        }
        
    } 
}
