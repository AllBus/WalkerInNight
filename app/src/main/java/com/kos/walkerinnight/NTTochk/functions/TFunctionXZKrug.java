package com.kos.walkerinnight.NTTochk.functions;

public class TFunctionXZKrug extends IFunctionXZ
{
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
		public float coefficient ;
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
		public float miny ;
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
		public float maxy;
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
		public TFunctionXZKrug(float Coefficient,float Miny,float Maxy)
		{
		coefficient = Coefficient;
		miny = Miny;
		maxy = Maxy;
		}
		public void dispose()
		{
		}

//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
	@Override
	public float Main(float x,float z) //Сама функция
	{
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
		float rad = (float) (Math.sqrt(x * x + z * z) * coefficient);
		return Math.min(Math.max(rad,miny),maxy);
	}
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
	@Override
	public float Different(float x,float z) //Её дифференциал
	{
	   return 0; //Нужно пересчитать
	}


//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
	@Override
	public float DifferentX(float x,float z) //Её дифференциал по X
	{ //Нужно пересчитать
		return 0;
	}
//C++ TO JAVA CONVERTER TODO TASK: The typedef 'float' was defined within a preprocessor conditional and cannot be replaced in-line:
	@Override
	public float DifferentZ(float x,float z) //Её дифференциал по Z
	{ //Нужно пересчитать
		return 0;
	}

}
///#endif