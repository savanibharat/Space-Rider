import greenfoot.*; 


public class Dark extends AnimatedActor
{
    private int frameCounter = 0;
    private int frameWait = 8;
    private boolean dying;
    private int deathcounter = 0;
    public Dark ()
    {
        super("darkidle", ".png", 4);
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
        setLocation(getX() - 5, (getY()));


        ShootyThing bullet = (ShootyThing) getOneIntersectingObject(ShootyThing.class);
            if(bullet != null) {
                dying = true;
                super.changeImage("darkdeath", ".png", 5);
                getWorld().removeObject(bullet);
            }
        Bright bright = (Bright) getOneIntersectingObject(Bright.class);
        Hitbox box = (Hitbox) getOneIntersectingObject(Hitbox.class);
        if (deathcounter > 10)
        {
            wrld.removeObject(this);
            ((Universe)wrld).score += 50;
        }
        else if (getX() < 10)
            wrld.removeObject(this);

        if(box!= null) {
            bright.die();
        }
    }    
}
