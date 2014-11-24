package jp;
import processing.core.*;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedHashMap;
public class Java_painter extends PApplet {

	
	public static void main(String args[])
	{
	    PApplet.main("jp.Java_painter");
	}
	
	/*Für später: Alle Zeichenobjekte (ellipsen, rects und lines) als Objekte instanzieren, 
	statt mit den Methoden zu zeichnen. Diese objekte dann immer in eine Liste speichern. Das macht ein redraw 
	wahrscheinlich effizienter. Momentan ist redraw über das pixels[] Array gelöst
	class circ
	{
	  int x;
	  int y;
	  int rl;
	  int rt;
	  circ(int x1,int y1,int r1, int r2)
	  {
	    x=x1;
	    y=y1;
	    rl=r1;
	    rt=r2;
	    create();
	  }
	  public void create()
	 {
	   fill (0,0,0,100);
	   ellipse(x,y,rl,rt);
	 }
	}
	*/
	public void formstage_mouseDragged()
	{
	//  objman.reCreateEverything();
	}

	public void mainstage_mouseReleased()
	{
	//  objman.reCreateEverything();
	}


	int breite=1000;
	int hoehe=500;
	float drawcounter=0;
	int[] px1;
	//Vector<int[]> undoList=new Vector<int[]>();
	int undoMax=100;
	int undoCursor=-1;
	int hightestUndo=0;
	int[][] undoList=new int[undoMax][breite*hoehe];

	//int undoListCounter=-1; 

	GUIElementsManager objman;
	GUIElementsManager drawman;
	button yline;
	button xline;
	button crossline; 
	button xyline1;
	button xyline2;
	button pencil;
	button thickthinpencil;  
	button freeline;
	button growlines;
	button lineshot;
	button makerect;
	button makesquare;
	button makecircle;
	button makeellipse;
	button makerecteffect;
	button makesquareeffect;
	button makecircleeffect;
	button makeellipseeffect;
	button alphavalplus1;
	button alphavalplus10;
	button alphavalplus100;
	button alphavalminus1;
	button alphavalminus10;
	button alphavalminus100;
	button falphavalminus1;
	button falphavalminus10;
	button falphavalminus100;
	button falphavalplus1;
	button falphavalplus10;
	button falphavalplus100;
	button linethicknesplus;
	button linethicknesminus;
	textlabel alphavallabel;
	textlabel falphavallabel;
	textlabel linethicknes;

	textlabel actualColor;
	textlabel factualColor;
	textlabel colors;
	textlabel fcolors;

	button redminus1;
	button redminus10;
	button redminus100;
	button redplus1;
	button redplus10;
	button redplus100;
	textlabel redlabel;
	textlabel redlabelt;

	button greenminus1;
	button greenminus10;
	button greenminus100;
	button greenplus1;
	button greenplus10;
	button greenplus100;
	textlabel greenlabel;
	textlabel greenlabelt;

	button blueminus1;
	button blueminus10;
	button blueminus100;
	button blueplus1;
	button blueplus10;
	button blueplus100;
	textlabel bluelabel;
	textlabel bluelabelt;

	button fredminus1;
	button fredminus10;
	button fredminus100;
	button fredplus1;
	button fredplus10;
	button fredplus100;
	textlabel fredlabel;
	textlabel fredlabelt;

	button fgreenminus1;
	button fgreenminus10;
	button fgreenminus100;
	button fgreenplus1;
	button fgreenplus10;
	button fgreenplus100;
	textlabel fgreenlabel;
	textlabel fgreenlabelt;

	button fblueminus1;
	button fblueminus10;
	button fblueminus100;
	button fblueplus1;
	button fblueplus10;
	button fblueplus100;
	textlabel fbluelabel;
	textlabel fbluelabelt;

	button undo;
	button saveas;
	checkbox filledobject;

	/*
	checkbox addredcolor;
	checkbox addbluecolor;
	checkbox addgreencolor;
	*/
	stage mainstage;
	int drawmode=0;
	int redcolor=0;
	int bluecolor=0;
	int greencolor=0;
	int fredcolor=0;
	int fbluecolor=0;
	int fgreencolor=0;
	int alphacolor=0;
	int falphacolor=0;

	int lineweight=2;

	boolean drawNow=false;
	int mouseStartx=0;
	int mouseStarty=0;
	
	
	public static Java_painter canvas;
	//boolean stillclicked=false;


	public void mouseClicked()
	{
	 /*dem GUIElementsManager mitteilen, dass geklickt wurde, damit dieser entsprechend der Koordinaten checken kann ob auf eines seiner verwalteten GUI Elemente geklickt wurde und entsprechende
	 Eventmethoden aufruft*/
	 objman.mouseClick(mouseX,mouseY);
	 drawman.mouseClick(mouseX,mouseY);
	}
	public void mousePressed()
	{
	 /*dem GUIElementsManager mitteilen, dass geklickt wurde, damit dieser entsprechend der Koordinaten checken kann ob auf eines seiner verwalteten GUI Elemente geklickt wurde und entsprechende
	 Eventmethoden aufruft*/
	 objman.mousePress(mouseX,mouseY);
	 drawman.mousePress(mouseX,mouseY);
	}

