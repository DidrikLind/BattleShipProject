/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleshipprojekt3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class describes a player in the game.
 * @author didrik
 */
public class BattlePlayer {
    
    /**
     *The number of rows in the game.
     */
    protected int ROWS=10;

    /**
     *The number of colons in the game.
     */
    protected int COLONS=10;
    
    private String name;
   
    /**
     *printBoard interprets the field {@link #tileBoard} 
     *0=empty, 1=part, 2=miss and 3=hit.
     */
    protected int[][] printBoard;

    /**
     *{@link  battleshipprojekt3.BattleTileSquare  BatteTileSquare}
     */
    protected BattleTileSquare[][] tileBoard;

    /**
     *Reference to the nested class:  {@link  battleshipprojekt3.BattlePlayer.Ships  Ships}
     */
    protected Ships ships;
    
    /**
     *Stores the number of ships down for the BattlePlayer/BattleComputerPLayer object.
     */
    protected int shipsDown;

    /**
     *Stores the number of misses for the BattlePlayer/BattleComputerPLayer object.
     */
    protected int misses;

    /**
     *Stores the number of hits for the BattlePlayer/BattleComputerPLayer object.
     */
    protected int hits;
    
    /**
     *BattlePlayer constructor which initiate necessary fields, namely
     * the {@link #printBoard}, {@link #tileBoard} and
     * {@link  #ships} invokes the method {@link  #initTileBoard()}.
     */
    public BattlePlayer()
    {
        printBoard = new int[ROWS][COLONS];
        tileBoard = new BattleTileSquare[ROWS][COLONS];
        initTileBoard();
        ships = new Ships();
    }
    
    /**
     *Initiate the {@link #tileBoard} objects.
     */
    public void initTileBoard()
    {
        for(int r=0; r<ROWS; r=r+1)
        {
            for(int c=0; c<COLONS; c=c+1)
            {
                tileBoard[r][c] = new BattleTileSquare(r,c,"empty");
            }
        }
    }
    
    /**
     *Placing out the actual ship on the {@link #tileBoard}.
     * For now this method is empty and only the subclass
     * {@link  battleshipprojekt3.BattleComputerPlayer} overriden method is filled
     * up with logic. (In the future if one wants to upgrade this project
     * in the sense that a real person could play the game, this method
     * should be upgraded.
     * 
     * @param shipLength length of the ship.
     * @param shipName name of the ship.
     */
    public void initShip(int shipLength, String shipName)
    {
        //This is overriden in subclass: BattleComputerPlayer.java
        // I could give this meaning, if I would let a player meet the
        //computer etc.
    }
    
    /**
     *Clearing the {@link #tileBoard} objects.
     */
    public void clearBoard()
    {
        for(int row=0;row<10;row=row+1)
        {
            for(int col=0;col<10;col=col+1)
            {
                setBoardState(row,col,"empty"); //0 for empty,
                                      //1 for filled,-1 for filled and hitted or something ;D  
            }
        }
        
    }
    
    /**
     * sets boardstate "hit", "miss", "part" or "empty" on given row and colon
     * in {#tileBoard}.
     * @param row given row.
     * @param col given colon.
     * @param newState given state, it could either be: "hit", "miss", "part" or "empty".
     */
    public void setBoardState(int row, int col, String newState)
    {//newState: hit, part, miss or empty in string form.
        tileBoard[row][col].setState(newState);
        if(newState.equals("hit"))
            printBoard[row][col] = 3;
        else if(newState.equals("part"))
            printBoard[row][col] = 1;
        else if(newState.equals("miss"))
            printBoard[row][col] = 2;
        else
            printBoard[row][col] = 0;
    }
    
    /**
     *
     * @param row given row.
     * @param col given colon.
     * @return a state of chosen {@link #tileBoard} objects. The states are be:
     * "hit", "miss", "part" or "empty".
     */
    public String getBoardState(int row, int col)
    {
        return tileBoard[row][col].getState();
    }
    
