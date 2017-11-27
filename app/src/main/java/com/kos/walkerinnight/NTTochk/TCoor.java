package com.kos.walkerinnight.NTTochk;

//C++ TO JAVA CONVERTER TODO TASK: The original C++ template specifier was replaced with a Java generic specifier, which may not produce the same behavior:
//ORIGINAL LINE: template<class T>
public class TCoor<T>
{
	public T X;
	public T Y;
	public TCoor()
	{

	}
	public TCoor(T x,T y)
	{
		X = x;
		Y = y;
	}
//C++ TO JAVA CONVERTER TODO TASK: The original C++ template specifier was replaced with a Java generic specifier, which may not produce the same behavior:
//ORIGINAL LINE: template<class R>
	@SuppressWarnings("unchecked")
	public <R>TCoor(TCoor<R> xy)
	{
		X = (T)xy.X;
		Y = (T)xy.Y;
	}

	public final String toString()
	{
		return X.toString() + "; " + Y.toString();
	}

	public final T SetX(T newdata)
	{
		return X = newdata;
	}
//C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: __property T getx()const
	public final T getx()
	{
		return X;
	}


	public final T SetY(T newdata)
	{
		return Y = newdata;
	}
//C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: __property T gety() const
	 public final T gety()
	 {
		return Y;
	 }
	/*property	T x	//Свойство 
	{
		T get()
		{
			return X;
		}
		void set(const T value)
		{
			X=value;
			
		}
	} */ 
}