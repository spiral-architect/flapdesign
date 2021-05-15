import java.awt.*;

public class Flapper extends java.applet.Applet
{
	//declare GUI objects
	TextField Radius1, Arm, Angle1, AngleBend, Width, Height;
	TextField Lever, Offset, Conrod;
	Button display, animate, plot, phoenix, timbird, liliggle;
	String items[]={"inches 100%","inches 50%","cm 100%","cm 50%"};
	Choice choice=new Choice();
	//declare input variables
	float radius1, radius2, anglebend, arm, armx, army, angle1, angle2, lever, width;
	float height, offset, conrod, mxc, myc;
	//declare constants
	final double radian = 57.29577951;
	//declare output variables
	float crank1x, crank1y, crank2x, crank2y, lever1x, lever1y, lever2x, lever2y;
	float wing1x, wing1y, wing2x, wing2y;
	int lockangle1, lockangle2, wingangle1, wingangle2, crankpos, stopat;
	int plotv = 175;
	//initial scale of graphical display
	float sc = 72;

	String animation="load";

	public void init()
	{  
		setBackground(Color.white);

		setLayout(new FlowLayout(FlowLayout.LEFT,0,3));
		setFont(new Font("Helvetica", Font.PLAIN, 10));
		
		Radius1 = new TextField (4);
		Arm = new TextField (4);
		//To specify value use Radius.setText("0");
		Angle1 = new TextField (4);
		AngleBend = new TextField (4);
		Width = new TextField (4);
		Height = new TextField (4);
		Lever = new TextField (4);
		Offset = new TextField (4);
		Conrod = new TextField (4);

		display = new Button ("Display mechanism");
		animate = new Button ("Animate");
		plot = new Button ("Plot");
		phoenix = new Button ("Phoenix");
		timbird = new Button ("Tim Bird");
		liliggle = new Button ("Lil'Iggle");

		Label label1 = new Label("Crank 1 radius:");
		Label label1b = new Label("      Crank 2 arm:");
		Label label2 = new Label("      Crank 1 angle:");
		Label label3 = new Label("      Crank 2 bend angle:");
		Label label4 = new Label("Width:");
		Label label5 = new Label("      Height:");
		Label label6 = new Label("      Lever:");
		Label label7 = new Label("      Spar offset:");
		Label label8 = new Label("      Conrod:");
		Label label9 = new Label("      Examples:");

		add(label1);
		add(Radius1);
		add(label1b);
		add(Arm);
		add(label2);
		add(Angle1);
		add(label3);
		add(AngleBend);
		add(label4);
		add(Width);
		add(label5);
		add(Height);
		add(label6);
		add(Lever);
		add(label7);
		add(Offset);
		add(label8);
		add(Conrod);
		add(display);
		add(animate);
		add(plot);
		for (int i=0;i<items.length;i++)
		{
			choice.addItem(items[i]);
		}
		add(choice);
		add(label9);
		add(phoenix);
		add(timbird);
		add(liliggle);
	}


	public boolean mouseDown(Event mouseevt, int mx, int my)
	{
		mxc=mx;
		myc=my;
		usemouse();
		return true;
	}


	public boolean mouseDrag(Event mouseevt, int mx, int my)
	{
		mxc=mx;
		myc=my;
		usemouse();
		return true;
	}


