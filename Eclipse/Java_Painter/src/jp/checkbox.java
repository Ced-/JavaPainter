package jp;

public class checkbox extends AbstractGUIElement implements GUIElements 
{
  /* später noch Interface implementieren für dieses und künftige Userinterfaceobjekte, verpflichtet die Methoden "objectpositions", "create", "setObjectId" und "setObjectManager"zu implementieren... letztere zwei möglicherweise auch nur als Fields, statt als Methoden*/
  /* den Aufbau des Interfaces und wie ein Userinterface-Element dementsprechendes mindestens implementiert sein muss, bei Gelegenheit moch etwas genauer überdenken....*/
  

     private String Caption="";
     private int defaultcolor = Java_painter.canvas.color(255, 255, 255);
     private int labelcolor = Java_painter.canvas.color(150, 150, 150);
     private int textcolor = Java_painter.canvas.color(0, 0, 0);
     private int tempcolor;
     private boolean checked=false;
   private int elementX=0;
   private int elementY=0;
   private int elementWidth=12;
   private int elementHeight=15;
   private float labelWidth=20;
   private int labelHeight=15;   
   
  checkbox(int myX,int myY,String myCaption, boolean checkstate)
  {
    setObjectpositions(myX,myY);
    setCaption(myCaption);
    checked=checkstate;
  }

  /*<BEGIN create>*/
  public void create()
  { 
    int oldcolor=Java_painter.canvas.g.fillColor;
    Java_painter.canvas.fill(defaultcolor);
    Java_painter.canvas.rect(elementX,elementY,elementWidth,elementHeight);
    if (checked)
    {
    	Java_painter.canvas.line(elementX,elementY,elementX+elementWidth,elementY+elementHeight);
    	Java_painter.canvas.line(elementX+elementWidth,elementY,elementX,elementY+elementHeight);
    }
    Java_painter.canvas.fill(labelcolor);
    Java_painter.canvas.rect(elementX+elementWidth+2,elementY,labelWidth,labelHeight);
    Java_painter.canvas.fill(textcolor);
    Java_painter.canvas.text(Caption, elementX+elementWidth+3, elementY+11);

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
  public void setObjectpositions(int obtnx, int obtny)
  {
    elementX=obtnx;
    elementY=obtny;
  //  labelWidth=(Integer)olabelwidth;
    reCreateElements();        
   }
  public void setLabelcolor(int R, int G, int B)
  {
      labelcolor=Java_painter.canvas.color(R,G,B);
  //  labelWidth=(Integer)olabelwidth;
    create();
   }
  
   public void setFontcolor(int R, int G, int B)
  {
      textcolor=Java_painter.canvas.color(R,G,B);
  //  labelWidth=(Integer)olabelwidth;
    create();
   }
  
  public void setCaption(String caption)
   {
     Caption="   "+(String)caption+"   ";
     
     labelWidth=Java_painter.canvas.textWidth(Caption);
     reCreateElements();    
//     labelWidth=40;
   }
   
     public void setBgcolor(int R, int G, int B)
   {
     defaultcolor=Java_painter.canvas.color(R, G, B);
     create();
   }
     public void setChecked()
   {    
     checked=true;
     om.executeEventMethod(om.windowobjects.get(oid), "_checked", new Object[] {});
     create();
   }
     public void setUnchecked()
   {
     checked=false;
     om.executeEventMethod(om.windowobjects.get(oid), "_unchecked", new Object[] {});
     create();
   }
   
  /*<END set Methoden>*/    
   
   
  /*<BEGIN get Methoden>*/ 
   String getCaption()
   {
     return Caption.substring(3,Caption.length()-3);
   }
    boolean getChecked()
   {
     return checked;
   }
    int getWidth()
   {
     return (int)(elementWidth+2+labelWidth);
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
    if (getChecked())
    {
      setUnchecked();
    }
    else
    {
      setChecked();
    }
  }
    /*<END fixe Eventereignisse>*/ 
}

