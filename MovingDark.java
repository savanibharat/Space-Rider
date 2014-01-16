import greenfoot.*;  
class MovingDark extends AnimatedActor
{
    private int frameCounter = 0;
    private int frameWait = 8;
    private boolean dying;
    private int deathcounter = 0;
    private double posx;
    private double posy;
    private double velx;
    private double vely;
    private int lifetime;
    public MovingDark ()
    {
        super("dark2idle", ".png", 4);
        velx = -3;
    }
    /**
     * Act - do whatever the Dark wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        World wrld = getWorld();
        double randval = Math.random();
        double rand2 = Math.random();
        lifetime++;
        if (lifetime % 100 == 0)
        {
            if (randval < .5)
            {
                velx = 2;
            }
            else
                velx = -8;
            if (rand2 < .5)
                vely = -3;
            else
                vely = 3;
            
        }
        else if (lifetime % 50 == 0)
        {
            velx = -3;
            vely = 0;
            wrld.addObject(new DarkBeam(), getX(), getY());
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
        if (dying)
            deathcounter++;
        setLocation(getX() + (int)velx, (getY() + (int)vely));


        ShootyThing bullet = (ShootyThing) getOneIntersectingObject(ShootyThing.class);
            if(bullet != null) {
                dying = true;
                super.changeImage("dark2death", ".png", 5);
                getWorld().removeObject(bullet);
            }
        Bright bright = (Bright) getOneIntersectingObject(Bright.class);
        Hitbox box = (Hitbox) getOneIntersectingObject(Hitbox.class);
        if (deathcounter > 10)
        {
            wrld.removeObject(this);
            ((Universe)wrld).score += 75;
        }
        else if (getX() < 10)
            wrld.removeObject(this);
        
            if(box != null) {
                bright.die();
            }
    }   
}