	public void mouseReleased() {
	  if(drawNow && px1!=null)
	  {
	    //println("add");
	    // int[] temppx1=new int[px1.length];
	     undoCursor++;
	     if (undoCursor>=undoMax) undoCursor=0;
	     //highestUndo=undoCursor;
	     undoList[undoCursor]=new int[hoehe*breite];
	     java.lang.System.arraycopy(px1,0,undoList[undoCursor],0,px1.length);
	    // undoCursor++;
	     //undoListCounter++;
	  }
	  drawnow(false);
	  drawcounter=0;
	  
	  objman.reCreateEverything();
	 /*dem GUIElementsManager mitteilen, dass geklickt wurde, damit dieser entsprechend der Koordinaten checken kann ob auf eines seiner verwalteten GUI Elemente geklickt wurde und entsprechende
	 Eventmethoden aufruft*/
	 objman.mouseRelease(mouseX,mouseY);
	 drawman.mouseRelease(mouseX,mouseY);
	 
	}
	public void mouseDragged() {
	 /*dem GUIElementsManager mitteilen, dass geklickt wurde, damit dieser entsprechend der Koordinaten checken kann ob auf eines seiner verwalteten GUI Elemente geklickt wurde und entsprechende
	 Eventmethoden aufruft*/
	 objman.mouseDrag(mouseX,mouseY);
	 drawman.mouseDrag(mouseX,mouseY);

	}

