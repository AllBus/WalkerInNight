package com.kos.walkerinnight.NTTochk.functions;

public class TFunctionXZLine extends IFunctionXZ
{
		public TFunctionXZLine()
		{
		}
		public void dispose()
		{
		}

	@Override
	public float Main(float x,float z) //Сама функция
	{
		return x + z;
	}

	@Override
	public float Different(float x,float z) //Её дифференциал
	{
	   return 0;

	}


	@Override
	public float DifferentX(float x,float z) //Её дифференциал по X
	{
		return 1;
	}

	@Override
	public float DifferentZ(float x,float z) //Её дифференциал по Z
	{
		return 1;
	}

}