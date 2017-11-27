package com.kos.walkerinnight.NTTochk.functions;

//C++ TO JAVA CONVERTER TODO TASK: C++ template specifiers with non-type parameters cannot be converted to Java:
//ORIGINAL LINE: template <int C>
public class TFunctionXZPolynomX extends IFunctionXZ
{
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
	public static int C=10;
	public float[] coefficient = new float[C];

	public TFunctionXZPolynomX()
	{
		for (int i = 0;i < C;i++)
		{
			coefficient[i] = 1.0f;
		}
	}
	public void dispose()
	{
	}
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
	@Override
	public float Main(float x,float z) //Сама функция
	{
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
		float ret = 0;
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
		float xpow = 1;
		for (int i = 0;i < C;i++)
		{
			ret += xpow * coefficient[i];
			xpow *= x;
		}
		return ret;
	}

//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
	@Override
	public float DifferentX(float x,float z) //Её дифференциал по X
	{
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
		float ret = 0;
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
		float xpow = 1;
		for (int i = 1;i < C;i++)
		{
			ret += xpow * coefficient[i] * (i);
			xpow *= x;
		}
		return ret;
	}
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
	@Override
	public float DifferentZ(float x,float z) //Её дифференциал по Z
	{
		return 0;
	}
}