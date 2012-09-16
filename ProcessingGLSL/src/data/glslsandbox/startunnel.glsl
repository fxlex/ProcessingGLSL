//star tunnel, a somewhat happy accident in picking apart
//and trying to simplify the other starfield effect
//some random color would be nice -> done.

#ifdef GL_ES
precision mediump float;
#endif
 
uniform float time;
uniform vec2 resolution;

void main(void)
{
	const float stars	= 500.0;
	const float depth	= 512.0;
	const float skew	= 16.0;
	float gradient		= 0.0;
	float speed		= 128.0 * time; //negate to reverse
	
	vec2 pos		= (((gl_FragCoord.xy / resolution.xy)) - 0.5) * skew;
	vec2 pos_center		= pos - vec2(0.5);
	
	for (float i = 1.0; i < stars; i++)
	{
		//i * i will get a typical starfield effect
		float x = sin(i) * 256.0;
		float y = cos(i) * 256.0;
		float z = mod(i - speed, depth);
				
		vec2 blob_coord = vec2(x, y) / z;
		float fade 	= (depth - z) / 512.0;		
		gradient 	+= ((fade / depth) / pow(length(pos_center - blob_coord), 1.8));
	}
	float r = abs(sin(time * 1.13));
	float g = abs(sin(time * 2.23));
	float b = abs(sin(time * 3.33));

	gl_FragColor = vec4(gradient * r, gradient * g, gradient * b, 1.0);
}