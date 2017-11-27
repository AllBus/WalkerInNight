package com.kos.walkerinnight;


import com.kos.walkerinnight.NTTochk.PointF;
import com.kos.walkerinnight.NTTochk.meshs.TMeshWithoutNormal;

public class TDrawObjects {
	public PointF position = new PointF();
	public int texIndex=5;
	public int player;
	public TMeshWithoutNormal mesh=null;
	public int frameIndex=0;
	public int typeIndex=0;
	public float[] movePos=null;

	public long startTime=0;
	public long endTime=0;
	public boolean needDelete=false;
}
