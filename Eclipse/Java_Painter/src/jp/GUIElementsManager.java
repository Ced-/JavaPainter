package jp;

import java.util.HashMap;
import java.util.LinkedHashMap;

import jp.AbstractGUIElement;
import jp.stage;

/*Selbstprogrammiertes System für Graphical Userinterface-Objekte inklusive Eventhandling Abwicklung
Besteht aus einer Klasse "GUIElementsManager", von der eine Instanz erzeugt weden muss, wenn man dieses System nutzen will.
GUIElementsManager managt alle 
Er besitzt einen Konstruktor, der als Übergabeparameter die Referenz zu dem Objekt erwartet, von dem er instanziert wird (also eigentlich immer - this -)
*/

/*class GUIElementsManager*/
class GUIElementsManager
{  
  public HashMap<Double,Integer> objectpositions = new HashMap<Double,Integer>();
  public HashMap<Integer,String> windowobjects = new HashMap<Integer, String>();
  public LinkedHashMap<String,AbstractGUIElement> namemap = new   LinkedHashMap<String, AbstractGUIElement>();
  private int objectcounter = 0;
  public boolean showsystemerrors=false;
  public Object caller;
  public stage formstage;
  
GUIElementsManager(Object Caller,int formStageX, int formStageY, int formStageWidth, int formStageHeight, boolean deadelement)
{
  caller=Caller;
  formstage=new stage(formStageX, formStageY, formStageWidth, formStageHeight, deadelement);
  addElement(formstage,"formstage");
  executeElementMethod("formstage", "create");
  regObjectpositions(namemap.get("formstage"));
}

GUIElementsManager(Object Caller,int formStageX, int formStageY, int formStageWidth, int formStageHeight)
{
  caller=Caller;
  formstage=new stage(formStageX, formStageY, formStageWidth, formStageHeight,true);
  addElement(formstage,"formstage");
  executeElementMethod("formstage", "create");
  regObjectpositions(namemap.get("formstage"));
}  
 
 
  public void reCreateEverything()
  {
   objectpositions.clear();
    for(String entry : namemap.keySet())
   {
     namemap.get(entry).objectpositions.clear();
     executeElementMethod(entry, "create");
     regObjectpositions(namemap.get(entry));
   }
 }
 
 public void regObjectpositions(AbstractGUIElement AGE)
 {
   for(double entry : AGE.objectpositions.keySet())
   {
          objectpositions.put(entry,AGE.oid);
   }
   
 }


 public void addElements(Object... oargs)  
  {
    for (int i=0; i<oargs.length-1; i=i+2)
    {
    addElement((AbstractGUIElement)oargs[i],(String)oargs[i+1]);
    }
    reCreateEverything();
  }  
  
 public void addElement(AbstractGUIElement newelement, String elementname)
  {
    try{
    int objid=objectcounter;
    windowobjects.put(objid,elementname);    
    namemap.put(elementname,newelement);
    executeElementMethod(elementname, "setObjectId", new Object[] {objid});
    executeElementMethod(elementname, "setObjectManager", new Object[] {this});
   //executeElementMethod(elementname, "create", new Object[] {});
    //regObjectpositions(namemap.get(elementname));

    objectcounter++;
    }
     catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public Object extraLongMethodName_UseJavaReflectionToCallMethodsByGivenStringName(String methodname, Object[] args, Object that)
  {
    Object returnvalue=null;
    try
    {
    Class[] parameterTypes = new Class[args.length];
    for (int i=0; i<parameterTypes.length;i++)
      { 
        if (args[i] instanceof Object[])
        {
          parameterTypes[i]=Object[].class;
        }
        else
        {
          parameterTypes[i]=Object.class;
        }
      }
    Class thisobjectsclass = Class.forName(that.getClass().getName()); 
    java.lang.reflect.Method method = thisobjectsclass.getMethod(methodname, parameterTypes);
    returnvalue= method.invoke(that, args);
    }
   catch (Exception e)
    {
      if(showsystemerrors)
      {
        e.printStackTrace();
      }
    }
    return returnvalue;
  }
  
  public Object executeElementMethod(String elementname, String methodname)
  {
    Object returnvalue=null;

    returnvalue=extraLongMethodName_UseJavaReflectionToCallMethodsByGivenStringName(methodname, new Object[] {}, namemap.get(elementname));  

    return returnvalue;
  }
   
  public Object executeElementMethod(String elementname, String methodname, Object[] args)
  {
    Object returnvalue=null;

    returnvalue=extraLongMethodName_UseJavaReflectionToCallMethodsByGivenStringName(methodname, args, namemap.get(elementname));  

    return returnvalue;
  }
  
public void executeEventMethod(String elementname, String methodname, Object[] args)
  {
    /*Zunächst: Gibt es bei diesem Element irgendeine fixe Sache die zu diesem Event ausgeführt wird (z.B. bei Button kurzer Farbwechsel wenn geklickt, oder bei Checkbox check wenn geklickt*/
    executeElementMethod(elementname, methodname, args);
    
    /*Dann: gibt es etwas das anwendungsspezifisch zu diesem Element bei diesem Event implementiert wurde? (z.B. spezielle Aktion wenn auf einen Button geklickt wurde.*/
    extraLongMethodName_UseJavaReflectionToCallMethodsByGivenStringName(elementname + methodname, args, caller);

    //executeElementMethod(elementname, "create", new Object[] {});    
  }
 
 
 
 
 
 
 
   /*EVENTHANDLING FÜR Elemente DES GUIElementManagers*/
  
  /*1. MOUSECLICK_EVENTS*/

  public void mouseClick(int X, int Y)
  {
    if(objectpositions.get((double)X+((double)Y/1000000))!=null)
    {
      String objname=windowobjects.get(objectpositions.get((double)X+((double)Y/1000000)));
      executeEventMethod(objname, "_mouseClicked", new Object[] {});
    }
  }
  
  public void mousePress(int X, int Y)
  {
    if(objectpositions.get((double)X+((double)Y/1000000))!=null)
    {
      String objname=windowobjects.get(objectpositions.get((double)X+((double)Y/1000000)));
      executeEventMethod(objname, "_mousePressed", new Object[] {});
    }
  }
  public void mouseRelease(int X, int Y)
  {
    if(objectpositions.get((double)X+((double)Y/1000000))!=null)
    {
      String objname=windowobjects.get(objectpositions.get((double)X+((double)Y/1000000)));
      executeEventMethod(objname, "_mouseReleased", new Object[] {});
    }
  }
 
   public void mouseDrag(int X, int Y)
  {
    if(objectpositions.get((double)X+((double)Y/1000000))!=null)
    {
      String objname=windowobjects.get(objectpositions.get((double)X+((double)Y/1000000)));
      executeEventMethod(objname, "_mouseDragged", new Object[] {});
    }
  }
  /*ENDE MOUSECLICK_EVENTS*/
  
  /*ENDE EVENTHANDLING*/
}
