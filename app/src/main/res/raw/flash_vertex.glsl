uniform mat4 uMVPMatrix;
attribute vec4 vPosition;
attribute vec2 aTexCoord;
varying vec2 v_TexCoordinate;   // This will be passed into the fragment shader.
void main() {
    v_TexCoordinate=aTexCoord;
	gl_Position =  uMVPMatrix * vPosition;
}