	public void setup(){
	size(1000,500);
	//formstage=new stage(0,0,breite,hoehe);
	canvas=this;
	yline=new button(2,2," | senkrechte Linie");
	xline=new button(yline.getLeftPos()+yline.getWidth(),2," - waagrechte Linie");
	lineshot=new button(xline.getLeftPos()+xline.getWidth(),2," * Linienschuss");
	xyline1=new button(lineshot.getLeftPos()+lineshot.getWidth(),2," / 45° Linie");
	xyline2=new button(xyline1.getLeftPos()+xyline1.getWidth(),2," \\ 45° Linie");
	crossline=new button(xyline2.getLeftPos()+xyline2.getWidth(),2," X Crossline");
	pencil=new button(crossline.getLeftPos()+crossline.getWidth(),2," ~ Stift");
	thickthinpencil=new button(2,yline.getTopPos()+yline.getHeight()+2," Dick/Dünn-Stift");
	//thinthickpencil=new button(thickthinpencil.getLeftPos()+thickthinpencil.getWidth(),2," Dünn/Dick-Stift");
	freeline=new button(thickthinpencil.getLeftPos()+thickthinpencil.getWidth(),thickthinpencil.getTopPos()," Freie Linie");
	growlines=new button(freeline.getLeftPos()+freeline.getWidth(),freeline.getTopPos()," Wachsende Linien");
	linethicknesplus=new button(growlines.getLeftPos()+growlines.getWidth()+20,freeline.getTopPos()," + Liniendicke");
	linethicknesminus=new button(linethicknesplus.getLeftPos()+linethicknesplus.getWidth(),freeline.getTopPos()," - Liniendicke");
	linethicknes=new textlabel(linethicknesminus.getLeftPos()+linethicknesminus.getWidth(),freeline.getTopPos(),"000");
	linethicknes.setFixedWidth(50);

	/*alphavalplus=new button(linethicknes.getLeftPos()+linethicknes.getWidth()+40,freeline.getTopPos()," + Alphawert");
	alphavalminus=new button(alphavalplus.getLeftPos()+alphavalplus.getWidth(),freeline.getTopPos()," - Alphawert");
	alphavallabel=new textlabel(alphavalminus.getLeftPos()+alphavalminus.getWidth(),freeline.getTopPos()," ---");
	alphavallabel.setFixedWidth(50);*/

	makerect=new button(2,freeline.getTopPos()+freeline.getHeight()+2," Rechteck");
	makesquare=new button(makerect.getLeftPos()+makerect.getWidth(),makerect.getTopPos()," Quadrat");
	makecircle=new button(makesquare.getLeftPos()+makesquare.getWidth(),makerect.getTopPos()," Kreis");
	makeellipse=new button(makecircle.getLeftPos()+makecircle.getWidth(),makerect.getTopPos()," Ellipse");
	makerecteffect=new button(makeellipse.getLeftPos()+makeellipse.getWidth(),makerect.getTopPos()," Rechteck-Verschachtelung");
	makesquareeffect=new button(makerecteffect.getLeftPos()+makerecteffect.getWidth(),makerect.getTopPos()," Quadrat-Verschachtelung");
	makecircleeffect=new button(makesquareeffect.getLeftPos()+makesquareeffect.getWidth(),makerect.getTopPos()," Kreis-Verschachtelung");
	makeellipseeffect=new button(makecircleeffect.getLeftPos()+makecircleeffect.getWidth(),makerect.getTopPos()," Ellipsen-Verschachtelung");


	colors=new textlabel(2,makecircleeffect.getTopPos()+makecircleeffect.getHeight()+2,"Linienfarbe:");
	colors.setFixedWidth(130);
	actualColor=new textlabel(colors.getLeftPos()+colors.getWidth(),colors.getTopPos()," ");
	redlabelt=new textlabel(2,actualColor.getTopPos()+actualColor.getHeight()+2,"Rotwert:");
	redlabelt.setFixedWidth(130);
	redlabel=new textlabel(redlabelt.getLeftPos()+redlabelt.getWidth(),redlabelt.getTopPos()," ");
	redminus1=new button(redlabel.getLeftPos()+redlabel.getWidth(),redlabelt.getTopPos(),"-1");
	redminus10=new button(redminus1.getLeftPos()+redminus1.getWidth(),redlabelt.getTopPos(),"-10");
	redminus100=new button(redminus10.getLeftPos()+redminus10.getWidth(),redlabelt.getTopPos(),"-100");
	redplus1=new button(redminus100.getLeftPos()+redminus100.getWidth(),redlabelt.getTopPos(),"+1");
	redplus10=new button(redplus1.getLeftPos()+redplus1.getWidth(),redlabelt.getTopPos(),"+10");
	redplus100=new button(redplus10.getLeftPos()+redplus10.getWidth(),redlabelt.getTopPos(),"+100");
	greenlabelt=new textlabel(2,redplus100.getTopPos()+redplus100.getHeight()+2,"Grünwert:");
	greenlabelt.setFixedWidth(130);
	greenlabel=new textlabel(greenlabelt.getLeftPos()+greenlabelt.getWidth(),greenlabelt.getTopPos()," ");
	greenminus1=new button(greenlabel.getLeftPos()+greenlabel.getWidth(),greenlabelt.getTopPos(),"-1");
	greenminus10=new button(greenminus1.getLeftPos()+greenminus1.getWidth(),greenlabelt.getTopPos(),"-10");
	greenminus100=new button(greenminus10.getLeftPos()+greenminus10.getWidth(),greenlabelt.getTopPos(),"-100");
	greenplus1=new button(greenminus100.getLeftPos()+greenminus100.getWidth(),greenlabelt.getTopPos(),"+1");
	greenplus10=new button(greenplus1.getLeftPos()+greenplus1.getWidth(),greenlabelt.getTopPos(),"+10");
	greenplus100=new button(greenplus10.getLeftPos()+greenplus10.getWidth(),greenlabelt.getTopPos(),"+100");
	bluelabelt=new textlabel(2,greenplus100.getTopPos()+greenplus100.getHeight()+2,"Blauwert:");
	bluelabelt.setFixedWidth(130);
	bluelabel=new textlabel(bluelabelt.getLeftPos()+bluelabelt.getWidth(),bluelabelt.getTopPos()," ");
	blueminus1=new button(bluelabel.getLeftPos()+bluelabel.getWidth(),bluelabelt.getTopPos(),"-1");
	blueminus10=new button(blueminus1.getLeftPos()+blueminus1.getWidth(),bluelabelt.getTopPos(),"-10");
	blueminus100=new button(blueminus10.getLeftPos()+blueminus10.getWidth(),bluelabelt.getTopPos(),"-100");
	blueplus1=new button(blueminus100.getLeftPos()+blueminus100.getWidth(),bluelabelt.getTopPos(),"+1");
	blueplus10=new button(blueplus1.getLeftPos()+blueplus1.getWidth(),bluelabelt.getTopPos(),"+10");
	blueplus100=new button(blueplus10.getLeftPos()+blueplus10.getWidth(),bluelabelt.getTopPos(),"+100");
	alphavallabel=new textlabel(2,blueplus100.getTopPos()+blueplus100.getHeight()+2,"Transparenz: ");
	alphavallabel.setFixedWidth(130);
	alphavalminus1=new button(bluelabel.getLeftPos()+bluelabel.getWidth(),alphavallabel.getTopPos(),"-1");
	alphavalminus10=new button(alphavalminus1.getLeftPos()+alphavalminus1.getWidth(),alphavallabel.getTopPos(),"-10");
	alphavalminus100=new button(alphavalminus10.getLeftPos()+alphavalminus10.getWidth(),alphavallabel.getTopPos(),"-100");
	alphavalplus1=new button(alphavalminus100.getLeftPos()+alphavalminus100.getWidth(),alphavallabel.getTopPos(),"+1");
	alphavalplus10=new button(alphavalplus1.getLeftPos()+alphavalplus1.getWidth(),alphavallabel.getTopPos(),"+10");
	alphavalplus100=new button(alphavalplus10.getLeftPos()+alphavalplus10.getWidth(),alphavallabel.getTopPos(),"+100");


	fcolors=new textlabel(blueplus100.getLeftPos()+blueplus10.getWidth()+30,makecircleeffect.getTopPos()+makecircleeffect.getHeight()+2,"Füllfarbe:");
	fcolors.setFixedWidth(130);
	factualColor=new textlabel(fcolors.getLeftPos()+fcolors.getWidth(),colors.getTopPos()," ");
	fredlabelt=new textlabel(fcolors.getLeftPos(),actualColor.getTopPos()+actualColor.getHeight()+2,"Rotwert:");
	fredlabelt.setFixedWidth(130);
	fredlabel=new textlabel(fredlabelt.getLeftPos()+fredlabelt.getWidth(),redlabelt.getTopPos()," ");
	fredminus1=new button(fredlabel.getLeftPos()+fredlabel.getWidth(),redlabelt.getTopPos(),"-1");
	fredminus10=new button(fredminus1.getLeftPos()+fredminus1.getWidth(),redlabelt.getTopPos(),"-10");
	fredminus100=new button(fredminus10.getLeftPos()+fredminus10.getWidth(),redlabelt.getTopPos(),"-100");
	fredplus1=new button(fredminus100.getLeftPos()+fredminus100.getWidth(),redlabelt.getTopPos(),"+1");
	fredplus10=new button(fredplus1.getLeftPos()+fredplus1.getWidth(),redlabelt.getTopPos(),"+10");
	fredplus100=new button(fredplus10.getLeftPos()+fredplus10.getWidth(),redlabelt.getTopPos(),"+100");
	fgreenlabelt=new textlabel(fcolors.getLeftPos(),redplus100.getTopPos()+redplus100.getHeight()+2,"Grünwert:");
	fgreenlabelt.setFixedWidth(130);
	fgreenlabel=new textlabel(fgreenlabelt.getLeftPos()+fgreenlabelt.getWidth(),greenlabelt.getTopPos()," ");
	fgreenminus1=new button(fgreenlabel.getLeftPos()+fgreenlabel.getWidth(),greenlabelt.getTopPos(),"-1");
	fgreenminus10=new button(fgreenminus1.getLeftPos()+fgreenminus1.getWidth(),greenlabelt.getTopPos(),"-10");
	fgreenminus100=new button(fgreenminus10.getLeftPos()+fgreenminus10.getWidth(),greenlabelt.getTopPos(),"-100");
	fgreenplus1=new button(fgreenminus100.getLeftPos()+fgreenminus100.getWidth(),greenlabelt.getTopPos(),"+1");
	fgreenplus10=new button(fgreenplus1.getLeftPos()+fgreenplus1.getWidth(),greenlabelt.getTopPos(),"+10");
	fgreenplus100=new button(fgreenplus10.getLeftPos()+fgreenplus10.getWidth(),greenlabelt.getTopPos(),"+100");
	fbluelabelt=new textlabel(fcolors.getLeftPos(),greenplus100.getTopPos()+greenplus100.getHeight()+2,"Blauwert:");
	fbluelabelt.setFixedWidth(130);
	fbluelabel=new textlabel(fbluelabelt.getLeftPos()+fbluelabelt.getWidth(),bluelabelt.getTopPos()," ");
	fblueminus1=new button(fbluelabel.getLeftPos()+fbluelabel.getWidth(),bluelabelt.getTopPos(),"-1");
	fblueminus10=new button(fblueminus1.getLeftPos()+fblueminus1.getWidth(),bluelabelt.getTopPos(),"-10");
	fblueminus100=new button(fblueminus10.getLeftPos()+fblueminus10.getWidth(),bluelabelt.getTopPos(),"-100");
	fblueplus1=new button(fblueminus100.getLeftPos()+fblueminus100.getWidth(),bluelabelt.getTopPos(),"+1");
	fblueplus10=new button(fblueplus1.getLeftPos()+fblueplus1.getWidth(),bluelabelt.getTopPos(),"+10");
	fblueplus100=new button(fblueplus10.getLeftPos()+fblueplus10.getWidth(),bluelabelt.getTopPos(),"+100");
	falphavallabel=new textlabel(fcolors.getLeftPos(),fblueplus100.getTopPos()+fblueplus100.getHeight()+2,"Transparenz: ");
	falphavallabel.setFixedWidth(130);
	falphavalminus1=new button(fbluelabel.getLeftPos()+fbluelabel.getWidth(),falphavallabel.getTopPos(),"-1");
	falphavalminus10=new button(falphavalminus1.getLeftPos()+falphavalminus1.getWidth(),falphavallabel.getTopPos(),"-10");
	falphavalminus100=new button(falphavalminus10.getLeftPos()+falphavalminus10.getWidth(),falphavallabel.getTopPos(),"-100");
	falphavalplus1=new button(falphavalminus100.getLeftPos()+falphavalminus100.getWidth(),falphavallabel.getTopPos(),"+1");
	falphavalplus10=new button(falphavalplus1.getLeftPos()+falphavalplus1.getWidth(),falphavallabel.getTopPos(),"+10");
	falphavalplus100=new button(falphavalplus10.getLeftPos()+falphavalplus10.getWidth(),falphavallabel.getTopPos(),"+100");


	filledobject=new checkbox(2,falphavalplus100.getTopPos()+bluelabelt.getHeight()+2, "Formen (Vierecke/Kreise) mit Füllfarbe gefüllt zeichnen", false);
	undo=new button(filledobject.getLeftPos()+filledobject.getWidth()+14,filledobject.getTopPos(), "Rückgängig");
	//saveas=new button(undo.getLeftPos()+undo.getWidth()+2,filledobject.getTopPos(), "Speichern unter...");
	mainstage=new stage(0,0,0,0,false);

	objman=new GUIElementsManager(this, 0, 0, breite, filledobject.getTopPos()+filledobject.getHeight()+12);
	drawman=new GUIElementsManager(this, 0, objman.formstage.getHeight(), breite, hoehe-objman.formstage.getHeight());

	// mit button saveas: objman.addElements(yline,"yline",xline,"xline",lineshot,"lineshot",xyline1,"xyline1",xyline2,"xyline2",crossline,"crossline",pencil,"pencil",thickthinpencil,"thickthinpencil",filledobject,"filledobject",freeline,"freeline",makerect,"makerect",makesquare,"makesquare",makecircle,"makecircle",makeellipse,"makeellipse",makerecteffect,"makerecteffect",makesquareeffect,"makesquareeffect",makecircleeffect,"makecircleeffect",makeellipseeffect,"makeellipseeffect",growlines,"growlines",linethicknesplus,"linethicknesplus",linethicknesminus,"linethicknesminus",linethicknes,"linethicknes",colors,"colors",actualColor,"actualColor",redlabelt,"redlabelt",redlabel,"redlabel",redminus1,"redminus1",redminus10,"redminus10",redminus100,"redminus100",redplus1,"redplus1",redplus10,"redplus10",redplus100,"redplus100",greenlabelt,"greenlabelt",greenlabel,"greenlabel",greenminus1,"greenminus1",greenminus10,"greenminus10",greenminus100,"greenminus100",greenplus1,"greenplus1",greenplus10,"greenplus10",greenplus100,"greenplus100",bluelabelt,"bluelabelt",bluelabel,"bluelabel",blueminus1,"blueminus1",blueminus10,"blueminus10",blueminus100,"blueminus100",blueplus1,"blueplus1",blueplus10,"blueplus10",blueplus100,"blueplus100",fcolors,"fcolors",factualColor,"factualColor",fredlabelt,"fredlabelt",fredlabel,"fredlabel",fredminus1,"fredminus1",fredminus10,"fredminus10",fredminus100,"fredminus100",fredplus1,"fredplus1",fredplus10,"fredplus10",fredplus100,"fredplus100",fgreenlabelt,"fgreenlabelt",fgreenlabel,"fgreenlabel",fgreenminus1,"fgreenminus1",fgreenminus10,"fgreenminus10",fgreenminus100,"fgreenminus100",fgreenplus1,"fgreenplus1",fgreenplus10,"fgreenplus10",fgreenplus100,"fgreenplus100",fbluelabelt,"fbluelabelt",fbluelabel,"fbluelabel",fblueminus1,"fblueminus1",fblueminus10,"fblueminus10",fblueminus100,"fblueminus100",fblueplus1,"fblueplus1",fblueplus10,"fblueplus10",fblueplus100,"fblueplus100",falphavallabel,"falphavallabel",falphavalminus1,"falphavalminus1",falphavalminus10,"falphavalminus10",falphavalminus100,"falphavalminus100",falphavalplus1,"falphavalplus1",falphavalplus10,"falphavalplus10",falphavalplus100,"falphavalplus100",alphavallabel,"alphavallabel",alphavalminus1,"alphavalminus1",alphavalminus10,"alphavalminus10",alphavalminus100,"alphavalminus100",alphavalplus1,"alphavalplus1",alphavalplus10,"alphavalplus10",alphavalplus100,"alphavalplus100",undo,"undo",saveas,"saveas");
	objman.addElements(yline,"yline",xline,"xline",lineshot,"lineshot",xyline1,"xyline1",xyline2,"xyline2",crossline,"crossline",pencil,"pencil",thickthinpencil,"thickthinpencil",filledobject,"filledobject",freeline,"freeline",makerect,"makerect",makesquare,"makesquare",makecircle,"makecircle",makeellipse,"makeellipse",makerecteffect,"makerecteffect",makesquareeffect,"makesquareeffect",makecircleeffect,"makecircleeffect",makeellipseeffect,"makeellipseeffect",growlines,"growlines",linethicknesplus,"linethicknesplus",linethicknesminus,"linethicknesminus",linethicknes,"linethicknes",colors,"colors",actualColor,"actualColor",redlabelt,"redlabelt",redlabel,"redlabel",redminus1,"redminus1",redminus10,"redminus10",redminus100,"redminus100",redplus1,"redplus1",redplus10,"redplus10",redplus100,"redplus100",greenlabelt,"greenlabelt",greenlabel,"greenlabel",greenminus1,"greenminus1",greenminus10,"greenminus10",greenminus100,"greenminus100",greenplus1,"greenplus1",greenplus10,"greenplus10",greenplus100,"greenplus100",bluelabelt,"bluelabelt",bluelabel,"bluelabel",blueminus1,"blueminus1",blueminus10,"blueminus10",blueminus100,"blueminus100",blueplus1,"blueplus1",blueplus10,"blueplus10",blueplus100,"blueplus100",fcolors,"fcolors",factualColor,"factualColor",fredlabelt,"fredlabelt",fredlabel,"fredlabel",fredminus1,"fredminus1",fredminus10,"fredminus10",fredminus100,"fredminus100",fredplus1,"fredplus1",fredplus10,"fredplus10",fredplus100,"fredplus100",fgreenlabelt,"fgreenlabelt",fgreenlabel,"fgreenlabel",fgreenminus1,"fgreenminus1",fgreenminus10,"fgreenminus10",fgreenminus100,"fgreenminus100",fgreenplus1,"fgreenplus1",fgreenplus10,"fgreenplus10",fgreenplus100,"fgreenplus100",fbluelabelt,"fbluelabelt",fbluelabel,"fbluelabel",fblueminus1,"fblueminus1",fblueminus10,"fblueminus10",fblueminus100,"fblueminus100",fblueplus1,"fblueplus1",fblueplus10,"fblueplus10",fblueplus100,"fblueplus100",falphavallabel,"falphavallabel",falphavalminus1,"falphavalminus1",falphavalminus10,"falphavalminus10",falphavalminus100,"falphavalminus100",falphavalplus1,"falphavalplus1",falphavalplus10,"falphavalplus10",falphavalplus100,"falphavalplus100",alphavallabel,"alphavallabel",alphavalminus1,"alphavalminus1",alphavalminus10,"alphavalminus10",alphavalminus100,"alphavalminus100",alphavalplus1,"alphavalplus1",alphavalplus10,"alphavalplus10",alphavalplus100,"alphavalplus100",undo,"undo");
	drawman.addElements(mainstage,"mainstage");
	mainstage.setObjectpositions(2,drawman.formstage.getTopPos(),breite-4,hoehe-drawman.formstage.getTopPos()-2);
	objman.formstage.setBgcolor(230,230,230);
	drawman.formstage.setBgcolor(230,230,230);

	setButtoncolors("nothing");
	tgreen(0);
	tfgreen(255);
	tred(0);
	tfred(255);
	tblue(0);
	tfblue(255);
	actualizeLinecolor();
	actualizeFillcolor();
	lw(0);
	av(127);
	fav(127);
	makerecteffect_mouseClicked();
	for (int i=0; i< undoMax; i++) undoList[i]=null;
	   /* loadPixels();
	    px1=pixels;
	    java.lang.System.arraycopy(px1,0,undoList[undoCursor],0,px1.length);*/
	  frameRate(5200);
	}

