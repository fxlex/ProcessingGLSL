package glslsandbox;
import app.GLShader_Instance;
import processing.core.PApplet;
import processing.opengl.PShader;

public class Fireblobs extends GLShader_Instance {

	public Fireblobs(PApplet app,String name) {
		super(app,name,PShader.FLAT,true);
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
		return false;
	}
	
}
