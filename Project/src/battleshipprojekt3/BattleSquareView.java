/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleshipprojekt3;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * Class describes a square in the view, the gui, which
 * is an illustration of the class {@link BattleTileSquare}
 * used by {@link BattleModel}.
 * @author didrik
 */
public class BattleSquareView extends JButton   
{
    private  int row, col;
    private final javax.swing.border.Border border;
    
    /**
     *constructor initiate {@link #row}, {@link #col} and sets border 
     * on the button.
     * @param row given row.
     * @param col given colon.
     */
    public BattleSquareView(int row, int col)
    {
        this.row = row;
        this.col = col;
        border = BorderFactory.createLineBorder(new Color(255,215,0), 2);
        setBorder(border);
    }
     
    /**
     *returns {@link #row}.
     * @return {@link #row}
     */
    public int getRow()
    {
        return row;
    }
    
    /**
     * returns {@link #col}.
     * @return {@link #col}
     */
    public int getCol()
    {
        return col;
    }  
}