	 public void drawnow(boolean setTo)
	 {
	   /* if (!setTo)
	   {  
	   color tempcolor;
	   tempcolor=g.strokeColor;
	   stroke(mainstage.getBgcolor());
	   point(mouseStartx,mouseStarty);
	   stroke(tempcolor);
	   
	   }
	   */
	   drawNow=setTo;
	 }


	 public void setButtoncolors(String clickedbuttonname)
	 {  
	   for(String entry : objman.namemap.keySet())
	   {
	     AbstractGUIElement tempAGE=objman.namemap.get(entry);
	     if (tempAGE.getElementType().equals("button"))
	     {
	       button tempbutton;
	       tempbutton=(button)tempAGE;
	       if (entry==clickedbuttonname)
	       {
	        tempbutton.setBgcolor(230,100,230);
	       }
	       else
	       {
	        tempbutton.setBgcolor(255,255,255);
	       }
	      
	     }
	       
	   }
	 }

	public void undo_mouseClicked()
	{
	  
	  //if (undoList[1]==null) println(11);
	 if(undoCursor>-1 && undoList[undoCursor]!=null)
	  {
	    //println((undoList.get(undoList.size()-1).length));
	    
	    java.lang.System.arraycopy((int[])(undoList[undoCursor]),0,pixels,0,hoehe*breite);
	    undoList[undoCursor]=null;
	    undoCursor--;
	    if (undoCursor<0) undoCursor=undoMax-1;
	   // pixels=undoList.get(undoList.size()-1);
	  
	      
	   
	   //undoListCounter--;
	    updatePixels();
	    objman.reCreateEverything();
	  }
	}

