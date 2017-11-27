uniform mat4 uMVPMatrix;
attribute vec3 vPosition;
attribute vec2 aTexCoord;
varying vec2 v_TexCoordinate;   // This will be passed into the fragment shader.
uniform float time;

float interpolator(){
 	return abs(fract(time*3.0)-0.5)*2.0*0.3+0.7;
}


void main() {
    v_TexCoordinate=aTexCoord;
    vec4 vc=vec4(vPosition,1);

    gl_Position = uMVPMatrix * vc ;
}
