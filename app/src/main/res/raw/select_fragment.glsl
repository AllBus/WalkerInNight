precision mediump float;       	// Set the default precision to medium. We don't need as high of a 
								// precision in the fragment shader.
uniform vec4 vColor;
uniform sampler2D u_Texture;    // The input texture.
uniform float time;
varying vec2 v_TexCoordinate;   // Interpolated texture coordinate per fragment.
  
// The entry point for our fragment shader.

float interpolator(){
	return abs(time-0.5)+0.5;
}

void main()                    		
{
    float ft = length(v_TexCoordinate);
	vec4 sc=vColor;
	//float nast=nas;
	vec4 st=texture2D(u_Texture, v_TexCoordinate);

	float i=interpolator();

	bool p=false;


	if (ft>1.0 || p){
		st.a=0.0;
	}else{


		if (ft<0.8)
			st.a=ft*i+ft*i*0.8;
		else
			st.a=ft*i;
	}


	// Multiply the color by the diffuse illumination level and texture value to get final output color.
    gl_FragColor = sc;// mix(sc,st,0.7)*nast;
    gl_FragColor.a = st.a;


}