	public void saveas_mouseClicked()
	{
	PImage partialSave = get(mainstage.getLeftPos(),mainstage.getTopPos(),mainstage.getWidth(),mainstage.getHeight());
	partialSave.save("C:/temp/test.jpg");
	}


	public void lw(int oneplusorminus)
	{
	lineweight+=oneplusorminus;
	if (lineweight>12)
	{
	lineweight=12;
	}
	if (lineweight<1)
	{
	lineweight=1;
	}
	linethicknes.setCaption(String.valueOf(lineweight));
	}

	public void av(int oneplusorminus)
	{
	alphacolor+=oneplusorminus;
	if (alphacolor>255)
	{
	alphacolor=255;
	}
	if (alphacolor<0)
	{
	alphacolor=0;
	}
	alphavallabel.setCaption("Tranzsparenz: " + String.valueOf(alphacolor));
	}


	public void fav(int oneplusorminus)
	{
	falphacolor+=oneplusorminus;
	if (falphacolor>255)
	{
	falphacolor=255;
	}
	if (falphacolor<0)
	{
	falphacolor=0;
	}
	falphavallabel.setCaption("Tranzsparenz: " + String.valueOf(falphacolor));
	}


	public void linethicknesplus_mouseClicked()
	{
	  lw(1);
	}


