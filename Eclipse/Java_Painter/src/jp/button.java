package jp;

public class button extends AbstractGUIElement implements GUIElements
{
     private String Caption="";
     private int defaultcolor = Java_painter.canvas.color(210, 210, 210);
     private int pressedcolor =  Java_painter.canvas.color(210, 210, 210);
     private int textcolor = Java_painter.canvas.color(0, 0, 0);
     private int tempcolor;
   public int elementX=0;
   public int elementY=0;
   public float elementWidth=0;
   public int elementHeight=0;
   private boolean colorwaiter=false; 
   private java.util.Timer zz;
  

  button(int myX,int myY,String myCaption)
  {
     setCaption(myCaption);
     setObjectpositions(myX,myY,15);
  }
/*  
    button(int myX,int myY,int myHeight,int myWidth)
  {
     setObjectpositions(myX,myY,myHeight,myWidth);
  }
+7
  /*<BEGIN create>*/
  public void create()
  { 
    int oldcolor=Java_painter.canvas.g.fillColor;
    Java_painter.canvas.fill(defaultcolor);
    Java_painter.canvas.rect(elementX,elementY,elementWidth,elementHeight);
    Java_painter.canvas.fill(textcolor);
    Java_painter.canvas.text(Caption, elementX, elementY+(elementHeight/2)+4);
    registerElementPositions();
    Java_painter.canvas.fill(oldcolor);
  }
   /*<END create>*/
   
     
 /*<BEGIN registerElementPositions>*/
public void registerElementPositions()
{

   for (int i = elementX; i < elementX+elementWidth; i=i+1) 
    {
      for (int o = elementY; o < elementY+elementHeight; o=o+1)
      {
        addElementPosition(i,o);
      }
    }
}  
  /*<END registerElementPositions>*/
  

  /*<BEGIN set Methoden>*/ 
  public void setObjectpositions(int obtnx, int obtny, int obtnheight)
  {
    elementX=(Integer)obtnx;
    elementY=(Integer)obtny;   
    //nicht mehr benutzt, da jetzt Caption die Breite bestimmt: 
    //elementWidth=(Integer)obtnwidth;
    elementHeight=(Integer)obtnheight;

    reCreateElements();    
  }
/*
  public void setObjectpositions(int obtnx, int obtny, int obtnheight, int obtnwidth)
  {
    elementX=(Integer)obtnx;
    elementY=(Integer)obtny;   
    //nicht mehr benutzt, da jetzt Caption die Breite bestimmt: 
    //elementWidth=(Integer)obtnwidth;
    elementHeight=(Integer)obtnheight;
    elementWidth=(Integer)obtnwidth;
    reCreateElements();    
  }
*/
  
  public void setCaption(String caption)
   {
    // println (textWidth("ttt"));
     Caption="   "+(String)caption+"   ";
     elementWidth=Java_painter.canvas.textWidth(Caption);  
    // elementWidth=100;
    reCreateElements();
   }
   
   public void setBgcolor(int R, int G, int B)
   {
     
     while(colorwaiter)
     {
     } 

     defaultcolor=Java_painter.canvas.color(R,G,B);
     create();
   }
   
     public void setFontcolor(int R, int G, int B)
  {
      textcolor=Java_painter.canvas.color(R,G,B);
    create();
   }

  /*<END set Methoden>*/    
   
   
  /*<BEGIN get Methoden>*/ 
   public String getCaption()
   {
     return Caption.substring(3,Caption.length()-3);
   }
   int getWidth()
   {
     return (int)elementWidth;
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
    public void _mouseClicked()
  { 
    tempcolor=defaultcolor;
    defaultcolor=pressedcolor;
    create();
    //   rect(elementX,elementY,elementWidth,elementHeight); 
       // colorwaiter=true;
    //create();
       
        //tempcolor=tempcolor;
       
        
   zz = new java.util.Timer(false);
    zz.schedule(new java.util.TimerTask(){
        public void run(){
        if (defaultcolor==pressedcolor)defaultcolor=tempcolor; 
        create();
        //colorwaiter=false;
        this.cancel();
        zz.cancel();
        }},60);      
    
 
  }
    /*<END fixe Eventereignisse>*/ 
}