	public boolean action(Event event, Object arg)
	{

		if (event.target instanceof Choice)
		{
			if (arg=="inches 100%") {sc = 72F;}
			else if (arg=="inches 50%") {sc = 36F;}
			else if (arg=="cm 100%") {sc = 28.3464F;}
			else if (arg=="cm 50%") {sc = 14.1732F;}
			repaint();
		}

		if (event.target==display)
		{
			retrieve();
			animation="off";
			repaint();
		}

		else if (event.target==animate)
		{
			retrieve();
			animation="on";
			repaint();
		}


		else if (event.target==plot)
		{
			retrieve();
			animation="plot";
			repaint();
		}

		else if (event.target==phoenix)
		{
		Radius1.setText("0.375");
		Arm.setText("0.25");
		Angle1.setText("90");
		AngleBend.setText("110");
		Width.setText("0.0625");
		Height.setText("1.50");
		Lever.setText("0.875");
		Offset.setText("0");
		Conrod.setText("1.80");
		
		Radius1.requestFocus();
		Arm.requestFocus();
		Angle1.requestFocus();
		AngleBend.requestFocus();
		Width.requestFocus();
		Height.requestFocus();
		Lever.requestFocus();
		Offset.requestFocus();
		Conrod.requestFocus();

		retrieve();
		animation="off";
		repaint();
		}

		else if (event.target==liliggle)
		{
		Radius1.setText(".375");
		Arm.setText(".75");
		Angle1.setText("45");
		AngleBend.setText("180");
		Width.setText("4");
		Height.setText(".6875");
		Lever.setText(".5");
		Offset.setText("85");
		Conrod.setText("2.125");
		
		Radius1.requestFocus();
		Arm.requestFocus();
		Angle1.requestFocus();
		AngleBend.requestFocus();
		Width.requestFocus();
		Height.requestFocus();
		Lever.requestFocus();
		Offset.requestFocus();
		Conrod.requestFocus();

		retrieve();
		animation="off";
		repaint();
		}

		else if (event.target==timbird)
		{
		Radius1.setText("0.335");
		Arm.setText("0");
		Angle1.setText("65");
		AngleBend.setText("0");
		Width.setText("-1.23");
		Height.setText(".709");
		Lever.setText(".571");
		Offset.setText("158.5");
		Conrod.setText(".756");
		
		Radius1.requestFocus();
		Arm.requestFocus();
		Angle1.requestFocus();
		AngleBend.requestFocus();
		Width.requestFocus();
		Height.requestFocus();
		Lever.requestFocus();
		Offset.requestFocus();
		Conrod.requestFocus();

		retrieve();
		animation="off";
		repaint();
		}
		return true;
	}

	public void update(Graphics g)   
	{  
		paint(g);  
	}


