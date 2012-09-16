package shadertoy.plane_deformations;
import app.GLShader_Instance;
import processing.core.PApplet;
import processing.opengl.PShader;

public class Twist extends GLShader_Instance {

	public Twist(PApplet app,String name) {
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
		tex = app.loadImage("tex1.jpg");
		return true;
	}
	
}