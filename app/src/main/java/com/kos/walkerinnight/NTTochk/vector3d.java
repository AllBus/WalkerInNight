package com.kos.walkerinnight.NTTochk;

//========================================================================== 
public class vector3d
{
	public float X;
	public float Y;
	public float Z;
	public vector3d()
	{
	}
	public vector3d(float x,float y,float z)
	{
		X = x;
		Y = y;
		Z = z;
	}
public vector3d(double x, int y, double z) {
		X=(float)x;
		Y=(float)y;
		Z=(float)z;
	}

	public final vector3d Normalize()
	{
		vector3d pv = new vector3d((X * X), (Y * Y), (Z * Z));

		float nor = (pv.X + pv.Y + pv.Z);
		if (nor < 1e-08)
		{
			return this;
		}
		nor = 1.0f / nor;
		if (nor < 1e-08)
		{
			return this;
		}
		pv.X *= nor * (X > 0?1:(X < 0? - 1:0));
		pv.Y *= nor * (Y > 0?1:(Y < 0? - 1:0));
		pv.Z *= nor * (Z > 0?1:(Z < 0? - 1:0));
		return pv;
	}
	public vector3d Invert() {
		// TODO Auto-generated method stub
		return new vector3d(-X,-Y,-Z);
	}
}