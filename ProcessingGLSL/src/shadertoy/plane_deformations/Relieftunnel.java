package shadertoy.plane_deformations;
import app.GLShader_Instance;
import processing.core.PApplet;
import processing.opengl.PShader;

public class Relieftunnel extends GLShader_Instance {

	public Relieftunnel(PApplet app,String name) {
		super(app,name,PShader.TEXTURED);
	}

	@Override
	public void init() {
		setFullResolution();
	}
	@Override
	public void update() {
		setTime();
	}

	@Override
	public boolean useTexture() {
		tex = app.loadImage("tex3.jpg");
		return true;
	}
	
}