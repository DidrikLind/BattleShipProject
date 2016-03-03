/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleshipprojekt3;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *Interface implemented by {@link BattleModel} and used by {@link BattleView1}
 * @author didrik
 */
public interface BattleModelInterface {

    /**
     * num of rows.
     */
    final int ROWS = 10;
            
    /**
     * num of colons
     */
    final int COLONS = 10;

    /**
     * nothing in particular atm, just a reminder :P!
     */
    void viewCanUse();

    /**
     *{@link BattleView1} for example can here in the future use this method to add itself
     * as an listener to {@link BattleModel}
     * @param listen PropertyChangeListener
     */
    void addPropertyChangeListener(PropertyChangeListener listen);

    /**
     **{@link BattleView1} for example can here in the future use this method to remove itself
     * as an listener to {@link BattleModel}
     * @param listen PropertyChangeListener
     */
    void removePropertyChangeListener(PropertyChangeListener listen);    
}
