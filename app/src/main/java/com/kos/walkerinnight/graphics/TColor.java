package com.kos.walkerinnight.graphics;

public class TColor {

	public float[] c;
	public TColor(int color) {
		c=new float[4];
		c[0]=0;
		c[1]=0;
		c[2]=1;
		c[3]=1.0f;
		
	}
	public TColor(float r, float g, float b, float a) {
		c=new float[4];
		c[0]=r;
		c[1]=g;
		c[2]=b;
		c[3]=a;
	}
	public TColor(TColor color) {
		c=new float[4];
		c[0]=color.c[0];
		c[1]=color.c[1];
		c[2]=color.c[2];
		c[3]=color.c[3];
	}

}
