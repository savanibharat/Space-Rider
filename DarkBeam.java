import greenfoot.*;


public class DarkBeam extends AnimatedActor
{
    private double posx;
    private double posy;
    private double velocityX;
    private double velocityY;
    private int frameCounter = 0;
    private int frameWait = 4;
    DarkBeam()
    {
        super("darkbullet", ".png", 4);

    }
    /**
     * Act - do whatever the ShootyThing wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
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
        Bright bright = (Bright) getOneIntersectingObject(Bright.class);
        Hitbox box = (Hitbox) getOneIntersectingObject(Hitbox.class);
        if (posx <= 30.0)
            wrld.removeObject(this);

        if(box != null) {
            bright.die();
        }
            


    }    
}