	public void paint(Graphics g)
	{	

	if (animation != "load")
	{
		g.setColor(Color.white);
		g.fillRect(0,0,600,360);

		g.setFont(new Font("Helvetica", Font.PLAIN, 10));
		Color col1 = new Color(255,0,0);
		Color col2 = new Color(0,0,255);
		Color col1b = new Color(255,200,200);
		Color col2b = new Color(200,200,255);
		Color purple = new Color(200,0,255);

		if (animation=="off")
		{
		stopat = 1;
		}
		else
		{
			stopat = 361;
			if (animation=="plot")  //draw the background grid for the plot
			{
				g.translate(300,plotv);

				g.setColor(Color.lightGray);
				g.drawLine(-180, 30, 180, 30);
				g.drawLine(-180, 45, 180, 45);
				g.drawLine(-180, 60, 180, 60);
				g.drawLine(-180, -30, 180, -30);
				g.drawLine(-180, -45, 180, -45);
				g.drawLine(-180, -60, 180, -60);
				g.drawLine(-180, 120, 180, 120);
				g.drawLine(-180, 135, 180, 135);
				g.drawLine(-180, 150, 180, 150);

				g.drawLine(-90, -90, -90, 180);
				g.drawLine(0, -90, 0, 180);
				g.drawLine(90, -90, 90, 180);

				g.setColor(Color.black);
				g.drawLine(-185, 0, 185, 0);
				g.drawLine(-185, 90, 185, 90);
				g.drawLine(-185, -90, 180, -90);
				g.drawLine(-180, 180, 185, 180);

				g.drawLine(-180, -90, -180, 180);
				g.drawLine(180, -90, 180, 180);

				g.drawString("+90",-205,-86);
				g.drawString("0",-193,4);
				g.drawString("-90",-202,94);
				g.drawString("Wing Angle",-272,4);

				g.drawString("0",189,4);
				g.drawString("90",189,94);
				g.drawString("180",189,184);
				g.drawString("Conrod Angle",222,94);

				g.translate(-300,-plotv);
				
			}
		}

		for (int counter=1; counter<=stopat; counter++)
		{
		calc();

		String str1 = String.valueOf(wingangle1);
		String str2 = String.valueOf(wingangle2);
		String str1b = String.valueOf(lockangle1);
		String str2b = String.valueOf(lockangle2);

		//flapping angle plot
		if (animation=="plot")
		{
		if (angle1<-270)
		{
		angle1 = angle1 + 360;
		}
		crankpos = -90 - (int) (angle1);
		if (crankpos<180)
		{
			g.translate(300,plotv);
			g.setColor(col2b);
			g.drawLine(crankpos, lockangle2, crankpos, lockangle2);
			g.setColor(col1b);
			g.drawLine(crankpos, lockangle1, crankpos, lockangle1);
			g.setColor(col2);
			g.drawLine(crankpos, -wingangle2, crankpos, -wingangle2);
			g.setColor(col1);
			g.drawLine(crankpos, -wingangle1, crankpos, -wingangle1);
			g.translate(-300,-plotv);
		}
		}

		//graphical output for animation
		else
		{
		g.translate(300,290);
		g.setColor(Color.white);
		g.fillRect(-300,-290,600,360);

		//draw posterior shared crank arm
		g.setColor(Color.lightGray);
		g.drawOval(-2,-2,4,4);
		g.setColor(purple);
		g.drawLine(0,0, (int) (crank1x * sc), (int) (-crank1y * sc));

		//draw conrods
		g.setColor(col1b);
		g.drawLine((int) (crank1x * sc), (int) (-crank1y * sc), (int) (lever1x * sc), (int) (lever1y * sc));
		g.setColor(col2b);
		g.drawLine((int) (crank2x * sc), (int) (-crank2y * sc), (int) (lever2x * sc), (int) (lever2y * sc));

		//draw secondary crank arm
		g.setColor(col2);
		g.drawLine((int) (crank1x * sc), (int) (-crank1y * sc), (int) (crank2x * sc), (int) (-crank2y * sc));

		//draw levers
		g.setColor(col1);
		g.drawLine((int) (.5 * width * sc), (int) (-1 * height * sc), (int) (lever1x * sc), (int) (lever1y * sc));
		g.setColor(col2);
		g.drawLine((int) (-.5 * width * sc), (int) (-1 * height * sc), (int) (lever2x * sc), (int) (lever2y * sc));

		//draw wings
		g.setColor(col1);
		g.drawLine((int) (.5 * width * sc), (int) (-1 * height * sc), (int) (wing1x * sc), (int) (wing1y * sc));
		g.setColor(col2);
		g.drawLine((int) (-.5 * width * sc), (int) (-1 * height * sc), (int) (wing2x * sc), (int) (wing2y * sc));

		//text output for animation
		g.setColor(col1b);
		g.drawString(str1b,(int) (lever1x * sc) - 5, (int) (lever1y * sc) - 5);
		g.setColor(col2b);
		g.drawString(str2b,(int) (lever2x * sc) - 5, (int) (lever2y * sc) - 5);
		g.setColor(col1);
		g.drawString(str1,(int) (.5 * width * sc) +3, (int) (-1 * height * sc) - 10);
		g.setColor(col2);
		g.drawString(str2,(int) (-.5 * width * sc) -15, (int) (-1 * height * sc) - 10);

		g.translate(-300,-290);

		if (counter==stopat)
		{animation="off";}

		//time delay
 		try {Thread.sleep(10);}  
		catch (InterruptedException e) {}
		}

		angle1 = angle1 - 1F;
		angle2 = angle2 - 1F;
 
		}
		}
	}

	void retrieve()
	{
			//Convert text inputs to float values

			String text=Radius1.getText();
			Float value=Float.valueOf(text);
			radius1=value.floatValue();

			try
			{
				text=Arm.getText();
				value=Float.valueOf(text);
				arm=value.floatValue();
			}
			catch (NumberFormatException e)
			{
				arm=0;
			}

			text=Angle1.getText();
			value=Float.valueOf(text);
			angle1=value.floatValue();
			angle1=90-angle1;

			try
			{
				text=AngleBend.getText();
				value=Float.valueOf(text);
				anglebend=value.floatValue();
			}
			catch (NumberFormatException e)
			{
				anglebend=0;
			}

			text=Lever.getText();
			value=Float.valueOf(text);
			lever=value.floatValue();

			text=Width.getText();
			value=Float.valueOf(text);
			width=value.floatValue();

			text=Height.getText();
			value=Float.valueOf(text);
			height=value.floatValue();

			text=Offset.getText();
			value=Float.valueOf(text);
			offset=value.floatValue();
			offset=180-offset;

			text=Conrod.getText();
			value=Float.valueOf(text);
			conrod=value.floatValue();



			if (arm==0)
			{
			angle2=angle1;
			radius2=radius1;
			}
			else
			{
			anglebend=-1*(180-anglebend);
			armx= arm * (float) Math.cos(anglebend/radian);
			army= arm * (float) Math.sin(anglebend/radian);
			radius2 = (float) Math.sqrt(army*army+(radius1-armx)*(radius1-armx));
			angle2= (float) Math.atan((radius1-armx)/army);
			angle2= 90 + (angle2 * (float) radian);
			angle2= angle2+angle1;
 			if (army>0) {angle2=angle2-180;}
			}
	}

