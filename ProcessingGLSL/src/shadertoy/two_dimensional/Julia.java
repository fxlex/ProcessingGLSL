package shadertoy.two_dimensional;
import app.GLShader_Instance;
import processing.core.PApplet;
import processing.opengl.PShader;

public class Julia extends GLShader_Instance {

	public Julia(PApplet app,String name) {
		super(app,name,PShader.FLAT);
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
