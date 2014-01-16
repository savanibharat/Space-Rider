import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TargetBeam here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TargetBeam extends AnimatedActor
{
private double posx;
    private double posy;
    private double velocityX;
    private double velocityY;
    private int frameCounter = 0;
    private int frameWait = 4;
    TargetBeam()
    {
        super("darkbullet", ".png", 4);

    }
    
    public void act() 
    {
        World wrld = getWorld();
        if (posx == 0.0)
        {
            Bright ball = (Bright) wrld.getObjects(Bright.class).get(0); 
            double diffX = getX() - ball.getX();
            double diffY = getY() - ball.getY();
            velocityX = -10;
            velocityY = 0;
            posx = getX();
            posy = getY();
        }
        
        if(frameCounter >= frameWait)
        {
            super.act();
            frameCounter = 0;
        }
        else
        {
            frameCounter++;
        }
            
            

        
        
        
        setLocation((int)posx, (int)posy);

        posy += velocityY;
        posx += velocityX;
        if (posx <= 30.0)
            wrld.removeObject(this);
        
            


    }    
}
