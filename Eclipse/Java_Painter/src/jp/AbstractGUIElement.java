package jp;

import java.util.HashMap;

/*EINE KLASSE VON DER JEDES GUIElement erben muss: */
abstract class AbstractGUIElement
{
   GUIElementsManager om=null;
   int oid=0;
     public String getElementType()
   {
     return this.getClass().getName().replace("$",".").substring(this.getClass().getName().replace("$",".").lastIndexOf('.')+1);
   }
   /*<BEGIN setObjectManager>*/
  public void setObjectManager(Object objmanager)
  {
    om=(GUIElementsManager)objmanager;
  }
/*<END setObjectManager>*/

/*<BEGIN setObjectId>*/
  public void setObjectId(Object objid)
  {
    oid=(Integer)objid;
  }
/*<END setObjectId>*/   

  public HashMap<Double,Integer> objectpositions = new HashMap<Double,Integer>();
  public void addElementPosition(int myX,int myY)
  {
      objectpositions.put((double)myX+((double)myY/1000000),oid);
  }
  public void reCreateElements()
  {
   try
      {
      om.reCreateEverything();
      }
      catch (Exception e)
      {
      }
  }
}
