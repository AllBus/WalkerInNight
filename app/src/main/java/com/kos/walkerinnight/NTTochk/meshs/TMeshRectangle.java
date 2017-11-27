package com.kos.walkerinnight.NTTochk.meshs;

public class TMeshRectangle extends TMeshWithoutNormal {
	public TMeshRectangle(float left,float top,float right,float bottom,
			float texleft,float textop,float texright,float texbottom)
	{
		float[] ver=new float[4*3];
		float[] tex=new float[4*2];
		short[] drawOrder={0,1,2,3,1,2};
		
		 int i=0;
	        
	        ver[i*3+0]=left;
	        ver[i*3+1]=top;
	        ver[i*3+2]=0;
	        tex[i*2+0]=texleft;
	        tex[i*2+1]=textop;
	        i++;
	        
	        ver[i*3+0]=right;
	        ver[i*3+1]=top;
	        ver[i*3+2]=0;
	        tex[i*2+0]=texright;
	        tex[i*2+1]=textop;
	        i++;
	        
	        ver[i*3+0]=left;
	        ver[i*3+1]=bottom;
	        ver[i*3+2]=0;
	        tex[i*2+0]=texleft;
	        tex[i*2+1]=texbottom;
	        i++;
	        
	        ver[i*3+0]=right;
	        ver[i*3+1]=bottom;
	        ver[i*3+2]=0;
	        tex[i*2+0]=texright;
	        tex[i*2+1]=texbottom;
	        i++;
	        
	    init(ver,tex,drawOrder);
	}
}
