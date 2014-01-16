import greenfoot.*;  


public class BadBoss extends AnimatedActor
{
    private int frameCounter = 0;
    private int frameWait = 16;
    private boolean dying;
    private int deathcounter = 0;
    public int health = 500;
    public BadBoss ()
    {
        super("dark3idle", ".png", 4);
    }
    /**
     * Act - do whatever the Dark wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {

        World wrld = getWorld();
        if(frameCounter >= frameWait)
        {
            super.act();
            frameCounter = 0;
        }
        else
        {
            frameCounter++;
        }
        if (dying)
            deathcounter++;
        GreenfootImage trans = getImage();
        trans.scale(500,500);
        setImage(trans);
        setLocation(getX(), (getY()));

        if (deathcounter > 10)
        {
            wrld.removeObject(this);
            ((Universe)wrld).score += 50;
        }
        else if (getX() < 10)
            wrld.removeObject(this);
    }     
}
