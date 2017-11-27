precision mediump float;       	// Set the default precision to medium. We don't need as high of a 
								// precision in the fragment shader.
uniform vec4 vColor;
uniform sampler2D u_Texture;    // The input texture.
uniform float nas;  
varying vec2 v_TexCoordinate;   // Interpolated texture coordinate per fragment.
  
// The entry point for our fragment shader.
void main()                    		
{                              
	vec4 sc=vColor;
	float nast=nas;
	vec4 st=texture2D(u_Texture, v_TexCoordinate);
	// Multiply the color by the diffuse illumination level and texture value to get final output color.
    gl_FragColor =mix(sc,st,0.7)*nast;
    gl_FragColor.a= st.a;
                          		
}

