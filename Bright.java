import greenfoot.*; 
public class Bright extends AnimatedActor
{
    private int health;
    private double posx;
    private double poxy;
    private double velocityX;
    private double velocityY;
    private int frameCounter = 0;
    private int frameWait = 8;
    private int cooldown;
   
    public Bright()
    {
        super("spaceshipmotion", ".png", 6);
        velocityX = 0;
        velocityY = 0;
        health = 3;
        
    
    }
    public void act() 
    {
        ((Universe)getWorld()).healthUpdate(health);
        if(getX() < 10)
            die();
        if (cooldown <= 0)
        {
            if(velocityX <= -1)
            {
                super.changeImage("spaceshipidle", ".png", 6);
            }
            else
            {
                super.changeImage("spaceshipmotion", ".png", 6);
            }
        }
        else
        {
            cooldown--;
            GreenfootImage trans = getImage();
            trans.setTransparency(120);
            setImage(trans);
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
        setLocation(getX() + (int)velocityX, getY() + (int)velocityY);
       /* java.util.List l = getWorld().getObjects(Blackhole.class);
        for (int i = 0; i < l.size(); i ++)
        {
            
            Actor blob = (Actor)l.get(i);
            double ballX = (double)blob.getX();
            double ballY = (double)blob.getY();
            double diffX = (double)getX() - ballX;
            double diffY = (double)getY() - ballY;
            double distance = (diffX * diffX) + (diffY * diffY);
            double aXMult = 10000.0/distance;
            double aYMult = 13000.0/distance;
            if (diffX < 0)
                aXMult *= -1.0;
            if (diffY < 0)
                aYMult *= -1.0;
            if (distance < 45000)
            {
                super.changeImage("spaceship", ".png", 6);
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
        }*/
        //System.out.printf("Velocity Y %f\n", velocityY);
        //System.out.printf("Velocity X %f\n", velocityX);
        keycheck();
    }
    private void keycheck()
    {
        if(Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) {
            if (velocityX < 5.5)
            {
                velocityX += 1.0;
            }
                
        }
        else velocityX *= .85;
        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")){
            if (velocityX > -5.5)
            {
                velocityX -= 1.0;
            }
        }
        else
            velocityX *= .85;
        if(Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w")) {
            if (velocityY > -5.5)
                velocityY -= 1.0;
        } 
        else velocityY *= .85;
        if (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s")){
            if (velocityY < 5.5)
                velocityY += 1.0;
        }
        else velocityY *= .85;
    }
    public void die()
    {
        if (cooldown <= 0)
        {
            super.changeImage("spaceship", ".png", 6);
            health --;
            ((Universe)getWorld()).healthUpdate(health);
            cooldown = 30;
            if (health <= 0)
            {
                ((Universe)getWorld()).gameOver();
            }
        }
    }
}