	public void linethicknesminus_mouseClicked()
	{
	  lw(-1);
	}


	public void alphavalminus1_mouseClicked()
	{
	  av(-1);
	}
	public void alphavalplus1_mouseClicked()
	{
	  av(1);
	}
	public void alphavalminus10_mouseClicked()
	{
	  av(-10);
	}
	public void alphavalplus10_mouseClicked()
	{
	  av(10);
	}
	public void alphavalminus100_mouseClicked()
	{
	  av(-100);
	}
	public void alphavalplus100_mouseClicked()
	{
	  av(100);
	}

	public void falphavalminus1_mouseClicked()
	{
	  fav(-1);
	}
	public void falphavalplus1_mouseClicked()
	{
	  fav(1);
	}
	public void falphavalminus10_mouseClicked()
	{
	  fav(-10);
	}
	public void falphavalplus10_mouseClicked()
	{
	  fav(10);
	}
	public void falphavalminus100_mouseClicked()
	{
	  fav(-100);
	}
	public void falphavalplus100_mouseClicked()
	{
	  fav(100);
	}






	public void tgreen(int pom)
	{
	greencolor+=pom;
	if (greencolor>255)
	{
	greencolor=255;
	}
	if (greencolor<0)
	{
	greencolor=0;
	}
	greenlabel.setBgcolor(0,greencolor,0);
	greenlabelt.setCaption("Grünwert: " + String.valueOf(greencolor));
	actualizeLinecolor();
	}


	public void tfgreen(int pom)
	{
	fgreencolor+=pom;
	if (fgreencolor>255)
	{
	fgreencolor=255;
	}
	if (fgreencolor<0)
	{
	fgreencolor=0;
	}
	fgreenlabel.setBgcolor(0,fgreencolor,0);
	fgreenlabelt.setCaption("Grünwert: " + String.valueOf(fgreencolor));
	actualizeFillcolor();
	}



	public void tred(int pom)
	{
	redcolor+=pom;
	if (redcolor>255)
	{
	redcolor=255;
	}
	if (redcolor<0)
	{
	redcolor=0;
	}
	redlabel.setBgcolor(redcolor,0,0);
	redlabelt.setCaption("Rotwert: " + String.valueOf(redcolor));
	actualizeLinecolor();
	}


	public void tfred(int pom)
	{
	fredcolor+=pom;
	if (fredcolor>255)
	{
	fredcolor=255;
	}
	if (fredcolor<0)
	{
	fredcolor=0;
	}
	fredlabel.setBgcolor(fredcolor,0,0);
	fredlabelt.setCaption("Rotwert: " + String.valueOf(fredcolor));
	actualizeFillcolor();
	}



	public void tblue(int pom)
	{
	bluecolor+=pom;
	if (bluecolor>255)
	{
	bluecolor=255;
	}
	if (bluecolor<0)
	{
	bluecolor=0;
	}
	bluelabel.setBgcolor(0,0,bluecolor);
	bluelabelt.setCaption("Blauwert: " + String.valueOf(bluecolor));
	actualizeLinecolor();
	}


	public void tfblue(int pom)
	{
	fbluecolor+=pom;
	if (fbluecolor>255)
	{
	fbluecolor=255;
	}
	if (fbluecolor<0)
	{
	fbluecolor=0;
	}
	fbluelabel.setBgcolor(0,0,fbluecolor);
	fbluelabelt.setCaption("Blauwert: " + String.valueOf(fbluecolor));
	actualizeFillcolor();
	}

