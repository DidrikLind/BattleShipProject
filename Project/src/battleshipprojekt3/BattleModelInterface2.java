/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleshipprojekt3;

/**
 *Interface implemented by {@link BattleModel} and used by {@link BattleController}
 * @author didrik
 */
public interface BattleModelInterface2 
{
    /**
     * a method to reach the {@link BattleComputerPlayer} public methods.
     * @return {@link BattleComputerPlayer}
     */
    BattleComputerPlayer getComputerPlayer();
    
    /**
     * num of rows.
     */
    final int ROWS = 10;

    /**
     * num of colons.
     */
    final int COLONS = 10;
}
