package app;

import glslsandbox.Anotherworld;
import glslsandbox.Autumntrees;
import glslsandbox.Blackandwhite;
import glslsandbox.Blobneon;
import glslsandbox.Blobs;
import glslsandbox.Blocks;
import glslsandbox.Blurspline;
import glslsandbox.Bouncingspheres;
import glslsandbox.Boy;
import glslsandbox.Bubblegum;
import glslsandbox.Chains;
import glslsandbox.Chainsandgears;
import glslsandbox.City;
import glslsandbox.Clock;
import glslsandbox.Cloudy;
import glslsandbox.Cnoise;
import glslsandbox.Coffeeandtable;
import glslsandbox.Colourtunnel;
import glslsandbox.Compilation;
import glslsandbox.Continua;
import glslsandbox.Eggs;
import glslsandbox.Fireblobs;
import glslsandbox.Fish;
import glslsandbox.Flags;
import glslsandbox.Flower;
import glslsandbox.Gifts;
import glslsandbox.Green;
import glslsandbox.Hills;
import glslsandbox.Hyberbolicspace;
import glslsandbox.Jungle;
import glslsandbox.Leds;
import glslsandbox.Light;
import glslsandbox.Lighttexture;
import glslsandbox.Lofiterrain;
import glslsandbox.Magister;
import glslsandbox.Meteors;
import glslsandbox.Minecraft;
import glslsandbox.Movingup;
import glslsandbox.Pacman;
import glslsandbox.Planedeformation;
import glslsandbox.Pnoise;
import glslsandbox.Raymarcher;
import glslsandbox.Rects;
import glslsandbox.Rileycorpsefinder;
import glslsandbox.Rings;
import glslsandbox.Rock;
import glslsandbox.Sandbox;
import glslsandbox.Shabtronic;
import glslsandbox.Sinus;
import glslsandbox.Spheres;
import glslsandbox.Starspace;
import glslsandbox.Startunnel;
import glslsandbox.Street;
import glslsandbox.Sunsetonthesea;
import glslsandbox.Terrain;
import glslsandbox.Tigrou;
import glslsandbox.Timewarp;
import glslsandbox.Torus;
import glslsandbox.Train;
import glslsandbox.Voronoi;
import glslsandbox.Warp;
import glslsandbox.Wave;
import glslsandbox.Yellowandblue;
import glslsandbox.Zebra;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.InputEvent;

import processing.core.PApplet;
import processing.opengl.PGraphics2D;
import processing.opengl.Texture;
import shadertoy.plane_deformations.Deform;
import shadertoy.plane_deformations.Fly;
import shadertoy.plane_deformations.Kaleidoscope;
import shadertoy.plane_deformations.Monjori;
import shadertoy.plane_deformations.Relieftunnel;
import shadertoy.plane_deformations.Squaretunnel;
import shadertoy.plane_deformations.Star;
import shadertoy.plane_deformations.Tunnel;
import shadertoy.plane_deformations.Twist;
import shadertoy.plane_deformations.ZInvert;
import shadertoy.three_dimensional.Apple;
import shadertoy.three_dimensional.Chocolux;
import shadertoy.three_dimensional.Clod;
import shadertoy.three_dimensional.Disco;
import shadertoy.three_dimensional.Droid;
import shadertoy.three_dimensional.Kinderpainter;
import shadertoy.three_dimensional.Landscape;
import shadertoy.three_dimensional.Leizex;
import shadertoy.three_dimensional.Mandelbulb;
import shadertoy.three_dimensional.Mengersponge;
import shadertoy.three_dimensional.Metatunnel;
import shadertoy.three_dimensional.Nautilus;
import shadertoy.three_dimensional.Quaternion;
import shadertoy.three_dimensional.Red;
import shadertoy.three_dimensional.Shadertoy_704;
import shadertoy.three_dimensional.Slisesix;
import shadertoy.three_dimensional.Sult;
import shadertoy.three_dimensional.Totheroadofribbon;
import shadertoy.three_dimensional.Valleyball;
import shadertoy.two_dimensional.Heart;
import shadertoy.two_dimensional.Julia;
import shadertoy.two_dimensional.Mandel;
import shadertoy.two_dimensional.Metablob;
import shadertoy.two_dimensional.Motionblur;
import shadertoy.two_dimensional.Pepa;
import shadertoy.two_dimensional.Plasma;
import shadertoy.two_dimensional.Postprocessing;
import shadertoy.two_dimensional.Radialblur;
import shadertoy.two_dimensional.Shapes;
import shadertoy.two_dimensional.Sierpinski;
import shadertoy.two_dimensional.Water;

