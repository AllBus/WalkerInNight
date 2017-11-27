package com.kos.walkerinnight.NTTochk;

//C++ TO JAVA CONVERTER TODO TASK: The original C++ template specifier was replaced with a Java generic specifier, which may not produce the same behavior:
//ORIGINAL LINE: template<class T>
public class TLineUr<T>
{
	public T A ;
	public T B ;
	public T C ;
	public TLineUr()
	{
	}
	public TLineUr(T a,T b,T c)
	{
		A = a;
		B = b;
		C = c;
	}
	/*public TLineUr(TCoor<T> M, TCoor<T> N)
	{
		A = M.Y - N.Y;
		B = N.X - M.X;
		C = M.X * N.Y - N.X * M.Y;
	}*/

	//Расстояние от прямой до точки M
	/*public final float GetRast(TCoor<T> M)
	{
		return Math.abs(A * M.X + B * M.Y + C) / Math.sqrt(A * A + B * B);
	}*/
}