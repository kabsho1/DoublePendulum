import processing.core.PApplet;
import processing.core.PGraphics;

public class DrawingSurface extends PApplet {
	private float l1 = 150;
	private float l2 = 150/2;
	private float m1 = 40;
	private float m2 = 40;
	private float theta1 = PI/2 + random(-PI/8, PI/8);
	private float theta2 = PI/2 + random(-PI/8, PI/8);
	private float om1 = 0; // omega
	private float om2 = 0;
	private float al1;// = 0.00001f; // alpha
	private float al2;// = -0.0001f;
	private float gravity = 1f;
	private float px2, py2 = -1;
	
	private float tranx, trany;
	private PGraphics pg;

	public void settings() {
		size(800, 800);
	}

	public void setup() {
		surface.setResizable(true);
		stroke(3, 219, 252);
		strokeWeight(3);
		fill(3, 219, 252);
		pg = createGraphics(width, height);
		pg.beginDraw();
		pg.background(0);
		pg.endDraw();

	}

	public void draw() {
		tranx = width/2;
		trany = height/3;
		
		float x1 = l1 * sin(theta1);
		float y1 = l1 * cos(theta1);

		float x2 = x1 + l2 * sin(theta2);
		float y2 = y1 + l2 * cos(theta2);
		
		
		pg.beginDraw();
		pg.translate(tranx, trany);
		pg.stroke(3, 219, 252, 75);
		pg.strokeWeight(4);
		if(py2 != -1)
			pg.line(px2,py2,x2,y2);

		pg.endDraw();
		image(pg, 0, 0);

		//background(0);
		translate(tranx, trany);


		line(0, 0, x1, y1);
		ellipse(x1, y1, m1/2, m1/2);

		line(x1, y1, x2, y2);
		ellipse(x2, y2, m2/2, m2/2);

		px2 = x2;
		py2 = y2;
		
		update();
	}
	
	private void update() {
		float num1 = -gravity * (2*m1 + m2) * sin(theta1);
		float num2 = -m2 * gravity * sin(theta1 - 2*theta2);
		float num3 = -2 * sin(theta1 - theta2) * m2 * (om2*om2 * l2 + om1*om1 * l1 * cos(theta1 - theta2));
		float den = l1 * (2*m1 + m2 - m2 * cos(2*theta1 - 2*theta2));
		al1 = (num1 + num2 + num3)/den;
		
		num1 = 2*sin(theta1 - theta2);
		num2 = om1*om1 * l1 * (m1 + m2) + gravity * (m1 + m2) * cos(theta1) + om2*om2 * l2 * m2 * cos(theta1 - theta2);
		den = l2 * (2 * m1 + m2 - m2 * cos(2*theta1 - 2*theta2));
		al2 = (num1 * num2) / den;
		
		om1 += al1;
		om2 += al2;
		theta1 += om1;
		theta2 += om2;
		
		
//		if(om2<30) {
//			if (theta2 > 0)
//				theta2 += 0.01;
//			else theta2 -= 0.01;
//		}
		


	}
}