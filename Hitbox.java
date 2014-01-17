import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Hitbox extends Actor
{
    public Hitbox()
    {
            GreenfootImage trans = getImage();
            trans.setTransparency(0);
            setImage(trans);
    }
    /**
     * Act - do whatever the Hitbox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        Bright ball = (Bright) getWorld().getObjects(Bright.class).get(0); 
        setLocation(ball.getX(), ball.getY());
    }    
}