	void calc()
	{
			//formulas begin here
			//calculate crankcenter to shoulder
			float strut = (float) Math.sqrt(Math.pow((width/2),2)+Math.pow(height,2));			
			//calculate x and y crankarm coordinates
			crank1x= radius1 * (float) Math.cos(angle1/radian);
			crank1y= radius1 * (float) Math.sin(angle1/radian);
			crank2x= radius2 * (float) Math.cos(angle2/radian);
			crank2y= radius2 * (float) Math.sin(angle2/radian);
			//calculate crankarm-to-shoulder distance for right 1 and left 2 wings
			float d1= (float) Math.sqrt(Math.pow((crank1x-width/2),2)+Math.pow(height-crank1y,2));
			float d2= (float) Math.sqrt(Math.pow((width/2+crank2x),2)+Math.pow(height-crank2y,2));

			//calculate crankarm-to-shoulder angles
			float A1= (float) Math.atan((crank1x-width/2)/(height-crank1y));
			if (crank1y>height)
			{A1= (float) (90/radian) + (float) Math.atan((crank1y-height)/(crank1x-width/2));}
			float A2= (float) Math.atan((-width/2-crank2x)/(height-crank2y));
			if (crank2y>height)
			{A2= (float) (90/radian) + (float) Math.atan((crank2y-height)/(-width/2-crank2x));}

			//calculate lever angles (in radians)
			float B1= (float) Math.acos((lever*lever+d1*d1-conrod*conrod)/(2*d1*lever));
			float B2= (float) Math.acos((lever*lever+d2*d2-conrod*conrod)/(2*d2*lever));
			float C1 = A1 + B1;
			float C2 = A2 + B2;
			//calculate lever-conrod joint coordinates
			lever1x= lever * (float) Math.cos(C1-90/radian) + width/2;
			lever1y= -lever * (float) Math.sin(C1-90/radian) - height;
			lever2x= -width/2 - lever * (float) Math.cos(C2-90/radian);
			lever2y= -lever * (float) Math.sin(C2-90/radian) - height;
			//calculate pseudo-wingtip coordinates
			wing1x= lever * 2 * (float) Math.cos(C1-(270+offset)/radian) + width/2;
			wing1y= -lever * 2 * (float) Math.sin(C1-(270+offset)/radian) - height;
			wing2x= -width/2 - lever * 2 * (float) Math.cos(C2-(270+offset)/radian);
			wing2y= -lever * 2 * (float) Math.sin(C2-(270+offset)/radian) - height;	
			//calculate lockup angles
			double lockangle1temp= Math.acos((conrod*conrod+lever*lever-d1*d1)/(2*conrod*lever));
			double lockangle2temp= Math.acos((conrod*conrod+lever*lever-d2*d2)/(2*conrod*lever));
			lockangle1= (int) (lockangle1temp * radian);
			lockangle2= (int) (lockangle2temp * radian);
			//calculate wing angles
			wingangle1= (int) ((90+offset)-C1*radian);
			wingangle2= (int) ((90+offset)-C2*radian);
			if (width>0)
			{
				wingangle1 = 180-wingangle1;
				wingangle2 = 180-wingangle2;
			}
	}

	void usemouse ()
	{
		if (animation != "plot")
		{
		angle1=90+((float) ((Math.atan((290-myc)/(300-mxc))) * (radian)));
		int angleint= (int) angle1;
		if (mxc<301)
		{angleint=angleint+180;}
		String mouseangle = String.valueOf(angleint);
		Angle1.setText(mouseangle);
		Angle1.requestFocus();
		retrieve();
		animation="off";
		repaint();
		}
	}

}
