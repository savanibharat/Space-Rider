import greenfoot.*; 

/**
 * Write a description of class Inevitable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Inevitable extends AnimatedActor
{
    private int frameCounter = 0;
    private int frameWait = 6;
    private boolean dying;
    private int deathcounter = 0;
    public Inevitable ()
    {
        super("darkness", ".png", 4);
    }
    /**
     * Act - do whatever the Dark wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(frameCounter >= frameWait)
        {
            super.act();
            frameCounter = 0;
        }
        else
        {
            frameCounter++;
        }
    }  
}
