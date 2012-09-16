package shadertoy.two_dimensional;
import app.GLShader_Instance;
import processing.core.PApplet;
import processing.opengl.PShader;

public class Radialblur extends GLShader_Instance {

	public Radialblur(PApplet app,String name) {
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