	public void actualizeLinecolor()
	{
	actualColor.setBgcolor(redcolor,greencolor,bluecolor);
	}
	public void actualizeFillcolor()
	{
	  factualColor.setBgcolor(fredcolor,fgreencolor,fbluecolor);
	}


	public void fgreenminus100_mouseClicked()
	{
	tfgreen(-100);
	}
	public void fgreenminus10_mouseClicked()
	{
	tfgreen(-10);
	}
	public void fgreenminus1_mouseClicked()
	{
	tfgreen(-1);
	}


	public void greenminus100_mouseClicked()
	{
	tgreen(-100);
	}
	public void greenminus10_mouseClicked()
	{
	tgreen(-10);
	}
	public void greenminus1_mouseClicked()
	{
	tgreen(-1);
	}



	public void fgreenplus100_mouseClicked()
	{
	tfgreen(100);
	}
	public void fgreenplus10_mouseClicked()
	{
	tfgreen(10);
	}
	public void fgreenplus1_mouseClicked()
	{
	tfgreen(1);
	}

	public void greenplus100_mouseClicked()
	{
	tgreen(100);
	}
	public void greenplus10_mouseClicked()
	{
	tgreen(10);
	}
	public void greenplus1_mouseClicked()
	{
	tgreen(1);
	}




	public void fredminus100_mouseClicked()
	{
	tfred(-100);
	}
	public void fredminus10_mouseClicked()
	{
	tfred(-10);
	}
	public void fredminus1_mouseClicked()
	{
	tfred(-1);
	}


	public void redminus100_mouseClicked()
	{
	tred(-100);
	}
	public void redminus10_mouseClicked()
	{
	tred(-10);
	}
	public void redminus1_mouseClicked()
	{
	tred(-1);
	}



	public void fredplus100_mouseClicked()
	{
	tfred(100);
	}
	public void fredplus10_mouseClicked()
	{
	tfred(10);
	}
	public void fredplus1_mouseClicked()
	{
	tfred(1);
	}

	public void redplus100_mouseClicked()
	{
	tred(100);
	}
	public void redplus10_mouseClicked()
	{
	tred(10);
	}
	public void redplus1_mouseClicked()
	{
	tred(1);
	}




	public void fblueminus100_mouseClicked()
	{
	tfblue(-100);
	}
	public void fblueminus10_mouseClicked()
	{
	tfblue(-10);
	}
	public void fblueminus1_mouseClicked()
	{
	tfblue(-1);
	}


	public void blueminus100_mouseClicked()
	{
	tblue(-100);
	}
	public void blueminus10_mouseClicked()
	{
	tblue(-10);
	}
	public void blueminus1_mouseClicked()
	{
	tblue(-1);
	}



	public void fblueplus100_mouseClicked()
	{
	tfblue(100);
	}
	public void fblueplus10_mouseClicked()
	{
	tfblue(10);
	}
	public void fblueplus1_mouseClicked()
	{
	tfblue(1);
	}

	public void blueplus100_mouseClicked()
	{
	tblue(100);
	}
	public void blueplus10_mouseClicked()
	{
	tblue(10);
	}
	public void blueplus1_mouseClicked()
	{
	tblue(1);
	} 

	 
	  public void mainstage_mousePressed()
	  {
	    drawnow(true);
	    loadPixels();
	    px1=pixels;
	    mouseStartx=mouseX;
	    mouseStarty=mouseY;
	  }

	 public void xline_mouseClicked()
	  {
	    drawmode=1;
	    drawnow(false);
	    setButtoncolors("xline");
	  } 
	 public void yline_mouseClicked()
	  {
	    drawmode=2;
	    drawnow(false);
	    setButtoncolors("yline");
	  } 
	  /*
	 public void xyline_mouseClicked()
	  {
	    drawmode=3;
	    drawnow(false);
	  } 
	  */
	 public void lineshot_mouseClicked()
	  {
	    drawmode=4;
	    drawnow(false);
	    setButtoncolors("lineshot");
	  }   
	  
	  public void xyline1_mouseClicked()
	  {
	    drawmode=5;
	    drawnow(false);
	    setButtoncolors("xyline1");
	  }   
	  public void xyline2_mouseClicked()
	  {
	    drawmode=6;
	    drawnow(false);
	    setButtoncolors("xyline2");
	  }   
	  public void crossline_mouseClicked()
	  {
	    drawmode=7;
	    drawnow(false);
	    setButtoncolors("crossline");
	  }   
	  public void pencil_mouseClicked()
	  {
	    drawmode=8;
	    drawnow(false);
	    setButtoncolors("pencil");
	  }   
	  public void thickthinpencil_mouseClicked()
	  {
	    drawmode=9;
	    drawnow(false);
	    setButtoncolors("thickthinpencil");
	  }   


	public void freeline_mouseClicked()
	{
	   drawmode=10;
	   drawnow(false);
	   setButtoncolors("freeline");
	}
	public void growlines_mouseClicked()
	{
	   drawmode=11;
	   drawnow(false);
	   setButtoncolors("growlines");
	}

