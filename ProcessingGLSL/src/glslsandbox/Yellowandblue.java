package glslsandbox;
import app.GLShader_Instance;
import processing.core.PApplet;
import processing.opengl.PShader;

public class Yellowandblue extends GLShader_Instance {

	public Yellowandblue(PApplet app,String name) {
		super(app,name,PShader.FLAT,true);
	}

	@Override
	public void init() {
			setFullResolution();
	}
	@Override
	public void update() {
		setTime();
		setMouse();
	}

	@Override
	public boolean useTexture() {
		return false;
	}
	
}
