uniform mat4 uMVPMatrix;
attribute vec3 vPosition;
attribute vec2 aTexCoord;
varying vec2 v_TexCoordinate;   // This will be passed into the fragment shader.
uniform float time;

float interpolator(){
	return (abs(time-0.5)*2.0-0.5);
}


void main() {
    v_TexCoordinate=aTexCoord;
    vec4 vc=vec4(vPosition,1);

	float timer=interpolator();
	mat4 rotation = mat4(
         cos(timer), -sin(timer), 0.0, 0.0,
         sin( timer), cos(timer), 0.0, 0.0,
         0.0,  0.0, 1.0, 0.0,
         0.0,  0.0, 0.0, 1.0 );

    gl_Position = uMVPMatrix *rotation* vc ;
}
