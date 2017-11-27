precision mediump float;       	// Set the default precision to medium. We don't need as high of a 
								// precision in the fragment shader.
uniform vec4 vColor;
uniform sampler2D u_Texture;    // The input texture.
uniform float time;
varying vec2 v_TexCoordinate;   // Interpolated texture coordinate per fragment.
  
// The entry point for our fragment shader.

	vec4 transparent=vec4(0.0,0.0,0.0,0.0);
	vec4 snowColor=  vec4(1.0,1.0,1.0,1.0);
    vec4 shadowColor=vec4(0.8,0.8,0.8,1.0);

float interpolator(){
	return (abs(time-0.5)*2.0-0.5);
}

vec4 generateColor(float x,float y){

	if (y<3.0){
		float y2=(y-2.2)*2.0;
		float le= length(vec2(x*2.0,y2));
		float les=length(vec2(x-0.2,y2-0.2));

		if (le<1.0){
			return mix(snowColor,shadowColor,les);
		}
	}
	if (y<2.0){
			float y2=(y-1.5)*2.0;
    		float le= length(vec2(x*1.4,y2));
    		float les=length(vec2(x-0.2,y2-0.2));

    		if (le<1.0){
    			return mix(snowColor,shadowColor,les);
    		}
	}
	if (y<1.5){
		float y2=(y-0.8)*1.6;
		float le= length(vec2(x,y2));
		float les=length(vec2(x-0.2,y2-0.2));

		if (le<1.0){
			return mix(snowColor,shadowColor,les);
		}
	}


	return transparent;

}

void main()                    		
{



    float ft = length(v_TexCoordinate);
	vec4 sc=vColor;
	//float nast=nas;
	vec4 st=texture2D(u_Texture, v_TexCoordinate);

	float i=interpolator();


	float x=v_TexCoordinate.x;
	float y=v_TexCoordinate.y;

	sc=generateColor(x,y);

    gl_FragColor = sc;// mix(sc,st,0.7)*nast;



}

