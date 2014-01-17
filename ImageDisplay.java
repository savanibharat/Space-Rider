import greenfoot.*;  
import java.awt.Font;
import java.awt.Color;

public class ImageDisplay extends Actor
{
    private GreenfootImage textImage = new GreenfootImage(200, 24);  
    private String text;
  
    public ImageDisplay()  
    {
        setText("*");
        setImage(textImage);  
        Font font = textImage.getFont();  
        textImage.setFont(font.deriveFont(24.0F));
        textImage.setColor(Color.white);
    }  
      
    public void act()   
    {
        textImage.clear();  
        textImage.drawString(text, 1, 18);    
    }
    
    public void setText(String text) {
        this.text = text;
        this.textImage = new GreenfootImage(20*text.length(), 24); 
        setImage(textImage);  
        Font font = textImage.getFont();  
        textImage.setFont(font.deriveFont(24.0F));
        textImage.setColor(Color.white);
    }
}
