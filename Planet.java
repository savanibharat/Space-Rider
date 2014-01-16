import greenfoot.*; 

public class Planet extends AnimatedActor
{
private double velocity;
    private double posx;
    private double poxy;
    private double velocityX;
    private double velocityY;
    private double theta;
    private double r;
    public boolean star;
    private int planetType;
    /**
     * Act - do whatever the BouncyBall wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Planet()
    {
        super("planet1sad", ".png");
        velocityX = 0;
        velocityY = 0;
        star = false;
        theta = Math.random()*2*Math.PI;
        planetType = (int)(Math.random()*5.99999999999);
        super.changeImage("planet" + planetType + "sad", ".png");
        r = Math.random()*50 + 75;
       // System.out.printf("PlanetDraw\n");
    }
    public void act() 
    {
        setLocation(getX() + (int)velocityX, (getY() + (int)velocityY));
        theta += .1;
        if (theta >= 2*Math.PI)
            theta = 0;
        
            
        Bright ball = (Bright) getWorld().getObjects(Bright.class).get(0);
        double ballX = 0;
        double ballY = 0;
        if (((Universe)getWorld()).isMoving())
        {
            ballX = ball.getX() + r * Math.sin(theta);
            ballY = ball.getY() + r * Math.cos(theta);
        }
        else if (star)
        {
            ballX = 700;
            ballY = 0;
        }
        else
        {
            ballX = 1000;
            ballY = 300;
            GreenfootImage trans = getImage();
            trans.setTransparency(trans.getTransparency() - 1);
            setImage(trans);
           // System.out.printf("Transparency: %d\n", trans.getTransparency());
            if (trans.getTransparency() < 50)
            {
                //super.changeImage("spaceshipidle", ".png", 6);
                star = true;
            }
            

            
        }
        double diffX = getX() - ballX;
        double diffY = getY() - ballY;
        double distance = (diffX * diffX) + (diffY * diffY);
        if(!((Universe)getWorld()).isMoving())
        {

            velocityX = -diffX/10;
            velocityY = -diffY/10;
        }
        else
        {
            if (distance < 25000)
            {
                super.changeImage("planet" + planetType + "happy", ".png");
                velocityX = -diffX/10;
                velocityY = -diffY/10;
            }
            else
            {
                super.changeImage("planet" + planetType + "sad", ".png");
                velocityX = .95*velocityX;
                velocityY = .95*velocityY;
                velocityX -= .3;
                if (velocityX < -5.0)
                    velocityX = -5.0;
            }
        }
        if (getX() <= 30.0)
        {
            getWorld().removeObject(this);
           // System.out.printf("Planet Removed\n");
        }
        if (star)
        {
            if (getY() < 20)
            {
                ((Universe)getWorld()).levelend = true;
            }
        }

    }
}