public class GLSL_starter extends PApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static int PRESENT_MODE = 1; // fullscreen
	static int CODE_MODE = 2; // show code
	static int CODEX_MODE = PRESENT_MODE | CODE_MODE;

	//// SETTINGS ////
	/**/ static int MODUS = CODEX_MODE;       /**/ 
	/**/ static String render = P2D;          /**/ 
	/**/ static int alternativeWidth = 800;   /**/ 
	/**/ static int alternativeHeight = 800;  /**/ 
	//// SETTINGS ////

	GLShader_Instance[][] shaders = new GLShader_Instance[2][];
	static int shaderHorizIndex;
	static int shaderVertIndex;

	final String SHADERTOY = "shadertoy/";
	final String SHADERTOY_PLANE = SHADERTOY+"plane_deformations/";
	final String SHADERTOY_2D = SHADERTOY+"two_dimensional/";
	final String SHADERTOY_3D = SHADERTOY+"three_dimensional/";

	final String GLSLSANDBOX = "glslsandbox/";

	GLSLViewer viewer;
	static boolean indexChanged;

	public void init() {
		super.init();
		if((MODUS & CODEX_MODE) == CODEX_MODE) {
			frame.removeNotify();
			frame.setUndecorated(true);
			frame.addNotify();
		}
	}

	public void setup() {
		if((MODUS & CODEX_MODE) == CODEX_MODE)
			size((int) (displayWidth*(2/3f)),displayHeight,render);
		else if((MODUS & PRESENT_MODE) == PRESENT_MODE)
			size(displayWidth,displayHeight, render);
		else
			size(alternativeWidth,alternativeHeight, render);
		if(render == P2D)
			((PGraphics2D)g).textureWrap(Texture.REPEAT);  
		frameRate(60);
		try {
			new Robot().mousePress(InputEvent.BUTTON1_DOWN_MASK);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if((MODUS & CODE_MODE) == CODE_MODE) {
			frame.setAlwaysOnTop(true);
			frame.addComponentListener(new ComponentAdapter()
			{
				@Override
				public void componentMoved(ComponentEvent e) {
					super.componentMoved(e);
					Point newLocation = frame.getLocationOnScreen();
					newLocation.x += frame.getWidth();
					viewer.setLocation(newLocation);
				}}
					);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			if((MODUS & CODEX_MODE) == CODEX_MODE) 
				frame.setLocation(0,0);
			else
				frame.setLocation(dim.width/2-alternativeWidth,dim.height/2-alternativeHeight/2);
		}
		hint(DISABLE_DEPTH_TEST);
		hint(DISABLE_DEPTH_SORT);
		hint(DISABLE_OPENGL_ERROR_REPORT);
		noCursor();
		shaders[0] = new GLShader_Instance[] { 
				// shadertoy
				new Deform(this,SHADERTOY_PLANE+"Deform"),
				new Monjori(this,SHADERTOY_PLANE+"Monjori"),  
				new Star(this,SHADERTOY_PLANE+"Star"), 
				new Twist(this,SHADERTOY_PLANE+"Twist") ,
				new Kaleidoscope(this,SHADERTOY_PLANE+"Kaleidoscope"),
				new ZInvert(this,SHADERTOY_PLANE+"ZInvert"),
				new Tunnel(this,SHADERTOY_PLANE+"Tunnel"),
				new Relieftunnel(this,SHADERTOY_PLANE+"Relieftunnel"),
				new Squaretunnel(this,SHADERTOY_PLANE+"Squaretunnel"),
				new Fly(this,SHADERTOY_PLANE+"Fly"),
				new shadertoy.plane_deformations.Pulse(this,SHADERTOY_PLANE+"Pulse"),
				new Radialblur(this,SHADERTOY_2D+"Radialblur"),
				new Motionblur(this,SHADERTOY_2D+"Motionblur"),
				new Pepa(this,SHADERTOY_2D+"Pepa"),
				new Postprocessing(this,SHADERTOY_2D+"Postprocessing"),
				new Julia(this,SHADERTOY_2D+"Julia"),
				new Mandel(this,SHADERTOY_2D+"Mandel"),
				// Multitexture missing
				new shadertoy.two_dimensional.Flower(this,SHADERTOY_2D+"Flower"),
				new Heart(this,SHADERTOY_2D+"Heart"),
				new Sierpinski(this,SHADERTOY_2D+"Sierpinski"),
				new Metablob(this,SHADERTOY_2D+"Metablob"),
				new Plasma(this,SHADERTOY_2D+"Plasma"),
				new Shapes(this,SHADERTOY_2D+"Shapes"),
				new Water(this,SHADERTOY_2D+"Water"),
				new Apple(this,SHADERTOY_3D+"Apple"),
				new Shadertoy_704(this,SHADERTOY_3D+"704"),
				new Landscape(this,SHADERTOY_3D+"Landscape"),
				new Totheroadofribbon(this,SHADERTOY_3D+"Totheroadofribbon"),
				new Chocolux(this,SHADERTOY_3D+"Chocolux"),
				new Nautilus(this,SHADERTOY_3D+"Nautilus"),
				new Mengersponge(this,SHADERTOY_3D+"Mengersponge"),
				new Clod(this,SHADERTOY_3D+"Clod"),
				//new Earth(this,SHADERTOY_3D+"Earth"), -> OpenGL error 1282 at bot endDraw(): invalid operation
				new Slisesix(this,SHADERTOY_3D+"Slisesix"),
				new Sult(this,SHADERTOY_3D+"Sult"),
				new Valleyball(this,SHADERTOY_3D+"Valleyball"),
				new Kinderpainter(this,SHADERTOY_3D+"Kinderpainter"), 
				new Red(this,SHADERTOY_3D+"Red"),
				new Quaternion(this,SHADERTOY_3D+"Quaternion"),
				new Quaternion(this,SHADERTOY_3D+"Lunaquatic"),
				new Metatunnel(this,SHADERTOY_3D+"Metatunnel"),
				new Droid(this,SHADERTOY_3D+"Droid"),
				new Disco(this,SHADERTOY_3D+"Disco"),
				new Mandelbulb(this,SHADERTOY_3D+"Mandelbulb"),
				new Leizex(this,SHADERTOY_3D+"Leizex")
		};

		shaders[1] = new GLShader_Instance[] { 
				// GLSLSANDBOX
				new Compilation(this,GLSLSANDBOX+"Compilation"), 
				new Meteors(this,GLSLSANDBOX+"Meteors"),
				new Continua(this,GLSLSANDBOX+"Continua"),
				new Hills(this,GLSLSANDBOX+"Hills"),
				new Flags(this,GLSLSANDBOX+"Flags"),
				new Timewarp(this,GLSLSANDBOX+"Timewarp"), // -> flickering
				new Green(this,GLSLSANDBOX+"Green"),
				new City(this,GLSLSANDBOX+"City"), 
				new Street(this,GLSLSANDBOX+"Street"), 
				//new Blankslate(this,GLSLSANDBOX+"Blankslate"), -> same as Timewarp, wrong rendering
				new Sandbox(this,GLSLSANDBOX+"Sandbox"), 
				new Planedeformation(this,GLSLSANDBOX+"Planedeformation"), 
				new Blackandwhite(this,GLSLSANDBOX+"Blackandwhite"), 
				new Jungle(this,GLSLSANDBOX+"Jungle"), 
				new Terrain(this,GLSLSANDBOX+"Terrain"), 
				new Blobs(this,GLSLSANDBOX+"Blobs"), 
				new Blocks(this,GLSLSANDBOX+"Blocks"), 
				new Startunnel(this,GLSLSANDBOX+"Startunnel"), 
				new Leds(this,GLSLSANDBOX+"Leds"), 
				new Raymarcher(this,GLSLSANDBOX+"Raymarcher"), // -> bla bla...seee above
				new Fish(this,GLSLSANDBOX+"Fish"), 
				new Tigrou(this,GLSLSANDBOX+"Tigrou"), 
				new Sunsetonthesea(this,GLSLSANDBOX+"Sunsetonthesea"), 
				new Blobneon(this,GLSLSANDBOX+"Blobneon"), 
				new Clock(this,GLSLSANDBOX+"Clock"), 
				new Cloudy(this,GLSLSANDBOX+"Cloudy"), 
				new Torus(this,GLSLSANDBOX+"Torus"), 
				new Cnoise(this,GLSLSANDBOX+"Cnoise"),
				new Hyberbolicspace(this,GLSLSANDBOX+"Hyberbolicspace"),
				new Coffeeandtable(this,GLSLSANDBOX+"Coffeeandtable"), // -> result different to WebGL
				new Shabtronic(this,GLSLSANDBOX+"Shabtronic"),
				new Minecraft(this,GLSLSANDBOX+"Minecraft"),
				new Starspace(this,GLSLSANDBOX+"Starspace"), // -> flickering
				new Pacman(this,GLSLSANDBOX+"Pacman"),
				new Pnoise(this,GLSLSANDBOX+"Pnoise"),
				new Bubblegum(this,GLSLSANDBOX+"Bubblegum"),
				new glslsandbox.Julia(this,GLSLSANDBOX+"Julia"),
				new Colourtunnel(this,GLSLSANDBOX+"Colourtunnel"),
				new Sinus(this,GLSLSANDBOX+"Sinus"),
				new Rects(this,GLSLSANDBOX+"Rects"), // -> flickering
				new Blurspline(this,GLSLSANDBOX+"Blurspline"),
				new Lofiterrain(this,GLSLSANDBOX+"Lofiterrain"),
				new Voronoi(this,GLSLSANDBOX+"Voronoi"), // -> Mouse inverted
				new Yellowandblue(this,GLSLSANDBOX+"Yellowandblue"),
				new Warp(this,GLSLSANDBOX+"Warp"),
				//new Boy(this,GLSLSANDBOX+"Boy"),  -> array offset argument "infoLog_offset" (0) equals or exceeds array length (0)
				new Spheres(this,GLSLSANDBOX+"Spheres"), // -> noisy
				new glslsandbox.Puls(this,GLSLSANDBOX+"Puls"),
				new Rileycorpsefinder(this,GLSLSANDBOX+"Rileycorpsefinder"),
				new Chains(this,GLSLSANDBOX+"Chains"),
				//new Gameoflife(this,GLSLSANDBOX+"Gameoflife"), -> backbuffer   
				new Light(this,GLSLSANDBOX+"Light"),
				new Chainsandgears(this,GLSLSANDBOX+"Chainsandgears"),
				new Bouncingspheres(this,GLSLSANDBOX+"Bouncingspheres"),
				new Eggs(this,GLSLSANDBOX+"Eggs"),
				new Gifts(this,GLSLSANDBOX+"Gifts"),
				new Anotherworld(this,GLSLSANDBOX+"Anotherworld"),
				new Flower(this,GLSLSANDBOX+"Flower"),
				// new Christmastree(this,GLSLSANDBOX+"Christmastree"), -> rendering not correct
				new Rings(this,GLSLSANDBOX+"Rings"),
				new Rock(this,GLSLSANDBOX+"Rock"), // is it correct??
				new Autumntrees(this,GLSLSANDBOX+"Autumntrees"), // little bit flickering
				new Lighttexture(this,GLSLSANDBOX+"Lighttexture"),
				//new Redjulia(this,GLSLSANDBOX+"Redjulia"), -> not working
				new Train(this,GLSLSANDBOX+"Train"),
				new Fireblobs(this,GLSLSANDBOX+"Fireblobs"),
				new Movingup(this,GLSLSANDBOX+"Movingup"),
				new Magister(this,GLSLSANDBOX+"Magister"),
				new Zebra(this,GLSLSANDBOX+"Zebra"),
				new Wave(this,GLSLSANDBOX+"Wave"),
		};

		if(!indexChanged) {
			/*
			shaderVertIndex = shaders.length-1;
			shaderHorizIndex = shaders[shaderVertIndex].length-1;
			 */
			shaderVertIndex = 0;
			shaderHorizIndex = 0;

		}
		if((MODUS & CODE_MODE) == CODE_MODE) {
			viewer = new GLSLViewer(this);
			if((MODUS & CODEX_MODE) == CODEX_MODE) {
				viewer.setUndecorated(true);
				viewer.setOpacity(0.8f);
				Dimension dim = frame.getSize();
				viewer.setSize((int) (dim.width*(2/3f)),dim.height);
			} else {
				viewer.setSize(frame.getSize());
			}
			viewer.setVisible(true);
			viewer.setAlwaysOnTop(true);
		}
		switchShader();	
	}


	public void draw() {

		shaders[shaderVertIndex][shaderHorizIndex].update();
		if(frameCount == 1) {
			shaders[shaderVertIndex][shaderHorizIndex].check();
		}
		if(shaders[shaderVertIndex][shaderHorizIndex].useTexture()) {
			image(shaders[shaderVertIndex][shaderHorizIndex].tex, 0, 0, width, height);
		}

		else {
			noStroke();
			fill(0);
			rect(0, 0, width, height);  
		}
	}


	public static void main(String args[]) {
		if(args.length != 0) {
			shaderVertIndex = Integer.parseInt(args[0]);
			shaderHorizIndex = Integer.parseInt(args[1]);
			indexChanged = true;
		}
		if((MODUS & PRESENT_MODE) == PRESENT_MODE && (MODUS & CODEX_MODE) != CODEX_MODE)
			PApplet.main(new String[] { "--present",Thread.currentThread().getStackTrace()[1].getClassName()});
		else
			PApplet.main(new String[] { Thread.currentThread().getStackTrace()[1].getClassName() });

	}
	public void switchShader() {
		shaders[shaderVertIndex][shaderHorizIndex].init();	
		shaders[shaderVertIndex][shaderHorizIndex].shader(false);	
		System.out.println("Shader changed to:"+shaders[shaderVertIndex][shaderHorizIndex].name);
		if((MODUS & CODE_MODE) == CODE_MODE) {
			String fileName = shaders[shaderVertIndex][shaderHorizIndex].name.toLowerCase()+".glsl";
			String[] codeLines = loadStrings(fileName);
			String code = "";
			for(int i=0;i<codeLines.length;i++)
				code += codeLines[i] + "\n";
			viewer.setText(code);
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				   public void run() { 
				       viewer.scroll.getVerticalScrollBar().setValue(0);
				   }
				});

		}
		frame.setTitle(shaders[shaderVertIndex][shaderHorizIndex].name);

		frameCount = 1;
	}

	public void keyReleased() {
		switch(keyCode) {
		case 37: // left
			shaderHorizIndex--;
			if(shaderHorizIndex < 0) 
				shaderHorizIndex = shaders[shaderVertIndex].length - 1;
			switchShader();
			break;
		case 39: // right
			shaderHorizIndex = (++shaderHorizIndex % shaders[shaderVertIndex].length);
			switchShader();
			break;
		case 38: // up
			shaderVertIndex = (++shaderVertIndex % shaders.length);
			switchShader();
			break;
		case 40: // down
			shaderVertIndex--;
			if(shaderVertIndex < 0) 
				shaderVertIndex = shaders.length - 1;
			switchShader();
			break;
		case 70: // f
			println(frameRate);
			break;
		}
	}

	public void shaderChangeError() {
		System.err.println("Couldn't change Shader");
	}

}