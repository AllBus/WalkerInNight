precision mediump float;       	// Set the default precision to medium. We don't need as high of a 
								// precision in the fragment shader.
uniform vec4 vColor;
uniform sampler2D u_Texture;    // The input texture.
uniform float nas;
uniform float time;
uniform vec4 vMovePos;
varying vec2 v_TexCoordinate;   // Interpolated texture coordinate per fragment.

float interpolator(float v,float t){
	float t2;
	if (t<0.1){
		t2=1.0-(t)/0.1;

	}else{
		t2=(t-0.1)/0.9;

	}
	return v*0.4+t2;
}

// The entry point for our fragment shader.
void main()                    		
{                              
	vec4 sc=vColor;
	float nast=nas;
	vec4 st=texture2D(u_Texture, v_TexCoordinate);

	vec2 m=mix(vMovePos.xy,vMovePos.zw ,time);
	float rast=length(v_TexCoordinate+m);
	// Multiply the color by the diffuse illumination level and texture value to get final output color.
    gl_FragColor =mix( vec4(1.0,1.0,1.0,0.0),vec4(0.0,0.0,0.0,1.0),interpolator(rast,time)) ;



  //  gl_FragColor.a= st.a;
                          		
}

