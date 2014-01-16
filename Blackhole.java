import greenfoot.*; 


public class Blackhole extends AnimatedActor
{
    private int frameCounter = 0;
    private int frameWait = 6;
    public Blackhole()
    {
        super("blackhole", ".png", 4);
    }
    
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
        setLocation(getX() - 5, (getY()));
        if (getX() <= 10.0)
            getWorld().removeObject(this);
    }    
}
