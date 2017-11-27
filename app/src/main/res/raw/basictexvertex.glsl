uniform mat4 uMVPMatrix;
attribute vec3 vPosition;
attribute vec2 aTexCoord;
varying vec2 v_TexCoordinate;   // This will be passed into the fragment shader.  
void main() {
    v_TexCoordinate=aTexCoord;
    vec4 vc=vec4(vPosition,1);
    gl_Position = uMVPMatrix * vc ;
}
