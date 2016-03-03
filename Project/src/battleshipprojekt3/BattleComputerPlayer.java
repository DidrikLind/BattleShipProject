/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleshipprojekt3;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class describes a computerplayer in the game.
 * @author didrik
 */
public class BattleComputerPlayer extends BattlePlayer 
{
    /**
     * Random object in use frequently in this class.
     */
    private Random rand = new Random();
    
    /**
     *BattleComputerPlayer constructor, calling the constructor in
     *{@link  battleshipprojekt3.BattlePlayer#BattlePlayer()}
     * with the "super()" method.
     */
    public BattleComputerPlayer()
    {
        super();
    }
    
    /**
     *Reseting the specific computer player instance.
     */
    public void resetComputerPlayer()
    {
        clearBoard();
        
        initShip(6,"ship1");
        
        initShip(4,"ship2");
        initShip(4,"ship3");
        
        initShip(3,"ship4");
        initShip(3,"ship5");
        initShip(3,"ship6");
        
        initShip(2,"ship7");
        initShip(2,"ship8");
        initShip(2,"ship9");
        initShip(2,"ship10");

        setShipsDown(0);
        setMisses(0);
        setHits(0);
        
        printPrintBoard();
    }
    
    /**
     * Creates all the ships on the {@link  #tileBoard}.
     * It uses {@link  #giveCombinations(int) giveCombinations}.
     *
     * @param shipLength length of the ship.
     * @param shipName name of the ship.
     */
    @Override
    public void initShip(int shipLength, String shipName)
    {
        ArrayList<Integer> listCombinations = giveCombinations(shipLength);
        int numOfCombinations = listCombinations.size()/(shipLength*2); 
        if(numOfCombinations!=0) //MADE IT BUG. nextInt(0) gives java.lang.IllegalArgumentException
        {
            int randomNum = rand.nextInt(Math.abs(numOfCombinations));
            final int START_INDEX = randomNum*shipLength*2;
            
//adding the ship to the modelBoard:
            int row, col;
            ArrayList<Integer> shipCords = new ArrayList<>();
            for(int startNum = START_INDEX; startNum<START_INDEX+shipLength*2; startNum=startNum+2)
            {//Here we set out the random picked combination on the board (Building the ship).
                row = listCombinations.get(startNum);
                col = listCombinations.get(startNum+1);
                setBoardState(row, col, "part");

                shipCords.add(row);
                shipCords.add(col);
            }
            
            ships.setKeyValueShip(shipName, shipCords);  
        }
    }
    
    /**
     *
     * @param shipLength length of the ship.
     * @return an ArrayList with all possible combinations for a ship
     * with given length.
     */
    public ArrayList<Integer> giveCombinations(int shipLength)
    {
        ArrayList<Integer> list = new ArrayList<>();
        
        // 10-shipLength since we otherwise check the same combination
        for(int row=0; row<ROWS-shipLength+1; row=row+1) 
        {
            for(int col=0; col<COLONS-shipLength+1; col=col+1)
            {//check which horizontal   Combinations that works, and add it to the list!
                if(isLegalHorizontal(row, col, shipLength))
                {
                    for(int i=0; i<shipLength; i=i+1)
                    {
                        list.add(row);
                        list.add(col+i);  
                    }
                }
                
                if(isLegalVertical(row, col, shipLength))
                {//check which verticalCombinations that works, and add it to the list!
                    for(int i=0; i<shipLength; i=i+1)
                    {
                        list.add(row+i);
                        list.add(col);
                    }
                }

            }
        }
        return list;
    }
    
    /**
     * loops up to the shipLength and invokes {@link  #isLegalHorizontalSpot(int,int,int) isLegalHorizontalSpot} method
     * each loop. Returns true if the horizontal direction is legal.
     * @param startRow given row to start on.
     * @param startCol given colon to start on.
     * @param shipLength given length of the ship.
     * @return true if it is legal horizontally.
     */
    private boolean isLegalHorizontal(int startRow, int startCol, int shipLength)
    {
        for(int i=0; i<shipLength; i=i+1)
        {
            if(!isLegalHorizontalSpot(startRow,startCol+i, i)) 
            {// if not legalspot!(we then have shipparts to close)
                return false;
            }
        }
        return true;
    }
    
    /**
     * loops up to the shipLength and invokes  {@link  #isLegalVerticalSpot(int,int,int) isLegalVerticalSpot} method
     * each loop. Returns true if the vertical direction is legal.
     * @param startRow given row to start on.
     * @param startCol given colon to start on.
     * @param shipLength given length of the ship.
     * @return true if it is legal vertically.
     */
    private boolean isLegalVertical(int startRow, int startCol, int shipLength)
    {
        for(int i=0; i<shipLength; i=i+1)
        {
            if(!isLegalVerticalSpot(startRow+i,startCol, i)) 
            {// if not legalspot!(we then have shipparts to close)
                return false;
            }
        }
        return true;
    }

    /**
     * 
     *
     * @param row given row.
     * @param col given colon.
     * @param whichPart if whichPart=0, we will check to the left,
     * otherwise this parameter has not function.
     * @return true if it is a legal spot.
     */
    private boolean isLegalHorizontalSpot(int row, int col, int whichPart)
    {
        
//if we have a part there already!
        if(getBoardTile(row, col).isPart())
        {
            return false;
        }

//if we need to checkLeft and another part from another ship is to hte left.
        if(col!=0 && whichPart==0 && getBoardTile(row,col-1).isPart()) 
        {
            return false;
        }
        
//write this on paper and it is easy to understand.(thats where the idea came from;))
        if((col>=0 && col<9) && row==0 && 
           (getBoardState(row+1,col).equals("part")|| 
            getBoardState(row,col+1).equals("part")))
        {
            return false;
        }
        if((col>=0 && col<9) && row!=0 && (getBoardTile(row+1,col).isPart() ||
                                           getBoardTile(row-1,col).isPart() ||
                                           getBoardTile(row, col+1).isPart()))
        {
            return false;
        }
        if(col==9 && getBoardTile(row+1,col).isPart())
        {
            return false;
        }
        return true; 
    }
    
    /**
     *
     * @param row given row.
     * @param col given col.
     * @param whichPart if whichPart=0, we will check upwards, 
     * otherwise this parameter has not function.
     * @return true if it is a legal spot.
     */
    private boolean isLegalVerticalSpot(int row,int col, int whichPart)
    {
        
 //if we have a part there already!       
        if(getBoardTile(row,col).isPart())
        {
            return false;
        }
        
 // so if we need to checkLeft and another part from another ship is to hte left.
        if(row!=0 && whichPart==0 && getBoardTile(row-1,col).isPart())
        {
            return false;
        }
        
//write this on paper and it is easy to understand.(thats where the idea came from;))
            if((row>=0 && row<9) && col==0 &&
               (getBoardTile(row+1,col).isPart())  || 
                getBoardTile(row,col+1).isPart())
            {
                return false;
            }
            if((row>=0 && row<9) && col!=0 && (getBoardTile(row+1, col).isPart() ||
                                               getBoardTile(row, col+1).isPart() ||
                                               getBoardTile(row, col-1).isPart()))
            {
                return false;
            }
            if(row==9 &&  getBoardTile(row,col+1).isPart())
            {
                return false;
            }
            return true;
    }
}
