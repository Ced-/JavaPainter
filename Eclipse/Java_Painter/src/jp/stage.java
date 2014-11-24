package jp;


public class stage extends AbstractGUIElement implements GUIElements 
{

     private int defaultcolor = Java_painter.canvas.color(255, 255, 255);
     private int tempcolor;
   private int elementX=0;
   private int elementY=0;
   private int elementWidth=0;
   private int elementHeight=0;
   private boolean dead=true;
   private boolean locked=false;

   
  stage(int myX,int myY,int elementWidth, int elementHeight, boolean deadelement)
  {
    dead=deadelement;
    setObjectpositions(myX,myY,elementWidth,elementHeight);
  }

  /*<BEGIN create>*/
  public void create()
  { 
    int oldcolor=Java_painter.canvas.g.fillColor;
    Java_painter.canvas.fill(defaultcolor);
    Java_painter.canvas.rect(elementX,elementY,elementWidth,elementHeight);
    registerElementPositions();
    Java_painter.canvas.fill(oldcolor);
    
  }
   /*<END create>*/

 /*<BEGIN registerElementPositions>*/
public void registerElementPositions()
{
  if (!dead)
  {
   
   for (int i = elementX; i < elementX+elementWidth; i=i+1) 
    {
      for (int o = elementY; o < elementY+elementHeight; o=o+1)
      {
        addElementPosition(i,o);
      }
    }
  }
}  
  /*<END registerElementPositions>*/
   
   
  /*<BEGIN set Methoden>*/ 
  public void setObjectpositions(int obtnx, int obtny, int obtnw, int obtnh)
  {
    elementX=obtnx;
    elementY=obtny;
    elementWidth=obtnw;
    elementHeight=obtnh;
    
  //  labelWidth=(Integer)olabelwidth;
    reCreateElements();    
   }


     public void setBgcolor(int R, int G, int B)
   {
     defaultcolor=Java_painter.canvas.color(R, G, B);
     reCreateElements();
   }
   
   
  /*<END set Methoden>*/    
   
   
  /*<BEGIN get Methoden>*/ 
   int getBgcolor()
   {
     return defaultcolor;
   }
   int getWidth()
   {
     return elementWidth;
   }
      int getHeight()
   {
     return elementHeight;
   }
      int getLeftPos()
   {
     return elementX;
   }
   int getTopPos()
   {
     return elementY;
   }

   
   /*<END get Methoden>*/  
   
   
  /*<BEGIN fixe Eventereignisse>*/ 
    
    /*<END fixe Eventereignisse>*/ 
}
