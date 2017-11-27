package com.kos.walkerinnight.NTTochk.functions;


import com.kos.walkerinnight.NTTochk.vector3d;

public class TFunctionXZPlace extends IFunctionXZ
{
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
		public float sdvigy;
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
		public TFunctionXZPlace(float Sdvigy)
		{
		sdvigy = Sdvigy;
		}
		public void dispose()
		{
		}
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
	@Override
	public float Main(float x,float z) //Сама функция
	{
		return sdvigy;
	}
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
	@Override
	public float Different(float x,float z) //Её дифференциал
	{
	   return 0;

	}
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
	@Override
	public vector3d Normal(float x, float z) //Функция нормали
	{
		return new vector3d(0.0,1,0);
	}
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
	@Override
	public float DifferentX(float x,float z) //Её дифференциал по X
	{
		return 0;
	}
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
	@Override
	public float DifferentZ(float x,float z) //Её дифференциал по Z
	{
		return 0;
	}

}