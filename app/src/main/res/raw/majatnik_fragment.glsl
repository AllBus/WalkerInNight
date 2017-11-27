precision mediump float;       	// Set the default precision to medium. We don't need as high of a 
								// precision in the fragment shader.
uniform vec4 vColor;
uniform sampler2D u_Texture;    // The input texture.
uniform float time;
varying vec2 v_TexCoordinate;   // Interpolated texture coordinate per fragment.
  
// The entry point for our fragment shader.

float interpolator(){
	return (abs(time-0.5)*2.0-0.5);
}

float func(float x,float y,float t){
	float x2=x-1.0;
	if (x-0.1<y*y*t && x+0.1>y*y*t)
		return 1.0;
	else
		return 0.0;
}

void main()                    		
{
    float ft = length(v_TexCoordinate);
	vec4 sc=vColor;
	//float nast=nas;
	vec4 st=texture2D(u_Texture, v_TexCoordinate);

	float i=interpolator();


	float lineWidth=0.9;
	float topLine=1.1;
	float p=func(v_TexCoordinate.x,v_TexCoordinate.y,i);
	if (p>lineWidth && p<topLine)
		sc.a=1.0;
	else
		sc.a=0.0;

    gl_FragColor = sc;// mix(sc,st,0.7)*nast;



}

