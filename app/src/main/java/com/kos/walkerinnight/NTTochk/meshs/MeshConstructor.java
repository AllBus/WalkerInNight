package com.kos.walkerinnight.NTTochk.meshs;

/**
 * Created by Kos on 19.02.2017.
 */

public class MeshConstructor {

	public static TMeshRectangle simpleRect(float radius){
		return new TMeshRectangle(-radius,radius,radius,-radius,-1,-1,1,1);
	}

	public static TMeshWithoutNormal circle(float radius,int edgeCount) {
		TMeshWithoutNormal mesh = new TMeshWithoutNormal();
		mesh.init(edgeCount+1,edgeCount);
		double angle = (float) (2 * Math.PI / edgeCount);
		mesh.setVertex(0,0,0,0,0,0);
		for (int i=1;i<=edgeCount;i++){
			double a=  (i*angle);
			double x= Math.cos(a)*radius;
			double y= Math.sin(a)*radius;
			mesh.setVertex(i,x,y,0,1,0);
			mesh.setFace(i-1,0,i,i+1);
		}
		mesh.setFace(edgeCount-1,0,edgeCount,1);
		mesh.completeBuffer();
		return mesh;
	}
}
