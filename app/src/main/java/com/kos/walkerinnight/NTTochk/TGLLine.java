package com.kos.walkerinnight.NTTochk;


import com.kos.walkerinnight.NTTochk.meshs.TMeshWithoutNormal;

public class TGLLine extends TMeshWithoutNormal {

	public float width;
	public TGLLine()
	{
		init(4,6);
		GridOrdered(1, 6, 4);
		width=0.01f;
		
	}
	
	public void setLine(float x,float y,float ex,float ey)
	{
		this.vertexBuffer.position(0);
		this.vertexBuffer.put(x);
		this.vertexBuffer.put(y);
		this.vertexBuffer.put(0);	
		this.vertexBuffer.put(x+width);
		this.vertexBuffer.put(y);
		this.vertexBuffer.put(0);
		this.vertexBuffer.put(ex);
		this.vertexBuffer.put(ey);
		this.vertexBuffer.put(0);
		this.vertexBuffer.put(ex+width);
		this.vertexBuffer.put(ey);
		this.vertexBuffer.put(0);
		
		this.vertexBuffer.position(0);
	}
}
