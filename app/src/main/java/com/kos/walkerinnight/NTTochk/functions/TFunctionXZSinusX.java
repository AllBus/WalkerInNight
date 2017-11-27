package com.kos.walkerinnight.NTTochk.functions;

public class TFunctionXZSinusX extends IFunctionXZ
{
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
		public float coefficient ;
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
		public float period ;
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
		public float sdvigx ;
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
		public TFunctionXZSinusX(float Period,float Coefficient,float Sdvigx)
		{
		coefficient = Coefficient;
		period = Period;
		sdvigx = Sdvigx;
		}
		public void dispose()
		{
		}

//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
	@Override
	public float Main(float x,float z) //Сама функция
	{
		return (float)(Math.sin((x + sdvigx) * 2 * Math.PI / period) * coefficient);
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
		return (float)(2 * Math.PI / period * Math.cos(x * 2 * Math.PI / period) * coefficient);
	}
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
	@Override
	public float DifferentZ(float x,float z) //Её дифференциал по Z
	{
		return 0;
	}


}