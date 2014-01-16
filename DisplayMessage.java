/**
 * Write a description of class DisplayMessage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DisplayMessage implements Observer 
{
    Universe universe;
    static int addcount;
    /**
     * Constructor for objects of class DisplayMessage
     */
    public DisplayMessage()
    {
    }
    public void levelFailure(Universe universe){
    this.universe=universe;
    universe.gameOver();
    
    }
    public void updateScore(int addCount){
        this.addcount+=addCount;
    }
}