	public void makerect_mouseClicked()
	{
	   drawmode=12;
	   drawnow(false);
	   setButtoncolors("makerect");
	}
	public void makesquare_mouseClicked()
	{
	   drawmode=13;
	   drawnow(false);
	   setButtoncolors("makesquare");
	}
	public void makecircle_mouseClicked()
	{
	   drawmode=14;
	   drawnow(false);
	   setButtoncolors("makecircle");
	}
	public void makeellipse_mouseClicked()
	{
	  drawmode=15;
	  drawnow(false);
	  setButtoncolors("makeellipse");
	}
	public void makerecteffect_mouseClicked()
	{
	  drawmode=16;
	  drawnow(false);
	  setButtoncolors("makerecteffect");
	}
	public void makesquareeffect_mouseClicked()
	{
	  drawmode=17;
	  drawnow(false);
	  setButtoncolors("makesquareeffect");
	}
	public void makecircleeffect_mouseClicked()
	{
	  drawmode=18;
	  drawnow(false);
	  setButtoncolors("makecircleeffect");
	} 
	public void makeellipseeffect_mouseClicked()
	{
	  drawmode=19;
	  drawnow(false);
	  setButtoncolors("makeellipseeffect");
	}

	public void redrawall()
	{
	  pixels=px1;
	   updatePixels();
	}





	  public void mainstage_mouseDragged()
	  {
	    
	    
	    if (drawNow)
	    {
	     
	      int tempstrokecolor=g.strokeColor;
	      int tempfillcolor=g.fillColor;
	      int tempstrokec=(int)g.strokeWeight;
	      stroke(redcolor,greencolor,bluecolor,255-alphacolor);
	      if (filledobject.getChecked())
	      {
	      fill(fredcolor,fgreencolor,fbluecolor,255-falphacolor);
	      }
	      else
	      {
	      fill(fredcolor,fgreencolor,fbluecolor,0);
	      }
	      strokeWeight(lineweight);
	      switch(drawmode)
	      {
	        case 1:
	        line(mouseStartx,mouseStarty,mouseX,mouseStarty);
	        break;
	        case 2:        
	        line(mouseStartx,mouseStarty,mouseStartx,mouseY);
	        break;
	        case 10:
	        redrawall();
	        line(mouseStartx,mouseStarty,mouseX,mouseY);
	        break;
	        /*
	        case 3:
	        tempcolor=g.strokeColor;
	        stroke(redcolor,greencolor,bluecolor);
	        if (mouseX>mouseStartx)
	        {
	        line(mouseStartx,mouseStarty,mouseStartx+(mouseStarty-mouseY),mouseY);
	        }
	        else
	        {
	        line(mouseStartx,mouseStarty,mouseStartx-(mouseStarty-mouseY),mouseY);
	        }
	        stroke(tempcolor);
	        break;
	        */
	        case 4:
	        line(mouseStartx,mouseStarty,mouseX,mouseY);
	        break;
	        case 5:
	        redrawall();
	        line(mouseStartx,mouseStarty,mouseStartx-drawcounter,mouseStarty+drawcounter);
	        drawcounter++;
	        break;
	        case 6:
	        redrawall();
	        line(mouseStartx,mouseStarty,mouseStartx+drawcounter,mouseStarty+drawcounter);
	        drawcounter++;
	        break;
	        case 7:
	        line(mouseStartx,mouseStarty,mouseStartx+drawcounter,mouseStarty+drawcounter);
	        line(mouseStartx,mouseStarty,mouseStartx+drawcounter,mouseStarty-drawcounter);
	        line(mouseStartx,mouseStarty,mouseStartx-drawcounter,mouseStarty+drawcounter);
	        line(mouseStartx,mouseStarty,mouseStartx-drawcounter,mouseStarty-drawcounter);
	        drawcounter++;
	        break;
	        case 8:
	        line(mouseX,mouseY,pmouseX,pmouseY);
	        break;
	        case 9:
	        strokeWeight(drawcounter);
	        if(drawcounter<25)
	        {
	        drawcounter++;
	        }
	        line(mouseX,mouseY,pmouseX,pmouseY);
	        break;
	        case 11:
	        drawcounter+=0.3;
	        line(mouseStartx+drawcounter,mouseStarty+drawcounter,pmouseX,pmouseY);
	        break;
	        
	        case 12:
	        redrawall();
	        rect(mouseStartx,mouseStarty,abs(mouseStartx-mouseX),abs(mouseStarty-mouseY));
	        break;
	        case 13:
	        redrawall();
	        rect(mouseStartx,mouseStarty,abs(mouseStartx-mouseX),abs(mouseStartx-mouseX));
	        break;
	        case 14:
	        redrawall();
	        ellipse(mouseStartx,mouseStarty,abs(mouseStartx-mouseX),abs(mouseStartx-mouseX));
	        break;
	        case 15:
	        redrawall();
	        ellipse(mouseStartx,mouseStarty,abs(mouseStartx-mouseX),abs(mouseStarty-mouseY));
	        break;
	        
	        case 16:
	        
	        rect(mouseStartx,mouseStarty,abs(mouseStartx-mouseX),abs(mouseStarty-mouseY));
	        break;
	        case 17:
	        
	        rect(mouseStartx,mouseStarty,abs(mouseStartx-mouseX),abs(mouseStartx-mouseX));
	        break;
	        case 18:
	        
	        ellipse(mouseStartx,mouseStarty,abs(mouseStartx-mouseX),abs(mouseStartx-mouseX));
	        break;
	        case 19:
	        
	        ellipse(mouseStartx,mouseStarty,abs(mouseStartx-mouseX),abs(mouseStarty-mouseY));
	        break;

	      }
	      stroke(tempstrokecolor);
	      fill(tempfillcolor);
	      strokeWeight(tempstrokec);
//	      drawnow(false);
	    }
	/*    else
	    {
	      color tempcolor;
	      mouseStartx=mouseX;
	      mouseStarty=mouseY;
	      tempcolor=g.strokeColor;
	      stroke(redcolor,greencolor,bluecolor);
	      point(mouseStartx,mouseStarty);
	      stroke(tempcolor);
	      drawnow(true);
	    }*/
	  }
	  
	  
	public void draw()
	{
	 // objman.formstage.create();
	}


}







