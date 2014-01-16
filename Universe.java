import greenfoot.*;  
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
public class Universe extends World
{
    private int distance;
    private int level;
    private int[][] PixelArray;
    private int widthDex;
    private boolean moving;
    private GreenfootSound currentMusic;
    private GreenfootSound firing;
    public boolean levelend;
    public int score;
    private boolean over;
    String successful;
    private Integer numPlanetsOrbiting = 0;
    private ImageDisplay planetText;
    private ImageDisplay levelText;
    private ImageDisplay overText;
    private ImageDisplay scoreCounter;
    private ImageDisplay bossHealth;
    private ImageDisplay health;
    private ImageDisplay introText;
    private DisplayMessage msg;
    private int introTextLife = 150;

    private ArrayList<Observer>  life;  
    private GreenfootImage[] images;
    private int currentImage = 0;

    private FireStrategy lowCaliber = new LowCaliber();
    private FireStrategy highCaliber = new HighCaliber();
    public void fire(){}
    public Universe()
    {    

        super(1280, 720, 1); 
        distance = 0;
        level = 1;
        score = 0;
        moving = true;
        ConstructImage(level);
        populate();
        levelend = false;
        over = false;

        firing = new GreenfootSound("fx_projectileFire.mp3");
        life=new ArrayList();
    }

    public boolean isMoving() {
        return moving;
    }

    public void started() {
        switch(level){
            case 1:
            currentMusic = new GreenfootSound("brightTheme.mp3");
            break;
            case 2:
            currentMusic = new GreenfootSound("darkTheme.mp3");
            break;
            case 3:
            currentMusic = new GreenfootSound("forward.mp3");
            break;
        }
        currentMusic.playLoop();
    }

    //public void stopped() {
    //  currentMusic.stop();
    // }

    public void act() {  

        scoreCounter.setText("Score " + Integer.toString(score));
        if (over)
        {
            removeObjects(getObjects(Actor.class));
            overText = new ImageDisplay();
            currentMusic.stop();
            overText.setText("Game over! Press Enter to retry level!");
            addObject(overText, 600, 500);
            moving = false;

            continueCheck();

        }
        else
        {
            distance++;
            if (moving)
            {
                scrollWorld(1, 5);
            }
            else
            {
                if (levelend){
                    this.getBackground().drawImage(images[1], 0, 0);  
                    this.getBackground().drawImage(images[1], 0, 0);
                    continueCheck();
                }
            }
            if (distance%10 == 0)
            {
                getActorFactory();
            }
            String keyPressed=lowCaliber.fire();
            if(Greenfoot.isKeyDown(keyPressed))
            {
                fireLowCaliber();
                if(!firing.isPlaying())
                {
                    firing.playLoop();
                }
            }
            else {
                firing.stop();
            }
            if(Greenfoot.isKeyDown("x"))
            {
                fireHighCaliber();
                if(!firing.isPlaying())
                {
                    firing.playLoop();
                }
            }
            else {
                firing.stop();
            }
        }

        if(introTextLife > 0) {
            introTextLife -= 1;
        }
        else if (introTextLife == 0) {
            introText.setText("   ");
        }
    }

    public void getActorFactory() {
        widthDex++;
        if (widthDex >= 600)
        {
            moving = false;
        }
        else
        {
            for (int i = 0; i < 10; i++)
            {
                int val = PixelArray[widthDex][i];
                if (val != -1)
                {
                    //System.out.printf("Val is %d\n", val);
                }
                if (val == -65536)
                {
                    //System.out.printf("planet Time");
                    addObject(new Planet(), 1280, 72 * i);
                }
                if (val == -16777216)
                    addObject(new Blackhole(), 1280, 72 * i);
                if (val == -16711936)
                {    
                    addObject(new Dark(), 1280, 72 * i);
                    addObject(new DarkBeam(), 1280, 72 * i);
                }
                if (val == -16711681)
                {
                    addObject(new MovingDark(), 1280, 72* i);
                }
                if (val == -256)
                {
                    addObject(new BadBoss(), 1280, 72*i );
                }
            }
        }

    }

    public void levelTransition() {
        score += 2000*(getObjects(Planet.class).size());
        levelend = false;
        moving = true;
        distance = 0;
        switch (level)
        {
            case 1: 
            level = 2;
            removeObjects(getObjects(Actor.class));
            currentMusic.stop();
            currentMusic = new GreenfootSound("darkTheme.mp3");
            break;
            case 2: 
            level = 3;
            removeObjects(getObjects(Actor.class));
            currentMusic.stop();
            currentMusic = new GreenfootSound("forward.mp3");
            break;

            case 3: level = 4;
            removeObjects(getObjects(Actor.class));
            currentMusic.stop();
            break;
            case 0: 
            level = 1;
            currentMusic.stop();
            currentMusic = new GreenfootSound("brightTheme.mp3");
            break;
        }
        currentMusic.playLoop();
        levelend = false;
        populate();
        ConstructImage(level);
    }

