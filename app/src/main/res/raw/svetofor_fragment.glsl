precision mediump float;       	// Set the default precision to medium. We don't need as high of a 
								// precision in the fragment shader.
uniform vec4 vColor;
uniform sampler2D u_Texture;    // The input texture.
uniform float time;
varying vec2 v_TexCoordinate;   // Interpolated texture coordinate per fragment.
  
// The entry point for our fragment shader.

float interpolator(){
	return time*2.2;
}

bool mainBlink(float t){
	return (t<1.0 && t>0.9) || (t<0.8 && t>0.7);
}

bool sideBlink(float t){
	return (t>1.1 && t<1.7) || (t<2.1 && t>2.0) || (t<1.9 && t>1.8);
}

void main()                    		
{
    float ft = length(v_TexCoordinate);
	vec4 sc=vColor;
	//float nast=nas;
	vec4 st=texture2D(u_Texture, v_TexCoordinate);

	float t=interpolator();

	bool p=false;


	vec4 backColor=	 vec4(0.3,0.3,0.3,1.0);
	vec4 crishaColor=vec4(0.6,0.6,0.6,1.0);
	vec4 lightColor= vec4(0.2,0.2,0.2,1.0);
	vec4 transparent=vec4(0.0,0.0,0.0,0.0);
	vec4 redColor=   vec4(0.9,0.0,0.0,1.0);
	vec4 yellowColor=vec4(0.9,0.8,0.0,1.0);
	vec4 greenColor= vec4(0.0,0.9,0.0,1.0);
	vec4 disableColor=vec4(0.4,0.4,0.4,1.0);


	sc=backColor;

	if (abs(v_TexCoordinate.x)<=0.5){
		float svet= length(vec2(v_TexCoordinate.x,fract(v_TexCoordinate.y)-0.5));

		if (fract(v_TexCoordinate.y+0.025)<0.05 || fract(v_TexCoordinate.x+0.525)<0.05)
			sc=vec4(0.1,0.1,0.1,1.0);

		if (svet<0.4){
			float y=v_TexCoordinate.y;
			sc=disableColor;
			if (y<1.0){
				if (t<0.6 || mainBlink(t) ){
					sc=greenColor;
				}

			}else if (y<2.0){
				if (t>1.0 && t<1.1 || t>2.1)
					sc=yellowColor;
			}else{
				if (t>1.1 && t<2.2)
					sc=redColor;
			}
			sc=mix(sc,lightColor,svet*0.5);

			float verh=length(vec2(v_TexCoordinate.x,fract(v_TexCoordinate.y+0.5)-0.5));
			if (verh<0.4 && fract(v_TexCoordinate.y)>0.5){
				sc=mix(crishaColor,backColor,verh);
			}
		}
	}else{
		float x=abs(v_TexCoordinate.x)-0.5;
		float y=v_TexCoordinate.y;
		sc=transparent;

		float svet=length(vec2(x+0.2,fract(v_TexCoordinate.y)-0.5));
		if (svet<0.4){
			sc=disableColor;
			if (y<1.0){
				if (sideBlink(t))
					sc=greenColor;
			}else if (y<2.0){
				if (t>1.0 && t<1.1 || t>2.1)
					sc=yellowColor;
			}else{
				if (t<1.1)
					sc=redColor;
			}
			sc=mix(sc,lightColor,svet*0.5);
		}

		float verh=length(vec2(x,fract(v_TexCoordinate.y+0.5)-0.5));
		if (verh<0.4 && fract(v_TexCoordinate.y)>0.5 && fract(v_TexCoordinate.y)<0.9){
        				sc=mix(crishaColor,backColor,verh);
        }
	}

	// Multiply the color by the diffuse illumination level and texture value to get final output color.
    gl_FragColor = sc;// mix(sc,st,0.7)*nast;
 //   gl_FragColor.a = st.a;


}

