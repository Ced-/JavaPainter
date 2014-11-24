package jp;






/*Nun die eigentlichen GUIElements... es wurden zunächst Mal zwei umgesetzt: ein Button und eine Checkbox. Es ist aber in dieser Struktur noch anderes denkbar:
ein simples Textlabel, eine Listbox für verschiedene (einzeilige) Textelemente die angeklickt werden können, sogar eine Dropbox wäre denkbar.*/

public class textlabel extends AbstractGUIElement implements GUIElements
{
     private String Caption="";
     private int defaultcolor = Java_painter.canvas.color(210, 210, 210);
     private int textcolor = Java_painter.canvas.color(0, 0, 0);
     private int tempcolor;
   public int elementX=0;
   public int elementY=0;
   public float elementWidth=0;
   public int elementHeight=0;
   private boolean dead=true;  
      private boolean fixedWidth=false;  
 
  

  textlabel(int myX,int myY,String myCaption)
  {
     setCaption(myCaption);
     setObjectpositions(myX,myY,15);
  }

 textlabel(int myX,int myY,String myCaption, boolean deadelement)
  {
     setCaption(myCaption);
     setObjectpositions(myX,myY,15);
     dead=deadelement;
  }

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
  if(!dead)
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
  public void setObjectpositions(int obtnx, int obtny, int obtnheight)
  {
    elementX=(Integer)obtnx;
    elementY=(Integer)obtny;   
    //nicht mehr benutzt, da jetzt Caption die Breite bestimmt: 
    //elementWidth=(Integer)obtnwidth;
    elementHeight=(Integer)obtnheight;

    
    reCreateElements();    
  }
  public void setFontcolor(int R, int G, int B)
  {
      textcolor=Java_painter.canvas.color(R,G,B);
  //  labelWidth=(Integer)olabelwidth;
    create();
   }  
  public void setCaption(String caption)
   {
    // println (textWidth("ttt"));
     Caption="   "+(String)caption+"   ";
    
    if (!fixedWidth)
    {
      elementWidth=Java_painter.canvas.textWidth(Caption);
      reCreateElements();
    }
    else
    {
      create();
    }
   }
   
   public void setBgcolor(int R, int G, int B)
   {
     defaultcolor=Java_painter.canvas.color(R,G,B);
     create();
   }
   public void setFixedWidth(int fwidth)
   {
     if (fwidth>-1)
     {
       elementWidth=fwidth; 
       fixedWidth=true;
     }
     else
     {
       fixedWidth=false;
     }
     setCaption(Caption);
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

    /*<END fixe Eventereignisse>*/ 
}


