/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleshipprojekt3;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *Class contains the correlation between the components in the MVC theory.
 *The main method creates a new object of this class and the empty contructor
 *initiate the essential objects.
 * 
 * @author didrik
 */
public class BattleShipProjekt3 extends JFrame 
{
    /**
     * constructor creates first two objects to {@link BattleModelInterface} and
     * {@link BattleModelInterface2}, where {@link BattleView1} gets the
     * reference to the {@link BattleModelInterface} object and
     * {@link BattleModel} gets the reference to the {@link BattleModelInterface2}
     * object. Also it adds the panel {@link BattleView1} to the JFrame {@link BattleShipProjekt3} 
     * class.
     */
    public BattleShipProjekt3()
    {
        BattleModelInterface modelInterface = new BattleModel();
        BattleModelInterface2 modelInterface2 = (BattleModelInterface2) modelInterface;
        
        BattleView1 view = new BattleView1(modelInterface);
        add(view); 
        
        BattleController controller = new BattleController(view, modelInterface2);
        
        
        
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * creates a new anonymous instance of {@link BattleShipProjekt3}
     * in the The Event Dispatch Thread.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> 
        { 
            new BattleShipProjekt3();
        });
          
    }
        
       
    
    
    
}
