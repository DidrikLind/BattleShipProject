/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleshipprojekt3;

/**
 * Class describes a square used by {@link BattleModel}, which has
 * its illustration in the {@link BattleView1} as {@link BattleSquareView}.
 * @author didrik
 */
public class BattleTileSquare 
{
    private int row, col;
    private String state, belongToShip; //part, miss, hit, empty
    
    /**
     *constructor initiate {@link #row}, {@link #col} and {@link #state}.
     * @param row given row.
     * @param col given colon.
     * @param state given state, it could either be: "hit", "miss", "part" or "empty".
     */
    public BattleTileSquare(int row, int col, String state)
    {
        this.row = row;
        this.col = col;
        this.state = state;
    }
    
    /**
     *returns {@link #row}.
     *@return {@link #row}
     */
    public int getRow()
    {
        return row;
    }
    
    /**
     *returns {@link #col}.
     * @return {@link #col}
     */
    public int getCol()
    {
        return col;
    }
    
    /**
     *returns {@link #state}
     * @return {@link #state}
     */
    public String getState()
    {
        return state;
    }
    
    /**
     *sets {@link #state} to "hit", "miss", "part" or "empty".
     * @param state given state, it could either be: "hit", "miss", "part" or "empty".
     */
    public void setState(String state)
    {
        this.state = state;
    }
    
    /**
     *returns boolean, true or false depending on if {@link #state} is equal to
     * "hit".
     * @return boolean.
     */
    public boolean isHit()
    {
        return state.equals("hit");
    }
    
    /**
     *returns boolean, true or false depending on if {@link #state} is equal to
     * "part".
     * @return boolean.
     */
    public boolean isPart()
    {
        return state.equals("part");
    }
    
    /**
     *returns boolean, true or false depending on if {@link #state} is equal to
     * "empty".
     * @return boolean.
     */
    public boolean isEmpty()
    {
        return state.equals("empty");
    }
    
    /**
     *returns boolean, true or false depending on if {@link #state} is equal to
     * "miss".
     * @return boolean.
     */
    public boolean isMiss()
    {
        return state.equals("miss");
    }
}
