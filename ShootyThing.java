import greenfoot.*;  

/**
 * Write a description of class ShootyThing here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShootyThing extends AnimatedActor 
{
    private double posx;
    private double posy;
    private double velocityX;
    private double velocityY;
    private int frameCounter = 0;
    private int frameWait = 4;
    ShootyThing(int yvel)
    {
        super("bullet", ".png", 4);
        velocityY = yvel;
    }
    /**
     * Act - do whatever the ShootyThing wants to do. This method is called whenever
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
        if (posx == 0)
            posx = getX();
        if (posy == 0)
            posy = getY();
        setLocation((int)posx, (int)posy);

        velocityX = 30;
        java.util.List l = wrld.getObjects(Blackhole.class);
        for (int i = 0; i < l.size(); i ++)
        {
            Actor blob = (Actor)l.get(i);
            double ballX = (double)blob.getX();
            double ballY = (double)blob.getY();
            double diffX = (double)getX() - ballX;
            double diffY = (double)getY() - ballY;
            double distance = (diffX * diffX) + (diffY * diffY);
            double aXMult = 5000.0/distance;
            double aYMult = 5000.0/distance;
            if (diffX < 0)
                aXMult *= -1.0;
            if (diffY < 0)
                aYMult *= -1.0;
            if (distance < 45000)
            {
                velocityX -= aXMult;
                if (aYMult > 1.0)
                    aYMult = .95;
                if (aYMult < -1.0)
                    aYMult = -.95;
                velocityY -= aYMult;
                if (velocityX > 5)
                    velocityX = 5;
                if (velocityX < -5)
                    velocityX = -5;
                if (velocityY > 5)
                    velocityY = 5;
                if (velocityY < -5)
                    velocityY = -5;
            }
        }
        posy += velocityY;
        posx += velocityX;
        velocityY *= .9;
        if (posx >= 1270.0)
            wrld.removeObject(this);
        if (posx <= 30.0)
            wrld.removeObject(this);
        if (velocityX < -4.7)
            wrld.removeObject(this);
        
            


    }    
}
