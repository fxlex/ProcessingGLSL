package glslsandbox;
import app.GLShader_Instance;
import processing.core.PApplet;
import processing.opengl.PShader;

public class Puls extends GLShader_Instance {

	public Puls(PApplet app,String name) {
		super(app,name,PShader.FLAT,true);
	}

	@Override
	public void init() {
		setFullResolution();
		// only for audio relevant:
		shader.set("bin0", 0f);
		shader.set("bin1", 0f);
		shader.set("bin2", 0f);
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
