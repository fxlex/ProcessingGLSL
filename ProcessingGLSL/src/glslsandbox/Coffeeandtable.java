package glslsandbox;
import app.GLShader_Instance;
import processing.core.PApplet;
import processing.opengl.PShader;

public class Coffeeandtable extends GLShader_Instance {

	public Coffeeandtable(PApplet app,String name) {
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
