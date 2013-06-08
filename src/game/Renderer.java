package game;

import game.movement.Location;
import game.sprite.Sprite;
import game.character.Character;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.swing.*;


public class Renderer extends JPanel
{
    
    private Image background;
    Collection<Character> charactersToRender;
    
    public void setBackground(Image background)
    {
	this.background = background;
    }
    
    public static int width(){return getInstance().getWidth();}
    public static int height(){return getInstance().getHeight();}
    
    public Image getBackgroundImage()
    {
	return background;
    }
    
    
    public void setBackgroundImage(Image image)
    {
	background=image;
    }
    
    public static void reset(){
	singleton=null;
    
	
	
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 718, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 491, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
    
    /**
     * Creates a new instance of Renderer2d
     */
    
    public Renderer()
    {
	    singleton = this;
	    initComponents();
	
	transformArrow = new AffineTransform();
	
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
	
    }
    
    // declare two instance variables at the head of the program
    private Image doubleBuffer;
    private Graphics doubleBufferSwap;
    
    
    
    /**update - this acts as a double buffer
     update is normally called before paint.
     the image is cleared and this results in flicker.
     Here we override the update method to create an image
     to swap behind to reduce flicker.
     @param g a Graphics object representing the current graphics
     context
     */
    public void update(Graphics g)
    {

	Graphics2D gg = (Graphics2D)g;
	gg.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	if (doubleBuffer == null)
	{
	    doubleBuffer = createImage(this.getSize().width, this.getSize().height);
	    doubleBufferSwap = doubleBuffer.getGraphics();
	}
	
	
	//doubleBufferSwap.setColor(getBackground());
	doubleBufferSwap.setColor(Color.BLACK);
	doubleBufferSwap.fillRect(0, 0, this.getSize().width, this.getSize().height);
	
	
	
	paint(doubleBufferSwap);
	
	
	gg.drawImage(doubleBuffer, 0, 0, this);
	
    }
    
    
    
    @Override
    public void paint(Graphics g)
    {
	
	Graphics2D gg = (Graphics2D)g;
	//Firstly draw the background
	
	
	gg.drawImage(getBackgroundImage(), 0,0, this.getWidth(),this.getHeight(),this);
	gg.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	
	charactersToRender=GameEngine.getInstance().getCharacters().values();
	for (Iterator it = charactersToRender.iterator(); it.hasNext(); )
	{
	    Character o = (Character) it.next();
	    Location l = o.getLocation();
	    Sprite s=(Sprite)o.getSprite();
	    if (s.isSpriteShown())
	    {
		gg=(Graphics2D)(s.paint((Graphics)gg,l));
		
	    }
	}
	
	//gg=	drawArrow(gg);
	
	
	
	try
	{
	    Thread.sleep(GameEngine.SLEEP_LENGTH);
	}
	catch(InterruptedException E)
	{}
	
	
    }
    private static Renderer singleton;
    
    public static Renderer getInstance()
    {
	if (singleton != null)
	{
	    return singleton;
	    
	}
	else
	{
	    synchronized(Renderer.class)
	    {
		if (singleton==null)
		    singleton = new Renderer();
	    }
	    
	}
	return singleton;
    }
    
    private BufferedImage iArrow;
    private AffineTransform transformArrow;
    private boolean arrowDrawnOnce;
    
/*    private Graphics2D drawArrow(Graphics2D gg)
    {
	CharacterBoat boat = (CharacterBoat)GameEngine.getInstance().getCharacters().get(0);
	
	Location boatLocation = boat.getLocation();
	
	Controller c = Controller.getInstance();
	arrowDrawnOnce=true;
	Location l =c.getMouseLocation();
	int boatX= (int)boatLocation.x;
	int boatY= (int)boatLocation.y;
	int mouseX= (int)l.x;
	int mouseY= (int)l.y;
	int dx = mouseX -boatX;
	int dy = mouseY- boatY;
	if (dx==0) dx =1;//just approximate so avoids divide by zero errors and creating 0 height/width images
	if (dy==0) dy =1;
	double angle = Math.tan((int)(dy / dx));
	BufferedImage b = (BufferedImage)createImage(Math.abs(dx),Math.abs(dy));
	
	Graphics2D gTemp  =  	(Graphics2D)b.createGraphics();
	
	Color  arrow= new java.awt.Color(230,230,10,40);
	gTemp.setColor(arrow);
	gTemp.fillRoundRect(boatX,boatY,Math.abs(dx),Math.abs(dy),5,5);
	
	transformArrow.setToRotation(angle);
	transformArrow.translate(boatX,boatY);
	
	
	gg= gTemp;
	
	
	
	return gg;
    }
  */  

    
}
