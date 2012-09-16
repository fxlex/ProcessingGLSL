package app;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;


public abstract class GLShader_Instance {
	protected PImage tex = null;
	protected PApplet app;
	String name;
	ArrayList<PImage> textures = new ArrayList<>();
	protected PShaderObject shader;

	public GLShader_Instance(PApplet app, String name, int type) {
		this(app,name,type,true);
	}
	
	public GLShader_Instance(PApplet app, String name, int type,boolean glslExtension) {
		this.app = app;
		this.name = name;
		if(glslExtension)
			shader = new PShaderObject(app,name.toLowerCase()+".glsl",type);
		else
		shader = new PShaderObject(app,name.toLowerCase()+".frag",name.toLowerCase()+".vert",type);
	}

	public abstract void init();
	public abstract void update();
	public abstract boolean useTexture();
	
	public void shader(boolean check) {
		shader.shader(check);
	}
	
	public void check() {
		shader.check(PShaderObject.ALL_FIELDS);
	}
	
	protected void setFullResolution() {
		setResolution(app.width,app.height);
	}
	
	protected void setResolution(float width,float height) {
	  shader.set("resolution",width,height);
	}
	
	protected void setTime() {
	      shader.set("time", app.millis() / 1000f);
	}
	
	protected void setMouse() {
		  app.cursor(PApplet.CROSS);
	      shader.set("mouse",PApplet.map(app.mouseX,0,app.width,0,1),PApplet.map(app.mouseY,0,app.height,0,1));
	}
}
