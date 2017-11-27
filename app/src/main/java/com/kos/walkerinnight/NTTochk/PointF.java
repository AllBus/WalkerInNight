package com.kos.walkerinnight.NTTochk;

public class PointF {
	public float X;
	public float Y;
	public PointF(){};
	public PointF(float x,float y)
	{
		X=x;
		Y=y;
	}
	public PointF(PointF position) {
		X=position.X;
		Y=position.Y;
	}
	public void set(PointF position) {
		X=position.X;
		Y=position.Y;
		
	}
}
