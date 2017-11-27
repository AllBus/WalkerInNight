package com.kos.walkerinnight.NTTochk;

//C++ TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
///#pragma warning(disable:4244)
//C++ TO JAVA CONVERTER TODO TASK: The original C++ template specifier was replaced with a Java generic specifier, which may not produce the same behavior:
//ORIGINAL LINE: template<class T>

public class Matrix4D
{
	public float[] Value = new float[16];
	/*float operator[](int x,int y)
	{
		return Value[x+y*4];
	}  */
	public static void translate(float[] m,float x,float y)
	{
		m[3]+=x;
		m[7]+=y;
	}
	public static void translate(float[] m,float x,float y,float z)
	{
		m[3] +=x;
		m[7] +=y;
		m[11]+=z;
	}
}