    /**
     *
     * @param row given row
     * @param col given col
     * @return specific {@link  BattleTileSquare} object.
     */
    public BattleTileSquare getBoardTile(int row, int col)
    {
        return tileBoard[row][col];
    }
    
    

    /**
     *Uses the method "System.out.println()" to write out the 
     * {@link  #printBoard} array.
     */
    public void printPrintBoard()
    {
        //PrintBoard is a way for us to interpret the data in the model
       //as an easy integer array! 
        for(int r=0;r<10;r=r+1)
        {
            for(int c=0;c<10;c=c+1)
            {
                if(c%10==0)
                {
                    System.out.println("");
                }
                System.out.print(printBoard[r][c]);                         
            }
        }
        System.out.println("\n \n playerBoard updated,"
        + " 1=BLACK is PART, 2=RED is MISS and 3=GREEN is HIT");
    }
    
    /**
     *
     * @return {@link #printBoard}
     */
    public int[][] getPrintBoard()
    {
        return printBoard; //for example to use for the minimap!
    }
    
    /**
     *
     * @return {@link #tileBoard}
     */
    public BattleTileSquare[][] getTileBoard()
    {
        return tileBoard;     
    }

    /**
     *changes the field {@link #misses}
     * @param misses number of misses.
     */
    public void setMisses(int misses)
    {
        this.misses = misses;
    }
    
    /**
     *
     * @return {@link #misses}
     */
    public int getMisses()
    {
        return misses;
    }
    
    /**
     *changes the field {@link #hits}.
     * @param hits number of hits.
     */
    public void setHits(int hits)
    {
        this.hits = hits;
    }
    
    /**
     * @return {@link #hits}.
     */
    public int getHits()
    {
        return hits;
    }
    
    /**
     *changes the field {@link #shipsDown}.
     * @param shipsDown number of ships down.
     */
    public void setShipsDown(int shipsDown)
    {
        this.shipsDown = shipsDown;
    }
    
    /**
     *changes the field {@link #shipsDown}.
     * @return {@link #shipsDown}
     */
    public int getShipsDown()
    {
        return shipsDown;
    }
    
    /**
     *checks how many of the ships are down. 
     */
    public void checkShips()
    {
        int checkedShips=0, countHits, row, col;
        shipsDown = 0;
        while(checkedShips<ships.getNumOfShips())
        {
            ArrayList shipCords = ships.getShipCoords("ship"+(checkedShips+1));
            countHits = 0;
            for(int i=0; i<shipCords.size(); i=i+2)
            {
                row = (int) shipCords.get(i);
                col = (int) shipCords.get(i+1);
                if(getBoardTile(row, col).isHit())
                {
                    countHits = countHits + 1;
                }
            }
            
            int shipLength = shipCords.size()/2;
            if(countHits == shipLength)
            {
                shipsDown = shipsDown + 1;
            }
            checkedShips = checkedShips + 1;
        }   
    }
    
    protected class Ships
    {
        //"A map that every player has for its own ships"
        /**
         * "A map that every player has for its own ships, YARR!!"
         */
        protected Map<String, ArrayList<Integer>> shipMap;
        
        /**
         *Ships constructor which initiate the field *shipMap*
         */
        public Ships()
        {
            shipMap = new HashMap<>();
        }
        
        /**
         * corresponds a String key in the hashmap to a value
         * 
         * @param shipName
         * @param shipCoords
         */
        public void setKeyValueShip(String shipName, ArrayList<Integer> shipCoords)
        {//ex: setKeyValueShip("ship1", [row col row col row col]);
            shipMap.put(shipName,shipCoords);
            
        }
        
        /**
         *gives the coordinates of the ship
         *for the given shipname.
         * @param shipName
         * @return 
         */
        public ArrayList<Integer> getShipCoords(String shipName)
        {
            ArrayList<Integer> shipCoords = shipMap.get(shipName);
            return shipCoords;
        }
        
        /**
         * returns the number of ships that exists in this instance.
         * 
         * @return 
         */
        public int getNumOfShips()
        {
            return shipMap.size();
        }
    }  
}