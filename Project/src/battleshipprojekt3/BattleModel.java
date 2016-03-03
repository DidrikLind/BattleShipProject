/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleshipprojekt3;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Class describes the model in the MVC theory.
 * @author didrik
 */
public class BattleModel implements BattleModelInterface, BattleModelInterface2{
    
    private BattleComputerPlayer computer;
    //private BattlePlayer player; for later purpose!

    /**
     *constructor which initiate {@link #computer}.
     */
    public BattleModel()
    {
        computer = new BattleComputerPlayer();   
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public BattleComputerPlayer getComputerPlayer()
    {
        return computer;
    }
   
//For the views in the future??    
    
    /**
    *used in the {@link #addPropertyChangeListener(PropertyChangeListener)}
    * and {@link #removePropertyChangeListener(PropertyChangeListener)}
    * in the future.
    */
    private final PropertyChangeSupport changer = new PropertyChangeSupport(this);
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listen)
    {
        this.changer.addPropertyChangeListener(listen);
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void removePropertyChangeListener(PropertyChangeListener listen)
    {
        this.changer.removePropertyChangeListener(listen);
    }
    
    /**
     *{@inheritDoc}
     */
    @Override
    public void viewCanUse()
    {
        System.out.println("View cannot use"  );
        //changer.firePropertyChange("MESSAGE", "oldValue", "newValue");
    
    }
//For the views in the future??  
}
