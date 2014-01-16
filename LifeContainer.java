import greenfoot.*;  
/**
 * Write a description of class LifeContainer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LifeContainer implements Observer 
{
    Universe universe;
    LifeContainer(Universe universe){
        this.universe=universe;
    }
    
   public void levelFailure(Universe universe){
       
   }

    public void updateScore(int addCount){
        
        universe.addObject(new ImageDisplay(),1280,720);
        Greenfoot.stop();
    }
}
