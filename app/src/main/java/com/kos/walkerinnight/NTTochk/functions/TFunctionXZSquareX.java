package com.kos.walkerinnight.NTTochk.functions;

public class TFunctionXZSquareX extends IFunctionXZ
{
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
		public float coefficient ;
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
		public TFunctionXZSquareX(float Coefficient)
		{
		coefficient = Coefficient;
		}
		public void dispose()
		{
		}
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
	@Override
	public float Main(float x,float z) //Сама функция
	{
		return x * x * coefficient;
	}
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
	@Override
	public float Different(float x,float z) //Её дифференциал
	{
	   return 0;
	}


//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
	@Override
	public float DifferentX(float x,float z) //Её дифференциал по X
	{
		return x * 2 * coefficient;
	}
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
	@Override
	public float DifferentZ(float x,float z) //Её дифференциал по Z
	{
		return 0;
	}

}