package app;
import java.util.HashMap;

import processing.core.PApplet;
import processing.opengl.PShader;


public class PShaderObject {
	class Uniform {
		String type;
		String name;
		int arrayNum;
		boolean set = false;
		boolean optional;

		private Uniform(String name,String type,int arrayNum,boolean optional) {
			this.name = name;
			this.type = type;
			this.arrayNum = arrayNum;
			this.optional = optional;
		}
	}
	private PShader shader;
	private PApplet app;
	private HashMap<String,Uniform> fields = new HashMap<String,Uniform>();
	public static int MISSING_FIELDS = 1;
	public static int OPTIONAL_FIELDS = 2;
	public static int ALL_FIELDS = MISSING_FIELDS | OPTIONAL_FIELDS;

	public PShaderObject(PApplet app,String fileName,int type) {
		this(app,fileName,null,type);
	}
	public PShaderObject(PApplet app,String fragmentName,String vertexName,int type) {
		this.app = app;
		if(fragmentName != null) // must not be null
			saveUniforms(fragmentName);
		if(vertexName != null) {
			saveUniforms(vertexName);
			shader = app.loadShader(type,fragmentName,vertexName);
		} else
			shader = app.loadShader(type,fragmentName);	
	}
	
	public void saveUniforms(String fileName) {
		String[] shader_data = app.loadStrings(fileName);
		for(int i=0;i<shader_data.length;i++) {
			if(shader_data[i].contains("{")) 
				break;
			boolean isUniform = shader_data[i].contains("uniform") && !shader_data[i].contains("textureSampler") &&
								!shader_data[i].contains("backbuffer") &&  !shader_data[i].trim().startsWith("//");
			if(isUniform) {
				String s2Split = shader_data[i].replaceAll("\\s*(uniform)\\s+(\\S+)\\s+(\\S+);(.+\\/\\/.+(optional))*","$2,$3");
				String[] data = s2Split.split(",");	
				int arrayNum = 0;
				if(data[1].contains("[")) {
					String[] s = data[1].split("\\[");
					data[1] = s[0];
					arrayNum = Integer.parseInt(s[1].substring(0,s[1].length()-1));
				}
				boolean optional = shader_data[i].matches(".+\\/\\/.+(optional)")?true:false;
				fields.put(data[1],new Uniform(data[1],data[0],arrayNum,optional));
			}
		}
	}
	
	public void set(String string, float f, float g) {
		shader.set(string, f, g);
		valueAccepted(string);
	}
	public void set(String string, float f) {
		shader.set(string, f);
		valueAccepted(string);
	}
	
	public void set(String string,float[] vec,int num) {
		shader.set(string, vec,num);
		valueAccepted(string);
	}
	

	public void shader(boolean check) {
		if(check)
			check();
		app.shader(shader);
	}

	public void check() {
		check(ALL_FIELDS);
	}

	public void check(int level) {
		for(Uniform cur:fields.values())
			if(!cur.set) {
				if(cur.optional) {
					if((level & OPTIONAL_FIELDS) == OPTIONAL_FIELDS || (level & ALL_FIELDS) == ALL_FIELDS)
						System.out.println("INFO: Attribute '"+cur.name+ "'"+(cur.arrayNum != 0?"["+cur.arrayNum+"]":"")+"("+cur.type+") could be set.");
				} else {
					if((level & MISSING_FIELDS) == MISSING_FIELDS || (level & ALL_FIELDS) == ALL_FIELDS)
						System.err.println("INFO: Attribute '"+cur.name+ "'"+(cur.arrayNum != 0?"["+cur.arrayNum+"]":"")+"("+cur.type+") not set.");
				}
			}
	}

	private void valueAccepted(String string) {
		if(fields.get(string)!=null)
			fields.get(string).set = true;

	}
}
