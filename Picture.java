import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  
  
  /** Method to set the red and green to 0 */
  public void keepOnlyBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    //Iterate through each row and column of pixels
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        //Set red and green equal to zero
        pixelObj.setRed(0);
        pixelObj.setGreen(0);
      }
    }
  }
  
  
  
  
  /** Method to negate the colors of each pixel */
  public void negate()
  {
    Pixel[][] pixels = this.getPixels2D();
    //Iterate through each row and column of pixels
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        //Subtract the current color number from 255 to invert the color
        pixelObj.setRed(255 - pixelObj.getRed());
        pixelObj.setGreen(255 - pixelObj.getGreen());
        pixelObj.setBlue(255 - pixelObj.getBlue());
      }
    }
  }
  
  /** Method to gray the colors of each pixel */
  public void grayscale()
  {
    Pixel[][] pixels = this.getPixels2D();
    //Iterate through each row and column of pixels
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        //Average the pixel colors for red  
        pixelObj.setRed((pixelObj.getRed() + pixelObj.getGreen() + 
                pixelObj.getBlue()) / 3);
        //Average the pixel colors for green
        pixelObj.setGreen((pixelObj.getRed() + pixelObj.getGreen() + 
                pixelObj.getBlue()) / 3);
        //Average the pixel colors for blue
        pixelObj.setBlue((pixelObj.getRed() + pixelObj.getGreen() + 
                pixelObj.getBlue()) / 3);
      }
    }
  }  

  /** Method to make the blue fish appear darker blue underwater */  
  public void fixUnderwater()
  {
    Pixel[][] pixels = this.getPixels2D();
    //Iterate through each row and column of pixels
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
          //Increase the blue if the blue is currently greater than 160  
          if (pixelObj.getBlue() > 160){
                pixelObj.setBlue(pixelObj.getBlue() + 50);
            }            
      }
    }
  }
   
  
  
//MIRROR THE IMAGE VERTICALLY FROM LEFT TO RIGHT
  public void mirrorVertical()
  {
    //Declare the array and variables for left and right pixels
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    //Declare the width variable
    int width = pixels[0].length;
    //Iterate through each row
    for (int row = 0; row < pixels.length; row++)
    {
      //Iterate through each column for columns less than half the width
      for (int col = 0; col < width / 2; col++)
      {
        //Determine the left pixels
        leftPixel = pixels[row][col];
        //Determine the right pixels
        rightPixel = pixels[row][width - 1 - col]; 
        //Set the rigth pixels equal to the left pixels for mirror effect
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  

//MIRROR THE IMAGE VERTICALLY FROM RIGHT TO LEFT  
    public void mirrorVerticalRightToLeft()
  {
    //Declare the array and variables for left and right pixels
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    //Declare the width variable
    int width = pixels[0].length;
    //Iterate through each row
    for (int row = 0; row < pixels.length; row++)
    {
      //Iterate through each column for columns less than half the width
      for (int col = 0; col < width / 2; col++)
      {
        //Determine the left pixels
        leftPixel = pixels[row][col];
        //Determine the right pixels
        rightPixel = pixels[row][width - 1 - col];
        //Set the left pixels equal to the right pixels for mirror effect
        leftPixel.setColor(rightPixel.getColor());
      }
    } 
  }
    
//MIRROR THE IMAGE HORIZONTALLY FROM TOP TO BOTTOM
    public void mirrorHorizontal() 
  {
    //Declare the array and variables for top and bottom pixels
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    //Declare the height variable
    int height = pixels[0].length;
    //Iterate through each row for rows less than half the height
    for (int row = 0; row < pixels.length / 2; row++)
    {      
      //Iterate through each column
      for (int col = 0; col < height; col++)
      {                 
      //Determine the top pixels
      topPixel = pixels[row][col];
      //Determine the bottom pixels
      bottomPixel = pixels[pixels.length - 1 - row][col]; 
      //Set the bottom pixels equal to the top pixels for mirror effect
      bottomPixel.setColor(topPixel.getColor());            

      }
    }
  }
    
//MIRROR THE IMAGE HORIZONTALLY FROM BOTTOM TO TOP
    public void mirrorHorizontalBotToTop() 
  {
    //Declare the array and variables for top and bottom pixels
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    //Declare the height variable
    int height = pixels[0].length;
    //Iterate through each row for rows less than half the height
    for (int row = 0; row < pixels.length / 2; row++)
    {       
      //Iterate through each column
      for (int col = 0; col < height; col++)
      {               
      //Determine the top pixels
      topPixel = pixels[row][col];  
      //Determine the bottom pixels 
      bottomPixel = pixels[pixels.length - 1 - row][col];       
      //Set the top pixels equal to the bottom pixels for mirror effect
      topPixel.setColor(bottomPixel.getColor());            

      }
    }
  }
   
    
  //MIRROR THE IMAGE DIAGONALLY
  public void mirrorDiagonal()
  {
    //Declare the array and variables for top and bottom pixels
    Pixel[][] pixels = this.getPixels2D();
    Pixel topReflectionPixel = null;
    Pixel bottomReflectionPixel = null;
    //Declare the width variable
    int width = pixels[0].length;
    //Declare the height variable
    int height = pixels.length;
    //Iterate through each row
    for (int row = 0; row < height; row++)
    {
      //Iterate through each column
      for (int col = 0; col < width; col++)
      {
        /**
         * Reflects the image only when the column is less than the height, 
         * causing a square section of the image to be diagonally reflected. 
         */
        if (col < height)
        {
            //Determine the top reflection pixels
            topReflectionPixel = pixels[row][col];
            //Determine the bottom reflection pixels
            bottomReflectionPixel = pixels[col][row];
            /** 
             * Set the top reflection pixels equal to the bottom reflection
             * pixels to produce a mirror effect. 
             */
            topReflectionPixel.setColor(bottomReflectionPixel.getColor());
        }
      }
    } 
  }
    
    
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