    public void ConstructImage(int level){
        //Level background information
        images = new GreenfootImage[2];
        for(int i=0; i < 2; i++) {
            images[i] = new GreenfootImage("Level" + level + "Background" + i + ".jpg");
        }

        widthDex = 1;
        StringBuilder sb = new StringBuilder();
        sb.append("images/Level");
        sb.append(level);
        sb.append(".bmp");
        String strI = sb.toString();
        try{
            GreenfootImage levelImage = new GreenfootImage(strI);
            BufferedImage bufferimage=levelImage.getAwtImage();
            int height=bufferimage.getHeight();
            int width=bufferimage.getWidth();

            PixelArray=new int[width][height];
            for(int i=0;i<width;i++){
                for(int j=0;j<height;j++){
                    PixelArray[i][j]=bufferimage.getRGB(i, j);

                }
            }

            for(int i = 0; i < width; i ++){
            }
        }
        catch(Exception ee){
            ee.printStackTrace();
        }
    }

    /**
     * SPEED WITH DIRECTION
     * 
     * @param direction Accepted values: 1: rightwards, -1: leftwards, 0: no movement
     * @param speed Speed value; 1 is slow, 3 is medium, 5+ is fast (all at medium Greenfoot timestep)
     */
    private void scrollWorld(int direction, int speed) {
        assert direction > -2;
        assert direction < 2;
        GreenfootImage background = new GreenfootImage(images[currentImage]);
        this.getBackground().drawImage(background, -(direction*speed), 0);  
        this.getBackground().drawImage(background, direction*(this.getWidth()-speed), 0);
        images[currentImage] = this.getBackground();
    }

    private void populate() {
        addObject(new Bright(), 400, 550);
        addObject(new Hitbox(), 400, 550);
        addObject(new Inevitable(), 50, 360);

        levelText = new ImageDisplay();
        levelText.setText("Level " + Integer.toString(level));
        addObject(levelText, 375, 75);

        scoreCounter = new ImageDisplay();
        scoreCounter.setText("Score " + Integer.toString(score));
        addObject(scoreCounter, 575, 75);

        health = new ImageDisplay();
        health.setText("Health is");
        addObject(health, 175, 75);

        introText = new ImageDisplay();
        introText.setText("Arrow keys for navigation. Keys z and x for fire.");
        addObject(introText, 500, 200);

    }

    public void fireLowCaliber() {
        Actor bright = (Actor)getObjects(Bright.class).get(0);
        if (distance %5 == 0)
        {
            addObject(new ShootyThing(0), bright.getX(), bright.getY());
            addObject(new ShootyThing(10), bright.getX(), bright.getY());
            addObject(new ShootyThing(20), bright.getX(), bright.getY());
            //lowCaliber.fire(bright);
        }
    }
    public void fireHighCaliber() {
        Actor bright = (Actor)getObjects(Bright.class).get(0);
        if (distance %5 == 0)
        {
            addObject(new ShootyThing(0), bright.getX(), bright.getY());
            addObject(new ShootyThing(-20), bright.getX(), bright.getY());
            addObject(new ShootyThing(-10), bright.getX(), bright.getY());
        }
    }

    private void continueCheck()
    {
        levelText.setText("Press enter to continue!");
        if(Greenfoot.isKeyDown("enter")) {
            over = false;
            removeObjects(getObjects(Actor.class));
            if(level < 3)
            {
                levelTransition();
            }
            else
            {
                overText = new ImageDisplay();
                currentMusic.stop();
                overText.setText("Game over! You win! Your score was: " + Integer.toString(score));
                addObject(overText, 600, 500);
            }
        }
    }

    public void gameOver()
    {
        level -= 1;
        score = 0;
        over = true;

    }

    public void healthUpdate(int heal)
    {
        health.setText("Health: " + Integer.toString(heal));
    }
//////////////////////////////////////////////////////////////////
    public void registerObserverInUniverse(Observer myNewObserver){
        life.add(myNewObserver);
    }

    public void unregisterObserverInUniverse(Observer removeObserver){
        life.remove(removeObserver);
    }

    public void notifyObserver(){
        for(Observer observerObject:life){
            observerObject.updateScore(score);
        }
    }

    public void setState(String successful){
        this.successful=successful;
        notifyObserver();
    }

    public String getState(){
        return successful;
    }
    ////////////////////////////////////////////////////////////////////
}
