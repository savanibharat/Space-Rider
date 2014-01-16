import greenfoot.*; 

public class AnimatedActor extends Actor
{
    private GreenfootImage[] images;
    private int currentImage = 0;
    
    public AnimatedActor(String basename, String suffix, int noOfImages)
    {
        images = new GreenfootImage[noOfImages];
        ImageRepository imageRepository = new ImageRepository();
        for(Iterator iter = imageRepository.getIterator(); iter.getindex() < noOfImages;){
          int i = iter.next();
             images[i] = new GreenfootImage(basename + i + suffix);
         }
         setImage(images[currentImage]);
        
       /*for(int i=0; i < noOfImages; i++) {
            images[i] = new GreenfootImage(basename + i + suffix);
        }
        setImage(images[currentImage]);*/
    }
    
    public AnimatedActor(String basename, String suffix)
    {
        images = new GreenfootImage[1];
        images[0] = new GreenfootImage(basename + suffix);
        setImage(images[currentImage]);
    }
    
    public void changeImage(String basename, String suffix, int noOfImages)
    {
        images = new GreenfootImage[noOfImages];
        ImageRepository imageRepository = new ImageRepository();
        for(Iterator iter = imageRepository.getIterator(); iter.getindex() < noOfImages;){
          int i = iter.next();
             images[i] = new GreenfootImage(basename + i + suffix);
         }
         setImage(images[currentImage]);
        /*for(int i=0; i < noOfImages; i++) {
            images[i] = new GreenfootImage(basename + i + suffix);
        }
        setImage(images[currentImage]);*/
    }
    
     public void changeImage(String basename, String suffix)
    {
        images = new GreenfootImage[1];
        images[0] = new GreenfootImage(basename + suffix);
        setImage(images[currentImage]);
    }
    
   
    public void act() 
    {
        currentImage = (currentImage + 1) % images.length;
        setImage(images[currentImage]);
    }
}