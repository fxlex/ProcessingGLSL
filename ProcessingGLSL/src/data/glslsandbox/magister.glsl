// Magister by daeken (Cody Brocious)
// Best at 0.5

#ifdef GL_ES
precision highp float;
#endif

uniform vec2 resolution;
uniform float time;

vec4 z(float z) {
	float v;
	z = time + z;
	vec2 p = gl_FragCoord.xy / resolution * 8.0 - 4.0;
	p *= z * 0.0125 + 0.1;
	p *= dot(p,p);
	p *= atan(0.1 / dot(p,p));
	p = tan(mod(sqrt(abs(p*cos(sqrt(v=dot(p,p))) / pow(z, (z+1.5)*v*1.5))), 0.05) / 0.1);
	
	v = sin(p.y/p.x) * cos(1.0/p.y) * min(z*0.2, 1.0) * 0.2;
	return v > 0.0 ? vec4(v/0.8, v, v*1.5, 1) : vec4(-v*0.2, v*0.3*sin(z), -v*0.2, 1);
}

void main()
{
	gl_FragColor = z(0.0) + 
		z(0.05) + z(0.1) + 
		z(-0.05) + z(-0.1);
}
