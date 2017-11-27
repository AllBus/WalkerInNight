uniform mat4 uMVPMatrix;
attribute vec3 vPosition;
attribute vec2 aTexCoord;
varying vec2 v_TexCoordinate;   // This will be passed into the fragment shader.
uniform float time;

float interpolator(){
 	return abs(fract(time*2.0)-0.5)*2.0*0.2+0.9;
}


void main() {
    v_TexCoordinate=aTexCoord;
    vec4 vc=vec4(vPosition,1);
    float i=interpolator();
    vc=vec4(vc.x*i,0.5*vc.y,vc.z,vc.w);
    gl_Position = uMVPMatrix * vc ;
}
